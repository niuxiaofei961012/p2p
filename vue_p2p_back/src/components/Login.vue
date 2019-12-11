<template>
  <el-main>
    <h3>p2p后台登录</h3>
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
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://localhost:10010/p2p/user_service/";
  axios.defaults.withCredentials = true;
  export default {
    name: "Login",
    data(){
      return {
        loginForm:{},
        Admin:{}
      }
    },
    methods:{
      login(){
        this.$router.push("/home/");
        if(this.loginForm.userUsername==null){
          this.$message("请输入用户名")
          return;
        }
        if(this.loginForm.userPassword==null){
          this.$message("请输入密码")
          return;
        }
        let self = this;
        axios({
          url:baseURL+"p2p/menegerLogin",
          method:"post",
          data:this.loginForm
        }).then(function (res) {
          if(res.data.ok){
            self.Admin = res.data.ok
            let admin = JSON.parse(self.Admin);
            window.localStorage.setItem("menegerName",admin.userUsername);
            self.$message("登录成功")
            self.$router.push("/home/");
          }else if(res.data.menegerName){
            self.$message(res.data.menegerName)
          }else{
            self.$message(res.data.menegerPassword)
          }
        })

      },
    },
    created(){

    }
  }
</script>

<style scoped>

</style>
