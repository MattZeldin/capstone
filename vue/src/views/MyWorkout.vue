<template>
  <div>
      <h2>My workouts page </h2>
      <button v-on:click="addWorkout=true">Add workout</button>
      <form id="button" v-show="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workoutType">Workout type:</label>
        <input id="workoutType" type="text" v-model="workout.workout_type" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="exercise">Exercise:</label>
        <input id="exercise" type="text" v-model="workout.exercise" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDate">Workout date:</label>
        <input id="workoutDate" type="date" v-model="workout.workout_date" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDuration">Workout duration (minutes):</label>
        <input id="workoutDuration" type="text" v-model="workout.workout_duration_minutes" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutNotes">Workout notes:</label>
        <textarea id="workoutNotes" rows="4" cols="50" v-model="workout.workout_notes" required="false" />
      </div>
 
      <input type="submit" value="Save" v-on:click.prevent="updateWorkouts" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
      <display-workout></display-workout>
  </div>
</template>

<script>
import DisplayWorkout from "../components/DisplayWorkout.vue"
import workoutService from "../services/WorkoutService";

export default {
    name: "my-workout",
    components: {
        DisplayWorkout
    },
    data() {
        return {
            addWorkout: false,
            workout: {
                workout_type: "",
                exercise: "",
                workout_date: "",
                workout_duration_minutes: "",
                workout_notes: "",
                username: this.$store.state.user.username
            }
        }
    },
    methods: {
        updateWorkouts() {
            // a commit line that updates the workouts array in the vuex
            workoutService
            .addWorkout(this.workout)
            .then((response) => {
          if (response.status == 200) {
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
        },
        resetForm(){
            this.addWorkout = false;
        }
    }

};
</script>

<style>

</style>