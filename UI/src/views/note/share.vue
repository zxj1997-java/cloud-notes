<template>
  <el-row>
    <el-col :span="3" class="col" style="background-color: #7dae00">
      <MdCatalog :editorId="id" :scrollElement="scrollElement"/>
    </el-col>
    <el-col :span="18" class="col">
      <MdPreview :editorId="id" :modelValue="text"/>
    </el-col>
    <el-col :span="3" class="col" style="background-color: #01961a">
      <div>云笔记:程序员专属的笔记</div>
    </el-col>
  </el-row>
</template>

<script setup>
import {ref} from 'vue';
import {MdCatalog, MdPreview} from 'md-editor-v3';
// preview.css相比style.css少了编辑器那部分样式
import 'md-editor-v3/lib/preview.css';

const id = 'preview-only';
const text = ref('# Hello Editor');
const scrollElement = document.documentElement;
</script>

<style scoped>
.col {
  height: 100vh;
}
</style>