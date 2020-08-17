<template>
  <el-container class="librarybody">
  <category :discipline="$route.params.discipline"></category>
  <cont :discipline="$route.params.discipline"></cont>
  </el-container>
</template>

<script>
import category from './category'
import cont from './content'
export default {
  name: 'library',
  components: {category, cont},
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
</style>
