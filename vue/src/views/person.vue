<template>
  <el-card style="width: 500px">

    <el-form label-width="60px" size="small">

      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="密码">
        <el-input v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="save">确定</el-button>
      </el-form-item>

    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.request.get("/username/" + this.user.username).then(res => {
      if (res.code === '200') {
        this.form = res.data
      }
    })

  },
  methods: {
    save() {
      this.request.post("/adddto", this.form).then(res => {
        if (res.data) {
          this.$message.success("保存成功!")
        } else {
          this.$message.error("保存失败！")
        }
      })
    }
  }
}
</script>

<style scoped>

</style>