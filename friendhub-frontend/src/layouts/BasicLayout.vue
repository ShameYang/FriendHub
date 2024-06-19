<script setup lang="ts">
import {useRouter} from "vue-router";
import { ref } from "vue";
import routes from "../config/route";

const router = useRouter();

const DEFAULT_TITLE= 'FriendHub';
const title = ref(DEFAULT_TITLE);

/**
 * 根据路由切换标题
 */
router.beforeEach((to) => {
  const toPath = to.path;
  const route = routes.find((route) => {
    return toPath == route.path;
  })
  title.value = route?.title ?? DEFAULT_TITLE;
})

const onClickLeft = () => history.back();
const onClickRight = () => {
  router.push('/search');
};
</script>

<template>
  <van-nav-bar
      :title="title"
      left-text=""
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
  >
    <template #right>
      <van-icon name="search" size="18"/>
    </template>
  </van-nav-bar>
  <div id="content">
    <router-view/>
  </div>
  <van-tabbar route>
    <van-tabbar-item to="/" icon="home-o"></van-tabbar-item>
    <van-tabbar-item to="/team" icon="friends-o"></van-tabbar-item>
    <van-tabbar-item to="/user" icon="contact-o"></van-tabbar-item>
  </van-tabbar>
</template>

<style scoped>
  #content {
    padding-bottom: 45px;
  }
</style>