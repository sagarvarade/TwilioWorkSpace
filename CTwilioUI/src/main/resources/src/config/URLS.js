const API_GATEWAY = 'http://localhost:8765'
const AUTH_APPLICATION = 'eauthenticationapplication'
const URLSMAP = {
  LOGIN: {
    login: {
      URL: API_GATEWAY + '/' + AUTH_APPLICATION + '/' + 'api/authenticate',
      METHOD: 'POST',
    },
  },
}
export default URLSMAP
