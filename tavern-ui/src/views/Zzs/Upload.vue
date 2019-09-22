<!--系统提醒设置-->
<template>
  <div class="page-container">
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="formInline" class="form-inline" v-if="sys_zzs_upload_view">
        <el-form-item>
          <el-form-item>
            <el-select v-model="formInline.status" clearable placeholder="上传状态">
              <el-option label="未开始" value='0'></el-option>
              <el-option label="处理中" value='1'></el-option>
              <el-option label="已完成" value='2'></el-option>
              <el-option label="失败" value='3'></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <kt-button icon="fa fa-search" type="primary" :label="$t('action.search')" @click="queryFileUpload(null)"
                       v-if="sys_zzs_upload_view"/>
          </el-form-item>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-cloud-upload" type="success" @click="uploadDialogVisible = true" label="文件上传"
                     v-if="sys_zzs_upload"/>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="tableData" stripe stripe height="600" size="mini" style="width: 100%;" v-loading="loading">
      <el-table-column
        prop="batchId"
        label="批次号"
        header-align="center"
        align="center">
        <template slot-scope="scope1">
          <router-link :to="{name:'增值税验证结果',params:{batchId:scope1.row.batchId}}" replace
                       style="color: #66B3FF;text-decoration: underline;">
            {{scope1.row.batchId}}
          </router-link>
        </template>

      </el-table-column>
      <el-table-column prop="packageName" header-align="center" align="center" label="文件名">
      </el-table-column>
      <el-table-column prop="fileCount" label="发票数量" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="uploadDate" label="上传时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态" align="center">
        <template slot-scope="scope3">
          <!--0:未开始,1:处理中,2:已完成，3:失败-->
          <span v-if="scope3.row.status == '0'">未开始</span>
          <span v-if="scope3.row.status == '1'">处理中</span>
          <span v-if="scope3.row.status == '2'">已完成</span>
          <span v-if="scope3.row.status == '3'">失败</span>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right" header-align="center" align="center" width="185" label="操作" v-if="sys_zzs_upload_del">
        <template slot-scope="scope">
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" type="danger"
                     @click="handleDelete(scope.row)" v-if="sys_zzs_upload_del"/>
        </template>
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                     :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                     style="float:right;">
      </el-pagination>
    </div>
    <!--文件上传弹出框-->
    <el-dialog
      class="dialog"
      title="文件上传"
      :visible.sync="uploadDialogVisible"
      width="30%"
      @close='closeDialog'>
      <div>
        <el-col :span="24">
          <el-form ref="ruleForm" :model="formInline3" class="form-inline">
            <div>
              <el-form-item
                label="文件上传"
                label-width="100px">
                <el-upload
                  ref="upload"
                  :action="uploadUrl"
                  name="attach"
                  :on-remove="handleRemove"
                  :file-list="fileList"
                  :auto-upload="false"
                  :on-change="handleChange"
                  :before-upload="handleBeforeUpload"
                  :http-request="handleSendUploadRequest">
                  <el-button class="el-icon-upload" size="medium" type="primary">上传文件</el-button>
                </el-upload>
              </el-form-item>
            </div>
            <el-form-item label="批次文件名" label-width="100px">
              <el-input class="editInput" v-model="sid" :readonly="true"></el-input>
            </el-form-item>
            <el-form-item v-show="showPercentage" label="完成进度" label-width="100px" class="form-item">
              <el-progress :text-inside="true" :stroke-width="14" :percentage="percentage" status="success"
                           class="form-progress"></el-progress>
            </el-form-item>
          </el-form>
          <div class="tips-import">
            <span>注意</span>
            <ul>
              <li>文件大小不可超过50M,默认请使用.zip压缩包</li>
              <li>如果文件比较大，提交时间会相对较长，请耐心等待提交完成</li>
            </ul>
          </div>

        </el-col>
      </div>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button type="danger" @click="handleCommit" :disabled="isCommit" size="small">确认</el-button>
        <el-button type="warning" @click="handleCancel" size="small"
                   style="margin-left: 10px ">取消
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
  import Dtl from "@/views/Zzs/Dtl"
  import {genNonDuplicateID} from '@/utils/common'
  import {hasPermission} from '@/permission/index.js'

  export default {
    components: {
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog,
      Dtl
    },
    props: {
      columns: Array, // 表格列配置
      data: Object, // 表格分页数据
      permsEdit: String,  // 编辑权限标识
      permsDelete: String,  // 删除权限标识
      size: { // 尺寸样式
        type: String,
        default: 'mini'
      },
      align: {  // 文本对齐方式
        type: String,
        default: 'left'
      },
      maxHeight: {  // 表格最大高度
        type: Number,
        default: 420
      },
      height: {  // 表格最大高度
        type: Number,
        default: 250
      },
      showOperation: {  // 是否显示操作组件
        type: Boolean,
        default: true
      },
      border: {  // 是否显示边框
        type: Boolean,
        default: false
      },
      stripe: {  // 是否显示斑马线
        type: Boolean,
        default: true
      },
      highlightCurrentRow: {  // // 是否高亮当前行
        type: Boolean,
        default: true
      },
      showOverflowTooltip: {  // 是否单行显示
        type: Boolean,
        default: true
      },
      showBatchDelete: {  // 是否显示操作组件
        type: Boolean,
        default: true
      },
      showEdit: {  // 是否显示操作组件
        type: Boolean,
        default: true
      }
    },
    data() {
      return {
        uploadUrl: '',
        _file: null,
        /*查询区域*/
        formInline: {
          status: ''
        },
        loading: false,
        formInline2: {},
        formInline3: {},
        isEnable: true,
        tableData: [],
        tableData2: [],
        hideRow: false,
        pageRequest: {
          current: 1,
          size: 20
        },
        total: 0,
        uploadDialogVisible: false,
        showPercentage: false,
        isStop: true,
        isCommit: false,
        uploading: false,
        loadingText: '上传进度',
        fileList: [],
        percentage: 0.0,
        getPercent: null,
        sid: '', batchId: '',
        sys_zzs_upload: false,
        sys_zzs_upload_view: false,
        sys_zzs_upload_del: false
      }
    },
    created() {
      this.sys_zzs_upload_view = hasPermission('sys:zzs:upload:view')
      this.sys_zzs_upload = hasPermission('sys:zzs:upload')
      this.sys_zzs_upload_del = hasPermission('sys:zzs:upload:del')
      this.queryFileUpload(null);
    },
    methods: {
      /*设置表头背景色*/
      tableHeaderColor({row, column, rowIndex, columnIndex}) {
        if (rowIndex === 0) {
          return 'background-color: #ecf5ff;color:#333;'
        }
      },
      handleRemove: function () {
        this.fileList = []
      },
      handleChange: function (file, fileList) {
        this._file = file
        this.fileList.push(this._file)
        if (this.fileList.length > 1) this.fileList.shift()
        this.sid = file.name
        this.isCommit = false
      },
      //查询
      queryFileUpload(data) {
        let _this = this
        if (data !== null) {
          _this.pageRequest = data
        }
        _this.loading = true
        let callback = res => {
          _this.loading = false
        }
        this.pageRequest.status = this.formInline.status
        this.pageRequest.fileType = 3
        this.$api.zzs.findPage(this.pageRequest).then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
          this.pageRequest.current = res.data.current;
          this.pageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        });
      },
      //覆盖原有的上传action
      handleSendUploadRequest() {
        let _this = this;
        _this.getPercent = setInterval(_this.handlePercentage, 500);
        _this.$message.info('请求已提交，请耐心等待处理完成！');
        _this.showPercentage = true;
        let formData = new FormData()
        formData.append('file', _this._file.raw)
        formData.append('batchId', _this.batchId)
        _this.$api.zzs.uploadFile(formData).then(function (response) {
          _this.isCommit = false
          _this.uploadDialogVisible = false;
          _this.$message.success("提交完成！");
          clearInterval(_this.getPercent);
          _this.percentage = 0;
          _this.$refs.upload.clearFiles();
          _this.sid = ''
          _this.showPercentage = false;
          _this.queryFileUpload(null);
        }).catch((res) => {
          _this.isStop = true;
          clearInterval(_this.getPercent);
          _this.percentage = 0;
          _this.showPercentage = false;
          _this.$message.error(res.response.data.retMessage || "系统异常");
        })
      },
      handleBeforeUpload: function () {
        this.isCommit = true
      },
      handleCommit() {
        let _this = this
        if (!this.isCommit) {
          if (_this.fileList.length < 1) {
            _this.$message({
              message: "请上传文件！",
              type: 'warning'
            })
            return false;
          }
          const fileName = _this._file.name.substring(_this._file.name.lastIndexOf('.') + 1).toUpperCase();
          const isLt50M = _this._file.size / 1024 / 1024 < 50;
          if (fileName != 'ZIP') {
            this.$message.error('上传压缩包只能是 .Zip 格式!');
            return false;
          }
          if (!isLt50M) {
            this.$message.error('上传压缩包大小不能超过 50MB!');
            return false;
          }
          _this.isCommit = true;
          _this.batchId = genNonDuplicateID(10);
          _this.isStop = false;
          _this.handleSendUploadRequest();
        }
      },
      handlePercentage() {
        let _this = this;
        _this.$api.fileManager.findPercentage(Object.assign({
          batchId: _this.batchId
        })).then((res) => {
          if (res.retCode == 0 && res.data.status != -1) {
            _this.percentage = res.data.percentage;
          } else {
            clearInterval(_this.getPercent);
          }
        }).catch((res) => {
          clearInterval(_this.getPercent);
          _this.$message.error(res.response.data.retMessage || "系统异常");
        })
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.queryFileUpload( _this.pageRequest);
      },
      // 删除
      handleDelete: function (data) {

        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.loading = true
          let callback = res => {
            if (res.retCode == 0) {
              this.$message({message: '删除成功', type: 'success'})
              this.queryFileUpload(null)
            } else {
              this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            }
            this.loading = false
          }
          this.$api.zzs.batchDelete(data.batchId).then(data != null ? callback : '')
        }).catch(() => {
        })
      },
      closeDialog: function () {
        let _this = this;
        _this.percentage = 0;
        _this.$refs.upload.clearFiles();
        _this.sid = ''
        _this.showPercentage = false;
      },
      handleCancel: function () {
        let _this = this;
        _this.percentage = 0;
        _this.$refs.upload.clearFiles();
        _this.sid = ''
        _this.showPercentage = false;
        _this.uploadDialogVisible = false
      }
    }
  }
</script>

<style scoped="scoped">

  /*查询区域*/
  .form-inline {
    padding-top: 15px;
    padding-left: 0;
  }

  .form-inline >>> .el-form-item__label {
    font-size: 12px !important;
  }

  .el-form-item >>> .el-input__inner {
    height: 30px !important;
    padding: 0 5px !important;
    font-size: 12px !important;
  }

  .dialog >>> .el-dialog__title {
    font-size: 14px !important;
    line-height: 15px !important;
  }

  .dialog >>> .el-dialog__body {
    font-size: 12px !important;
    padding: 0 20px;
    height: 400px;
  }

  .editUL li {
    line-height: 34px;
  }

  .editUL li span {
    display: inline-block;
    width: 80px;
    text-align: right;
  }

  .selectInput >>> .el-input__inner {
    height: 28px !important;
    line-height: 28px !important;
    font-size: 12px !important;
  }

  .selectInput >>> .el-input__icon {
    line-height: 28px !important;
  }

  .editInput {
    width: 170px !important;
  }

  .editInput >>> .el-input__inner {
    height: 28px !important;
  }

  .dialog-footer button {
    padding: 9px 10px !important;
    font-size: 14px !important;
  }

  .tips-import {
    position: absolute;
    color: coral;
  }

  .tips-import span {
    font-weight: bold;
    font-size: 14px;
  }

  .tips-import li {
    font-size: 14px;
  }

  .form-progress {
    line-height: 40px;
    max-width: 300px;
  }

</style>
