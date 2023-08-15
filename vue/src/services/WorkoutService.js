import axios from 'axios';

export default {


  getWorkouts() {
    return axios.get('/workouts')

  },

  getWorkoutsByUsername(username) {
    return axios.get(`${username}/workouts`)

  },
  
  addWorkout(workout) {
    return axios.post(`${workout.username}/workouts`, workout)
  },

  updateWorkout(workout){
    return axios.put(`/workouts`, workout)
  },
  
  deleteWorkoutById(workoutId){
    return axios.delete(`${workoutId}/workouts`)
  }
}