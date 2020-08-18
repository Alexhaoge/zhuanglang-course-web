<template>
  <el-aside>
    <el-menu
      background-color="transparent"
      text-color="white"
      @open="send($event)"
      @close="send($event)"
      :unique-opened="true"
      :default-openeds="[]">
      <el-submenu v-for="item in books" :key="item.name"
        :index="[item.id]">
        <template slot="title">{{item.name}}</template>
        <el-submenu v-for="sec in item.sections" :key="sec.number"
          :index="[item.id, sec.id]">
          <template slot="title">模块{{sec.number}}</template>
          <el-menu-item v-for="les in sec.lessons"
            :key="les.number" :index="[item.id, sec.id, les.id]"
            @click="sendLesson(item.id, sec.id, les.id)">
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
      books: [{'id': 1, 'name': '八年级上', 'discipline': 'IT', 'upload': {'username': 'Alexhaoge'}, 'sections': [{'id': 1, 'number': 1, 'note': 'Test', 'lessons': [{'id': 1, 'number': 1, 'note': 'Test', 'resources': [{'id': 2, 'path': '1.mp4', 'md5': null, 'name': '1.mp4', 'bilibili': null, 'note': 'Test', 'vorS': 'VIDEO'}, {'id': 1, 'path': '1.pdf', 'md5': null, 'name': '1.pdf', 'bilibili': null, 'note': 'Test', 'vorS': 'SLIDE'}]}, {'id': 3, 'number': 2, 'note': 'Test', 'resources': []}, {'id': 2, 'number': 3, 'note': 'Test', 'resources': []}]}, {'id': 3, 'number': 2, 'note': 'Test', 'lessons': []}, {'id': 2, 'number': 3, 'note': 'Test', 'lessons': [{'id': 4, 'number': 1, 'note': 'Test', 'resources': []}, {'id': 5, 'number': 2, 'note': 'Test', 'resources': []}]}]}, {'id': 2, 'name': '八年级下', 'discipline': 'IT', 'upload': {'username': 'Alexhaoge'}, 'sections': [{'id': 4, 'number': 2, 'note': 'Test', 'lessons': []}, {'id': 5, 'number': 3, 'note': 'Test', 'lessons': [{'id': 6, 'number': 2, 'note': 'Test', 'resources': []}, {'id': 7, 'number': 3, 'note': 'Test', 'resources': []}]}]}],
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
    sendLesson (bookID, sectionID, lessonID) {
      this.$store.commit('sendLesson', {
        lessonID: lessonID || 0,
        bookID: bookID,
        sectionID: sectionID || 0
      })
      // console.log('sendlesson ' + (lessonID || 0) + ' ' + (sectionID || 0) + ' ' + bookID)
    },
    send (arr) {
      if (arr.length === 2) {
        this.sendLesson(arr[0], arr[1], 0)
      } else if (arr.length === 1) {
        this.sendLesson(arr[0], 0, 0)
      } else {
        this.sendLesson(arr[0], arr[1], arr[2])
      }
    }
  },
  computed: {
    updateCat () {
      return this.$store.state.updateCat
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
    },
    updateCat: function () {
      this.loadBooks()
      this.$store.commit('updateCategory', {updateCat: false})
    }
  }
}
</script>

<style>

</style>
