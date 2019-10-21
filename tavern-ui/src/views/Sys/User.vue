<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_user_view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" v-if="sys_user_add" type="primary"
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
    <el-table :data="tableData" stripe size="mini" style="width: 100%;" :height="600"
              rowKey="userId" v-loading="loading" :element-loading-text="$t('action.loading')"
              :stripe="false">
      <el-table-column
        prop="userId" header-align="center" align="center" label="ID">
      </el-table-column>
      <el-table-column
        prop="userAccnt" header-align="center" align="center" label="用户登录账户">
      </el-table-column>
      <el-table-column
        prop="userName" header-align="center" align="center" label="用户名">
      </el-table-column>
      <el-table-column
        prop="roleNames" header-align="center" align="center" label="角色">
      </el-table-column>
      <el-table-column
        prop="updateDate" header-align="center" align="center" label="更新时间">
      </el-table-column>
      <el-table-column  header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" v-if="sys_user_edit" @click="handleEdit(scope)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" v-if="sys_user_del" type="danger"
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
    <el-dialog :title="operation?'新增':'编辑'" width="30%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="120px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.userId" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户登录账户" prop="userAccnt">
          <el-input v-model="dataForm.userAccnt" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="dataForm.userName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="dataForm.password" auto-complete="off" show-password></el-input>
        </el-form-item>
        <!--<el-form-item label="机构" prop="deptName">
                  <popup-tree-input
                      :data="deptData"
                      :props="deptTreeProps"
                      :prop="dataForm.deptName"
                      :nodeKey="''+dataForm.deptId"
                      :currentChangeHandle="deptTreeCurrentChangeHandle">
                  </popup-tree-input>
              </el-form-item>-->
        <el-form-item label="角色" prop="userRoles" v-if="!operation">
          <el-select v-model="dataForm.userRoles" multiple placeholder="请选择"
                     style="width: 100%;">
            <el-option v-for="item in roles" :key="item.roleId"
                       :label="item.roleName" :value="item.roleId">
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
        columns: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20
        },
        total: 0,
        tableData: [],

        loading: false, // true:新增, false:编辑
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          userAccnt: [
            {required: true, message: '请输入用户登录账户', trigger: 'blur'}
          ],
          userName: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          userId: '',
          userAccnt: '',
          userName: '',
          password: '123456',
          userRoles: []
        },
        deptData: [],
        deptTreeProps: {
          label: 'name',
          children: 'children'
        },
        roles: [],
        sys_user_edit: false,
        sys_user_del: false,
        sys_user_add: false,
        sys_user_view: false,
      }
    }, created() {
      this.sys_user_edit = hasPermission('sys:user:edit')
      this.sys_user_del = hasPermission('sys:user:del')
      this.sys_user_add = hasPermission('sys:user:add')
      this.sys_user_view = hasPermission('sys:user:view')
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
        this.pageRequest.userName = this.filters.name
        this.$api.user.findPage(this.pageRequest).then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageRequest.current = res.data.current;
          this.pageRequest.size = res.data.size;
          this.findUserRoles()
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        })
      },
      findUserRoles: function () {
        this.$api.role.findAll().then((res) => {
          this.roles = res.data
        })
      },
      // 批量删除
      handleDelete: function (data) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.loading = true
          let callback = res => {
            this.$message({message: '删除成功', type: 'success'})
            this.loading = false
            this.findPage(null)
          }
          let ids = []
          ids.push(data.userId)
          this.$api.user.batchDelete(ids).then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            this.loading = false
          })
        })
      },
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true
        this.operation = true
        this.dataForm = {
          userId: '',
          userAccnt: '',
          userName: '',
          password: '123456',
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
                  let callback = res => {
                    this.editLoading = false
                    this.dialogVisible = false
                  }
                  let params = Object.assign({}, this.dataForm)
                  let userRoles = []
                  for (let i = 0, len = params.userRoles.length; i < len; i++) {
                    if (params.userRoles[i] == undefined || params.userRoles[i] == null) {
                      continue
                    }
                    let userRole = {
                      userId: params.userId,
                      roleId: params.userRoles[i]
                    }
                    userRoles.push(userRole)
                  }
                  params.userRoles = userRoles
                  this.$api.user.save(params).then((res) => {
                    this.$message({message: '操作成功', type: 'success'})
                    callback(res)
                    this.findPage(null)
                  }).catch((res) => {
                    this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                    callback(res)
                  });
                }
              )
            }
          }
        )
      },
      /*// 获取部门列表
      findDeptTree: function () {
        this.$api.dept.findDeptTree().then((res) => {
          this.deptData = res.data
        })
      },*/
      // 菜单树选中
      deptTreeCurrentChangeHandle(data, node) {
        this.dataForm.deptId = data.id
        this.dataForm.deptName = data.name
      }
      ,
      // 时间格式化
      dateFormat: function (row, column, cellValue, index) {
        return format(row[column.property])
      }
      ,
      // 处理表格列过滤显示
      displayFilterColumnsDialog: function () {
        this.$refs.tableColumnFilterDialog.setDialogVisible(true)
      }
      ,
      // 处理表格列过滤显示
      handleFilterColumns: function (data) {
        this.filterColumns = data.filterColumns
        this.$refs.tableColumnFilterDialog.setDialogVisible(false)
      }
      ,
      // 处理表格列过滤显示
      initColumns: function () {
        this.columns = [
          {prop: "userId", label: "ID", minWidth: 50},
          {prop: "userAccnt", label: "用户账户", minWidth: 120},
          {prop: "userName", label: "用户名", minWidth: 120},
          // {prop:"deptName", label:"机构", minWidth:120},
          {prop: "roleNames", label: "角色", minWidth: 100},
          {prop: "updateDate", label: "更新时间", minWidth: 100},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      }
      ,
      handleCurrentChange(val) {
        this.pageRequest.current = val;
        this.findPage(this.pageRequest);
      }
    },
    mounted() {
      // this.findDeptTree()
      this.initColumns()
      this.findPage(null)
    }
  }
</script>

<style scoped>

</style>
