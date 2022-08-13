<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sidewidth+'px'" style="background-color: rgb(238, 241, 246);">
      <Aside :isCollapse="isCollapse" :logotext="logotext"/>
    </el-aside>

    <el-container>
      <el-header style="  border-bottom:  1px solid #ccc ;  ">
        <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse"  />
      </el-header>

      <el-main>
        <router-view/>
        <!--表示当前页面的子路由会在<router-view/>里面展示-->
      </el-main>
    </el-container>
  </el-container>

</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";
export default {
  name: 'HomeView'
, data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sidewidth: 200,
      logotext: true,
    }
  } ,
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {//收缩
        this.sidewidth = 64
        this.collapseBtnclass = 'el-icon-s-unfold'
        this.logotext = false
      } else {//展开
        this.sidewidth = 200
        this.collapseBtnclass = 'el-icon-s-fold'
        this.logotext = true
      }
    }
  },components: {
    Aside,
    Header
  }
}
</script>

