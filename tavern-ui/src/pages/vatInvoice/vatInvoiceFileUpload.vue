<!--系统提醒设置-->
<template>
  <div class="mainWrap">
    <el-col :span="24" class="main-top">
      <p><span class="short-line"></span>增值税上传管理</p>
    </el-col>
    <el-col :span="24" style="padding-left: 15px;">
      <el-form :inline="true" :model="formInline" class="form-inline">
        <el-form-item>
          <el-form-item label="上传状态">
            <el-select v-model="formInline.status" placeholder="上传状态">
              <el-option label="未开始" value='0'></el-option>
              <el-option label="处理中" value='1'></el-option>
              <el-option label="已完成" value='2'></el-option>
              <el-option label="失败" value='3'></el-option>
            </el-select>
          </el-form-item>
          <el-button type="primary" class="searchBtn" @click="querySms">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-col :span="24" class="btnWrap">
            <el-button type="success" class="el-icon-upload" @click="uploadDialogVisible = true">文件上传</el-button>
          </el-col>
        </el-form-item>
      </el-form>
    </el-col>
    <el-table
      :data="tableData"
      border stripe
      height="350"
      style="width: 100%"
      :header-cell-style="tableHeaderColor" size="mini">
      <el-table-column
        prop="batchId"
        label="批次号"
        type="index"
        width="100" align="center">
        <template slot-scope="scope1">
          <router-link :to="{path:'/vatInvoice/vatInvoiceFileDtl',query:{batchId:scope1.row.batchId}}"
                       style="color: #66B3FF;text-decoration: underline;">
            {{scope1.row.batchId}}
          </router-link>
        </template>

      </el-table-column>
      <el-table-column
        prop="packageName"
        label="文件名" align="center">
      </el-table-column>
      <el-table-column
        prop="fileCount"
        label="发票数量" align="center">
      </el-table-column>
      <el-table-column
        prop="uploadDate"
        label="上传时间" align="center">
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
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="current"
      :page-sizes="[10, 50, 100, 200]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <!--文件上传弹出框-->
    <el-dialog
      class="dialog"
      title="文件上传"
      :visible.sync="uploadDialogVisible"
      width="80%">
      <div>
        <el-col :span="24">
          <el-form ref="ruleForm" :rule = "rule" :model="formInline3" class="form-inline">
            <div>
              <el-form-item v-loading='uploading'
                            :element-loading-text='loadingText'
                            element-loading-spinner="el-icon-loading"
                            label="文件上传"
                            label-width="100px">
                <el-upload class="upload-block"
                           action="customize"
                           :before-upload="beforeAvatarUpload"
                           :show-file-list='false'
                           :http-request="uploadFile">
                  <el-button class="el-icon-upload" size="medium" type="primary">上传文件</el-button>
                </el-upload>
              </el-form-item>
            </div>
            <el-form-item label="批次文件名" label-width="100px">
              <el-input class="editInput" v-model="sid" readonly="true"></el-input>
            </el-form-item>
            <el-form-item v-show="showPercentage" label="完成进度" label-width="100px" class="form-item">
              <el-progress :text-inside="true" :stroke-width="14" :percentage="percentage" status="success" class="form-progress"></el-progress>
            </el-form-item>
          </el-form>
          <div class="tips-import">
            <span>注意</span>
            <ul>
              <li>文件大小不可超过50M,默认请使用.zip压缩包</li>
              <li>完成进度小于20%时，正在处理文件，无法终止操作</li>
              <li>如果文件比较大，提交时间会相对较长，请耐心等待提交完成，或者在进度超过20%后点击"终止"按钮终止提交</li>
            </ul>
          </div>

        </el-col>
      </div>
      <span slot="footer" class="dialog-footer">
		     <el-button type="warning" @click="handleStop()" :disabled="isStop" size="small" style="margin-left: 10px ">终止</el-button>
		     <el-button type="danger" @click="handleCommit('ruleForm')" :disabled="isCommit" size="small" >确认</el-button>
		  </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'vatInvoiceFileUpload',
    props: ['uploadText'],
    data() {
      return {
        /*查询区域*/
        formInline: {
          status: ''
        },
        formInline2: {},
        formInline3: {},
        isEnable: true,
        tableData: [],
        tableData2: [],
        hideRow: false,
        current: 1,
        total: 0,
        size: 10,
        showPercentage: true,
        isStop: true,
        isCommit: false,
        sid: '',
        ed_depotStatus: '',
        add_depotStatus: '',
        uploadDialogVisible: false,
        add_can_retail_sale: '',
        depotStatus: 'TRUE',
        search: '',
        add_sourceWhsId: '',
        add_depotName: '',
        add_depot_no: '',
        //新增
        add_oper_type: '',
        add_pur_model: '',
        add_depot_status: '',
        add_depotLevel: '',
        add_replenish_unit: '',
        add_surgery_depot: '',
        add_sys_replenish_rules_id: '',
        add_unksk: '',
        add_com_depot_type_id: '',
        add_default_storager: '',
        //修改
        ed_oper_type: '',
        ed_can_retail_sale: '',
        ed_pur_model: '',
        ed_depot_status: '',
        ed_depotLevel: '',
        ed_replenish_unit: '',
        ed_surgery_depot: '',
        ed_sys_replenish_rules_id: '',
        ed_unksk: '',
        ed_com_depot_type_id: '',
        ed_default_storager: '',
        canzhaoDialogVisibleFlag: '',
        uploading: false,
        loadingText: '上传进度',
        fileList: []
      }
    },
    created() {
      this.queryFileUpload();
    },
    methods: {
      /*设置表头背景色*/
      tableHeaderColor({row, column, rowIndex, columnIndex}) {
        if (rowIndex === 0) {
          return 'background-color: #ecf5ff;color:#333;'
        }
      },
      //查询
      queryFileUpload() {
        let _this = this;

        _this.ajaxPost({
          url: '/alleria/packages/page',
          data: {
            fileType: 3,
            status: _this.formInline.status,
            page: _this.current,
            size: _this.size
          },
          success(data) {
            if (data.statuCode == 200) {
              _this.tableData.reSet(data.data.records);
              _this.total = data.total;
            } else {
              _this.$message.error('获取文件上传信息失败,原因为:' + data.msg);
            }
          },
          error(err) {
            _this.$message.error('获取文件上传遇到异常,异常为:' + err);
          }
        });
      },
      //覆盖原有的上传action
      uploadFile(file) {
        /* let _this = this;
         _this.upload({
           url: '/alleria/packages/upload',
           data: {
             file: file
           },
           success(data) {
               _this.$message.info('获取文件上传信息成功');
           },
           error(err) {
             _this.$message.error('获取文件上传遇到异常,异常为:' + err);
           }
         })*/
        console.log(file)
      },
      beforeAvatarUpload(file) {
        const isJPGOrZip = file.type === 'image/jpeg' || 'zip';
        const isLt30M = file.size / 1024 / 1024 < 30;

        if (!isJPGOrZip) {
          this.$message.error('上传头像图片只能是 JPG/Zip 格式!');
        }
        if (!isLt30M) {
          this.$message.error('上传头像图片大小不能超过 30MB!');
        }
        return isJPGOrZip && isLt30M;
      },
      handleStop() {
      },
      handleCommit(ruleForm) {
      },

      //添加
      addSms() {
        let _this = this;
        _this.addDialogVisible = true;
        var errMsg = '';
        var flag = true;
        if (_this.add_depotName == '') {
          flag = false;
          _this.$message.error('请填写仓间名称');
        }
        if (_this.formInline2.add_com_depot_type_id == '') {
          flag = false;
          _this.$message.error('请填写仓间类型');
        }
        if (flag) {
          _this.ajaxPost({
            url: '/depot/addDepot',
            data: {
              depotName: _this.add_depotName,
              depotNo: _this.add_depotNo,
              depotPostAddrName: _this.add_depot_post_addr_name,
              sourceWhsId: _this.add_sourceWhsId,
              operType: _this.add_oper_type,
              canRetailSale: _this.add_can_retail_sale,
              purModel: _this.add_pur_model,
              depotStatus: _this.add_depot_status,
              depotLevel: _this.add_depotLevel,
              replenish_unit: _this.add_replenish_unit,
              replenishUnit: _this.add_surgery_depot,
              sysReplenishRulesId: _this.add_sys_replenish_rules_id,
              unksk: _this.add_unksk,
              comDepotTypeId: _this.formInline2.add_com_depot_type_id
            },
            success(data) {
              if (data.code == 0) {
                _this.$message.success(data.msg);
                _this.queryFileUpload();
                _this.clearaAddMsg();
              } else {
                _this.$message.Error(data.msg);
              }
            },
            error(err) {
              _this.$message.error('获取短信提醒设置遇到异常,异常为:' + err);
            }
          });
        }
      },
      openDetails(row) {
        //具体操作
        this.$message.error(this.row.number);
      },
      //清空数据
      clearaAddMsg() {
        let _this = this;
        _this.addDialogVisible = false;
        _this.radio = '2001';
      },
      //编辑
      editUser(row) {
        let _this = this;
        var radios;
        if (row['depotStatus'] == 'TRUE') {
          radios = 'TRUE'
        } else {
          radios = 'FALSE'
        }
        _this.ed_depotLevel = row.depotLevel.toString();
        _this.ed_oper_type = row.operType.toString();
        _this.ed_can_retail_sale = row.canRetailSale.toString();
        _this.ed_pur_model = row.purModel.toString();
        _this.ed_depot_status = row.depotStatus.toString();
        _this.ed_replenish_unit = row.replenishUnit == null ? null : row.replenishUnit.toString();
        _this.ed_surgery_depot = row.surgeryDepot.toString();
        _this.ed_sys_replenish_rules_id = row.sysReplenishRulesId == null ? null : row.sysReplenishRulesId.toString();
        _this.ed_unksk = row.unksk.toString();

        _this.ed_depot_post_addr_name = row['depotPostAddrName'];
        _this.ed_sourceWhsId = row['sourceWhsId'];
        _this.dialogVisible = true;//弹出修改框
        //_this.ed_depotLevel=row['depotLevel'];
        _this.ed_depotName = row['depotName'];
        _this.formInline3.ed_com_depot_type_id = row['comDepotTypeId'];
        _this.ed_depotNo = row['depotNo'];
        //_this.ed_depotStatus=radios;
        _this.sid = row['comDepotId'];
        _this.ed_version = row['version'];
        ///_this.queryCusts();
        //_this.ed_custCode=row['custcode'];
      },
      //保存编辑
      editSms() {
        let _this = this;
        var flag = true;
        if (_this.ed_depotName == '') {
          flag = false;
          _this.$message.error('请填写科室名称');
        }
        if (_this.formInline3.ed_com_depot_type_id == '') {
          flag = false;
          _this.$message.error('请填仓间类型');
        }
        if (flag) {
          _this.ajaxPost({
            url: '/depot/editSavaDepot',
            data: {
              comDepotId: _this.sid,
              version: _this.ed_version,
              depotPostAddrName: _this.ed_depot_post_addr_name,
              depotName: _this.ed_depotName,
              comDepotTypeId: _this.formInline3.ed_com_depot_type_id,
              sourceWhsId: _this.ed_sourceWhsId,
              operType: _this.ed_oper_type,
              canRetailSale: _this.ed_can_retail_sale,
              purModel: _this.ed_pur_model,
              depotStatus: _this.ed_depot_status,
              depotLevel: _this.ed_depotLevel,
              replenish_unit: _this.ed_replenish_unit,
              replenishUnit: _this.ed_surgery_depot,
              sysReplenishRulesId: _this.ed_sys_replenish_rules_id,
              unksk: _this.ed_unksk,
            },
            success(data) {
              if (data.code == 0) {
                _this.$message.success(data.msg);
                _this.queryFileUpload();
                _this.dialogVisible = false;
              } else {
                _this.$message.Error(data.msg);
              }
            },
            error(err) {
              _this.$message.error('获取短信提醒设置遇到异常,异常为:' + err);
            }
          });
        }
      },
      //删除
      handleClick(sid) {
        let _this = this;
        var sids = JSON.stringify(sid);

        _this.$confirm('你确定删除吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.ajaxPost({
            url: '/depot/delDepot',
            data: {
              sid: sids
            },
            success(data) {
              if (data.code == 0) {
                _this.$message.success(data.msg);
                _this.queryFileUpload();
              } else {
                _this.$message.Error(data.msg);
              }
            },
            error(err) {
              _this.$message.error('删除短信提醒设置遇到异常,异常为:' + err);
            }
          });
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleSizeChange(val) {
        let _this = this;
        _this.size = val;
        _this.queryFileUpload();
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.current = val;
        _this.queryFileUpload();
      },

    }
  }
</script>

<style scoped="scoped">

  .short-line {
    display: inline-block;
    height: 12px;
    border-right: 2px solid #007FE1;
    margin-right: 8px;
    position: relative;
    top: 2px;
  }

  /*查询区域*/
  .form-inline {
    padding-top: 15px;
    padding-left: 0;
  }

  .form-inline >>> .el-form-item__label {
    font-size: 12px !important;
  }

  .el-form-item {
    margin-bottom: 12px !important;
  }

  .el-form-item >>> .el-input__inner {
    height: 30px !important;
    padding: 0 5px !important;
    font-size: 12px !important;
  }

  .searchBtn {
    padding: 9px 26px !important;
    font-size: 14px !important;

  }

  .el-select {
    width: 170px;
  }

  .btnWrap {
    display: flex;
    justify-content: flex-start;
    margin-bottom: 15px;
    padding-left: 15px;
  }

  .btn1 {
    padding: 9px 26px !important;
    font-size: 14px !important;
  }

  .el-table {
    margin-bottom: 15px;
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

  .editUL {
    border-top: 1px solid #ddd;
    padding-top: 10px;
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
    text-align: center;
  }

  .mainWrap {
    background: #fff;
    margin-left: 20px;
    overflow: hidden;
    height: 100%;
    overflow-y: auto;
  }
  .tips-import{
    position: absolute;
    color: coral;
  }
  .tips-import span{
   font-weight: bold;
    font-size: 14px;
  }
  .tips-import li{
    font-size: 14px;
  }
  .form-progress {
    line-height: 40px;
    max-width: 300px;
  }

</style>
