<script setup lang="ts">

import { useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import myAxios from "../plugins/myAxios";
import { showFailToast } from "vant";
import TeamCardList from "../components/TeamCardList.vue";

const router = useRouter();

// 跳转到创建队伍页
const toAddTeam = () => {
  router.push({
    path: "/team/add"
  })
}

const teamList = ref([]);

// 页面加载时只触发一次
onMounted(async () => {
  const res = await myAxios.get("/team/list");
  if (res?.code === 0) {
    teamList.value = res.data;
  } else {
    showFailToast('加载队伍失败，请刷新重试');
  }
})

</script>

<template>
  <div id="teamPage">
    <van-button type="primary" @click="toAddTeam">创建队伍</van-button>
    <team-card-list :teamList="teamList"/>
    <van-empty v-if="teamList?.length < 1" description="数据为空"/>
  </div>
</template>

<style scoped>

</style>