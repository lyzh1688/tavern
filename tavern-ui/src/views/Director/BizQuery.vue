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
          <el-input v-model="filters.name" placeholder="请输入旺旺账号"></el-input>
        </el-form-item>
        <el-form-item label="区" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        <el-form-item label="公司名称" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="客户名称" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入旺旺账号"></el-input>
        </el-form-item>
        <el-form-item label="当前流程" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入当前流程名"></el-input>
        </el-form-item>
        <el-form-item label="客户级别" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择客户等级">
            <el-option label="VVIP" value='0'></el-option>
            <el-option label="VIP" value='1'></el-option>
            <el-option label="高级客户" value='2'></el-option>
            <el-option label="普通客户" value='3'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="微信昵称" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入微信昵称"></el-input>
        </el-form-item>
        <el-form-item label="业务创建时间" label-width="100px">
          <el-date-picker v-model="filters.startTradeDate" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="业务类型" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入业务类型"></el-input>
        </el-form-item>
        <el-form-item label="紧急程度" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择紧急程度">
            <el-option label="重要" value='0'></el-option>
            <el-option label="紧急" value='1'></el-option>
            <el-option label="一般" value='2'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:role:view" type="primary"
                     @click="findPage(null)"/>
          <kt-button icon="fa fa-edit" label="批量转授权" perms="sys:role:view" type="primary"
                     @click="handleReChoose(null)"/>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="pageResult.content" stripe stripe height="600" size="mini" style="width: 100%;"
              v-loading="loading">
      <el-table-column prop="id" header-align="center" align="center" label="客户ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="客户姓名" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="aliAccnt" label="旺旺账号" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="comName" label="公司名称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="city" label="市" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="district" label="区" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="content" label="服务内容/备注" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="weChatName" label="微信昵称" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="bizType" label="业务类型" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="cost" label="耗时(天)" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="emeLevel" label="紧急程度" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="maxDays" label="最大天数" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="curProcess" label="当前流程" header-align="center" align="center">
      </el-table-column>
      <el-table-column prop="customerLevel" label="客户级别" header-align="center" align="center">
      </el-table-column>
      <el-table-column fixed="right" label="操作" header-align="center" align="center" width="500">
        <template slot-scope="scope">
          <kt-button icon="fa fa-gears" label="流程日志" perms="sys:user:add" type="primary"
                     @click="showWorkFlow"/>
          <kt-button icon="fa fa-plus" label="公司详情" perms="sys:user:add" type="primary"
                     @click="handleComDtl(scope.row)"/>
          <kt-button icon="fa fa-battery-2" label="延期" perms="sys:user:add" type="primary"
                     @click="handleDelay(scope.row)"/>
          <kt-button icon="fa fa-money" label="退款" perms="sys:user:add" type="primary"
                     @click="handleDrawBack(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper"
                     @current-change="handleCurrentChange"
                     :current-page="pageRequest.pageNum"
                     :page-size="pageRequest.pageSize"
                     :total="totalSize" style="float:right;">
      </el-pagination>
    </div>
    <!--新增编辑界面-->
    <el-dialog title="添加备注" width="60%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="center" align="center">
        <el-form-item label="当前流程: " prop="curProcess" label-width="100px">
          <span style="text-align: left;float: left;color: #a71d5d">{{dataForm.curProcess}}</span>
        </el-form-item>
      </el-form>
      <el-form :size="size" label-position="center" align="left">
        <el-form-item label="历史备注: " prop="id" label-width="100px">
          <el-table :data="bakHistory.content" stripe size="mini" style="width: 100%;" v-loading="loading"
                    element-loading-text="$t('action.loading')">
            <el-table-column
              prop="content" header-align="center" align="center" label="备注内容">
            </el-table-column>
            <el-table-column
              prop="time" header-align="center" align="center" label="备注时间">
            </el-table-column>
            <el-table-column
              prop="writer" header-align="center" align="center" label="填写人">
            </el-table-column>
            <el-table-column
              prop="link" header-align="center" align="center" label="附件链接">
              <template slot-scope="scope1">
                <a :href="scope1.row.link" target="_blank">{{scope1.row.link}}</a>
              </template>
            </el-table-column>
          </el-table>
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
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="dialogVisible = false" :loading="editLoading">
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
    <el-dialog width="40%" :visible.sync="comDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="订单号" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入订单号"></el-input>
        </el-form-item>
        <el-form-item label="实付金额" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入实付金额"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="公司名称" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入公司名称"></el-input>
        </el-form-item>
        <el-form-item label="客户名称" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入客户名称"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="公司区域" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入公司区域"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入联系方式"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="旺旺号" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入旺旺号"></el-input>
        </el-form-item>
        <el-form-item label="社保/公积金人数" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入社保/公积金人数"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="服务开始时间" label-width="100px">
          <el-date-picker v-model="filters.accountPeriod" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="服务结束时间" label-width="100px">
          <el-date-picker v-model="filters.accountPeriod" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="comDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" @click.native="comDialogVisible = false" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="延期批示" width="60%" :visible.sync="delayDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="订单号" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入订单号"></el-input>
        </el-form-item>
        <el-form-item label="延期业务" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入延期业务"></el-input>
        </el-form-item>
      </el-form>
      <div id="mountNode"></div>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="处理意见: " label-width="100px">
          <el-input type="textarea" v-model="filters.desc" style="width: 500px"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="延期天数" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入延期天数"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="delayDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="delayDialogVisible = false" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="退款处理" width="40%" :visible.sync="drawBackDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="订单号" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入订单号"></el-input>
        </el-form-item>
        <el-form-item label="退款业务" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入退款业务"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="应付金额" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入应付金额"></el-input>
        </el-form-item>
        <el-form-item label="实付金额" label-width="100px">
          <el-input v-model="filters.name" placeholder="请输入实付金额"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="审批意见: " label-width="100px">
          <el-input type="textarea" v-model="filters.desc" style="width: 500px"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="filters" align="left">
        <el-form-item label="是否同意退款" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="是" value='0'></el-option>
            <el-option label="否" value='1'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="drawBackDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="drawBackDialogVisible = false" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="转授权" width="60%" :visible.sync="reChooseDialogVisible" :close-on-click-modal="false">
      <el-form :inline="true" :model="filters" :size="size" align="left">
        <el-form-item label="当前处理人" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="张三丰" value='0'></el-option>
            <el-option label="李连杰" value='1'></el-option>
            <el-option label="萧敬腾" value='2'></el-option>
            <el-option label="薛之谦" value='3'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:role:view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
      </el-form>
      <el-form :size="size" label-position="center" align="left">
        <el-form-item prop="id">
          <el-table :data="bakHistory.content" stripe size="mini" style="width: 100%;" v-loading="loading"
                    element-loading-text="$t('action.loading')">
            <el-table-column
              prop="content" header-align="center" align="center" label="事件ID">
            </el-table-column>
            <el-table-column
              prop="time" header-align="center" align="center" label="客户名称">
            </el-table-column>
            <el-table-column
              prop="writer" header-align="center" align="center" label="公司名称">
            </el-table-column>
            <el-table-column
              prop="link" header-align="center" align="center" label="在办业务">
            </el-table-column>
            <el-table-column
              prop="link" header-align="center" align="center" label="当前处理人">
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
      <el-form :size="size" label-position="center" align="left">
        <el-form-item label="变更处理人" label-width="100px">
          <el-select v-model="filters.name" clearable auto-complete="off" placeholder="请选择">
            <el-option label="张三丰" value='0'></el-option>
            <el-option label="李连杰" value='1'></el-option>
            <el-option label="萧敬腾" value='2'></el-option>
            <el-option label="薛之谦" value='3'></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="reChooseDialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="reChooseDialogVisible = false" :loading="editLoading">
          {{$t('action.submit')}}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import G6 from '@antv/g6';
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
          name: ''
        },
        dialogImageUrl: '',
        pictureDialogVisible: false,
        nextDialogVisible: false,
        comDialogVisible: false,
        delayDialogVisible: false,
        reChooseDialogVisible: false,
        drawBackDialogVisible: false,
        loading: false,
        columns: [],
        filterColumns: [],
        pageRequest: {pageNum: 1, pageSize: 10},
        pageResult: {},
        bakHistory: {},

        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          name: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          id: 0,
          name: '',
          password: '123456',
          deptId: 1,
          deptName: '',
          email: 'test@qq.com',
          mobile: '13889700023',
          status: 1,
          userRoles: []
        },
        nextForm: {},
        comForm: {},
        deptData: [],
        deptTreeProps: {
          label: 'name',
          children: 'children'
        },
        roles: []
      }
    },
    created() {
      this.findPage(null);
    },
    mounted() {
    },
    methods: {
      // 获取分页数据
      findPage: function (data) {
        if (data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.pageRequest.columnFilters = {name: {name: 'name', value: this.filters.name}}
        this.$api.outer.findPage(this.pageRequest).then((res) => {
          this.pageResult = res.data
          this.pageRequest.pageNum = res.data.pageNum
          this.pageRequest.pageSize = res.data.pageSize
          this.totalSize = res.data.totalSize
        }).then(data != null ? data.callback : '')
      },
      // 加载用户角色信息
      findUserRoles: function () {
        this.$api.role.findAll().then((res) => {
          // 加载角色集合
          this.roles = res.data
        })
      },
      // 批量删除
      handleDelete: function (data) {
        this.$api.outer.batchDelete(data.params).then(data != null ? data.callback : '')
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
      handleDelay: function (params) {
        // this.initG6()
        this.delayDialogVisible = true
      },
      handleReChoose: function (params) {
        this.reChooseDialogVisible = true
      },
      handleDrawBack: function (params) {
        this.drawBackDialogVisible = true
      },

      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.pictureDialogVisible = true;
      },
      // 显示新增界面
      /* handleAdd: function () {
         this.dialogVisible = true
         this.operation = true
         this.dataForm = {
           id: 0,
           name: '',
           password: '',
           deptId: 1,
           deptName: '',
           email: 'test@qq.com',
           mobile: '13889700023',
           status: 1,
           userRoles: []
         }
       },
       // 显示编辑界面
       handleEdit: function (params) {
         this.dialogVisible = true
         this.operation = false
         this.dataForm = Object.assign({}, params.row)
         let userRoles = []
         for (let i = 0, len = params.row.userRoles.length; i < len; i++) {
           userRoles.push(params.row.userRoles[i].roleId)
         }
         this.dataForm.userRoles = userRoles
       },*/
      handleBak: function (params) {
        this.dialogVisible = true
        this.operation = false
        this.dataForm = Object.assign({}, params)
        this.$api.outer.findBak(params).then((res) => {
          this.bakHistory = res.data
        })
      },
      handleComDtl: function (params) {
        this.comDialogVisible = true
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              let userRoles = []
              for (let i = 0, len = params.userRoles.length; i < len; i++) {
                let userRole = {
                  userId: params.id,
                  roleId: params.userRoles[i]
                }
                userRoles.push(userRole)
              }
              params.userRoles = userRoles
              this.$api.customer.save(params).then((res) => {
                this.editLoading = false
                if (res.code == 200) {
                  this.$message({message: '操作成功', type: 'success'})
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.findPage(null)
              })
            })
          }
        })
      },
      // 获取部门列表
      findDeptTree: function () {
        this.$api.dept.findDeptTree().then((res) => {
          this.deptData = res.data
        })
      },
      // 菜单树选中
      deptTreeCurrentChangeHandle(data, node) {
        this.dataForm.deptId = data.id
        this.dataForm.deptName = data.name
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
          {prop: "id", label: "客户ID", minWidth: 50},
          {prop: "name", label: "客户姓名", minWidth: 120},
          {prop: "aliAccnt", label: "旺旺账号", minWidth: 120},
          {prop: "wechatAccnt", label: "微信账号", minWidth: 100},
          {prop: "wechatName", label: "微信昵称", minWidth: 120},
          {prop: "mobile", label: "联系电话", minWidth: 100},
          {prop: "createTime", label: "创建时间", minWidth: 70},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      },
      handleCurrentChange(val) {
        let _this = this;
        _this.pageRequest.pageNum = val;
        _this.findPage(_this.pageRequest);
      },
      initG6() {
        const data = {
          nodes: [{
            id: 'node1',
            label: '流程开始',
            x: 100,
            y: 100,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node2',
            label: '张三录单',
            x: 100,
            y: 200,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node3',
            label: '李四同意',
            x: 100,
            y: 300,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node4',
            label: '王五代缴社保',
            x: 100,
            y: 400,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node5',
            label: '流程结束',
            x: 100,
            y: 500,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          },],
          edges: [{
            target: 'node2',
            source: 'node1'
          },
            {
              target: 'node3',
              source: 'node2'
            }, {
              target: 'node4',
              source: 'node3'
            }, {
              target: 'node5',
              source: 'node4'
            }]
        };
        const graph = new G6.Graph({
          container: 'mountNode',
          width: 500,
          height: 800,
          nodeStyle: {
            default: {
              stroke: '#096dd9',
            }
          },
          edgeStyle: {
            default: {stroke: '#A3B1BF'}
          }
        });
        graph.read(data);
      },
    },
  }
</script>

<style scoped>

</style>
