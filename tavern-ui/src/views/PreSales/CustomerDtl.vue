<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="dtlFrom" :size="size" align="left">
        <el-form-item label="法人姓名" label-width="100px">
          <el-input v-model="dtlFrom.corporation" placeholder="请输入法人姓名" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="法人电话" label-width="100px">
          <el-input v-model="dtlFrom.corporationNumber" placeholder="请输入法人电话" disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlFrom" :size="size" align="left">
        <el-form-item label="旺旺账号" label-width="100px">
          <el-input v-model="dtlFrom.wangwangAccnt" placeholder="请输入旺旺账号" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="微信账号" label-width="100px">
          <el-input v-model="dtlFrom.weixinAccnt" placeholder="请输入微信账号" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="微信昵称" label-width="100px">
          <el-input v-model="dtlFrom.weixinName" placeholder="请输入微信昵称" disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlFrom" :size="size" align="left">
        <el-form-item label="联系人姓名" label-width="100px">
          <el-input v-model="dtlFrom.contactPerson" placeholder="请输入联系人姓名" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="联系人电话" label-width="100px">
          <el-input v-model="dtlFrom.contactNumber" placeholder="请输入联系人电话" disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlFrom" :size="size" align="left">
        <el-form-item label="客户姓名" label-width="100px" prop="customName">
          <el-input v-model="dtlFrom.customName" placeholder="请输入客户姓名" disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="客户等级" label-width="100px">
          <el-input v-model="dtlFrom.customLevel" clearable auto-complete="off" placeholder="请选择客户等级" disabled="true">
          </el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlFrom" :size="size" align="left">
        <!--  <el-form-item>
            <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:role:view" type="primary"
                       @click="findPage(null)"/>
          </el-form-item>-->
        <el-form-item>
          <kt-button icon="fa fa-plus" label="新增公司信息" perms="sys:user:add" type="primary"
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
        dtlFrom: {
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
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},
        dtlParams: {},
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
    }, created() {
      this.dtlFrom = this.$route.params;
      let tmpInfo = JSON.parse(localStorage.getItem("customerDtl"));
      if (this.dtlFrom.customId == undefined || this.dtlFrom.customId == null) {
        if (tmpInfo.customId == undefined || tmpInfo.customId == null) {
          this.$router.push({name: "客户信息"})
          return
        } else {
          this.dtlFrom = tmpInfo;
        }
      }
      localStorage.setItem("customerDtl", JSON.stringify(this.dtlFrom));

    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.pageRequest.columndtlFrom = {name: {name: 'name', value: this.dtlFrom.name}}
        this.$api.customer.findCustomerPage(this.pageRequest).then((res) => {
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
        this.$api.customer.batchDelete(data.params).then(data != null ? data.callback : '')
      },
      // 显示新增界面
      handleAdd: function () {
        this.$router.push({path: '/preSales/companyDtl'})
      },
      /*handleAdd: function () {
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
          {prop: "id", label: "公司ID", minWidth: 50, hidden: true},
          {prop: "name", label: "公司名称", minWidth: 120},
          {prop: "city", label: "所在市", minWidth: 120},
          {prop: "district", label: "所在区", minWidth: 100},
          {prop: "createTime", label: "创建时间", minWidth: 70},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
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
