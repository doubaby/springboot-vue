<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100% ; overflow-x: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
           @select="handeSelect"
  >
    <div style="height: 60px ;line-height: 60px ; text-align: center">
      <img src="../assets/logo.png" style="width: 20px;position:relative;top:5px;">
      <b style="color:white;margin-left: 10px" v-show="logotext">后台管理系统</b>
    </div>
    <el-submenu index="1">
    <template slot="title">
      <i class="el-icon-s-custom"></i>
      <span slot="title">用户管理</span>
    </template>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logotext: Boolean
  },
  methods: {
    handeSelect() {
      this.$route.fullPath.split("/")
    }
  }
}
</script>

<style scoped>

</style>