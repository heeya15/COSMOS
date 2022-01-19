import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from '@/views/accounts/SignUp.vue'
import LogIn from '@/views/accounts/LogIn.vue'
import MainPage from '@/views/MainPage.vue'

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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
