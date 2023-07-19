<template>
  <el-drawer direction="ltr" v-model="props.showDrawer" @close="emits('toggleDrawer')" :size="400" :show-close="false" :z-index="100000">
    <template #header="{ close, titleId, titleClass }">
      <el-row>
        <el-button type="primary" icon="CaretLeft">上级目录</el-button>
        <el-button type="primary" icon="Upload" @click="dialogVisible = true">导入</el-button>
        <el-button type="warning" :icon="iconShow" circle @click="toggleTheme"/>
      </el-row>
    </template>
    <FileFolder :array="props.array"/>
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

const props = defineProps(['array', 'showDrawer']);
const emits = defineEmits(['toggleTheme', 'toggleDrawer']);
const theme = ref('light');
const dialogVisible = ref(false);

const iconShow = computed(() => {
  return theme.value === 'light' ? 'Sunny' : 'Moon';
});

const toggleTheme = () => {
  theme.value = theme.value === 'light' ? 'dark' : 'light';
  emits('toggleTheme', theme.value);
};

</script>
<style scoped>

.drawer-content {
  height: 100%;
  padding: 20px;
}
</style>