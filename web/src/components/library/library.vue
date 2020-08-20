<template>
  <el-container class="librarybody">
  <category></category>
  <component :is="comName"></component>
  </el-container>
</template>

<script>
import category from './category/category'
import cont from './resource/content'
import sect from './section/section'
import book from './book/book'
export default {
  name: 'library',
  components: {category, cont, sect, book},
  data () {
    return {
      comName: 'book'
    }
  },
  created () {
    // 父子组件的执行顺序为，父组件beforeCreated ->父组件created
    // ->父组件beforeMounted ->子组件beforeCreated ->子组件created
    // ->子组件beforeMounted ->子组件mounted -> 父组件mounted
    var Disciplines = ['IT', 'MUSIC', 'ART', 'PE']
    var s = this.$route.params.discipline.toUpperCase()
    var isValidDiscipline = false
    for (var i = 0; i < Disciplines.length; i++) {
      if (s === Disciplines[i]) {
        isValidDiscipline = true
        break
      }
    }
    if (isValidDiscipline === false) {
      this.$router.push('/error')
    }
    this.$store.commit('updateSubject', {subject: s})
    this.comSwitch()
  },
  computed: {
    sectionID () {
      return this.$store.state.sectionID
    },
    lessonID () {
      return this.$store.state.lessonID
    }
  },
  methods: {
    comSwitch () {
      if (this.lessonID !== 0) {
        this.comName = 'cont'
      } else if (this.sectionID !== 0) {
        this.comName = 'sect'
      } else {
        this.comName = 'book'
      }
      console.log('comSwitch ' + this.comName)
    }
  },
  watch: {
    sectionID: function () {
      this.comSwitch()
    },
    lessonID: function () {
      this.comSwitch()
    },
    $route (to, from) {
      this.$store.commit('updateSubject', {subject: this.$route.params.discipline.toUpperCase()})
      this.$store.commit('sendLesson', { lessonID: 0, bookID: 0, sectionID: 0 })
      console.log(this.$store.state.subject)
    }
  }
}
</script>

<style>
.librarybody {
  min-height: 20rem;
  margin-top: 2rem;
  margin-left: 3rem;
  margin-right: 3rem;
}
.content-title {
  color: white;
  font-size: 2rem;
  float: left;
  margin-left: 2rem;
}
</style>
