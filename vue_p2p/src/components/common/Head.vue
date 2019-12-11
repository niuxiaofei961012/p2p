<template>
  <el-header style="padding: 0px">
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          class="el-menu-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="margin-top: -60px;">
          <el-submenu index="1" align="right" >
            <template slot="title">
              <span style="font-size: 16px">欢迎</span>
              <span style="color: chartreuse;font-size: 16px">{{userName}}</span>
              <span style="font-size: 16px">登录</span>&nbsp;&nbsp;&nbsp;
              <router-link to="/homePage" style="font-size: 20px; color: #fff;">首页</router-link>&nbsp;&nbsp;&nbsp;
              <router-link to="/" style="font-size: 20px; color: #fff;">登录</router-link>&nbsp;&nbsp;&nbsp;
              <router-link to="" style="font-size: 20px; color: #fff;">帮助</router-link>
            </template>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
    export default {
        name: "Head",
        data(){
          return {
            userName:""
          }
        },
      created(){
        this.userName = window.localStorage.getItem("userName");
      }
    }
</script>

<style scoped>

</style>
