<template>
  <body>
    <div id="login">
      <form @submit.prevent="login" class="container">
        <h1 id="mainhead">Workout Companion App</h1>
        <!-- <h3>User Sign In</h3> -->
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div id="username-password-submit">
          <div id="username-password">
            <div class="form-input-group" id="username">
              <label for="username">Username</label>
              <input
                type="text"
                id="username"
                v-model="user.username"
                required
                autofocus
              />
            </div>

            <div class="form-input-group" id="password">
              <label for="password">Password</label>

              <input
                type="password"
                id="password"
                v-model="user.password"
                required
              />
            </div>
          </div>

          <button type="submit" id="submit-button">Sign in</button>
          <p>
            <router-link :to="{ name: 'register' }" id="routerlink"
              >Need an account? Sign up.</router-link
            >
          </p>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";
import userService from "../services/UserService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
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
        userService
      .profile(this.$store.state.user.id)
      .then((response) => {
          if (response.status == 200) {
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
    },
  },
};
</script>

<style>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

body {
  background-color: rgb(1, 48, 92);
  color: white;
  font-family: "Montserrat", sans-serif;
}

.container {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "header"
    "sign-in"
    "sign-in"
    "sign-in";
  gap: 10px;
  align-items: center;
  justify-content: center;
  height: 100vh;

  /* flex-wrap: wrap; */
  /* justify-content: space-around; */
  /* position: absolute; */
  left: 0;
  right: 0;
  bottom: 0;
  /* background-color: rgb(219, 218, 218);   */
}

h1 {
  display: flex;
  flex-wrap: wrap;
  font-size: 10vh;
  flex-grow: 2;
  grid-area: header;
}

#username-password-submit {
  grid-area: sign-in;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0.5em;
  align-items: center;
  flex-grow: 1;
}
</style>