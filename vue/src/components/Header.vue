<template>
  <div style="font-size: 12px ;line-height: 60px ;display: flex ;">

    <div style="flex:1;">

      <span :class="collapseBtnClass" style="cursor:pointer; font-size: 17px " @click="collapse"></span>

      <el-breadcrumb separator="/" style="display:inline-block ; margin-left: 18px">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-dropdown style="width:120px;cursor: pointer">

      <div style="display: inline-block;" >
        <img :src="user.personphoto" alt=""
        style="width: 30px; border-radius: 50%;position: relative;top: 10px;right: 5px">
        <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>

      <el-dropdown-menu slot="dropdown" style="width: 100px ; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0" >
          <router-link to="/person"  style="text-decoration: none;color:#606266">个人信息</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0" >
          <span style="text-decoration: none;color:#606266" @click="logout">退出</span>
        </el-dropdown-item>

      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String
  },
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },

  watch: {
    currentPathName(newVal, oldVal) {
      console.log(newVal)
    }
  },
  methods: {
    collapse() {
      this.$emit("asideCollapse")
    },logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("安全退出")
    }
  }
}

</script>

<style scoped>

</style>