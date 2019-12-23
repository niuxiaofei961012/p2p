<template>
  <el-main>
    <h3>还款明细</h3>

    <div class="excel_container">
      <el-button style="float: right" type="success" round icon="document" @click="exportToExcel()">导出数据</el-button>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        prop="loanName"
        label="借款标名称"
        width="100">
      </el-table-column>
      <el-table-column
        prop="phase"
        label="第几期"
        width="100">
      </el-table-column>
      <el-table-column
        prop="returnUserId"
        label="还款人id"
        width="100">
      </el-table-column>
      <el-table-column
        prop="returnMoney"
        label="还款本息"
        width="100">
      </el-table-column>
      <el-table-column
        prop="returnPrincipal"
        label="还款本金"
        width="100">
      </el-table-column>
      <el-table-column
        prop="returnInterest"
        label="还款利息"
        width="100">
      </el-table-column>
      <el-table-column
        prop="returnDeadline"
        label="还款截止日"
        width="200">
      </el-table-column>
      <el-table-column
        prop="returnStatus"
        label="状态"
        width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.returnStatus==0">未还款</span>
          <span v-else-if="scope.row.returnStatus==1">已还款</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button
            @click=""
            type="text"
            size="small">
            还款
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
        name: "ReturnList",
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
          this.form.returnUserId=userId
          let self = this;
          axios({
            url:BorrowURL+"return/selectReturnLists",
            method:"post",
            data:this.form
          }).then(function (res) {
            self.tableData=res.data.list;
          })
        },
        exportToExcel(){
          //excel数据导出
          require.ensure([], () => {
            const {
              export_json_to_excel
            } = require('@/assets/js/Export2Excel');
            const tHeader = ['借款标名称','第几期', '还款人id', '还款本息', '还款本金','还款利息', '还款截止日','状态'];
            const filterVal = ['loanName','phase', 'returnUserId', 'returnMoney', 'returnPrincipal','returnInterest', 'returnDeadline', 'returnStatus'];
            const list = this.tableData;
            const data = this.formatJson(filterVal, list);
            export_json_to_excel(tHeader, data, '列表excel');

          })
        },
        formatJson(filterVal, jsonData) {
          return jsonData.map(v => filterVal.map(j => v[j]))
        }

      },
      created(){
        this.getBidList()
      }
    }
</script>

<style scoped>

</style>
