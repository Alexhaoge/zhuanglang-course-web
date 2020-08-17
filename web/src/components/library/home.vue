<template>
  <el-container direction="vertical">
    <el-row type="flex" justify="space-around" align="middle"
       v-for="(item, i) in Courses" :key="i" style="margin: 1.5rem;">
    <el-col :span="8">
      <el-card shadow="hover">
        <h2 style="margin-top:0rem;float:left;">{{item.Name}}</h2>
        <el-button type="info"
          style="float: right;" @click="jump(item.url)">
          查看
        </el-button>
      </el-card>
    </el-col>
    </el-row>
  </el-container>
</template>

<script>
export default {
  name: 'libraryhome',
  data () {
    return {
      Courses: [
        {Name: '信息技术', url: '/library/it'},
        {Name: '音乐', url: '/library/music'},
        {Name: '美术', url: '/library/art'},
        {Name: '体育', url: '/library/pe'}
      ]
    }
  },
  methods: {
    jump (url) {
      this.$router.push(url)
    }
  }
}
</script>

<style>

</style>
