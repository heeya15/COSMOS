import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from '@/views/accounts/SignUp.vue'
import LogIn from '@/views/accounts/LogIn.vue'
import MainPage from '@/views/MainPage.vue'
import MyPage from '@/views/accounts/MyPage.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: MainPage
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/login',
    name: 'LogIn',
    component: LogIn
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
