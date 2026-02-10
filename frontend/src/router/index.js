import { createRouter, createWebHistory } from 'vue-router'
import RegisterView from '../views/RegisterView.vue'

const routes = [
  {
    path: '/',
    redirect: '/register'
  },
  {
    path: '/register',
    component: RegisterView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
