import Vue from 'vue'
import Router from 'vue-router'
// 导入编写的组件
import AppIndex from '../components/home/AppIndex'
import Login from '../components/login'
import library from '../components/library/library'
import libraryhome from '../components/library/home'
import error from '../components/error'

Vue.use(Router)
const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: AppIndex
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/library',
      name: 'libraryhome',
      component: libraryhome
    },
    {
      path: '/library/:discipline',
      name: 'library',
      component: library
    },
    // {
    //   path: '/library/:discipline/:bookID/:sectionID/:lessonID',
    //   name: 'library2',
    //   component: library
    // },
    {
      path: '/error',
      component: error
    },
    {
      path: '*',
      component: error,
      name: 'error'
    }
  ]
})
