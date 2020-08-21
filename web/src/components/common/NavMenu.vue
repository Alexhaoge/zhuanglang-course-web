<template>
  <el-menu
    :default-active="currentPath"
    router
    mode="horizontal"
    background-color="rgba(113,10,83,0.3)"
    text-color="white"
    active-text-color="aqua"
    style="min-width: 100%;">
    <el-menu-item id="sitetitle" index="/wtf">服务学习 - 庄浪教学资源平台</el-menu-item>
    <template v-for="(item,i) in navList">
      <el-submenu v-if="item.navItem=='目录'" :key="i" :index="item.name">
        <template slot="title">{{item.navItem}}</template>
        <el-menu-item v-for="(book,i) in bookList" :key="i" :index="book.name">
          {{book.navItem}}
        </el-menu-item>
      </el-submenu>
      <el-menu-item v-else :key="i" :index="item.name" :disabled="item.isDisabled">
        {{item.navItem}}
      </el-menu-item>
    </template>
    <div id="logo-container" style="float:right; max-height:60%;pointer-events: none;">
      <img src="../../assets/logo2.png" alt="site_logo">
    </div>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/', navItem: '首页'},
        {name: '/download', navItem: '下载', isDisabled: true},
        {name: '/library', navItem: '目录'},
        {name: '/admin', navItem: '管理', isDisabled: true}
      ],
      bookList: [
        {name: '/library/it', navItem: '信息技术'},
        {name: '/library/music', navItem: '音乐'},
        {name: '/library/art', navItem: '美术'},
        {name: '/library/pe', navItem: '体育'}
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
#sitetitle {
  font-size: 1.3rem;
  pointer-events: none;
  color: antiquewhite;
  font-family: 'STKaiti','Microsoft YaHei',sans-serif;
}
</style>
