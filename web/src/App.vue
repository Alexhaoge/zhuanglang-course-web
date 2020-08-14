<template>
  <div id="app">
    <el-container>
    <el-header>
    <nav-menu class="nav-menu"></nav-menu>
    </el-header>
    <router-view/>
    <div style="height: 3rem"></div>
    <el-footer>
      <Footer class="footer"></Footer>
    </el-footer>
    </el-container>
  </div>
</template>

<script>
import NavMenu from '@/components/common/NavMenu'
import Footer from '@/components/common/Footer'
export default {
  name: 'App',
  components: {NavMenu, Footer}
}
</script>

<style>
#app {
  font-family: 'Avenir',Helvetica,'iragino Sans GB','Microsoft YaHei',Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: rgb(113,10,83);
}

body {
  margin: 0rem;
}
.el-header {
  background-color: rgb(113,10,83);
  margin: 1rem;
}
.el-main {
  background-color: rgb(113,10,83);
}
.el-footer {
  /* background-color: rgb(233, 236, 239); */
  background-color: transparent;
  display: flex;
  justify-content: center;
  padding: unset;
}
</style>
