<template>
  <div class="mainWrap">
    <el-col :span="24" class="main-top">
      <p><span class="short-line"></span>增值税详情展示</p>
    </el-col>
    <el-table
      :data="tableData"
      border stripe
      height="350"
      style="width: 100%"
      :header-cell-style="tableHeaderColor" size="mini">
      <el-table-column
        prop="fileName"
        label="文件名"
        type="index"
        width="100" align="center">
        <template slot-scope="scope1">
          <router-link to="/vatInvoice/vatInvoiceFileAmend" style="color: #66B3FF;text-decoration: underline;">
            {{scope1.row.fileName}}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="isValid"
        label="验证结果" align="center">
        <template slot-scope="scope3">
          <span v-if="scope3.row.isValid == '0'">失败</span>
          <span v-if="scope3.row.isValid == '1'">成功</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="isEmend"
        label="是否已订正" align="center">
        <template slot-scope="scope4">
          <span v-if="scope4.row.isEmend == '0'">否</span>
          <span v-if="scope4.row.isEmend == '1'">是</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="operator"
        label="操作人" align="center">
      </el-table-column>
      <el-table-column
        prop="updateDate"
        label="操作时间" align="center">
      </el-table-column>
      <el-table-column
        prop="failedReason"
        label="失败原因" align="center" width="200">
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="current"
      :page-sizes="[10, 50, 100, 200]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
  export default {
    name: 'vatInvoiceFileDtl',
    data() {
      return { /*查询区域*/
        formInline: {
          status: ''
        },
        formInline2: {},
        formInline3: {},
        isEnable: true,
        tableData: [],
        tableData2: [],
        hideRow: false,
        current: 1,
        total: 0,
        size: 10,
        batchId:''
      }
    },
    beforeCreate() {
      let _this = this;
      _this.batchId= this.$route.query.batchId;
    },
    created(){
      this.queryFileDtl();
    },
    methods: {
      //查询
      queryFileDtl() {
        let _this = this;

        _this.ajaxPost({
          url: '/alleria/Invoice/zzs/scan',
          data: {
            batchId:  _this.batchId,
          },
          success(data) {
            if (data.statuCode == 200) {
              _this.tableData.reSet(data.data.records);
              _this.total = data.total;
            } else {
              _this.$message.error('获取增值税包文件详情信息失败,原因为:' + data.msg);
            }
          },
          error(err) {
            _this.$message.error('获取增值税包文件详情遇到异常,异常为:' + err);
          }
        });
      },
    }
  }
</script>

<style scoped>

</style>
