<template>
  <el-aside>
    <el-menu
      background-color="transparent"
      text-color="white"
      :unique-opened="true"
      :default-openeds="[]">
      <el-submenu v-for="item in books" :key="item.name" :index="''+item.id">
        <template slot="title">{{item.name}}</template>
        <el-submenu v-for="sec in item.sections" :key="sec.number" :index="''+item.id+'/'+sec.id">
          <template slot="title">模块{{sec.number}}</template>
          <el-menu-item v-for="les in sec.lessons"
            :key="les.number" :index="discipline+'/'+item.id+'/'+sec.id+'/'+les.id"
            @click="sendLesson(les.id)">
            <template slot="title">第{{les.number}}节</template>
          </el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: 'category',
  props: {
    discipline: {
      type: String
    }
  },
  data () {
    return {
      books: [{'id': 1, 'name': '八年级上', 'discipline': 'IT', 'upload': {'username': 'Alexhaoge'}, 'sections': [{'id': 1, 'number': 1, 'note': 'Test', 'lessons': [{'id': 1, 'number': 1, 'note': 'Test', 'resources': [{'id': 1, 'path': '1.pdf', 'md5': null, 'bilibili': null, 'note': 'Test', 'vorS': 'SLIDE'}]}, {'id': 3, 'number': 2, 'note': 'Test', 'resources': []}, {'id': 2, 'number': 3, 'note': 'Test', 'resources': []}]}, {'id': 3, 'number': 2, 'note': 'Test', 'lessons': []}, {'id': 2, 'number': 3, 'note': 'Test', 'lessons': [{'id': 4, 'number': 1, 'note': 'Test', 'resources': []}, {'id': 5, 'number': 2, 'note': 'Test', 'resources': []}]}]}, {'id': 2, 'name': '八年级下', 'discipline': 'IT', 'upload': {'username': 'Alexhaoge'}, 'sections': [{'id': 4, 'number': 2, 'note': 'Test', 'lessons': []}, {'id': 5, 'number': 3, 'note': 'Test', 'lessons': [{'id': 6, 'number': 2, 'note': 'Test', 'resources': []}, {'id': 7, 'number': 3, 'note': 'Test', 'resources': []}]}]}],
      s: ''
    }
  },
  methods: {
    loadBooks () {
      var _this = this
      this.$axios.get('/library/' + this.discipline.toUpperCase() + '/book').then(resp => {
        if (resp && resp.status === 200) {
          _this.books = resp.data
        }
      })
    },
    sendLesson (lesson) {
      this.$store.commit('sendLesson', {
        lessonID: lesson
      }
      )
    }
  },
  mounted () {
    this.loadBooks()
  },
  beforeRouteUpdate (to, from, next) {
    this.loadBooks()
  },
  watch: {
    discipline: function () {
      this.loadBooks()
    }
  }
}
</script>

<style>

</style>
