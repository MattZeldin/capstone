import axios from 'axios';

export default {

  events(user) {
    return axios.get(`${user.id}/events`)
  }
}
