<script setup lang="ts">
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getCurrentUser } from "../services/user.ts";
import myAxios from "../plugins/myAxios.ts";

const route = useRoute();
const router = useRouter();

const editUser = ref({
  editKey: route.query.editKey,
  editName: route.query.editName,
  currentValue: route.query.currentValue,
})

const columns = [
  { text: '男', value: 0 },
  { text: '女', value: 1 },
];

const fieldValue = ref('');
const showPicker = ref(false);
const selectValue = ref(null);

const onConfirm = ({ selectedOptions }) => {
  showPicker.value = false;
  fieldValue.value = selectedOptions[0].text;
  selectValue.value = selectedOptions[0].value;
};

const onSubmit = async () => {
  const currentUser = await getCurrentUser();

  if (!currentUser) {
    showFailToast('用户未登录');
    return;
  }

  const res = await myAxios.post('/user/update', {
    'id': currentUser.id,
    [editUser.value.editKey as string]: selectValue.value,
  })
  if (res.code === 0 && res.data > 0) {
    showSuccessToast('修改成功');
    router.back();
  } else {
    showFailToast('修改失败');
  }
}
</script>

<template>
  <van-form @submit="onSubmit">
    <van-field
      v-model="fieldValue"
      is-link
      readonly
      :label="editUser.editName"
      :placeholder="`当前性别：${editUser.currentValue}`"
      @click="showPicker = true"
    />
    <van-popup v-model:show="showPicker" round position="bottom">
      <van-picker
        :columns="columns"
        @cancel="showPicker = false"
        @confirm="onConfirm"
      />
    </van-popup>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<style scoped>

</style>