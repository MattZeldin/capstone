package com.techelevator.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
public class WorkoutDto {
    // properties
    private int id;
    private String workout_type;
    private String exercise;
    private LocalDate workout_date;
    private int workout_duration_minutes;
    private String workout_notes;
    private String username;

    // constructors

    public WorkoutDto(int id, String workout_type, String exercise, LocalDate workout_date, int workout_duration_minutes, String workout_notes, String username) {
        this.id = id;
        this.workout_type = workout_type;
        this.exercise = exercise;
        this.workout_date = workout_date;
        this.workout_duration_minutes = workout_duration_minutes;
        this.workout_notes = workout_notes;
        this.username = username;
    }
    public WorkoutDto(){};

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkout_type() {
        return workout_type;
    }

    public void setWorkout_type(String workout_type) {
        this.workout_type = workout_type;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public LocalDate getWorkout_date() {
        return workout_date;
    }

    public void setWorkout_date(LocalDate workout_date) {
        this.workout_date = workout_date;
    }

    public int getWorkout_duration_minutes() {
        return workout_duration_minutes;
    }

    public void setWorkout_duration_minutes(int workout_duration_minutes) {
        this.workout_duration_minutes = workout_duration_minutes;
    }

    public String getWorkout_notes() {
        return workout_notes;
    }

    public void setWorkout_notes(String workout_notes) {
        this.workout_notes = workout_notes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // other methods

    @Override
    public String toString() {
        return "WorkoutDto{" +
                "workout_id=" + id +
                ", workout_type='" + workout_type + '\'' +
                ", exercise='" + exercise + '\'' +
                ", workout_date=" + workout_date +
                ", workout_duration_minutes=" + workout_duration_minutes +
                ", workout_notes='" + workout_notes + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
