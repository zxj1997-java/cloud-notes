<template>
  <el-drawer v-model="props.showDrawer" :show-close="false" :size="400" :z-index="100000" direction="ltr" @close="emits('toggleDrawer')">
    <template #header="{ close, titleId, titleClass }">
      <el-row>
        <el-button icon="CaretLeft" circle @click="toParent" title="上一级" color="#1dc49d"></el-button>
        <el-button icon="UploadFilled" circle @click="dialogVisible = true" title="文档导入" color="#1da5c4"></el-button>
        <el-button :icon="iconShow" circle @click="toggleTheme" title="模式" color="#3a4138"/>
        <el-button icon="Calendar" circle title="最近" color="#00b391"/>
      </el-row>
      <el-space :size="10" wrap>
        <el-icon class="pointer" color="rgb(103,194,58)" title="新增文件夹" @click="addNoteFun(1)">
          <FolderOpened/>
        </el-icon>
        <el-icon class="pointer" color="rgb(103,194,58)" title="新增文件" @click="addNoteFun(0)">
          <DocumentAdd/>
        </el-icon>
      </el-space>
    </template>
    <FileFolder :array="array" @openFile="openFile" @toChild="toChild"/>
    <el-dialog v-model="dialogVisible" title="文件导入">
      <el-upload :http-request="handleUpload" class="upload-demo" drag multiple :before-upload="beforeUpload">
        <el-icon class="el-icon--upload">
          <upload-filled/>
        </el-icon>
        <div class="el-upload__text">
          拖拽文件 或者 <em>点击选择文件</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            文件大小不要超过20M
          </div>
        </template>
      </el-upload>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </el-drawer>
</template>
<script>
import {nextTick} from "vue";

export default {
  mounted() {
    nextTick(() => {
      document.getElementsByClassName("el-upload__input").webkitdirectory = true;
    })
  }
}
</script>
<script setup>
import {onMounted, ref} from 'vue';
import FileFolder from './FileFolder.vue';
import {addNote, childListNote, parentListNote, uploadFile} from "@/api/note/note";
import {saveNoteFile} from "@/api/note/notefile";
import {ElMessage} from "element-plus";

const props = defineProps(['showDrawer']);
const emits = defineEmits(['toggleTheme', 'toggleDrawer', 'openFile']);
const theme = ref('light');
const dialogVisible = ref(false);
const currNodeId = ref(null);
const iconShow = ref("Sunny");
const array = ref(null);

function addNoteFun(isDirectory) {
  let note = {
    filename: "未命名",
    isDirectory: isDirectory,
    parentId: currNodeId.value
  }
  addNote(note).then(response => {
    let newNote = response.data;
    newNote.isEdit = true;
    array.value.unshift(newNote);

    if (isDirectory == 0) {
      let noteFile = {
        id: newNote.id,
        title: newNote.filename
      }
      saveNoteFile(noteFile).then(response => {
      })
    }
  })
}


function searchList(params) {
  childListNote(params).then(response => {
    let data = response.rows;
    for (let row of data) {
      row.isEdit = false;
    }
    array.value = data;
  });
}

searchList({parentId: currNodeId.value});

//上级目录
function toParent() {
  parentListNote({id: currNodeId.value}).then(response => {
    currNodeId.value = response.rows[0].parentId;
    array.value = response.rows;
  });
}

//下级目录
function toChild(id) {
  currNodeId.value = id;
  searchList({parentId: id});
}

function openFile(id) {
  emits('openFile', id);
}

const toggleTheme = () => {
  theme.value = theme.value === 'light' ? 'dark' : 'light';
  iconShow.value = theme.value === 'light' ? 'Sunny' : 'Moon';
  emits('toggleTheme', theme.value);
};


function handleUpload(file, onProgress) {
  let formData = new FormData();
  formData.append('file', file.file);
  formData.append('parentId', currNodeId.value ? currNodeId.value : '');
  uploadFile(formData).then(response => {
    searchList({parentId: currNodeId.value});
    return response;
  });
}

function beforeUpload(file) {
  const isMarkdown = file.type === "text/markdown";
  if (!isMarkdown) {
    ElMessage({
      message: '只能上传markdown',
      type: 'error',
      customClass: "custom-tip"
    })
  }
  return isMarkdown;
}

onMounted(() => {
  let localTheme = localStorage.getItem('cloud-note-theme');
  if (localTheme) {
    iconShow.value = localTheme === 'light' ? 'Sunny' : 'Moon';
  }
})
</script>
<style scoped>

.drawer-content {
  height: 100%;
  padding: 20px;
}

:deep(.el-card__body) {
  padding: 4px 0px 8px 6px !important;
}
</style>