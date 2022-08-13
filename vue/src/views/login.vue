<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" alt=""/>
      </div>
      <!-- 表单区域 -->
      <el-form :model="user" :rules="loginFormRules" ref="ruleForm" label-width="0px" class="login_form">
        <el-form-item prop="username"><!-- 用户名 -->
          <el-input v-model="user.username" prefix-icon="el-icon-user-solid" size="medium"></el-input>
        </el-form-item>
        <el-form-item prop="password"><!-- 密码 -->
          <el-input v-model="user.password" prefix-icon="el-icon-lock" type="password" size="medium"
                    show-password></el-input>
        </el-form-item>
        <el-form-item class="btns"><!-- 按钮区域 -->
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="$router.push('/register')">注册</el-button>
          <!--          <el-button type="info" @click="resetLoginForm">重置</el-button>-->
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      //这里是登录表单的数据绑定对象
      user: {},
      //表单验证规则对象
      loginFormRules: {
        //验证用户名是否合法
        username: [
          {required: true, message: '请输入登录名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        //验证密码是否合法
        password: [
          {required: true, message: '请输入登密码', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ]
      }
    };
  }, methods: {
    login() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {//表单校验合法
          this.request.post("/login", this.user).then(res => {
            if (res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data)) //储存用户信息到浏览器
              this.$router.push("/")
              this.$message.success("登陆成功！")
            } else {
              this.$message.error(res.msg)
            }
          })
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<!-- 支持less语法格式，scoped代表样式只在本组件起作用 -->
<style lang="less" scoped>
.login_container {
  background-size:100%;
  background-attachment: fixed;
  background-image: url("../assets/img.png");
  height: 100%;
}

.login_box {

  width: 400px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px; //圆角效果
  position: absolute;
  left: 50%;
  top: 50%;
  background-color: rgba(0, 0, 0, 0.1);
  transform: translate(-50%, -50%);
  //less语法的嵌套
  .avatar_box {
    height: 130px;
    width: 130px;
    background-color: #fff;
    border: 1px solid #eee; //加个灰色边框
    border-radius: 50%; //div组件变成圆形
    padding: 10px; //图片和边框之间有10px间隙
    box-shadow: 0 0 10px #ddd; //添加一个阴影，向外扩散10像素
    //头像居中，向左移动box的50%，再向右移动自身的-50%，向上移动该组件高度的-50%，
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);

    img {
      height: 100%;
      width: 100%;
      border-radius: 50%; //图片变成圆形
      background-color: #eee;
    }
  }
}

.login_form {
  position: absolute;
  bottom: 0px; //靠下
  width: 100%;
  padding: 0 20px; //上下内边距0，左右内边距20px
  //border-box为元素指定的任何内边距和边框都将在已设定的宽度和高度内进行绘制。
  //实际宽度会是width减去border + padding的计算值
  //此处是为了不让input超出我们的登录父div
  box-sizing: border-box;
}

.btns {
  display: flex; //flex布局
  //justify-content控制项目在横轴的对齐方式
  //flex-start即左对齐，center 为居中，对应的flex-end为右对齐。
  justify-content: flex-end;
}

</style>