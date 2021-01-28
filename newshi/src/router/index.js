import Vue from 'vue';
import VueRouter from 'vue-router';
import Article from "../views/Article.vue";
import Main from "../views/Main.vue";
import MyPage from "../views/MyPage.vue";
import MyInfo from "../components/mypage/MyInfo";
import Channel from "../views/Channel.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/article/:newsNo",
    name: "Article",
    component: Article,
    props: true,
  },
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    children: [
      {
        path: "/",
        name: "MyInfo",
        component: MyInfo
      }
    ]
  },
  {
    path: "/channel/:id",
    name: "Channel",
    component: Channel,
  }
];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;
