package com.techelevator.model;

public class WorkoutDataDto {

    private int numberOfWorkouts;
    private double totalMinutes;


    public int getNumberOfWorkouts() {
        return numberOfWorkouts;
    }

    public void setNumberOfWorkouts(int numberOfWorkouts) {
        this.numberOfWorkouts = numberOfWorkouts;
    }

    public double getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(double totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public WorkoutDataDto(int numberOfWorkouts, double totalMinutes) {
        this.numberOfWorkouts = numberOfWorkouts;
        this.totalMinutes = totalMinutes;
    }

    public WorkoutDataDto() {}

    @Override
    public String toString() {
        return "WorkoutDataDto{" +
                "numberOfWorkouts=" + numberOfWorkouts +
                ", totalMinutes=" + totalMinutes +
                '}';
    }
}
