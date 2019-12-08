import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import PersonalCenter from '@/components/personCenter/PersonalCenter'
import Home from '@/components/home/Home'
import Account from '@/components/personCenter/acccount/Account'

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
      children: [
        {
          path: '/personalCenter',
          component: PersonalCenter,
          name: 'PersonalCenter',
          children: [
            {
              path: '/account',
              name: 'Account',
              component: Account,
            },
          ]
        },
      ]
    },

  ]
})
