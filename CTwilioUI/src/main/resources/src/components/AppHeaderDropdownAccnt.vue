<template>
  <CDropdown variant="nav-item">
    <CDropdownToggle placement="bottom-end" class="py-0" :caret="false">
      <CAvatar :src="avatar" size="md" />
    </CDropdownToggle>
    <CDropdownMenu class="pt-0">
      <CDropdownItem @click="logoutApp">
        <CIcon icon="cil-lock-locked" />Logout
      </CDropdownItem>
    </CDropdownMenu>
  </CDropdown>
</template>

<script>
import avatar from '@/assets/images/avatars/8.jpg'
import router from '@/router'
export default {
  name: 'AppHeaderDropdownAccnt',
  setup() {
    return {
      avatar: avatar,
      itemsCount: 42,
    }
  },
  methods: {
    logoutApp() {
      localStorage.clear()
      console.log(this.$router)
      const redirectUrl = '/pages/Login'
      //this.$router.replace(redirectUrl)
      router.replace(redirectUrl)
    },
  },
}
</script>
