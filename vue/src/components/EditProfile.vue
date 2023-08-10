<template>
  <div>
 <button v-on:click="edit=true">Edit Profile</button>
    <form id="form" v-show="edit === true">
      <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="user.name" required="false" />
      </div>

      <div class="form-element">
        <label for="email">Email:</label>
        <input id="email" type="text" v-model="user.email" required="false" />
      </div>
      <div class="form-element">
        <label for="username">User Name:</label>
        <input id="username" type="text" v-model="user.username" required="false" />
      </div>
      <div class="form-element">
        <label for="days">Target days per week:</label>
        <input id="days" type="text" v-model="user.days" required="false" />
      </div>
      <div class="form-element">
        <label for="minutes">Minutes per workout:</label>
        <input id="minutes" placeholder="" type="text" v-model="user.minutes" required="false" />
      </div>
      <input type="submit" value="Save" v-on:click.prevent="updateUser" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
  </div>
</template>

<script>
import userService from '../services/UserService';


export default {
  name: 'edit-profile',
  data() {
    return {
      edit: false,
      user:{
        name: this.$store.state.user.name,
        email: this.$store.state.user.email,
        username: this.$store.state.user.username,
        id: this.$store.state.user.id,
        days: this.$store.state.user.days,
        minutes: this.$store.state.user.minutes
      },

      UpdatedUser: {
        name: "",
        email: "", 
        username: "",
        id: "",
        days: "", 
        minutes: ""
      }
    };
  },
  methods:{
    updateUser(){
      this.$store.commit("SET_USER",this.user);
      
      // this.user={
      //   name: this.$store.state.user.name,
      //   email: this.$store.state.user.email,
      // };
  
      userService
      .updateProfile(this.user)
      .then((response) => {
          if (response.status == 200) {
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    resetForm(){
      this.edit = false;
      // var element = document.getElementById("form");
      // element.reset();
      var allInputs = document.querySelectorAll('#form');
      allInputs.forEach(singleInput => singleInput.reset());
    }
  }
};
</script>
<style>
</style>