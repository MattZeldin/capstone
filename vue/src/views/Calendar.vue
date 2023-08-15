<template>
  <calendar-events/>
</template>

<script>
import CalendarEvents from '../components/CalendarEvents.vue'
import calendarService from '../services/CalendarService.js'
export default {
  components: { CalendarEvents },
  beforeMount() {
      calendarService
          .events(this.$store.state.user)
          .then((response) => {
           if (response.status == 200) {
             this.$store.commit("SET_EVENTS", response.data);
            //  this.addWorkout = false;
             this.$router.push("/calendar");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
}
</script>

<style scoped>

</style>