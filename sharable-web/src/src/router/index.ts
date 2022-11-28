import { createRouter, createWebHistory } from "vue-router";
import AnswerView from "../views/AnswerView.vue";
import QRGenerationView from "../views/QRGenerationView.vue";
import QuestionView from "../views/QuestionsView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  linkActiveClass: 'active',
  routes: [
    {
      path: "/answer/:token?",
      name: "answer",
      component: AnswerView,
    },
    {
      path: "/memories/:id?",
      name: "memories",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/MemoriesView.vue"),
    },
    {
      path: "/qr",
      name: "qr-generation",
      component: QRGenerationView
    },
    {
      path: "/question",
      name: "question",
      component: QuestionView
    },
  ],
});

export default router;
