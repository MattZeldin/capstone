import axios from 'axios';

export default {

  getWorkouts(workout) {
    return axios.get(`${workout.username}/workouts`)  
  },
  
  addWorkout(workout) {
    return axios.post(`${workout.username}/workouts`, workout)
  },

  updateWorkout(workout){
    return axios.put(`${workout.username}/workouts`, workout)
  }
}