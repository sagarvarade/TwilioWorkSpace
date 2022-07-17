let timer

export default {
  async login(context, payload) {
    return context.dispatch('auth', {
      ...payload,
      mode: 'login',
    })
  },
  async signup(context, payload) {
    return context.dispatch('auth', {
      ...payload,
      mode: 'signup',
    })
  },
  async auth(context, payload) {
    const response = await fetch(
      'http://192.168.1.100:8765/eauthenticationapplication/issuetoken',
      {
        method: 'POST',
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify({
          username: payload.username,
          password: payload.password,
        }),
      },
    )
      .then((response) => response.json())
      .then((json) => {
        try {
          const jsonResponse = json
          const expiresIn = +jsonResponse.expiresIn * 1000
          const expirationDate = new Date().getTime() + expiresIn
          localStorage.setItem('token', jsonResponse.token)
          localStorage.setItem('username', jsonResponse.username)
          localStorage.setItem('password', jsonResponse.password)
          localStorage.setItem('tokenExpiration', expirationDate)
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
    const userId = localStorage.getItem('userId')
    const tokenExpiration = localStorage.getItem('tokenExpiration')

    const expiresIn = +tokenExpiration - new Date().getTime()

    if (expiresIn < 0) {
      return
    }

    timer = setTimeout(function () {
      context.dispatch('autoLogout')
    }, expiresIn)

    if (token && userId) {
      context.commit('setUser', {
        token: token,
        userId: userId,
      })
    }
  },
  logout(context) {
    localStorage.removeItem('token')
    localStorage.removeItem('userId')
    localStorage.removeItem('tokenExpiration')

    clearTimeout(timer)

    context.commit('setUser', {
      token: null,
      userId: null,
    })
  },
  autoLogout(context) {
    context.dispatch('logout')
    context.commit('setAutoLogout')
  },
}
