<script setup lang="ts">
import { useRouter } from "vue-router";

const user = {
  id: 1,
  userName: 'shameyang',
  userAccount: '123321',
  avatarUrl: 'https://avatars.githubusercontent.com/u/94451620?v=4',
  gender: '男',
  phone: '123123',
  email: '111@qq.com',
  createTime: new Date(),
};

const router = useRouter();

const toEdit = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: '/user/edit',
    query: {
      editKey,
      editName,
      currentValue,
    }
  })
}
</script>

<template>
  <van-cell title="昵称" is-link to="/user/edit" :value="user.userName" @click="toEdit('userName', '昵称', user.userName)"></van-cell>
  <van-cell title="账号" :value="user.userAccount"></van-cell>
  <van-cell title="头像" is-link to="/user/edit" @click="toEdit('avatarUrl', '头像URL', user.avatarUrl)">
    <img :src="user.avatarUrl" style="height: 50px">
  </van-cell>
  <van-cell title="性别" is-link to="/user/edit" :value="user.gender" @click="toEdit('gender', '性别', user.gender)"></van-cell>
  <van-cell title="电话" is-link to="/user/edit" :value="user.phone" @click="toEdit('phone', '电话', user.phone)"></van-cell>
  <van-cell title="邮箱" is-link to="/user/edit" :value="user.email" @click="toEdit('email', '邮箱', user.email)"></van-cell>
  <van-cell title="注册时间" :value="user.createTime.toISOString()"></van-cell>
</template>

<style scoped>

</style>