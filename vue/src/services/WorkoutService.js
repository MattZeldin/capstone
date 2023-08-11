import axios from 'axios';

export default {

<<<<<<< HEAD
<<<<<<< HEAD
  getWorkouts(workout) {
    return axios.get(`${workout.username}/workouts`)  
=======
  getWorkouts() {
    return axios.get(`/workouts`)
>>>>>>> f0420464357ddd7929a2dc7bb693adf249db4d0c
=======
  getWorkouts() {
    return axios.get(`/workouts`)
>>>>>>> b819e754cdb3190251f804edc4398a66b9001258
  },
  
  addWorkout(workout) {
    return axios.post(`${workout.username}/workouts`, workout)
  },

  updateWorkout(workout){
    return axios.put(`${workout.username}/workouts`, workout)
  }
}