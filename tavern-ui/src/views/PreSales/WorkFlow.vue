<template>
  <div class="page-container">
    <div id="mountNode"></div>

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
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="dialogVisible = false" :loading="editLoading">
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
    name: "WorkFlow",
    data() {
      return {
        size: 'small',
        filters: {
          name: ''
        },
        dialogImageUrl: '',
        pictureDialogVisible: false,
        nextDialogVisible: false,
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
        deptData: [],
        deptTreeProps: {
          label: 'name',
          children: 'children'
        },
        roles: []
      }
    },
    created() { //不能在created方法中初始化
      //this.initG6()
    },
    mounted() {
      this.initG6()
    },
    methods: {
      initG6() {
        let _this = this;
        const data = {
          nodes: [{
            id: 'node1',
            label: '流程开始',
            x: 200,
            y: 300,
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
            x: 200,
            y: 200,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node4',
            label: '王五代缴社保',
            x: 300,
            y: 200,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          }, {
            id: 'node5',
            label: '流程结束',
            x: 300,
            y: 300,
            labelCfg: {
              position: 'right',
              offset: 5
            }, style: {
              lineWidth: 5
            }
          },],
          edges: [{
            source: 'node1',
            target: 'node2'
          },
            {
              source: 'node1',
              target: 'node3'
            }, {
              source: 'node1',
              target: 'node4'
            }, {
              source: 'node4',
              target: 'node5'
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
        graph.on('click', function (ev) {
          _this.handleBak(null)
        });
      },handleBak() {
        this.dialogVisible = true
      }
    }
  }
</script>
