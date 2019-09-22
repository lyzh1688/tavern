<template>
  <div class="mainWrap">
    <div class="page-container">
      <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
        <el-form :inline="true" :model="formInline" class="form-inline" v-if="sys_zzs_dtl_view">
          <el-form-item>
            <el-form-item>
              <el-select v-model="formInline.verify" clearable placeholder="验证通过状态">
                <el-option label="未通过" value='0'></el-option>
                <el-option label="通过" value='1'></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-select v-model="formInline.emend" clearable placeholder="修正状态">
                <el-option label="未修正" value='0'></el-option>
                <el-option label="已修正" value='1'></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <kt-button icon="fa fa-search" type="primary" :label="$t('action.search')" @click="queryFileGeneral(null)"
                         v-if="sys_zzs_dtl_view"/>
            </el-form-item>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-table :data="tableData" stripe stripe height="600" size="mini" style="width: 100%;" v-loading="loading">
      <el-table-column
        prop="fileId"
        label="文件ID" align="center" v-if="showFileId">
      </el-table-column>
      <el-table-column
        prop="fileName"
        label="文件名"
        type="index"
        width="200px" align="center"
        style="color: #66B3FF;text-decoration: underline;">
        <template slot-scope="scope1">
          <router-link :to="{name:'增值税修正详情',params:{fileId:scope1.row.fileId}}"
                       style="color: #66B3FF;text-decoration: underline;">
            {{scope1.row.fileName}}
          </router-link>
        </template>
      </el-table-column>

      <el-table-column
        prop="isValid"
        label="验证结果" align="center">
        <template slot-scope="scope3">
          <span v-if="scope3.row.isValid == '0'">失败</span>
          <span v-if="scope3.row.isValid == '1'">成功</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="isEmend"
        label="是否已订正" align="center">
        <template slot-scope="scope4">
          <span v-if="scope4.row.isEmend == '0'">否</span>
          <span v-if="scope4.row.isEmend == null">否</span>
          <span v-if="scope4.row.isEmend == '1'">是</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="invoiceId"
        label="发票代码" align="center">
      </el-table-column>
      <el-table-column
        prop="invoiceType"
        label="发票类型" align="center">
        <template slot-scope="scope5">
          <span v-if="scope5.row.invoiceType == '01'">专票</span>
          <span v-if="scope5.row.invoiceType == '02'">货运票</span>
          <span v-if="scope5.row.invoiceType == '03'">机动车票</span>
          <span v-if="scope5.row.invoiceType == '15'">二手车票</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="invoiceDate"
        label="开票日期" align="center">
      </el-table-column>
      <el-table-column
        prop="checkCode"
        label="发票校验码" align="center">
      </el-table-column>
      <el-table-column
        prop="taxFreeAmount"
        label="不含税金额" align="center">
      </el-table-column>
      <el-table-column
        prop="sellerName"
        label="销售方" align="center">
      </el-table-column>
      <el-table-column
        prop="sellerTaxCode"
        label="销售方纳税人识别号" align="center">
      </el-table-column>
      <el-table-column
        prop="sellerBank"
        label="销售方开户行" align="center">
      </el-table-column>
      <el-table-column
        prop="buyerName"
        label="购方名称" align="center">
      </el-table-column>
      <el-table-column
        prop="buyerId"
        label="购方纳税人识别号" align="center">
      </el-table-column>
      <el-table-column
        prop="buyerContact"
        label="购方联系方式" align="center">
      </el-table-column>
      <el-table-column
        prop="buyerBank"
        label="购方开户行" align="center">
      </el-table-column>
      <el-table-column
        prop="invoiceMachineNum"
        label="发票机器码" align="center">
      </el-table-column>
      <el-table-column
        prop="isInvalid"
        label="是否作废" align="center">
      </el-table-column>
      <el-table-column
        prop="taxAmount"
        label="税额合计" align="center">
      </el-table-column>
      <el-table-column
        prop="totalAmount"
        label="价税合计" align="center">
      </el-table-column>
      <el-table-column
        prop="quantity"
        label="数量合计" align="center">
      </el-table-column>
      <el-table-column
        prop="remark"
        label="备注" align="center">
      </el-table-column>
      <el-table-column
        label="商品详情"
        width="100px" align="center">
        <template slot-scope="scope6">
          <el-button @click="handleShow(scope6.row.goodsData)" type="primary" size="mini" round>详情</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="verifyTimes"
        label="税局查验次数" align="center">
      </el-table-column>
      <el-table-column
        prop="updateDate"
        label="操作时间" align="center">
      </el-table-column>
      <el-table-column
        prop="failedReason"
        label="失败原因" align="center">
      </el-table-column>

    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange"
                     :current-page="pageRequest.current" :page-size="pageRequest.size" :total="total"
                     style="float:right;">
      </el-pagination>
    </div>

    <el-dialog width="60%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-table :data="tableGoodsData" stripe size="mini" style="width: 100%;" v-loading="loading"
                :element-loading-text="$t('action.loading')">
        <el-table-column
          prop="name" header-align="center" align="center" label="名称">
        </el-table-column>
        <table-tree-column
          prop="spec" header-align="center" treeKey="id" label="规格">
        </table-tree-column>
        <el-table-column
          prop="unit" header-align="center" align="center" label="单位">
        </el-table-column>
        <el-table-column
          prop="amount" header-align="center" align="center" label="数量">
        </el-table-column>
        <el-table-column
          prop="priceUnit" header-align="center" align="center" label="单价">
        </el-table-column>
        <el-table-column
          prop="priceSum" header-align="center" align="center" label="金额">
        </el-table-column>
        <el-table-column
          prop="taxRate" header-align="center" align="center" label="税率">
        </el-table-column>
        <el-table-column
          prop="taxSum" header-align="center" align="center" label="税额">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button :size="size" @click="submitForm()">确认</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import TableTreeColumn from '@/views/Core/TableTreeColumn'
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import {genNonDuplicateID} from '@/utils/common'
  import Amend from "@/views/Zzs/Amend"
  import {hasPermission} from '@/permission/index.js'

  export default {
    components: {
      PopupTreeInput,
      TableTreeColumn,
      KtTable,
      KtButton,
      TableColumnFilterDialog,
      Amend
    },
    name: 'zzsDtl',
    data() {
      return {
        size: 'small',
        /*查询区域*/
        formInline: {
          verify: '',
          emend: ''
        },
        pageRequest: {
          current: 1,
          size: 20,
        },
        formInline2: {},
        formInline3: {},
        isEnable: true,
        tableData: [],
        tableGoodsData: [],
        hideRow: false,
        batchId: '',
        loading: false,
        showFileId: false,
        showGoodData: false,
        dialogVisible: false,
        sys_zzs_dtl_view: false
      }
    },
    beforeCreate() {
    },
    created() {
      let _this = this;
      _this.sys_zzs_dtl_view = hasPermission('sys:zzs:dtl:view')
      _this.batchId = this.$route.params.batchId;
      let _id = localStorage.getItem("zzsDtlBatchId");
      if (_this.batchId == undefined || _this.batchId == null || _this.batchId == '') {
        if (_id == undefined || _id == null) {
          _this.$router.push("/zzs/upload")
          return
        } else {
          _this.batchId = _id;
        }
      }
      _this.queryFileGeneral(null);
      localStorage.setItem("zzsDtlBatchId", _this.batchId);
    },
    methods: {
      //查询
      queryFileGeneral(data) {
        let _this = this;
        if (data !== null) {
          _this.pageRequest = data
        }
        _this.loading = true
        let callback = res => {
          _this.loading = false
        }
        _this.pageRequest.batchId = _this.batchId;
        _this.pageRequest.verify = _this.formInline.verify;
        _this.pageRequest.emend = _this.formInline.emend;
        this.$api.zzs.findFileGeneralPage(this.pageRequest).then((res) => {
          _this.tableData = res.data.records;
          _this.total = res.data.total;
          _this.pageRequest.current = res.data.current;
          _this.pageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
          callback(res)
        });
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.current = val;
        _this.queryFileGeneral(_this.pageRequest);
      },
      handleShow: function (data) {
        let _this = this;
        _this.tableGoodsData = data
        _this.dialogVisible = true
      },
      submitForm: function () {
        let _this = this;
        _this.dialogVisible = false;
      }
    }
  }
</script>

<style scoped>

</style>
