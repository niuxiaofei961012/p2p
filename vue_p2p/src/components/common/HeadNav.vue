<template>
  <el-header style="padding: 0px;margin-top: -60px">
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
      >
      <el-menu-item @click="persone(1)">首页</el-menu-item>
      <el-menu-item @click="persone(2)">我要投资</el-menu-item>
      <el-menu-item @click="persone(3)">我要贷款</el-menu-item>
      <el-menu-item @click="persone(4)">个人中心</el-menu-item>
      <el-menu-item @click="persone(5)">新手引导</el-menu-item>
      <el-menu-item @click="persone(6)">关于我们</el-menu-item>
    </el-menu>
  </el-header>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://back.p2p.com/";
  export default {
    name: "ShowAll",
    data(){
      return {
        activeIndex2: '1'
      }
    },
    methods:{
      persone(key){
        let self=this;
        switch (key) {
          case 1: self.$router.push('/homePage');break;
          case 2: self.$router.push('/bidList');break;
          case 3: self.$router.push('/borrow');break;
          case 4: self.$router.push('/personalCenter');break;
          case 5:
          case 6:
        }

      },

    },
    created(){

    }
  }
</script>

<style scoped>

</style>
