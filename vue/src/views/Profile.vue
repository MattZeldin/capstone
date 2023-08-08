<template>
  <div>
    <h1>User Profile</h1>
    <p>
      The purpose of this page is to display current profile info and allow user to make
      changes to their profile.
    </p>
    <ul>
      <li>Name: {{ this.$store.state.user.username }} </li>
      <li>Email Address: </li>
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

<style>
</style>