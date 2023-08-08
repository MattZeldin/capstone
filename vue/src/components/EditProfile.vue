<template>

<form v-show="editProfile===true">
    <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="user.name" required="false"/>
       </div>

    <div class="form-element">
        <label for="email">Email:</label>
        <input id="email" type="text" v-model="user.email"  required="false"/>
    </div>
        <input type="submit" value="Save" v-on:click.prevent="updateUser"/> 
        <input type="button" value="Cancel" v-on:click="resetForm"/>

</form>

  
</template>

<script>
export default {
    name: "profile",
    data(){
        return{
            editProfile: false,
            // user:[name: currentName, email: currentEmail]
        }
    }
}
</script>
<style>

</style>