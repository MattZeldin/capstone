<template>
  <div class="home">
    <h1>Home</h1>
    <track-goals/>
<<<<<<< HEAD
    <quotes/>
=======
>>>>>>> b819e754cdb3190251f804edc4398a66b9001258
  </div>
</template>

<script>
import TrackGoals from "../components/TrackGoals.vue";
import Quotes from "../components/Quotes.vue";
export default {
  name: "home",
  components: {
    TrackGoals,
    Quotes
  }
};
</script>

<style scoped>

h1 {
  font-size: 10vh;
  color: white;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
  text-shadow: 0px 0px 10px black;
}

</style>
