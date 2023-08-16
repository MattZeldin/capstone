<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">Home</router-link>
      &nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'my-workout' }"
        v-if="$store.state.token != ''"
        >My Workouts</router-link
      >&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'workout-timer' }"
        v-if="$store.state.token != ''"
        >Workout Timer</router-link
      >&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'exercise-assistance' }"
        v-if="$store.state.token != ''"
        >Exercise Assistance</router-link
      >&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'calendar' }"
        v-if="$store.state.token != ''"
        >My Calendar</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'profile' }"
        v-if="$store.state.token != ''"
        >My Profile</router-link
      >&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <loading v-show="isLoading"/>
    <router-view v-show="!isLoading"/>
  </div>
</template>

<script>
import Loading from "./components/Loading";

export default {
  name: "Inventory",
  components: {
    Loading,
  },
  data() {
    return {
      isLoading: true,
    };
  },

  

   mounted() {
       console.log("Loading")
     setTimeout(() => {
       this.isLoading = false;
     }, 1000);
   },
};
</script>

<style>
/*
  blue logo: #1926ef
  light blue logo: #19c2ff 
  skyblue
  darkblue
  */
#loading {
  display: grid;
  justify-items: center;
  margin-top: 5%;
}
.img {
  position: relative;
  width: 800px;
  height: 800px;
  border-radius: 25px;
}

body {
  background-color: #1926ef;
  font-family: "Share Tech Mono", sans-serif;
  font-size: 20px;
}

h1 {
  font-size: 10vh;
  color: white;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
  text-shadow: 0px 0px 10px black;
  margin: 50px;
}

#nav {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  background-color: black;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
  outline: 3px solid darkblue;
}

#app {
  background-color: skyblue;
  color: black;
  outline: 3px solid darkblue;
}

#app > div:nth-child(2) {
  padding-left: 5%;
  padding-right: 5%;
  padding-bottom: 5%;
}
/*for nav bar only*/
/* unvisited link */
a:link {
  color: white;
}
/* visited link */
a:visited {
  color: #19c2ff;
}
/* mouse over link */
a:hover {
  color: cyan;
}
/* selected link */
a:active {
  color: #1926ef;
}
</style>