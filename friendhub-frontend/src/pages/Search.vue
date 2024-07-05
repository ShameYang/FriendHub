<script setup lang="ts">
import {ref} from 'vue';
import { useRouter } from "vue-router";

const searchText = ref('');

const originTagList = [
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

// 标签列表
let tagList = ref(originTagList);

// 搜索过滤
const onSearch = () => {
  tagList.value = originTagList.map(parentTag => {
    const tempChildren = [...parentTag.children];
    const tempParentTag = {...parentTag};
    tempParentTag.children = tempChildren.filter(item => item.text.includes(searchText.value));
    return tempParentTag;
  });
};
const onCancel = () => {
  searchText.value = '';
  tagList.value = originTagList;
};

// 已选中标签
const activeIds = ref([]);
const activeIndex = ref(0);

// 移除标签
const close = (tag) => {
  activeIds.value = activeIds.value.filter(item => {
    return item !== tag;
  })
};

const router = useRouter();

// 执行搜索
const doSearchResult = () => {
  router.push({
    path: "/user/list",
    query: {
      tags: activeIds.value
    }
  });
}
</script>

<template>
  <form action="/">
    <van-search
        v-model="searchText"
        show-action
        placeholder="请输入要搜索的标签"
        @search="onSearch"
        @cancel="onCancel"
    />
  </form>
  <van-divider content-position="left">已选标签</van-divider>
  <van-row gutter="16" style="padding: 0 16px">
    <van-col v-for="tag in activeIds">
      <van-tag closeable size="medium" type="primary" @close="close(tag)">
        {{ tag }}
      </van-tag>
    </van-col>
  </van-row>
  <van-divider content-position="left">选择标签</van-divider>
  <van-tree-select
      v-model:active-id="activeIds"
      v-model:main-active-index="activeIndex"
      :items="tagList"
  />
  <div style="padding: 30px">
    <van-button type="primary" block @click="doSearchResult">搜索用户</van-button>
  </div>
</template>

<style scoped>

</style>