<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.thirdPartyName" placeholder="合作方姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.province" placeholder="省份"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.city" placeholder="城市"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="filters.district" placeholder="地区"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_thirdParty_view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" v-if="sys_thirdParty_add" type="primary"
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
              rowKey="thirdPartyId" v-loading="loading" :element-loading-text="$t('action.loading')"
              :stripe="false">
      <el-table-column
        prop="thirdPartyId" header-align="center" align="center" label="ID" v-if="false">
      </el-table-column>
      <el-table-column
        prop="thirdPartyName" header-align="center" align="center" label="合作方姓名">
      </el-table-column>
      <el-table-column
        prop="province" header-align="center" align="center" label="省份">
      </el-table-column>
      <el-table-column
        prop="city" header-align="center" align="center" label="城市">
      </el-table-column>
      <el-table-column
        prop="district" header-align="center" align="center" label="区域">
      </el-table-column>
      <el-table-column
        prop="address" header-align="center" align="center" width="300px" label="联系地址">
      </el-table-column>
      <el-table-column
        prop="deliveryAddr" header-align="center" align="center" width="300px" label="快递地址">
      </el-table-column>
      <el-table-column
        prop="contact1" header-align="center" align="center" label="合作方联系人1姓名">
      </el-table-column>
      <el-table-column
        prop="contactNumber1" header-align="center" align="center" label="合作方联系人1电话">
      </el-table-column>
      <el-table-column
        prop="contactWechat1" header-align="center" align="center" label="合作方联系人1微信号">
      </el-table-column>
      <el-table-column
        prop="contact2" header-align="center" align="center" label="合作方联系人2姓名">
      </el-table-column>
      <el-table-column
        prop="contactNumber2" header-align="center" align="center" label="合作方联系人2电话">
      </el-table-column>
      <el-table-column
        prop="contactWechat2" header-align="center" align="center" label="合作方联系人2微信号">
      </el-table-column>
      <el-table-column header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" v-if="sys_thirdParty_edit"
                     @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" v-if="sys_thirdParty_del" type="danger"
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
        <el-form-item label="ID" prop="thirdPartyId" label-width="100px" v-if="false">
          <el-input v-model="dataForm.thirdPartyId" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方姓名"  label-width="100px" prop="thirdPartyName">
          <el-input v-model="dataForm.thirdPartyName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择地区" label-width="100px" prop="area">
          <el-cascader
            ref="cascaderAddr"
            v-model="dataForm.area"
            placeholder="请选择地区"
            :props="{ expandTrigger: 'hover' }"
            :options="areaData"
            style="float: left"
            @change="handleItemChange"
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="联系地址"  label-width="100px" prop="address">
          <el-input v-model="dataForm.address" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="快递地址" label-width="100px" prop="deliveryAddr">
          <el-input v-model="dataForm.deliveryAddr" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方联系人1姓名" label-width="100px" prop="contact1">
          <el-input v-model="dataForm.contact1" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方联系人1电话" label-width="100px" prop="contactNumber1">
          <el-input v-model="dataForm.contactNumber1" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方联系人1微信号" label-width="100px" prop="contactWechat1">
          <el-input v-model="dataForm.contactWechat1" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方联系人2姓名" label-width="100px" prop="contact1">
          <el-input v-model="dataForm.contact2" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方联系人2电话" label-width="100px" prop="contactNumber1">
          <el-input v-model="dataForm.contactNumber2" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="合作方联系人2微信号" label-width="100px" prop="contactWechat1">
          <el-input v-model="dataForm.contactWechat2" auto-complete="off"></el-input>
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
  import AreaJson from "@/utils/area.json"
  import {hasPermission} from '@/permission/index.js'

  export default {
    name: "ThirdPartyInfo",
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
        areaData: [],
        loading: false, // true:新增, false:编辑
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          thirdPartyName: [
            {required: true, message: '请输入合作方姓名', trigger: 'blur'}
          ],
          area: [
            {required: true, message: '请选择区域', trigger: 'blur'}
          ],
        },
        // 新增编辑界面数据
        dataForm: {
          thirdPartyId: '',
          thirdPartyName: '',
          area:'',
          address: '',
          deliveryAddr: '',
          contact1: '',
          contactNumber1: '',
          contactWechat1: '',
          contact2: '',
          contactNumber2: '',
          contactWechat2: '',
        },
        cascaderAddr: [],
        editAreaLabel: '',
        areaLabel: '',
        sys_thirdParty_edit: false,
        sys_thirdParty_del:false,
        sys_thirdParty_add:false,
        sys_thirdParty_view:false,
      }
    }, created() {
      this.sys_thirdParty_edit = hasPermission('sys:thirdParty:edit')
      this.sys_thirdParty_del = hasPermission('sys:thirdParty:del')
      this.sys_thirdParty_add = hasPermission('sys:thirdParty:add')
      this.sys_thirdParty_view = hasPermission('sys:thirdParty:view')

      //初始化区域信息
      this.areaData = AreaJson
      for (let i = 0; i < this.areaData.length; i++) {
        if (this.areaData[i].children == undefined || this.areaData[i].children.length == 0) {
          delete this.areaData[i].children //解决因为省级区域没有下级市的BUG
        }
      }
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

        this.pageRequest.thirdPartyName = this.filters.thirdPartyName
        this.pageRequest.province = this.filters.province
        this.pageRequest.city = this.filters.city
        this.pageRequest.district = this.filters.district
        this.$api.thirdParty.findPage(this.pageRequest).then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageRequest.current = res.data.current;
          this.pageRequest.size = res.data.size;
          callback(res)
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
            this.loading = false
            this.findPage(null)
          }
          let request = {};
          request.thirdPartyId = data.thirdPartyId
          this.$api.thirdParty.deleteOne(request).then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            this.loading = false
          })
        })
      },
      // 显示新增界面
      handleAdd: function () {
        this.dataForm = {
          thirdPartyId: '',
          thirdPartyName: '',
          area:'',
          address: '',
          deliveryAddr: '',
          contact1: '',
          contactNumber1: '',
          contactWechat1: '',
          contact2: '',
          contactNumber2: '',
          contactWechat2: '',
        }
        this.dialogVisible = true
        this.operation = true
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.operation = false
        this.dataForm = Object.assign({}, params)
        this.getTargetArea(params.district, this.areaData)
        this.dataForm.area = this.editAreaLabel;
        this.dialogVisible = true
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
            if (valid) {
              this.$confirm('确认提交吗？', '提示', {}).then(() => {
                this.editLoading = true
                let params = Object.assign({}, this.dataForm)
                params.area = this.$refs.cascaderAddr.getCheckedNodes()[0].pathLabels;
                this.$api.thirdParty.save(params).then((res) => {
                  this.editLoading = false
                  this.$message({message: '操作成功', type: 'success'})
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                  this.findPage(null)
                }).catch(res => {
                  this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                  this.editLoading = false
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                })
              })
            }
          }
        )
      },
      handleItemChange() {
        this.areaLabel = this.$refs.cascaderAddr.getCheckedNodes()[0].pathLabels
      },
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
      getTargetArea(district, options) {
        for (let i = 0; i < options.length; i++) {
          if (options[i].children == undefined || options[i].children.length == 0) {
            continue
          }
          let secLevel = options[i].children;
          for (let j = 0; j < secLevel.length; j++) {
            let thirdLevel = secLevel[j].children
            for (let k = 0; k < thirdLevel.length; k++) {
              let thirdItem = thirdLevel[k]
              if (district === thirdItem.label) {
                this.editAreaLabel = thirdItem.value;
                return
              }
            }
          }
        }
      },
      handleCurrentChange(val) {
        this.pageRequest.current = val;
        this.findPage(this.pageRequest);
      }
    },
    mounted() {
      this.findPage(null)
    }
  }
</script>

<style scoped>

</style>
