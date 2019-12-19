<template>
    <el-main>
      <h3>修改用户信息</h3>
      <div style="width: 300px;height: 500px">
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item label="手机号">
            <el-input v-model="form.userPhone" ></el-input>
          </el-form-item>
          <el-form-item label="微博">
            <el-input v-model="form.userMicroblog"></el-input>
          </el-form-item>
          <el-form-item label="推荐人">
            <el-input v-model="form.userPresenter" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.userEmail" ></el-input>
          </el-form-item>
          <el-form-item label="用户地址">
            <el-input v-model="form.userAddress"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateUser">修改</el-button>
            <el-button type="primary" @click="back">返回</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
</template>

<script>
  const axios = require("axios");
  const UserURL  = "http://localhost:10010/p2p/user_service/";
    export default {
        name: "DetialUser",
      data(){
          return{
            form: {},
        }
      },
      methods:{
        updateUser(){
          let self = this;
          axios({
            url: UserURL + "p2p/updateUser",
            method: "post",
            data:self.form
          }).then(function (res) {
              if(res.data){
                alert("修改成功")
                self.$router.push("/home")
              }else{
                alert("修改失败")
                self.$router.push("/home")
              }
          })
        },
        back(){
          this.$router.push("/personalCenter")
        },
        getUserInfoById(userId){
          let self = this;
          axios({
            url: UserURL + "p2p/getUserInfoById",
            method: "get",
            params: {id: userId}
          }).then(function (res) {
            self.form = res.data
          })
        }
      },
      created(){
        let userId = window.localStorage.getItem("userId");
        this.getUserInfoById(userId);
      }
    }
</script>

<style scoped>

</style>
