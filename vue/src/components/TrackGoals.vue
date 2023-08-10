<template>
    <div>
        <h2>Goals: </h2>
        <h3>Target days per week: {{ this.$store.state.user.days }}</h3>
        <h3>Target minutes per workout: {{ this.$store.state.user.minutes }} </h3>
        <!-- <p>Visit the profile page to edit your goals. </p><router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''"><button id="button">My Profile</button></router-link> -->
        <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''">Click here to visit your profile and edit your goals.</router-link>
        <h2>Progress: </h2>
        <h3>Days so far this week: <!--{{ this.$store.state.workouts.days running total}}--></h3>
        <h3>Average minutes per workout: <!--{{ this.$store.state.workouts.minutes but average }}--> </h3>
    </div>
</template>

<script>
export default {
    name: 'track-goals',
    data() {
        return {
            
        }
    }
}
</script>

<style>

</style>