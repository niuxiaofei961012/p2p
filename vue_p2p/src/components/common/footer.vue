<template>
  <el-footer style="padding: 0px">
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          >
          <el-menu-item index="1">
            <span slot="title">版权所有:    2018xxx科技有限公司</span>
          </el-menu-item>
          <el-menu-item index="2">
            <span slot="title">地    址:山西省太原市晋源区</span>
          </el-menu-item>
          <el-menu-item index="3">
            <span slot="title">电    话:0351-xxxxxxx</span>
          </el-menu-item>
          <el-menu-item index="4">
            <span slot="title">ICP备案:晋ICP备字520</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </el-footer>
</template>

<script>
  export default {
    name: "footer"
  }
</script>

<style scoped>

</style>
