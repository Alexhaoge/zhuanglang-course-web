<template>
  <el-container direction="vertical" style="padding: 2rem;">
  <el-row>
        <img src="@/assets/logo.png" alt="nankai_logo" >
        <h1 class="login-title">庄浪中学教学资源平台</h1>
  </el-row>
  <el-row type="flex" justify="center">
  <el-col :xs="22" :sm="16" :md="8">
  <component :is="inorout"></component>
  </el-col>
  </el-row>
  </el-container>
</template>

<script>
import loginform from './loginform'
import logout from './logout'
export default {
  name: 'Login',
  components: {loginform, logout},
  inject: ['reload'],
  data () {
    return {
      inorout: 'loginform'
    }
  },
  computed: {
    whoami () {
      return this.$store.state.username
    }
  },
  watch: {
    whoami: function () {
      this.changeview()
    }
  },
  methods: {
    changeview () {
      this.inorout = (this.whoami !== '' && this.whoami != null) ? 'logout' : 'loginform'
    }
  },
  created () {
    this.changeview()
  },
  mounted () {
    this.changeview()
  }
}
</script>

<style>
.el-card.login-card {
  border: unset;
  background-color: rgba(255, 255, 255, 0.8);
}
h1.login-title {
  font-family: "STKaiti", "Microsoft YaHei","微软雅黑",Arial,sans-serif;
  color: white;
  font-size: 1.5rem;
}
</style>
