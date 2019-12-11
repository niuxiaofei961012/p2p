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
            <span>用户管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">平台用户管理</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="1-2">员工管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>审核项目</span>
          </template>
          <el-menu-item-group>
            <el-menu-item @click="audit(1)">发标前审核</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item @click="audit(2)">满标审核</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item @click="audit(3)">线下充值审核</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>平台管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/">账户流水</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="3-2">系统设置</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="3-3">企业资讯</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="3-4">友情链接</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="3-5">广告设置</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-col >
    <el-col :span="9">


      <div v-if="auditIndex==1">
        <pre-bid-audit></pre-bid-audit>
      </div>
      <div v-if="auditIndex==2">
        <FullBidAudit></FullBidAudit>
      </div>
      <div v-if="auditIndex==3">
        <DownLineRechargeAudit></DownLineRechargeAudit>
      </div>


    </el-col>
  </el-row>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://back.p2p.com/";
  import PreBidAudit from '@/components/audit/PreBidAudit'
  import FullBidAudit from '@/components/audit/FullBidAudit'
  import DownLineRechargeAudit from '@/components/audit/DownLineRechargeAudit'
    export default {
        name: "Left",
        data(){
          return {
            auditIndex:0,
          }
        },
      components:{PreBidAudit,DownLineRechargeAudit,FullBidAudit},

        methods:{
          audit(id){
            this.auditIndex=id;
          }
        },
      created(){

      }
    }
</script>

<style scoped>
</style>
