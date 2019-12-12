<template>
  <el-main style="width: 500px;height: 800px;">
    <div style="width: 100%;height: 100px;">
      <h2 style="color: blue">信用借款</h2>
      <h3>信用额度{{remainCreditLimit}}</h3>
    </div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="借款金额">
        <el-input v-model="form.borrowMoney" >
          <template slot="append">元</template>
        </el-input>
      </el-form-item>

      <el-form-item label="年化利率" >
        <el-input v-model="form.yearRate">
          <template slot="append">%</template>
        </el-input>
      </el-form-item>

      <el-form-item label="还款方式">
        <el-radio-group v-model="form.paymentMethod" @change="computerMoney">
          <el-radio label="0" value="0">等额本息</el-radio>
          <el-radio label="1" value="1">等额本金</el-radio>
          <el-radio label="2" value="2">按月到期</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="贷款期限">
        <el-select v-model="form.returnMonthes" placeholder="请选择贷款期限" @change="computerMoney">
          <el-option label="请选择期限" value="1"></el-option>
          <el-option label="1" value="1"></el-option>
          <el-option label="2" value="2"></el-option>
          <el-option label="3" value="3"></el-option>
          <el-option label="4" value="4"></el-option>
          <el-option label="5" value="5"></el-option>
          <el-option label="6" value="6"></el-option>
          <el-option label="7" value="7"></el-option>
          <el-option label="8" value="8"></el-option>
          <el-option label="9" value="9"></el-option>
          <el-option label="10" value="10"></el-option>
          <el-option label="11" value="11"></el-option>
          <el-option label="12" value="12"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="招标天数">
        <el-input v-model="form.borrowDays">
          <template slot="append">天</template>
        </el-input>
      </el-form-item>

      <el-form-item label="借款名称">
        <el-input v-model="form.borrowName">
        </el-input>
      </el-form-item>

      <el-form-item label="借款描述">
        <el-input type="textarea" v-model="form.borrowUse"></el-input>
      </el-form-item>

      <el-form-item label="需还利息">
        <el-input :disabled="true" v-model="interest"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交申请</el-button>
        <el-button @click="computerMoney">利息计算</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const BorrowURL  = "http://localhost:10010/p2p/loan_service/";
  const baseURL  = "http://localhost:10010/p2p/account_service/";
    export default {
        name: "Borrow",
        data(){
          return {
            form:{},
            remainCreditLimit:0,
            interest:""
          }
        },
        methods:{
          //计算利息
          computerMoney(){
            let self = this;
            axios({
              url:BorrowURL+"loan/computerMoney",
              method:"post",
              data:this.form
            }).then(function (res) {
              if(res){
                self.interest=res.data;
              }
            })
          },
          //查询账户信息
          getAccountInfo(userId){
            let self = this;
            axios({
              url:baseURL+"p2p/account/selectByPrimaryKey",
              method:"get",
              params:{id:userId}
            }).then(function (res) {
              self.remainCreditLimit = res.data.remainCreditLimit;
            })
          },
          //提交
          onSubmit(){
            let self = this;
            axios({
              url:BorrowURL+"loan/addLoanMark",
              method:"post",
              data:this.form
            }).then(function (res) {
              if(res){
                self.$message("添加成功")
                self.$router.push('/home');
              }else{
                self.$message("添加失败")
              }
            })
          },
          //取消提交跳转home
        },
        created(){
          let userId = window.localStorage.getItem("userId");
          this.form.borrowUserId=userId;
          this.getAccountInfo(userId);
        }
    }
</script>

<style scoped>

</style>
