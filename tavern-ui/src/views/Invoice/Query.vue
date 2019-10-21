<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item label="公司名称：">
          <el-input v-model="filters.name" placeholder="公司名称"></el-input>
        </el-form-item>
        <el-form-item label="会计期间：">
          <el-date-picker v-model="filters.accountPeriod" type="date" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_invoice_query_view" type="primary"
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
          </el-button-group>
        </el-form-item>
      </el-form>
      <!--表格显示列界面-->
      <table-column-filter-dialog ref="tableColumnFilterDialog" :columns="columns"
                                  @handleFilterColumns="handleFilterColumns">
      </table-column-filter-dialog>
    </div>
    <!--表格内容栏-->
    <el-table :data="tableData" stripe stripe height="600" size="mini" style="width: 100%;" v-loading="loading">
      <el-table-column
        prop="batchId"
        label="批次号"
        header-align="center"
        align="center">
        <template slot-scope="scope1">
          <router-link :to="{name:'发票明细',params:{batchId:scope1.row.batchId}}" replace
                       style="color: #66B3FF;text-decoration: underline;">
            {{scope1.row.batchId}}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column prop="accountPeriod" header-align="center" align="center" label="会计时间">
      </el-table-column>
      <el-table-column prop="fileName" label="文件名" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="updateDate" label="上传时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column
         header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger"  v-if="sys_invoice_query_del"
                     @click="handleDelete(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                   :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                   style="float:right;">
    </el-pagination>
  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import {format,formatDate} from "@/utils/datetime"
  import {hasPermission} from '@/permission/index.js'

  export default {
    components: {
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog
    },
    data() {
      return {
        tableData: [],
        size: 'small',
        filters: {
          name: '',
          accountPeriod: ''
        },
        columns: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20
        },
        pageResult: {},
        total: 0,
        loading: false,  // 加载标识
        sys_invoice_query_view: false,
        sys_invoice_query_del: false,
      }
    },
    created() {
      let _this = this;
      _this.sys_invoice_query_view = hasPermission('sys:invoice:query:view')
      _this.sys_invoice_query_del = hasPermission('sys:invoice:query:del')
      _this.batchId = this.$route.params.batchId;
      _this.findPage(null);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        let _this = this
        if (data !== null) {
          _this.pageRequest = data
        }
        _this.loading = true
        let callback = res => {
          _this.loading = false
        }
        if (_this.batchId !== undefined && _this.batchId != null && _this.batchId !== '') {
          _this.pageRequest.batchId = _this.batchId;
        }
        _this.pageRequest.name = _this.filters.name
        if(_this.filters.accountPeriod !== ''){
          _this.pageRequest.accountPeriod = _this.dateFormat(_this.filters.accountPeriod)
        }
        _this.$api.invoice.findPage(_this.pageRequest).then((res) => {
          _this.tableData = res.data.records;
          _this.total = res.data.total;
          _this.pageRequest.current = res.data.current;
          _this.pageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        });
      },
      // 批量删除
      handleDelete: function (data) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.loading = true
          let callback = res => {
            if (res.retCode === 0) {
              this.$message({message: '删除成功', type: 'success'})
              this.findPage(null)
            } else {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            }
            this.loading = false
          }
          this.$api.invoice.batchDelete(data.fileId,'0').then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            this.loading = false
          })
        })
      },
      // 时间格式化
      dateFormat: function (date) {
        return formatDate(date)
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
          {prop: "batchId", label: "批次号", minWidth: 50},
          {prop: "accountPeriod", label: "会计期间", minWidth: 120},
          {prop: "fileName", label: "文件名", minWidth: 120},
          {prop: "updateDate", label: "上传时间", minWidth: 120},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      handleCurrentChange(val) {
        this.pageRequest.current = val;
        this.findPage(this.pageRequest);
      },
    },
    mounted() {
      this.initColumns();
      this.findPage(null);
    }
  }
</script>

<style scoped>

</style>
