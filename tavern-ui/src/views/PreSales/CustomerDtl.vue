<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="法人姓名" label-width="100px">
          <el-input v-model="dtlForm.corporation" placeholder="请输入法人姓名" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="法人电话" label-width="100px">
          <el-input v-model="dtlForm.corporationNumber" placeholder="请输入法人电话" :readonly=true></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="旺旺账号" label-width="100px">
          <el-input v-model="dtlForm.wangwangAccnt" placeholder="请输入旺旺账号" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="微信账号" label-width="100px">
          <el-input v-model="dtlForm.weixinAccnt" placeholder="请输入微信账号" :readonly=true></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="微信昵称" label-width="100px">
          <el-input v-model="dtlForm.weixinName" placeholder="请输入微信昵称" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="客户姓名" label-width="100px" prop="customName">
          <el-input v-model="dtlForm.customName" placeholder="请输入客户姓名" :readonly=true></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="联系人姓名" label-width="100px">
          <el-input v-model="dtlForm.contactPerson" placeholder="请输入联系人姓名" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="联系人电话" label-width="100px">
          <el-input v-model="dtlForm.contactNumber" placeholder="请输入联系人电话" :readonly=true></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="客户等级" label-width="100px">
          <el-input v-model="dtlForm.customLevel" clearable auto-complete="off" placeholder="请选择客户等级" :readonly=true>
          </el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item>
          <kt-button icon="fa fa-plus" label="新增公司信息" v-if="sys_presales_customer_dtl_add" type="primary"
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
    <el-table :data="tableData" stripe stripe height="400" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="companyId" header-align="center" align="center" label="公司ID" v-if="false">
      </el-table-column>
      <el-table-column prop="companyName" label="公司名称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="province" label="所在省份" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="city" label="所在市" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="district" label="所在区" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="updateDate" label="创建时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column  label="操作" header-align="center" align="center" width="500"
                       v-if="sys_presales_customer_dtl_edit || sys_presales_customer_dtl_view || sys_presales_customer_dtl_del">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" label="修改公司信息" v-if="sys_presales_customer_dtl_edit" type="primary"
                     @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-retweet" label="公司信息详情" v-if="sys_presales_customer_dtl_view" type="primary"
                     @click="handleDtl(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger" v-if="sys_presales_customer_dtl_del"
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
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false"
               :before-close="handleDialogClose">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item label="客户ID" label-width="100px" v-if="editShow" prop="customId">
          <el-input v-model="dataForm.customId"></el-input>
        </el-form-item>
        <el-form-item label="公司ID" label-width="100px" v-if="editShow" prop="companyId">
          <el-input v-model="dataForm.companyId"></el-input>
        </el-form-item>
        <el-form-item label="公司名称" label-width="100px" prop="companyName">
          <el-input v-model="dataForm.companyName" placeholder="请输入公司名称"></el-input>
        </el-form-item>
        <el-form-item label="纳税类型" label-width="100px" prop="taxType">
          <el-select v-model="dataForm.taxType" clearable auto-complete="off" placeholder="请选择纳税类型" style="float: left">
            <el-option label="小规模纳税人" value='0'></el-option>
            <el-option label="一般纳税人" value='1'></el-option>
          </el-select>
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
        <el-form-item label="金税盘种类" label-width="100px" prop="financeDiskType">
          <el-select v-model="dataForm.financeDiskType" clearable auto-complete="off" placeholder="请选择金税盘种类"
                     style="float: left">
            <el-option label="百旺" value='0'></el-option>
            <el-option label="航天" value='1'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="税率" label-width="100px" prop="taxRate">
          <el-input v-model="dataForm.taxRate" placeholder="请输入税率"></el-input>
        </el-form-item>
        <el-form-item label="银行列表" label-width="100px" prop="banks">
          <el-checkbox-group v-model="dataForm.banks" @change="handleCheckedBanksChange">
            <el-checkbox v-for="bank in dataForm.allBanks" :label="bank" :key="bank" style="float: left">{{bank}}
            </el-checkbox>
          </el-checkbox-group>
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

  const bankOptions = ['工商银行', '招商银行', '建设银行', '中国银行', '农业银行', '交通银行', '中信银行', '光大银行', '民生银行', '上海银行']
  export default {
    components: {
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog,
      AreaJson
    },
    data() {
      return {
        size: 'small',
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
        areaData: [],
        columns: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20,
        },
        total: 0,
        tableData: [],
        dtlParams: {},
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          companyName: [
            {required: true, message: '请填写公司名称', trigger: 'blur'}
          ],
          taxType: [
            {required: true, message: '请选择纳税类型', trigger: 'blur'}
          ],
          area: [
            {required: true, message: '请选择区域信息', trigger: 'blur'}
          ],
        },
        loading: false,
        editShow: false,
        // 新增编辑界面数据
        dataForm: {
          customId: '',
          companyId: '',
          companyName: '',
          taxType: '',
          area: '',
          financeDiskType: '',
          taxRate: '',
          banks: [],
          allBanks: bankOptions
        },
        cascaderAddr: [],
        editAreaLabel: '',
        areaLabel: '',
        taxTypeRef: '',
        sys_presales_customer_dtl_add:false,
        sys_presales_customer_dtl_edit: false,
        sys_presales_customer_dtl_view: false,
        sys_presales_customer_dtl_del: false,

      }
    }, created() {
      //初始化客户信息
      this.sys_presales_customer_dtl_add = hasPermission('sys:presales:customer:dtl:add')
      this.sys_presales_customer_dtl_edit = hasPermission('sys:presales:customer:dtl:edit')
      this.sys_presales_customer_dtl_view = hasPermission('sys:presales:customer:dtl:view')
      this.sys_presales_customer_dtl_del = hasPermission('sys:presales:customer:dtl:del')
      this.dtlForm = this.$route.params;
      let tmpInfo = JSON.parse(localStorage.getItem("customerDtl"));
      if (this.dtlForm.customId == undefined || this.dtlForm.customId == null) {
        if (tmpInfo.customId == undefined || tmpInfo.customId == null) {
          this.$router.push({name: "客户信息"})
          return
        } else {
          this.dtlForm = tmpInfo;
        }
      }
      localStorage.setItem("customerDtl", JSON.stringify(this.dtlForm));

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
        this.pageRequest.customId = this.dtlForm.customId;
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        this.$api.customer.findCompanyPage(this.pageRequest).then((res) => {
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
            if (res.retCode == 0) {
              this.$message({message: '删除成功', type: 'success'})
              this.findPage(null)
            } else {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            }
            this.loading = false
          }
          let customCompanyRel = {};
          customCompanyRel.companyId = data.companyId;
          customCompanyRel.customId = data.customId;
          let rels = []
          rels.push(customCompanyRel)
          this.$api.customer.batchDeleteCompany(rels).then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'});
            this.loading = false
          })
        })
      },
      handleDialogClose: function () {
        this.$refs['dataForm'].resetFields()
        this.dialogVisible = false
      },
      handleAdd: function () {
        this.dataForm = {
          customId: '',
          companyId: '',
          companyName: '',
          taxType: '',
          area: '',
          financeDiskType: '',
          taxRate: '',
          banks: [],
          allBanks: bankOptions
        }
        this.dialogVisible = true
        this.operation = true

      },
      handleDtl: function (params) {
        params.allBanks = this.dataForm.allBanks;
        this.getTargetArea(params.district, this.areaData)
        params.area = this.editAreaLabel;
        params.taxType = this.handleTaxType(params.taxType)
        params.financeDiskType = this.handleFinanceDisType(params.financeDiskType)
        this.$router.push({name: '公司详情', params: params})
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.operation = false
        this.dataForm = Object.assign({}, params)
        this.getTargetArea(params.district, this.areaData)
        this.dataForm.area = this.editAreaLabel;
        this.dataForm.allBanks = bankOptions;
        this.dialogVisible = true
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              params.customId = this.dtlForm.customId;
              params.area = this.$refs.cascaderAddr.getCheckedNodes()[0].pathLabels;
              this.$api.customer.saveCompany(params).then((res) => {
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
        })
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
      handleItemChange() {
        this.areaLabel = this.$refs.cascaderAddr.getCheckedNodes()[0].pathLabels
      },
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
        let _this = this;
        _this.pageRequest.current = val;
        _this.findPage(_this.pageRequest);
      }, handleCheckedBanksChange(val) {
      }, handleTaxType(val) {
        switch (val) {
          case '0':
            return '小规模纳税人';
          case '1':
            return '一般纳税人';
        }
      }, handleFinanceDisType(val) {
        switch (val) {
          case '0':
            return '百旺';
          case '1':
            return '航天';
        }
      }
    },
    mounted() {
      this.findPage(null)
    }
  }
</script>

<style scoped>

</style>
