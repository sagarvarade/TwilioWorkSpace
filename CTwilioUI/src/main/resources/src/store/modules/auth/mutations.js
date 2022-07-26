export default {
  setUser(state, payload) {
    state.token = payload.token
    state.username = payload.username
    state.password = payload.password
    state.tokenExpiration = payload.tokenExpiration
    state.didAutoLogout = false
  },
  setAutoLogout(state) {
    state.didAutoLogout = true
  },
}
