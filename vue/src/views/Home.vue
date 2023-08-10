<template>
  <div class="home">
    <h1>Home</h1>
    <track-goals/>
    <track-goals/>
  </div>
</template>

<script>
import TrackGoals from "../components/TrackGoals.vue"
export default {
  name: "home",
  components: {
    TrackGoals
  }
};
</script>
