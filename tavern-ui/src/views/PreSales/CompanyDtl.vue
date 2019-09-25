<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="公司名称" label-width="100px">
          <el-input v-model="dtlForm.companyName" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="纳税类型" label-width="100px" prop="taxType">
          <el-input v-model="dtlForm.taxType" clearable auto-complete="off" style="float: left" :readonly="true">
          </el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="所在地区" label-width="100px" prop="area">
          <el-cascader
            ref="cascaderAddr"
            v-model="dtlForm.area"
            :props="{ expandTrigger: 'hover' }"
            :options="areaData"
            style="float: left"
            :readonly="true"
          >
          </el-cascader>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="金税盘种类" label-width="100px">
          <el-input v-model="dtlForm.financeDiskType" clearable auto-complete="off" :readonly="true">
          </el-input>
        </el-form-item>
        <el-form-item label="税率" label-width="100px" prop="taxRate">
          <el-input v-model="dtlForm.taxRate" :readonly="true"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="false" :model="dtlForm" :size="size" align="left">
        <el-form-item label="银行列表" label-width="100px" prop="banks">
          <el-checkbox-group v-model="dtlForm.banks" :disabled="true">
            <el-checkbox v-for="bank in dtlForm.allBanks" :label="bank" :key="bank" style="float: left">{{bank}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
    </div>
    <el-row>
      <el-col :span="24" class="main-top">
        <p><span class="short-line"></span>在办业务
        </p>
      </el-col>
    </el-row>
    <el-table :data="bizTableData" stripe stripe height="200" size="mini" style="width: 100%;"
              v-loading="bizLoading">
      <el-table-column prop="companyId" header-align="center" align="center" label="公司ID" v-if="false">
      </el-table-column>
      <el-table-column prop="businessId" header-align="center" align="center" label="业务ID" v-if="false">
      </el-table-column>
      <el-table-column prop="businessName" label="业务类型" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="beginDate" label="开始时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="endDate" label="到期时间" header-align="center" align="center">
      </el-table-column>
      <!--<el-table-column  label="操作" header-align="center" align="center" width="500">
        <template slot-scope="scope">
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger"
                     @click="handleDeleteBiz(scope.row)"/>
        </template>
      </el-table-column>-->
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleBizCurrentChange"
                     :current-page="bizPageRequest.current" :page-size="bizPageRequest.size" :total="bizTotal"
                     style="float:right;">
      </el-pagination>
    </div>
    <el-row>
      <el-col :span="24" class="main-top">
        <p><span class="short-line"></span>员工信息
        </p>
      </el-col>
    </el-row>
    <div class="toolbar" style="padding:10px;float: right">
      <kt-button icon="fa fa-plus" label="新增员工" v-if="sys_presales_company_staff_add" type="primary"
                 @click="handleStaffAdd"/>
    </div>
    <el-table :data="staffTableData" stripe stripe height="400" size="mini" style="width: 100%;"
              v-loading="staffLoading">
      <el-table-column prop="companyId" header-align="center" align="center" label="公司ID" v-if="false">
      </el-table-column>
      <el-table-column prop="staffId" header-align="center" align="center" label="员工ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="姓名" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="idCard" label="身份证" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="needSocialIns" label="缴纳社保" header-align="center" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.needSocialIns == '1'">是</span>
          <span v-if="scope.row.needSocialIns == '0'">否</span>
        </template>
      </el-table-column>
      <el-table-column prop="needHabitationIns" label="缴纳公积金" header-align="center" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.needHabitationIns == '1'">是</span>
          <span v-if="scope.row.needHabitationIns == '0'">否</span>
        </template>
      </el-table-column>
      <el-table-column prop="needHabitationServer" label="公积金代缴" header-align="center" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.needHabitationServer == '0'">不需要</span>
          <span v-if="scope.row.needHabitationServer == '1'">需要</span>
          <span v-if="scope.row.needHabitationServer == '2'">代开中</span>
        </template>
      </el-table-column>
      <el-table-column prop="needSocialServer" label="社保代缴" header-align="center" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.needSocialServer == '0'">不需要</span>
          <span v-if="scope.row.needSocialServer == '1'">需要</span>
          <span v-if="scope.row.needSocialServer == '2'">代开中</span>
        </template>
      </el-table-column>
      <el-table-column  label="操作" header-align="center" align="center" width="500"
                       v-if="sys_presales_company_staff_edit ||sys_presales_company_staff_del ">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" label="修改员工" v-if="sys_presales_company_staff_edit" type="primary"
                     @click="handleStaffEdit(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger" v-if="sys_presales_company_staff_del"
                     @click="handleStaffDelete(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleStaffCurrentChange"
                     :current-page="staffPageRequest.current" :page-size="staffPageRequest.size" :total="staffTotal"
                     style="float:right;">
      </el-pagination>
    </div>
    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item label="公司ID" prop="companyId" v-if="false">
          <el-input v-model="dataForm.companyId" :readonly="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="员工ID" prop="staffId" v-if="editShow" label-width="100px">
          <el-input v-model="dataForm.staffId" :readonly="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name" label-width="100px">
          <el-input v-model="dataForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="身份证" prop="idCard" label-width="100px">
          <el-input v-model="dataForm.idCard" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactNumber" label-width="100px">
          <el-input v-model="dataForm.contactNumber" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="缴纳社保" prop="needSocialIns" label-width="100px">
          <el-select v-model="dataForm.needSocialIns" clearable auto-complete="off" placeholder="请选择是否缴纳社保"
                     style="float: left">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="缴纳公积金" prop="needHabitationIns" label-width="100px">
          <el-select v-model="dataForm.needHabitationIns" clearable auto-complete="off" placeholder="请选择是否缴纳公积金"
                     style="float: left">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公积金代缴" prop="needSocialServer" label-width="100px">
          <el-select v-model="dataForm.needSocialServer" clearable auto-complete="off" placeholder="请选择是否需要代缴公积金"
                     style="float: left">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
            <el-option label="代开中" value='2'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="社保代缴" prop="needHabitationServer" label-width="100px">
          <el-select v-model="dataForm.needHabitationServer" clearable auto-complete="off" placeholder="请选择是否需要代缴社保"
                     style="float: left">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
            <el-option label="代开中" value='2'></el-option>
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
  import AreaJson from "@/utils/area.json"
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
        dtlForm: {
          companyId: '',
          companyName: '',
          taxType: '',
          area: '',
          financeDiskType: '',
          taxRate: '',
          banks: [],
          allBanks: []
        },
        bizPageRequest: {
          current: 1,
          size: 20,
        },
        bizTotal: 0,
        staffPageRequest: {
          current: 1,
          size: 20,
        },
        staffTotal: 0,
        bizTableData: [],
        staffTableData: [],
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        bizLoading: false,
        staffLoading: false,
        editShow: false,
        dataFormRules: {
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          companyId: '',
          staffId: '',
          name: '',
          idCard: '',
          contactNumber: '',
          needSocialIns: '',
          needHabitationIns: '',
          needSocialServer: '',
          needHabitationServer: '',
        },
        areaData: [],
        sys_presales_company_staff_add: false,
        sys_presales_company_staff_edit: false,
        sys_presales_company_staff_del: false
      }
    }, created() {
      this.sys_presales_company_staff_add = hasPermission('sys:presales:company:staff:add')
      this.sys_presales_company_staff_edit = hasPermission('sys:presales:company:staff:edit')
      this.sys_presales_company_staff_del = hasPermission('sys:presales:company:staff:del')
      //初始化客户信息
      this.dtlForm = this.$route.params;
      let tmpInfo = JSON.parse(localStorage.getItem("companyDtl"));
      if (this.dtlForm.companyId == undefined || this.dtlForm.companyId == null) {
        if (tmpInfo.companyId == null || tmpInfo.companyId == undefined) {
          this.$router.push({name: "客户详情"})
          return
        } else {
          this.dtlForm = tmpInfo;
        }
      }
      localStorage.setItem("companyDtl", JSON.stringify(this.dtlForm));

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
      findBizPage: function (data) {
        if (data !== null) {
          this.bizPageRequest = data
        }
        this.bizPageRequest.companyId = this.dtlForm.companyId;
        this.bizLoading = true
        let callback = res => {
          this.bizLoading = false
        }
        this.$api.customer.findCompanyBusinessPage(this.bizPageRequest).then((res) => {
          this.bizTableData = res.data.records;
          this.bizTotal = res.data.total;
          this.bizPageRequest.current = res.data.current;
          this.bizPageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        })
      },
      findStaffPage: function (data) {
        if (data !== null) {
          this.staffPageRequest = data
        }
        this.staffPageRequest.companyId = this.dtlForm.companyId;
        this.staffLoading = true
        let callback = res => {
          this.staffLoading = false
        }
        this.$api.customer.findStaffPage(this.staffPageRequest).then((res) => {
          this.staffTableData = res.data.records;
          this.staffTotal = res.data.total;
          this.staffPageRequest.current = res.data.current;
          this.staffPageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        })
      },
      // 批量删除
      handleDeleteBiz: function (data) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.bizLoading = true
          let callback = res => {
            if (res.retCode == 0) {
              this.$message({message: '删除成功', type: 'success'})
              this.findBizPage(null)
            } else {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            }
            this.bizLoading = false
          }
          let companyBizRel = {};
          companyBizRel.companyId = data.companyId;
          companyBizRel.businessId = data.businessId;
          let rels = []
          rels.push(companyBizRel)
          this.$api.customer.batchDeleteCompanyBusiness(rels).then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'});
            this.bizLoading = false
          })
        })
      },
      // 批量删除
      handleStaffDelete: function (data) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.staffLoading = true
          let callback = res => {
            if (res.retCode == 0) {
              this.$message({message: '删除成功', type: 'success'})
              this.findStaffPage(null)
            } else {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            }
            this.staffLoading = false
          }
          let companyStaffRel = {};
          companyStaffRel.companyId = data.companyId;
          companyStaffRel.staffId = data.staffId;
          let rels = []
          rels.push(companyStaffRel)
          this.$api.customer.batchDeleteStaff(rels).then(data != null ? callback : '').catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'});
            this.staffLoading = false
          })
        })
      },
      // 显示新增界面
      handleStaffAdd: function () {
        this.editShow = false
        this.dialogVisible = true
        this.operation = true
        this.dataForm = {
          companyId: this.dtlForm.companyId,
          staffId: '',
          name: '',
          idCard: '',
          contactNumber: '',
          needSocialIns: '',
          needHabitationIns: '',
          needSocialServer: '',
          needHabitationServer: '',
        }
      },
      // 显示编辑界面
      handleStaffEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.editShow = true
        this.dataForm = Object.assign({}, params)
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              params.companyId = this.dtlForm.companyId
              this.$api.customer.saveStaff(params).then((res) => {
                this.editLoading = false
                this.$message({message: '操作成功', type: 'success'})
                this.dialogVisible = false
                this.$refs['dataForm'].resetFields()
                this.findStaffPage(null)
                this.editShow = false
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'});
                this.staffLoading = false
                this.editShow = false
                this.$refs['dataForm'].resetFields()
              })
            })
          }
        })
      },
      handleBizCurrentChange(val) {
        this.bizPageRequest.current = val;
        this.findBizPage(this.bizPageRequest);
      },
      handleStaffCurrentChange(val) {
        this.staffPageRequest.current = val;
        this.findStaffPage(this.staffPageRequest);
      },
    },
    mounted() {
      this.findBizPage(null)
      this.findStaffPage(null)
    }
  }
</script>

<style scoped>

</style>
