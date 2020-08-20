<template>
  <el-main>
    <el-row>
      <span class="content-title">{{thisSection.belong.name}} 模块{{thisSection.number}}</span>
      <newLesson :belongID="thisSection.id" @onSubmit="loadLesson()"></newLesson>
    </el-row>
    <el-row>
    <el-collapse>
      <el-collapse-item v-for="(item, i) in thisSection.lessons" :key="item.id"
      :title="'第'+item.number+'节'" :name="i">
        <p style="clear:right; text-align:left;">{{item.note}}</p>
        <delLesson :lid="item.id" :sons="item.resources.length" @onSubmit="loadLesson()"></delLesson>
        <editLesson :lid="item.id" @onSubmit="loadLesson()"></editLesson>
      </el-collapse-item>
    </el-collapse>
    </el-row>
  </el-main>
</template>

<script>
import delLesson from './delLesson'
import newLesson from './newLesson'
import editLesson from './editLesson'
export default {
  name: 'sect',
  components: {delLesson, editLesson, newLesson},
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
      // console.log('load sec:' + this.section)
      var _this = this
      this.$axios.get('/library/section/' + this.section).then(resp => {
        // console.log(resp)
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
