<template>
  <div class="home">
    <h1>Home</h1>
    <track-goals/>
    <progress-bar/>
    <quotes/>
  </div>
</template>

<script>
import TrackGoals from "../components/TrackGoals.vue";
import Quotes from "../components/Quotes.vue";
import ProgressBar from "../components/ProgressBar.vue";

export default {
  name: "home",
  components: {
    TrackGoals,
    Quotes,
    ProgressBar
  }
};
</script>

<style scoped>

.home {
  display: grid;
  justify-content: center;
  justify-items: center;
}

#app > div.home > div:nth-child(2) {
  display: grid;
  justify-items: center;
}

#progress-bar {
  max-height: 50px;
  max-width: 500px;
  justify-content: left;
  padding: 10px;
}

</style>
