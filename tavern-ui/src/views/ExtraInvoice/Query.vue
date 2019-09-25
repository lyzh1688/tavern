<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item label="公司名称：" v-if="sys_extInvoice_view">
          <el-input v-model="filters.name" placeholder="公司名称"></el-input>
        </el-form-item>
        <el-form-item label="会计期间：" v-if="sys_extInvoice_view">
          <el-date-picker v-model="filters.accountPeriod" type="date" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" type="primary" @click="findPage(null)"
                     v-if="sys_extInvoice_view"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" type="primary" @click="handleAdd"
                     v-if="sys_extInvoice_add"/>
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
        prop="customId"
        label="公司名称"
        header-align="center"
        align="center">
      </el-table-column>
      <el-table-column prop="accountPeriod" header-align="center" align="center" label="会计时间">
      </el-table-column>
      <el-table-column prop="invoiceType" label="发票大类" header-align="center" align="center">
        <template slot-scope="scope3">
          <!--0:未开始,1:处理中,2:已完成，3:失败-->
          <span v-if="scope3.row.invoiceType == '0'">类型1</span>
          <span v-if="scope3.row.invoiceType == '1'">类型2</span>
          <span v-if="scope3.row.invoiceType == '2'">类型3</span>
          <span v-if="scope3.row.invoiceType == '3'">类型4</span>
        </template>
      </el-table-column>
      <el-table-column prop="totalAmount" label="总金额" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="itemCount" label="发票张数" header-align="center" align="center">
      </el-table-column>
      <el-table-column
        v-if="sys_extInvoice_edit || sys_extInvoice_del"
         header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" @click="handleEdit(scope.row)"
                     v-if="sys_extInvoice_edit"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger" @click="handleDelete(scope.row)"
                     v-if="sys_extInvoice_del"/>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                   :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                   style="float:right;">
    </el-pagination>
    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="20%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item label="公司名称" prop="customId">
          <el-input v-model="dataForm.customId"></el-input>
        </el-form-item>
        <el-form-item label="会计期间" prop="accountPeriod">
          <el-date-picker v-model="dataForm.accountPeriod" type="date" placeholder="选择日期时间"
                          align="left"></el-date-picker>
        </el-form-item>
        <el-form-item label="发票大类" prop="invoiceType">
          <el-select clearable v-model="dataForm.invoiceType">
            <el-option label="类型1" value='0'></el-option>
            <el-option label="类型2" value='1'></el-option>
            <el-option label="类型3" value='2'></el-option>
            <el-option label="类型4" value='3'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="总金额" prop="totalAmount">
          <el-input v-model="dataForm.totalAmount" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="发票张数" prop="itemCount">
          <el-input v-model="dataForm.itemCount" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import {format, formatDate, formatDateSimple8} from "@/utils/datetime"
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
          name: ''
        },
        columns: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20
        },
        total: 0,
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          customId: [
            {required: true, message: '请输入公司名称', trigger: 'blur'}
          ],
          accountPeriod: [
            {required: true, message: '请输入会计时间', trigger: 'blur'}
          ],
          invoiceType: [
            {required: true, message: '请输入发票类型', trigger: 'blur'}
          ]
        },
        dataForm: {
          customId: '',
          accountPeriod: '',
          invoiceType: '',
          totalAmount: '',
          itemCount: '',
        },
        loading: false,  // 加载标识
        sys_extInvoice_add: false,
        sys_extInvoice_edit: false,
        sys_extInvoice_view: false,
        sys_extInvoice_del: false
      }
    },
    created() {
      this.sys_extInvoice_add = hasPermission('sys:extInvoice:add')
      this.sys_extInvoice_edit = hasPermission('sys:extInvoice:edit')
      this.sys_extInvoice_view = hasPermission('sys:extInvoice:view')
      this.sys_extInvoice_del = hasPermission('sys:extInvoice:del')
      findPage(null)
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data
        }
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        this.loading = true
        if (this.filters.name !== '') {
          this.pageRequest.customId = this.filters.customId
        }
        if (this.filters.accountPeriod !== '') {
          this.pageRequest.accountPeriod = this.dateFormat(this.filters.accountPeriod)
        }
        this.$api.extrainvoice.findPage(this.pageRequest).then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageRequest.current = res.data.current;
          this.pageRequest.size = res.data.size;
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
          }
          this.$api.extrainvoice.deleteRecord(data.recordId, "0").then(data != null ? callback : '')
        })
      },
      // 显示新增界面
      handleAdd: function () {
        this.dataForm = {
          customId: '',
          accountPeriod: '',
          invoiceType: '',
          totalAmount: '',
          itemCount: '',
        }
        this.dialogVisible = true
        this.operation = true
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.dataForm = Object.assign({}, params)
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              if (this.dataForm.accountPeriod !== '') {
                params.accountPeriod = this.dateFormat(this.dataForm.accountPeriod)
              }
              params.recordId = this.dataForm.customId + "_" + params.accountPeriod + "_" + this.dataForm.invoiceType
              this.$api.extrainvoice.save(params).then((res) => {
                this.editLoading = false
                this.$message({message: '操作成功', type: 'success'})
                this.dialogVisible = false
                this.$refs['dataForm'].resetFields()
                this.findPage(null)
              }).catch((res) => {
                this.editLoading = false
                this.dialogVisible = false
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
              })
            })
          }
        })
      },
      // 时间格式化
      dateFormat: function (date) {
        return formatDateSimple8(date)
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
          {prop: "customId", label: "公司名称", minWidth: 200},
          {prop: "accountPeriod", label: "会计期间", minWidth: 120},
          {prop: "invoiceType", label: "发票大类", minWidth: 120},
          {prop: "totalAmount", label: "总金额", minWidth: 100},
          {prop: "itemCount", label: "发票张数", minWidth: 120},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.pageNum = val;
        _this.findPage(_this.pageRequest);
      },
    },
    mounted() {
      this.initColumns();
      this.findPage(null);
    }
  }
</script>

<style scoped>
  .dialog-footer button {
    padding: 9px 10px !important;
    font-size: 14px !important;
  }
</style>
