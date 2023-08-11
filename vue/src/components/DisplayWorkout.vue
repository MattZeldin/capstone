<template>
<!-- <p> workout table goes here</p> -->
 <table>
  <thead>
    <tr>
      <th>Type of Workout</th>
      <th>Excerise</th>
      <th>Date of Workout</th>
      <th>Duration of Workout in Minutes</th>
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
      <td> {{workout.username}} </td>
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
          .getWorkouts()
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