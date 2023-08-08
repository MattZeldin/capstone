<template>
  <div>
      <h1>User profile</h1>
      <p>Purpose of page is to display current profile info and allow user to make changes</p>
      <ul>
          <li>name</li>
          <li>email address</li>
          <!-- <li>photo</li> -->
          <li>workout goals</li>
          <li>workout profile?</li>
      </ul>
    
      <button v-on:click="editProfile=true">Edit Profile</button>
      
      <edit-profile/>

  </div>
</template>

<script>
import EditProfile from '../components/EditProfile.vue'
export default {
    name: "profile",
    components:{
        EditProfile
    }

}
</script>

<style>

</style>