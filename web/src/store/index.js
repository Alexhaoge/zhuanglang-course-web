import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    teacher: {
      username: window.sessionStorage.getItem('teacher' || '[]') == null
        ? '' : JSON.parse(window.sessionStorage.getItem('teacher' || '[]')).username
    }
  },
  mutations: {
    login (state, teacher) {
      state.teacher = teacher
      window.sessionStorage.setItem('teacher', JSON.stringify(teacher))
    }
  }
})
