<template>
  <div class="mainWrap">
    <div class="tableWrap">
      <el-form :inline="true" :model="formInline" class="form-inline" style="margin-left: 50px">
        <el-form-item label="发票号码" label-width="100px">
          <el-input v-model="formInline.invoiceId" size='small' placeholder="请输入发票号码" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="发票日期" class="date-item" style="margin-left: 100px" label-width="100px">
          <el-input v-model="formInline.invoiceDate" size='small' placeholder="请输入发票日期" style="width: 200px">
          </el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="form-inline" style="margin-left: 50px">
        <el-form-item label="发票代码" label-width="100px">
          <el-input v-model="formInline.invoiceCode" size='small' placeholder="请输入发票代码" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="校验码" style="margin-left: 100px;" label-width="100px">
          <el-input v-model="formInline.checkCode" size='small' placeholder="请输入校验码" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="form-inline" style="margin-right: 380px">
        <el-form-item label="不含税金额" label-width="100px">
          <el-input v-model="formInline.taxFreeAmount" size='small' placeholder="请输入不含税金额"
                    style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="formInline" class="form-inline" style="margin-left: 150px">
        <el-form-item>
          <el-image v-model="formInline.imageUrl" style="width: 700px;height: 400px"
                    :src="formInline.imageUrl"></el-image>
        </el-form-item>
        <el-form-item>
          <i :class="[rotate?'fa fa fa-refresh go':'fa fa fa-refresh aa']" aria-hidden="true"
             style="margin-left: 100px;font-size: 50px; font-weight: bold;color: #66B3FF;margin-top: 100px"
             @click="refreshUrl"></i>
        </el-form-item>
      </el-form>
      <el-form :model="formInline" class="form-inline" style="margin-left: 100px;">
        <el-form-item size="large" style="margin-left: 40px">
          <el-button type="primary" @click="confirm" :loading="loading">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
  export default {
    name: 'zzdAmend',
    data() {
      return {

        //弹框表格
        dialogImageUrl: '',
        imageVisible: false,
        formInline: {
          invoiceNo: '',
          invoiceDate: '',
          invoiceCode: '',
          checkCode: '',
          taxFreeAmount: '',
          imageUrl: ''
        },
        rotate: false,
        fileId: '',
        loading: false
      }
    },
    created() {
      let _this = this;
      _this.params = this.$route.params;
      _this.fileId = this.$route.params.fileId;
      let _id = localStorage.getItem("zzsFileId");
      if (_this.fileId == undefined || _this.fileId == null || _this.fileId == '') {
        if (_id == undefined || _id == null) {
          this.$router.push("/zzs/upload")
          return
        } else {
          _this.fileId = _id;
        }
      }
      _this.findFileScanDtl();
      localStorage.setItem("zzsFileId", _this.fileId);
    },
    methods: {
      /*设置表头背景色*/
      tableHeaderColor({row, column, rowIndex, columnIndex}) {
        if (rowIndex === 0) {
          return 'background-color: #dce3f2;color:#333;'
        }
      },
      confirm() {
      },
      refreshUrl() {
        let _this = this;
        _this.rotate = !_this.rotate;
        _this.findFileScanDtl();
      },
      findFileScanDtl() {
        let _this = this;
        this.$api.zzs.findFileScanDtl(_this.fileId).then((res) => {
          if (res.retCode == 0) {
            console.log(JSON.stringify(res))
            _this.formInline = res.data;
          } else {
            _this.$message({message: '操作失败, ' + res.msg, type: 'error'})
          }
        });
      },
      confirm: function () {
        let _this = this;
        _this.$confirm('确认修改记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          _this.loading = true
          _this.$api.zzs.editScanDtl(_this.formInline).then((res) => {
            if (res.retCode == 0) {
              _this.$message({message: '修改成功', type: 'success'})
              _this.findFileScanDtl()
            } else {
              _this.$message({message: '操作失败, ' + res.msg, type: 'error'})
            }
            _this.loading = false
          })
        }).catch(() => {
        })
      },
    }
  }
</script>

<style scoped="scoped">

  .mainWrap {
    background: #fff;
    overflow: hidden;
    height: 100%;
    overflow-y: auto;
  }

  /*查询区域*/
  .form-inline {
    padding-top: 15px;
    padding-left: 0;
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
    position: relative;
    top: 2px;
  }

  .form-inline >>> .el-form-item__label {
    font-size: 12px !important;
  }

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

  .el-form-item >>> .el-input__inner {
    height: 30px !important;
    padding: 0 5px !important;
    font-size: 12px !important;
  }

  .aa {
    transition: all 2s;
  }

  .go {
    transform: rotate(-180deg);
    transition: all 2s;
  }


</style>
