import axios from 'axios';

export default {


  getWorkouts() {
    return axios.get('/workouts')

  },
  
  addWorkout(workout) {
    return axios.post(`${workout.username}/workouts`, workout)
  },

  updateWorkout(workout){
    return axios.put(`${workout.username}/workouts`, workout)
  }
}