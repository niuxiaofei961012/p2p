<template>
  <div style="width: 800px;height: 500px">
    <el-main>
      用户名:{{userName}}<br>
      <el-button type="primary" @click="recharge">账户充值</el-button>
      <el-button type="primary" @click="">账户提现</el-button>
      <hr>
      账户总额:{{Account.accoubtAvbalance}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      可用余额:{{Account.accoubtAvbalance}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      冻结金额:{{Account.accoubtFrobalance}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <br>
      待收利息:{{Account.unreceiveInterest}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      待收本金:{{Account.unreceivePrincipal}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      待收本息:{{Account.unreceiveMoney}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <hr>
      <div style="width: 900px;height: 400px;margin-top: 50px">
        <div style="float: left;margin-right: 20px;width: 160px;height: 150px">
          实名认证 <br>
          <el-button type="info" round @click="dialogFormVisible1 = true" v-if="!Account.realNameFlag">马上认证</el-button>
          <el-button type="info" round @click="dialogFormVisible1 = true" v-if="Account.realNameFlag" disabled>已认证</el-button><br/>
          实名认证之后才能在平台投资
        </div>
        <div style="float: left;margin-right: 20px;width: 160px;height: 150px">
          邮箱认证 <br>
          <el-button type="danger" round @click="dialogFormVisible2 = true" v-if="!Account.emailFlag">马上认证</el-button>
          <el-button type="danger" round @click="dialogFormVisible2 = true" v-if="Account.emailFlag" disabled>已认证</el-button><br/>
          您可以设置邮箱来接收重要信息
        </div>
        <div style="float: left;margin-right: 20px;width: 160px;height: 150px">
          手机认证 <br>
           <el-button type="success" round @click="dialogFormVisible3 = true" v-if="!Account.phoneFlag">马上认证</el-button>
           <el-button type="success" round @click="dialogFormVisible3 = true" v-if="Account.phoneFlag" disabled>已认证</el-button><br/>
          可以收到系统操作信息<br> 并增加使用安全性
        </div>
        <div style="float: left;margin-right: 20px;width: 150px;height: 150px">
          VIP会员 <br>
          <el-button type="warning" round @click="dialogFormVisible3 = true" v-if="!Account.vipFlag">马上认证</el-button>
          <el-button type="warning" round @click="dialogFormVisible3 = true" v-if="Account.vipFlag" disabled>已认证</el-button><br/>
        </div>
      </div>
    </el-main>

    <el-dialog title="实名认证" :visible.sync="dialogFormVisible1">
      <el-form :model="RealNameForm">
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input v-model="RealNameForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="RealNameForm.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible1 = false">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="邮箱认证" :visible.sync="dialogFormVisible2">
      <el-form :model="mailForm">
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input v-model="mailForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="mailForm.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible2 = false">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="手机认证" :visible.sync="dialogFormVisible3">
      <el-form :model="phoneForm">
        <el-form-item label="请输入手机号" :label-width="formLabelWidth">
          <el-input v-model="phoneForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入验证码" :label-width="formLabelWidth">
          <el-input v-model="phoneForm.verifyCode" autocomplete="off"></el-input>
          <span v-show="show" @click="getVerifyCode">获取验证码</span>
          <span v-show="!show" class="count">{{count}} s</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible3 = false">取 消</el-button>
        <el-button type="primary" @click="verifyPhoneCode">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  const axios = require("axios");
  const baseURL  = "http://www.p2p.com/p2p/account_service/";
    export default {
        name: "Account",
        data(){
          return {
            Account:{},
            userName:"",
            dialogFormVisible1: false,
            dialogFormVisible2: false,
            dialogFormVisible3: false,
            formLabelWidth: '120px',
            mailForm:{},
            RealNameForm:{},
            phoneForm:{
              phone:"",
              verifyCode:""
            },
            show: true,
            count: '',
            timer: null,
          }
        },
      methods:{
        //验证手机验证码
        verifyPhoneCode(){
          let self = this;
          self.phoneForm.bitState=self.Account.bitState
          self.phoneForm.userId=self.Account.id;
          axios({
            url:baseURL+"p2p/auth/verifyPhone",
            method:"post",
            data:self.phoneForm
          }).then(function (res) {
            if(res.data){
              alert("手机认证成功")
              self.dialogFormVisible3=false;
              self.getList(self.Account.id)
            }else{
              alert("手机认证失败")
            }
          })
        },
        //获取验证码
        getVerifyCode(){
          if(this.phoneForm.phone==null||this.phoneForm.phone==""){
            alert("手机号不能为空")
            return;
          }
          axios({
            url:baseURL+"p2p/auth/sendPhone",
            method:"get",
            params:{phoneNum:this.phoneForm.phone}
          })
          const TIME_COUNT = 60;
          if (!this.timer) {
            this.count = TIME_COUNT;
            this.show = false;
            this.timer = setInterval(() => {
              if (this.count > 0 && this.count <= TIME_COUNT) {
                this.count--;
              } else {
                this.show = true;
                clearInterval(this.timer);
                this.timer = null;
              }
            }, 1000)
          }
        },
        //个人信息
        getList(userId){
          let self = this;
          axios({
            url:baseURL+"p2p/account/selectByPrimaryKey",
            method:"get",
            params:{id:userId}
          }).then(function (res) {
            self.Account = res.data;
          })
        },
        recharge(){
          this.$router.push("/recharge/"+this.Account.id)
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
