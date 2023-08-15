<template>
  <div>
   <button id="button2" v-on:click="eventToAdd=true">Add event</button>
      <form v-show="eventToAdd === true">
      <div class="form-element">
        <label id="field_name" for="start">Start date and time:</label>
        <input id="field" type="datetime-local" v-model="event.start" required="true" />
      </div>
      <div class="form-element">
       <label id="field_name" for="end">End date and time:</label>
        <input id="field" type="datetime-local" v-model="event.end" required="true" />
      </div>
      <div class="form-element">
        <label id="field_name" for="title">Title:</label>
        <input id="field" type="text" v-model="event.title" required="true" />
      </div>
      <div class="form-element">
        <label id="field_name" for="content">Description:</label>
        <input id="field" type="text" v-model="event.content" required="false" />
      </div>
      <!-- <div class="form-element">
        <label id="field_name" for="class">Class:</label>
        <input id="field" type="text" v-model="event.class" required="false" />
      </div> -->
      <input id="button2" type="submit" value="Save" v-on:click="addEvent" />
      <input id="button2" type="button" value="Cancel" v-on:click="resetForm" />
    </form>
  <calendar-events/>
  </div>
</template>

<script>
import CalendarEvents from '../components/CalendarEvents.vue'
import calendarService from '../services/CalendarService.js'
export default {
  data(){
    return {
      eventToAdd: false, 
      event: {
                start: "",
                end: "",
                title: "",
                content: "",
                class: "none",
                user_id: this.$store.state.user.user_id,
                date: "null", 
                
            }
    }
  },
  components: { CalendarEvents } ,
  


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
    }, 

    methods: {
      
        addEvent(){
          calendarService.createEvent(this.event)
          .then((response)=>{
            if (response.status == 201){
              this.$store.commit("SET_EVENTS" , response.data);
              this.$store.push("/calendar")
            }
          })
          .catch((error) =>{
              const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
          });
        }, 
        
         resetForm(){
            this.eventToAdd = false;
        }
    }
}
</script>

<style scoped>

</style>