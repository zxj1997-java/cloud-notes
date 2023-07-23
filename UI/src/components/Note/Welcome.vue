<template>
  <div class="welcome">
    <div style="padding: 20px 0;width: 100%;text-align: center;color: antiquewhite;"><h1>决心打造一款属于程序员的云笔记,对有道云的恶心吃相坚决抵制</h1></div>

    <table data-line="0" style="margin: 0 auto;">
      <thead>
      <tr>
        <th>键位</th>
        <th>功能</th>
        <th>说明</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>TAB</td>
        <td>空格</td>
        <td>通过tabWidth属性预设 TAB 键位新增空格长度，默认 2，支持多行</td>
      </tr>
      <tr>
        <td>SHIFT + TAB</td>
        <td>取消空格</td>
        <td>同上，一次取消两个空格，支持多行</td>
      </tr>
      <tr>
        <td>CTRL + C</td>
        <td>复制</td>
        <td>选中时复制选中内容，未选中时复制当前行内容</td>
      </tr>
      <tr>
        <td>CTRL + X</td>
        <td>剪切</td>
        <td>选中时剪切选中内容，未选中时剪切当前行</td>
      </tr>
      <tr>
        <td>CTRL + D</td>
        <td>删除</td>
        <td>选中时删除选中内容，未选中时删除当前行</td>
      </tr>
      <tr>
        <td>CTRL + S</td>
        <td>保存</td>
        <td>触发编辑器的onSave回调</td>
      </tr>
      <tr>
        <td>CTRL + B</td>
        <td>加粗</td>
        <td><strong>加粗</strong></td>
      </tr>
      <tr>
        <td>CTRL + U</td>
        <td>下划线</td>
        <td><u>下划线</u></td>
      </tr>
      <tr>
        <td>CTRL + I</td>
        <td>斜体</td>
        <td><em>斜体</em></td>
      </tr>
      <tr>
        <td>CTRL + 1-6</td>
        <td>1-6 级标题</td>
        <td># 标题</td>
      </tr>
      <tr>
        <td>CTRL + ↑</td>
        <td>上角标</td>
        <td><sup>上角标</sup></td>
      </tr>
      <tr>
        <td>CTRL + ↓</td>
        <td>下角标</td>
        <td><sub>下角标</sub></td>
      </tr>
      <tr>
        <td>CTRL + O</td>
        <td>有序列表</td>
        <td>1. 有序列表</td>
      </tr>
      <tr>
        <td>CTRL + L</td>
        <td>链接</td>
        <td><a href="https://github.com/imzbf/md-editor-v3">链接</a></td>
      </tr>
      <tr>
        <td>CTRL + Z</td>
        <td>撤回</td>
        <td>触发编辑器内内容撤回，与系统无关</td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + S</td>
        <td>删除线</td>
        <td>~删除线~</td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + U</td>
        <td>无序列表</td>
        <td>- 无序列表</td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + C</td>
        <td>块级代码</td>
        <td>多行代码块</td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + I</td>
        <td>图片链接</td>
        <td><img alt="图片" src="https://imzbf.github.io/md-editor-v3/imgs/preview-light.png" style="width: 300px"></td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + Z</td>
        <td>前进一步</td>
        <td>触发编辑器内内容前进，与系统无关</td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + F</td>
        <td>美化内容</td>
        <td></td>
      </tr>
      <tr>
        <td>CTRL + ALT + C</td>
        <td>行内代码 行内代码块</td>
        <td></td>
      </tr>
      <tr>
        <td>CTRL + SHIFT + ALT + T</td>
        <td>表格</td>
        <td></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script setup>

</script>
<style scoped>
.welcome {
  width: 100%;
  height: 100%;
  background-color: #159957;
  background-image: linear-gradient(120deg, #155799, #159957);
}
</style>