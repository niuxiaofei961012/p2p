import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import PersonalCenter from '@/components/personCenter/PersonalCenter'
import Common from '@/components/Common'
import Home from '@/components/home/Home'
import Account from '@/components/personCenter/acccount/Account'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    /*  meta: {
        keepAlive:false//是否缓存
      },*/
    },
    {
      path: '/personalCenter',
      name: 'PersonalCenter',
      component: PersonalCenter,
      children: [{
        path: '/account',
        component: Account,
        name: 'Account',
        hidden: true
      },
      ]
    },
    {
      path: '/common',
      name: 'Common',
      component: Common,
      /*meta: {
        keepAlive:true//是否缓存
      },*/
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      /*meta: {
        keepAlive:true//是否缓存
      },*/
    },

  ]
})
