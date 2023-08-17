<template>
  <div class="home">
    <alert-notification/>
    <h1 id="mainhead">Welcome to ActivElevate, {{this.$store.state.user.name}}!</h1>
    <track-goals/>
    <quotes/>
    
  </div>
</template>

<script>
import TrackGoals from "../components/TrackGoals.vue";
import Quotes from "../components/Quotes.vue";
// import ProgressBar from "../components/ProgressBar.vue";
import AlertNotification from '../components/AlertNotification.vue';

export default {
  name: "home",
  components: {
    TrackGoals,
    Quotes,
    // ProgressBar,
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

#mainhead {
  display: grid;
  justify-items: center;
  text-align: center;
  margin-bottom: 0px;
}

.home {
  display: grid;
  justify-content: center;
  justify-items: center;
}

#app > div.home > div:nth-child(3) {
  display: grid;
  justify-items: center;
}

#app > div.home > div:nth-child(5){
  display: grid;
  justify-items: center;
}

#app > div.home {
  display: grid;
  justify-items: center;
}

#progress-bar {
  max-height: 50px;
  max-width: 500px;
  justify-content: left;
  padding: 10px;
}

h2 {
    color: #1926ef;
    text-shadow: 0px 0px 10px white;
    font-size: 35px;
}
</style>
