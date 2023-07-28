<template>
  <el-row>
    <el-col :span="3" class="col left">
      <MdCatalog :editorId="id" :scrollElement="scrollElement"/>
    </el-col>
    <el-col :span="18" class="col">
      <MdPreview :editorId="id" :modelValue="text"/>
    </el-col>
    <el-col :span="3" class="col right">
      <h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">云笔记 </h1>
      <h6 align="center" style="margin: 30px 0 30px;">基于若依v3.8.6</h6>
      <h4 align="center">基于SpringBoot+Vue前后端分离的Java云笔记</h4>
      <p align="center">
        <a href="https://gitee.com/y_project/RuoYi-Vue/stargazers"><img src="https://gitee.com/y_project/RuoYi-Vue/badge/star.svg?theme=dark"></a>
        <a href="https://gitee.com/y_project/RuoYi-Vue"><img src="https://img.shields.io/badge/RuoYi-v3.8.6-brightgreen.svg"></a>
        <a href="https://gitee.com/y_project/RuoYi-Vue/blob/master/LICENSE"><img src="https://img.shields.io/github/license/mashape/apistatus.svg"></a>
      </p>
      <p align="center">
        平台简介
      </p>
      决心打造一款属于程序员的云笔记,对有道云的恶心吃相坚决抵制,免费提供给个人和企业使用
    </el-col>
  </el-row>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {MdCatalog, MdPreview} from 'md-editor-v3';
import 'md-editor-v3/lib/preview.css';
import {getShareNote} from "@/api/note/note";

const id = 'preview-only';
const text = ref('');
const scrollElement = document.documentElement;

onMounted(() => {
  // 解析查询字符串，并获取 id 参数的值
  const params = new URLSearchParams(window.location.search);
  getShareNote(params.get('id')).then(response => {
    text.value = response;
  })
})
</script>

<style scoped>
.col {
  height: 100vh;
  overflow: auto
}

.left, .right {
  height: 100vh;
  padding: 0 10px;
  background-color: #159957;
  background-image: linear-gradient(120deg, #155799, #159957);
}
</style>