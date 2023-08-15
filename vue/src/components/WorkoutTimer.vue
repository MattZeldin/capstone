<template>
  <div id="clock">
    <span class="time">{{ time }}</span>

    <div class="btn-container">
      <a id="start" v-on:click="start">Start</a>
      <a id="stop" v-on:click="stop">Stop</a>
      <a id="reset" v-on:click="reset">Reset</a>
      <a id="save" v-on:click="saveTime">Save</a>
    </div>


    <!-- drop down form for logging a workout  -->
    <form v-if="addWorkout === true">
      <div class="form-element">
        <label id="field_name" for="workoutType">Workout type:</label>
        <input id="field" type="text" v-model="workout.workout_type" required="false" />
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
 
      <input id="button2" type="submit" value="Save" v-on:click.prevent="updateWorkouts" />
      <input id="button2" type="button" value="Cancel" v-on:click="resetForm" />
    </form>



    <div class="text">
      <a href="https://codepen.io/raphael_octau" target="_blank"
        >@raphael_octau</a
      >
    </div>
  </div>
</template>

<script>
import workoutService from '../services/WorkoutService'

export default {
  name: "workoutTimer",
  data() {
    return {
      time: "00:00:00.000",
      timeBegan: null,
      timeStopped: null,
      started: null,
      stoppedDuration: 0,
      running: false,
      addWorkout: false,
      workout: {
                workout_type: "",
                exercise: "",
                workout_date: "",
                workout_duration_minutes: "",
                workout_notes: "",
                username: this.$store.state.user.username
            }
    };
  },
  methods: {
    start() {
      if (this.running) return;

      if (this.timeBegan === null) {
        this.reset();
        this.timeBegan = new Date();
      }

      if (this.timeStopped !== null) {
        this.stoppedDuration += new Date() - this.timeStopped;
      }

      this.started = setInterval(this.clockRunning, 10);
      this.running = true;
    },

    stop() {
      this.running = false;
      this.timeStopped = new Date();
      clearInterval(this.started);
    },

    reset() {
      this.running = false;
      clearInterval(this.started);
      this.stoppedDuration = 0;
      this.timeBegan = null;
      this.timeStopped = null;
      this.time = "00:00:00.000";
    },
    saveTime() {
      this.running = false;
      this.timeStopped = new Date();
      clearInterval(this.started);
      this.addWorkout = true;
      this.workout.workout_duration_minutes = 60 * parseInt(this.time.substring(0,2)) + parseInt(this.time.substring(3,5));
      
      
    },
    updateWorkouts() {
            // a commit line that updates the workouts array in the vuex
            // this.$store.state.workouts.unshift(this.workout) 
            workoutService
            .addWorkout(this.workout)
            .then((response) => {
          if (response.status == 200) {
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
    },

    resetForm(){
        this.addWorkout = false;
    },

    clockRunning() {
      let currentTime = new Date();
      let timeElapsed = new Date(
        currentTime - this.timeBegan - this.stoppedDuration
      );
      let hour = timeElapsed.getUTCHours();
      let min = timeElapsed.getUTCMinutes();
      let sec = timeElapsed.getUTCSeconds();
      let ms = timeElapsed.getUTCMilliseconds();

      this.time =
        this.zeroPrefix(hour, 2) +
        ":" +
        this.zeroPrefix(min, 2) +
        ":" +
        this.zeroPrefix(sec, 2) +
        "." +
        this.zeroPrefix(ms, 3);
    },

    zeroPrefix(num, digit) {
      let zero = "";
      for (let i = 0; i < digit; i++) {
        zero += "0";
      }
      return (zero + num).slice(-digit);
    },
  },
};

//     var clock = new Vue({
//   el: '#clock',
//   data: {

//   }
// }),

// var timeBegan = null
// , timeStopped = null
// , stoppedDuration = 0
// , started = null
// , running = false;

// document.getElementById("start").addEventListener("click", start);
// document.getElementById("stop").addEventListener("click", stop);
// document.getElementById("reset").addEventListener("click", reset);

// function start() {
//   if(running) return;

//   if (timeBegan === null) {
//     reset();
//     timeBegan = new Date();
//   }

//   if (timeStopped !== null) {
//     stoppedDuration += (new Date() - timeStopped);
//   }

//   started = setInterval(clockRunning, 10);
//   running = true;
// }

// function stop() {
//   running = false;
//   timeStopped = new Date();
//   clearInterval(started);
// }

// function reset() {
//   running = false;
//   clearInterval(started);
//   stoppedDuration = 0;
//   timeBegan = null;
//   timeStopped = null;
//   clock.time = "00:00:00.000";
// }

// function clockRunning(){
//   var currentTime = new Date()
//   , timeElapsed = new Date(currentTime - timeBegan - stoppedDuration)
//   , hour = timeElapsed.getUTCHours()
//   , min = timeElapsed.getUTCMinutes()
//   , sec = timeElapsed.getUTCSeconds()
//   , ms = timeElapsed.getUTCMilliseconds();

//   clock.time =
//     zeroPrefix(hour, 2) + ":" +
//     zeroPrefix(min, 2) + ":" +
//     zeroPrefix(sec, 2) + "." +
//     zeroPrefix(ms, 3);
// };

// function zeroPrefix(num, digit) {
//   var zero = '';
//   for(var i = 0; i < digit; i++) {
//     zero += '0';
//   }
//   return (zero + num).slice(-digit);
// }
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Share+Tech+Mono&display=swap");

html, body {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 100%;
}

#clock > span {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 250px;
  font-size: 150px
}

#clock {
  order: 0;
  flex: 0 1 auto;
  align-self: center;
  color: white;
}

#clock.time {
  font-size: 6.5em;

}
#clock > span {
  margin: 10px;
  text-shadow: 0px 0px 10px black;
  -webkit-text-stroke-width: 2px;
  -webkit-text-stroke-color: black;
  
}

#clock > form {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  padding: 25px;
  justify-content: center;
  align-content: center;
  border: 10px solid darkblue;
  border-radius: 25px;
  background: skyblue;
}

#clock > div.text > a:hover {
  color: cyan;
}

#clock > form > div:nth-child(1) {
  padding: 10px
}
#clock > form > div:nth-child(2) {
  padding: 10px
}
#clock > form > div:nth-child(3) {
  padding: 10px
}
#clock > form > div:nth-child(4) {
  padding: 10px
}
#clock > form > div:nth-child(5) {
  padding: 10px
}


#field {
  border-radius: 10px;
  border: 3px solid darkblue;
  min-height: 30px;
}

#field_name {
  color: black;
  text-shadow: 0px 0px 0px black;
}

#button2 {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  padding: 10px 10px;
  justify-content: center;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 5px;
  min-width: 25%;
  max-width: 25%;
  margin-left: 125px;
  margin-top: 10px
}

#button2:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}

.text > a {
  text-decoration: none;
  color: inherit;

  transition: color 0.1s ease-out;
}

.text > a:hover {
  color: white;
}

.btn-container {
  display: flex;
  font-size: 4vh;
  text-shadow: 0px 0px 10px black;
  -webkit-text-stroke-width: 2px;
  -webkit-text-stroke-color: black;
}

.btn-container > a {
  text-align: center;
  font-family: "Share Tech Mono", sans-serif;
  background: transparent;
  border: none;
  color: white;
  text-transform: uppercase;
  font-size: 10vh;
  cursor: pointer;
  flex-grow: 1;
  transition: color 0.1s ease-out;
}

.btn-container > a:hover {
  color: cyan;
}


label {
  color: white;
  text-shadow: 0px 0px 10px black;
}
</style>