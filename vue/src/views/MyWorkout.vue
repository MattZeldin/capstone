<template>
  <div>
      <h1>My workouts page </h1>
      <button v-on:click="addWorkout=true">Add workout</button>
      <form id="button" v-show="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workoutType">Workout type:</label>
        <input id="workoutType" type="text" v-model="workout.workoutType" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="exercise">Exercise:</label>
        <input id="exercise" type="text" v-model="workout.exercise" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDate">Workout date:</label>
        <input id="workoutDate" type="text" v-model="workout.workoutDate" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDuration">Workout duration (minutes):</label>
        <input id="workoutDuration" type="text" v-model="workout.workoutDuration" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutNotes">Workout notes:</label>
        <textarea id="workoutNotes" rows="4" cols="50" v-model="workout.workoutNotes" required="false" />
      </div>

      
      
      <input type="submit" value="Save" v-on:click.prevent="updateWorkouts" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
      <display-workout></display-workout>
  </div>
</template>

<script>
import DisplayWorkout from "../components/DisplayWorkout.vue"

export default {
    name: "my-workout",
    components: {
        DisplayWorkout
    },
    data() {
        return {
            addWorkout: false,
            workout: {
                workoutType: "",
                exercise: "",
                workoutDate: "",
                workoutDuration: "",
                workoutNotes: "",
                username: ""
            }
        }
    }

}
</script>

<style>

</style>