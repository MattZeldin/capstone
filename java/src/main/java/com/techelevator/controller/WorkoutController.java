package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.WorkoutDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.util.List;

@RestController
@CrossOrigin
public class WorkoutController {
    private WorkoutDao workoutDao;

    public WorkoutController(WorkoutDao workoutDao){
        this.workoutDao = workoutDao;
    }

    // handle get request for all workouts in database. Maybe change so it gets all workouts for a given user
    @RequestMapping(path = "/{username}/workouts", method = RequestMethod.GET)
    public List<WorkoutDto> workouts(@PathVariable String username){
        List<WorkoutDto> workouts;
        try {
            workouts = workoutDao.getWorkoutsByUsername(username);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }
        return workouts;
    }

    // handle post request for adding a new workout to the database
    @RequestMapping(path = "/{username}/workouts", method = RequestMethod.POST)
    public void addWorkout(@PathVariable String username, @RequestBody WorkoutDto newWorkout) {
        try {
            WorkoutDto workout = workoutDao.createWorkout(newWorkout);
            if (workout == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Logging new workout failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Logging new workout failed.");
        }
    }

    // handle update workout request
//    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/{username}/workouts", method = RequestMethod.PUT)
    public WorkoutDto updateWorkout(@PathVariable String username, @RequestBody WorkoutDto updatedWorkout){
        WorkoutDto result;
        try {
            result = workoutDao.updateWorkoutDto(updatedWorkout);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Update workout failed. " + e.getMessage());
        }
        return result;
    }

    //get a workout data DTO for the home page
    @RequestMapping(path = "/{username}/workouts/userData", method = RequestMethod.GET)
    public WorkoutDataDto getData(@PathVariable String username){
        WorkoutDataDto data;
        try {
            WorkoutDataDto result = new WorkoutDataDto(workoutDao.countWorkouts(username), workoutDao.calculateAverageMinutes(username));
            data = result;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Data posting failed. " + e.getMessage());
        }
        return data;
    }
}
