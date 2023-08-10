<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''">My Profile</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'LogWorkout' }" v-if="$store.state.token != ''">Log Workout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'workout-timer' }" v-if="$store.state.token != ''">Workout Timer</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'ExerciseAssistance' }" v-if="$store.state.token != ''">Exercise Assistance</router-link>&nbsp;|&nbsp;

    </div>
    <router-view />
  </div>
</template>

<style>
  /*
  blue logo: #1926ef
  light blue logo: #19c2ff 
  */

  body {
  background-color: #19c2ff;
  color: white;
  font-family: "Share Tech Mono", sans-serif;
  }

  #nav {
    display: flex;
    align-items: center;
    justify-content: center;

    background-color: black;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;

  }

  #app {
    background-color: darkblue;
    color: white;
  }

  #app > div:nth-child(2) {
    padding-left: 10%;
    padding-right: 10%;
    padding-top: 5%;
    padding-bottom: 10%;
  }

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