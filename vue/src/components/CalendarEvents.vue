<template>
<div>
  <!-- <vue-cal v-bind:events="events" :twelveHour="true"/> -->

<vue-cal
  
  :time-from="9 * 60"
  :time-to="19 * 60"
  :disable-views="['years', 'year']"
  :events="events"
  :on-event-click="onEventClick"
  :twelveHour="true">
</vue-cal>

<v-dialog v-model="showDialog">
  <v-card>
    <v-card-title>
      <!-- <v-icon>{{ selectedEvent.icon }}</v-icon> -->
      <span>{{ selectedEvent.title }}</span>
      <v-spacer/>
      <strong>{{ selectedEvent.start && selectedEvent.start.formatTime()  }}</strong>
    </v-card-title>
    <v-card-text>
      <p v-html="selectedEvent.contentFull"/>
      <strong>Event details:</strong>
      <ul>
        <li>Event starts at: {{ selectedEvent.start && selectedEvent.start.formatTime() }}</li>
        <li>Event ends at: {{ selectedEvent.end && selectedEvent.end.formatTime() }}</li>
        <!-- <li>{{selectedEvent.details}}</li> -->
      </ul>
    </v-card-text>
  </v-card>
</v-dialog>

</div>

</template>

<script>
import VueCal from 'vue-cal';
import 'vue-cal/dist/vuecal.css';


export default {
components: {VueCal},
data() {
  return {
      selectedEvent: {},
      showDialog: false

  //   events: [
  //     {
  //       start: '2023-08-14 10:30',
  //       // start: this.startFromStore,
  //       end: '2023-08-14 11:30',
  //       // You can also define event dates with Javascript Date objects:
  //       // start: new Date(2018, 11 - 1, 16, 10, 30),
  //       // end: new Date(2018, 11 - 1, 16, 11, 30),
  //       title: 'Doctor appointment',
  //       content: '<i class="icon material-icons">local_hospital</i>',
  //       class: 'health'
  //     },
  //     {
  //       start: '2023-08-14 14:30',
  //       // start: this.startFromStore,
  //       end: '2023-08-14 15:30',
  //       // You can also define event dates with Javascript Date objects:
  //       // start: new Date(2018, 11 - 1, 16, 10, 30),
  //       // end: new Date(2018, 11 - 1, 16, 11, 30),
  //       title: 'Doctor appointment',
  //       content: '<i class="icon material-icons">local_hospital</i>',
  //       class: 'health'
  //     }
  //  ]
  }
},
computed: {
    events() {
      return this.$store.state.events
    }
  },

  methods:  {  
    onEventClick (event, e) {
      this.selectedEvent = event
      this.showDialog = true

      e.stopPropagation()
  }
  }
}
</script>

<style scoped src="../../node_modules/vuetify/dist/vuetify.min.css" >
/* @import "../../node_modules/vuetify/dist/vuetify.min.css"; */

.vuecal__event {cursor: pointer;}

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
}
</style>