<template>
  <el-container direction="vertical">
    <div style="height:5rem;"></div>
    <el-row type="flex" justify="center">
      <h1 class="efont">错误 404!</h1>
    </el-row>
    <div style="height:5rem;"></div>
    <el-row type="flex" justify="center">
      <p class="efont">您想要访问的页面不存在</p>
    </el-row>
    <div style="height:5rem;"></div>
    <el-row type="flex" justify="center">
      <el-menu router mode="horizontal"
      :default-active="this.$router.path"
      background-color="transparent"
      text-color="white"
      style="border-bottom:unset">
        <el-menu-item :key="1" index="/index">首页</el-menu-item>
    </el-menu>
    </el-row>
    <div :style="{ height: '10rem' }"></div>
  </el-container>
</template>

<script>
export default {
  name: 'error',
  data () {
    return {
      height: `${document.documentElement.clientHeight}`
    }
  },
  created () {
    this.windowHeight(document.documentElement.clientHeight)
  },
  mounted () {
    const _this = this
    window.onresize = () => {
      return (() => {
        _this.height = `${document.documentElement.clientHeight}`
      })()
    }
  }
}
</script>

<style>
.efont {
  font-size: 1.5rem;
  color: azure;
}
</style>
