import { createRouter, createWebHistory } from "vue-router";
import AnswerView from "../views/AnswerView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  linkActiveClass: 'active',
  routes: [
    {
      path: "/",
      name: "answer",
      component: AnswerView,
    },
    {
      path: "/memories",
      name: "memories",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/MemoriesView.vue"),
    },
  ],
});

export default router;
