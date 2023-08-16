

<template>
  <div>
    <!-- <button @click="timeOutNotify">Hello world</button> -->
    <!--<button @click="something">Hello world</button>
    <button @click="animate">Hello world</button> -->
  </div>
</template>

<script>
import "animate.css";
// import 'date-fns';

export default {
  name: "alert-notification",
  // data() {
  //   return {
  //     // progressing: false,
  //     // daysSoFarThisWeek:
  //   }
  // },
  methods: {
    //general, do not use
    // showAlert() {
    //   this.$swal("Hello Vue world!!!");
    // },

    //use this for both options
    timeOutNotify() {
      let timerInterval;
      this.$swal
        .fire({
          title: "Auto close alert!",
          html: "I will close in <b></b> seconds.",
          timer: 2000,
          timerProgressBar: true,
          didOpen: () => {
            this.$swal.showLoading();
            const b = this.$swal.getHtmlContainer().querySelector("b");
            timerInterval = setInterval(() => {
              b.textContent = (this.$swal.getTimerLeft() / 1000).toFixed(0);
            }, 100);
          },
          willClose: () => {
            clearInterval(timerInterval);
          },
        })
        .then((result) => {
          /* Read more about handling dismissals below */
          if (result.dismiss === this.$swal.DismissReason.timer) {
            console.log("I was closed by the timer");
          }
        });
    },

    //for up to date on goals
    goodProgressAlert() {
      let timerInterval;
      this.$swal
        .fire({
          position: "top-end",
          icon: "success",
          title: "You are well on your way to achieving your goals!",
          // html: "I will close in <b></b> milliseconds.",
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: () => {
            this.$swal.showLoading();
            const b = this.$swal.getHtmlContainer().querySelector("b");
            timerInterval = setInterval(() => {
              b.textContent = this.$swal.getTimerLeft();
            }, 100);
          },
          willClose: () => {
            clearInterval(timerInterval);
          },
        })
        .then((result) => {
          /* Read more about handling dismissals below */
          if (result.dismiss === this.$swal.DismissReason.timer) {
            console.log("I was closed by the timer");
          }
        });
    },

    //for failure to meet minutes goal
    minutesWarningAlert() {
      let timerInterval;
      this.$swal
        .fire({
          title: "You need to up the length of your workouts to meet your goal!",
          html: "I will close in <b></b> seconds.",
          timer: 3000,
          timerProgressBar: true,
          didOpen: () => {
            this.$swal.showLoading();
            const b = this.$swal.getHtmlContainer().querySelector("b");
            timerInterval = setInterval(() => {
              b.textContent = (this.$swal.getTimerLeft() / 1000).toFixed(0);
            }, 100);
          },
          willClose: () => {
            clearInterval(timerInterval);
          },
          showClass: {
            popup: "animate__animated animate__fadeInDown",
          },
          hideClass: {
            popup: "animate__animated animate__fadeOutUp",
          },
        })
        .then((result) => {
          /* Read more about handling dismissals below */
          if (result.dismiss === this.$swal.DismissReason.timer) {
            console.log("I was closed by the timer");
          }
        });
    },

    //failure to meet days goal
    daysWarningAlert() {
      let timerInterval;
      this.$swal
        .fire({
          title: "You may not complete the number of workouts you'd like to this week!",
          html: "I will close in <b></b> seconds.",
          timer: 3000,
          timerProgressBar: true,
          didOpen: () => {
            this.$swal.showLoading();
            const b = this.$swal.getHtmlContainer().querySelector("b");
            timerInterval = setInterval(() => {
              b.textContent = (this.$swal.getTimerLeft() / 1000).toFixed(0);
            }, 100);
          },
          willClose: () => {
            clearInterval(timerInterval);
          },
          showClass: {
            popup: "animate__animated animate__fadeInDown",
          },
          hideClass: {
            popup: "animate__animated animate__fadeOutUp",
          },
        })
        .then((result) => {
          /* Read more about handling dismissals below */
          if (result.dismiss === this.$swal.DismissReason.timer) {
            console.log("I was closed by the timer");
          }
        });
    },
    
    //failing all goals
    goalsWarningAlert() {
      let timerInterval;
      this.$swal
        .fire({
          title: "You are in danger of meeting neither of your goals this week.",
          html: "I will close in <b></b> seconds.",
          timer: 3000,
          timerProgressBar: true,
          didOpen: () => {
            this.$swal.showLoading();
            const b = this.$swal.getHtmlContainer().querySelector("b");
            timerInterval = setInterval(() => {
              b.textContent = (this.$swal.getTimerLeft() / 1000).toFixed(0);
            }, 100);
          },
          willClose: () => {
            clearInterval(timerInterval);
          },
          showClass: {
            popup: "animate__animated animate__fadeInDown",
          },
          hideClass: {
            popup: "animate__animated animate__fadeOutUp",
          },
        })
        .then((result) => {
          /* Read more about handling dismissals below */
          if (result.dismiss === this.$swal.DismissReason.timer) {
            console.log("I was closed by the timer");
          }
        });
    }
  },

  mounted() {
    var now = new Date();
    var today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    var lastSunday = new Date(today.setDate(today.getDate() - today.getDay()));
    var diffDays = parseInt((now - lastSunday) / (1000 * 60 * 60 * 24), 10) + 1;
    // console.log(now);
    // console.log(lastSunday);
    // console.log(diffDays);
    console.log(this.$store.state.userData.averageMinutes)
    console.log(this.$store.state.user.minutes)
    console.log(this.$store.state.userData.numberOfWorkouts / diffDays)
    console.log(this.$store.state.user.days / 7)

    if (
      this.$store.state.userData.averageMinutes >=
        this.$store.state.user.minutes &&
      this.$store.state.userData.numberOfWorkouts / diffDays >=
        this.$store.state.user.days / 7
    ) {
      this.goodProgressAlert();
    }
    else if (
      this.$store.state.userData.averageMinutes <
        this.$store.state.user.minutes &&
      this.$store.state.userData.numberOfWorkouts / diffDays <
        this.$store.state.user.days / 7
    ) {
      this.goalsWarningAlert();
    }
    else if (
      this.$store.state.userData.averageMinutes < this.$store.state.user.minutes
    ) {
      //need to up length of workout
      this.minutesWarningAlert();
    }
    else if (
      this.$store.state.userData.numberOfWorkouts / diffDays <
      this.$store.state.user.days / 7
    ) {
      //need to work out more often
      this.daysWarningAlert();

      

    }
  },
};
</script>

<style scoped>
body {
  font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial,
    sans-serif;
}
</style>
