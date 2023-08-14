<template>
<!-- <p> workout table goes here</p> -->
 <table width="100%">
  <thead>
    <tr>
      <th>Type of Workout</th>
      <th>Excerise</th>
      <th>Date</th>
      <th>Duration (minutes)</th>
      <th>Workout Notes</th>
      </tr>
  </thead>
  <tbody>
    <tr v-for="workout in this.$store.state.workouts" v-bind:key="workout.id">
      <td> {{workout.workout_type}} </td>
      <td> {{workout.exercise}} </td> 
      <td> {{workout.workout_date}} </td>
      <td> {{workout.workout_duration_minutes}} </td>
      <td> {{workout.workout_notes}} </td>
    </tr>
  </tbody> 
 </table>    
</template>

<script>
import workoutService from '../services/WorkoutService'


export default {
  name: "display-workout" , 
   data(){
      return {
      workouts: this.$store.state.workouts
     }
   }, 

   methods: {
    updateWorkoutLog() {

    
      workoutService
          .getWorkoutsByUsername(this.$store.user.username)
          .then((response) => {
           if (response.status == 200) {
             this.$store.commit("SET_WORKOUTS", response.data);
            //  this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
   },
    getWorkouts() {
    
      workoutService
          .getWorkoutsByUsername(this.$store.user.username)
          .then((response) => {
           if (response.status == 200) {
             this.$store.commit("SET_WORKOUTS", response.data);
            //  this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
   }


   } 


}
</script>

<style>

</style>