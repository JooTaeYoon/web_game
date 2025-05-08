import GameVue from '../views/GameVue.vue';
import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
  {
    path: '/home',
    name: 'GameVue',
    component: GameVue,
  },
];

const router = createRouter({
  history: createWebHashHistory(), // #이 포함된 URL 사용
  routes,
});

export default router;
