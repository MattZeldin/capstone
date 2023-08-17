import axios from 'axios';

export default {

  events(user) {
    return axios.get(`/${user.id}/events`)
  },

  getSingleEvent(event){
    return axios.get(`/event${event.eventId}`)
  }, 

  createEvent(event){
      return axios.post(`/${event.user_id}/events` , event)
  }, 

  updateEvent(event){
    return axios.put(`/${event.user_id}/events` , event)
}, 

  delete(event){
    return axios.delete(`/events/${event.eventId}`)
}
}