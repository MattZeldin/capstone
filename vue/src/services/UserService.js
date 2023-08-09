import axios from 'axios';

export default {

  profile(userId) {
    return axios.get(`/user/${userId}/profile`)
  }

}