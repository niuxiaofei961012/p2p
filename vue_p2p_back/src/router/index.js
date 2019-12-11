import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/home/Home'
import Left from '@/components/common/Left'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect:'/left',
      children: [
        {
          path: '/left',
          component: Left,
          name: 'Left',
        },
      ]
    },

  ]
})
