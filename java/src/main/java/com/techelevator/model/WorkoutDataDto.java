package com.techelevator.model;

public class WorkoutDataDto {

    private int numberOfWorkouts;
    private double averageMinutes;


    public int getNumberOfWorkouts() {
        return numberOfWorkouts;
    }

    public void setNumberOfWorkouts(int numberOfWorkouts) {
        this.numberOfWorkouts = numberOfWorkouts;
    }

    public double getAverageMinutes() {
        return averageMinutes;
    }

    public void setAverageMinutes(double averageMinutes) {
        this.averageMinutes = averageMinutes;
    }

    public WorkoutDataDto(int numberOfWorkouts, double averageMinutes) {
        this.numberOfWorkouts = numberOfWorkouts;
        this.averageMinutes = averageMinutes;
    }

    public WorkoutDataDto() {}

    @Override
    public String toString() {
        return "WorkoutDataDto{" +
                "numberOfWorkouts=" + numberOfWorkouts +
                ", averageMinutes=" + averageMinutes +
                '}';
    }
}
