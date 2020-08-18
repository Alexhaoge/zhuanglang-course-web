<template>
  <el-main v-if="lesson !== 0">
  <el-row>
  <span class="content-title">模块{{thisLesson.belong.belong.number}} 第{{thisLesson.belong.number}}节</span>
  <el-button type="info" style="float:right; margin-right:5rem;">添加教学资源</el-button>
  </el-row>
  <el-row v-for="item in thisLesson.resources" :key="item.id">
    <el-card class="res-card">
      <div slot="header" class="res-card-head">
        <span>{{item.name}} 上传者: {{item.upload.username}}</span>
        <el-button type="danger" icon="el-icon-delete" class="card-button"
          @click="deleteResource(item.id)"></el-button>
        <el-button type="primary" icon="el-icon-edit" class="card-button"></el-button>
        <a :href="'/resources/'+item.path" class="card-button">下载</a>
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
      thisLesson: {'id': 1, 'number': 1, 'note': 'Test', 'upload': {'username': 'Alexhaoge'}, 'belong': {'id': 1, 'number': 1, 'note': 'Test', 'belong': {'id': 1, 'name': '八年级上', 'discipline': 'IT'}}, 'resources': [{'id': 2, 'path': '1.mp4', 'md5': null, 'name': '1.mp4', 'bilibili': null, 'note': 'Test', 'upload': {'username': 'Alexhaoge'}, 'vorS': 'VIDEO'}, {'id': 1, 'path': '1.pdf', 'md5': null, 'name': '1.pdf', 'bilibili': null, 'note': 'Test', 'upload': {'username': 'Alexhaoge'}, 'vorS': 'SLIDE'}]}
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
    },
    deleteResource (id) {
      this.$confirm('此操作将永久删除该小节及其包含的教学资源, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/delete/resource', {id: id}).then(resp => {
            if (resp && resp.status === 200) {
              this.loadResources()
              if (resp.data.code) {
                this.$message({type: 'warning', message: '删除成功, 但后台文件错误'})
              } else { this.$message({type: 'info', message: '删除成功'}) }
            } else { this.$message.error('删除失败') }
          })
      }).catch(() => { this.$message({type: 'info', message: '已取消删除'}) })
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
.card-button {
  float: right;
  margin: 0.5rem;
}
</style>
