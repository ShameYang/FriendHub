<script setup lang="ts">
import { ref, watchEffect } from "vue";
import myAxios from '../plugins/myAxios'
import { showFailToast } from "vant";
import { UserType } from "../models/user";
import UserCardList from "../components/UserCardList.vue";

const isMatchMode = ref<boolean>(false);

const userList = ref([]);
const loading = ref(true);

/**
 * 加载数据
 */
const loadData = async () => {
  let userListData;
  loading.value = true;
  // 匹配模式
  if (isMatchMode.value) {
    const num = 10;
    userListData = await myAxios.get('/user/match', {
      params: {
        num,
      },
    })
        .then(function (response) {
          return response?.data;
        })
        .catch(function () {
          showFailToast('请求失败');
        })

  } else {
    // 普通模式
    userListData = await myAxios.get('/user/recommend', {
      params: {
        pageSize: 8,
        pageNum: 1,
      },
    })
        .then(function (response) {
          return response?.data?.records;
        })
        .catch(function () {
          showFailToast('请求失败');
        })
  }
  if (userListData) {
    userListData.forEach((user: UserType) => {
      if (user.tags) {
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
  loading.value = false;
}

watchEffect(() => {
  loadData();
})
</script>

<template>
  <van-cell center title="匹配模式">
    <template #right-icon>
      <van-switch v-model="isMatchMode" size="24"/>
    </template>
  </van-cell>
  <van-cell title="管理我的标签" is-link to="/user/tags"/>
  <user-card-list :user-list="userList" :loading="loading"/>
  <van-empty v-if="!userList || userList.length < 1" description="数据为空" />
</template>

<style scoped>

</style>