<template>
  <el-scrollbar height="100%">
    <el-row v-for="item in props.array">
      <el-col :span="24">
        <el-card shadow="always">
          <div class="inlineblock" style="width: 10%">
            <el-icon style="vertical-align: middle">
              <Folder v-if="item.isFolder"/>
              <Document v-if="!item.isFolder"/>
            </el-icon>&nbsp;
          </div>
          <div class="inlineblock" style="width: 70%">
            <el-input class="titleInput" v-show="item.isEdit" v-model="item.title" size="small" @blur="item.isEdit=!item.isEdit"
                      placeholder="重输入文件名">
              <template #suffix>
                <el-icon class="el-input__icon" @click="item.isEdit=!item.isEdit">
                  <Check/>
                </el-icon>
              </template>
            </el-input>
            <span v-show="!item.isEdit">
                  <el-text class="mx-1" v-text="item.title" truncated style="width: 70%"></el-text>&nbsp;
                  <el-text class="mx-1" size="small" v-text="item.date"></el-text>
                </span>
          </div>
          <div class="inlineblock" style="width: 20%">
            <el-icon v-if="item.isFolder" title="新增" color="rgb(103,194,58)">
              <CirclePlusFilled/>
            </el-icon>
            <el-icon title="重命名" @click="item.isEdit=!item.isEdit" color="rgb(230,162,94)">
              <Edit/>
            </el-icon>
            <el-icon title="删除" color="#f56c6c">
              <DeleteFilled/>
            </el-icon>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-scrollbar>
</template>
<script setup>
const props = defineProps(['array']);

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
</style>