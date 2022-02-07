import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from '@/views/accounts/SignUp.vue'
import LogIn from '@/views/accounts/LogIn.vue'
import MainPage from '@/views/MainPage.vue'
import MyPage from '@/views/accounts/MyPage.vue'
import MainBoard from '@/views/community/MainBoard.vue'
import BoardForm from '@/components/community/BoardForm.vue'
import BoardDetail from '@/components/community/BoardDetail.vue'
import BoardList from '@/components/community/BoardList.vue'
import StudyRoomCreateForm from '@/views/study/StudyRoomCreateForm.vue'
import StudyDetail from '@/views/study/StudyDetail.vue'
import AdminPage from '@/views/admin/Admin.vue'

import Openvidu from '@/views/Openvidu.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: MainPage
  },
  {
    path: '/openvidu',
    name: 'Openvidu',
    component: Openvidu
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
  {
    path: '/mainboard',
    name: 'MainBoard',
    component: MainBoard
  },
  {
    path: '/boardform',
    name: 'BoardForm',
    component: BoardForm
  },
  {
    path: '/boarddetail',
    name: 'BoardDetail',
    component: BoardDetail
  },
  {
    path: '/boardlist',
    name: 'BoardList',
    component: BoardList
  },
  {
    path: '/studyroom',
    name: 'StudyRoomCreateForm',
    component: StudyRoomCreateForm
  },
  {
    path: '/study/:studyNo',
    name: 'StudyDetail',
    component: StudyDetail
  },
  {
    path: '/admin',
    name: 'AdminPage',
    component: AdminPage,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
