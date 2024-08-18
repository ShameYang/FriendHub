<script setup lang="ts">
import { ref } from 'vue';
import myAxios from "../plugins/myAxios";
import { useRouter } from "vue-router";

const router = useRouter();

const userAccount = ref('');
const password = ref('');
const checkPwd = ref('');

const onSubmit = async () => {
  const res = await myAxios.post('/user/register', {
    userAccount: userAccount.value,
    password: password.value,
    checkPwd: checkPwd.value
  })
  if (res.code === 0 && res.data) {
    showSuccessToast('注册成功');
    // 跳转到登录页面
    await router.push('/user/login');
  } else {
    showFailToast(res.description);
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
      <van-field
          v-model="checkPwd"
          type="password"
          name="确认密码"
          label="确认密码"
          placeholder="确认密码"
          autocomplete="off"
          :rules="[{ required: true, message: '请填写确认密码' }]"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<style scoped>

</style>