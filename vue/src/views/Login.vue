<template>
  <body>
    <div id="login">
      <form @submit.prevent="login" class="container">
        <h1 id="mainhead">ActivElevate</h1>
        <img id="logo" src="../../../images/ActivElevate_Logo_3.png" alt="logo" />
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div id="username-password-submit">
          <div id="username-password">
            <div class="form-input-group" id="field_name">
              <label for="username">Username</label>
              <input
                type="text"
                id="field"
                v-model="user.username"
                required
                autofocus
              />
            </div>

            <div class="form-input-group" id="field_name">
              <label for="password">Password</label>

              <input
                type="password"
                id="field"
                v-model="user.password"
                required
              />
            </div>
          </div>

          <button type="submit" id="button">Sign in</button>
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
import workoutService from '../services/WorkoutService';

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
        workoutService
          .getWorkoutsByUsername(this.user.username)
          .then((response) => {
           if (response.status == 200) {
             this.$store.commit("SET_WORKOUTS", response.data);
            //  this.$router.push("/");
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

<style scoped>

#mainhead {
  margin: 20px;
}

#login > form {
  display: grid;
  justify-items: center;
}

#login > form > img {
  display: block;
  border: 10px solid white;
  border-radius: 20px;
  max-height: 250px;
  margin-bottom: 10px;
  margin-right: 10px;
  box-shadow: 0px 0px 20px black;
}

body {
  background-color: skyblue;
}

#login {
  display: grid;
  justify-items: center;
  margin: 10px;
}

#username-password-submit {
  display: grid;
}

#username-password-submit > p {
  padding-top: 10px;
}

#field_name {
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
  padding: 10px;
  margin: 10px;
  font-size: 25px
}

#field_name > label {
  padding-right: 25px;
}

#field {
  display: flex;
  justify-content: center;
  align-content: center;
  padding: 10px;
  margin: auto;
  margin-bottom: 10px;
  margin-right: 10px;
  min-width: 50%;
  border-radius: 10px;
  border: 5px solid darkblue;
  font-size: 20px
}

#button {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  margin-top: 10px;
  margin-bottom: 10px;
  padding: 10px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 15px;
  min-width: 100%
}

#button:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}

#submit-button {
  margin-left: 25%;
  margin-top: 5%;
  margin-bottom: 5%;
  width: 50%;
  padding: 10px;
  font-size: 2.5vh;
}

#username-password-submit > p {
  margin: auto;
  padding-bottom: 10%;
}

#routerlink {
  font-size: 2.5vh;
}

/* unvisited link */
a:link {
  color: white;
}
/* visited link */
a:visited {
  color: darkblue;
}
/* mouse over link */
a:hover {
  color: cyan;
}
/* selected link */
a:active {
  color: #1926ef;
}
</style>