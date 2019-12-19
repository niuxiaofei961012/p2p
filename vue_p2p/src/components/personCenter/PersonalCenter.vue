<template>
  <el-row class="tac">
    <el-col :span="4">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        style="height:350px"
        router>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>借款项目</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/">投标项目</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="1-2">收款明细</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="1-3">借款项目</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="1-4">还款明细</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>我的账户</span>
          </template>
          <el-menu-item-group>
            <el-menu-item @click="toAccount(1)">账户信息</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item @click="toAccount(2)">修改个人信息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>资产详情</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="3-1">账户流水</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="3-2">充值明细</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="3-1">提现申请</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-col >
    <el-col :span="20">

      <div v-if="accountIndex==1">
        <Account></Account>
      </div>
      <div v-if="accountIndex==2">
        <UpdateUser></UpdateUser>
      </div>

    </el-col>
  </el-row>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://back.p2p.com/";
  import Account from "@/components/personCenter/acccount/Account";
  import UpdateUser from "@/components/personCenter/acccount/UpdateUser";
    export default {
        name: "PersonalCenter",
        components:{Account,UpdateUser},
        data(){
          return {
            accountIndex:0,
          }
        },
        methods:{
          toAccount(id){
            this.accountIndex=id;
          }
        },
      created(){

      }
    }
</script>

<style scoped>

</style>
