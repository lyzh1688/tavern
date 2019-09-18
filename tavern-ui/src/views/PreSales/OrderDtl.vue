<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="订单号" label-width="100px">
          <el-input v-model="dtlForm.orderId" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="订单日期" label-width="100px">
          <el-date-picker v-model="dtlForm.orderDate" type="datetime" :readonly=true></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item label="应付金额" label-width="100px">
          <el-input v-model="dtlForm.receivableAmt" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="实付金额" label-width="100px">
          <el-input v-model="dtlForm.payableAmt" :readonly=true></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dtlForm" :size="size" align="left">
        <el-form-item>
          <kt-button icon="fa fa-add" label="添加关联业务" perms="sys:role:view" type="primary" @click="handleAdd(null)"/>
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
    <!--表格内容栏-->
    <el-table :data="tableData" stripe stripe height="400" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="customId" header-align="center" align="center" label="客户ID" v-if="false">
      </el-table-column>
      <el-table-column prop="businessId" label="业务Id" header-align="center" align="center" v-if="false">
      </el-table-column>
      <el-table-column prop="businessName" label="业务类型" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="owner" label="对接人员" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="companyId" label="关联公司ID" header-align="center" align="center" v-if="false">
      </el-table-column>
      <el-table-column prop="companyName" label="关联公司" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="remark" label="备注" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" header-align="center" align="center">
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                     :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                     style="float:right;">
      </el-pagination>
    </div>
    <el-dialog :title="operation?'添加关联业务':'编辑关联业务'" width="50%" center :visible.sync="dialogVisible"
               :close-on-click-modal="false">
      <el-form :inline="true" :model="dataForm" align="left" ref="dataForm" :rules="dataFormRules">
        <el-form-item label="业务类型" label-width="100px" prop="business">
          <el-select v-model="dataForm.business"
                     filterable
                     remote
                     clearable
                     :remote-method="remoteBusinessDict"
                     placeholder="请输入业务类型"
                     no-data-text="无匹配数据"
                     @change="linkChange"
                     :loading="remoteBusinessDictLoading"
                     prop="business">
            <el-option v-for="item in selectedBizDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="关联公司" label-width="150px" prop="company">
          <el-select v-model="dataForm.company"
                     filterable
                     remote
                     clearable
                     :remote-method="remoteCompanyDict"
                     placeholder="请输入关联公司"
                     no-data-text="无匹配数据"
                     :loading="remoteCompanyDictLoading"
                     prop="company">
            <el-option v-for="item in selectedCompanyDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="对接人员" label-width="100px" prop="owner">
          <el-select v-model="dataForm.owner"
                     :disabled="ownerShow"
                     filterable
                     clearable
                     placeholder="请选择对接人员"
                     no-data-text="无匹配数据/请检查是否配置相关人员"
                     prop="owner"
                     @change="handleItemChange"

          >
            <el-option v-for="item in ownerDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否需要合作方" label-width="150px" prop="needThirdParty">
          <el-select v-model="dataForm.needThirdParty"
                     clearable auto-complete="off"
                     placeholder="请选择"
                     @change="linkThirdPartyChange"
                     prop="needThirdParty"
                     :rules="dataFormRules.needThirdParty"
                     ref="dataForm">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合作方" label-width="100px" prop="thirdParty">
          <el-select v-model="dataForm.thirdParty"
                     :disabled="thirdPartyShow"
                     filterable
                     remote
                     clearable
                     :remote-method="remoteThirdPartyDict"
                     placeholder="请选择合作方"
                     no-data-text="无匹配数据/请检查是否配置相关人员"
                     :loading="remoteThirdPartyDictLoading"
                     prop="thirdParty">
            <el-option v-for="item in selectedPartyDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="支付合作方费用" label-width="150px" prop="thirdPartyFee">
          <el-input v-model="dataForm.thirdPartyFee" placeholder="请输入费用" :disabled="thirdPartyShow"></el-input>
        </el-form-item>
        <el-form-item label="前置任务" label-width="100px" prop="preEvent">
          <el-select v-model="dataForm.preEvent"
                     filterable
                     remote
                     clearable
                     :remote-method="remotePreEventDict"
                     placeholder="请输入前置任务"
                     no-data-text="无前置任务数据"
                     :loading="remotePreEventDictLoading">
            <el-option v-for="item in selectedPreEventDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="业务标签" label-width="150px" prop="businessTag">
          <el-select v-model="dataForm.businessTag" clearable auto-complete="off" placeholder="请选择">
            <el-option label="加急" value='加急'></el-option>
            <el-option label="一般" value='一般'></el-option>
            <el-option label="投诉" value='投诉'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务内容/备注" prop="remark" label-width="100px">
          <el-input v-model="dataForm.remark" auto-complete="off" style="width: 550px" placeholder="输入内容"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="dljzForm" align="left" v-if="showHelpBookKeeping"
               ref="dljzForm">
        <el-form>
          <el-form-item label="代理记账" label-width="150px"/>
        </el-form>
        <el-form-item label="服务开始时间" label-width="150px" prop="beginDate"
                      :rules="{required: true, message: '请选择是否开始服务', trigger: 'change'}">
          <el-date-picker v-model="dljzForm.beginDate" type="datetime" placeholder="选择日期时间"
                          @change="dljzEndDateChange"></el-date-picker>
        </el-form-item>
        <el-form-item label="服务期限(月)" label-width="150px" prop="months"
                      :rules="{required: true, message: '请填写服务期限(月)', trigger: 'change'}">
          <el-input v-model="dljzForm.months" placeholder="请输入服务期限(月)" @change="dljzEndDateChange"></el-input>
        </el-form-item>
        <el-form-item label="服务开始" label-width="150px" prop="isBegin"
                      :rules="{required: true, message: '请填写服务开始', trigger: 'change'}">
          <el-select v-model="dljzForm.isBegin" clearable auto-complete="off" placeholder="请选择">
            <el-option label="未开始" value='0'></el-option>
            <el-option label="已开始" value='1'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务结束时间" label-width="150px" prop="endDate">
          <el-date-picker v-model="dljzForm.endDate" type="datetime" :disabled="true"></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="djfwForm" align="left" v-if="showHelpPay" ref="djfwForm">
        <el-form>
          <el-form-item label="公积金代缴/社保代缴" label-width="150px">
          </el-form-item>
        </el-form>
        <el-form-item label="服务开始时间" label-width="150px" prop="beginDate"
                      :rules="{required: true, message: '请填写服务开始时间', trigger: 'change'}">
          <el-date-picker v-model="djfwForm.beginDate" type="datetime" placeholder="选择日期时间"
                          @change="djfwEndDateChange"></el-date-picker>
        </el-form-item>
        <el-form-item label="服务期限(月)" label-width="150px" prop="months"
                      :rules="{required: true, message: '请填写服务期限(月)', trigger: 'change'}">
          <el-input v-model="djfwForm.months" placeholder="请输入服务期限(月)" @change="djfwEndDateChange"></el-input>
        </el-form-item>
        <el-form-item label="公司人数确认" label-width="150px" prop="confirmNum"
                      :rules="{required: true, message: '请选择是否需要确认人数', trigger: 'change'}">
          <el-select v-model="djfwForm.confirmNum" clearable auto-complete="off" placeholder="请选择"
                     @change="djfwConfirmChange">
            <el-option label="已确认" value='1'></el-option>
            <el-option label="未确认" value='0'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="缴款人数" label-width="150px" prop="employeeNum">
          <el-input v-model="djfwForm.employeeNum" placeholder="请输入缴款人数" :disabled="djfwConfirmNum"></el-input>
        </el-form-item>
        <el-form-item label="服务结束时间" label-width="150px" prop="endDate">
          <el-date-picker v-model="djfwForm.endDate" type="datetime" :disabled="true"></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :model="gszcForm" align="left" v-if="showHelpRegister" ref="gszcForm">
        <el-form>
          <el-form-item label="公司注册" label-width="200px">
          </el-form-item>
        </el-form>
        <el-form-item label="银行开户是否需要到场" label-width="200px" prop="absent"
                      :rules="{required: true, message: '请选择银行开户是否需要到场', trigger: 'change'}">
          <el-select v-model="gszcForm.absent" clearable auto-complete="off" placeholder="请选择银行开户是否需要到场">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="注册地类型" label-width="200px" prop="regLocationType">
          <el-select v-model="gszcForm.regLocationType" clearable auto-complete="off" placeholder="请选择注册地类型">
            <el-option label="虚拟注册" value='虚拟注册'></el-option>
            <el-option label="实地注册" value='实地注册'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click="submitForm()" :loading="editLoading">
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
  import {format, calDate, formatDate, formatDateSimple8} from "@/utils/datetime"

  export default {
    components: {
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog,
      format, calDate, formatDate, formatDateSimple8
    },
    data() {
      return {
        size: 'small',
        dtlForm: {
          customId: '',
          orderId: '',
          orderDate: '',
          receivableAmt: '',
          payableAmt: '',
        },
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
          business: [
            {required: true, message: '请选择业务类型', /*trigger: 'change'*/}
          ],
          company: [
            {required: true, message: '请选择关联公司', /*trigger: 'change'*/}
          ],
          owner: [
            {required: true, message: '请选择对接人员', /*trigger: 'change'*/}
          ],
          needThirdParty: [
            {required: true, message: '请选择是否需要合作方', /*trigger: 'change'*/}
          ],
          businessTag: [
            {required: true, message: '请选择业务标签', /*trigger: 'change'*/}
          ],
        },
        dljzFormRules: {
          isBegin: [
            {required: true, message: '请选择是否开始服务', /*trigger: 'change'*/}
          ],
          months: [
            {required: true, message: '请填写服务期限(月)', /*trigger: 'change'*/}
          ],
          beginDate: [
            {required: true, message: '请选择服务开始时间', /*trigger: 'change'*/}
          ],
        },
        djfwFormRules: {
          isBegin: [
            {required: true, message: '请选择是否开始服务', /*trigger: 'change'*/}
          ],
          confirmNum: [
            {required: true, message: '请选择是否需要确认人数', /*trigger: 'change'*/}
          ],
          beginDate: [
            {required: true, message: '请选择服务开始时间', /*trigger: 'change'*/}
          ],
        },
        gszcFormRules: {
          absent: [
            {required: true, message: '请选择银行开户是否需要到场', /*trigger: 'change'*/}
          ],
        },
        dljzForm: {
          isBegin: '',
          months: '',
          beginDate: '',
          endDate: ''
        },
        djfwForm: {
          confirmNum: '',
          employeeNum: '',
          months: '',
          beginDate: '',
          endDate: ''
        },
        gszcForm: {
          absent: '',
          regLocationType: '',
        },
        loading: false,
        editShow: false,
        // 新增编辑界面数据
        dataForm: {
          business: '',
          company: '',
          owner: '',
          needThirdParty: '',
          thirdParty: '',
          thirdPartyFee: '',
          preEvent: '',
          businessTag: '',
          remark: '',
        },
        showHelpBookKeeping: false,
        showHelpPay: false,
        showHelpRegister: false,
        //字典对象
        bizDict: [],
        selectedBizDict: [],
        companyDict: [],
        ownerDict: [],
        selectedCompanyDict: [],
        thirdPartyDict: [],
        selectedPartyDict: [],
        preEventDict: [],
        selectedPreEventDict: [],
        remoteBusinessDictLoading: false,
        remoteCompanyDictLoading: false,
        remotePreEventDictLoading: false,
        remoteThirdPartyDictLoading: false,
        ownerShow: true,
        thirdPartyShow: true,
        djfwConfirmNum: true,
        ownerName: ''

      }
    }, created() {
      //初始化客户信息
      this.dtlForm = this.$route.params;
      let tmpInfo = JSON.parse(localStorage.getItem("orderDtlInfo"));
      if (this.dtlForm.orderId == undefined || this.dtlForm.orderId == null) {
        if (tmpInfo.orderId == undefined || tmpInfo.orderId == null) {
          this.$router.push({name: "订单管理"})
          return
        } else {
          this.dtlForm = tmpInfo;
        }
      }
      localStorage.setItem("orderDtlInfo", JSON.stringify(this.dtlForm));
      this.findPage(null);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.pageRequest.orderId = this.dtlForm.orderId;
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        this.$api.customer.findOrderBusinessPage(this.pageRequest).then((res) => {
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
      // 显示新增界面
      handleAdd: function () {
        this.dataForm = {
          business: '',
          company: '',
          owner: '',
          needThirdParty: '',
          thirdParty: '',
          thirdPartyFee: '',
          preEvent: '',
          businessTag: '',
          remark: '',
        }
        this.dljzForm = {
          isBegin: '',
          months: '',
          beginDate: '',
          endDate: ''
        }
        this.djfwForm = {
          confirmNum: '',
          employeeNum: '',
          months: '',
          beginDate: '',
          endDate: ''
        }
        this.gszcForm = {
          absent: '',
          regLocationType: '',
        }
        this.dialogVisible = true
        this.operation = true
        this.selectBiz(null)
      },
      // 显示编辑界面
      handleEdit: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.dataForm = Object.assign({}, params.row)
      },
      // 编辑
      submitForm: function () {
        let dataFormValid = ''
        this.$refs.dataForm.validate((valid) => {
          dataFormValid = valid;
        });
        let label = {}
        label = this.bizDict.find(item => {
          return this.dataForm.business == item.id;
        })
        let valid = '';
        switch (label.name) {
          case "代理记账":
            this.$refs.dljzForm.validate((bizValid) => {
              valid = bizValid;
            });
            valid = valid && dataFormValid
            break;
          case "代缴公积金":
            this.$refs.djfwForm.validate((bizValid) => {
              valid = bizValid;
            });
            valid && dataFormValid
            break;
          case "代缴社保":
            this.$refs.djfwForm.validate((bizValid) => {
              valid = bizValid;
            });
            valid = valid && dataFormValid
            break;
          case "公司注册":
            this.$refs.gszcForm.validate((bizValid) => {
              valid = bizValid;
            });
            valid = valid && dataFormValid
            break;
        }
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true
            let params = Object.assign({}, this.dataForm)
            params.businessId = this.dataForm.business
            params.thirdPartyId = this.dataForm.thirdParty
            params.orderId = this.dtlForm.orderId
            params.companyId = this.dataForm.company
            params.creatorId = sessionStorage.getItem("userId")
            params.ownerId = this.dataForm.owner
            params.owner = this.ownerName;

            let dljzDetail = {}
            if (this.dljzForm.beginDate != '') {
              dljzDetail.dljzBeginDate = this.dateFormat(this.dljzForm.beginDate)
            }
            if (this.dljzForm.endDate != '') {
              dljzDetail.dljzEndDate = this.dljzForm.endDate
            }
            dljzDetail.isBegin = this.dljzForm.isBegin
            let gjjsbdjDetail = {}
            if (this.djfwForm.beginDate != '') {
              gjjsbdjDetail.gjjsbdjBeginDate = this.dateFormat(this.djfwForm.beginDate)
            }
            if (this.djfwForm.endDate != '') {
              gjjsbdjDetail.gjjsbdjEndDate = this.djfwForm.endDate
            }
            gjjsbdjDetail.employeeNum = this.djfwForm.employeeNum
            let gszcDetail = {}
            gszcDetail.absent = this.gszcForm.absent
            gszcDetail.regLocationType = this.gszcForm.regLocationType

            params.dljzDetail = dljzDetail
            params.gjjsbdjDetail = gjjsbdjDetail
            params.gszcDetail = gszcDetail

            this.$api.customer.saveOrderBusiness(params).then((res) => {
              this.editLoading = false
              this.$message({message: '操作成功', type: 'success'})
              this.dialogVisible = false
              this.findPage(null)
            }).catch(res => {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
              this.editLoading = false
              this.dialogVisible = false
            })
          })
        }

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
          {prop: "businessName", label: "业务类型", minWidth: 120},
          {prop: "owner", label: "对接人员", minWidth: 120},
          {prop: "companyName", label: "关联公司", minWidth: 120},
          {prop: "remark", label: "备注", minWidth: 120},
          {prop: "createDate", label: "创建时间", minWidth: 120},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      selectBiz: function (e) {

        this.showHelpBookKeeping = false
        this.showHelpPay = false
        this.showHelpRegister = false
        this.dljzForm = {
          isBegin: '',
          months: '',
          beginDate: '',
          endDate: ''
        }
        this.djfwForm = {
          confirmNum: '',
          employeeNum: '',
          months: '',
          beginDate: '',
          endDate: ''
        }
        this.gszcForm = {
          absent: '',
          regLocationType: '',
        }
        let label = this.bizDict.find(item => {
          return e == item.id;
        })
        switch (label.name) {
          case "代理记账":
            this.showHelpBookKeeping = true;
            break;
          case "代缴公积金":
            this.showHelpPay = true;
            break;
          case "代缴社保":
            this.showHelpPay = true;
            break;
          case "公司注册":
            this.showHelpRegister = true;
            break;
        }
      }, initBusinessDict: function () {
        this.$api.customer.findBizDict(null).then((res) => {
          this.bizDict = res.data;
          this.selectedBizDict = res.data;
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      }, remoteBusinessDict: function (param) {
        if (param != '') {
          this.remoteBusinessDictLoading = true;
          setTimeout(() => {
            this.remoteBusinessDictLoading = false;
            this.selectedBizDict = this.bizDict.filter(item => {
              return item.name.toLowerCase()
                .indexOf(param.toLowerCase()) > -1;
            });
          }, 200);
        } else {
          this.selectedBizDict = [];
        }
      },
      initCompanyDict: function () {
        let param = {};
        param.customId = this.dtlForm.customId;
        this.$api.customer.findCompanyDict(param).then((res) => {
          this.companyDict = res.data;
          this.selectedCompanyDict = res.data;
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      }, remoteCompanyDict: function (param) {
        if (param != '') {
          this.remoteCompanyDictLoading = true;
          setTimeout(() => {
            this.remoteCompanyDictLoading = false;
            this.selectedCompanyDict = this.companyDict.filter(item => {
              return item.name.toLowerCase()
                .indexOf(param.toLowerCase()) > -1;
            });
          }, 200);
        } else {
          this.selectedCompanyDict = [];
        }
      },
      initPreEventDict: function () {
        let param = {};
        param.orderId = this.dtlForm.orderId;
        this.$api.customer.findPreEventDict(param).then((res) => {
          this.preEventDict = res.data;
          this.selectedPreEventDict = res.data;
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      }, remotePreEventDict: function (param) {
        if (param != '') {
          this.remotePreEventDictLoading = true;
          setTimeout(() => {
            this.remotePreEventDictLoading = false;
            this.selectedPreEventDict = this.preEventDict.filter(item => {
              return item.name.toLowerCase()
                .indexOf(param.toLowerCase()) > -1;
            });
          }, 200);
        } else {
          this.selectedPreEventDict = [];
        }
      },
      initThirdPartyDict: function () {
        this.$api.customer.findThirdPartyDict().then((res) => {
          this.thirdPartyDict = res.data;
          this.selectedPartyDict = res.data;
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      }, remoteThirdPartyDict: function (param) {
        if (param != '') {
          this.remoteThirdPartyDictLoading = true;
          setTimeout(() => {
            this.remoteThirdPartyDictLoading = false;
            this.selectedPartyDict = this.thirdPartyDict.filter(item => {
              return item.name.toLowerCase()
                .indexOf(param.toLowerCase()) > -1;
            });
          }, 200);
        } else {
          this.selectedPartyDict = [];
        }
      }, linkChange: function (val) {
        if (val != undefined && val != '') {
          this.ownerShow = false;
        } else {
          this.ownerShow = true;
        }
        let ownerRequest = {};
        ownerRequest.business = val;
        this.$api.customer.findOwnerDict(ownerRequest).then((res) => {
          this.ownerDict = res.data
        })
        this.selectBiz(val)
      }, linkThirdPartyChange: function () {
        if (this.dataForm.needThirdParty != undefined && this.dataForm.needThirdParty != '' && this.dataForm.needThirdParty != '0') {
          this.thirdPartyShow = false;
        } else {
          this.thirdPartyShow = true;
        }
      }, djfwConfirmChange: function (val) {
        if (val != undefined && val != '' && val != '0') {
          this.djfwConfirmNum = false;
        } else {
          this.djfwConfirmNum = true;
        }
      }, djfwEndDateChange: function () {
        if (this.djfwForm.beginDate != '' && this.djfwForm.months != '') {
          this.djfwForm.endDate = this.calEndDate(this.djfwForm.beginDate, this.djfwForm.months);
        }
      }, dljzEndDateChange: function () {
        if (this.dljzForm.beginDate != '' && this.dljzForm.months != '') {
          this.dljzForm.endDate = this.calEndDate(this.dljzForm.beginDate, this.dljzForm.months);
        }
      }, calEndDate: function (beginDate, months) {
        return calDate(beginDate, months);
      }, handleItemChange: function (val) {
        this.ownerName = this.ownerDict.find(item => {
          return val == item.id;
        }).name
      },// 时间格式化
      dateFormat: function (date) {
        return formatDateSimple8(date)
      },
      initDict: function () {
        //1. 初始化业务类型
        this.initBusinessDict();
        this.initCompanyDict();
        this.initThirdPartyDict();
        this.initPreEventDict();

        //1. 初始化业务角色
        //2. 初始化关联公司/customId
        //3. 初始化对接人员/role->过滤人员
        //4. 初始化前置任务/orderId


      },
      handleCurrentChange(val) {
        this.pageRequest.current = val;
        this.findPage(this.pageRequest);
      },
    },
    mounted() {
      this.initColumns()
      this.initDict()
    }
  }
</script>

<style scoped>

</style>
