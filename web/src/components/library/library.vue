<template>
  <el-container class="librarybody">
  <category :discipline="$route.params.discipline"></category>
  <!-- <cont v-if="lessonID !== 0"></cont>
  <section v-else-if="sectionID !== 0"></section>
  <book v-else></book> -->
  <component :is="comName" :discipline="$route.params.discipline"></component>
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
  mounted () {
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
