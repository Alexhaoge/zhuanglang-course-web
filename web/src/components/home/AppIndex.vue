<template>
<el-container direction="vertical">
  <div style="height:5rem"></div>
  <el-row type="flex" class="row-bg" justify="space-around">
    <el-col :xs="20" :sm="16" :md="16">
    <el-carousel
      indicator-position="outside"
      :interval="4000">
      <el-carousel-item v-for="item in ListCarousel" :key="item.id">
        <img :src="item.src" alt="site_banner" style="height:100%;width:100%;"/>
      </el-carousel-item>
    </el-carousel>
    </el-col>
  </el-row>
  <divider></divider>
  <Login></Login>
  <divider></divider>
  <h2>课程</h2>
  <el-row type="flex" class="row-bg" justify="space-around" style="margin:3rem;">
    <el-col :xs="6" :sm="5" :md="4" v-for="(item, i) in Courses" :key="i" >
    <el-card shadow="hover">
      <h3 style="margin-top:0rem;">{{item.Name}}</h3>
      <img :src="item.img" :alt="item.Name" class="image" style="width:100%;height:auto;">
      <br/><br/>
      <div class="bottom">
        <el-button v-if="item.Name == '信息技术'"
          type="primary" class="button" @click="jump(item.url)">
          查看</el-button>
        <el-button v-else
          disabled type="info" class="button" @click="jump(item.url)">
          开发中</el-button>
      </div>
    </el-card>
  </el-col>
  </el-row>
</el-container>
</template>

<script>
import Login from '../admin/login'
import divider from '../common/divider'
export default {
  name: 'AppIndex',
  components: {Login, divider},
  data () {
    return {
      ListCarousel: [
        {src: require('@/assets/banner1.png'), id: 0},
        {src: require('@/assets/banner2.png'), id: 1}
      ],
      Courses: [
        {Name: '信息技术', img: require('@/assets/it.png'), url: '/library/it'},
        {Name: '音乐', img: require('@/assets/music.png'), url: '/library/music'},
        {Name: '美术', img: require('@/assets/art.png'), url: '/library/art'},
        {Name: '体育', img: require('@/assets/pe.png'), url: '/library/pe'}
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

<style scoped>
.el-carousel {
  width: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
h2 {
  color: white;
}
</style>
