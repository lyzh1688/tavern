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
      <el-table-column prop="id" header-align="center" align="center" label="订单ID" v-if="false">
      </el-table-column>
      <el-table-column prop="customId" header-align="center" align="center" label="客户ID" v-if="false">
      </el-table-column>
      <el-table-column prop="orderId" header-align="center" align="center" label="订单号">
      </el-table-column>
      <el-table-column prop="businessName" header-align="center" align="center" label="业务类型">
      </el-table-column>
      <el-table-column prop="orderDate" label="订单时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="receivableAmt" label="应付金额" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="payableAmt" label="实付金额" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="orderSource" label="订单来源" header-align="center" align="center">
      </el-table-column>
      <el-table-column label="操作" header-align="center" align="center" width="500"
                       v-if="sys_presales_order_edit || sys_presales_order_dtl">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" label="修改订单" type="primary" v-if="sys_presales_order_edit"
                     @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-retweet" label="订单详情" type="primary" v-if="sys_presales_order_dtl"
                     @click="handleDtl(scope.row)"/>
          <kt-button icon="fa fa-delete" label="订单删除" type="danger" v-if="sys_presales_order_del"
                     @click="handleDelete(scope.row)"/>
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
        <el-form-item label="订单ID" prop="id" label-width="100px" v-if="false">
          <el-input v-model="dataForm.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="订单号" prop="orderId" label-width="100px">
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
        <el-form-item label="订单来源" prop="orderSource" label-width="100px">
          <el-select v-model="dataForm.orderSource" clearable auto-complete="off" placeholder="请选择订单来源"
                     style="float: left">
            <el-option label="烁翼旗舰店" value='烁翼旗舰店'></el-option>
            <el-option label="咔嗒旗舰店" value='咔嗒旗舰店'></el-option>
            <el-option label="美臻明旗舰店" value='美臻明旗舰店'></el-option>
            <el-option label="银行对公账户" value='银行对公账户'></el-option>
            <el-option label="支付宝" value='支付宝'></el-option>
          </el-select>
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
          id: '',
          customId: '',
          orderId: '',
          orderDate: '',
          receivableAmt: '',
          payableAmt: '',
          orderSource: '',
        },
        sys_presales_order_edit: false,
        sys_presales_order_dtl: false,
        sys_presales_order_view: false,
        sys_presales_order_add: false,
        sys_presales_order_del: false,
      }
    }, created() {
      this.sys_presales_order_edit = hasPermission('sys:presales:order:edit')
      this.sys_presales_order_dtl = hasPermission('sys:presales:order:dtl')
      this.sys_presales_order_view = hasPermission('sys:presales:order:view')
      this.sys_presales_order_add = hasPermission('sys:presales:order:add')
      this.sys_presales_order_del = hasPermission('sys:presales:order:del')
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
        this.editable = false
        this.dataForm = {
          id: '',
          customId: '',
          orderId: '',
          orderDate: '',
          receivableAmt: '',
          payableAmt: '',
          orderSource: '',
        }
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.editable = true
        this.dataForm = Object.assign({}, params)
      },
      handleDelete: function (pa) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.loading = true
          let callback = res => {
            this.$message({message: '删除成功', type: 'success'})
            this.findPage(null)
            this.loading = false
          }
          let request = {}
          request.orderId = pa.id;
          this.$api.customer.deleteOrder(request).then(pa != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            this.loading = false
          })
        })
      },
      // 编辑
      submitForm: function () {
        if (eval(this.dataForm.payableAmt) > eval(this.dataForm.receivableAmt)) {
          this.$message({message: '实付金额不能大于应付金额', type: 'warn'});
          return;
        }
        if(this.dataForm.orderDate != undefined && this.dataForm.orderDate != null){
          if (this.dataForm.orderDate > Date.now()) {
            this.$message({message: '订单日期不能大于当前日期！', type: 'warn'});
            return;
          }
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
                this.editLoading = false
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
