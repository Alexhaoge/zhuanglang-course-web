<template>
  <el-main v-if="book !== 0">
    <el-row>
      <span class="content-title">{{disTable[discipline.toUpperCase()]}} {{thisBook.name}}</span>
      <el-button type="info" style="">添加模块</el-button>
    </el-row>
    <el-row>
    <el-collapse>
      <el-collapse-item v-for="(item, i) in thisBook.sections" :key="item.id"
      :title="'模块'+item.number" :name="i" class="self">
        <p style="clear:right; text-align:left;">{{item.note}}</p>
        <div>
        <el-button type="primary" icon="el-icon-edit"
          circle @click="fatherBook = item.id"></el-button>
        <editSec ></editSec>
        <el-button type="danger" icon="el-icon-delete"
          circle @click="deleteSection(item.id)"></el-button>
        </div>
      </el-collapse-item>
    </el-collapse>
    </el-row>
  </el-main>
</template>

<script>
import editSec from './editSec'
export default {
  name: 'book',
  component: {editSec},
  props: {
    discipline: {
      type: String
    }
  },
  data () {
    return {
      thisBook: {'name': '1', 'sections': [{'number': '1', 'note': '1', 'id': 1}]},
      disTable: {'IT': '信息技术', 'ART': '美术', 'MUSIC': '音乐', 'PE': '体育'},
      fatherBook: 0
    }
  },
  computed: {
    book () {
      return this.$store.state.bookID
    }
  },
  methods: {
    loadsection () {
      console.log('load book:' + this.section)
      var _this = this
      this.$axios.get('/library/book/' + this.book).then(resp => {
        console.log(resp)
        if (resp && resp.status === 200) {
          _this.thisBook = resp.data
        }
      })
    },
    deleteSection (id) {
      this.$confirm('此操作将永久删除该模块及其包含的小节和教学资源, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/delete/section', {id: id}).then(resp => {
            if (resp && resp.status === 200) {
              this.$message({type: 'info', message: '删除成功'})
              this.$store.commit('updateCategory', {updateCat: true})
              this.loadsection()
            } else { this.$message.error('删除失败') }
          })
      }).catch(() => { this.$message({type: 'info', message: '已取消删除'}) })
    }
  },
  watch: {
    book: function () {
      this.loadsection()
    }
  },
  mounted () {
    this.loadsection()
  }
}
</script>

<style>
.el-collapse {
  margin: 3rem;
  width: 60%;
}

.el-collapse-item__header {
  padding-left: 2rem;
  font-size: 1rem;
  background-color: rgba(255, 255, 255, 0.7);
}
.el-collapse-item__wrap {
  padding-left: 2rem;
  padding-right: 2rem;
  background-color: rgba(255, 255, 255, 0.7);
}
/* .el-button.edit {
  float: left;
}
.el-button.del {
  float: left;
} */
</style>
