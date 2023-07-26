<template>
  <el-dialog v-model="props.show" style="height: 82%" title="全局搜索" @close="closeDialog">
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header" style="display: flex;flex-flow: row">
        <span>全局搜索</span>
      </div>
    </template>
    <div>
      <el-input v-model="keyword" placeholder="请输入内容" type="text" @change="search"/>
    </div>
    <div style="height: 180px;margin-top: 20px;overflow: auto;">
      <ul class="infinite-list">
        <li v-for="(item,index) in note" :key="index" class="infinite-list-item" @click="content=item.content">{{ item.title }}</li>
      </ul>
    </div>
    <div id="prew" style="max-height: 500px;overflow: auto;margin-top: 20px">
      <MdPreview v-if="content!=null" v-model="content" :theme="props.theme"/>
    </div>
  </el-dialog>
</template>

<script setup>
import {MdPreview} from 'md-editor-v3';
import {fullSearch} from "@/api/note/notefile.js";
import 'md-editor-v3/lib/style.css';
import {ref} from "vue";

const props = defineProps(['show', 'theme']);
const emits = defineEmits(['closeDialog']);
const keyword = ref('');
const note = ref([]);
const content = ref(null);

function search() {
  note.value = [];
  content.value = null;
  fullSearch(keyword.value).then((res) => {
    note.value = res;
  })
}

function closeDialog() {
  emits('closeDialog');
  keyword.value = '';
  note.value = [];
  content.value = null;
}
</script>
<style scoped>
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}

.infinite-list .infinite-list-item {
  height: 30px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
  line-height: 30px;
  padding-left: 20px;
}

.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
</style>