<template>
  <div id="app">
    <el-container>
    <!-- <div style="background-color:rgba(113,10,83);padding:0.5rem;">
    <el-divider class="head-divider"></el-divider>
    </div> -->
    <router-view/>
    <div style="height: 20rem"></div>
    <el-footer>
      <el-menu mode="horizontal">
        <el-menu-item >服务学习：庄浪教学资源平台</el-menu-item>
        <el-menu-item><a href="https://github.com/Alexhaoge/" target="_blank">开发者：Alexhaoge</a></el-menu-item>
        <el-menu-item><a href="https://creativecommons.org/licenses/by-nc-sa/3.0/cn/" target="_blank">本网站遵循CC BY-NC-SA 3.0 CN协议</a></el-menu-item>
      </el-menu>
    </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  /* color: #585858; */
}

body {
  margin: unset;
}
.el-header {
  display: flex;
  align-content: flex-start;
  align-items: center;
  text-align: center;
  background-color:rgba(113,10,83);
  padding-top: 0.5rem;
}
.head-title{
  font-size: 1.2rem;
  font-family: 'STKaiti','Microsoft YaHei',sans-serif;
  color: antiquewhite;
}
.head-divider {
  background-color: darkcyan;
  width: 90%;
  margin: 3rem;
  margin-top: 0rem;
  margin-bottom: 0rem;
}
.el-main {
  background-color: rgba(113,10,83);
}
.el-footer {
  background-color: rgb(233, 236, 239);
  display: flex;
  justify-content: center;
  padding: unset;
}
.el-footer .el-menu {
  background-color: transparent;
}
.el-footer .el-menu .el-menu-item a{
  text-decoration: none;
}
</style>
