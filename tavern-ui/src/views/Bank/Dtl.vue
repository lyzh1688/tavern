<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item label="交易时间：">
          <el-date-picker v-model="filters.startTradeDate" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="到">
          <el-date-picker v-model="filters.endTradeDate" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="对方账户：">
          <el-input v-model="filters.recvAccntId" placeholder="对方账户"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:role:view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
      </el-form>
    </div>
    <div class="toolbar" style="float:right;padding-top:10px;padding-right:15px;">
      <el-form :inline="true" :size="size">
        <el-form-item>
          <el-button-group>
            <el-tooltip content="刷新" placement="top">
              <el-button icon="fa fa-refresh" @click="findPage(null)"></el-button>
            </el-tooltip>
            <el-tooltip content="列显示" placement="top">
              <el-button icon="fa fa-filter" @click="displayFilterColumnsDialog"></el-button>
            </el-tooltip>
            <el-tooltip content="导出" placement="top">
              <el-button icon="fa fa-file-excel-o"></el-button>
            </el-tooltip>
          </el-button-group>
        </el-form-item>
      </el-form>
      <!--表格显示列界面-->
      <table-column-filter-dialog ref="tableColumnFilterDialog" :columns="columns"
                                  @handleFilterColumns="handleFilterColumns">
      </table-column-filter-dialog>
    </div>
    <!--表格内容栏-->
    <kt-table :height="600"
              :data="pageResult" :columns="filterColumns"
              @findPage="findPage" :showOperation="showOperation">
    </kt-table>
  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import {format, formatDate} from "@/utils/datetime"

  export default {
    components: {
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog
    },
    data() {
      return {
        size: 'small',
        filters: {
          recvAccntId: '',
          startTradeDate: '',
          endTradeDate: ''
        },
        columns: [],
        filterColumns: [],
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},
        showOperation: false,
        batchId: ''
      }
    },
    created() {
      let _this = this;
      _this.batchId = this.$route.params.batchId;
      let _id = localStorage.getItem("bankStatementDtlBatchId");
      if (_this.batchId == undefined || _this.batchId == null || _this.batchId == '') {
        if (_id == undefined || _id == null) {
          this.$router.push("/bank/query")
          return
        } else {
          _this.batchId = _id;
        }
      }
      this.findPage(null);
      localStorage.setItem("bankStatementDtlBatchId", _this.batchId);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
          this.pageRequest.current = data.pageRequest.pageNum;
          this.pageRequest.size = data.pageRequest.pageSize;
        }
        this.pageRequest.batchId = this.batchId;
        if (this.filters.recvAccntId != '') {
          this.pageRequest.recvAccntId = this.filters.recvAccntId
        }
        if (this.filters.startTradeDate != '') {
          this.pageRequest.startTradeDate = this.dateFormat(this.filters.startTradeDate)
        }
        if (this.filters.endTradeDate != '') {
          this.pageRequest.endTradeDate = this.dateFormat(this.filters.endTradeDate)
        }
        if(this.filters.startTradeDate != '' && this.filters.endTradeDate != '' && this.filters.startTradeDate > this.filters.endTradeDate){
          this.$message({message: '结束日期必须大于开始日期', type: 'error'})
          return
        }
        this.$api.bank.findDtlPage(this.pageRequest).then((res) => {
          this.pageResult.pageNum = res.data.current
          this.pageResult.pageSize = res.data.size
          this.pageResult.totalSize = res.data.total
          this.pageResult.content = res.data.records
        }).then(data != null ? data.callback : '')
      },
      // 时间格式化
      dateFormat: function (row, column, cellValue, index) {
        return format(row[column.property])
      },
      // 处理表格列过滤显示
      displayFilterColumnsDialog: function () {
        this.$refs.tableColumnFilterDialog.setDialogVisible(true)
      },
      // 处理表格列过滤显示
      handleFilterColumns: function (data) {
        this.filterColumns = data.filterColumns
        this.$refs.tableColumnFilterDialog.setDialogVisible(false)
      },
      // 处理表格列过滤显示
      initColumns: function () {
        this.columns = [
          {prop: "detailId", label: "账户明细编号-交易流水号", minWidth: 200},
          {prop: "customAccntId", label: "账号", minWidth: 200},
          {prop: "customAccntName", label: "账户名称", minWidth: 120},
          {prop: "tradeDate", label: "交易时间", minWidth: 120},
          {prop: "debitAmount", label: "借方发生额（支取）", minWidth: 100},
          {prop: "creditAmount", label: "贷方发生额（收入）", minWidth: 120},
          {prop: "balance", label: "余额", minWidth: 120},
          {prop: "coinCode", label: "币种", minWidth: 120},
          {prop: "recvAccntId", label: "对方户名", minWidth: 120},
          {prop: "recvAccntName", label: "对方账号", minWidth: 120},
          {prop: "institution", label: "对方开户机构", minWidth: 120},
          {prop: "accountingDate", label: "记账日期", minWidth: 120},
          {prop: "companySeq", label: "企业流水号", minWidth: 120},
          {prop: "certificateType", label: "凭证种类", minWidth: 120},
          {prop: "certificateId", label: "凭证号", minWidth: 120},
          {prop: "abstracts", label: "摘要", minWidth: 120},
          {prop: "remark", label: "备注", minWidth: 200},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      // 时间格式化
      dateFormat: function (date) {
        return format(date)
      },
    },
    mounted() {
      this.initColumns()
    }
  }
</script>

<style scoped>

</style>
