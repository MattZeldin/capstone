<template>
<!-- <p> workout table goes here</p> -->
 <table id= 'table' width="100%">
  <thead>
    <tr>
      <th id= 'tableItems'>Type of Workout</th>
      <th id= 'tableItems'>Excerise</th>
      <th id= 'tableItems'>Date</th>
      <th id= 'tableItems'>Duration (minutes)</th>
      <th id= 'tableItems'>Workout Notes</th>
      </tr>
  </thead>
  <tbody>
    <tr v-for="workout in this.$store.state.workouts" v-bind:key="workout.id" v-bind:value="workout.id">
      <td id= 'tableItems'> {{workout.workout_type}} </td>
      <td id= 'tableItems'> {{workout.exercise}} </td> 
      <td id= 'tableItems'> {{workout.workout_date}} </td>
      <td id= 'tableItems'> {{workout.workout_duration_minutes}} </td>
      <td id= 'tableItems'> {{workout.workout_notes}} </td>
      <td>
            <button class="btnDelete" v-on:click="handleDelete(workout)">Delete</button>
          </td>
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
      workouts: this.$store.state.workouts,
      fakeVar : ""
     }
   },


   methods: {
    handleDelete(workout){
      workoutService
          .deleteWorkoutById(workout.id)
          .then((response) => {
           if (response.status == 204) {
            //  this.updateWorkoutLog();
            //  this.$router.push("/my-workout");
            this.fakeVar = "nothing";
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
        
        this.updateWorkoutLog();


    },
    updateWorkoutLog() {
      workoutService
          .getWorkoutsByUsername(this.$store.state.user.username)
          .then((response) => {
           if (response.status == 200) {
             this.$store.commit("SET_WORKOUTS", response.data);
            //  this.$router.push("my-workout");
            this.$router.go(0);
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

<style scoped>

#table {
  justify-content: space-around;
  background: #19c2ff;
  padding: 20px;

  border: 5px solid darkblue;
  border-radius: 10px;
}

#tableItems {
  padding: 20px;
  border: 3px solid #1926ef;
  background: white;
  border-radius: 10px;
  font-size: 25px;
  color: darkblue;
}

</style>