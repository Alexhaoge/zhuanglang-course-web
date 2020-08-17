<template>
  <div id="app" :style="apps">
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
  components: {NavMenu, Footer},
  data () {
    return {
      apps: {
        minHeight: '',
        backgroundcolor: 'rgb(113,10,83)',
        overflow: 'auto'
      }
    }
  },
  methods: {
    getHeight () {
      this.apps.height = window.innerHeight + 'px'
    }
  },
  created () {
    window.addEventListener('resize', this.getHeight)
    this.getHeight()
  }
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
/* html,body,#app {height: 100vh;} */
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
  background-color: rgba(216, 197, 211, 0.2);
  display: flex;
  justify-content: center;
  padding: unset;
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>
