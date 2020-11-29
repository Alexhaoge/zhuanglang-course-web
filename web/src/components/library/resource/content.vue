<template>
  <el-main v-if="lesson !== 0">
  <el-row>
  <span class="content-title">模块{{thisLesson.belong.number}} 第{{thisLesson.number}}节</span>
  <newResource :belongID="thisLesson.id" @onSubmit="loadResources()"></newResource>
  </el-row>
  <el-row v-for="item in thisLesson.resources" :key="item.id">
    <el-card class="res-card">
      <div slot="header" class="res-card-head">
        <span>{{item.name}}
          <!-- 上传者: {{item.upload.username}} -->
        </span>
        <delResource :rid="item.id" @onSubmit="loadResources()"></delResource>
        <editResource :rid="item.id" @onSubmit="loadResources()"></editResource>
        <a :href="'/api/resources/'+item.path" class="card-button">下载</a>
        <p style="clear:both;margin:unset;text-align:left;">{{item.note}}</p>
      </div>
      <iframe title="item.path" style="width:100%; height: 30rem;"
        :src="getIframe(item.bilibili, item.path)">
        您当前的浏览器不支持页面上的功能，请升级您当前的浏览器版本或使用谷歌浏览器访问当前页面
      </iframe>
    </el-card>
  </el-row>
  </el-main>
</template>

<script>
import newResource from './newResource'
import delResource from './delResource'
import editResource from './editResource'
export default {
  name: 'cont',
  components: {newResource, delResource, editResource},
  data () {
    return {
      thisLesson: {},
      holder: {}
    }
  },
  computed: {
    lesson () {
      return this.$store.state.lessonID
    }
  },
  methods: {
    loadResources () {
      if (this.lesson === 0) {
        this.resources = []
        return
      }
      var _this = this
      this.$axios.get('/library/lesson/' + this.lesson).then(resp => {
        if (resp && resp.status === 200) {
          _this.thisLesson = resp.data
        }
      })
    },
    getIframe (bilibili, path) {
      if (bilibili === '' || bilibili == null) {
        return 'http://localhost:8080/api/resources/' + path
      } else {
        return 'https://player.bilibili.com/player.html?page=1&bvid=' + bilibili
      }
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
.card-button {
  float: right;
  margin: 0.5rem;
}
</style>
