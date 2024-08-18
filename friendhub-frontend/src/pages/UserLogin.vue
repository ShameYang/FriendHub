<script setup lang="ts">
import { ref } from 'vue';
import myAxios from "../plugins/myAxios";
import { useRoute } from "vue-router";

const route = useRoute();

const userAccount = ref('');
const password = ref('');

const onSubmit = async () => {
  const res = await myAxios.post('/user/login', {
    userAccount: userAccount.value,
    password: password.value
  })
  if (res.code === 0 && res.data) {
    showSuccessToast('登录成功');
    // 跳转到之前的页面
    const redirectUrl = route.query?.redirect as string ?? '/';
    window.location.href = redirectUrl;
  } else {
    showFailToast('登录失败');
  }
};
</script>

<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="userAccount"
          name="账号"
          label="账号"
          placeholder="账号"
          :rules="[{ required: true, message: '请填写账号' }]"
      />
      <van-field
          v-model="password"
          type="password"
          name="密码"
          label="密码"
          placeholder="密码"
          autocomplete="off"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-cell class="register" value="还没有账号？点击注册" to="register"/>
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<style scoped>
  .register {
    --van-cell-value-color: #1989fa;
  }
</style>