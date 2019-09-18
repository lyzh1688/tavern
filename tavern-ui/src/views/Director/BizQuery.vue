<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="市" label-width="100px">
          <el-input v-model="filters.city" placeholder="请输入城市"></el-input>
        </el-form-item>
        <el-form-item label="区" label-width="100px">
          <el-input v-model="filters.district" placeholder="请输入区域"></el-input>
        </el-form-item>
        <el-form-item label="公司名称" label-width="100px">
          <el-input v-model="filters.companyName" placeholder="请输入公司名称"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="客户名称" label-width="100px">
          <el-input v-model="filters.customName" placeholder="请输入客户名称"></el-input>
        </el-form-item>
        <el-form-item label="当前流程" label-width="100px">
          <el-input v-model="filters.curNodeName" placeholder="请输入当前流程名"></el-input>
        </el-form-item>
        <el-form-item label="客户级别" label-width="100px">
          <el-select v-model="filters.customLevel" clearable auto-complete="off" placeholder="请选择客户等级">
            <el-option label="VVIP" value='VVIP'></el-option>
            <el-option label="VIP" value='VIP'></el-option>
            <el-option label="高级客户" value='高级客户'></el-option>
            <el-option label="普通客户" value='普通客户'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="微信昵称" label-width="100px">
          <el-input v-model="filters.weixinName" placeholder="请输入微信昵称"></el-input>
        </el-form-item>
        <el-form-item label="业务创建时间" label-width="100px">
          <el-date-picker v-model="filters.createDate" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="业务类型" label-width="100px">
          <el-input v-model="filters.businessName" placeholder="请输入业务类型"></el-input>
        </el-form-item>
        <el-form-item label="紧急程度" label-width="100px">
          <el-select v-model="filters.businessTag" clearable auto-complete="off" placeholder="请选择紧急程度">
            <el-option label="加急" value='加急'></el-option>
            <el-option label="一般" value='一般'></el-option>
            <el-option label="投诉" value='投诉'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" type="primary"
                     @click="findPage(null)"/>
          <kt-button icon="fa fa-edit" label="批量转授权" type="primary"
                     @click="handleBatchReChoose(null)"/>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="tableData" stripe stripe height="500" size="mini" style="width: 100%;"
              v-loading="loading" @selection-change="selectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="orderId" header-align="center" align="center" label="订单ID" v-if="false">
      </el-table-column>
      <el-table-column prop="businessId" header-align="center" align="center" label="业务ID" v-if="false">
      </el-table-column>
      <el-table-column prop="companyId" header-align="center" align="center" label="公司ID" v-if="false">
      </el-table-column>
      <el-table-column prop="eventId" header-align="center" align="center" label="事件ID" v-if="false">
      </el-table-column>
      <el-table-column prop="curNodeId" header-align="center" align="center" label="业务节点ID" v-if="false">
      </el-table-column>
      <el-table-column prop="customName" label="客户姓名" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="wangwangAccnt" label="旺旺账号" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="companyName" label="公司名称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="city" label="市" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="district" label="区" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="remark" label="服务内容/备注" header-align="center" align="center" width="300px">
      </el-table-column>
      <el-table-column prop="weixinName" label="微信昵称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="businessName" label="业务类型" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="cost" label="耗时(天)" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="businessTag" label="紧急程度" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="maxLeftDay" label="最大剩余天数" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间" header-align="center" align="center" width="200px">
      </el-table-column>
      <el-table-column prop="curNodeName" label="当前流程" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="customLevel" label="客户级别" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="curOperatorName" label="当前处理人" header-align="center" align="center">
      </el-table-column>
      <el-table-column fixed="right" label="操作" header-align="center" align="center" width="500">
        <template slot-scope="scope">
          <kt-button icon="fa fa-gears" label="流程日志" type="primary"
                     @click="showWorkFlow(scope.row)"/>
          <kt-button icon="fa fa-university" label="公司详情" type="primary"
                     @click="handleComDtl(scope.row)"/>
          <kt-button icon="fa fa-battery-2" label="延期" type="primary"
                     @click="handleDelay(scope.row)"/>
          <kt-button icon="fa fa-money" label="转授权" type="primary"
                     @click="handleReChoose(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                     :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                     style="float:right;">
      </el-pagination>
    </div>
    <el-dialog title="公司详情" width="40%" :visible.sync="comDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="comDtlForm" align="left">
        <el-form-item label="订单号" label-width="100px">
          <el-input v-model="comDtlForm.orderId"></el-input>
        </el-form-item>
        <el-form-item label="实付金额" label-width="100px">
          <el-input v-model="comDtlForm.receivableAmt"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="comDtlForm" align="left">
        <el-form-item label="公司名称" label-width="100px">
          <el-input v-model="comDtlForm.companyName"></el-input>
        </el-form-item>
        <el-form-item label="客户名称" label-width="100px">
          <el-input v-model="comDtlForm.customName"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="comDtlForm" align="left">
        <el-form-item label="公司区域" label-width="100px">
          <el-input v-model="comDtlForm.district"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" label-width="100px">
          <el-input v-model="comDtlForm.contactNumber"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="comDtlForm" align="left">
        <el-form-item label="旺旺号" label-width="100px">
          <el-input v-model="comDtlForm.wangwangAccnt"></el-input>
        </el-form-item>
        <el-form-item label="社保/公积金人数" label-width="100px">
          <el-input v-model="comDtlForm.employeeNum"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="comDtlForm" align="left">
        <el-form-item label="服务开始时间" label-width="100px">
          <el-input v-model="comDtlForm.beginDate"></el-input>
        </el-form-item>
        <el-form-item label="服务结束时间" label-width="100px">
          <el-date-picker v-model="comDtlForm.endDate"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" type="primary" @click.native="comDialogVisible = false" :loading="editLoading">
          确认
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="延期批示" width="40%" :visible.sync="delayDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="delayForm" :rules="delayFormRules" ref="delayForm" :size="size"
               label-position="center" align="left">
        <el-form-item label="订单号" label-width="100px" prop="orderId">
          <el-input v-model="delayForm.orderId" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="延期业务" label-width="100px" prop="businessName">
          <el-input v-model="delayForm.businessName" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="流程日志: " label-width="100px">
          <div id="mountNode" ref="mount"></div>
        </el-form-item>
        <el-form-item label="处理意见: " label-width="100px" prop="message">
          <el-input type="textarea" v-model="delayForm.message" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="延期天数" label-width="100px" prop="delayDays">
          <el-input v-model="delayForm.delayDays" placeholder="请输入延期天数"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="delayDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitDelay" :loading="delayLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="历史备注" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" :size="size" label-position="center" align="left">
        <el-form-item prop="logHistory" label-width="100px">
          <el-table :data="dataForm.logHistory" stripe size="mini" style="width: 100%;" v-loading="logHisLoading"
                    :element-loading-text="$t('action.loading')" height="300">
            <el-table-column
              prop="logId" header-align="center" align="center" label="备注Id" v-if="false">
            </el-table-column>
            <el-table-column
              prop="eventId" header-align="center" align="center" label="eventId" v-if="false">
            </el-table-column>
            <el-table-column
              prop="operator" header-align="center" align="center" label="操作人Id" v-if="false">
            </el-table-column>
            <el-table-column
              prop="operatorName" header-align="center" align="center" label="操作人">
            </el-table-column>
            <el-table-column
              prop="createTime" header-align="center" align="center" label="备注时间">
            </el-table-column>
            <el-table-column
              prop="message" header-align="center" align="center" label="备注内容">
            </el-table-column>
            <el-table-column
              prop="refundFee" header-align="center" align="center" label="退款">
            </el-table-column>
            <el-table-column
              prop="attachmentsPath" header-align="center" align="center" label="附件地址">
              <template slot-scope="scope1">
                <a :href="scope1.row.attachmentsPath" target="_blank">{{scope1.row.attachmentsPath}}</a>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" type="primary" @click.native="dialogVisible = false" :loading="editLoading">
          确认
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="转授权" width="20%" :visible.sync="reChooseDialogVisible"
               :close-on-click-modal="false">
      <el-form :model="reChooseForm" :size="size" align="left" :rules="reChooseFormRules" label-position="center"
               ref="reChooseForm">
        <el-form-item label="当前处理人" label-width="100px" prop="curOperatorName">
          <el-input v-model="reChooseForm.curOperatorName" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="变更处理人" label-width="100px" prop="handler">
          <el-select v-model="reChooseForm.handler"
                     filterable
                     clearable
                     placeholder="请选择处理人"
                     no-data-text="无匹配数据/请检查是否配置相关处理人"
                     prop="handlerShow"
                     @change="handlerChange"
                     style="float: left"

          >
            <el-option v-for="item in handlerDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="reChooseDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitRechoose"
                   :loading="reChooseEditLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="转授权" width="60%" :visible.sync="reChooseBatchDialogVisible" :close-on-click-modal="false">
      <el-form :model="reChooseBatchForm" :size="size" label-position="center" align="left"
               :rules="reChooseBatchFormRules"
               ref="reChooseBatchForm">
        <el-form-item prop="content">
          <el-table :data="reChooseBatchForm.content" stripe size="mini" style="width: 100%;" height="200"
                    v-loading="reChooseBatchLoading"
                    :element-loading-text="$t('action.loading')">
            <el-table-column
              prop="eventId" header-align="center" align="center" label="事件ID">
            </el-table-column>
            <el-table-column
              prop="customName" header-align="center" align="center" label="客户名称">
            </el-table-column>
            <el-table-column
              prop="companyName" header-align="center" align="center" label="公司名称">
            </el-table-column>
            <el-table-column
              prop="businessName" header-align="center" align="center" label="在办业务">
            </el-table-column>
            <el-table-column
              prop="curOperatorName" header-align="center" align="center" label="当前处理人">
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="变更处理人" label-width="100px" prop="handler">
          <el-select v-model="reChooseBatchForm.handler"
                     filterable
                     clearable
                     placeholder="请选择处理人"
                     no-data-text="无匹配数据/请检查是否配置相关处理人"
                     prop="handlerShow"
                     @change="handlerBatchChange"
                     style="float: left"

          >
            <el-option v-for="item in handlerDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="reChooseBatchDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitReChooseBatch"
                   :loading="reChooseBatchEditLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import G6 from '@antv/g6';
  import dagre from 'dagre';
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
          userId: '',
          city: '',
          district: '',
          customName: '',
          customLevel: '',
          companyName: '',
          curNodeName: '',
          weixinName: '',
          createDate: '',
          businessName: '',
          businessTag: '',
        },
        dataForm: {
          logHistory: []
        },
        logHisTotal: 0,
        loading: false,
        tableData: [],
        pageRequest: {
          current: 1,
          size: 20,
        },
        total: 0,
        logHisPageRequest: {
          current: 1,
          size: 100,
        },
        operation: false, // true:新增, false:编辑
        logHisLoading: false,
        dialogVisible: false,
        delayDialogVisible: false,
        reChooseBatchLoading: false,
        reChooseDialogVisible: false,
        reChooseDialogBatchVisible: false,
        reChooseBatchDialogVisible: false,
        drawBackDialogVisible: false,
        comDialogVisible: false,
        editLoading: false,
        reChooseEditLoading: false,
        reChooseBatchEditLoading: false,
        delayLoading: false,
        dataFormRules: {},
        delayFormRules: {
          delayDays: [
            {required: true, message: '请输入延期天数', trigger: 'blur'}
          ],
          message: [
            {required: true, message: '请输入延期备注', trigger: 'blur'}
          ],
        },
        reChooseFormRules: {
          handler: [
            {required: true, message: '请选择变更处理人', trigger: 'blur'}
          ],
        },
        reChooseBatchFormRules: {
          handler: [
            {required: true, message: '请选择变更处理人', trigger: 'blur'}
          ],
        },
        comDtlForm: {
          orderId: '',
          receivableAmt: '',
          companyName: '',
          customName: '',
          district: '',
          contactNumber: '',
          wangwangAccnt: '',
          employeeNum: '',
          beginDate: '',
          endDate: '',
        },
        delayForm: {
          orderId: '',
          mountNode: '',
          businessName: '',
          delayDays: 0,
          message: '',
          businessId: '',
          companyId: '',
          eventId: '',
        },
        bakHistory: {
          content: []
        },
        data: {},
        reChooseForm: {
          handler: ''
        },
        reChooseBatchForm: {
          content: [],
          handler: ''

        },
        showHandler: false,
        handlerDict: [],
        chosenHandler: {},
        chosenBatchHandler: {},
        selections: []  // 列表选中列
      }
    },
    created() {
      this.findPage(null);
    },
    mounted() {
    },
    methods: {
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        this.pageRequest.city = this.filters.city
        this.pageRequest.district = this.filters.district
        this.pageRequest.customName = this.filters.customName
        this.pageRequest.customLevel = this.filters.customLevel
        this.pageRequest.companyName = this.filters.companyName
        this.pageRequest.curNodeName = this.filters.curNodeName
        this.pageRequest.weixinName = this.filters.weixinName
        if (this.filters.createDate != undefined && this.filters.createDate != '') {
          this.pageRequest.createDate = this.dateFormat(this.filters.createDate)
        }
        this.pageRequest.businessName = this.filters.businessName
        this.pageRequest.businessTag = this.filters.businessTag
        this.$api.workflow.findAllEvent(this.pageRequest).then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageRequest.current = res.data.current;
          this.pageRequest.size = res.data.size;
          callback(res)
        }).then(data != null ? data.callback : '')
          .catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            callback(res)
          })
      },
      showWorkFlow: function (params) {
        this.$router.push({name: '工作流日志', params: params})
      },
      handleDelay: function (params) {
        this.delayForm = {
          orderId: '',
          mountNode: '',
          businessName: '',
          delayDays: 0,
          message: '',
          businessId: '',
          companyId: '',
          eventId: '',
        }

        let ele = document.getElementById('mountNode')
        if (ele != null) {
          ele.innerHTML = '';
        }
        this.initG6(params)
        this.delayDialogVisible = true
        this.delayForm = Object.assign({}, params)
      },
      handleReChoose: function (params) {
        this.reChooseForm = {
          handler: ''
        }
        this.$api.customer.findAllOperator(null).then((res) => {
          this.handlerDict = res.data
        })
        this.reChooseForm = Object.assign({}, params)
        this.reChooseDialogVisible = true
      },
      handleBatchReChoose: function (params) {
        if (this.selections == undefined || this.selections.length == 0) {
          this.$message({message: '未勾选转授权业务', type: 'error'})
          return
        }
        this.reChooseBatchForm = {
          content: [],
          handler: ''
        }
        this.$api.customer.findAllOperator(null).then((res) => {
          this.handlerDict = res.data
        })
        this.reChooseBatchForm.content = this.selections
        this.reChooseBatchDialogVisible = true
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.pictureDialogVisible = true;
      },
      handleComDtl: function (params) {
        this.comDtlForm = {
          orderId: '',
          receivableAmt: '',
          companyName: '',
          customName: '',
          district: '',
          contactNumber: '',
          wangwangAccnt: '',
          employeeNum: '',
          beginDate: '',
          endDate: '',
        }
        let request = {}
        request.eventId = params.eventId
        this.$api.customer.findOrdercompanyDetail(request).then((res) => {
          this.comDtlForm = res.data;
          this.comDialogVisible = true
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          this.comDialogVisible = false
        })
      },
      // 编辑
      submitDelay: function () {
        this.$refs.delayForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.delayLoading = true
              let params = Object.assign({}, this.delayForm)
              params.operator =  sessionStorage.getItem("userId")
              params.operatorName = sessionStorage.getItem("userName")
              params.message = this.delayForm.message + ",需要延时" + this.delayForm.delayDays + "天"
              this.$api.workflow.delayNotes(params).then((res) => {
                this.delayLoading = false
                this.delayDialogVisible = false
                this.$message({message: '操作成功', type: 'success'})
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                this.delayLoading = false
                this.delayDialogVisible = false
              })
            })
          }
        })
      },
      submitRechoose: function () {
        this.$refs.reChooseForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.reChooseEditLoading = true
              let batchRequest = [];
              let request = {};
              request.eventId = this.reChooseForm.eventId
              request.operator =  sessionStorage.getItem("userId")
              request.operatorName = sessionStorage.getItem("userName")
              request.message = "订单号: " + this.reChooseForm.orderId
                + ",业务类型: " + this.reChooseForm.businessName
                + ",当前节点: " + this.reChooseForm.curNodeName
                + ",原处理人: " + this.reChooseForm.curOperatorName
                + ",变更处理人: " + this.chosenHandler.name
              request.handlerId = this.chosenHandler.id
              request.handlerName = this.chosenHandler.name
              request.curNodeId = this.reChooseForm.curNodeId
              batchRequest.push(request)
              this.$api.workflow.reChoose(batchRequest).then((res) => {
                this.reChooseEditLoading = false
                this.reChooseDialogVisible = false
                this.$message({message: '操作成功', type: 'success'})
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                this.reChooseEditLoading = false
                this.reChooseDialogVisible = false
              })
            })
          }
        })
      },
      submitReChooseBatch: function () {
        this.$refs.reChooseBatchForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.reChooseBatchEditLoading = true
              let batchRequest = [];
              this.reChooseBatchForm.content.forEach((reChoose) => {
                let request = {};
                request.eventId = reChoose.eventId
                request.operator =  sessionStorage.getItem("userId")
                request.operatorName = sessionStorage.getItem("userName")
                request.message = "订单号: " + reChoose.orderId
                  + ",业务类型: " + reChoose.businessName
                  + ",当前节点: " + reChoose.curNodeName
                  + ",原处理人: " + reChoose.curOperatorName
                  + ",变更处理人: " + this.chosenBatchHandler.name
                request.handlerId = this.chosenBatchHandler.id
                request.handlerName = this.chosenBatchHandler.name
                request.curNodeId = reChoose.curNodeId
                batchRequest.push(request)
              })
              this.$api.workflow.reChoose(batchRequest).then((res) => {
                this.reChooseBatchEditLoading = false
                this.reChooseBatchDialogVisible = false
                this.$message({message: '操作成功', type: 'success'})
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                this.reChooseBatchEditLoading = false
                this.reChooseBatchDialogVisible = false
              })
            })
          }
        })
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.findPage(_this.pageRequest);
      },
      initG6(params) {
        let request = {}
        request.eventId = params.eventId
        this.$api.workflow.findGraphLog(request).then((res) => {
          this.data = res.data;
          this.presentG6()
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      },
      presentG6() {
        let _this = this
        let data = {
          nodes: [],
          edges: []
        }
        data = this.data
        var g = new dagre.graphlib.Graph();
        g.setDefaultEdgeLabel(function () {
          return {};
        });
        g.setGraph({
          rankdir: 'TB'
        });
        data.nodes.forEach(function (node) {
          node.label = node.label;
          g.setNode(node.id, {
            width: 150,
            height: 50
          });
        });
        data.edges.forEach(function (edge) {
          g.setEdge(edge.source, edge.target);
        });
        dagre.layout(g);
        var coord = void 0;
        g.nodes().forEach(function (node, i) {
          coord = g.node(node);
          data.nodes[i].x = coord.x;
          data.nodes[i].y = coord.y;
        });
        g.edges().forEach(function (edge, i) {
          coord = g.edge(edge);
          data.edges[i].startPoint = coord.points[0];
          data.edges[i].endPoint = coord.points[coord.points.length - 1];
          data.edges[i].controlPoints = coord.points.slice(1, coord.points.length - 1);
        });
        G6.registerNode('operation', {
          drawShape: function drawShape(cfg, group) {
            var rect = group.addShape('rect', {
              attrs: {
                x: -75,
                y: -25,
                width: 150,
                height: 50,
                radius: 10,
                stroke: '#409EFF',
                fill: '#409EFF',
                fillOpacity: 0.45,
                lineWidth: 2
              }
            });
            return rect;
          }
        }, 'single-shape');
        var graph = new G6.Graph({
          container: 'mountNode',
          width: 800,
          height: 300,
          pixelRatio: 2,
          modes: {
            default: ['drag-canvas', 'zoom-canvas']
          },
          defaultNode: {
            shape: 'operation',
            labelCfg: {
              style: {
                fill: '#409EFF',
                fontSize: 14,
                fontWeight: 'bold'
              }
            }
          },
          defaultEdge: {
            shape: 'polyline'
          },
          edgeStyle: {
            default: {
              endArrow: true,
              lineWidth: 2,
              stroke: '#409EFF'
            }
          }
        });
        graph.data(data);
        graph.render();
        graph.fitView();
        graph.on('click', function (ev) {
          _this.findLogPage(ev.item.get('model'))
        });
      },
      findLogPage: function (params) {
        this.dialogVisible = true
        this.logHisLoading = true
        let callback = res => {
          this.logHisLoading = false
        }
        this.logHisPageRequest.eventId = this.delayForm.eventId
        this.logHisPageRequest.operator = params.operatorId
        this.$api.workflow.findLog(this.logHisPageRequest).then((res) => {
          this.dataForm.logHistory = res.data.records;
          this.logHisTotal = res.data.total;
          this.logHisPageRequest.current = res.data.current;
          this.logHisPageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        })
      }, handlerChange: function (val) {
        this.chosenHandler = this.handlerDict.find(item => {
          return val == item.id;
        })
      },// 选择切换
      handlerBatchChange: function (val) {
        this.chosenBatchHandler = this.handlerDict.find(item => {
          return val == item.id;
        })
      },// 选择切换
      selectionChange: function (selections) {
        this.selections = selections
      },
    },
  }
</script>

<style scoped>

</style>
