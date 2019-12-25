<template>
  <el-main>
    <h3>请输入用户名密码登录</h3>
    <el-form :inline="true" :model="loginForm" class="demo-form-inline">
      <el-form-item label="请输入账号">
        <el-input v-model="loginForm.userUsername" placeholder="用户名"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="请输入密码">
        <el-input v-model="loginForm.userPassword" placeholder="密码" type="password"></el-input>
      </el-form-item>
      <br>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
        <el-button type="primary" @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const baseURL = "http://www.p2p.com/p2p/user_service/";
  axios.defaults.withCredentials = true;
  export default {
    name: "Login",
    data() {
      return {
        loginForm: {},
        User: {}
      }
    },
    methods: {
      login() {
        if (this.loginForm.userUsername == null) {
          this.$message("请输入用户名")
          return;
        }
        if (this.loginForm.userPassword == null) {
          this.$message("请输入密码")
          return;
        }
        let self = this;
        axios({
          url: baseURL + "p2p/loginJWT",
          method: "post",
          data: this.loginForm
        }).then(function (res) {
          console.log(res)
          console.log(res.data)
            self.$router.push('/home');
         /* if (res.data.ok) {
            self.User = res.data.ok
            let user = JSON.parse(self.User);
            window.localStorage.setItem("userId", user.id);
            window.localStorage.setItem("userName", user.userUsername);
            self.$message("登录成功")
            self.$router.push("/home/");
          } else if (res.data.userName) {
            self.$message(res.data.userName)
          } else {
            self.$message(res.data.userPassword)
          }*/
        })

      },
      register() {
        this.$router.push("/register/");
      }
    },
    created() {

    }
  }
</script>

<style scoped>

</style>
