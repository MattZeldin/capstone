<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''">My Profile</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'LogWorkout' }" v-if="$store.state.token != ''">Log Workout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'WorkoutTimer' }" v-if="$store.state.token != ''">Workout Timer</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'ExerciseAssistance' }" v-if="$store.state.token != ''">Exercise Assistance</router-link>&nbsp;|&nbsp;

    </div>
    <router-view />
  </div>
</template>

<style >
  /* unvisited link */
  a:link {
  color: red;
  }
  /* visited link */
  a:visited {
  color: green;
  }
  /* mouse over link */
  a:hover {
    color: hotpink;
  }
  /* selected link */
  a:active {
  color: blue;
  }
</style>