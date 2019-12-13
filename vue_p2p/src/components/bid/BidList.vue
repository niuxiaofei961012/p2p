<template>
  <el-main style="width: 1200px;height: 600px">
    <h2>投标信息</h2>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="250">
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
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button
            @click="getById(scope.row.borrowSignId)"
            type="text"
            size="small">
            详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const BorrowURL  = "http://localhost:10010/p2p/loan_service/";
    export default {
        name: "BidList",
        data() {
        return {
          tableData:[],
          dialogFormVisible: false,
          formLabelWidth: '120px',
        }
      },
      methods:{
        getBidList(status){
          let userId = window.localStorage.getItem("userId");
          let self = this;
          axios({
            url:BorrowURL+"loan/getLoanMarkListByStatus",
            method:"get",
            params:{userId:userId,status:1}
          }).then(function (res) {
            self.tableData=res.data.list;
          })
        },
        getById(id){
          this.$router.push("/oneInfoFromBid/"+id)
        }
      },
      created(){
        this.getBidList(1)
      }
    }
</script>

<style scoped>

</style>
