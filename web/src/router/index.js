import Vue from 'vue'
import Router from 'vue-router'
// 导入编写的组件
import AppIndex from '../components/home/AppIndex'
import Login from '../components/login'
import library from '../components/library/library'
import error from '../components/error'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/library/:discipline',
      name: 'library',
      component: library,
      children: [
      ]
    },
    {
      path: '*',
      component: error,
      name: 'error'
    }
  ]
})
