package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import com.techelevator.model.UserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.WorkoutDto;
@Component
public class JdbcWorkoutDao implements WorkoutDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkoutDto> getWorkouts() {
        List<WorkoutDto> workouts = new ArrayList<>();
        String sql = "SELECT workout_id, workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username FROM workouts";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                WorkoutDto workout = mapRowToWorkoutDto(results);
                workouts.add(workout);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return workouts;
    };

    @Override
    public WorkoutDto getWorkoutById(int workoutId) {
        WorkoutDto workout = null;
        String sql = "SELECT workout_id, workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username FROM workouts WHERE workout_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);
            if (results.next()) {
                workout = mapRowToWorkoutDto(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return workout;
    };

    @Override
    public WorkoutDto getWorkoutByDate(LocalDate date) {
        WorkoutDto workout = null;
        String sql = "SELECT workout_id, workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username FROM workouts WHERE workout_date = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
            if (results.next()) {
                workout = mapRowToWorkoutDto(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return workout;
    };

    @Override
    public List<WorkoutDto> getWorkoutsByUsername(String username){
        List<WorkoutDto> workouts = new ArrayList<>();
        String sql = "SELECT workout_id, workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username FROM workouts WHERE username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                WorkoutDto workout = mapRowToWorkoutDto(results);
                workouts.add(workout);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return workouts;
    };

    @Override
    public WorkoutDto createWorkout(WorkoutDto workout) {
        WorkoutDto newWorkout = null;
        String insertWorkoutSql = "INSERT INTO workouts (workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username) values (?, ?, ?, ?, ?, ?) RETURNING workout_id";

        try {
            int newWorkoutId = jdbcTemplate.queryForObject(insertWorkoutSql, int.class, workout.getWorkout_type(), workout.getExercise(), workout.getWorkout_date(), workout.getWorkout_duration_minutes(), workout.getWorkout_notes(), workout.getUsername());
            newWorkout = getWorkoutById(newWorkoutId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newWorkout;
    };

    @Override
    public WorkoutDto updateWorkoutDto(WorkoutDto updatedWorkout) {
        WorkoutDto result;
        String sql = "UPDATE workouts SET workout_type = ?, exercise = ?, workout_date = ?, workout_duration_minutes = ?, workout_notes = ?, username = ? WHERE workout_id = ? RETURNING workout_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, updatedWorkout.getWorkout_type(), updatedWorkout.getExercise(), updatedWorkout.getWorkout_date(), updatedWorkout.getWorkout_duration_minutes(), updatedWorkout.getWorkout_notes(), updatedWorkout.getUsername(), updatedWorkout.getId());
            result = getWorkoutById(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return result;
    };

    @Override
    public int countWorkouts(String username) {
        int count = 0;
        String sql = "SELECT COUNT(workout_id) FROM workouts WHERE workout_date >= (SELECT CURRENT_DATE - EXTRACT(dow FROM CURRENT_DATE)::integer) AND username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                count = results.getInt("count");
            }
        } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
        }
        return count;
    }
    @Override
    public double calculateAverageMinutes(String username) {
        double avgMins = 0;
        String sql = "SELECT AVG(workout_duration_minutes) FROM workouts WHERE workout_date >= (SELECT CURRENT_DATE - EXTRACT(dow FROM CURRENT_DATE)::integer) AND username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()) {
                avgMins = results.getDouble("avg");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return avgMins;
    }


    private WorkoutDto mapRowToWorkoutDto(SqlRowSet rs) {
        WorkoutDto workout = new WorkoutDto();
        workout.setId(rs.getInt("workout_id"));
        workout.setWorkout_type(rs.getString("workout_type"));
        workout.setExercise(rs.getString("exercise"));
        workout.setWorkout_date(rs.getDate("workout_date").toLocalDate());
        workout.setWorkout_duration_minutes(rs.getInt("workout_duration_minutes"));
        workout.setWorkout_notes(rs.getString("workout_notes"));
        workout.setUsername(rs.getString("username"));
        return workout;
    }



}
