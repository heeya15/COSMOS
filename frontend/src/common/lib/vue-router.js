import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/home'
import ConferencesDetail from '@/views/conferences/conference-detail'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '/conferences/:conferenceId',
    name: 'conference-detail',
    component: ConferencesDetail,
  }
]
const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router
