<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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