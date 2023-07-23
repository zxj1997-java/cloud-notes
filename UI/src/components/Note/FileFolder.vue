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
              <div v-if="!item.isEdit" @click="toChild(item.id,item.isDirectory)">
                <el-text class="mx-1" size="small" style="font-size: 13px" truncated v-text="item.filename"></el-text>&nbsp;
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

                  <el-popover
                      :width="200" placement="right"
                      title="选择文件夹"
                      trigger="click">
                    <template #reference>
                      <el-icon class="pointer moveto" title="移动到其他分组" @click="getCurrentFile(item)"></el-icon>
                    </template>
                    <el-tree :check-on-click-node="true" :expand-on-click-node="false" :lazy="true" :load="loadNode" @node-click="nodeClick"/>
                  </el-popover>
                  <el-icon :color="item.isShare==1?'#3a4138':'#0b9f0b'" class="pointer" title="分享">
                    <Share/>
                  </el-icon>
                </el-space>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </template>
    <el-dialog
        v-model="dialogVisible"
        title="提示"
        width="20%">
      <span>确认选择 <span style="color:#00b391;" v-text="node.label"> </span>节点吗</span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancelMove">取消</el-button>
        <el-button type="primary" @click="moveTo"> 确认选择</el-button>
      </span>
      </template>
    </el-dialog>
  </el-scrollbar>
</template>
<script setup>
import {childListNote, delNote, updateNote} from "@/api/note/note";
import {ElMessage} from "element-plus";
import {ref} from "vue";
import dayjs from "dayjs";

const props = defineProps(['array']);
const emits = defineEmits(['toChild', 'openFile']);
const dialogVisible = ref(false);
const node = ref(null);
let currentFile = null;

function toChild(id, isDirectory) {
  if (isDirectory) {
    emits('toChild', id)
  } else {
    emits('openFile', id)
  }
}

function updateFileName(item) {
  let now = dayjs();
  let formattedDate = now.format('YYYY-MM-DD HH:mm:ss');
  item.updateTime = formattedDate;
  item.isEdit = !item.isEdit;
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

function nodeClick(e) {
  if (currentFile.id == e.id) {
    ElMessage({
      message: '不能选择自己',
      type: 'error',
    })
  } else {
    node.value = e;
    dialogVisible.value = true
  }
}


function getCurrentFile(note) {
  currentFile = note;
}

function cancelMove() {
  dialogVisible.value = false
}

function moveTo() {
  dialogVisible.value = false
  let oldParentId = currentFile.parentId;
  //调用ajax移动节点
  currentFile.parentId = node.value.id;
  updateNote(currentFile).then(response => {
    ElMessage({
      message: '移动成功',
      type: 'success',
    })
    emits('toChild', oldParentId);
  })
}

function loadNode(node, resolve) {
  if (node.data.length == 0) {
    let root = {
      id: null,
      label: "我的文件夹"
    }
    resolve([root]);
  } else {
    let note = {
      parentId: node.data.id,
      isDirectory: 1
    }

    childListNote(note).then(response => {
      let newRows = [];
      for (let row of response.rows) {
        if (currentFile.id != row.id) {
          row.label = row.filename;
          newRows.push(row)
        }
      }
      resolve(newRows)
    })
  }
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

.moveto {
  width: 1em;
  height: 1em;
  content: url('@/assets/icons/note/move.svg');
}
</style>