<template>
  <div>
      <exercise-card/>
    <!-- <exercise-card
        v-for="card in cards"
        v-on:click="toggleCard(card)"
        v-bind:key="card.id"
        v-bind:card="card"
    /> -->
  </div>
</template>

<script>
import ExerciseCard from "../components/ExerciseCard.vue";
export default {
  name: "exercise-assistance",
//   data() {
//     return {
//       cards: [
//         {
//           id: 1,
//           exercise: "",
//           info: "",
//           image: "",
//         },
//       ],
//     };
//   },
  components: {
    ExerciseCard,
  },
};
</script>

<style scoped>

#flashcard-app {
  display: grid;
  justify-items: center;
}
</style>