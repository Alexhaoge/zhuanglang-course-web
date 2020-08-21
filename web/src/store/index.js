import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username: window.localStorage.getItem('username' || '[]') == null
      ? '' : JSON.parse(window.localStorage.getItem('username' || '[]')).username,
    lessonID: 0,
    sectionID: 0,
    bookID: 0,
    updateCat: false,
    subject: ''
  },
  mutations: {
    login (state, data) {
      state.username = data.data
      window.localStorage.setItem('username', JSON.stringify(data))
    },
    sendLesson (state, payload) {
      state.bookID = payload.bookID
      state.sectionID = payload.sectionID
      state.lessonID = payload.lessonID
    },
    updateCategory (state, payload) {
      state.updateCat = payload.updateCat
    },
    updateSubject (state, payload) {
      state.subject = payload.subject
    },
    logout (state) {
      // 注意不能用 null 清除，否则将无法判断 user 里具体的内容
      state.username = ''
      window.localStorage.removeItem('username')
      state.adminMenus = []
    }
  }
})
