<template>
  <el-main style="width: 1200px;height: 600px">
    <h2>发标前审核</h2>
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
            @click="add(scope.row)"
            type="text"
            size="small">
            审核
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="借款标状态审核" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="审核状态" :label-width="formLabelWidth">
          <el-radio v-model="form.status" label="0">待审核</el-radio>
          <el-radio v-model="form.status" label="1">审核通过</el-radio>
          <el-radio v-model="form.status" label="2">审核不通过</el-radio>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="auditComment">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateStatus()">确 定</el-button>
      </div>
    </el-dialog>
  </el-main>
</template>

<script>
  const axios = require("axios");
  const BorrowURL = "http://localhost:10010/p2p/loan_service/";
  export default {
    name: "PreBidAudit",
    data() {
      return {
        tableData:[],
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form:{},
        auditUserId:0,
        auditComment:""
      }
    },
    methods: {
      getList(statusType){
        let self = this;
        axios({
          url:BorrowURL+"loan/getLoanMarkList",
          method:"get",
          params:{statusType:statusType}
        }).then(function (res) {
          self.tableData=res.data.list;
        })
      },
      add(form){
        this.form=form
        this.form.auditUserId=this.auditUserId
        this.dialogFormVisible=true;
      },
      updateStatus(){
        this.form.auditComment=this.auditComment
        let self = this;
        axios({
          url:BorrowURL+"loan/updateStatus",
          method:"post",
          data:this.form
        }).then(function (res) {
          if(res){
            self.dialogFormVisible=false;
            alert("修改成功")
            self.getList(0)
          }else{
            alert("修改失败")
            self.dialogFormVisible=false;
          }
        })
      }
    },
    created() {
      //获取借款标信息表
      this.getList(0);
      this.auditUserId = window.localStorage.getItem("menegerId");
    }
  }
</script>

<style scoped>

</style>
