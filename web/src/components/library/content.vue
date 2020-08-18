<template>
  <el-main v-if="lesson !== 0">
  <el-row>
  <span class="content-title">模块{{thisLesson.belong.belong.number}} 第{{thisLesson.belong.number}}节</span>
  </el-row>
  <el-row v-for="item in thisLesson.resources" :key="item.id">
    <el-card class="res-card">
      <div slot="header" class="res-card-head">
        <span>{{item.name}} 上传者: {{item.upload.username}}</span>
        <a :href="'/resources/'+item.path" style="float: right;">下载</a>
        <p style="clear:both;margin:unset;text-align:left;">{{item.note}}</p>
      </div>
      <iframe title="item.path" style="width:100%; height: 30rem;"
        :src="'//player.bilibili.com/player.html?aid=498838562&bvid=BV1CK411H7d2&cid=210278807&page=1'">
        您当前的浏览器不支持页面上的功能，请升级您当前的浏览器版本或使用谷歌浏览器访问当前页面
      </iframe>
    </el-card>
  </el-row>
  </el-main>
</template>

<script>

export default {
  name: 'cont',
  data () {
    return {
      thisLesson: {}
    }
  },
  computed: {
    lesson () {
      return this.$store.state.lessonID
    }
  },
  methods: {
    loadResources () {
      var les = this.lesson
      if (les === 0) {
        this.resources = []
        return
      }
      var _this = this
      this.$axios.get('/library/lesson/' + les).then(resp => {
        if (resp && resp.status === 200) {
          _this.thisLesson = resp.data
        }
      })
    }
  },
  watch: {
    lesson: function () {
      this.loadResources()
    },
    discipline: function () {
      this.resources = []
    }
  },
  mounted () {
    this.loadResources()
  },
  beforeRouteUpdate (to, from, next) {
    this.loadResources()
  }
}
</script>

<style>
/* .content-title {
  color: white;
  font-size: 2rem;
  float: left;
  margin-left: 2rem;
} */
.res-card {
  width: 90%;
  background-color: rgba(255, 255, 255, 0.4);
  margin: 1rem;
}
.res-card-head:before,
.res-card-head:after {
  display: table;
  content: "";
}
.res-card-head:after {
  clear: both
}
.res-card-head span {
  color: black;
  font-weight: bold;
  float: left;
}
</style>
