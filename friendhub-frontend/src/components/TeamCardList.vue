<script setup lang="ts">
import { TeamType } from "../models/team";
import { teamStatusEnum } from "../constants/team.ts";
import teamAvatar from "../assets/teamAvatar.png"
import myAxios from "../plugins/myAxios.ts";

interface TeamCardListProps {
  teamList: TeamType[];
}

const props = withDefaults(defineProps<TeamCardListProps>(), {
  // @ts-ignore
  teamList: [] as TeamType[],
});

/**
 * 加入队伍
 */
const doJoinTeam = async (id: number) => {
  const res = await myAxios.post('/team/join', {
    id: id
  });
  if (res?.code === 0) {
    showSuccessToast('加入成功');
  } else {
    showFailToast('加入失败' + (res.description ? `，${res.description}` : ''));
  }
}

</script>

<template>
  <div
      id="teamCardList"
  >
  <van-card
      v-for="team in props.teamList"
      :title="team.name"
      :desc="team.description"
      :thumb="teamAvatar"
  >
    <template #tags>
      <van-tag plain type="primary" style="margin-right: 8px; margin-top: 8px">
        {{
          teamStatusEnum[team.status]
        }}
      </van-tag>
    </template>
    <template #bottom>
      <div>
        {{ `队伍人数: ${team.hasJoinNum}/${team.maxNum}` }}
      </div>
      <div>
        {{ '创建时间: ' + team.createTime }}
      </div>
      <div v-if="team.expireTime">
        {{ '过期时间: ' + team.expireTime }}
      </div>
    </template>
    <template #footer>
      <van-button size="small" @click="doJoinTeam(team.id)">加入队伍</van-button>
    </template>
  </van-card>
  </div>
</template>

<style scoped>
#teamCardList :deep(.van-image__img){
  width: 110px;
  height: 90px;
  margin-left: -15px;
}
</style>