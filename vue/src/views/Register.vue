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


        <button type="submit">Create Account</button>
        <p>
          <router-link :to="{ name: 'login' }"
            >Already have an account? Log in.</router-link
          >
        </p>
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
</style>
