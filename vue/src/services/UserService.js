import axios from 'axios';

export default {

  profile(userId) {
    return axios.get(`/user/${userId}/profile`)
  },

  updateProfile(user){
    return axios.put(`/user/${user.id}/profile`, user)
  }

  // updateProfile(user){
  //   return axios.get(`/user/${user.id}/profile`)
  // }
}