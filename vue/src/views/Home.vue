<template>
  <div class="home">
    <alert-notification/>
    <h1>Home</h1>
    <track-goals/>
    <progress-bar/>
    <!-- <goal-toast msg = "Hello world"/> -->
    <quotes/>
    
  </div>
</template>

<script>
import TrackGoals from "../components/TrackGoals.vue";
import Quotes from "../components/Quotes.vue";
import ProgressBar from "../components/ProgressBar.vue";
import AlertNotification from '../components/AlertNotification.vue';


export default {
  name: "home",
  components: {
    TrackGoals,
    Quotes,
    ProgressBar,
    AlertNotification
  },
  // data() {
  //   return {
  //     loaded: true
  //   }
  // }
};
</script>

<style scoped>
#progress-bar {
  max-height: 50px;
  max-width: 500px;
  justify-content: left;
  padding: 10px;
}

</style>
