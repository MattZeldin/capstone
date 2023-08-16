<template>
  <div>
    <h1>User Profile</h1>
    <ul>
      <li>Name: {{ this.$store.state.user.name }}</li>
      <li>Email Address: {{ this.$store.state.user.email }}</li>
      <li>Username: {{ this.$store.state.user.username }}</li>
      <li>Target days per week: {{ this.$store.state.user.days }}</li>
      <li>Target minutes per week: {{ this.$store.state.user.minutes }}</li>
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

#app > div:nth-child(2) {
  display: grid;
  justify-content: center;
  justify-items: center;
}

#app > div:nth-child(2) > ul {
  font-size: 4vh;
  list-style-type: none
}

#app > div:nth-child(2) > div {
  display: grid;
  justify-content: center;
}

</style>