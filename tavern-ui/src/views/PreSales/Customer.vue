<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item label="旺旺账号" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入旺旺账号"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:role:view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" label="新增客户" perms="sys:user:add" type="primary"
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

    <el-table :data="pageResult.content" stripe stripe height="600" size="mini" style="width: 100%;" v-loading="loading">
      <el-table-column prop="id" header-align="center" align="center" label="客户ID">
      </el-table-column>
      <el-table-column prop="name" label="客户姓名" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="aliAccnt" label="旺旺账号" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="wechatAccnt" label="微信账号" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="wechatName" label="微信昵称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="mobile" label="联系电话" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column fixed="right"label="操作" header-align="center" align="center" width="500">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" label="修改客户" perms="sys:user:add" type="primary"
                     @click="handleEdit"/>
          <kt-button icon="fa fa-retweet" label="客户详情" perms="sys:user:add" type="primary"
                     @click="handleDtl"/>
          <kt-button icon="fa fa-shopping-cart" label="客户订单" perms="sys:user:add" type="primary"
                     @click="handleOrder"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger"
                     @click="handleDelete(scope.row)" />
        </template>
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper"
                     @current-change="handleCurrentChange"
                     :current-page="pageRequest.pageNum"
                     :page-size="pageRequest.pageSize"
                     :total="totalSize" style="float:right;">
      </el-pagination>
    </div>
    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="name">
          <el-input v-model="dataForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="dataForm.password" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="机构" prop="deptName">
          <popup-tree-input
            :data="deptData"
            :props="deptTreeProps"
            :prop="dataForm.deptName"
            :nodeKey="''+dataForm.deptId"
            :currentChangeHandle="deptTreeCurrentChangeHandle">
          </popup-tree-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dataForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="dataForm.mobile" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="userRoles" v-if="!operation">
          <el-select v-model="dataForm.userRoles" multiple placeholder="请选择"
                     style="width: 100%;">
            <el-option v-for="item in roles" :key="item.id"
                       :label="item.remark" :value="item.id">
            </el-option>
          </el-select>
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
          name: ''
        },
        loading:false,
        columns: [],
        filterColumns: [],
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},

        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          name: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
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
        roles: []
      }
    },
    created() {
      this.findPage(null);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.pageRequest.columnFilters = {name: {name: 'name', value: this.filters.name}}
        this.$api.customer.findPage(this.pageRequest).then((res) => {
          this.pageResult = res.data
          this.pageRequest.pageNum = res.data.pageNum
          this.pageRequest.pageSize = res.data.pageSize
          this.totalSize = res.data.totalSize
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
        this.$api.customer.batchDelete(data.params).then(data != null ? data.callback : '')
      },
      handleAdd: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      handleEdit: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      handleDtl: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      handleOrder: function () {
        this.$router.push({path: '/preSales/order'})
      },


      // 显示新增界面
     /* handleAdd: function () {
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
      },*/
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
          {prop: "id", label: "客户ID", minWidth: 50},
          {prop: "name", label: "客户姓名", minWidth: 120},
          {prop: "aliAccnt", label: "旺旺账号", minWidth: 120},
          {prop: "wechatAccnt", label: "微信账号", minWidth: 100},
          {prop: "wechatName", label: "微信昵称", minWidth: 120},
          {prop: "mobile", label: "联系电话", minWidth: 100},
          {prop: "createTime", label: "创建时间", minWidth: 70},
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
      // this.findDeptTree()
      this.initColumns()
    }
  }
</script>

<style scoped>

</style>
