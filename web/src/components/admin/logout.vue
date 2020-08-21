<template>
  <el-card>
    <h3 class="login_title">欢迎，{{whoami}}</h3>
    <el-button type="primary" disabled style="width: 20%;background: rgb(113,10,83);border: none"
      @click="$router.replace({path: '/admin'})">管理面板</el-button>
    <el-button type="primary" style="width: 20%;background: rgb(113,10,83);border: none"
      @click="logout">登出</el-button>
  </el-card>
</template>

<script>
export default {
  name: 'logout',
  computed: {
    whoami () {
      return this.$store.state.username
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$axios.get('/logout').then(resp => {
        if (resp.data.code === 200) {
          // 前后端状态保持一致
          _this.$store.commit('logout')
          _this.$router.replace('/index')
        }
      })
    }
  }
}
</script>

<style>

</style>
