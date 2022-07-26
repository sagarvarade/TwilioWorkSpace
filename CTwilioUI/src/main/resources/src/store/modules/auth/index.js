import mutations from './mutations.js'
import actions from './actions.js'
import getters from './getters.js'

export default {
  //namespaced: true,
  state() {
    return {
      name: 'sagar by store',
      token: null,
      username: null,
      password: null,
      tokenExpiration: null,
    }
  },
  mutations,
  actions,
  getters,
}
