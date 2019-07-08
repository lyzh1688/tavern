<template>
  <div class="mainWrap">
    <el-row>
      <el-col :span="24" class="main-top">
        <p><span class="short-line"></span>增值税发票内容修正
        </p>
      </el-col>
    </el-row>
    <div class="tableWrap">

      <el-form :inline="true" :model="formInline" class="form-inline" style="margin-left: 200px">
        <el-form-item label="发票号码" label-width="100px">
          <el-input v-model="formInline.invoiceId" size='small' placeholder="请输入发票号码" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="发票日期" class="date-item" style="margin-left: 100px" label-width="100px">
          <el-date-picker v-model="formInline.invoiceDate" align="right" type="date" size='small' placeholder="选择日期"
                          :picker-options="pickerOptions1">
          </el-date-picker>
        </el-form-item>
      </el-form>
        <el-form :inline="true" :model="formInline1" class="form-inline" style="margin-left: 200px">
        <el-form-item label="发票代码" label-width="100px">
          <el-input v-model="formInline1.invoiceCode" size='small' placeholder="请输入发票代码" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="校验码" style="margin-left: 100px" label-width="100px">
          <el-input v-model="formInline1.checkCode" size='small' placeholder="请输入校验码" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline1" class="form-inline" style="margin-left: 200px">
        <el-form-item label="不含税金额" label-width="100px">
          <el-input v-model="formInline1.taxFreeAmount" size='small' placeholder="请输入不含税金额" style="width: 200px;align-content: left"></el-input>
        </el-form-item>
      </el-form>
      <el-form  :inline="true" :model="formInline1" class="form-inline" style="margin-left: 300px">
        <el-form-item>
          <el-image v-model="formInline1.image" style="width: 600px;height: 300px" src="http://1233trip-miyako.oss-cn-shanghai.aliyuncs.com/images/20190115161501-%E3%81%BE%E3%82%8B%E3%81%A1%E3%81%8F%E3%80%80%E9%81%8B%E8%BB%A2%E6%89%8B1.jpg?Expires=4701140101&OSSAccessKeyId=LTAIl5Qet79YhOKh&Signature=yaEPXHB1YKlQNf61ePUABWzRm8g%3D"></el-image>
        </el-form-item>
         <el-form-item>
           <i class="el-icon-refresh-right" style="margin-left: 100px;font-size: 50px; font-weight: bold;color: #66B3FF;margin-top: 100px"></i>
         </el-form-item>
      </el-form>
      <el-form  :model="formInline1" class="form-inline" style="margin-left: 400px;">
        <el-form-item size="large" style="margin-left: 40px">
          <el-button type="primary" @click="confim">确定</el-button>
          <el-button style="margin-left: 80px">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
  export default {
    name: 'addInvoice',
    data() {
      return {

        //弹框表格
        tableData: [],
        //新增品种弹框查询
        //新增品种弹框搜索input
        searchValue: '',
        //新增品种弹框
        centerDialogVisible: false,
        vloading: false,
        tloading: false,
        //上传产品合格证弹框
        dialogVisible: false,
        //上传发票
        dialogImageUrl: '',
        imageVisible: false,
        /*日期*/
        pickerOptions1: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        value1: '',
        value2: '',
        /*表单*/
        formInline: {
          invoiceNo: '',
          invoiceDate: '',
          invoiceCode: '',
          checkCode: '',
          taxFreeAmount: ''
        },
        formInline1: {
          invoiceCode: '',
          checkCode: '',
          taxFreeAmount: ''
        },
        invoiceUrl: [],
        billUrl: [],
        /**新增品种的时候医院列表**/
        hospitalList: [],
        tableData3: [],
        batchNumber: '',
        /**用来保存点击的医院信息**/
        custCode: '',
        itemCode: '',
        itemName: '',
        manuFacturer: '',
        specification: '',
        isDisable: true, //默认在没勾选医院信息的时候是禁用右边的
        /**
         * 新增品种部分分页参数
         */
        itemPage: 1,
        itemPageSize: 10,
        itemTotal: 0,
        multipleSelection: [],
        dialogQualified: false,
        tempIndex: '', //用来临时记录当前在上传注册证的行序号
        tempUrl: [],
        /**用来记录已经上传的文件信息**/
        images: []
      }
    },
    methods: {
      /*设置表头背景色*/
      tableHeaderColor({row, column, rowIndex, columnIndex}) {
        if (rowIndex === 0) {
          return 'background-color: #dce3f2;color:#333;'
        }
      },
      uploadInvoice(content) {
        this.uploadCommon(content, 0);
      },
      uploadBill(content) {
        this.uploadCommon(content, 1);
      },
      uploadQualified(content) {
        this.uploadCommon(content, 2);
      },
      //点击触发上传合格证
      clickQualified(index) {
        this.tempIndex = index;
        if (this.isNull(this.tableData3[this.tempIndex].TEMPURL)) {
          this.tableData3[this.tempIndex].TEMPURL = [];
        }
        this.dialogQualified = true;
      },
      //当前发票影像界面上传发票和上传随货清单的通用方法,其实就是多传个参数，以及最后返回的url存放的数组不一样
      //暂定type == 0为发票，1为随货清单
      uploadCommon(content, type) {
        var _this = this;
        var uploadFile = new FormData;
        //默认后台接受的文件名必须叫uploadFile
        uploadFile.append('uploadFile', content.file);
        if (type == 0) {
          uploadFile.append('type', '发票');
        } else if (type == 1) {
          uploadFile.append('type', '发票/随货清单');
        } else if (type == 2) {
          uploadFile.append('type', '发票/产品合格证');
        }
        //如果是/开始，说明没设置前缀，默认自动添加
        this.$ajax({
          url: _this.baseUrl + '/invoiceShadow/upload.html',
          method: 'post',
          data: uploadFile
        })
          .then(function (data) {
            data = data.data;
            if (data.statuCode == 200) {
              content.onSuccess('成功');
              //如果是成功，记录一下当前文件的返回的地址
              if (type == 0) {
                _this.invoiceUrl.push(data.imageUrl);
              } else if (type == 1) {
                _this.billUrl.push(data.imageUrl);
              } else if (type == 2) {
                _this.tempUrl.push(data.imageUrl);
              }
            } else {
              //content.onError('上传文件失败');
              _this.$message.error('文件：' + content.file.name + '上传失败，请重试');
            }
          })
          .catch(function (err) {
            content.onError('上传文件失败');
          });
      },
      beforeUpload(file) {
        var _this = this;
        //上传之前验证一下图片大小不能超过2Mb
        const isImage = file.type.indexOf('image/') > -1;
        const isLt2m = file.size / 1024 / 1024 < 2;
        if (!isImage) {
          _this.$message.error('当前页面只可以上传图片，文件：' + file.name + ',不符合图片格式已经取消该文件上传!!');
        }
        if (!isLt2m) {
          _this.$message.error('上传文件大小不可以超过2M，当前文件为' + file.name + ',超过指定大小，请修改后再上传');
        }
        return (isImage && isLt2m);
      },
      onChange(file, fileList) {
      },
      handleRemove(file, fileList) {
        return true;
      },
      //删除之前可以禁止删除
      beforeRemove(file, fileList) {
        return true;
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.imageVisible = true;
      },
      itemSizeChange(size) {
        this.itemPageSize = size;
        this.queryItemByHosp();
      },
      itemCurrentChange(current) {
        this.itemPage = current;
        this.queryItemByHosp();
      },
      /**点击搜索符合的医院**/
      queryHospital() {
        this.queryAllHospital();
      },
      /**点击新增品种按钮**/
      addItem() {
        this.centerDialogVisible = true;
        //开始查询当前医院信息
        this.queryAllHospital();
      },
      /**查询当前供应商下面所有的医院信息**/
      queryAllHospital() {
        const _this = this;
        _this.vloading = true;
        _this.ajaxPost({
          url: '/invoiceShadow/queryHospitalBySupl',
          data: {
            searchValue: _this.searchValue
          },
          success: function (data) {
            _this.vloading = false;
            _this.hospitalList.reSet(data);
          },
          error: function (err) {
            _this.vloading = false;
            _this.$message.error('加载医院列表失败，请稍后再试');
          }
        });
      },
      //新增品种dialog的查询按钮
      onQueryItem() {
        this.queryItemByHosp();
      },
      //新增品种dialog的table改变的方法
      handleSelectionChange(selection) {
        this.multipleSelection = selection;
      },
      /**医院列表单击事件，根据当前传递的医院编码查询对应的item信息**/
      hospitalClick(custCode) {
        this.custCode = custCode;
        this.isDisable = false;
        //开始查询，调用通用查询方法
        this.queryItemByHosp();
      },
      /**根据选中的医院code，上方过滤栏等信息过滤查询出对应的品种信息**/
      queryItemByHosp() {
        this.tloading = true;
        const _this = this;
        _this.ajaxPost({
          url: '/invoiceShadow/queryItemsByHosp',
          data: {
            custCode: _this.custCode,
            itemCode: _this.itemCode,
            itemName: _this.itemName,
            manuFacturer: _this.manuFacturer,
            specification: _this.specification,
            page: _this.itemPage,
            pageSize: _this.itemPageSize
          },
          success: function (data) {
            _this.tloading = false;
            if (data.statuCode == 200) {
              //加载数据到table
              _this.tableData.reSet(data.rows);
              _this.itemTotal = data.total;
            } else {
              _this.$message.error('加载品种列表失败，请稍后再试');
            }
          },
          error: function (err) {
            _this.tloading = false;
            _this.$message.error(err);
          }
        });
      },
      /**新增品种dialog的确认按钮，将选中的item放到主页面的table中，并且重置整个form**/
      confim() {
        const _this = this;
        //确认的时候把这些数据存入外部数组中，需要先做这个操作，再去清空
        for (var i = 0; i < _this.multipleSelection.length; i++) {
          const item = _this.multipleSelection[i];
          var isPush = false;
          for (var j = 0; j < _this.tableData3.length; j++) {
            const citem = _this.tableData3[j];
            if (JSON.stringify(item) == JSON.stringify(citem)) {
              _this.$message.error('品种为:' + item.ITEMNAME + '已经存在于当前列表，请勿重复添加');
              isPush = true;
            }
          }
          if (!isPush) {
            _this.tableData3.push(item);
          }
        }
        this.centerDialogVisible = false;
      },
      /**关闭新增品种的dialog的时候触发的业务，所有参数默认清空**/
      closeItemDialog() {
        this.hospitalList.clear();
        this.multipleSelection = [];
        this.searchValue = '';
        this.custCode = '';
        this.itemCode = '';
        this.itemName = '';
        this.manuFacturer = '';
        this.specification = '';
        this.itemPage = 1;
        this.itemPageSize = 10;
        this.isDisable = true;
        this.tableData.clear();
      },
      //合格证上传dialog关闭的时候清空数据
      closeQualifiedDialog() {
        this.$refs['qualifiedUpload'].clearFiles();
      },
      //合格证上传dialog确认按钮触发事件(把临时变量的url加载到行中)
      confimQualified() {
        //开始把数据存进去
        var _this = this;
        _this.tableData3[_this.tempIndex].TEMPURL = [];
        console.log('_this.tempUrl:' + _this.tempUrl);
        _this.tableData3[_this.tempIndex].TEMPURL.reSet(_this.tempUrl);
        _this.tableData3[_this.tempIndex].NUMB = '1';
        //开始复制
        _this.tableData3.updateIndex(_this.tempIndex, _this.copy(_this.tableData3[_this.tempIndex]));
        _this.dialogQualified = false;
      },
      //简单对象的复制方法，快速迭代
      copy(obj) {
        let _this = this;
        var newObj = {};
        for (let key in obj) {
          let value = _this.isNull(obj[key]) ? '' : obj[key];
          newObj[key] = value;
        }
        return newObj;
      },
      /**打开上传合格证的dialog的时候触发的方法**/
      openQualified() {
        //先封装每一个url，并且提取文件名
        let _this = this;
        //首先清理已经上传文件列表
        _this.images.clear();
        for (let i = 0; i < _this.tableData3[_this.tempIndex].TEMPURL.length; i++) {
          let url = _this.tableData3[_this.tempIndex].TEMPURL[i];
          //提取文件名
          let nameArray = url.split('/');
          //截取完数组最后一个肯定是文件名
          let name = nameArray[nameArray.length - 1];
          //封装url
          url = _this.ftpUrl + url;
          //存进已上传文件列表中
          _this.images.push({
            'name': name,
            'url': url
          });
        }
      },
      /**新增页面最后的提交确认按钮,需要将头部输入框/发票路径/随货清单/新增的品种信息全部一口气传递到后台**/
      addPageConfim() {
        let data = {};
        let _this = this;
        for (let key in _this.formInline) {
          if (key == 'invoiceDate') {
            data[key] = _this.isNull(_this.formInline[key]) ? '' : _this.convertDate(_this.formInline[key]);
          } else {
            data[key] = _this.isNull(_this.formInline[key]) ? '' : _this.formInline[key];
          }
        }
        //发票信息拆分数组拆分成实体url
        let invoiceUrl = '';
        let billUrl = '';
        for (let i = 0; i < _this.invoiceUrl.length; i++) {
          invoiceUrl = invoiceUrl + _this.invoiceUrl[i];
          if (i != _this.invoiceUrl.length - 1) {
            invoiceUrl = invoiceUrl + ';';
          }
        }
        for (let i = 0; i < _this.billUrl.length; i++) {
          billUrl = billUrl + _this.billUrl[i];
          if (i != _this.billUrl.length - 1) {
            billUrl = billUrl + ';';
          }
        }
        //加入data
        data['invoiceUrl'] = invoiceUrl;
        data['billUrl'] = billUrl;
        //将品种信息作为一个字符串参数传递
        //前端就转化成能转化的实体类型
        let items = [];
        for (let i = 0; i < _this.tableData3.length; i++) {
          let item = _this.tableData3[i];
          //首先转化url
          let certificateUrl = '';
          if (_this.isNull(item['TEMPURL'])) {
            item['TEMPURL'] = [];
          }
          for (let j = 0; j < item['TEMPURL'].length; j++) {
            certificateUrl = certificateUrl + item['TEMPURL'][j];
            if (j != item['TEMPURL'].length - 1) {
              certificateUrl = certificateUrl + ';';
            }
          }
          //存入新的实体
          let nitem = {
            certificateUrl: certificateUrl
          };
          //再去判断一下品种是不是都填了
          //每个字段转换存入
          nitem['lotNo'] = item['LOTNO'];
          nitem['itemId'] = item['ITEMID'];
          if (_this.isNull(nitem['lotNo'])) {
            _this.$message.error('品种批号不可以为空,请填写后再次确认');
            return;
          }
          if (_this.isNull(nitem['certificateUrl'])) {
            _this.$message.error('产品合格证未维护,请维护后再次确认');
            return;
          }
          items.push(nitem);
        }
        data['items'] = JSON.stringify(items);
        //开启ajax提交
        _this.ajaxPost({
          url: '/invoiceShadow/addInvoiceInfo',
          data: data,
          success(data) {
            if (data.statuCode == 200) {
              _this.$message.success({
                message: '新增发票影像信息成功!',
                duration: 2000
              });
              _this.$router.push({path: '/invoiceShadow'});
            } else {
              _this.$message.error('新增发票影像信息失败，原因为:' + data.msg);
            }
          },
          error(err) {
            _this.$message.error('新增发票影像信息出现异常，error:' + err.error);
          }
        });
      }, /**根据index删除当前行**/
      delRow(index) {
        this.tableData3.removeIndex(index);
      }
    }
  }
</script>

<style scoped="scoped">
  /*.shouye {
        display: block;
        width: 128px;
        height: 36px;
        font-size: 14px;
        color: #333;
        text-align: center;
        line-height: 36px;
        background: #fff;
        position: absolute;
        left: 0;
        bottom: 0;
    }

    .main-top {
        background: #fff;
        font-size: 14px;
        padding-left: 15px;
        border-bottom: 1px solid #ddd;
    }

    .short-line {
        display: inline-block;
        height: 12px;
        border-right: 2px solid #007FE1;
        margin-right: 8px;
        position: relative;top:2px;
    }

    .el-main {
        height: 874px;
        background-color: #e6ebf2;
        color: #333;
    }*/

  .tableWrap {
    background: #fff;
    padding: 0 15px;
  }

  .el-table th {
    background: #dce3f2;
    color: #333
  }

  .el-table td {
    padding: 6px 0;
  }

  /*.el-pagination{padding: 30px 0 40px 25px;}*/

  .btns {
    background: #fff;
    height: 100px;
    margin-bottom: 20px;
    position: relative;
  }

  .btns .el-button:first-child {
    position: absolute;
    right: 240px;
    top: 20px;
    padding: 13px 64px;
  }

  .btns .el-button:last-child {
    position: absolute;
    right: 60px;
    top: 20px;
    color: #999;
  }

  /*弹框*/
  .el-dialog--center .el-dialog__body {
    padding-top: -10px !important;
  }

  .serachLists {
    padding: 0;
  }

  .serachLists li {
    height: 40px;
    line-height: 40px;
    font-size: 12px;
    color: #333;
    text-indent: 15px;
  }

  .serachLists li:nth-of-type(odd) {
    background: #f4f4f4;
  }

  .serachLists li:hover {
    background: #fff1e4;
  }

  .addImg {
    height: 200px !important;
    margin-top: -40px !important;
  }

  .dialogWrap {
    height: 380px !important;
  }

  .dialogLeft {
    height: 380px;
    border-right: 1px solid #ddd;
    padding: 10px !important;
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
    position: relative;
    top: -3px;
  }

  /*日期图标*/
  .date-item >>> .el-input__inner {
    padding: 0 28px !important;
  }

  .el-table {
    font-size: 12px !important;
    color: #333;
  }

  .el-container >>> .el-dialog__body {
    padding: 0 20px !important
  }
</style><!--
<template>
  <div class="mainWrap">
    &lt;!&ndash;<div class="right-top">
            <span class="shouye">发票影像管理</span>
        </div>&ndash;&gt;
    <el-row >
      <el-col :span="24" class="main-top">
        <p><span class="short-line"></span>
          <router-link to="/invoiceShadow" style="color: #999;text-decoration: none;">发票影像管理</router-link> &gt; 新增</p>
      </el-col>
    </el-row>
    <div class="tableWrap">

      <el-form :inline="true" :model="formInline" class="form-inline">
        <el-form-item label="发票号码">
          <el-input v-model="formInline.invoiceNo" size='small' placeholder="请输入发票号码"></el-input>
        </el-form-item>
        <el-form-item label="发票日期" class="date-item">
          <el-date-picker v-model="formInline.invoiceDate" align="right" type="date" size='small' placeholder="选择日期" :picker-options="pickerOptions1">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发票类型">
          <el-select v-model="formInline.invoiceType" size='small' placeholder="发票类型">
            <el-option label="普通发票" value="0"></el-option>
            <el-option label="增值税发票" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="formInline.remark" size='small' placeholder="请输入备注"></el-input>
        </el-form-item>
      </el-form>
      <div class="">
        <p style="font-size: 12px;margin: 10px 0;color: #666;">上传图片</p>
        <el-upload ref="insertUpload" accept="image/*" :on-change="onChange" :before-remove="beforeRemove" :before-upload="beforeUpload" action="https://jsonplaceholder.typicode.com/posts/" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :http-request="uploadInvoice" list-type="picture-card" :auto-upload="true" :multiple="true">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="imageVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </div>
      <div class="">
        <p style="font-size: 12px;margin: 10px 0;color: #666;">上传随货清单图片</p>
        <el-upload accept="image/*" :on-change="onChange" :before-upload="beforeUpload" action="https://jsonplaceholder.typicode.com/posts/" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :http-request="uploadBill" list-type="picture-card" :auto-upload="true" :multiple="true">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </div>
      <el-row style="position: relative;">
        <el-col :span="24" class="main-top">
          <p><span class="short-line"></span>随货清单明细</p>
        </el-col>
        <el-button type="primary" style="position: absolute;top: 0;right: 20px;padding: 10px 14px;" @click="addItem">+新增品种</el-button>
      </el-row>
      &lt;!&ndash;表格区域&ndash;&gt;
      <el-table :data="tableData3" height="330" stripe border :header-cell-style="tableHeaderColor" style="width: 100%" size="mini" >
        <el-table-column prop="number" label="序号" type="index" width="50" align="center">
        </el-table-column>
        <el-table-column prop="ITEMID" label="品种编码" width="80" align="center">
        </el-table-column>
        <el-table-column prop="ITEMNAME" label="品种名称" width="260" align="center">
        </el-table-column>
        <el-table-column prop="ITEMSPECIFICATION" label="规格" width="160" align="center">
        </el-table-column>
        <el-table-column prop="MANUNAME" label="生产厂家" width="160" align="center">
        </el-table-column>
        <el-table-column prop="LOTNO" label="批号" width="120" align="center">
          <template slot-scope="scope">
            <el-input type="text" v-model="scope.row.LOTNO" size="mini" placeholder="请输入批号"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="NUMB" label="是否维护合格证" width="120" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.NUMB == '1'">是</span>
            <span v-else style="color:red">否</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="clickQualified(scope.$index)">上传产品合格证</el-button>
            <el-button @click="delRow(scope.$index)" type="text" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="btns">
        <el-button type="primary" size="small" @click="addPageConfim">确定</el-button>
        <router-link to="/invoiceShadow" style="color: #999;text-decoration: none;">
          <el-button size="small">返回</el-button>
        </router-link>
      </div>
    </div>
    &lt;!&ndash;上传合格证弹框&ndash;&gt;
    <el-dialog title="上传产品合格证" center @open="openQualified" ref="dialogQualified" @close="closeQualifiedDialog" :visible.sync="dialogQualified" width="50%">
      <el-row class="addImg" style="margin-top: -40px!important;">
        <el-col :span="24" >
          <p style="font-size: 14px;margin-top: 0!important;">上传图片</p>
          <el-upload ref="qualifiedUpload" accept="image/*" :show-file-list="true" :file-list="images" :on-change="onChange" :before-remove="beforeRemove" :before-upload="beforeUpload" action="https://jsonplaceholder.typicode.com/posts/" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :http-request="uploadQualified" list-type="picture-card" :auto-upload="true" :multiple="true">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-col>
      </el-row>
      <span slot="footer">
			    <el-button @click="dialogQualified = false">取 消</el-button>
			    <el-button type="primary" @click="confimQualified">确 定</el-button>
			  </span>
    </el-dialog>
    &lt;!&ndash;新增品种弹框&ndash;&gt;
    <el-dialog title="新增品种" v-loading="vloading" @close="closeItemDialog" :visible.sync="centerDialogVisible" width="70%" top="7vh" :lock-scroll="true" :center="true">
      &lt;!&ndash;中间部分&ndash;&gt;
      <el-row :gutter="10" class="dialogWrap">
        <el-col :span="6" class="dialogLeft">
          <el-input placeholder="请输入医院" size="small" v-model="searchValue" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="queryHospital"></el-button>
          </el-input>
          <ul class="serachLists">
            <li v-for="(item,index) in hospitalList" @click="hospitalClick(item.custCode)" :key="index">{{item.custName}}</li>
          </ul>
        </el-col>
        <el-col :span="18">
          <el-form ref="addItemForm" :inline="true"  class="demo-form-inline">
            <el-form-item label="品种编码">
              <el-input size="small" :disabled="isDisable" v-model="itemCode" placeholder="请输入品种编码"></el-input>
            </el-form-item>
            <el-form-item label="品种名称">
              <el-input size="small" :disabled="isDisable" v-model="itemName" placeholder="请输入品种名称"></el-input>
            </el-form-item>
            <el-form-item label="生产厂家">
              <el-input size="small" :disabled="isDisable" v-model="manuFacturer" placeholder="请输入生产厂家"></el-input>
            </el-form-item>
            <el-form-item label="规格">
              <el-input size="small" :disabled="isDisable" v-model="specification" placeholder="请输入规格"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button size="small" :disabled="isDisable" type="primary" @click="onQueryItem">查询</el-button>
            </el-form-item>
          </el-form>
          &lt;!&ndash;表格&ndash;&gt;
          <el-table ref="multipleTable" v-loading="tloading" :data="tableData" tooltip-effect="dark" style="width: 100%" height="250" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="ITEMID" label="品种编码">
            </el-table-column>
            <el-table-column prop="ITEMNAME" label="品种名称">
            </el-table-column>
            <el-table-column prop="ITEMSPECIFICATION" label="规格" width="100">
            </el-table-column>
            <el-table-column prop="MANUNAME" label="生产厂家" show-overflow-tooltip>
            </el-table-column>
          </el-table>
          <el-pagination size="mini" @size-change="itemSizeChange" @current-change="itemCurrentChange" :current-page="itemPage" :page-sizes="[10,20,30,50,100]" :page-size="itemPageSize" layout="total, sizes, prev, pager, next, jumper" :total="itemTotal">
          </el-pagination>
        </el-col>
      </el-row>
      &lt;!&ndash;弹框底部&ndash;&gt;
      <span slot="footer">
			    <el-button @click="centerDialogVisible = false" size="small">取 消</el-button>
			    <el-button type="primary" :disabled="isDisable" @click="confim" size="small">确 定</el-button>
			  </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: 'vatInvoiceFileDtl'
    }
</script>

<style scoped>

</style>
-->
