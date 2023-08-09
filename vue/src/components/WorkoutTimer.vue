<template>
  <div id="clock">
    <span class="time">{{ time }}</span>

    <div class="btn-container">
      <a id="start" v-on:click="start">Start</a>
      <a id="stop" v-on:click="stop">Stop</a>
      <a id="reset" v-on:click="reset">Reset</a>
    </div>

    <div class="text">
      <a href="https://codepen.io/raphael_octau" target="_blank"
        >@raphael_octau</a
      >
    </div>
  </div>
</template>

<script>
export default {
  name: 'workoutTimer',
  data() {
    return {
      time: '00:00:00.000',
      timeBegan: null, 
      timeStopped: null, 
      started: null,
      stoppedDuration: 0, 
      running: false
    }
  },
  methods: {
    start() {
      if(this.running) return;
  
  if (this.timeBegan === null) {
    this.reset();
    this.timeBegan = new Date();
  }

  if (this.timeStopped !== null) {
    this.stoppedDuration += (new Date() - this.timeStopped);
  }

  this.started = setInterval(this.clockRunning, 10);	
  this.running = true;
    },

    stop() {
        this.running = false;
  this.timeStopped = new Date();
  clearInterval(this.started);
    },

    reset() {
        this.running = false;
  clearInterval(this.started);
  this.stoppedDuration = 0;
  this.timeBegan = null;
  this.timeStopped = null;
  this.time = "00:00:00.000";
    },

    clockRunning() {
    let currentTime = new Date();
    let timeElapsed = new Date(currentTime - this.timeBegan - this.stoppedDuration);
    let hour = timeElapsed.getUTCHours()
    let min = timeElapsed.getUTCMinutes()
    let sec = timeElapsed.getUTCSeconds()
    let ms = timeElapsed.getUTCMilliseconds();

  this.time = 
    this.zeroPrefix(hour, 2) + ":" + 
    this.zeroPrefix(min, 2) + ":" + 
    this.zeroPrefix(sec, 2) + "." + 
    this.zeroPrefix(ms, 3);
    },

    zeroPrefix(num, digit) { 
      let zero = '';
  for(let i = 0; i < digit; i++) {
    zero += '0';
  }
  return (zero + num).slice(-digit);}

  }
}


//     var clock = new Vue({
//   el: '#clock',
//   data: {
    
//   }
// }),

// var timeBegan = null
// , timeStopped = null
// , stoppedDuration = 0
// , started = null
// , running = false;

// document.getElementById("start").addEventListener("click", start);
// document.getElementById("stop").addEventListener("click", stop);
// document.getElementById("reset").addEventListener("click", reset);

// function start() {
//   if(running) return;
  
//   if (timeBegan === null) {
//     reset();
//     timeBegan = new Date();
//   }

//   if (timeStopped !== null) {
//     stoppedDuration += (new Date() - timeStopped);
//   }

//   started = setInterval(clockRunning, 10);	
//   running = true;
// }

// function stop() {
//   running = false;
//   timeStopped = new Date();
//   clearInterval(started);
// }

// function reset() {
//   running = false;
//   clearInterval(started);
//   stoppedDuration = 0;
//   timeBegan = null;
//   timeStopped = null;
//   clock.time = "00:00:00.000";
// }

// function clockRunning(){
//   var currentTime = new Date()
//   , timeElapsed = new Date(currentTime - timeBegan - stoppedDuration)
//   , hour = timeElapsed.getUTCHours()
//   , min = timeElapsed.getUTCMinutes()
//   , sec = timeElapsed.getUTCSeconds()
//   , ms = timeElapsed.getUTCMilliseconds();

//   clock.time = 
//     zeroPrefix(hour, 2) + ":" + 
//     zeroPrefix(min, 2) + ":" + 
//     zeroPrefix(sec, 2) + "." + 
//     zeroPrefix(ms, 3);
// };

// function zeroPrefix(num, digit) {
//   var zero = '';
//   for(var i = 0; i < digit; i++) {
//     zero += '0';
//   }
//   return (zero + num).slice(-digit);
// }

</script>

<style scoped>


@import url("https://fonts.googleapis.com/css2?family=Share+Tech+Mono&display=swap");

html,
body {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 100%;
}

body {
  background-color: rgb(10, 10, 10);
  font-family: "Share Tech Mono", sans-serif;

  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-content: stretch;
  align-items: center;
}

#clock {
  order: 0;
  flex: 0 1 auto;
  align-self: center;

  color: rgb(200, 200, 200);
  text-shadow: 0px 0px 25px rgb(200, 200, 200);
}

  #clock.time {
    font-size: 6.5em;
  }

  .text {
    margin-top: 30px;
    font-size: 1em;
    color: rgba(rgb(200, 200, 200), 0.4);
    text-align: center;
  }

  .text > a {
      text-decoration: none;
      color: inherit;

      transition: color 0.1s ease-out;
    }
  
   .text > a:hover {
        color: rgb(200, 200, 200);
      }

  .btn-container {
    display: flex;
    margin-top: 15px;
  }

  .btn-container > a {
      text-align: center;
      font-family: "Share Tech Mono", sans-serif;
      background: transparent;
      border: 3px solid rgb(200, 200, 200);
      border: none;
      color: rgb(200, 200, 200);
      padding: 10px 15px;
      margin: 0 10px;
      text-transform: uppercase;
      font-size: 2em;
      cursor: pointer;
      text-shadow: 0px 0px 10px rgb(200, 200, 200);

      flex-grow: 1;

      transition: color 0.1s ease-out;

  }
      
  .btn-container > a:hover {
        color: white;
      }

</style>