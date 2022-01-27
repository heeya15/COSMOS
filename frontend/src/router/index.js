import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from '@/views/accounts/SignUp.vue'
import LogIn from '@/views/accounts/LogIn.vue'
import MainPage from '@/views/MainPage.vue'
import MyPage from '@/views/accounts/MyPage.vue'
import MainBoard from '@/views/community/MainBoard.vue'
import BoardForm from '@/components/community/BoardForm.vue'
import BoardDetail from '@/components/community/BoardDetail.vue'
import BoardFormUpdate from '@/components/community/BoardFormUpdate.vue'
import BoardList from '@/components/community/BoardList.vue'
import StudyRoomCreateForm from '@/views/study/StudyRoomCreateForm.vue'
import ChangePasswordForm from '@/components/accounts/ChangePasswordForm.vue'
import StudyDetail from '@/views/study/StudyDetail.vue'


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
  {
    path: '/mypage/modifypassword',
    name: 'ChangePasswordForm',
    component: ChangePasswordForm
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
    path: '/boardformupdate',
    name: 'BoardFormUpdate',
    component: BoardFormUpdate
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
    path: '/study',
    name: 'StudyDetail',
    component: StudyDetail
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
