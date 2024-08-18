<script setup lang="ts">
import { useRoute } from "vue-router";
import { onMounted, ref } from "vue";
import myAxios from '../plugins/myAxios'
import { showFailToast } from "vant";
import qs from 'qs';
import UserCardList from "../components/UserCardList.vue";

const route = useRoute();

const { tags } = route.query;

const userList = ref([]);
const loading = ref(true);

onMounted(async () => {
  const userListData = await myAxios.get('/user/search/tags', {
    params: {
      tagNameList: tags
    },
    paramsSerializer: params => {
      return qs.stringify(params, {indices: false})
    }

  })
      .then(function (response) {
        return response?.data
      })
      .catch(function () {
        showFailToast('请求失败');
      })
      .finally(() => {
        loading.value = false;
      });
  if (userListData) {
    userListData.forEach(user => {
      if (user.tags) {
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
})
</script>

<template>
  <van-row justify="center">
    <van-loading v-if="loading" size="48px" text-size="24px" color="#0094ff"
                 style="padding-top: 100px">加载中...</van-loading>
  </van-row>
  <user-card-list :user-list="userList" :loading="loading"/>
  <van-empty v-if="!loading && (!userList || userList.length < 1)" description="搜索结果为空" />
</template>

<style scoped>

</style>