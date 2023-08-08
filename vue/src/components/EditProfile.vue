<template>
  <div>
 <button v-on:click="edit=true">Edit Profile</button>
    <form v-show="edit === true">
      <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="user.name" required="false" />
      </div>

      <div class="form-element">
        <label for="email">Email:</label>
        <input id="email" type="text" v-model="user.email" required="false" />
      </div>
      <input type="submit" value="Save" v-on:click.prevent="updateUser" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
  </div>
</template>

<script>
export default {
  name: 'edit-profile',
  data() {
    return {
      edit: false,
      user:{
        name: this.$store.state.user.name,
        email: this.$store.state.user.email,
      }
    };
  },
  methods:{
    updateUser(){
      this.$store.commit("UPDATE_USER",this.user)
      // this.user=if(!this.user.name==='');
      // this.user=if(!this.user.email==='');
      this.user={
        name: this.$store.state.user.name,
        email: this.$store.state.user.email,
      }
      // this.$router.push('/profile');
    },
    resetForm(){
      this.edit = false;
    }
  }
};
</script>
<style>
</style>