<script setup lang="ts">
import { onMounted, ref } from "vue";
import myAxios from '../plugins/myAxios'
import { showFailToast } from "vant";
import { UserType } from "../models/user";
import UserCardList from "../components/UserCardList.vue";

const userList = ref([]);

onMounted(async () => {
  const userListData = await myAxios.get('/user/recommend', {
    params: {
      pageSize: 8,
      pageNum: 1,
    },
  })
      .then(function (response) {
        console.log('/user/recommend   succeed', response);
        showSuccessToast('请求成功');
        return response?.data?.records;
      })
      .catch(function (error) {
        console.error('/user/recommend', error);
        showFailToast('请求失败');
      })
  if (userListData) {
    userListData.forEach((user: UserType) => {
      if (user.tags) {
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
})
</script>

<template>
  <user-card-list :user-list="userList"/>
  <van-empty v-if="!userList || userList.length < 1" description="数据为空" />
</template>

<style scoped>

</style>