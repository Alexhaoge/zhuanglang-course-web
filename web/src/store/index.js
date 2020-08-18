import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    teacher: {
      username: window.sessionStorage.getItem('teacher' || '[]') == null
        ? '' : JSON.parse(window.sessionStorage.getItem('teacher' || '[]')).username
    },
    lessonID: 0,
    sectionID: 0,
    bookID: 0
  },
  mutations: {
    login (state, teacher) {
      state.teacher = teacher
      window.sessionStorage.setItem('teacher', JSON.stringify(teacher))
    },
    sendLesson (state, payload) {
      state.bookID = payload.bookID
      state.sectionID = payload.sectionID
      state.lessonID = payload.lessonID
    }
  }
})
