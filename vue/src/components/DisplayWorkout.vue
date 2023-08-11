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
    <tr>
      <td> {{}} </td>
      <td> {{}} </td> 
      <td> {{}} </td>
      <td> {{}} </td>
      <td> {{}} </td>
    </tr>
  </tbody> 
 </table>    
</template>

<script>
// import WorkoutService from '../services/WorkoutService'


export default {
  name: "display-workout" , 
   data(){
      return {
      workouts: []
     }
   }, 

   methods: {

   }, 

  //  created(){
  //    WorkoutService.getWorkouts(this.username).then((response) => {
  //      this.$store.commit("SET_WORKOUTS" , this.workouts);
  //    })
  //    .catch((error) => {
  //      if (error.response.status == 400){
  //         this.invalidCredentials = true;      
  //          }
  //    });
  //  }
}
</script>

<style>

</style>