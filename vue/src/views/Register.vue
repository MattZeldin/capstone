<template>
  <div id="register" class="text-center">
    <form @submit.prevent="register" class="container">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div id="username-password-submit">
        <div class="form-input-group">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="user.confirmPassword"
            required
          />
        </div>
        <div class="form-input-group" id="username">
          <label for="email">Email</label>
          <input
            type="text"
            id="email"
            v-model="user.email"
            required
            autofocus
          />
        </div>
        <div class="form-input-group" id="name">
          <label for="name">Name</label>
          <input type="text" id="name" v-model="user.name" required autofocus />
        </div>
          <h2>Goals</h2>  
        <div class="form-input-group">
          <label for="goals-days">Target days per week</label>
          <input
            type="text"
            id="goals-days"
            v-model="user.days"
            required
            autofocus
          />
        </div>

        <div class="form-input-group">
          <label for="goals-minutes">Target minutes per workout</label>
          <input
            type="text"
            id="goals-minutes"
            v-model="user.minutes"
            required
            autofocus
          />
        </div>

        <button id="button4" type="submit">Create Account</button>
        <router-link id="button4" :to="{ name: 'login' }">Log In</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
        name: "",
        email: "",
        days: "", 
        minutes: ""
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>

.form-input-group {
  margin-bottom: 1rem;
}



label {
  margin-right: 0.5rem;
}



#button4 {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  padding: 10px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 5px;
  min-width: 25%;
  max-width: 25%;
  margin: 10px
}

#button4:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}
</style>
