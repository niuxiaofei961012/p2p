<template>
  <el-main>
    <div style="width: auto;height: 570px">
      <div style="float: left;margin-right: 20px;height: 500px;width: 200px">
        <br><br><br><br><br><br><br><br>
        籍贯:{{userInfo.userAddress}}<br/><br/><br/>
        认证信息:<br/>
      </div>
      <div style="float: left;height: 100px;width: 1300px">
        用户邮箱:{{userInfo.userEmail}}<br/><br/>
        用户手机号:{{userInfo.userPhone}}<br/>
      </div>
      <div style="height: 470px;width: 1300px">
        <div>
          <div>
            {{oneInfoFromBid.borrowName}}<br/>
            借款金额:{{oneInfoFromBid.borrowMoney}} 元 &nbsp; 年化利率:{{oneInfoFromBid.yearRate}}<br/><br/>
            借款期限:{{oneInfoFromBid.returnMonthes}}月 &nbsp; 总可得利息:{{oneInfoFromBid.grossInterest}} 元<br/><br/>
            还款方式:
            <span v-if="oneInfoFromBid.status==0">等额本金</span>
            <span v-else-if="oneInfoFromBid.status==1">等额本息</span>
            <span v-else-if="oneInfoFromBid.status==2">按月到期</span>
            &nbsp;
            最小投标:{{minBidMoney}} 元<br/><br/>
            风控意见:{{oneInfoFromBid.borrowUse}}<br/><br/>
            剩余时间:{{oneInfoFromBid.publishTime}}<br/><br/>
            <br>
          </div>
          <div>
            还需金额:{{needMoney}} 元<br/><br/>
            可用余额:{{Account.accoubtAvbalance}} 元<br/><br/>
            <input style="text-align: center" v-model="bidMoney"></input><br><br/>
            <el-button @click="showInputPwd">马上投标</el-button>
          </div>
        </div>
      </div>
    </div>

    <el-dialog title="身份验证" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="请输入交易密码" :label-width="formLabelWidth">
          <el-input v-model="password" type="password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="bid">投 标</el-button>
      </div>
    </el-dialog>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const AccountURL = "http://localhost:10010/p2p/account_service/";
  const BorrowURL = "http://localhost:10010/p2p/loan_service/";
  const UserURL = "http://localhost:10010/p2p/user_service/";
  export default {
    name: "OneInfoFromBid",
    data() {
      return {
        oneInfoFromBid: {},
        Account: {},
        userInfo: {},
        dialogFormVisible:false,
        formLabelWidth: '120px',
        password:"",
        bidMoney:0,
        minBidMoney:0,
        needMoney:0,
        VerifyDTO:{}
      }
    },
    methods: {
      //密码弹框
      showInputPwd(){
        if(this.bidMoney>this.Account.accoubtAvbalance){
          alert("余额不足")
          return
        }
        if(this.bidMoney>this.oneInfoFromBid.borrowMoney-this.oneInfoFromBid.accessMoney){
          alert("金额超出需要金额")
        }
        if(this.bidMoney<this.minBidMoney){
          alert("最少投"+this.minBidMoney+"元")
          return
        }
        this.password=""
        this.dialogFormVisible=true;
      },
      //投标
      bid(){
        let AccountId = this.Account.id
        this.VerifyDTO.id=AccountId;
        this.VerifyDTO.tradePassword=this.password
        let self = this;
        axios({
          url: AccountURL + "p2p/account/verifyPassword",
          method: "post",
          data: this.VerifyDTO
        }).then(function (res) {
          if(res.data){
            alert("密码正确")
            self.dialogFormVisible=false;
          }else{
            alert("密码错误")
            self.dialogFormVisible=false;
          }
        })
      },
      //查询借款标信息
      getBidList(id) {
        let self = this;
        axios({
          url: BorrowURL + "loan/getLoanMarkById",
          method: "get",
          params: {borrowSignId: id}
        }).then(function (res) {
          self.oneInfoFromBid = res.data
          self.getUserInfoById(self.oneInfoFromBid.borrowUserId)
          self.minBidMoney=Math.floor(self.oneInfoFromBid.borrowMoney/self.oneInfoFromBid.returnMonthes);
          self.needMoney=self.oneInfoFromBid.borrowMoney-self.oneInfoFromBid.accessMoney
        })
      },
      //查询借款人信息
      getUserInfoById(userId) {
        let self = this;
        axios({
          url: UserURL + "p2p/getUserInfoById",
          method: "get",
          params: {id: userId}
        }).then(function (res) {
          self.userInfo = res.data

        })
      },
      //查询当前账户信息
      getAccountInfo(AccountId) {
        let self = this;
        axios({
          url: AccountURL + "p2p/account/selectByPrimaryKey",
          method: "get",
          params: {id: AccountId}
        }).then(function (res) {
          self.Account = res.data;
        })
      }
    },
    created() {
      let AccountId = window.localStorage.getItem("userId");
      let id = this.$route.params.id
      this.getBidList(id)
      this.getAccountInfo(AccountId)
    }
  }
</script>

<style scoped>

</style>
