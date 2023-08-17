<template>
<div>

<vue-cal
  
  :time-from="5 * 60"
  :time-to="22 * 60"
  :disable-views="['years', 'year']"
  :events="events"
  :on-event-click="onEventClick"
  :twelveHour="true"
   :editable-events="{ title: true, drag: false, resize: true, delete: true, create: false }"
   class="vuecal--full-height-delete">

</vue-cal>

<v-dialog v-model="showDialog">
  <v-card>
    <v-card-title>
      <span>{{ selectedEvent.title }}</span>
      <v-spacer/>
      <strong>{{ selectedEvent.start && selectedEvent.start.formatTime()}}</strong>
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
import VueCal from "vue-cal";
import "vue-cal/dist/vuecal.css";

export default {
  components: { VueCal },
  data() {
    return {
      selectedEvent: {},
      showDialog: false,
    };
  },
  computed: {
    events() {
      return this.$store.state.events;
    },
  },

  methods: {
    onEventClick(event, e) {
      this.selectedEvent = event;
      this.showDialog = !this.showDialog;

      e.stopPropagation();
    },

    clickOutside() {
      this.showDialog = false;
    },
  },
};
</script>

<style scoped src="../../node_modules/vuetify/dist/vuetify.min.css" >

.vuecal__event {
  cursor: pointer;
}

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