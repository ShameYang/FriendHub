<script setup lang="ts">
import { useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import { getCurrentUser } from "../services/user.ts";
import myAxios from "../plugins/myAxios.ts";

const user = ref();

const router = useRouter();

onMounted(async () => {
  user.value = await getCurrentUser();
  const gender = user.value.gender
  if (gender === 0) {
    user.value.gender = '男'
  }
  if (gender === 1) {
    user.value.gender = '女'
  }
})

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

const toEditGender = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: '/user/edit/gender',
    query: {
      editKey,
      editName,
      currentValue,
    }
  })
}

// 退出登录
const doLogout = async () => {
  const res = await myAxios.post('/user/logout', {
  });
  if (res?.code === 0) {
    await router.push('/');
  } else {
    showFailToast('退出失败' + (res.description ? `，${res.description}` : ''));
  }
}
</script>

<template>
  <template v-if="user">
    <van-cell title="昵称" is-link to="/user/edit" :value="user.userName" @click="toEdit('userName', '昵称', user.userName)"></van-cell>
    <van-cell title="账号" :value="user.userAccount"></van-cell>
    <van-cell title="头像" is-link to="/user/edit" @click="toEdit('avatarUrl', '头像URL', user.avatarUrl)">
      <img :src="user.avatarUrl" style="height: 50px">
    </van-cell>
    <van-cell title="性别" is-link to="/user/edit" :value="user.gender" @click="toEditGender('gender', '性别', user.gender)"></van-cell>
    <van-cell title="电话" is-link to="/user/edit" :value="user.phone" @click="toEdit('phone', '电话', user.phone)"></van-cell>
    <van-cell title="邮箱" is-link to="/user/edit" :value="user.email" @click="toEdit('email', '邮箱', user.email)"></van-cell>
    <van-cell title="注册时间" :value="user.createTime"></van-cell>
  </template>
  <van-row justify="end">
    <van-button class="logout" size="normal" type="danger" round block plain @click="doLogout">退出登录</van-button>
  </van-row>
</template>

<style scoped>
  .logout {
    margin-top: 20px;

  }
</style>