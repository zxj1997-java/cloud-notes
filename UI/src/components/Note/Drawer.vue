<template>
  <el-drawer v-model="props.showDrawer" :show-close="false" :size="400" :z-index="100000" direction="ltr" @close="emits('toggleDrawer')">
    <template #header="{ close, titleId, titleClass }">
      <el-row>
        <el-button icon="CaretLeft" type="primary" @click="toParent">上级目录</el-button>
        <el-button icon="Upload" type="primary" @click="dialogVisible = true">导入</el-button>
        <el-button :icon="iconShow" circle type="warning" @click="toggleTheme"/>
      </el-row>
    </template>
    <FileFolder v-if="array" :array="array" @openFile="openFile" @toChild="toChild"/>
    <el-dialog v-model="dialogVisible" title="文件导入">
      <el-upload action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15" class="upload-demo" drag multiple>
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
import {computed, ref} from 'vue';
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
    let data = response.rows;
    for (let row of data) {
      row.isDeleted = 0;
      row.isEdit = false;
    }
    array.value = data;
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