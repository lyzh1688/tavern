<template>
  <div class="page-container">
    <!--工具栏-->
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
            <el-tooltip content="导出" placement="top">
              <el-button icon="fa fa-file-excel-o"></el-button>
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
    <kt-table :height="600"
              :data="pageResult" :columns="filterColumns"
              @findPage="findPage" :showOperation="showOperation">
    </kt-table>
  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import {format, formatDate} from "@/utils/datetime"

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
        columns: [],
        filterColumns: [],
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},
        showOperation: false,
        batchId: ''
      }
    },
    created() {
      let _this = this;
      _this.batchId = this.$route.params.batchId;
      let _id = localStorage.getItem("salaryDtlBatchId");
      if (_this.batchId == undefined || _this.batchId == null || _this.batchId == '') {
        if (_id == undefined || _id == null) {
          this.$router.push("/salary/query")
          return
        } else {
          _this.batchId = _id;
        }
      }
      this.findPage(null);
      localStorage.setItem("salaryDtlBatchId", _this.batchId);
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
          this.pageRequest.current = data.pageRequest.pageNum;
          this.pageRequest.size = data.pageRequest.pageSize;
        }
        this.pageRequest.batchId = this.batchId;
        this.$api.salary.findDtlPage(this.pageRequest).then((res) => {
          this.pageResult.pageNum = res.data.current
          this.pageResult.pageSize = res.data.size
          this.pageResult.totalSize = res.data.total
          this.pageResult.content = res.data.records
        }).then(data != null ? data.callback : '')
      },
      // 时间格式化
      dateFormat: function (row, column, cellValue, index) {
        return format(row[column.property])
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
          {prop: "staffId", label: "工号"},
          {prop: "name", label: "姓名"},
          {prop: "staffType", label: "员工性质"},
          {prop: "needEndowmentIns", label: "养老"},
          {prop: "needUnemployIns", label: "失业"},
          {prop: "needMedicalIns", label: "医疗"},
          {prop: "needInjuryIns", label: "工伤"},
          {prop: "needBirthIns", label: "生育"},
          {prop: "needHabitationIns", label: "住房"},
          {prop: "householdType", label: "户籍类型"},
          {prop: "baseSalary", label: "基本工资"},
          {prop: "positionValue", label: "岗位工资"},
          {prop: "travelAllowance", label: "交通补贴"},
          {prop: "businessAllowance", label: "公务补贴"},
          {prop: "telephoneAllowance", label: "电话补贴"},
          {prop: "taxiAllowance", label: "车补"},
          {prop: "lunchAllowance", label: "餐补"},
          {prop: "ageAllowance", label: "司龄补"},
          {prop: "overtimeDays", label: "加班天数"},
          {prop: "overtimePay", label: "加班工资"},
          {prop: "bonus", label: "奖金"},
          {prop: "absenceDays", label: "缺勤天数"},
          {prop: "attendanceDeduct", label: "考勤应扣"},
          {prop: "totalWages", label: "工资总额"},
          {prop: "allowanceBase", label: "缴金基数"},
          {prop: "endowmentInsPers", label: "养老保险"},
          {prop: "unemployInsPers", label: "失业保险"},
          {prop: "medicalInsPers", label: "医疗保险"},
          {prop: "habitationInsPers", label: "养老(公司)"},
          {prop: "unemployInsCom", label: "失业(公司)"},
          {prop: "medicalInsCom", label: "医疗(公司)"},
          {prop: "injuryInsCom", label: "工伤(公司)"},
          {prop: "birthInsCom", label: "生育(公司)"},
          {prop: "habitationInsCom", label: "住房公积金(公司)"},
          {prop: "individualIncomeTax", label: "代扣个人所得税"},
          {prop: "netWages", label: "实发工资"},
          {prop: "bankCard", label: "银行工资卡"},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      // 时间格式化
      dateFormat: function (date) {
        return format(date)
      },
    },
    mounted() {
      this.initColumns()
    }
  }
</script>

<style scoped>

</style>
