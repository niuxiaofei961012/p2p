<template>
  <el-main style="width: 1200px;height: 600px">
    <h2>线下充值审核</h2>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="250">
      <el-table-column
        fixed
        prop="downOrderTime"
        format="yyyy-MM-dd"
        label="下单时间"
        width="160">
      </el-table-column>
      <el-table-column
        fixed
        prop="payTime"
        format="yyyy-MM-dd"
        label="支付时间"
        width="160">
      </el-table-column>
      <el-table-column
        prop="tradeCode"
        label="交易编号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="payMoney"
        label="充值金额"
        width="100">
      </el-table-column>
      <el-table-column
        prop="payComment"
        label="付款单备注"
        width="100">
      </el-table-column>
      <el-table-column
        prop="createUserId"
        label="用户id"
        width="100">
      </el-table-column>
      <el-table-column
        prop="payStatus"
        label="状态"
        width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.auditStatue==0">待审核</span>
          <span v-else-if="scope.row.auditStatue==1">已审核</span>
          <span v-else-if="scope.row.auditStatue==2">审核不通过</span>
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
          <el-radio v-model="form.auditStatue" label="0">待审核</el-radio>
          <el-radio v-model="form.auditStatue" label="1">审核通过</el-radio>
          <el-radio v-model="form.auditStatue" label="2">审核不通过</el-radio>
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
  </el-main></template>

<script>
  const axios = require("axios");
  const AuditURL  = "http://localhost:10010/p2p/audit_service/";
  const AccountURL  = "http://localhost:10010/p2p/account_service/";
    export default {
        name: "DownLineRechargeAudit",
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
        add(form){
          this.form={}
          this.form=form
          this.form.auditUserId=this.auditUserId
          this.dialogFormVisible=true;
        },
        updateStatus(){
          this.form.auditComment=this.auditComment
          let self = this;
          axios({
            url:AuditURL+"audit/auditRechargeRecord",
            method:"post",
            data:this.form
          }).then(function (res) {
            if(res){
              self.dialogFormVisible=false;
              alert("修改成功")
              self.getList()
            }else{
              alert("修改失败")
              self.dialogFormVisible=false;
            }
          })
        },
        getList() {
          let self = this;
          axios({
            url: AccountURL + "p2p/account/getRechargeRecordList",
            method: "get",
          }).then(function (res) {
            self.tableData = res.data.list;
          })
        },
      },
      created() {
        this.getList();
        this.auditUserId = window.localStorage.getItem("menegerId");
      }
    }

</script>

<style scoped>

</style>
