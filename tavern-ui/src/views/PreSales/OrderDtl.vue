<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="订单号" label-width="100px">
          <el-input v-model="filters.orderId" placeholder="请输入订单号"></el-input>
        </el-form-item>
        <el-form-item label="订单日期" label-width="100px">
          <el-date-picker v-model="filters.accountPeriod" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="应付金额" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入应付金额"></el-input>
        </el-form-item>
        <el-form-item label="实付金额" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入实付金额"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left" >
        <el-form-item>
          <kt-button icon="fa fa-search" label="添加关联业务" perms="sys:role:view" type="primary" @click="handleEdit(null)"/>
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
    <kt-table :height="300" permsEdit="sys:user:edit" permsDelete="sys:user:delete"
              :data="pageResult" :columns="filterColumns"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete">
    </kt-table>
    <!--新增编辑界面-->
    <el-dialog title="添加关联业务" width="50%" center :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="filters"  align="left">
        <el-form-item label="业务类型" label-width="100px">
          <el-select v-model="name" clearable auto-complete="off" placeholder="请选择" @change="selectBiz">
            <el-option label="代理记账" value='0'></el-option>
            <el-option label="公积金代缴/社保代缴" value='1'></el-option>
            <el-option label="公司注册" value='2'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关联公司" label-width="150px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="微软" value='0'></el-option>
            <el-option label="空客" value='1'></el-option>
            <el-option label="洛克希德马丁" value='2'></el-option>
            <el-option label="休斯顿火箭" value='3'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters"  align="left">
        <el-form-item label="对接人员" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="张三丰" value='0'></el-option>
            <el-option label="李连杰" value='1'></el-option>
            <el-option label="萧敬腾" value='2'></el-option>
            <el-option label="薛之谦" value='3'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否需要合作方" label-width="150px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='2'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters"  align="left" >
        <el-form-item label="合作方" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="张三丰" value='0'></el-option>
            <el-option label="李连杰" value='1'></el-option>
            <el-option label="萧敬腾" value='2'></el-option>
            <el-option label="薛之谦" value='3'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付合作方费用" label-width="150px">
          <el-input v-model="filters.name" placeholder="请输入费用"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters"  align="left" >
        <el-form-item label="前置任务" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="任务一" value='0'></el-option>
            <el-option label="任务二" value='1'></el-option>
            <el-option label="任务三" value='2'></el-option>
            <el-option label="任务四" value='3'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务标签" label-width="150px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="标签一" value='0'></el-option>
            <el-option label="标签二" value='1'></el-option>
            <el-option label="标签三" value='2'></el-option>
            <el-option label="标签四" value='3'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="服务内容/备注" prop="bak" label-width="100px">
          <el-input v-model="filters.name" auto-complete="off" style="width: 550px" placeholder="输入内容"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters"  align="left" v-if="showHelpBookKeeping">
        <el-form :inline="true" :model="filters" align="left">
          <el-form-item label="代理记账" prop="bak" label-width="100px">
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="filters"  align="left" >
          <el-form-item label="应付金额" label-width="100px">
            <el-input v-model="filters.name" placeholder="请输入应付金额"></el-input>
          </el-form-item>
          <el-form-item label="实付金额" label-width="100px">
            <el-input v-model="filters.name" placeholder="请输入实付金额"></el-input>
          </el-form-item>
        </el-form>
      </el-form>
      <el-form :model="filters"  align="left" v-if="showHelpPay">
        <el-form :inline="true" :model="filters" align="left">
          <el-form-item label="公积金代缴/社保代缴" prop="bak" label-width="200px">
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="filters"  align="left" style="margin-left: 50px">
          <el-form-item label="服务开始时间" label-width="100px">
            <el-date-picker v-model="filters.accountPeriod" type="datetime" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="服务期限(月)" label-width="100px">
            <el-input v-model="filters.name" placeholder="请输入服务期限(月)"></el-input>
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="filters" align="left" style="margin-left: 50px">
          <el-form-item label="缴款人数" label-width="100px">
            <el-input v-model="filters.name" placeholder="请输入缴款人数"></el-input>
          </el-form-item>
          <el-form-item label="服务结束时间" label-width="100px">
            <el-date-picker v-model="filters.accountPeriod" type="datetime" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
        </el-form>
      </el-form>
      <el-form :model="filters"  align="left" v-if="showHelpRegister">
        <el-form :inline="true" :model="filters" align="left">
          <el-form-item label="公司注册" prop="bak" label-width="100px">
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="filters"  align="left" >
          <el-form-item label="银行开户是否需要到场" label-width="200px">
            <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
              <el-option label="是" value='0'></el-option>
              <el-option label="否" value='1'></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :inline="true" :model="filters"  align="left" >
          <el-form-item label="注册地类型" label-width="200px">
            <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
              <el-option label="大陆" value='0'></el-option>
              <el-option label="香港" value='1'></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="dialogVisible = false" :loading="editLoading">
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
          orderId: '',
          name: ''
        },
        columns: [],
        filterColumns: [],
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},

        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          orderId: [
            {required: true, message: '请输入订单号', trigger: 'blur'}
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          id: 0,
          name: '',
          password: '123456',
          deptId: 1,
          deptName: '',
          email: 'test@qq.com',
          mobile: '13889700023',
          status: 1,
          userRoles: []
        },
        deptData: [],
        deptTreeProps: {
          label: 'name',
          children: 'children'
        },
        roles: [],
        showHelpBookKeeping: false,
        showHelpPay: false,
        showHelpRegister: false
      }
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.pageRequest.columnFilters = {name: {name: 'name', value: this.filters.name}}
        this.$api.order.findDtlPage(this.pageRequest).then((res) => {
          this.pageResult = res.data
        }).then(data != null ? data.callback : '')
      },
      // 加载用户角色信息
      findUserRoles: function () {
        this.$api.role.findAll().then((res) => {
          // 加载角色集合
          this.roles = res.data
        })
      },
      // 批量删除
      handleDelete: function (data) {
        this.$api.order.batchDelete(data.params).then(data != null ? data.callback : '')
      },
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true
        this.operation = true
        this.dataForm = {
          id: 0,
          name: '',
          password: '',
          deptId: 1,
          deptName: '',
          email: 'test@qq.com',
          mobile: '13889700023',
          status: 1,
          userRoles: []
        }
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.dataForm = Object.assign({}, params.row)
        let userRoles = []
        for (let i = 0, len = params.row.userRoles.length; i < len; i++) {
          userRoles.push(params.row.userRoles[i].roleId)
        }
        this.dataForm.userRoles = userRoles
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              let userRoles = []
              for (let i = 0, len = params.userRoles.length; i < len; i++) {
                let userRole = {
                  userId: params.id,
                  roleId: params.userRoles[i]
                }
                userRoles.push(userRole)
              }
              params.userRoles = userRoles
              this.$api.customer.save(params).then((res) => {
                this.editLoading = false
                if (res.code == 200) {
                  this.$message({message: '操作成功', type: 'success'})
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.findPage(null)
              })
            })
          }
        })
      },
      // 获取部门列表
      findDeptTree: function () {
        this.$api.dept.findDeptTree().then((res) => {
          this.deptData = res.data
        })
      },
      // 菜单树选中
      deptTreeCurrentChangeHandle(data, node) {
        this.dataForm.deptId = data.id
        this.dataForm.deptName = data.name
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
          {prop: "name", label: "业务类型", minWidth: 120},
          {prop: "staff", label: "对接人员", minWidth: 120},
          {prop: "source", label: "业务来源", minWidth: 120},
          {prop: "company", label: "关联公司", minWidth: 120},
          {prop: "bak", label: "备注", minWidth: 120},
          {prop: "createTime", label: "创建时间", minWidth: 120},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      selectBiz: function (e) {
        this.showHelpBookKeeping = false
        this.showHelpPay = false
        this.showHelpRegister = false
        if (e == 0) {
          this.showHelpBookKeeping = true
        }
        if (e == 1) {
          this.showHelpPay = true
        }
        if (e == 2) {
          this.showHelpRegister = true
        }
      }
    },
    mounted() {
      // this.findDeptTree()
      this.initColumns()
    }
  }
</script>

<style scoped>

</style>
