import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import moment from 'moment'
import Vuetify from 'vuetify'

Vue.use(Vuetify)



Vue.config.productionTip = false



axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

Vue.prototype.moment = moment;

new Vue({
  router,
  vuetify: new Vuetify(),
  store,
  render: h => h(App)
}).$mount('#app')
