<template>
  <el-main v-if="book !== 0">
    <el-row>
      <span class="content-title">{{disTable[discipline.toUpperCase()]}} {{thisBook.name}}</span>
    </el-row>
    <el-row>
    <el-collapse>
      <el-collapse-item v-for="(item, i) in thisBook.sections" :key="item.id"
      :title="'模块'+item.number" :name="i">
        <p style="clear:right; text-align:left;">{{item.note}}</p>
        <div>
        <el-button class="edit" type="primary" icon="el-icon-edit" circle></el-button>
        <el-button class="del" type="danger" icon="el-icon-delete" circle></el-button>
        </div>
      </el-collapse-item>
    </el-collapse>
    </el-row>
  </el-main>
</template>

<script>
export default {
  name: 'book',
  props: {
    discipline: {
      type: String
    }
  },
  data () {
    return {
      thisBook: {'name': '1', 'sections': [{'number': '1', 'note': '1', 'id': 1}]},
      disTable: {'IT': '信息技术', 'ART': '美术', 'MUSIC': '音乐', 'PE': '体育'}
    }
  },
  computed: {
    book () {
      return this.$store.state.bookID
    }
  },
  methods: {
    loadsection () {
      var _this = this
      this.$axios.get('/library/book/' + this.book).then(resp => {
        if (resp && resp.status === 200) {
          _this.thisBook = resp.data
        }
      })
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
  background-color: rgba(255, 255, 255, 0.5);
}
.el-collapse-item {
  background-color: rgba(255, 255, 255, 0.5);
}
.el-collapse-item__header {
  padding-left: 2rem;
  font-size: 1rem;
}
.el-collapse-item__wrap {
  padding-left: 2rem;
  padding-right: 2rem;
}
/* .el-button.edit {
  float: left;
}
.el-button.del {
  float: left;
} */
</style>
