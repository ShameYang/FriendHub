<script setup lang="ts">

import { useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import myAxios from "../plugins/myAxios";
import { showFailToast } from "vant";
import TeamCardList from "../components/TeamCardList.vue";

const router = useRouter();
const searchText = ref('');

// 搜索队伍
const onSearch = (val) => {
  listTeam(val);
};

// 跳转到创建队伍页
const toAddTeam = () => {
  router.push({
    path: "/team/add"
  })
}
const toUserTeamCreate = () => {
  router.push({
    path: "/team/create"
  })
}
const toUserTeamJoin = () => {
  router.push({
    path: "/team/join"
  })
}

const teamList = ref([]);

// 加载队伍信息
const listTeam = async (val = '', status = 0) => {
  const res = await myAxios.get("/team/list", {
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
    <span id="teamAdd">
      <van-button class="add-button" icon="plus" type="primary" round @click="toAddTeam"/>
    </span>
    <span id="teamCreate">
      <van-button type="primary" @click="toUserTeamCreate">查看创建的队伍</van-button>
    </span>
    <span id="teamJoin">
      <van-button type="primary" @click="toUserTeamJoin">查看加入的队伍</van-button>
    </span>
    <team-card-list :teamList="teamList"/>
    <van-empty v-if="teamList?.length < 1" description="数据为空"/>
  </div>
</template>

<style scoped>
#teamAdd {
  padding-left: 8px;
}
#teamCreate {
  padding-left: 5px;
}
#teamJoin {
  padding-left: 5px;
}
</style>