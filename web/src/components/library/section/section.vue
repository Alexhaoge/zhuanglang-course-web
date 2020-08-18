<template>
  <el-main>
    <el-row>
      <span class="content-title">{{thisSection.belong.name}} 模块{{thisSection.number}}</span>
      <el-button type="info" style="">添加小节</el-button>
    </el-row>
    <el-row>
    <el-collapse>
      <el-collapse-item v-for="(item, i) in thisSection.lessons" :key="item.id"
      :title="'第'+item.number+'节'" :name="i">
        <p style="clear:right; text-align:left;">{{item.note}}</p>
        <el-button type="primary" icon="el-icon-edit" circle></el-button>
        <el-button type="danger" icon="el-icon-delete"
          circle @click="deleteLesson(item.id)"></el-button>
      </el-collapse-item>
    </el-collapse>
    </el-row>
  </el-main>
</template>

<script>
export default {
  name: 'sect',
  data () {
    return {
      thisSection: {belong: {name: 1}, number: 1, lessons: [{id: 1, number: 1, note: 1}]}
    }
  },
  computed: {
    section () {
      return this.$store.state.sectionID
    }
  },
  methods: {
    loadLesson () {
      // console.log('load sec:' + this.section)
      var _this = this
      this.$axios.get('/library/section/' + this.section).then(resp => {
        // console.log(resp)
        if (resp && resp.status === 200) {
          _this.thisSection = resp.data
        }
      })
    },
    deleteLesson (id) {
      this.$confirm('此操作将永久删除该小节及其包含的教学资源, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/delete/lesson', {id: id}).then(resp => {
            if (resp && resp.status === 200) {
              this.$message({type: 'info', message: '删除成功'})
              this.$store.commit('updateCategory', {updateCat: true})
              this.loadLesson()
            } else { this.$message.error('删除失败') }
          })
      }).catch(() => { this.$message({type: 'info', message: '已取消删除'}) })
    }
  },
  watch: {
    section: function () {
      this.loadLesson()
    }
  },
  mounted () {
    this.loadLesson()
  }
}
</script>

<style>

</style>
