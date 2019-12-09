<template>
  <div style="width: 800px;height: 500px">
    <el-main>
      用户名:{{userName}}<br>
      <el-button type="primary" @click="">账户充值</el-button>
      <el-button type="primary" @click="">账户提现</el-button>
      <hr>
      账户总额:{{Account.accoubtAvbalance}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      可用余额:{{Account.accoubtAvbalance}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      冻结金额:{{Account.accoubt_frobalance}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <br>
      待收利息:{{Account.unreceiveInterest}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      待收本金:{{Account.unreceive_principal}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      待收本息:{{Account.unreceiveMoney}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <hr>
      <div style="width: 600px;height: 400px;margin-top: 50px">
        <div style="float: left;margin-right: 40px;width: 160px;height: 150px">
          实名认证 <br>
          未认证 <router-link to="1">马上认证</router-link><br>
          实名认证之后才能在平台投资
        </div>
        <div style="float: left;margin-right: 40px;width: 160px;height: 150px">
          手机认证 <br>
          未认证 <router-link to="1">立即绑定</router-link><br>
          可以收到系统操作信息, <br> 并增加使用安全性
        </div>
        <div style="float: left;margin-right: 40px;width: 160px;height: 150px">
          邮箱认证 <br>
          未认证 <router-link to="1">立即绑定</router-link><br>
          您可以设置邮箱来接收重要信息
        </div>
        <div style="float: left;width: 160px;height: 150px">
          VIP会员 <br>
          普通会员 <router-link to="1">查看</router-link><br>
        </div>
      </div>
    </el-main>
  </div>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://localhost:10010/p2p/account_service/";
    export default {
        name: "Account",
        data(){
          return {
            Account:{},
            userName:""
          }
        },
      methods:{
        getList(userId){
          let self = this;
          axios({
            url:baseURL+"p2p/account/selectByPrimaryKey",
            method:"get",
            params:{id:userId}
          }).then(function (res) {
            self.Account = res.data;
          })
        }
      },
      created(){
        let userId = window.localStorage.getItem("userId");
        this.userName = window.localStorage.getItem("userName");
        this.getList(userId);
      }
    }
</script>

<style scoped>

</style>
