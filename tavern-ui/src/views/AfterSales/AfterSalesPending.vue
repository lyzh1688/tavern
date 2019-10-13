<template>
  <div class="page-container">
    <el-row>
      <el-col :span="24" align="left">
        <p><span></span>我的待办：
        </p>
      </el-col>
    </el-row>
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;" v-if="sys_aftersales_pending_view">
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
        <el-form-item label="显示结束待办" label-width="100px">
          <el-switch
            v-model="filters.ifOver"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" v-if="sys_aftersales_pending_view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="tableData" stripe stripe height="500" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="orderId" header-align="center" align="center" label="订单ID" v-if="false">
      </el-table-column>
      <el-table-column prop="receivableAmt" header-align="center" align="center" label="实收价格" v-if="false">
      </el-table-column>
      <el-table-column prop="payableAmt" header-align="center" align="center" label="应收价格" v-if="false">
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
      <el-table-column  label="操作"
                       v-if="sys_aftersales_pending_flow || sys_aftersales_pending_addlog || sys_aftersales_pending_drawback || sys_aftersales_pending_next"
                       header-align="center" align="center" width="500">
        <template slot-scope="scope">
          <kt-button icon="fa fa-gears" label="流程日志" type="primary" v-if="sys_aftersales_pending_flow"
                     @click="showWorkFlow(scope.row)"/>
          <kt-button icon="fa fa-plus" label="添加备注" type="primary"
                     v-if="sys_aftersales_pending_addlog && scope.row.curOperatorName == userName "
                     @click="handleLog(scope.row)"/>
          <kt-button icon="fa fa-money" label="退款审批" type="primary"
                     v-if="scope.row.curNodeName == '退款审批' && sys_aftersales_pending_drawback && scope.row.curOperatorName == userName"
                     @click="handleDrawBack(scope.row)"/>
          <kt-button icon="fa fa-arrow-right" label="下一步" type="primary"
                     v-if="sys_aftersales_pending_next && scope.row.curOperatorName == userName"
                     @click="handleNext(scope.row)"/>
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
    <el-dialog title="添加备注" width="60%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="center" align="center">
        <el-form-item label="当前流程: " prop="curNodeName" label-width="100px">
          <span style="text-align: left;float: left;color: #a71d5d">{{dataForm.curNodeName}}</span>
        </el-form-item>
        <el-form-item label="历史备注:" prop="bakHistory" label-width="100px">
          <el-table :data="dataForm.logHistory" stripe size="mini" style="width: 100%;" v-loading="logHisLoading"
                    :element-loading-text="$t('action.loading')" height="200">
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
              prop="nodeName" header-align="center" align="center" label="流程节点名称">
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
                <a :href="scope1.row.attachmentsPath" target="_blank">{{(scope1.row.attachmentsPath != '' && scope1.row.attachmentsPath != null)?'点击查看附件':''}}</a>
              </template>
            </el-table-column>
          </el-table>
          <div class="toolbar" style="padding:10px;">
            <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleLogHisCurrentChange"
                           :current-page="logHisPageRequest.current" :page-size="logHisPageRequest.size"
                           :total="logHisTotal"
                           style="float:right;">
            </el-pagination>
          </div>
        </el-form-item>
        <el-form-item label="备注: " label-width="100px">
          <el-input type="textarea" v-model="dataForm.message" style="width: 600px;float: left"></el-input>
        </el-form-item>
        <el-form-item label="微信截图: " label-width="100px">
          <el-upload
            action="customize"
            ref="nextUploadLogFiles"
            accept='image/jpeg,image/gif,image/png'
            :auto-upload="false"
            list-type="picture-card"
            :on-change="handleChange"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :http-request="handleSendUploadRequest"
            :file-list="logFiles"
            style="float: left">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="pictureDialogVisible">
            <el-image :src="dialogImageUrl" alt=""/>
          </el-dialog>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitLogForm" :loading="editLoading"
                   v-if="this.dataForm.curOperatorName == userName">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="下一步" width="40%" :visible.sync="nextDialogVisible" :close-on-click-modal="false">
      <el-form :model="nextForm" label-width="80px" :rules="nextFormRules" ref="nextForm" :size="size"
               label-position="center" align="center">
        <el-form-item label="当前流程: " label-width="100px" prop="curNodeName">
          <span style="text-align: left;float: left;color: #a71d5d">{{nextForm.curNodeName}}</span>
        </el-form-item>
        <el-form-item label="退款金额" label-width="100px" v-if="showRefund">
          <el-input v-model="nextForm.refundFee" placeholder="请输入退款金额"></el-input>
        </el-form-item>
        <el-form-item label="下一流程" label-width="100px" prop="nextNode">
          <el-select v-model="nextForm.nextNode"
                     filterable
                     remote
                     clearable
                     :remote-method="remoteNextNodeDict"
                     placeholder="请输入下一流程"
                     no-data-text="无匹配数据/请检查是否配置相关流程"
                     @change="linkChange"
                     :loading="remoteNextNodeDictLoading"
                     prop="nextNode"
                     style="float: left">
            <el-option v-for="item in selectedNextNodeDict"
                       :key="item.nodeId"
                       :value="item.nodeId"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="对接人员" label-width="100px" prop="nextOperator" v-if="showNextOperator">
          <el-select v-model="nextForm.nextOperator"
                     filterable
                     clearable
                     :disabled="nextOperatorShow"
                     placeholder="请选择对接人员"
                     no-data-text="无匹配数据/请检查是否配置相关人员"
                     prop="nextOperator"
                     @change="handleItemChange"
                     style="float: left"

          >
            <el-option v-for="item in nextOperatorDict"
                       :key="item.id"
                       :value="item.id"
                       :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="备注: " label-width="100px">
          <el-input type="textarea" v-model="nextForm.message" style="width: 500px;float: left"></el-input>
        </el-form-item>
        <el-form-item label="微信截图: " label-width="100px">
          <el-upload
            action="customize"
            ref="uploadLogFiles"
            accept='image/jpeg,image/gif,image/png'
            :auto-upload="false"
            list-type="picture-card"
            :on-change="handleNextChange"
            :on-preview="handleNextPictureCardPreview"
            :on-remove="handleNextRemove"
            :http-request="handleNextSendUploadRequest"
            :file-list="nextFileList"
            style="float: left">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="nextPictureDialogVisible">
            <el-image :src="nextDialogImageUrl" alt=""/>
          </el-dialog>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="nextDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitNextLogForm" :loading="nextEditLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="退款处理" width="60%" :visible.sync="drawBackDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="drawBackForm" align="left" label-width="80px" :rules="drawBackFormRules"
               ref="drawBackForm" :size="size"
               label-position="center">
        <el-form-item label="订单号" label-width="200px" prop="orderId">
          <el-input v-model="drawBackForm.orderId" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="退款业务" label-width="200px" prop="businessName">
          <el-input v-model="drawBackForm.businessName" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="应付金额" label-width="200px" prop="payableAmt">
          <el-input v-model="drawBackForm.payableAmt" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="实付金额" label-width="200px" prop="receivableAmt">
          <el-input v-model="drawBackForm.receivableAmt" :readonly=true></el-input>
        </el-form-item>
        <el-form-item label="流程日志: " label-width="200px">
          <div id="mountNode" ref="mount"></div>
        </el-form-item>
        <el-form-item label="审批意见: " label-width="200px" prop="message">
          <el-input type="textarea" v-model="drawBackForm.message" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="是否同意退款" label-width="200px" prop="agreeRefund">
          <el-select v-model="drawBackForm.agreeRefund" clearable auto-complete="off" placeholder="请选择"
                     @change="handleRefundChange">
            <el-option label="是" value='1'></el-option>
            <el-option label="否" value='0'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="变更处理人" label-width="200px" prop="handler" v-if="showHandler">
          <el-select v-model="drawBackForm.handler"
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
        <el-button :size="size" @click.native="drawBackDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="commitDrawBack" :loading="drawBackEditLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="历史备注" width="40%" :visible.sync="logDialogVisible" :close-on-click-modal="false">
      <el-form :model="logDataForm" :size="size" label-position="center" align="left">
        <el-form-item prop="logHistory" label-width="100px">
          <el-table :data="logDataForm.logHistory" stripe size="mini" style="width: 100%;" v-loading="logLoading"
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
              prop="nodeName" header-align="center" align="center" label="流程节点名称">
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
                <a :href="scope1.row.attachmentsPath" target="_blank">{{(scope1.row.attachmentsPath != '' && scope1.row.attachmentsPath != null)?'点击查看附件':''}}</a>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" type="primary" @click.native="logDialogVisible = false" :loading="logEditLoading">
          确认
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
  import {format, formatDateSimple8} from "@/utils/datetime"
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
        _file: null,
        size: 'small',
        uploadUrl: '',
        fileList: [],
        nextFileList: [],
        nextOperatorDict: [],
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
          ifOver:false
        },
        dialogImageUrl: '',
        nextDialogImageUrl: '',
        pictureDialogVisible: false,
        nextPictureDialogVisible: false,
        nextDialogVisible: false,
        drawBackDialogVisible: false,
        loading: false,
        logHisLoading: false,
        logEditLoading: false,
        drawBackEditLoading: false,
        logLoading: false,
        logDialogVisible: false,
        tableData: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20,
        },
        total: 0,
        logHisPageRequest: {
          current: 1,
          size: 5,
        }, logPageRequest: {
          current: 1,
          size: 100,
        },
        logHisTotal: 0,
        logTotal: 0,
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        nextOperatorShow: false,
        nextEditLoading: false,
        dataFormRules: {},
        nextFormRules: {
          nextNode: [
            {required: true, message: '请选择下一流程', trigger: 'blur'}
          ],
        },
        drawBackFormRules: {
          message: [
            {required: true, message: '请输入审批意见', trigger: 'blur'}
          ],
          agreeRefund: [
            {required: true, message: '请选择是否同意退款', trigger: 'blur'}
          ],
        },
        // 新增编辑界面数据
        dataForm: {
          curNodeName: '',
          logHistory: [],
          message: '',
          curOperatorName: ''
        },
        logDataForm: {
          eventId: '',
          curNodeName: '',
          logHistory: [],
          message: '',
        },
        nextForm: {
          eventId: '',
          curNodeName: '',
          nextNode: '',
          nextOperator: '',
          message: '',
          refundFee: '',
          curOperatorName: ''
        },
        drawBackForm: {
          orderId: '',
          eventId: '',
          businessName: '',
          payableAmt: '',
          receivableAmt: '',
          message: '',
          agreeRefund: '',
          handler: '',
          mountNode: '',
          curNodeId: ''
        },
        files: [],
        logRowContent: {},
        formData: new FormData(),
        logFiles: [],
        nextLogFiles: [],
        remoteNextNodeDictLoading: false,
        selectedNextNodeDict: [],
        nextNodeDict: [],
        handlerShow: true,
        chosenNode: {},
        operatorName: '',
        chosenOperator: '',
        chosenHandler: {},
        showRefund: false,
        showHandler: false,
        showNextOperator: false,
        handlerDict: [],
        data: {},
        userName: '',
        sys_aftersales_pending_view: false,
        sys_aftersales_pending_flow: false,
        sys_aftersales_pending_addlog: false,
        sys_aftersales_pending_drawback: false,
        sys_aftersales_pending_next: false,

      }
    },
    created() {
      this.sys_aftersales_pending_view = hasPermission('sys:aftersales:pending:view')
      this.sys_aftersales_pending_flow = hasPermission('sys:aftersales:pending:flow')
      this.sys_aftersales_pending_addlog = hasPermission('sys:aftersales:pending:addlog')
      this.sys_aftersales_pending_drawback = hasPermission('sys:aftersales:pending:drawback')
      this.sys_aftersales_pending_next = hasPermission('sys:aftersales:pending:next')
      this.userName = sessionStorage.getItem("userName")
      this.findPage(null);
    },
    methods: {// 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data
        }
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        this.pageRequest.userId = sessionStorage.getItem("userId")
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
        this.pageRequest.ifOver = this.filters.ifOver
        this.$api.workflow.findTodo(this.pageRequest).then((res) => {
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
      findLogPage: function (data, params) {
        if (data !== null) {
          this.logHisPageRequest = data
        }
        this.logHisLoading = true
        let callback = res => {
          this.logHisLoading = false
        }
        this.logHisPageRequest.eventId = params.eventId
        this.$api.workflow.findLog(this.logHisPageRequest).then((res) => {
          this.dataForm.logHistory = res.data.records;
          this.logHisTotal = res.data.total;
          this.logHisPageRequest.current = res.data.current;
          this.logHisPageRequest.size = res.data.size;
          callback(res)
        }).then(data != null ? data.callback : '')
          .catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            callback(res)
          })
      },
      handleDrawBack: function (params) {
        this.drawBackForm = {
          orderId: '',
          eventId: '',
          businessName: '',
          payableAmt: '',
          receivableAmt: '',
          message: '',
          agreeRefund: '',
          handler: '',
          mountNode: '',
          curNodeId: ''
        }
        this.drawBackDialogVisible = true
        let ele = document.getElementById('mountNode')
        if (ele != null) {
          ele.innerHTML = '';
        }
        this.drawBackForm = Object.assign({}, params)
        this.initG6(params)
      },
      showWorkFlow: function (params) {
        this.$router.push({name: '工作流日志', params: params})
      },
      handleNext: function (params) {
        this.nextLogFiles = []
        this.nextFileList = []
        this.nextForm = {
          eventId: '',
          curNodeId: '',
          curNodeName: '',
          nextNode: '',
          nextOperator: '',
          message: '',
          refundFee: '',
          curOperatorName: ''
        }
        this.nextDialogVisible = true
        this.operation = false
        this.nextForm = Object.assign({}, params)
        this.initNextNodeDict(params)
      },
      handleSendUploadRequest(file) {

      }, handleBeforeUpload: function () {
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.pictureDialogVisible = true;
      },
      handleRemove: function (file, fileList) {
        this.fileList = fileList
      },
      handleChange: function (file, fileList) {
        this.fileList = fileList
      },
      handleNextSendUploadRequest(file) {

      },
      handleNextPictureCardPreview(file) {
        this.NextDialogImageUrl = file.url;
        this.NextPictureDialogVisible = true;
      },
      handleNextRemove: function (file, fileList) {
        this.nextFileList = fileList
      },
      handleNextChange: function (file, fileList) {
        this.nextFileList = fileList
      },
      handleLog: function (params) {
        this.logFiles = []
        this.dataForm = {
          logHistory: [],
          message: '',
        }
        this.logRowContent = {}
        this.fileList = []
        this.dataForm = Object.assign({}, params)
        this.findLogPage(null, params)
        this.dialogVisible = true
        this.operation = false
        this.logRowContent = params
        this.formData = new FormData()
      },
      // 编辑
      submitLogForm: function () {

        let valid = (this.fileList != undefined && this.fileList.length != 0)
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true
            let formData = new FormData()
            for (let i = 0; i < this.fileList.length; i++) {
              formData.append('files', this.fileList[i].raw);
            }
            formData.append('operator', sessionStorage.getItem("userId"));
            formData.append('operatorName', sessionStorage.getItem("userName"));
            if (this.dataForm.message != undefined) {
              formData.append('message', this.dataForm.message);
            }
            formData.append('eventId', this.logRowContent.eventId);
            formData.append('curNodeId', this.logRowContent.curNodeId);
            this.$api.workflow.saveLog(formData).then((res) => {
              this.editLoading = false
              this.$message({message: '操作成功', type: 'success'})
              this.dialogVisible = false
              this.logFiles = []
            }).catch((res) => {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
              this.editLoading = false
              this.dialogVisible = false
              this.logFiles = []
            })
          })
        } else {
          this.$message({message: '请上传微信截图', type: 'error'})
        }
      },
      submitNextLogForm: function () {

        if (this.showRefund && this.nextForm.refundFee == undefined && this.nextForm.refundFee == '') {
          this.$message({message: '请输入退款金额！', type: 'error'})
          return
        }
        if (this.chosenNode.name != '结束' && (this.nextForm.nextOperator == undefined || this.nextForm.nextOperator == '')) {
          this.$message({message: '请选择对接人员！', type: 'error'})
          return
        }

        this.$refs.nextForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.nextEditLoading = true
              let formData = new FormData()
              formData.append('eventId', this.nextForm.eventId);
              formData.append('preNodeId', this.nextForm.curNodeId);
              formData.append('curNodeId', this.chosenNode.nodeId);
              if (this.chosenOperator == undefined || this.chosenOperator == null || this.chosenOperator == '') {
                formData.append('curOperator', '');
                formData.append('curOperatorName', '');
              } else {
                formData.append('curOperator', this.chosenOperator.id);
                formData.append('curOperatorName', this.chosenOperator.name);
              }
              formData.append('operator', sessionStorage.getItem("userId"));
              formData.append('operatorName', sessionStorage.getItem("userName"));
              if (this.nextForm.message != undefined) {
                formData.append('message', this.nextForm.message);
              }
              for (let i = 0; i < this.nextFileList.length; i++) {
                formData.append('files', this.nextFileList[i].raw);
              }
              if (this.showRefund && this.nextForm.refundFee != undefined && this.nextForm.refundFee != '') {
                formData.append('refundFee', this.nextForm.refundFee);
              }

              this.$api.workflow.saveNextEvent(formData).then((res) => {
                this.nextEditLoading = false
                this.$message({message: '操作成功', type: 'success'})
                this.nextDialogVisible = false
                this.nextLogFiles = []
                this.findPage(null)
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                this.nextEditLoading = false
                this.nextDialogVisible = false
                this.nextLogFiles = []
              })
            })
          }
        })
      },
      commitDrawBack: function () {
        if (this.showHandler) {
          if (this.drawBackForm.handler == undefined || this.drawBackForm.handler == '') {
            this.$message({message: '请选择处理人！', type: 'error'})
            return
          }
        }
        this.$refs.drawBackForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.drawBackEditLoading = true

              let request = {};
              request.eventId = this.drawBackForm.eventId
              request.operator = sessionStorage.getItem("userId")
              request.operatorName = sessionStorage.getItem("userName")
              request.message = this.drawBackForm.message

              if (this.drawBackForm.agreeRefund == '0') {
                request.handlerId = this.chosenHandler.id
                request.handlerName = this.chosenHandler.name
              } else {
                request.operator = sessionStorage.getItem("userId")
                request.operatorName = sessionStorage.getItem("userName")
              }
              request.curNodeId = this.drawBackForm.curNodeId

              this.$api.workflow.drawBack(request).then((res) => {
                this.drawBackEditLoading = false
                this.$message({message: '操作成功', type: 'success'})
                this.drawBackDialogVisible = false
              }).catch((res) => {
                this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
                this.drawBackEditLoading = false
                this.drawBackDialogVisible = false
              })
            })
          }
        })
      },
      // 时间格式化
      dateFormat: function (date) {
        return formatDateSimple8(date)
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.findPage(_this.pageRequest);
      },
      handleLogHisCurrentChange(val) {
        let _this = this;
        _this.logHisPageRequest.current = val;
        _this.findLogPage(_this.logHisPageRequest, this.dataForm);
      }, initNextNodeDict: function (params) {
        let nextNodeRequest = {};
        nextNodeRequest.businessId = params.businessId
        nextNodeRequest.curNode = params.curNodeId
        this.$api.workflow.findNextNode(nextNodeRequest).then((res) => {
          this.nextNodeDict = res.data;
          this.selectedNextNodeDict = res.data;
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      }, remoteNextNodeDict: function (param) {
        if (param != '') {
          this.remoteNextNodeDictLoading = true;
          setTimeout(() => {
            this.remoteNextNodeDictLoading = false;
            this.selectedNextNodeDict = this.nextNodeDict.filter(item => {
              return item.name.toLowerCase()
                .indexOf(param.toLowerCase()) > -1;
            });
          }, 200);
        } else {
          this.selectedNextNodeDict = [];
        }
      }, linkChange: function (val) {

        if (val != undefined && val != '') {
          let nodeName = this.selectedNextNodeDict.find(item => {
            return val == item.nodeId;
          }).name
          if (nodeName == '退款') {
            this.showRefund = false;
          }
          this.showNextOperator = true;
          if (nodeName == '结束') {
            this.showNextOperator = false;
          }
        }
        if (val != undefined && val != '') {
          this.nextOperatorShow = false;
          let role = this.selectedNextNodeDict.find(item => {
            return val == item.nodeId;
          }).role
          let nextOperatorRequest = {}
          nextOperatorRequest.role = role
          this.$api.workflow.findNextOperator(nextOperatorRequest).then((res) => {
            this.nextOperatorDict = res.data
          })
        } else {
          this.nextOperatorShow = true;
        }
        this.chosenNode = this.selectedNextNodeDict.find(item => {
          return val == item.nodeId;
        })
      }, handleItemChange: function (val) {
        this.chosenOperator = this.nextOperatorDict.find(item => {
          return val == item.id;
        })
      }, handleRefundChange: function (val) {
        this.showHandler = false
        if (val == '0') {
          this.showHandler = true
          this.$api.customer.findAllOperator(null).then((res) => {
            this.handlerDict = res.data
          })
        }
      }, handlerChange: function (val) {
        this.chosenHandler = this.handlerDict.find(item => {
          return val == item.id;
        })
      }, initG6(params) {
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
          _this.findHisLogPage(ev.item.get('model'))
        });
      },
      findHisLogPage: function (params) {
        this.logDialogVisible = true
        this.logLoading = true
        let callback = res => {
          this.logLoading = false
        }
        this.logPageRequest.eventId = this.drawBackForm.eventId
        this.logPageRequest.operator = params.operatorId
        this.$api.workflow.findLog(this.logPageRequest).then((res) => {
          this.logDataForm.logHistory = res.data.records;
          this.logTotal = res.data.total;
          this.logPageRequest.current = res.data.current;
          this.logPageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        })
      },
    },
    mounted() {
    }
  }
</script>

<style scoped>

</style>
