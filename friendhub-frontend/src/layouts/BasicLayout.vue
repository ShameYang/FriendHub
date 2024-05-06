<script setup lang="ts">
import { ref } from 'vue';
import Index from "../pages/Index.vue";
import Team from "../pages/Team.vue";
import User from "../pages/User.vue";

const onClickLeft = () => history.back();

const active = ref("index");
const onChange = (index) => showToast(`${index}`);
</script>

<template>
  <van-nav-bar title="标题" left-text="" left-arrow @click-left="onClickLeft">
    <template #right>
      <van-icon name="search" size="18"/>
    </template>
  </van-nav-bar>
  <div id="content">
    <template v-if="active === 'index'"><Index /></template>
    <template v-if="active === 'team'"><Team /></template>
    <template v-if="active === 'user'"><User /></template>
  </div>
  <van-tabbar v-model="active" @change="onChange">
    <van-tabbar-item icon="home-o" name="index"></van-tabbar-item>
    <van-tabbar-item icon="search" name="team"></van-tabbar-item>
    <van-tabbar-item icon="contact-o" name="user"></van-tabbar-item>
  </van-tabbar>
</template>

<style scoped>

</style>