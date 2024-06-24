<script setup lang="ts">

import { useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import myAxios from "../plugins/myAxios";
import { showFailToast } from "vant";
import TeamCardList from "../components/TeamCardList.vue";

const router = useRouter();
const searchText = ref('');
const active = ref('public')

/**
 * 切换查询状态
 * @param name
 */
const onTabChange = (name: string) => {
  // 查公开
  if (name === 'public') {
    listTeam(searchText.value, 0);
  }
  // 查加密
  if (name === 'secret') {
    listTeam(searchText.value, 2);
  }
  // 已加入的队伍
  if (name === 'join') {
    teamJoin(searchText.value);
  }
  // 我创建的队伍
  if (name === 'create') {
    teamCreate(searchText.value);
  }
}

// 搜索队伍
const onSearch = (val: string) => {
  if (active.value === 'public') {
    listTeam(val, 0);
  }
  if (active.value === 'secret') {
    listTeam(val, 2);
  }
  if (active.value === 'join') {
    teamJoin(val);
  }
  if (active.value === 'create') {
    teamCreate(val);
  }
};
const onClickButton = () => {
  onSearch(searchText.value);
}

// 跳转到创建队伍页
const toAddTeam = () => {
  router.push({
    path: "/team/add"
  })
}

const teamList = ref([]);

// 加载公开或加密队伍
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

// 加载已加入队伍
const teamJoin = async (val = '') => {
  const res = await myAxios.get("/team/list/my/join", {
    params: {
      searchText: val,
      pageNum: 1,
    }
  });
  if (res?.code === 0) {
    teamList.value = res.data;
  } else {
    showFailToast('加载队伍失败，请刷新重试');
  }
}

// 加载我创建的队伍
const teamCreate = async (val = '') => {
  const res = await myAxios.get("/team/list/my/create", {
    params: {
      searchText: val,
      pageNum: 1,
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
  teamJoin();
  teamCreate();
})

</script>

<template>
  <div id="teamPage">
    <van-search v-model="searchText" show-action placeholder="搜索队伍" @search="onSearch">
      <template #action>
        <div @click="onClickButton">搜索</div>
      </template>
    </van-search>
    <van-button class="add-button" icon="plus" type="primary" round @click="toAddTeam"/>
    <van-tabs v-model:active="active" @change="onTabChange">
      <van-tab title="公开队伍" name="public"/>
      <van-tab title="加密队伍" name="secret"/>
      <van-tab title="已加入" name="join"/>
      <van-tab title="我的创建" name="create"/>
    </van-tabs>
    <div style="margin-bottom: 8px" />
    <team-card-list :teamList="teamList"/>
    <van-empty v-if="teamList?.length < 1" description="数据为空"/>
  </div>
</template>

<style scoped>

</style>