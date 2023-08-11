<template>
  <div>
      <button v-on:click="addWorkout=true">Add workout</button>
      <form id="form" v-show="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workout-type">Workout type:</label>
        <input id="workout-type" type="text" v-model="workout.workoutType" required="false" />
      </div>

      <div class="form-element">
        <label id="field_name" for="email">Email:</label>
        <input id="field" type="text" v-model="user.email" required="false" />
      </div>
      
      <input type="submit" value="Save" v-on:click.prevent="updateUser" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
      <display-workout></display-workout>
  </div>
</template>

<script>
import DisplayWorkout from "../components/DisplayWorkout.vue"

export default {
    name: "my-workouts",
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
                workoutDurationMinutes: 0,
                workoutNotes: "",
                username: ""
            }
        }
    }

}
</script>

<style>

</style>