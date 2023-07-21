<template>
  <el-scrollbar height="100%">
    <template v-for="item in props.array">
      <el-row v-if="item.isDeleted==0">
        <el-col :span="24">
          <el-card shadow="always">
            <div class="inlineblock pointer" style="width: 7%" @click="toChild(item.id,item.isDirectory)">
              <el-icon style="vertical-align: middle">
                <Folder v-if="item.isDirectory==1"/>
                <Document v-if="item.isDirectory==0"/>
              </el-icon>&nbsp;
            </div>
            <div class="inlineblock pointer" style="width: 57%">
              <el-input v-if="item.isEdit" v-model="item.filename" class="titleInput" placeholder="重输入文件名" size="small" @blur="updateFileName(item)">
                <template #suffix>
                  <el-icon class="el-input__icon" @click="item.isEdit=!item.isEdit">
                    <Check/>
                  </el-icon>
                </template>
              </el-input>
              <div @click="toChild(item.id,item.isDirectory)">
                <el-text v-show="!item.isEdit" class="mx-1" size="small" style="font-size: 13px" truncated v-text="item.filename"></el-text>&nbsp;
              </div>
            </div>
            <div class="inlineblock" style="width: 36%">
              <el-text class="mx-1" size="small" style="font-size: 11px" v-text="item.updateTime"></el-text>
              <div style="margin-top: 10px">
                <el-space :size="10" wrap>
                  <el-icon class="pointer" color="rgb(230,162,94)" title="重命名" @click="item.isEdit=!item.isEdit">
                    <Edit/>
                  </el-icon>
                  <el-popconfirm :visible="item.popconfirm" title="确认要删除吗?" trigger="click" @confirm="confirmEvent(item)">
                    <template #reference>
                      <el-icon class="pointer" color="#f56c6c" title="删除" @click="item.popconfirm=true">
                        <DeleteFilled/>
                      </el-icon>
                    </template>
                  </el-popconfirm>
                </el-space>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </template>
  </el-scrollbar>
</template>
<script setup>
import {delNote, updateNote} from "@/api/note/note";
import {ElMessage} from "element-plus";

const props = defineProps(['array']);
const emits = defineEmits(['toChild', 'openFile']);

function toChild(id, isDirectory) {
  if (isDirectory) {
    emits('toChild', id)
  } else {
    emits('openFile', id)
  }
}

function updateFileName(item) {
  item.isEdit = !item.isEdit
  updateNote(item).then(response => {
    ElMessage({
      message: '修改成功',
      type: 'success',
    })
  });
}

function confirmEvent(e) {
  deleteFile(e)
}

function deleteFile(item) {
  delNote(item.id).then(response => {
    ElMessage({
      message: '删除成功',
      type: 'success',
    })
    item.isDeleted = 1;
  });
}
</script>
<style scoped>
.titleInput {
  width: 70%;
}

.inlineblock {
  display: inline-block;
}

.el-text.is-truncated {
  max-width: 60% !important;
}

.pointer:hover {
  cursor: pointer;
}

:deep(.el-card__body) {
  display: flex;
  align-items: center;
}
</style>