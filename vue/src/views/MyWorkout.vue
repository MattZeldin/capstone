<template>
  <div>
    <h1>My Workouts Page</h1>
    <button id="button2" v-on:click="addWorkout = true">Add workout</button>
    <form id="form" v-show="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workoutType">Workout type:</label>
        <input
          id="field"
          type="text"
          v-model="workout.workoutType"
          required="false"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="exercise">Exercise:</label>
        <input
          id="field"
          type="text"
          v-model="workout.exercise"
          required="false"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDate">Workout date:</label>
        <input
          id="field"
          type="date"
          v-model="workout.workoutDate"
          required="false"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDuration"
          >Workout duration (minutes):</label
        >
        <input
          id="field"
          type="text"
          v-model="workout.workoutDuration"
          required="false"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutNotes">Workout notes:</label>
        <textarea
          id="field"
          rows="4"
          cols="50"
          v-model="workout.workoutNotes"
          required="false"
        />
      </div>

      <input
        id="button2"
        type="submit"
        value="Save"
        v-on:click.prevent="updateWorkouts"
      />
      <input id="button2" type="button" value="Cancel" v-on:click="resetForm" />
    </form>
    <display-workout></display-workout>
  </div>
</template>

<script>
import DisplayWorkout from "../components/DisplayWorkout.vue";

export default {
  name: "my-workout",
  components: {
    DisplayWorkout,
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
        username: "",
      },
    };
  },
};
</script>

<style>

h1 {
  font-size: 10vh;
  color: white;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
  text-shadow: 0px 0px 10px cyan;
}

#button2 {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  margin-left: 38%;
  margin-right: 38%;
  margin-bottom: 10px;
  margin-top: 10px;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 5px;
  min-width: 25%;
}

#button2:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}

</style>