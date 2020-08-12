import Vue from 'vue'
import Router from 'vue-router'
// 导入编写的组件
import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'
// import Home from '../components/Home'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
