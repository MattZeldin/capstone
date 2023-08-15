<template>
  <!-- <p> workout table goes here</p> -->
  <div>
  <table id="table" width="100%">
    <thead>
      <tr>
        <th id="tableItems">Type of Workout</th>
        <th id="tableItems">Excerise</th>
        <th id="tableItems">Date</th>
        <th id="tableItems">Duration (minutes)</th>
        <th id="tableItems">Workout Notes</th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="workout in this.$store.state.workouts"
        v-bind:key="workout.id"
        v-bind:value="workout.id"
      >
        <td id="tableItems">{{ workout.workout_type }}</td>
        <td id="tableItems">{{ workout.exercise }}</td>
        <td id="tableItems">{{ workout.workout_date }}</td>
        <td id="tableItems">{{ workout.workout_duration_minutes }}</td>
        <td id="tableItems">{{ workout.workout_notes }}</td>
        <td>
          <button id="update" class="btnUpdate" v-on:click.prevent="handleUpdateClick(workout)">
            Update
          </button>
        </td>
        <td>
          <button id="delete" class="btnDelete" v-on:click="handleDelete(workout)">
            Delete
          </button>
        </td>
        
      </tr>
    </tbody>
  </table>

  <form id="form" v-show="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workoutType">Workout type:</label>
        <input id="field" type="text" v-model="updatedWorkout.workout_type" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="exercise">Exercise:</label>
        <input
          id="field"
          type="text"
          v-model="updatedWorkout.exercise"
          required="false"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDate">Workout date:</label>
        <input id="field" type="date" v-model="updatedWorkout.workout_date" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutDuration">Workout duration (minutes):</label>
        <input id="field" type="text" v-model="updatedWorkout.workout_duration_minutes" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="workoutNotes">Workout notes:</label>
        <textarea id="field" rows="4" cols="50" v-model="updatedWorkout.workout_notes" required="false" />
      </div>
 
      <input id="button2" type="submit" value="Save" v-on:click="handleUpdate(updatedWorkout)" />
      <input id="button2" type="button" value="Cancel" v-on:click="resetForm" />
    </form>

  </div>
  


</template>

<script>
import workoutService from "../services/WorkoutService";

export default {
  name: "display-workout",
  data() {
    return {
      workouts: this.$store.state.workouts,
      fakeVar: "",
      addWorkout: false,
      updatedWorkout: {}
    };
  },

  methods: {
    handleDelete(workout) {
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
    handleUpdateClick(workout){
      this.addWorkout = true;
      this.updatedWorkout.id = workout.id;
      this.updatedWorkout.workout_type = workout.workout_type;
      this.updatedWorkout.exercise = workout.exercise;
      this.updatedWorkout.workout_date = workout.workout_date;
      this.updatedWorkout.workout_duration_minutes = workout.workout_duration_minutes;
      this.updatedWorkout.workout_notes = workout.workout_notes;
      this.updatedWorkout.username = workout.username;
    }
    ,
    handleUpdate(workout) {
      workoutService
        .updateWorkout(workout)
        .then((response) => {
          if (response.status == 200) {
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
    },

        resetForm(){
            this.addWorkout = false;
        }
  },
};
</script>

<style scoped>

#delete {
  background-color: red;
  border: 3px solid orange;
  color: white;
  font-family: "Share Tech Mono", sans-serif;
  padding: 10px 10px;
  justify-content: center;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 5px;
  margin-left: 10px;
}

#update:hover {
  background-color: red;
  color: yellow;
  text-shadow: 0px 0px 10px yellow;
}

#update {
  background-color: red;
  border: 3px solid orange;
  color: white;
  font-family: "Share Tech Mono", sans-serif;
  padding: 10px 10px;
  justify-content: center;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 5px;
  margin-left: 10px;
}

#delete:hover {
  background-color: red;
  color: yellow;
  text-shadow: 0px 0px 10px yellow;
}




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