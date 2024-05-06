<script setup lang="ts">

const onClickLeft = () => history.back();

</script>

<template>
  <van-nav-bar title="标题" left-text="" left-arrow @click-left="onClickLeft">
    <template #right>
      <van-icon name="search" size="18"/>
    </template>
  </van-nav-bar>
  <div id="content">
    <router-view />
  </div>
  <van-tabbar route>
    <van-tabbar-item to="/" icon="home-o"></van-tabbar-item>
    <van-tabbar-item to="/team" icon="search"></van-tabbar-item>
    <van-tabbar-item to="/user" icon="contact-o"></van-tabbar-item>
  </van-tabbar>
</template>

<style scoped>

</style>