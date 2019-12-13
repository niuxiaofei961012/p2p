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
            借款金额:{{oneInfoFromBid.borrowMoney}} &nbsp; 年化利率:{{oneInfoFromBid.yearRate}}<br/><br/>
            借款期限:{{oneInfoFromBid.returnMonthes}}月 &nbsp; 总可得利息:{{oneInfoFromBid.grossInterest}}<br/><br/>
            还款方式:{{oneInfoFromBid.paymentMethod}} &nbsp;
            最小投标:{{oneInfoFromBid.borrowMoney/oneInfoFromBid.returnMonthes}}<br/><br/>
            风控意见:{{oneInfoFromBid.borrowUse}}<br/><br/>
            剩余时间:{{oneInfoFromBid.publishTime}}<br/><br/>
            <br>
          </div>
          <div>
            还需金额:<br/><br/>
            可用余额:{{Account.accoubtAvbalance}}<br/><br/>
            <input style="text-align: center"></input><br><br/>
            <button>马上投标</button>
          </div>
        </div>
      </div>
    </div>

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
        userInfo: {}
      }
    },
    methods: {
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
