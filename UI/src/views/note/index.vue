<template>
  <div class="floating-button-wrapper-left">
    <el-button class="floating-button-left" size="mini" @click="showDrawer = true" icon="Expand" type="info" color="#e9e9eb"></el-button>
  </div>
  <div class="floating-button-wrapper-right">
    <el-button class="floating-button-right" size="mini" @click="showPreview=!showPreview" icon="View" color="#e9e9eb"></el-button>
  </div>
  <Drawer :showDrawer="showDrawer" @toggleTheme="toggleTheme" @toggleDrawer="showDrawer = false" @openFile="openFile"/>
  <MdEditor editorId="editmark" class="markedit" v-model="text" :preview="true" v-show="showPreview" @onSave="onSave" :theme="theme"
            @onUploadImg="onUploadImg"/>
  <MdPreview editorId="editpreview" class="markedit" v-model="text" :preview="true" v-show="!showPreview" :theme="theme"/>
</template>

<script setup>
import {
  ref
} from 'vue';
import {
  MdEditor, MdPreview
} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import Drawer from '../../components/Note/Drawer.vue'

const showDrawer = ref(false);
const showPreview = ref(false);
const text = ref('# Hello Editor');
const theme = ref('light');


//开打文件
function openFile(id) {
}

function toggleTheme(res) {
  theme.value = res;
}

/*保存数据*/
function onSave(value, html) {
  console.log(value)
  console.log(html)
}

/*图片上传*/
function onUploadImg(files, callback) {
  callback(["https://img2.woyaogexing.com/2023/07/18/e853805e9abd3fc22a4e3dfec0c62ee3.jpg"]);
}

</script>
<style scoped>
#editmark {
  height: 100%;
}

#editpreview {
  height: 100%;
}

.floating-button-wrapper-right {
  position: fixed;
  bottom: 35px;
  right: 20px;
  z-index: 99999;
}

.floating-button-right {
  border-radius: 50%;
  height: 40px;
  width: 40px;
}

.floating-button-wrapper-left {
  position: fixed;
  bottom: 35px;
  left: 20px;
  z-index: 99999;
}

.floating-button-left {
  border-radius: 50%;
  height: 40px;
  width: 40px;
}
</style>