let timer
import URLS from '@/config/URLS'

export default {
  async login(context, payload) {
    return context.dispatch('auth', {
      ...payload,
      mode: 'login',
    })
  },
  async auth(context, payload) {
    console.log(URLS.LOGIN)
    const response = await fetch(URLS.LOGIN.login.URL, {
      method: 'POST',
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
      body: JSON.stringify({
        username: payload.username,
        password: payload.password,
      }),
    })
      .then((response) => response.json())
      .then((json) => {
        try {
          const jsonResponse = json
          const tokenExpiration = jsonResponse.tokenExpiration
          localStorage.setItem('token', jsonResponse.token)
          localStorage.setItem('username', jsonResponse.username)
          localStorage.setItem('password', jsonResponse.password)
          localStorage.setItem('tokenExpiration', jsonResponse.tokenExpiration)

          timer = setTimeout(function () {
            context.dispatch('autoLogout')
          }, tokenExpiration)

          context.commit('setUser', {
            token: jsonResponse.token,
            username: jsonResponse.username,
            password: jsonResponse.password,
            tokenExpiration: jsonResponse.tokenExpiration,
          })
        } catch (error) {
          const error2 = new Error(
            'Failed to authenticate. Check your login data.',
          )
          throw error2
        }
      })
      .catch((error) => {
        console.log(error)
      })
    console.log(response)
  },
  tryLogin(context) {
    const token = localStorage.getItem('token')
    const username = localStorage.getItem('username')
    const password = localStorage.getItem('password')
    const tokenExpiration = localStorage.getItem('tokenExpiration')
    const expiresIn = +tokenExpiration - new Date().getTime()
    if (expiresIn < 0) {
      return
    }

    timer = setTimeout(function () {
      context.dispatch('autoLogout')
    }, expiresIn)

    if (token && username) {
      context.commit('setUser', {
        token: token,
        username: username,
        password: password,
        tokenExpiration: tokenExpiration,
      })
    }
  },
  logout(context) {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    localStorage.removeItem('tokenExpiration')
    localStorage.removeItem('password')

    clearTimeout(timer)

    context.commit('setUser', {
      token: null,
      username: null,
      password: null,
      tokenExpiration: null,
    })
  },
  autoLogout(context) {
    context.dispatch('logout')
    context.commit('setAutoLogout')
  },
}
