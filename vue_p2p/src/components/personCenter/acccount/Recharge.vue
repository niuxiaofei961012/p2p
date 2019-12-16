<template>
  <el-main>
    <div style="width: 300px;height: 500px">
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="充值金额">
          <el-input v-model="form.payMoney" type="number"></el-input>
        </el-form-item>
        <el-form-item label="充值方式">
          <el-radio label="0" v-model="form.chargeType">线下</el-radio>
          <el-radio label="1" v-model="form.chargeType">线上</el-radio>
        </el-form-item>
        <el-form-item label="付款单备注">
          <el-input type="textarea" v-model="form.payComment"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="showInputPwd">充值</el-button>
          <el-button type="primary" @click="back">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-dialog title="身份验证" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="请输入交易密码" :label-width="formLabelWidth">
          <el-input v-model="password" type="password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="recharge">充 值</el-button>
      </div>
    </el-dialog>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const AccountURL = "http://localhost:10010/p2p/account_service/";
  export default {
    name: "Recharge",
    data() {
      return {
        form: {},
        dialogFormVisible:false,
        VerifyDTO:{},
        formLabelWidth: '120px',
        password:""
      }
    },
    methods: {
      showInputPwd(){
        this.dialogFormVisible=true
      },
      recharge() {
        let self = this;
        this.VerifyDTO.id=this.form.createUserId
        this.VerifyDTO.tradePassword=this.password
        axios({
          url: AccountURL + "p2p/account/verifyPassword",
          method: "post",
          data: this.VerifyDTO
        }).then(function (res) {
          if(res.data){
            alert("密码正确")
            axios({
              url: AccountURL + "p2p/account/recharge",
              method: "post",
              data: self.form
            }).then(function (res) {
              if(res.data){
                alert("充值成功")
                self.$router.push("/personalCenter")
              }else{
                alert("充值失败")
              }
            })
            self.dialogFormVisible=false;
          }else{
            alert("密码错误")
            self.dialogFormVisible=false;
          }
        })
      },
      back() {
        this.$router.push("/personalCenter")
      }
    },
    created() {
      this.form.createUserId = this.$route.params.id
    }
  }
</script>

<style scoped>

</style>
