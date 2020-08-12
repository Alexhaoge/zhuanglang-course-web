<template>
  <el-container direction="vertical">
  <el-row>
        <img src="../assets/nankai.png" alt="nankai_logo" >
        <h1 class="login-title">庄浪中学教学资源平台</h1>
  </el-row>
  <el-row type="flex" justify="center">
  <el-col :xs="22" :sm="16" :md="8">
  <el-card >
    <el-form class="login-container"
          :model="loginForm" :rules="rules" ref="loginForm">
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" style="width: 70%;"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" style="width: 70%;"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item >
        <el-button type="primary" style="width: 20%;background: rgb(113,10,83);border: none"
                  v-on:click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </el-card>
  </el-col>
  </el-row>
  </el-container>
</template>

<script>
let sha1 = require('js-sha1')
export default {
  name: 'Login',
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
      responseResult: []
    }
  },
  methods: {
    login () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
        .post('/login', {
          username: this.loginForm.username,
          password: sha1(this.loginForm.password)
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            // this.$router.replace({path: '/index'})
            _this.$store.commit('login', _this.loginForm)
            var path = this.$route.query.redirect
            this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
          } else if (successResponse.data.code === 400) {
            this.$message({
              message: '账户或密码错误！',
              type: 'warning'
            })
          }
        })
        .catch(failResponse => {
          alert('未知错误，请联系管理员')
        })
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
