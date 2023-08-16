<template>
  <div>
    <button id="button2" v-on:click="eventToAdd = true">Add event</button>
    <form v-show="eventToAdd === true">
      <div class="form-element">
        <label id="field_name" for="start">Start date and time:</label>
        <input
          id="field"
          type="datetime-local"
          v-model="event.start"
          required="true"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="end">End date and time:</label>
        <input
          id="field"
          type="datetime-local"
          v-model="event.end"
          required="true"
        />
      </div>
      <div class="form-element">
        <label id="field_name" for="title">Title:</label>
        <input id="field" type="text" v-model="event.title" required="true" />
      </div>
      <div class="form-element">
        <label id="field_name" for="content">Description:</label>
        <input
          id="field"
          type="text"
          v-model="event.content"
          required="false"
        />
      </div>
      <!-- <div class="form-element">
        <label id="field_name" for="class">Class:</label>
        <input id="field" type="text" v-model="event.class" required="false" />
      </div> -->
      <input
        id="button2"
        type="submit"
        value="Save"
        v-on:click="addEvent"
      />
      <input id="button2" type="button" value="Cancel" v-on:click="resetForm" />
    </form>
    <calendar-events />

 
  </div>
</template>

<script>
import CalendarEvents from "../components/CalendarEvents.vue";
import calendarService from "../services/CalendarService.js";
import moment from "moment";
export default {
  components: { CalendarEvents },
  data() {
    return {
      eventToAdd: false,
      event: {
        start: "",
        end: "",
        title: "",
        content: "",
        class: "none",
        user_id: this.$store.state.user.id,
      },
    };
  },

  beforeMount() {
    calendarService
      .events(this.$store.state.user)
      .then((response) => {
        if (response.status == 200) {
          this.$store.commit("SET_EVENTS", response.data);
          //  this.addWorkout = false;
          // this.$router.push("/calendar");
        }
      })
      .catch((error) => {
        const response = error.response;

        if (response.status === 401) {
          this.invalidCredentials = true;
        }
      });
  },

  methods: {
    addEvent() {
      this.event.start = this.format(this.event.start);
      this.event.end = this.format(this.event.end);
      this.event.userId = this.event.user_id;
      console.log(this.event);

      calendarService
        .createEvent(this.event)
        .then((response) => {
          if (response.status == 201) {
            this.$store.commit("SET_EVENTS", response.data);
            this.$router.go(0);
            console.log(this.event);
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },

    resetForm() {
      this.eventToAdd = false;
    },
    log_console(event) {
      console.log(event);
    },
    format(value) {
      return moment(value).format("YYYY-MM-DD HH:mm");
    },
   
  },
};
</script>

<style scoped>

#button2 {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  margin-left: 38%;
  margin-right: 38%;
  margin-bottom: 10px;
  margin-top: 10px;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  border-radius: 5px;
  min-width: 25%;
}

#app > div:nth-child(3) > form {
  display: grid;
  justify-items: center;
}

#button2:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}

#app > div:nth-child(2) {
  display: grid;
  justify-items: stretch;
}

#field {
  border-radius: 10px;
  border: 3px solid darkblue;
  min-height: 30px;
  margin-left: 10px;
  margin-top: 10px;
  padding: 10px
}

/* .vuecal__event {cursor: pointer;}

.vuecal__event-title {
  font-size: 1.2em;
  font-weight: bold;
  margin: 4px 0 8px;
}

.vuecal__event-time {
  display: inline-block;
  margin-bottom: 12px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}

.vuecal__event-content {
  font-style: italic;
} */
</style>