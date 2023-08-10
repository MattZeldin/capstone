<template>
  <div>
<<<<<<< HEAD
    <button id="button" v-on:click="edit = true">Edit Profile</button>
    <form v-show="edit === true">
=======
 <button v-on:click="edit=true">Edit Profile</button>
    <form id="form" v-show="edit === true">
>>>>>>> 8333da94f4deda61a6263491403a9fa6ee3f0b49
      <div class="form-element">
        <label id="field_name" for="name">Name:</label>
        <input id="field" type="text" v-model="user.name" required="false" />
      </div>

      <div class="form-element">
        <label id="field_name" for="email">Email:</label>
        <input id="field" type="text" v-model="user.email" required="false" />
      </div>
      <div class="form-element">
        <label id="field_name" for="username">Username:</label>
        <input
          id="field"
          type="text"
          v-model="user.username"
          required="false"
        />
      </div>
<<<<<<< HEAD
      <input
        id="button"
        type="submit"
        value="Save"
        v-on:click.prevent="updateUser"
      />
      <input id="button" type="button" value="Cancel" v-on:click="resetForm" />
=======
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
>>>>>>> 8333da94f4deda61a6263491403a9fa6ee3f0b49
    </form>
  </div>
</template>

<script>
import userService from "../services/UserService";

export default {
  name: "edit-profile",
  data() {
    return {
      edit: false,
      user: {
        name: this.$store.state.user.name,
        email: this.$store.state.user.email,
        username: this.$store.state.user.username,
        id: this.$store.state.user.id,
<<<<<<< HEAD
      },
=======
        days: this.$store.state.user.days,
        minutes: this.$store.state.user.minutes
      }
>>>>>>> 8333da94f4deda61a6263491403a9fa6ee3f0b49
    };
  },
  methods: {
    updateUser() {
      this.$store.commit("SET_USER", this.user);

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
<<<<<<< HEAD
    resetForm() {
      this.edit = false;
    },
  },
=======
    resetForm(){
    
      this.edit = false;
      

    }
  }
>>>>>>> 8333da94f4deda61a6263491403a9fa6ee3f0b49
};
</script>
<style>

#button {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  padding: 10px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 40px;
  border-radius: 5px;
}

#field_name {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  margin: 10px;
  font-size: 20px
}

#field {
  display: flex;
  justify-content: center;
  align-content: center;
  padding: 10px;
  margin: auto;
  margin-bottom: 10px;
  min-width: 50%;
}

</style>