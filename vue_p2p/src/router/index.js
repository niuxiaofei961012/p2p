import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import PersonalCenter from '@/components/personCenter/PersonalCenter'
import Home from '@/components/home/Home'
import HomePage from '@/components/home/HomePage'
import Register from '@/components/Register'
import Borrow from '@/components/borrow/Borrow';

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect:'/personalCenter',
      children: [
        {
          path: '/personalCenter',
          component: PersonalCenter,
          name: 'PersonalCenter',
        },
        {
          path: '/homePage',
          component: HomePage,
          name: 'HomePage',
        },
        {
          path: '/borrow',
          component: Borrow,
          name: 'Borrow',
        },
      ]
    },

  ],
  mode:'history'
})
