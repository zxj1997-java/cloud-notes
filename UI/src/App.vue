<template>
  <router-view />
</template>

<script setup>
import useSettingsStore from '@/store/modules/settings'
import { handleThemeStyle } from '@/utils/theme'
import {nextTick, onMounted} from "vue";

onMounted(() => {
  nextTick(() => {
    // 初始化主题样式
    handleThemeStyle(useSettingsStore().theme)
  })
})
</script>
<style>
.el-drawer__body {
  padding: 0 !important;
}

.el-popper {
  z-index: 100000 !important;
}

.custom-tip{
  z-index: 100000 !important;
}
</style>