<template>
  <div class="page-container">
    <el-row>
      <el-col :span="24" align="left">
        <p><span></span>我的待办：
        </p>
      </el-col>
    </el-row>
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
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:role:view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="tableData" stripe stripe height="500" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="eventId" header-align="center" align="center" label="事件ID" v-if="false">
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
      <el-table-column fixed="right" label="操作" header-align="center" align="center" width="500">
        <template slot-scope="scope">
          <kt-button icon="fa fa-gears" label="流程日志" perms="sys:user:add" type="primary"
                     @click="showWorkFlow"/>
          <kt-button icon="fa fa-plus" label="添加备注" perms="sys:user:add" type="primary"
                     @click="handleLog(scope.row)"/>
          <!-- <kt-button icon="fa fa-retweet" label="客户详情" perms="sys:user:add" type="primary"
                      @click="handleDtl"/>-->
          <kt-button icon="fa fa-arrow-right" label="下一步" perms="sys:user:add" type="primary"
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
                    element-loading-text="$t('action.loading')" height="300">
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
          <!--    ref="uploadvideo"
              :action="upload_url"
              :auto-upload="false"
              :before-upload="newVideo"
              accept=".mp4"-->

          <!--<el-upload
            action="customize"
            ref="uploadFiles"
            accept='image/jpeg,image/gif,image/png'
            :auto-upload="false"
            list-type="picture-card"
            :on-change="handleChange"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :http-request="handleSendUploadRequest"
            style="float: left">
            <i class="el-icon-plus"></i>
          </el-upload>-->
          <el-upload
            ref="upload"
            :action="uploadUrl"
            name="attach"
            list-type="picture-card"
            :on-remove="handleRemove"
            :file-list="fileList"
            :auto-upload="false"
            :on-change="handleChange"
            :before-upload="handleBeforeUpload"
            :http-request="handleSendUploadRequest"
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
        <el-button :size="size" type="primary" @click.native="submitLogForm" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog width="40%" :visible.sync="nextDialogVisible" :close-on-click-modal="false">
      <el-form :model="nextForm" label-width="80px" :rules="dataFormRules" ref="nextForm" :size="size"
               label-position="center" align="center">
        <el-form-item label="当前流程: " prop="curProcess" label-width="100px">
          <span style="text-align: left;float: left;color: #a71d5d">{{nextForm.curProcess}}</span>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="下一流程" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择下一流程">
            <el-option label="社保代缴" value='0'></el-option>
            <el-option label="社保代开" value='1'></el-option>
            <el-option label="公积金代缴" value='2'></el-option>
            <el-option label="公积金代开" value='3'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="对接人员" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="张三丰" value='0'></el-option>
            <el-option label="李连杰" value='1'></el-option>
            <el-option label="萧敬腾" value='2'></el-option>
            <el-option label="薛之谦" value='3'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="备注: " label-width="100px">
          <el-input type="textarea" v-model="filters.desc" style="width: 500px"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="微信截图: " label-width="100px">
          <el-upload
            action="https://jsonplaceholder.typicode.com/posts/"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="pictureDialogVisible">
            <el-image :src="dialogImageUrl" alt=""/>
          </el-dialog>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="nextDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" @click.native="nextDialogVisible = false" :loading="editLoading">
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
  import {format, formatDateSimple8} from "@/utils/datetime"

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
        dialogImageUrl: '',
        pictureDialogVisible: false,
        nextDialogVisible: false,
        loading: false,
        logHisLoading: false,
        tableData: [],
        filterColumns: [],
        pageRequest: {
          current: 1,
          size: 20,
        },
        total: 0,
        logHisPageRequest: {
          current: 1,
          size: 20,
        },
        logHisTotal: 0,
        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {},
        // 新增编辑界面数据
        dataForm: {
          curNodeName: '',
          logHistory: [],
          message: '',
        },
        nextForm: {},
        files: [],
        logRowContent: {},
        formData: new FormData()
      }
    },
    created() {
      this.findPage(null);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.loading = true
        let callback = res => {
          this.loading = false
        }
        // this.pageRequest.userId = sessionStorage.getItem("userId")
        this.pageRequest.userId = '8'
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
          this.logHisPageRequest = data.logHisPageRequest
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
      handleAdd: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      handleEdit: function () {
        this.$router.push({path: '/preSales/customerDtl'})
      },
      showWorkFlow: function () {
        this.$router.push({path: '/preSales/workFlow'})
      },
      handleNext: function (params) {
        this.nextDialogVisible = true
        this.operation = false
        this.nextForm = Object.assign({}, params)
      },
      /* handleRemove: function () {
         this.formData = new FormData()
       },
       handleChange: function (file, fileList) {
         this.formData.append('files',file)
       },
       handlePictureCardPreview(file) {
         this.dialogImageUrl = file.url;
         this.pictureDialogVisible = true;
       }, handleSendUploadRequest(file) {

       },*/handleSendUploadRequest(file) {

      }, handleBeforeUpload: function () {
      },
      handleRemove: function () {
        this.fileList = []
      },
      handleChange: function (file, fileList) {
        this._file = file
        this.fileList.push(this._file)
      },
      handleLog: function (params) {
        this.dataForm = {
          logHistory: [],
          message: '',
        }
        this.logRowContent = {}
        this.files = []
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
            formData.append('operator', "8");
            formData.append('operatorName', "我是主管1");
            formData.append('message', this.dataForm.message);
            formData.append('eventId', this.logRowContent.eventId);

            this.$api.workflow.saveLog(formData).then((res) => {
              this.editLoading = false
              this.$message({message: '操作成功', type: 'success'})
              this.dialogVisible = false
            })
          }).catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            this.dialogVisible = false
          })
        } else {
          this.$message({message: '请上传微信截图', type: 'error'})
        }
      },
      // 时间格式化
      dateFormat: function (date) {
        return formatDateSimple8(date)
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
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.findPage(_this.pageRequest);
      },
      handleLogHisCurrentChange(val) {
        let _this = this;
        _this.logHisPageRequest.current = val;
        _this.findPage(_this.logHisPageRequest);
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>

</style>
