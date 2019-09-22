<template>
  <div class="page-container">
    <!--工具栏-->
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
              @findPage="findPage"  :showOperation = "showOperation">
    </kt-table>
  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import { format } from "@/utils/datetime"
  import {hasPermission} from '@/permission/index.js'

  export default {
    components:{
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog
    },
    data() {
      return {
        size: 'small',
        filters: {
          name: ''
        },
        columns: [],
        filterColumns: [],
        pageRequest: { pageNum: 1, pageSize: 10 },
        pageResult: {},
        showOperation: false,
        batchId:''
      }
    },
    created() {
      let _this = this;
      _this.batchId = this.$route.params.batchId;
      let _id = localStorage.getItem("invoiceDtlBatchId");
      if (_this.batchId == undefined || _this.batchId == null || _this.batchId == '') {
        if (_id == undefined || _id == null) {
          this.$router.push("/invoice/query")
          return
        } else {
          _this.batchId = _id;
        }
      }
      this.findPage(null);
      localStorage.setItem("invoiceDtlBatchId", _this.batchId);
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
        this.$api.invoice.findDtlPage(this.pageRequest).then((res) => {
          this.pageResult.pageNum = res.data.current
          this.pageResult.pageSize = res.data.size
          this.pageResult.totalSize = res.data.total
          this.pageResult.content = res.data.records
        }).then(data != null ? data.callback : '')
      },
      // 时间格式化
      dateFormat: function (row, column, cellValue, index){
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
        this.columns =  [
          {prop: "invoiceId", label: "发票代码", minWidth: 100,},
          {prop: "invoiceCode", label: "发票号码", minWidth: 100},
          {prop: "buyerName", label: "购方企业名称", minWidth: 120},
          {prop: "buyerTaxCode", label: "购方税号", minWidth: 200},
          {prop: "bankBranch", label: "银行支行名称", minWidth: 200},
          {prop: "bankAccount", label: "银行账号", minWidth: 120},
          {prop: "address", label: "地址", minWidth: 200},
          {prop: "tel", label: "电话", minWidth: 120},
          {prop: "invoiceDate", label: "开票日期", minWidth: 60},
          {prop: "productVersion", label: "商品编码版本号", minWidth: 60},
          {prop: "receiptId", label: "单据号", minWidth: 80},
          {prop: "productName", label: "商品名称", minWidth: 120},
          {prop: "productSpec", label: "规格", minWidth: 40},
          {prop: "productUnit", label: "单位", minWidth: 40},
          {prop: "productCount", label: "数量", minWidth: 40},
          {prop: "productUnitPrice", label: "单价", minWidth: 40},
          {prop: "totalPrice", label: "金额", minWidth: 80},
          {prop: "taxRate", label: "税率", minWidth: 40},
          {prop: "tax", label: "税费", minWidth: 60},
          {prop: "taxTypeCode", label: "税收分类代码", minWidth: 120}
        ],
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      }
    },
    mounted() {
      this.initColumns()
    }
  }
</script>

<style scoped>

</style>
