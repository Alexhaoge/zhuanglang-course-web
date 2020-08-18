<template>
  <el-main>
    <el-row>
      <span class="content-title">{{thisSection.belong.name}} 模块{{thisSection.number}}</span>
    </el-row>
    <el-row>
    <el-collapse>
      <el-collapse-item v-for="(item, i) in thisSection.lessons" :key="item.id"
      :title="'第'+item.number+'节'" :name="i">
        <p style="clear:right; text-align:left;">{{item.note}}</p>
        <el-button type="primary" icon="el-icon-edit" circle></el-button>
        <el-button type="danger" icon="el-icon-delete" circle></el-button>
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
      thisSection: {}
    }
  },
  computed: {
    section () {
      return this.$store.state.sectionID
    }
  },
  methods: {
    loadLesson () {
      console.log('load sec:' + this.section)
      var _this = this
      this.$axios.get('/library/section/' + this.section).then(resp => {
        console.log(resp)
        if (resp && resp.status === 200) {
          _this.thisSection = resp.data
        }
      })
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
