<template>
  <div>
      <h1>My Workouts</h1>
      <button id="button2" v-on:click="addWorkout=true">Add workout</button>
      <!-- <router-link v-bind:to="{name:}"> -->
      <form id="form" v-show="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workoutType">Workout type:</label>
        <!-- <input id="field" type="text" v-model="workout.workout_type" required="false" /> -->
        <select id="field" 
          v-model="workout.workout_type" required="true">
          <option value="Weightlifting">Weightlifting</option>
          <option value="Cardio">Cardio</option>
          <option value="Class">Workout Class</option>
          <option value="Sports">Sports</option>
          <option value="Other">Other</option>
        </select>
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
        <input id="field" type="date" v-model="workout.workout_date" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDuration">Workout duration (minutes):</label>
        <input id="field" type="text" v-model="workout.workout_duration_minutes" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutNotes">Workout notes:</label>
        <textarea id="field" rows="4" cols="50" v-model="workout.workout_notes" required="false" />
      </div>
 
      <input id="button2" type="submit" value="Save" v-on:click="updateWorkouts" />
      <input id="button2" type="button" value="Cancel" v-on:click="resetForm" />
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
            // this.$store.state.workouts.unshift(this.workout) 
            workoutService
            .addWorkout(this.workout)
            .then((response) => {
          if (response.status == 201) {
            // this.$router.push("/");
            this.$store.commit("SET_WORKOUTS", response.data);
            this.$router.push("/my-workout");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
        //  workoutService
        //   .getWorkouts()
        //   .then((response) => {
        //    if (response.status == 200) {
        //      this.$store.commit("SET_WORKOUTS", response.data);
        //      this.addWorkout = false;
        //      this.$router.push("/my-workout");
        //   }
        // })
        // .catch((error) => {
        //   const response = error.response;

        //   if (response.status === 401) {
        //     this.invalidCredentials = true;
        //   }
        // });
        },

        resetForm(){
            this.addWorkout = false;
        }
    }
    ,
    beforeMount() {
      workoutService
          .getWorkoutsByUsername(this.$store.state.user.username)
          .then((response) => {
           if (response.status == 200) {
             this.$store.commit("SET_WORKOUTS", response.data);
             this.addWorkout = false;
             this.$router.push("/my-workout");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }

};
</script>

<style scoped>

#app > div:nth-child(3) {
  display: grid;
  justify-items: center;
  padding-bottom: 5%;
}

h1 {
  font-size: 10vh;
  color: white;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
  text-shadow: 0px 0px 5px black;
}

#form {
  margin: auto;
  width: 50%;
}

#field_name {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  margin: 10px;
  font-size: 25px
}

#field {
  display: flex;
  justify-content: center;
  align-content: center;
  padding: 10px;
  margin: auto;
  margin-bottom: 10px;
  min-width: 50%;
  border-radius: 10px;
  border: 5px solid darkblue;
  font-size: 20px
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