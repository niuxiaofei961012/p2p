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
              <span style="color: chartreuse;font-size: 16px">{{menegerName}}</span>
              <router-link to="/" style="font-size: 20px; color: #fff;">登录</router-link>&nbsp;&nbsp;&nbsp;
              <router-link to="" style="font-size: 20px; color: #fff;">退出</router-link>&nbsp;&nbsp;&nbsp;
              <router-link to="" style="font-size: 20px; color: #fff;">个人设置</router-link>
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
            menegerName:""
          }
        },
      created(){
        this.menegerName = window.localStorage.getItem("menegerName");
      }
    }
</script>

<style scoped>

</style>
