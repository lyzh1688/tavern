<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item label="旺旺账号" label-width="100px" prop="wangwangAccnt" v-if="sys_presales_customer_view">
          <el-input v-model="filters.wangwangAccnt" placeholder="请输入旺旺账号"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名" label-width="100px" prop="customName" v-if="sys_presales_customer_view">
          <el-input v-model="filters.customName" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        <el-form-item  label-width="100px">
          <el-switch
            v-model="filters.myCustomer"
            active-color="#00A854"
            active-text="我的客户"
            active-value="1"
            inactive-color="#F04134"
            inactive-text="全部客户"
            inactive-value="0"
            @change="changeSwitch"
            >
          </el-switch>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_presales_customer_view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" label="新增客户"  type="primary" v-if="sys_presales_customer_add"
                     @click="handleAdd"/>
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
          </el-button-group>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="tableData" stripe stripe height="600" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="customId" header-align="center" align="center" label="客户ID" v-if="false">
      </el-table-column>
      <el-table-column prop="customName" label="客户姓名" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="companyName" label="公司名称" header-align="center" align="center" width="200px">
      </el-table-column>
      <el-table-column prop="wangwangAccnt" label="旺旺账号" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="weixinAccnt" label="微信账号" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="weixinName" label="微信昵称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="contactNumber" label="联系电话" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="updateDate" label="创建时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column  label="操作" header-align="center" align="center" width="500"
                       v-if="sys_presales_customer_edit || sys_presales_customer_dtl
                       || sys_presales_customer_order || sys_presales_customer_del"
      >
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" label="修改客户" v-if="sys_presales_customer_edit" type="primary"
                     @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-retweet" label="客户详情" v-if="sys_presales_customer_dtl" type="primary"
                     @click="handleDtl(scope.row)"/>
          <kt-button icon="fa fa-shopping-cart" label="客户订单" v-if="sys_presales_customer_order" type="primary"
                     @click="handleOrder(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger"  v-if="sys_presales_customer_del"
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
        <el-form-item label="客户ID" label-width="100px" prop="customId" v-if="editShow">
          <el-input v-model="dataForm.customId" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="法人姓名" label-width="100px" prop="corporation">
          <el-input v-model="dataForm.corporation" placeholder="请输入法人姓名"></el-input>
        </el-form-item>
        <el-form-item label="法人电话" label-width="100px" prop="corporationNumber">
          <el-input v-model="dataForm.corporationNumber" placeholder="请输入法人电话"></el-input>
        </el-form-item>
        <el-form-item label="旺旺账号" label-width="100px" prop="wangwangAccnt">
          <el-input v-model="dataForm.wangwangAccnt" placeholder="请输入旺旺账号"></el-input>
        </el-form-item>
        <el-form-item label="微信账号" label-width="100px" prop="weixinAccnt">
          <el-input v-model="dataForm.weixinAccnt" placeholder="请输入微信账号"></el-input>
        </el-form-item>
        <el-form-item label="微信昵称" label-width="100px" prop="weixinName">
          <el-input v-model="dataForm.weixinName" placeholder="请输入微信昵称"></el-input>
        </el-form-item>
        <el-form-item label="联系人姓名" label-width="100px" prop="contactPerson">
          <el-input v-model="dataForm.contactPerson" placeholder="请输入联系人姓名"></el-input>
        </el-form-item>
        <el-form-item label="联系人电话" label-width="100px" prop="contactNumber">
          <el-input v-model="dataForm.contactNumber" placeholder="请输入联系人电话" ></el-input>
        </el-form-item>
        <el-form-item label="客户姓名" label-width="100px" prop="customName">
          <el-input v-model="dataForm.customName" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        <el-form-item label="客户等级" label-width="100px" prop="customLevel">
          <el-select v-model="dataForm.customLevel" clearable auto-complete="off" placeholder="请选择客户等级"
                     style="float: left">
            <el-option label="VVIP" value='VVIP'></el-option>
            <el-option label="VIP" value='VIP'></el-option>
            <el-option label="高级客户" value='高级客户'></el-option>
            <el-option label="普通客户" value='普通客户'></el-option>
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
          wangwangAccnt: '',
          customName: '',
          myCustomer: ''
        },
        tableData: [],
        loading: false,
        columns: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20,
        },
        total: 0,
        pageResult: {},

        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        editShow: false,
        dataFormRules: {
          /*weixinAccnt: [
            {required: true, message: '请输入微信号', trigger: 'blur'}
          ],*/
      /*    wangwangAccnt: [
            {required: true, message: '请输入旺旺号', trigger: 'blur'}
          ],*/
          contactPerson: [
            {required: true, message: '请输入联系人', trigger: 'blur'}
          ],
          contactNumber: [
            {required: true, message: '请输入联系电话', trigger: 'blur'}
          ],
          customLevel: [
            {required: true, message: '请输入客户等级', trigger: 'blur'}
          ],
          customName: [
            {required: true, message: '请输入客户名称', trigger: 'blur'}
          ]
        },
        // 新增编辑界面数据
        dataForm: {
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
        sys_presales_customer_del:false,
        sys_presales_customer_view:false,
        sys_presales_customer_add:false,
        sys_presales_customer_edit:false,
        sys_presales_customer_dtl:false,
        sys_presales_customer_order:false,
      }
    },
    created() {
      this.sys_presales_customer_del = hasPermission('sys:presales:customer:del')
      this.sys_presales_customer_view = hasPermission('sys:presales:customer:view')
      this.sys_presales_customer_add = hasPermission('sys:presales:customer:add')
      this.sys_presales_customer_edit = hasPermission('sys:presales:customer:edit')
      this.sys_presales_customer_dtl = hasPermission('sys:presales:customer:dtl')
      this.sys_presales_customer_order = hasPermission('sys:presales:customer:order')
      this.findPage(null);
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
        this.pageRequest.wangwangAccnt = this.filters.wangwangAccnt;
        this.pageRequest.customName = this.filters.customName;
        if(this.filters.myCustomer == 1){
          this.pageRequest.userId = sessionStorage.getItem("userId");
        }else {
          this.pageRequest.userId = ''
        }
        this.$api.customer.findPage(this.pageRequest).then((res) => {
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
      // 批量删除
      handleDelete: function (data) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.loading = true
          let callback = res => {
            if (res.retCode == 0) {
              this.$message({message: '删除成功', type: 'success'})
              this.findPage(null)
            } else {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            }
            this.loading = false
          }
          let ids = []
          ids.push(data.customId)
          this.$api.customer.batchDelete(ids).then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'});
            this.loading = false
          })
        })
      },
      handleDtl: function (params) {
        this.$router.push({name: '客户详情', params: params})
      },
      handleOrder: function (params) {
        this.$router.push({name: '订单管理', params: params})
      },


      // 显示新增界面
      handleAdd: function () {
        this.editShow = false
        this.dialogVisible = true
        this.operation = true
        this.dataForm = {
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
        }
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.editShow = true
        this.dataForm = Object.assign({}, params)
      },
      // 编辑
      submitForm: function () {
        const reg = /^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\d{8}$/;
        if (!reg.test(this.dataForm.contactNumber) || this.dataForm.contactNumber.length != 11){
          this.$message({message: '手机号不合法！', type: 'error'});
          return
        }
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              params.userId = sessionStorage.getItem("userId")
              this.$api.customer.save(params).then((res) => {
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
      // 处理表格列过滤显示
      displayFilterColumnsDialog: function () {
        this.$refs.tableColumnFilterDialog.setDialogVisible(true)
      },
      // 处理表格列过滤显示
      handleFilterColumns: function (data) {
        this.filterColumns = data.filterColumns
        this.$refs.tableColumnFilterDialog.setDialogVisible(false)
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.findPage(_this.pageRequest);
      },
      changeSwitch(val){
        this.findPage(null)
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>

</style>
