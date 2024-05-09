<script setup lang="ts">
import { useRoute } from "vue-router";
import {ref} from "vue";

const route = useRoute();

const { tags } = route.query;

const mockUser = {
  id: 1,
  userName: 'shameyang',
  userAccount: '123321',
  avatarUrl: 'https://avatars.githubusercontent.com/u/94451620?v=4',
  gender: '男',
  phone: '123123',
  email: '111@qq.com',
  createTime: new Date(),
  tags: ['java', '学习中', '大三', '待实习'],
  profile: '一个热爱编程的大学生，持续学习中...'
}

const userList = ref([mockUser]);
</script>

<template>
  <van-card
      v-for="user in userList"
      :title="user.userName"
      :desc="user.profile"
      :thumb="user.avatarUrl"
  >
    <template #tags>
      <van-tag plain type="primary" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
        {{tag}}
      </van-tag>
    </template>
    <template #footer>
      <van-button size="small">联系我</van-button>
    </template>
  </van-card>
</template>

<style scoped>

</style>