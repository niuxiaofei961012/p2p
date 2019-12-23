<template>
  <el-main>
    <h3>请填写个人信息</h3>
    <el-form :inline="true" :model="registerForm" class="demo-form-inline">
      <el-form-item label="请输入用户名">
        <el-input v-model="registerForm.userUsername" placeholder="用户名"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="请输入密码">
        <el-input v-model="registerForm.userPassword" placeholder="密码" type="password"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="请输入手机号">
        <el-input v-model="registerForm.userPhone" placeholder="手机号" ></el-input>
      </el-form-item>
      <br>
      <el-form-item label="请输入推荐人">
        <el-input v-model="registerForm.userPresenter" placeholder="推荐人" ></el-input>
      </el-form-item>
      <br>
      <el-form-item label="请输入邮箱">
        <el-input v-model="registerForm.userEmail" placeholder="邮箱"></el-input>
      </el-form-item>
      <br>
      <el-form-item label="请输入住址">
        <el-input v-model="registerForm.userAddress" placeholder="住址" ></el-input>
      </el-form-item>
      <br>
      <el-form-item>
        <el-button type="primary" @click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://www.p2p.com/p2p/user_service/";
  axios.defaults.withCredentials = true;
  export default {
    name: "Login",
    data(){
      return {
        registerForm:{},
      }
    },
    methods:{
      register(){
        if(this.registerForm.userUsername==null){
          this.$message("请输入用户名")
          return;
        }
        if(this.registerForm.userPassword==null){
          this.$message("请输入密码")
          return;
        }
        if(this.registerForm.userPhone==null){
          this.$message("请输入手机")
          return;
        }
        let self = this;
        axios({
          url:baseURL+"p2p/register",
          method:"post",
          data:this.registerForm
        }).then(function (res) {
            if(res.data){
              self.$router.push("/");
            }
            console.log(res.data)
        })

      }
    },
    created(){

    }
  }
</script>

<style scoped>

</style>
