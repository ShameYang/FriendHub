<script setup lang="ts">
import { onMounted, ref } from "vue";
import { getCurrentUser } from "../services/user.ts";
import myAxios from "../plugins/myAxios.ts";
import { useRouter } from "vue-router";

const router = useRouter();

const user = ref({});

onMounted(async () => {
  user.value = await getCurrentUser();
  if (user.value && user.value.tags) {
    tagList.value = [...JSON.parse(user.value.tags)];
  }
})

const tagList = ref([]);

const activeIndex = ref(0);

const tags = [
  {
    text: '性别',
    children: [
      {text: '男', id: '男'},
      {text: '女', id: '女'},
    ],
  },
  {
    text: '爱好',
    children: [
      {text: '编程', id: '编程'},
      {text: '运动', id: '运动'},
      {text: '听音乐', id: '听音乐'},
      {text: '游戏', id: '游戏'},
    ],
  },
  {
    text: '编程语言',
    children: [
      {text: 'java', id: 'java'},
      {text: 'python', id: 'python'},
      {text: 'c++', id: 'c++'},
      {text: 'go', id: 'go'},
    ],
  },
];

const close = (tag) => {
  tagList.value = tagList.value.filter(item => {
    return item !== tag;
  });
}

const saveTags = async () => {
  user.value.tags = JSON.stringify(tagList.value);
  console.log(user.value);
  const res = await myAxios.post('/user/update', user.value);
  if (res.code === 0 && res.data > 0) {
    showSuccessToast('保存成功');
    router.back();
  } else {
    showFailToast('保存失败');
  }
};

const onClickItem = (e) => {
  if (tags[activeIndex.value].text === '性别') {
    if (tagList.value.includes('男') && e.id != '男') {
      tagList.value = tagList.value.filter(item => {
        return item !== '男';
      });
    } else if (tagList.value.includes('女') && e.id != '女') {
      tagList.value = tagList.value.filter(item => {
        return item !== '女';
      });
    }
  }
}
</script>

<template>
  <van-divider content-position="left">我的标签</van-divider>
  <template v-if="user && user.id">
    <van-row gutter="16" style="padding: 0 16px">
      <van-col v-for="tag in tagList">
        <van-tag closeable size="large" type="primary" @close="close(tag)">
          {{ tag }}
        </van-tag>
      </van-col>
    </van-row>
  </template>
  <van-divider content-position="left">可选标签</van-divider>
  <van-tree-select
      v-model:active-id="tagList"
      v-model:main-active-index="activeIndex"
      @click-item="onClickItem"
      :items="tags"
      height="320px"
  />
  <div style="padding-top: 40px">
    <van-button type="primary" round block @click="saveTags">保存</van-button>
  </div>
</template>

<style scoped>

</style>