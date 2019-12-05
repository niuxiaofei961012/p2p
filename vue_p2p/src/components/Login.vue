<template>
  <el-main>
    <h3>请输入用户名密码登录</h3>
    <el-form :inline="true" :model="loginForm" class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input v-model="loginForm.userUsername" placeholder="用户名"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="密码">
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
  const baseURL  = "http://back.p2p.com/";
  export default {
    name: "Login",
    data(){
      return {
        loginForm:{}
      }
    },
    methods:{
      login(){
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
          url:baseURL+"p2p/login",
          method:"post",
          data:this.loginForm
        }).then(function (res) {
          if(res){
            alert(res)
          }
          /*if(res.data.mes){
            window.localStorage.setItem("user",res.data.user.userId);
            self.$router.push("list")
          }else if(res.data.nameMes){
            self.$message(res.data.nameMes)
          }else{
            self.$message(res.data.passMes)
          }*/
        })
      }
    },
    created(){

    }
  }
</script>

<style scoped>

</style>
