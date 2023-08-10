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
    public WorkoutDto createWorkout(WorkoutDto newWorkout) {

    };

    @Override
    public WorkoutDto updateWorkout(WorkoutDto updateWorkout) {

    };

    private WorkoutDto mapRowToWorkoutDto(SqlRowSet rs) {
        WorkoutDto workout = new WorkoutDto();
        workout.setId(rs.getInt("workout_id"));
        workout.setWorkout_type(rs.getString("workout_type"));
        workout.setExercise(rs.getString("exercise"));
        workout.setWorkout_date(rs.getDate("workout_date").toLocalDate());
        workout.setDuration_minutes(rs.getInt("workout_duration_minutes"));
        workout.setWorkout_notes(rs.getString("workout_notes"));
        workout.setUsername(rs.getString("username"));
        return workout;
    }

}
