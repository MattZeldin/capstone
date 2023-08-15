<template>
<div>
  <vue-cal v-bind:events="events" :twelveHour="true"/>
</div>
</template>

<script>
import VueCal from 'vue-cal';
import 'vue-cal/dist/vuecal.css';

export default {
components: {VueCal},
data() {
  return {

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
  }
}
</script>

<style scoped>

</style>