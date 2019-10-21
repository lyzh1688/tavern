<template>
  <div class="page-container" style="margin:0 auto;">
    <el-form :inline="true" :model="filters" align="left">
      <el-form-item label="订单号" label-width="100px">
        <el-input v-model="filters.name" placeholder="请输入订单号"></el-input>
      </el-form-item>
      <el-form-item label="退款业务" label-width="100px">
        <el-input v-model="filters.name" placeholder="请输入退款业务"></el-input>
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
    <div id="mountNode"></div>
    <el-form :inline="true" :model="filters" :size="size" align="left">
      <el-form-item label="审批意见: " label-width="100px">
        <el-input type="textarea" v-model="filters.desc" style="width: 500px"></el-input>
      </el-form-item>
    </el-form>
    <el-form :inline="true" :model="filters" align="left">
      <el-form-item label="是否同意退款" label-width="100px">
        <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
          <el-option label="是" value='0'></el-option>
          <el-option label="否" value='1'></el-option>
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import G6 from '@antv/g6';
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
        dialogImageUrl: '',
        pictureDialogVisible: false,
        nextDialogVisible: false,
        comDialogVisible: false,
        delayDialogVisible: false,
        reChooseDialogVisible: false,
        loading: false,
        columns: [],
        filterColumns: [],
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},
        bakHistory: {},

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
        nextForm: {},
        comForm: {},
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
    mounted() {
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data
        }
        this.pageRequest.columnFilters = {name: {name: 'name', value: this.filters.name}}
        this.$api.outer.findPage(this.pageRequest).then((res) => {
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
        this.$api.outer.batchDelete(data.params).then(data != null ? data.callback : '').catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          this.loading = false
        })
      },
      handleAdd: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      handleEdit: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      showWorkFlow: function () {
        this.$router.push({path: '/preSales/workFlow'})
      },
      handleDelay: function (params) {
        // this.initG6()
        this.delayDialogVisible = true
      },
      handleReChoose: function (params) {
        this.reChooseDialogVisible = true
      },

      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.pictureDialogVisible = true;
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
      handleBak: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.dataForm = Object.assign({}, params)
        this.$api.outer.findBak(params).then((res) => {
          this.bakHistory = res.data
        })
      },
      handleComDtl: function (params) {
        this.comDialogVisible = true
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
      initG6() {
        const data = {
          nodes: [{
            id: 'node1',
            label: '流程开始',
            x: 100,
            y: 100,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node2',
            label: '张三录单',
            x: 100,
            y: 200,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node3',
            label: '李四同意',
            x: 100,
            y: 300,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node4',
            label: '王五代缴社保',
            x: 100,
            y: 400,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node5',
            label: '流程结束',
            x: 100,
            y: 500,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          },],
          edges: [{
            target: 'node2',
            source: 'node1'
          },
            {
              target: 'node3',
              source: 'node2'
            }, {
              target: 'node4',
              source: 'node3'
            }, {
              target: 'node5',
              source: 'node4'
            }]
        };
        const graph = new G6.Graph({
          container: 'mountNode',
          width: 500,
          height: 800,
          nodeStyle: {
            default: {
              stroke: '#096dd9',
            }
          },
          edgeStyle: {
            default: {stroke: '#A3B1BF'}
          }
        });
        graph.read(data);
      },
    },
  }
</script>

<style scoped>

</style>
