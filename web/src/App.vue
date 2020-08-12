<template>
  <div id="app">
    <el-container>
    <!-- <div style="background-color:rgba(113,10,83);padding:0.5rem;">
    <el-divider class="head-divider"></el-divider>
    </div> -->
    <nav-menu class="nav-menu"></nav-menu>
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
import NavMenu from '@/components/common/NavMenu'
export default {
  name: 'App',
  components: {NavMenu}
}
</script>

<style>
#app {
  font-family: 'Avenir',Helvetica,'iragino Sans GB','Microsoft YaHei',Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color:rgba(113,10,83);
}

body {
  margin: 0rem;
}
.head-divider {
  background-color: darkcyan;
  width: 100%;
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
