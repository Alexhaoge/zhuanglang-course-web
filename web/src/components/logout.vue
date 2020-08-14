<template>
  <el-container direction="vertical" style="padding: 2rem;">
  <el-row>
        <img src="../assets/nankai.png" alt="nankai_logo" >
        <h1 class="login-title">庄浪中学教学资源平台</h1>
  </el-row>
  <el-row type="flex" justify="center">
  <el-col :xs="22" :sm="16" :md="8">
  <el-card >
      <h3 class="login_title">欢迎，{{whoami}}</h3>
      <el-button type="primary" style="width: 20%;background: rgb(113,10,83);border: none"
        v-on:click="1">管理面板</el-button>
      <el-button type="primary" style="width: 20%;background: rgb(113,10,83);border: none"
        v-on:click="1">登出</el-button>
  </el-card>
  </el-col>
  </el-row>
  </el-container>
</template>

<script>
// import store from '../store'
export default {
  name: 'logout',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      },
      responseResult: [],
      whoami: this.$store.state.teacher.username
    }
  },
  methods: {
    isLogin () {
      return false
    //   if (this.$store.state.teacher.username) {
    //     return true
    //   } else return false
    }
  }
}
</script>

<style>
.el-card {
  border: unset;
  background-color: rgba(255, 255, 255, 0.8);
}
h1.login-title {
  font-family: "STKaiti", "Microsoft YaHei","微软雅黑",Arial,sans-serif;
  color: white;
  font-size: 1.5rem;
}
</style>
