<template>
  <div class="floating-button-wrapper-left">
    <el-button class="floating-button-left" color="#e9e9eb" icon="Expand" size="mini" type="info" @click="showDrawer = true"></el-button>
  </div>
  <div class="floating-button-wrapper-right">
    <el-button v-show="showPreview==1 || showPreview==2" class="floating-button-right" color="#e9e9eb" icon="View" size="mini" @click="showPreview=showPreview==2?1:2"></el-button>
  </div>
  <Drawer :showDrawer="showDrawer" @openFile="openFile" @toggleDrawer="showDrawer = false" @toggleTheme="toggleTheme"/>
  <MdEditor v-if="showPreview==1" v-model="noteFile.content" :autoDetectCode="true" :preview="true" :theme="theme"
            :toolbars="toolbars" :toolbarsExclude="toolbarsExclude" class="markedit"
            editorId="editmark" @onBlur="onBlur"
            @onSave="onSave" @onUploadImg="onUploadImg">
    <template #defToolbars>
      <span style="margin-right:20px;font-size: 13px" v-text="noteFile.title"></span>
      <span>
        <span v-if="!loading"> <el-icon class="save-success" title="保存成功"></el-icon></span>
        <span v-if="loading"  v-loading="loading"></span>
      </span>
    </template>
  </MdEditor>
  <MdPreview v-else-if="showPreview==2" v-model="noteFile.content" :preview="true" :theme="theme" class="markedit" editorId="editpreview"/>
  <Welcome v-else-if="showPreview==3"/>
  <FullSearch :show="fullSearchDialog" :theme="theme" @closeDialog="fullSearchDialog=false"/>
</template>

<script setup>
import {onBeforeUnmount, onMounted, ref} from 'vue';
import {MdEditor, MdPreview} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import Drawer from '../../components/Note/Drawer.vue'
import Welcome from '../../components/Note/Welcome.vue'
import FullSearch from "../../components/Note/FullSearch.vue";
import {getNoteFile, saveNoteFile, uploadFile} from "@/api/note/notefile";
import {ElMessage, ElNotification} from "element-plus";

const baseApi = import.meta.env.VITE_APP_BASE_API;
const showDrawer = ref(false);
const showPreview = ref(3);
const fullSearchDialog = ref(false);
const theme = ref('light');
const noteFile = ref({});
const loading = ref(false);
const toolbarsExclude = ref(['pageFullscreen', 'github', 'htmlPreview']);
const toolbars = ref([0, 'bold', 'underline', 'italic', '-', 'title', 'strikeThrough', 'sub', 'sup', 'quote', 'unorderedList', 'orderedList', 'task', '-', 'codeRow', 'code', 'link', 'image', 'table', 'mermaid', 'katex', '-', 'revoke', 'next', 'save', '-', 1, '=', 'pageFullscreen', 'fullscreen', 'preview', 'htmlPreview', 'catalog', 'github'])

function toggleTheme(res) {
  theme.value = res;
}

//开打文件
function openFile(fileId) {
  getNoteFile(fileId).then(response => {
    noteFile.value = response;
    showPreview.value = 1;
    ElNotification({
      title: response.title,
      message: '文件打开成功',
      type: 'success',
      duration: 1000,
      customClass: "custom-tip"
    })
  })
}

function onBlur(event) {
  loading.value=true;
  noteFile.value.html = event.target.innerHTML;
  saveNoteFile(noteFile.value).then(response => {
    setTimeout(function(){
      loading.value=false;
    },500)
  })
}

/*保存数据*/
function onSave(value, html) {
  loading.value=true;
  html.then(obj => {
    noteFile.value.content = value;
    noteFile.value.html = obj;
    noteFile.value.updateTime = new Date();
    saveNoteFile(noteFile.value).then(response => {
      setTimeout(function(){
        loading.value=false;
      },500)
    })
  }).catch(error => {
    ElMessage({
      message: '保存失败',
      type: 'error',
      customClass: "custom-tip"
    })
  });
}

/*图片上传*/
function onUploadImg(files, callback) {
  let formData = new FormData();
  for (let i = 0; i < files.length; i++) {
    formData.append('files', files[i]);
  }
  uploadFile(formData).then(response => {
    let url = baseApi + "/notefile/image/" + response;
    callback([url])
  })
}

onMounted(() => {
  let localTheme = localStorage.getItem('vueuse-color-scheme');
  if (localTheme) {
    theme.value = localTheme;
  }
  window.addEventListener('keydown', handleKeyDown);
})

onBeforeUnmount(() => {
  window.removeEventListener('keydown', handleKeyDown);
});

function handleKeyDown(event) {
  if (event.ctrlKey && event.key === 'h') {
    fullSearchDialog.value = true;
    event.preventDefault();
  }
}
</script>
<style scoped>
#editmark {
  height: 100%;
}

#editpreview {
  height: 100%;
}

.save-success {
  width: 1em;
  height: 1em;
  content: url('@/assets/icons/note/success.svg');
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

:deep(.circular) {
  width: 20px !important;
}
</style>