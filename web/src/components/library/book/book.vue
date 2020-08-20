<template>
  <el-main v-if="book !== 0">
    <el-row>
      <span class="content-title">{{disTable[subject]}} {{thisBook.name}}</span>
      <newSection :belongID="thisBook.id" @onSubmit="loadsection()"></newSection>
    </el-row>
    <el-row>
    <el-collapse>
      <el-collapse-item v-for="(item, i) in thisBook.sections" :key="item.id"
      :title="'模块'+item.number" :name="i" class="self">
        <p style="clear:right; text-align:left;">{{item.note}}</p>
        <div>
        <delSection :sid="item.id" :sons="item.lessons.length" @onSubmit="loadsection()"></delSection>
        <editSection :sid="item.id" @onSubmit="loadsection()"></editSection>
        </div>
      </el-collapse-item>
    </el-collapse>
    </el-row>
  </el-main>
</template>

<script>
import editSection from './editSection'
import newSection from './newSection'
import delSection from './delSection'
export default {
  name: 'book',
  components: {editSection, newSection, delSection},
  data () {
    return {
      thisBook: {},
      disTable: {'IT': '信息技术', 'ART': '美术', 'MUSIC': '音乐', 'PE': '体育'}
    }
  },
  computed: {
    book () {
      return this.$store.state.bookID
    },
    subject () {
      return this.$store.state.subject
    }
  },
  methods: {
    loadsection () {
      // console.log('load book:' + this.section)
      var _this = this
      this.$axios.get('/library/book/' + this.book).then(resp => {
        // console.log(resp)
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
