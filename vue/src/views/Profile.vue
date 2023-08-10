<template>
  <div>
    <h1>User Profile</h1>
    <p>Select "Edit Profile" to change name and/or email.</p>
    <ul>
      <li>Name: {{ this.$store.state.user.name }}</li>
      <li>Email Address: {{ this.$store.state.user.email }}</li>
<<<<<<< HEAD
      <li>Username: {{ this.$store.state.user.username }}</li>
      <li>Password:</li>
=======
      <li>User Name: {{ this.$store.state.user.username }} </li>
      <li>Password: </li>
      <li>Target days per week: {{ this.$store.state.user.days }} </li>
      <li>Minutes per workout: {{ this.$store.state.user.minutes }} </li>
>>>>>>> 8333da94f4deda61a6263491403a9fa6ee3f0b49

      <!--<li>photo</li>
          <li>workout goals</li>
          <li>workout profile?</li>-->
    </ul>
    <edit-profile />
  </div>
</template>

<script>
import EditProfile from "../components/EditProfile.vue";

export default {
  name: "profile",
  components: {
    EditProfile,
  },
};
</script>

<style scoped>


h1 {
  font-size: 10vh;
  margin: 5%;
  color: white;
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
  text-shadow: 0px 0px 10px cyan;
}

#app > div:nth-child(2) > p {
  font-size: 4vh;
}

#app > div:nth-child(2) > ul {
  font-size: 3vh;
}

#app > div:nth-child(2) > div {
  display: grid;
  justify-content: center;
} 



</style>