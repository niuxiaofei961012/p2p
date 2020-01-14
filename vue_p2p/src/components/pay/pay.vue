<template>
  <el-main>
    <h3>支付</h3>
    <form action="http://www.p2p.com/p2p/pay_service/alipay/createOrder/" >
      <input placeholder="订单号" name="orderNo" type="text"></input>
      <input placeholder="价格" name="amount" type="text"></input>
      <input placeholder="订单名称" name="body" type="text"></input>
      <input type="submit" value="提交"></input>
    </form>
    <!--<el-form :inline="true" :model="payForm" class="demo-form-inline">-->
      <!--<el-form-item label="订单号">-->
        <!--<el-input v-model="payForm.orderNo" placeholder="订单号"></el-input>-->
      <!--</el-form-item>-->
      <!--<br>-->
      <!--<el-form-item label="价格">-->
        <!--<el-input v-model="payForm.amount" placeholder="价格"></el-input>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="名称">-->
        <!--<el-input v-model="payForm.body" placeholder="名称"></el-input>-->
      <!--</el-form-item>-->
      <!--<br>-->
      <!--<el-form-item>-->
        <!--<el-button type="primary" @click="pay">支付</el-button>-->
      <!--</el-form-item>-->
    <!--</el-form>-->

  </el-main>
</template>
<script>
    const axios = require("axios");
    const baseURL = "http://www.p2p.com/p2p/pay_service/";
    axios.defaults.withCredentials = true;
    export default {
        name: "pay",
      data() {
        return {
          payForm: {},
        }
      },
      methods: {
        pay() {
          let self = this;
          axios({
            url: baseURL+"alipay/createOrder/",
            method: "get",
            params: this.payForm
          }).then(function (res) {

          })
        },
      },
      created() {

      }
    }
</script>

<style scoped>

</style>
