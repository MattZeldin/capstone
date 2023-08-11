<template>
  <body>
    <div id="login">
      <form @submit.prevent="login" class="container">
        <img src="../../../images/ActivElevate_Logo_3.png" alt="logo" />
        <h1 id="mainhead">ActivElevate</h1>
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

<style scoped>

#login > form > img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  border: 10px solid black;
  max-height: 300px;
}

body {
  background-color: skyblue;
}

#login {
  display: flex;
  flex-direction: column;
  padding: 10px;
  align-items: center;
}

#mainhead {
  font-size: 7.5vh;
  justify-content: center;
  margin: auto;
  padding: 5%;
  color: black;
  text-shadow: 0px 0px 5px black;
}

#username-password-submit {
  display: grid;
  margin: auto;
}

#username {
  padding: 10px;
  font-size: 2.5vh;
}

#password {
  padding: 10px;
  font-size: 2.5vh;
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