<template>
  <el-main>
    <h3>投标项目</h3>
    <el-table
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        fixed
        prop="publishTime"
        format="yyyy-MM-dd"
        label="发布日期"
        width="200">
      </el-table-column>
      <el-table-column
        prop="borrowName"
        label="标题"
        width="100">
      </el-table-column>
      <el-table-column
        prop="borrowUserName"
        label="借款人"
        width="100">
      </el-table-column>
      <el-table-column
        prop="borrowMoney"
        label="借款金额"
        width="100">
      </el-table-column>
      <el-table-column
        prop="returnMonthes"
        label="期限"
        width="100">
      </el-table-column>
      <el-table-column
        prop="yearRate"
        label="利率"
        width="100">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.status==0">待审核</span>
          <span v-else-if="scope.row.status==1">已审核</span>
          <span v-else-if="scope.row.status==2">审核不通过</span>
          <span v-else-if="scope.row.status==3">还款中</span>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const BorrowURL = "http://localhost:10010/p2p/loan_service/";
  export default {
    name: "BidProject",
    data() {
      return {
        tableData:[],
        form:{
          pageNo:1,
          pageSize:5
        }
      }
    },
    methods:{
      getBidList(){
        let userId = window.localStorage.getItem("userId");
        this.form.bidUserId=userId
        let self = this;
        axios({
          url:BorrowURL+"loan/getLoanMarkListsByBidUser",
          method:"post",
          data:this.form
        }).then(function (res) {
          self.tableData=res.data.list;
        })
      },
    },
    created(){
      this.getBidList()
    }
  }
</script>

<style scoped>

</style>
