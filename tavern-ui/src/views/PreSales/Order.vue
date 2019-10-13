<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" type="primary" v-if="sys_presales_order_view"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" type="primary" v-if="sys_presales_order_add"
                     @click="handleAdd"/>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="tableData" stripe stripe height="600" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="customId" header-align="center" align="center" label="客户ID" v-if="false">
      </el-table-column>
      <el-table-column prop="orderId" header-align="center" align="center" label="订单号">
      </el-table-column>
      <el-table-column prop="orderDate" label="订单时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="receivableAmt" label="应付金额" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="payableAmt" label="实付金额" header-align="center" align="center">
      </el-table-column>
      <el-table-column label="操作" header-align="center" align="center" width="500"
                       v-if="sys_presales_order_edit || sys_presales_order_dtl">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" label="修改订单" type="primary" v-if="sys_presales_order_edit"
                     @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-retweet" label="订单详情" type="primary" v-if="sys_presales_order_dtl"
                     @click="handleDtl(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                     :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                     style="float:right;">
      </el-pagination>
    </div>
    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item label="订单号" prop="orderId" :disable="editable" label-width="100px">
          <el-input v-model="dataForm.orderId" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="订单时间" prop="orderDate" label-width="100px">
          <el-date-picker v-model="dataForm.orderDate" type="datetime" placeholder="选择日期时间"
                          style="float: left"></el-date-picker>
        </el-form-item>
        <el-form-item label="应付金额" prop="receivableAmt" label-width="100px">
          <el-input v-model="dataForm.receivableAmt" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="实付金额" prop="payableAmt" label-width="100px">
          <el-input v-model="dataForm.payableAmt" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
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
  import {format} from "@/utils/datetime"
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
        size: 'small',
        filters: {
          name: ''
        },
        dtlForm: {
          customId: '',
          weixinAccnt: '',
          weixinName: '',
          wangwangAccnt: '',
          contactPerson: '',
          contactNumber: '',
          corporation: '',
          corporationNumber: '',
          customLevel: '',
          customName: '',
          updateDate: ''
        },
        columns: [],
        filterColumns: [],
        loading: false,
        pageRequest: {
          current: 1,
          size: 20,
        },
        total: 0,
        tableData: [],
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        editable: true,
        dataFormRules: {
          orderId: [
            {required: true, message: '请输入订单号', trigger: 'blur'}
          ],
          orderDate: [
            {required: true, message: '请选择订单时间', trigger: 'blur'}
          ],
          receivableAmt: [
            {required: true, message: '请输入应付金额', trigger: 'blur'}
          ],
          payableAmt: [
            {required: true, message: '请输入实付金额', trigger: 'blur'}
          ],
          customLevel: [
            {required: true, message: '请输入客户等级', trigger: 'blur'}
          ],
        },
        // 新增编辑界面数据
        dataForm: {
          customId: '',
          orderId: '',
          orderDate: '',
          receivableAmt: '',
          payableAmt: '',
        },
        sys_presales_order_edit: false,
        sys_presales_order_dtl: false,
        sys_presales_order_view: false,
        sys_presales_order_add: false,
      }
    }, created() {
      this.sys_presales_order_edit = hasPermission('sys:presales:order:edit')
      this.sys_presales_order_dtl = hasPermission('sys:presales:order:dtl')
      this.sys_presales_order_view = hasPermission('sys:presales:order:view')
      this.sys_presales_order_add = hasPermission('sys:presales:order:add')
      //初始化客户信息
      this.dtlForm = this.$route.params;
      let tmpInfo = JSON.parse(localStorage.getItem("orderInfo"));
      if (this.dtlForm.customId == undefined || this.dtlForm.customId == null) {
        if (tmpInfo.customId == undefined || tmpInfo.customId == null) {
          this.$router.push({name: "客户信息"})
          return
        } else {
          this.dtlForm = tmpInfo;
        }
      }
      localStorage.setItem("orderInfo", JSON.stringify(this.dtlForm));
      this.findPage(null);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        this.pageRequest.customId = this.dtlForm.customId;
        this.$api.customer.findOrderPage(this.pageRequest).then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageRequest.current = res.data.current;
          this.pageRequest.size = res.data.size;
          callback(res)
        }).then(data != null ? data.callback : '')
          .catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            callback(res)
          })
      },
      handleDtl: function (params) {
        this.$router.push({name: '订单详情', params: params})
      },
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true
        this.operation = true
        this.dataForm = {
          customId: '',
          orderId: '',
          orderDate: '',
          receivableAmt: '',
          payableAmt: '',
        }
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.editable = false
        this.dataForm = Object.assign({}, params)
      },
      // 编辑
      submitForm: function () {
        alert(1)
        if (this.dataForm.payableAmt > this.dataForm.receivableAmt) {
          this.$message({message: '实付金额不能大于应付金额', type: 'warn'});
          return;
        }
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              params.orderDate = this.dateFormat(this.dataForm.orderDate)
              params.customId = this.dtlForm.customId
              this.$api.customer.saveOrder(params).then((res) => {
                this.editLoading = false
                this.$message({message: '操作成功', type: 'success'})
                this.dialogVisible = false
                this.$refs['dataForm'].resetFields()
                this.findPage(null)
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'});
                this.loading = false
              })
            })
          }
        })
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.findPage(_this.pageRequest);
      },
      // 时间格式化
      dateFormat: function (date) {
        return format(date)
      },
    },
    mounted() {
    }
  }
</script>

<style scoped>

</style>
