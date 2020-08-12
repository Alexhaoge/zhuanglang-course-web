<template>
  <el-menu
    :default-active="currentPath"
    router
    mode="horizontal"
    background-color="transparent"
    text-color="white"
    active-text-color="red"
    style="min-width: 100%;">
    <el-menu-item class="title" index="/wtf">服务学习 - 庄浪教学资源平台</el-menu-item>
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
    <div id="logo-container" style="float:right; max-height:60%;pointer-events: none;">
      <img src="../../assets/nankai2.png" alt="site_logo">
    </div>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/download', navItem: '下载'},
        {name: '/library', navItem: '目录'},
        {name: '/admin', navItem: '管理'}
      ]
    }
  },
  computed: {
    hoverBackground () {
      return '#ffd04b'
    },
    currentPath () {
      var x = this.$route.path.indexOf('/', 1)
      if (x !== -1) {
        return this.$route.path.substring(0, x)
      } else {
        return this.$route.path
      }
    }
  }
}
</script>

<style>
a{
  text-decoration: none;
}
.title {
  font-size: 1.5rem;
  pointer-events: none;
  color: antiquewhite;
  font-family: 'STKaiti','Microsoft YaHei',sans-serif;
}
</style>
