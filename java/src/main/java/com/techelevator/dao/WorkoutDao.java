package com.techelevator.dao;

import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import com.techelevator.model.UserDto;
import com.techelevator.model.WorkoutDto;

import java.time.LocalDate;
import java.util.List;
public interface WorkoutDao {

    List<WorkoutDto> getWorkouts();
    List<WorkoutDto> getWorkoutsByUsername(String username);
    WorkoutDto getWorkoutById(int id);
    WorkoutDto getWorkoutByDate(LocalDate date);
    WorkoutDto createWorkout(WorkoutDto newWorkout);
    WorkoutDto updateWorkoutDto(WorkoutDto updateWorkout);

}
