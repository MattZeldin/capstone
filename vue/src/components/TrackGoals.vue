<template>
    <div>
        <h2>Goals: </h2>
        <h3>Target days per week: {{ this.$store.state.user.days }}</h3>
        <h3>Target minutes per week: {{ this.$store.state.user.minutes }} </h3>
        <!-- <p>Visit the profile page to edit your goals. </p><router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''"><button id="button">My Profile</button></router-link> -->
        <router-link id="button3" v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''">Edit Profile and Goals</router-link>
        <h2>Progress: </h2>
        <h3>Days so far this week: {{ this.$store.state.userData.numberOfWorkouts }} </h3>
        <progress-bar v-bind:progress="'days'"/>
        <h3>Minutes so far this week: {{ this.$store.state.userData.totalMinutes.toFixed(0) }} </h3>
        <progress-bar v-bind:progress="'minutes'"/>
        <!-- <button v-on:click="getUserData">data</button> -->
    </div>
</template>

<script>
import userService from "../services/UserService";
import ProgressBar from "../components/ProgressBar.vue";

export default {
    name: 'track-goals',
    components: {
        ProgressBar
    },
    data() {
        return {
            userData: {
                numberOfWorkouts: "",
                totalMinutes: ""
            }
        }
    },
    methods: {
        getUserData() {
            // console.log(this.$store.state.user.username)
            userService
                .userDataLocation(this.$store.state.user.username)
                .then((response) => {
                    // if (response.status == 200) {
                        console.log(response);
                        this.userData.numberOfWorkouts = response.data.numberOfWorkouts;
                        console.log(this.userData.numberOfWorkouts);
                        this.userData.totalMinutes = response.data.totalMinutes;
                        this.$store.commit("SET_USER_DATA", this.userData);
                    
                    // }
                })
                .catch((error) => {
                    const response = error.response;

                    if (response.status === 401) {
                        this.invalidCredentials = true;
                        this.userData.numberOfWorkouts = 999;
                        this.userData.totalMinutes = 999;
                        this.$store.commit("SET_USER_DATA", this.userData)
                    }
                });
        }
    },
    mounted() {
        this.getUserData();
    },
}
</script>

<style>
#progress-bar {
  max-height: 50px;
  max-width: 500px;
  justify-content: left;
  padding: 10px;
}

#button3 {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  margin: 10px;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  border-radius: 5px;
  width: 250px;
}

#button3:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}


h2 {
    color: #1926ef;
    text-shadow: 0px 0px 10px white;
    font-size: 35px;
}

</style>