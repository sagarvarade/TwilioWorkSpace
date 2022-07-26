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
      return 'Login'
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
        }
      } catch (err) {
        this.error = err.message || 'Failed to authenticate, try later.'
      }
      setTimeout(() => {
        if (localStorage.getItem('token') == null) {
          const redirectUrl = '/pages/Login'
          this.$router.replace(redirectUrl)
        } else {
          const redirectUrl = '/dashboard'
          console.log('Login succes ', redirectUrl)
          this.$router.replace(redirectUrl)
        }
      }, 300)
      this.isLoading = false
    },
    handleError() {
      this.error = null
    },
  },
}
</script>
