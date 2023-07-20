<template>
  <el-drawer direction="ltr" v-model="props.showDrawer" @close="emits('toggleDrawer')" :size="400" :show-close="false" :z-index="100000">
    <template #header="{ close, titleId, titleClass }">
      <el-row>
        <el-button type="primary" icon="CaretLeft" @click="toParent">上级目录</el-button>
        <el-button type="primary" icon="Upload" @click="dialogVisible = true">导入</el-button>
        <el-button type="warning" :icon="iconShow" circle @click="toggleTheme"/>
      </el-row>
    </template>
    <FileFolder :array="array" @toChild="toChild" @openFile="openFile"/>
    <el-dialog v-model="dialogVisible" title="文件导入">
      <el-upload class="upload-demo" drag action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15" multiple>
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
<script setup>
import {ref, computed} from 'vue';
import FileFolder from './FileFolder.vue';
import {childListNote, parentListNote} from "@/api/note/note";

const props = defineProps(['showDrawer']);
const emits = defineEmits(['toggleTheme', 'toggleDrawer', 'openFile']);
const theme = ref('light');
const dialogVisible = ref(false);
const currNodeId = ref(null);

const array = ref(null);

function searchList(params) {
  childListNote(params).then(response => {
    array.value = response.rows;
  });
}

searchList({parentId: currNodeId.value});

const iconShow = computed(() => {
  return theme.value === 'light' ? 'Sunny' : 'Moon';
});

const toggleTheme = () => {
  theme.value = theme.value === 'light' ? 'dark' : 'light';
  emits('toggleTheme', theme.value);
};

//上级目录
function toParent() {
  parentListNote({id: currNodeId.value}).then(response => {
    array.value = response.rows;
    currNodeId.value = response.rows[0].parentId;
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

</script>
<style scoped>

.drawer-content {
  height: 100%;
  padding: 20px;
}

>>> .el-card__body {
  padding: 4px 0px 8px 6px !important;
}
</style>