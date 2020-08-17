<template>
  <el-main style="padding: 1.5rem;">
  <template v-if="lesson !== 0">
  <el-row v-for="item in resources" :key="item.id">
    <a :href="'/resources/'+item.path">
      {{item.id}} {{item.path}}
    </a>
    <iframe title="item.path"
      :src="'/resources/'+item.path">
      您当前的浏览器不支持页面上的功能，请升级您当前的浏览器版本或使用谷歌浏览器访问当前页面
    </iframe>
  </el-row>
  </template>
  </el-main>
</template>

<script>
export default {
  name: 'cont',
  props: {
    discipline: {
      type: String
    }
  },
  data () {
    return {
      resources: [{'id': 1, 'path': '1.pdf', 'md5': null, 'bilibili': null, 'note': 'Test', 'upload': {'username': 'Alexhaoge'}, 'belong': {'id': 1, 'number': 1, 'note': 'Test', 'belong': {'id': 1, 'number': 1, 'note': 'Test', 'belong': {'id': 1, 'name': '八年级上', 'discipline': 'IT'}}}, 'vorS': 'SLIDE'}, {'id': 2, 'path': '1.mp4', 'md5': null, 'bilibili': null, 'note': 'Test', 'upload': {'username': 'Alexhaoge'}, 'belong': {'id': 1, 'number': 1, 'note': 'Test', 'belong': {'id': 1, 'number': 1, 'note': 'Test', 'belong': {'id': 1, 'name': '八年级上', 'discipline': 'IT'}}}, 'vorS': 'VIDEO'}]
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
      this.$axios.get('/library/lesson/' + les + '/resource').then(resp => {
        if (resp && resp.status === 200) {
          _this.resources = resp.data
        }
      })
    }
  },
  watch: {
    lesson: function () {
      this.loadResources()
    },
    discipline: function () {
      this.resources = []
    }
  }
}
</script>

<style>

</style>
