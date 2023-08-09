<template>
  <div>
 <button v-on:click="edit=true">Edit Profile</button>
    <form v-show="edit === true">
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
      <input type="submit" value="Save" v-on:click.prevent="updateUser" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
  </div>
</template>

<script>
import UserService from '../services/UserService';
import authService from '../services/AuthService';

export default {
  name: 'edit-profile',
  data() {
    return {
      edit: false,
      user:{
        name: this.$store.state.user.name,
        email: this.$store.state.user.email,
        username: this.$store.state.user.username
      }
    };
  },
  methods:{
    updateUser(){
      this.$store.commit("UPDATE_USER",this.user);
      // this.user=if(!this.user.name==='');
      // this.user=if(!this.user.email==='');
      this.user={
        name: this.$store.state.user.name,
        email: this.$store.state.user.email,
      };
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
      UserService
      .updateProfile(this.user)
      .then((response) => {
          if (response.status == 200) {
            this.$router.push("/profile");
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
    }
  }
};
</script>
<style>
</style>