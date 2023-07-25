<template>
  <el-dialog v-model="props.show" style="height: 78%" title="全局搜索" @close="closeDialog">
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header" style="display: flex;flex-flow: row">
        <span>全局搜索</span>
        <!--<el-checkbox v-model="type" :checked="type==1" label="标题" size="large"/>
                <el-checkbox v-model="type" :checked="type==2" label="内容" size="large"/>-->
      </div>
    </template>
    <div style="height: 200px;">
      <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
        <li v-for="i in count" :key="i" class="infinite-list-item">{{ i }}xxxxxx</li>
      </ul>
    </div>
    <div id="prew" style="max-height: 500px;overflow: auto;margin-top: 20px">
      <MdPreview v-model="content"/>
    </div>
  </el-dialog>
</template>

<script setup>
import {MdPreview} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {ref} from "vue";

const props = defineProps(['show']);
const emits = defineEmits(['closeDialog']);
const count = ref(0);
const content = ref('# 这是内容\nxxxxxxx\n\nxxxxxxx\n\nxxxxxxx\n\n\nxxxxxxx\n\nxxxxxxx\n\n\nxxxxxxx \n \n xxxxxxx\n \n\n\nxxxxxxx\n\n\nxxxxxxx\n\n\n\n\n\n\nxxxxxxx\n\n\n\nxxxxxxx\n\n\nxxxxxxx\n\n\n\n\nxxxxxxx');
const load = () => {
  count.value += 2
}

function closeDialog() {
  emits('closeDialog');
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