<template>
  <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol :md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm>
                  <h1>Login</h1>
                  <p class="text-medium-emphasis">Sign In to your account</p>
                  <CInputGroup class="mb-3">
                    <CInputGroupText>
                      <CIcon icon="cil-user" />
                    </CInputGroupText>
                    <CFormInput
                      placeholder="Username"
                      autocomplete="username"
                      id="username"
                      v-model.trim="username"
                    />
                  </CInputGroup>
                  <CInputGroup class="mb-4">
                    <CInputGroupText>
                      <CIcon icon="cil-lock-locked" />
                    </CInputGroupText>
                    <CFormInput
                      type="password"
                      placeholder="Password"
                      autocomplete="current-password"
                      id="password"
                      v-model.trim="password"
                    />
                  </CInputGroup>
                  <CRow>
                    <CCol :xs="2">
                      <CButton color="primary" class="px-4" @click="submitForm">
                        {{ submitButtonCaption }}
                      </CButton>
                    </CCol>
                    <CCol :xs="5">
                      <CButton
                        color="primary"
                        class="px-4"
                        type="button"
                        mode="flat"
                        @click="switchAuthMode"
                        >{{ switchModeButtonCaption }}</CButton
                      >
                    </CCol>
                    <CCol :xs="6" class="text-right">
                      <CButton color="link" class="px-0">
                        Forgot password?
                      </CButton>
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      formIsValid: true,
      mode: 'login',
      isLoading: false,
      error: null,
    }
  },
  computed: {
    submitButtonCaption() {
      if (this.mode === 'login') {
        return 'Login'
      } else {
        return 'Signup'
      }
    },
    switchModeButtonCaption() {
      if (this.mode === 'login') {
        return 'Signup instead'
      } else {
        return 'Login instead'
      }
    },
  },
  methods: {
    async submitForm() {
      this.formIsValid = true
      if (this.username === '' || this.password.length < 2) {
        this.formIsValid = false
        alert('Fill form')
        return
      }

      this.isLoading = true

      const actionPayload = {
        username: this.username,
        password: this.password,
      }

      try {
        if (this.mode === 'login') {
          try {
            await this.$store.dispatch('login', actionPayload)
          } catch (err) {
            this.error = err.message || 'Failed to authenticate, try later.'
            return
          }
        } else {
          try {
            await this.$store.dispatch('signup', actionPayload)
          } catch (err) {
            this.error = err.message || 'Failed to authenticate, try later.'
            return
          }
        }
      } catch (err) {
        this.error = err.message || 'Failed to authenticate, try later.'
      }
      setTimeout(() => {
        if (localStorage.getItem('token') == null) {
          alert('Failed to Login')
          const redirectUrl = '/pages/Login'
          this.$router.replace(redirectUrl)
        } else {
          const redirectUrl = '/' + (this.$route.query.redirect || 'dashboard')
          this.$router.replace(redirectUrl)
        }
      }, 300)
      this.isLoading = false
    },
    switchAuthMode() {
      if (this.mode === 'login') {
        this.mode = 'signup'
      } else {
        this.mode = 'login'
      }
    },
    handleError() {
      this.error = null
    },
  },
}
</script>
