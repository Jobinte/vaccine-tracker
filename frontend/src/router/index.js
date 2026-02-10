import { createRouter, createWebHistory } from "vue-router";

import RegisterView from "../views/RegisterView.vue";
import LoginView from "../views/LoginView.vue";
import DashboardView from "../views/DashboardView.vue";

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/register", component: RegisterView },
  { path: "/login", component: LoginView },
  { path: "/dashboard", component: DashboardView, meta: { requiresAuth: true } },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 🔐 ROUTE GUARD
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");

  if (to.meta.requiresAuth && !token) {
    next("/login");
  } else {
    next();
  }
});

export default router;
