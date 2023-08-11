import axios from 'axios';

export default {

<<<<<<< HEAD
  getWorkouts(workout) {
    return axios.get(`${workout.username}/workouts`)  
=======
  getWorkouts() {
    return axios.get(`/workouts`)
>>>>>>> f0420464357ddd7929a2dc7bb693adf249db4d0c
  },
  
  addWorkout(workout) {
    return axios.post(`${workout.username}/workouts`, workout)
  },

  updateWorkout(workout){
    return axios.put(`${workout.username}/workouts`, workout)
  }
}