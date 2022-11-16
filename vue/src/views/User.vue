<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width:200px" suffix-icon="el-icon-search" placeholder="请输入名称" v-model="username"></el-input>
      <el-input style="width:200px" suffix-icon="el-icon-message" placeholder="请输入邮箱" v-model="email"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
      <div style="padding: 10px 0">
        <el-button class="el-icon-circle-plus-outline" type="primary" @click="addForm">新增</el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='考虑一下'
            icon="el-icon-info"
            icon-color="red"
            title="你确定删除吗？"
            @confirm="delBatch()"
        >
          <el-button class="el-icon-circle-close" type="danger" slot="reference" >批量删除</el-button>
        </el-popconfirm>
        <el-upload action="http://localhost:9090/user/import" style="display: inline-block"  :accept="'xlsx'" :show-file-list="false" :on-success="onsuccess" >
          <el-button type="primary" class="ml-5"  >导入<i  class="el-icon-download"></i></el-button>
        </el-upload>
        <el-button type="primary"  class="ml-5" @click="exp" >导出<i class="el-icon-upload2" /></el-button>
      </div>

    </div>
    <el-table :data="tableData" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="120" align="center">
      </el-table-column>
      <el-table-column prop="username" label="姓名" width="120" align="center">
      </el-table-column>
      <el-table-column prop="age" label="年龄" align="center">
      </el-table-column>
      <el-table-column prop="gender" label="性别" align="center">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" align="center">
      </el-table-column>
      <el-table-column prop="options" label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="success" class="el-icon-link" @click="editForm(scope.row)">编辑</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='考虑一下'
              icon="el-icon-info"
              icon-color="red"
              title="你确定删除吗？"
              @confirm="delById(scope.row.id)"
          >
            <el-button type="danger" class="el-icon-remove-outline" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>
    <div style="padding: 10px 0;text-align: center">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%">
      <el-form label-width="60px" size="small">

        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="年龄">
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>

        <div style="text-align: center ; padding-bottom: 10px ">
          <el-radio v-model="form.gender" label="男">男</el-radio>
          <el-radio v-model="form.gender" label="女">女</el-radio>
        </div>


        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>

    </el-dialog>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible1" width="25%">
      <el-form label-width="60px" size="small">

        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="年龄">
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>

        <div style="text-align: center ; padding-bottom: 10px ">
          <el-radio v-model="form.gender" label="男">男</el-radio>
          <el-radio v-model="form.gender" label="女">女</el-radio>
        </div>


        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>


      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="edit">确 定</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      email: "",
      dialogFormVisible: false,
      form: {},
      dialogFormVisible1: false,
      multipleSelection: [],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      //请求分页查询数据
      this.request.get("/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.userList
        this.total = res.data.total
      })
    },
    components: {
      Aside,
      Header
    },
    reset() {
      this.username = ""
      this.email = ""
      this.load()
    },
    save() {
      this.request.post("/add", this.form).then(res => {
        if (res.data) {
          this.$message.success("保存成功!")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败！")
        }
      })
    },
    onsuccess(){
      this.$message.success("导入成功！")
      this.load()
},
    delById(id) {
      this.request.delete("/del/" + id).then(res => {
        if (res.data) {
          this.$message.success("删除成功!")
          this.load()
        } else {
          this.$message.error("删除失败！")
        }
      })
    }, edit() {
      this.request.put("/update", this.form).then(res => {
        if (res.data) {
          this.$message.success("修改成功!")
          this.dialogFormVisible1 = false
          this.load()
        } else {
          this.$message.error("修改失败！")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val;
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id) //把对象数组变成纯id数组
      this.request.post("/delBatch", ids).then(res => {
        if (res.data) {
          this.$message.success("批量删除成功!")
          this.load()
        } else {
          this.$message.error("删除失败！")
        }
      })
    },
    editForm(row) {
      this.form = row
      this.dialogFormVisible1 = true
    },
    addForm() {
      this.dialogFormVisible = true
      this.form = {}
    },
    exp(){
      window.open("http://localhost:9090/user/export")
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    }, handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    }

  }

}
</script>

<style scoped>

</style>