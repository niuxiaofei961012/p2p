<template>
  <el-main>
    <h3>收款明细</h3>
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
        prop="receiveMoney"
        label="收款总金额"
        width="100">
      </el-table-column>
      <el-table-column
        prop="receiveInterest"
        label="收款利率"
        width="100">
      </el-table-column>
      <el-table-column
        prop="receivePrincipal"
        label="收款本金"
        width="100">
      </el-table-column>
      <el-table-column
        prop="interestFee"
        label="收款利息"
        width="100">
      </el-table-column>
      <el-table-column
        prop="receiveUserId"
        label="回款人id"
        width="100">
      </el-table-column>
      <el-table-column
        fixed
        prop="receiveDate"
        format="yyyy-MM-dd"
        label="回款日"
        width="200">
      </el-table-column>
      <el-table-column
        prop="receiveStatus"
        label="状态"
        width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.receiveStatus==0">未还款</span>
          <span v-else-if="scope.row.receiveStatus==1">已还款</span>
        </template>
      </el-table-column>
    </el-table>
  </el-main>
</template>
<script>
  const axios = require("axios");
  const ReceiveURL = "http://localhost:10010/p2p/loan_service/";
  export default {
    name: "ReceiveInfo",
    data() {
      return {
        tableData: [],
        form: {
          pageNo: 1,
          pageSize: 5
        }
      }
    },
    methods: {
      getBidList() {
        let userId = window.localStorage.getItem("userId");
        this.form.receiveUserId = userId
        let self = this;
        axios({
          url: ReceiveURL + "receive/selectReceiveLists",
          method: "post",
          data: this.form
        }).then(function (res) {
          self.tableData = res.data.list;
        })
      },
      exportToExcel() {
        //excel数据导出
        require.ensure([], () => {
          const {
            export_json_to_excel
          } = require('@/assets/js/Export2Excel');
          const tHeader = ['借款标名称', '第几期', '收款总金额', '收款利率', '收款本金', '收款利息', '回款人id'];
          const filterVal = ['loanName', 'phase', 'receiveMoney', 'receiveInterest', 'receivePrincipal', 'interestFee', 'receiveUserId'];
          const list = this.tableData;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '列表excel');

        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]))
      }
    },
    created() {
      this.getBidList()
    }
  }
</script>

<style scoped>

</style>
