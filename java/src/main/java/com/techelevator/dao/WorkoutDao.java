package com.techelevator.dao;

import com.techelevator.model.*;

import java.time.LocalDate;
import java.util.List;
public interface WorkoutDao {

    List<WorkoutDto> getWorkouts();
    List<WorkoutDto> getWorkoutsByUsername(String username);
    WorkoutDto getWorkoutById(int id);
    WorkoutDto getWorkoutByDate(LocalDate date);
    WorkoutDto createWorkout(WorkoutDto newWorkout);
<<<<<<< HEAD
    WorkoutDto updateWorkout(WorkoutDto updateWorkout);
    double calculateAverageMinutes(String username);
    int countWorkouts(String username);
=======
    WorkoutDto updateWorkoutDto(WorkoutDto updateWorkout);
>>>>>>> 4e944b9b3b69a3f2673a407751ce2d2121099df2

}
