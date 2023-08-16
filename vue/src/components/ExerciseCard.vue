<template>
  <div id="flashcard-app" class="container">
    <h1>Exercise Assistance</h1>

    <div class="flashcard-form">
      <label for="front"
        >Front
        <input v-model="newFront" type="text" id="front" />
      </label>
      <label for="back"
        >Back
        <input
          v-on:keypress.enter="addNew"
          v-model="newBack"
          type="text"
          id="back"
        />
      </label>

      <button id='button' v-on:click="addNew">Add a New Card</button>

      <span v-show="error" class="error">Oops! Flashcards need a front and a back.</span>
    </div>

    <ul class="flashcard-list">
      <li
        v-on:click="toggleCard(card)"
        v-for="(card, index) in cards"
        v-bind:key="card.id"
        v-bind:card="card"
      >
        <transition name="flip">
          <p v-bind:key="card.flipped" class="card" v-bind:class="{'back-of-card' : card.flipped}">
            <img id="image" v-if="!card.flipped" :src="card.front" alt="Front of card" />
            <span v-else>{{ card.back }}</span>
            <span v-on:click="cards.splice(index, 1)" class="delete-card"
              >X</span
            >
          </p>
        </transition>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "exercise-card",
  data() {
    return {
      cards: [
        {
          front: require('../../../images/warmup.png'), 
          back: 'Start with some light cardio for 5-10 minutes to warm up before stretching.',
          flipped: false
        },
        {
          front: require('../../../images/running.jpg'), 
          back: 'Keep your head up, relax your hands at your waist, and keep your back straight with shoulders back.',
          flipped: false
        },
        {
          front: require('../../../images/pullup.jpg'), 
          back: 'Start angled against a wall or lower surface, and then go to your knees on the floor.',
          flipped: false
        },
        {
          front: require('../../../images/deadlift.png'), 
          back: 'Push through the floor, drive your chest up and hips forward, and lockout by extending your knees and hips and pulling your shoulders tight.',
          flipped: false
        },
        {
          front: require('../../../images/pushup.png'), 
          back: 'Use an overhand grip and pull yourself up until your chin clears the bar, then lower yourself down in a controlled manner.',
          flipped: false
        }
      ],
      newFront: '',
      newBack: '',
      error: false
    };
  },
  methods: {
    toggleCard: function(card) {
      card.flipped = !card.flipped;
    },
    addNew: function() {
      if (!this.newFront || !this.newBack) {
        this.error = true;
      } else {
        this.cards.push({
          front: this.newFront,
          back: this.newBack,
          flipped: false
        });
        // set the field empty
        this.newFront = '';
        this.newBack = '';
        // Make the warning go away
        this.error = false;
      }
    }
  }
};
</script>

<style scoped>
body {
  font-family: "Montserrat", sans-serif;
  text-align: center;
}

ul {
  padding-left: 0;
  display: flex;
  flex-flow: row wrap;
}

li {
  list-style-type: none;
  padding: 10px 10px;
  transition: all 0.3s ease;
}

.container {
  max-width: 100%;
  padding: 2em;
}

.card.back-of-card {
  font-weight: 400;
  font-size: 12.5px;
}

.card {
  display: block;
  width: 250px;
  height: 400px;
  padding: 50px;
  background-color: darkblue;
  border: 5px solid #19c2ff;
  border-radius: 10px;
  margin: 5px;
  text-align: center;
  line-height: 27px;
  cursor: pointer;
  position: relative;
  color: white;
  font-weight: 600;
  font-size: 20px;
  will-change: transform;
}

#image {
  width: 250px;
  height: 400px;
  border-radius: 10px;
}

li:hover {
  transform: scale(1.1);
}

.delete-card {
  position: absolute;
  right: 0;
  top: 0;
  padding: 10px 15px;
  opacity: 0.4;
  transition: all 0.5s ease;
}

.delete-card:hover,
.error {
  opacity: 1;
  transform: rotate(360deg);
}

.flip-enter-active {
  transition: all 0.4s ease;
}

.flip-leave-active {
  display: none;
}

.flip-enter,
.flip-leave {
  transform: rotateY(180deg);
  opacity: 0;
}

/* Form */
.flashcard-form {
  position: relative;
}

label {
  font-size: 25px;
  color: black;
  margin-right: 10px;
}

#flashcard-app > div {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
}

input {
  border-radius: 5px;
  padding: 20px;
  border-radius: 5px;
  border: 3px solid darkblue;
  min-height: 30px;
  color: black;
}

#button {
  background-color: #1926ef;
  border: 3px solid #19c2ff;
  color: white;
  text-shadow: 0px 0px 5px cyan;
  font-family: "Share Tech Mono", sans-serif;
  padding: 10px;
  justify-content: center;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  border-radius: 10px;
  min-height: 30px
}

#button:hover {
  background-color: cyan;
  color: #1926ef;
  text-shadow: 0px 0px 10px #1926ef;
}

.error {
  margin-top: 10px;
  display: block;
  color: #e44e42;
  font-weight: 600;
}
</style>