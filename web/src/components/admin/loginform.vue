<template>
  <el-card class="login-card" v-if="inorout">
    <el-form class="login-container"
      style="text-align: center"
      :model="loginForm"
      :inline-message="true"
      :rules="rules"
      ref="loginForm">
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" style="width: 70%;"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" style="width: 70%;"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item label="自动登录" prop="rem" :label-width="'120px'" style="text-align:left">
        <el-switch v-model="loginForm.rem"></el-switch>
      </el-form-item>
      <el-form-item >
        <el-button type="primary" style="width: 20%;background: rgb(113,10,83);border: none"
          v-on:click="login">登录</el-button>
        <el-button type="primary" style="width: 20%;background: rgb(113,10,83);border: none"
          v-on:click="$router.push('/register')">注册</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: 'loginform',
  data () {
    return {
      inorout: true,
      loginForm: {
        username: '',
        password: '',
        rem: false
      },
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    login () {
      var _this = this
      this.$axios
        .post(this.loginForm.rem ? '/login/1' : '/login/0', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(resp => {
          if (resp.data.code === 200) {
            _this.$store.commit('login', resp.data)
            // var path = _this.$route.query.redirect
            console.log(this.$store.state.username)
            // _this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
          } else {
            _this.$alert(resp.data.message, '提示', {
              confirmButtonText: '确定'
            })
          }
        }).catch(failResponse => { alert('未知错误，请联系管理员') })
    }
  }
}
</script>

<style>

</style>
