<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="角色名"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_role_view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" v-if="sys_role_add" type="primary"
                     @click="handleAdd"/>
        </el-form-item>
      </el-form>
    </div>
    <!--表格内容栏-->
    <el-table :data="tableData" stripe size="mini" style="width: 100%;" height="300"
              rowKey="roleId" v-loading="loading" :element-loading-text="$t('action.loading')"
              permsDelete="sys:role:delete" :highlightCurrentRow="true"
              :stripe="false"
              @current-change="handleRoleSelectChange">
      <el-table-column
        prop="roleId" header-align="center" align="center" label="ID">
      </el-table-column>
      <el-table-column
        prop="roleName" header-align="center" align="center" label="角色名">
      </el-table-column>
      <el-table-column  header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" v-if="sys_role_edit" @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" v-if="sys_role_del" type="danger"
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
    <!--<kt-table :height="300" permsEdit="sys:role:edit" permsDelete="sys:role:delete" :highlightCurrentRow="true"
              :stripe="false"
              :data="pageResult" :columns="columns" :showBatchDelete="false"
              @handleCurrentChange="handleRoleSelectChange"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete">
    </kt-table>-->
    <!-- </el-col> -->
    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
        <el-form-item label="ID" prop="roleId" >
          <el-input v-model="dataForm.roleId" :disabled=!operation auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色名" prop="roleName">
          <el-input v-model="dataForm.roleName" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <!--角色菜单，表格树内容栏-->
    <div class="menu-container" :v-if="true">
      <div class="menu-header">
        <span><B>角色菜单授权</B></span>
      </div>
      <el-tree :data="menuData" size="mini" show-checkbox node-key="menuId" :props="defaultProps"
               style="width: 100%;pading-top:20px;" ref="menuTree" :render-content="renderContent"
               v-loading="menuLoading" :element-loading-text="$t('action.loading')" :check-strictly="true"
               @check-change="handleMenuCheckChange">
      </el-tree>
      <div style="float:left;padding-left:24px;padding-top:12px;padding-bottom:4px;">
        <el-checkbox v-model="checkAll" @change="handleCheckAll" :disabled="this.selectRole.roleId == null"><b>全选</b>
        </el-checkbox>
      </div>
      <div style="float:right;padding-right:15px;padding-top:4px;padding-bottom:4px;">
        <kt-button :label="$t('action.reset')" v-if="sys_role_edit" type="primary" @click="resetSelection"
                   :disabled="this.selectRole.roleId == null"/>
        <kt-button :label="$t('action.submit')" v-if="sys_role_del" type="primary" @click="submitAuthForm"
                   :disabled="this.selectRole.roleId == null" :loading="authLoading"/>
      </div>
    </div>
  </div>
</template>

<script>
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableTreeColumn from '@/views/Core/TableTreeColumn'
  import {format} from "@/utils/datetime"
  import { hasPermission } from '@/permission/index.js'

  export default {
    components: {
      KtTable,
      KtButton,
      TableTreeColumn,
      hasPermission
    },
    data() {
      return {
        size: 'small',
        filters: {
          name: ''
        },
        columns: [
          {prop: "roleId", label: "ID", minWidth: 50},
          {prop: "roleName", label: "角色名", minWidth: 120},
        ],
        pageRequest: {
          current: 1,
          size: 20
        },
        total: 0,
        tableData:[],

        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          roleId: [
            {required: true, message: '请输入角色ID', trigger: 'blur'}
          ],
          roleName: [
            {required: true, message: '请输入角色名', trigger: 'blur'}
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          roleId: '',
          roleName: '',
        },
        selectRole: {},
        menuData: [],
        menuSelections: [],
        loading: false,
        menuLoading: false,
        authLoading: false,
        checkAll: false,
        currentRoleMenus: [],
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        sys_role_edit: false,
        sys_role_del: false,
        sys_role_add: false,
        sys_role_view: false,
      }
    },created(){
      this.sys_role_edit = hasPermission('sys:role:edit')
      this.sys_role_del = hasPermission('sys:role:del')
      this.sys_role_add = hasPermission('sys:role:add')
      this.sys_role_view = hasPermission('sys:role:view')
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
            this.pageRequest.roleName = this.filters.name
          this.$api.role.findPage(this.pageRequest).then((res) => {
            this.tableData = res.data.records;
            this.total = res.data.total;
            this.pageRequest.current = res.data.current;
            this.pageRequest.size = res.data.size;
            callback(res)
            this.findTreeData()
          }).catch((res) => {
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
              this.$message({message: '删除成功', type: 'success'})
              this.findPage(null)
              this.loading = false
          }
          this.$api.role.batchDelete(data).then(data != null ? callback : '')
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      },
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true
        this.operation = true
        this.dataForm = {
          roleId: '',
          roleName: '',
        }
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.dataForm = Object.assign({}, params)
      },
      // 编辑
      submitForm: function () {
        let callback = res => {
          this.editLoading = false
          this.dialogVisible = false
        }
        this.$refs["dataForm"].validate(valid => {
          if (valid) {
            this.$confirm("确认提交吗？", "提示", {}).then(() => {
              this.editLoading = true;
              let params = Object.assign({}, this.dataForm);
              this.$api.role.save(params).then(res => {
                this.$message({message: "操作成功", type: "success"});
                this.findPage(null)
                this.findTreeData();
                callback(res)
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                callback(res)
              });
            });
          }
        });
      },
      // 获取数据
      findTreeData: function () {
        this.menuLoading = true
        this.$api.menu.findMenuTree().then((res) => {
          this.menuData = res.data
          this.menuLoading = false
        })
      },
      // 角色选择改变监听
      handleRoleSelectChange(val) {
        if (val == null || val.roleId == null) {
          return
        }
        this.selectRole = val
        this.$api.role.findRoleMenus({'roleId': val.roleId}).then((res) => {
          this.currentRoleMenus = res.data
          this.$refs.menuTree.setCheckedNodes(res.data)
        })
      },
      // 树节点选择监听
      handleMenuCheckChange(data, check, subCheck) {
        if (check) {
          // 节点选中时同步选中父节点
          let parentId = data.parentId
          this.$refs.menuTree.setChecked(parentId, true, false)
        } else {
          // 节点取消选中时同步取消选中子节点
          if (data.children != null) {
            data.children.forEach(element => {
              this.$refs.menuTree.setChecked(element.menuId, false, false)
            });
          }
        }
      },
      // 重置选择
      resetSelection() {
        this.checkAll = false
        this.$refs.menuTree.setCheckedNodes(this.currentRoleMenus)
      },
      // 全选操作
      handleCheckAll() {
        if (this.checkAll) {
          let allMenus = []
          this.checkAllMenu(this.menuData, allMenus)
          this.$refs.menuTree.setCheckedNodes(allMenus)
        } else {
          this.$refs.menuTree.setCheckedNodes([])
        }
      },
      // 递归全选
      checkAllMenu(menuData, allMenus) {
        menuData.forEach(menu => {
          allMenus.push(menu)
          if (menu.children) {
            this.checkAllMenu(menu.children, allMenus)
          }
        });
      },
      // 角色菜单授权提交
      submitAuthForm() {
        let roleId = this.selectRole.roleId
        if ('ADMIN' == this.selectRole.roleName) {
          this.$message({message: '超级管理员拥有所有菜单权限，不允许修改！', type: 'error'})
          return
        }
        this.authLoading = true
        let checkedNodes = this.$refs.menuTree.getCheckedNodes(false, true)
        let roleMenus = []
        for (let i = 0, len = checkedNodes.length; i < len; i++) {
          let roleMenu = {roleId: roleId, menuId: checkedNodes[i].menuId}
          roleMenus.push(roleMenu)
        }
        if(checkedNodes.length == 0){
          let roleMenu = {roleId: roleId, menuId: null}
          roleMenus.push(roleMenu)
        }
        this.$api.role.saveRoleMenus(roleMenus).then((res) => {
            this.$message({message: '操作成功', type: 'success'})
          this.authLoading = false
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          this.authLoading = false
        })
      },
      renderContent(h, { node, data, store }) {
        return (
          <div class="column-container">
          <span style="text-algin:center;margin-right:80px;">{data.menuName}</span>
        <span style="text-algin:center;margin-right:80px;">
          <el-tag type={data.type === 0?'':data.type === 1?'success':'info'} size="small">
        {data.type === 0?'目录':data.type === 1?'菜单':'按钮'}
      </el-tag>
        </span>
        <span style="text-algin:center;margin-right:80px;"> <i class={data.icon}></i></span>
        <span style="text-algin:center;margin-right:80px;">{data.parentName?data.parentName:'顶级菜单'}</span>
        <span style="text-algin:center;margin-right:80px;">{data.menuUrl?data.url:'\t'}</span>
        </div>);
      },
      // 时间格式化
      dateFormat: function (row, column, cellValue, index){
        return format(row[column.property])
      },handleCurrentChange(val) {
        this.pageRequest.current = val;
        this.findPage(this.pageRequest);
      }
    },
    mounted() {
      this.findPage(null)
      this.findTreeData()
    }
  }
</script>
<style scoped>
  .menu-container {
    margin-top: 10px;
  }

  .menu-header {
    padding-left: 8px;
    padding-bottom: 5px;
    text-align: left;
    font-size: 16px;
    color: rgb(20, 89, 121);

  }
</style>
