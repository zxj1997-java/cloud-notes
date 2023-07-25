<template>
  <MdPreview :editorId="id" :modelValue="text"/>
  <MdCatalog :editorId="id" :scrollElement="scrollElement"/>
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