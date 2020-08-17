<template>
    <el-menu mode="horizontal">
      <el-menu-item style="pointer-events: none;">服务学习：庄浪教学资源平台</el-menu-item>
      <el-menu-item>
        <a rel="noopener noreferrer" href="https://github.com/Alexhaoge/" target="_blank">
          开发者：Alexhaoge
        </a>
      </el-menu-item>
      <el-menu-item>
        <a rel="noopener noreferrer" href="https://creativecommons.org/licenses/by-nc-sa/3.0/cn/" target="_blank">
          本网站遵循CC BY-NC-SA 3.0 CN协议
        </a>
      </el-menu-item>
    </el-menu>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<style>
.el-footer .el-menu {
  background-color: transparent;
}
.el-footer .el-menu .el-menu-item a{
  text-decoration: none;
}
</style>
