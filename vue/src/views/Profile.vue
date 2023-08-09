<template>
  <div>
    <h1>User Profile</h1>
    <p>
      Select "Edit Profile" to change name and/or email.
    </p>
    <ul>
      <li>Name: {{ this.$store.state.user.name }} </li>
      <li>Email Address: {{ this.$store.state.user.email }}</li>
      <li>User Name: {{ this.$store.state.user.username }} </li>
      <li>Password: </li>

      <!--<li>photo</li>
          <li>workout goals</li>
          <li>workout profile?</li>-->
    </ul>
    <edit-profile/>
  </div>
</template>

<script>
import EditProfile from "../components/EditProfile.vue";

export default {
  name: 'profile',
  components: {
    EditProfile
  },

};
</script>

<style scoped>
  h1 {
    font-size: 20pt;
  }
</style>