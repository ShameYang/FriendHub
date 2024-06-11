<script setup lang="ts">

import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios";
import {showFailToast} from "vant";
import TeamCardList from "../components/TeamCardList.vue";

const searchText = ref('');

// 搜索队伍
const onSearch = (val) => {
  listTeam(val);
};

const teamList = ref([]);

// 加载队伍信息
const listTeam = async (val = '', status = 0) => {
  const res = await myAxios.get("/team/list/my/join", {
    params: {
      searchText: val,
      pageNum: 1,
      status,
    },
  });
  if (res?.code === 0) {
    teamList.value = res.data;
  } else {
    showFailToast('加载队伍失败，请刷新重试');
  }
}

// 页面加载时只触发一次
onMounted(async () => {
  listTeam();
})

</script>

<template>
  <div id="teamPage">
    <van-search v-model="searchText" placeholder="搜索队伍" @search="onSearch"/>
    <team-card-list :teamList="teamList"/>
    <van-empty v-if="teamList?.length < 1" description="数据为空"/>
  </div>
</template>

<style scoped>

</style>