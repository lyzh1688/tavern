<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_menu_view" type="primary"
                     @click="findTreeData(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" v-if="sys_menu_add" type="primary"
                     @click="handleAdd"/>
        </el-form-item>
      </el-form>
    </div>
    <!--表格树内容栏-->
    <el-table :data="tableTreeDdata" stripe size="mini" style="width: 100%;" height="600"
              rowKey="menuId" v-loading="loading" :element-loading-text="$t('action.loading')">
      <el-table-column
        prop="menuId" header-align="center" align="center" label="ID">
      </el-table-column>
      <table-tree-column
        prop="menuName" header-align="center" treeKey="id" label="名称">
      </table-tree-column>
      <el-table-column header-align="center" align="center" label="图标">
        <template slot-scope="scope">
          <i :class="scope.row.icon || ''"></i>
        </template>
      </el-table-column>
      <el-table-column prop="type" header-align="center" align="center" label="类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 0" size="small">目录</el-tag>
          <el-tag v-else-if="scope.row.type === 1" size="small" type="success">菜单</el-tag>
          <el-tag v-else-if="scope.row.type === 2" size="small" type="info">按钮</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="parentName" header-align="center" align="center" label="上级菜单">
      </el-table-column>
      <el-table-column
        prop="menuUrl" header-align="center" align="center"
        :show-overflow-tooltip="true" label="菜单URL">
      </el-table-column>
      <el-table-column
        prop="perms" header-align="center" align="center"
        :show-overflow-tooltip="true" label="授权标识">
      </el-table-column>
      <el-table-column
        prop="orderNum" header-align="center" align="center" label="排序">
      </el-table-column>
      <el-table-column  header-align="center" align="center"  :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" v-if="sys_menu_edit" @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" v-if="sys_menu_del" type="danger"
                     @click="handleDelete(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增修改界面 -->
    <el-dialog :title="!dataForm.menuId ? '新增' : '修改'" width="40%" :visible.sync="dialogVisible"
               :close-on-click-modal="false">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="submitForm()"
               label-width="80px" :size="size" style="text-align:left;">
        <el-form-item label="菜单类型" prop="type">
          <el-radio-group v-model="dataForm.type">
            <el-radio v-for="(type, index) in menuTypeList" :label="index" :key="index">{{ type }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item :label="menuTypeList[dataForm.type] + '名称'" prop="menuName">
          <el-input v-model="dataForm.menuName" :placeholder="menuTypeList[dataForm.type] + '名称'"></el-input>
        </el-form-item>
        <el-form-item label="上级菜单" prop="parentName">
          <popup-tree-input
            :data="popupTreeData" :props="popupTreeProps"
            :prop="dataForm.parentName==null||dataForm.parentName==''?'顶级菜单':dataForm.parentName"
            :nodeKey="''+dataForm.parentId" :currentChangeHandle="handleTreeSelectChange">
          </popup-tree-input>
        </el-form-item>
        <el-form-item v-if="dataForm.type !== 0" label="授权标识" prop="perms">
          <el-input v-model="dataForm.perms" placeholder="如: sys:user:add, sys:user:edit, sys:user:delete"></el-input>
        </el-form-item>
        <el-form-item v-if="dataForm.type === 1" label="菜单路由" prop="menuUrl">
          <el-row>
            <el-col :span="22">
              <el-input v-model="dataForm.menuUrl" placeholder="菜单路由"></el-input>
            </el-col>
            <el-col :span="2" class="icon-list__tips">
              <el-tooltip placement="top" effect="light" style="padding: 10px;">
                <div slot="content">
                  <p>URL格式：</p>
                  <p>1.常规业务开发的功能URL，如用户管理，Views目录下页面路径为 /Sys/User, 此处填写 /sys/user。</p>
                  <p>2.嵌套外部网页，如通过菜单打开百度网页，此处填写 http://www.baidu.com，http:// 不可省略。</p>
                  <p>示例：用户管理：/sys/user 嵌套百度：http://www.baidu.com 嵌套网页：http://127.0.0.1:8000</p></div>
                <i class="el-icon-warning"></i>
              </el-tooltip>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item v-if="dataForm.type !== 2" label="排序编号" prop="orderNum">
          <el-input-number v-model="dataForm.orderNum" controls-position="right" :min="0"
                           label="排序编号"></el-input-number>
        </el-form-item>
        <el-form-item v-if="dataForm.type !== 2" label="菜单图标" prop="icon">
          <el-row>
            <el-col :span="22">
              <!-- <el-popover
                ref="iconListPopover"
                placement="bottom-start"
                trigger="click"
                popper-class="mod-menu__icon-popover">
                <div class="mod-menu__icon-list">
                  <el-button
                    v-for="(item, index) in dataForm.iconList"
                    :key="index"
                    @click="iconActiveHandle(item)"
                    :class="{ 'is-active': item === dataForm.icon }">
                    <icon-svg :name="item"></icon-svg>
                  </el-button>
                </div>
              </el-popover> -->
              <el-input v-model="dataForm.icon" v-popover:iconListPopover :readonly="false"
                        placeholder="菜单图标名称（此处使用ELement官方图标）" class="icon-list__input"></el-input>
            </el-col>
            <el-col :span="2" class="icon-list__tips">
              <fa-icon-tooltip/>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button :size="size" @click="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click="submitForm()">{{$t('action.comfirm')}}</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import KtButton from "@/views/Core/KtButton";
  import TableTreeColumn from "@/views/Core/TableTreeColumn";
  import PopupTreeInput from "@/components/PopupTreeInput";
  import FaIconTooltip from "@/components/FaIconTooltip";
  import {hasPermission} from '@/permission/index.js'

  export default {
    components: {
      PopupTreeInput,
      KtButton,
      TableTreeColumn,
      FaIconTooltip
    },
    data() {
      return {
        size: "small",
        loading: false,
        filters: {
          name: ""
        },
        tableTreeDdata: [],
        dialogVisible: false,
        menuTypeList: ["目录", "菜单", "按钮"],
        dataForm: {
          menuId: 0,
          type: 1,
          menuName: "",
          parentId: 0,
          parentName: "",
          menuUrl: "",
          perms: "",
          orderNum: 0,
          icon: "",
          iconList: []
        },
        dataRule: {
          menuName: [{required: true, message: "菜单名称不能为空", trigger: "blur"}]
        },
        popupTreeData: [],
        popupTreeProps: {
          label: "menuName",
          children: "children"
        },
        sys_menu_edit: false,
        sys_menu_del: false,
        sys_menu_add: false,
        sys_menu_view: false,
      };
    },
    created() {
      this.sys_menu_edit = hasPermission('sys:menu:edit')
      this.sys_menu_del = hasPermission('sys:menu:del')
      this.sys_menu_add = hasPermission('sys:menu:add')
      this.sys_menu_view = hasPermission('sys:menu:view')
    },
    methods: {
      // 获取数据
      findTreeData: function () {
        this.loading = true;
        this.$api.menu.findMenuTree().then(res => {
          this.tableTreeDdata = res.data;
          this.popupTreeData = this.getParentMenuTree(res.data);
          this.loading = false;
        }).catch((res) => {
          this.loading = false;
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        });
      }
      ,
      // 获取上级菜单树
      getParentMenuTree: function (tableTreeDdata) {
        let parent = {
          parentId: 0,
          menuName: "顶级菜单",
          children: tableTreeDdata
        };
        return [parent];
      }
      ,
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true;
        this.dataForm = {
          menuId: 0,
          type: 1,
          typeList: ["目录", "菜单", "按钮"],
          menuName: "",
          parentId: 0,
          parentName: "",
          menuUrl: "",
          perms: "",
          orderNum: 0,
          icon: "",
          iconList: []
        };
      }
      ,
      // 显示编辑界面
      handleEdit: function (row) {
        this.dialogVisible = true;
        Object.assign(this.dataForm, row);
      }
      ,
      // 删除
      handleDelete(row) {
        this.$confirm("确认删除选中记录吗？", "提示", {
          type: "warning"
        }).then(() => {
          let params = this.getDeleteIds([], row);
          this.$api.menu.batchDelete(params).then(res => {
            this.findTreeData();
            this.$message({message: "删除成功", type: "success"});
          });
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        });
      }
      ,
      // 获取删除的包含子菜单的id列表
      getDeleteIds(ids, row) {
        ids.push({menuId: row.menuId});
        if (row.children != null) {
          for (let i = 0, len = row.children.length; i < len; i++) {
            this.getDeleteIds(ids, row.children[i]);
          }
        }
        return ids;
      }
      ,
      // 菜单树选中
      handleTreeSelectChange(data, node) {
        this.dataForm.parentId = data.menuId;
        this.dataForm.parentName = data.menuName;
      }
      ,
      // 图标选中
      iconActiveHandle(iconName) {
        this.dataForm.icon = iconName;
      }
      ,
      // 表单提交
      submitForm() {
        let callback = res => {
          this.editLoading = false
          this.dialogVisible = false
        }
        this.$refs["dataForm"].validate(valid => {
          if (valid) {
            this.$confirm("确认提交吗？", "提示", {}).then(() => {
              this.editLoading = true;
              let params = Object.assign({}, this.dataForm);
              this.$api.menu.save(params).then(res => {
                this.$message({message: "操作成功", type: "success"});
                this.findTreeData();
                callback(res)
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                callback(res)
              });
            });
          }
        });
      }
    }
    ,
    mounted() {
      this.findTreeData();
    }
  }
  ;
</script>

<style scoped>
</style>
