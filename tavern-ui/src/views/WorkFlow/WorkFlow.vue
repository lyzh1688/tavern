<template>
  <div class="page-container">
    <div id="mountNode"></div>

    <el-dialog title="历史备注" width="60%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" :size="size" label-position="center" align="left">
        <el-form-item label="历史备注:" prop="bakHistory" label-width="100px">
          <el-table :data="dataForm.logHistory" stripe size="mini" style="width: 100%;" v-loading="logHisLoading"
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
                <a :href="scope1.row.attachmentsPath" target="_blank">{{scope1.row.attachmentsPath}}}</a>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" align="center">
        <el-button :size="size" type="primary" @click.native="dialogVisible = false" :loading="editLoading">
         确认
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
  import {format} from "@/utils/datetime"
  import G6 from '@antv/g6';
  import dagre from 'dagre';


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
        dtlForm: {},
        size: 'small',
        loading: false,
        logHisTotal: 0,
        logHisPageRequest: {
          current: 1,
          size: 1000,
        },
        dialogVisible: false,
        logHisLoading: false, // 新增编辑界面是否显示
        editLoading: false,
        dataForm: {
          logHistory: []
        },
        data: {}
      }
    },
    created() { //不能在created方法中初始化
      //this.initG6()

      this.dtlForm = this.$route.params;
      let tmpInfo = JSON.parse(localStorage.getItem("workFlowGraphLog"));
      if (this.dtlForm.eventId == undefined || this.dtlForm.eventId == null) {
        if (tmpInfo.eventId == undefined || tmpInfo.eventId == null) {
          this.$router.push({name: "主页"})
          return
        } else {
          this.dtlForm = tmpInfo;
        }
      }
      localStorage.setItem("workFlowGraphLog", JSON.stringify(this.dtlForm));

    },
    mounted() {
      this.initG6()

    },
    methods: {
      initG6() {
        let request = {}
        request.eventId = this.dtlForm.eventId
        this.$api.workflow.findGraphLog(request).then((res) => {
          this.data = res.data;
          this.presentG6()
        }).catch((res) => {
          this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
        })
      },
      presentG6() {
        let _this = this
        let data = this.data
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
          width: 1000,
          height: 600,
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
          _this.findLogPage(ev.item.get('model'))
        });
      },
      findLogPage: function (params) {

        this.dialogVisible = true
        this.logHisLoading = true
        let callback = res => {
          this.logHisLoading = false
        }
        this.logHisPageRequest.eventId = this.dtlForm.eventId
        this.logHisPageRequest.operator = params.operatorId
        this.logHisPageRequest.curNodeId = params.id
        this.$api.workflow.findLog(this.logHisPageRequest).then((res) => {
          this.dataForm.logHistory = res.data.records;
          this.logHisTotal = res.data.total;
          this.logHisPageRequest.current = res.data.current;
          this.logHisPageRequest.size = res.data.size;
          callback(res)
        }).catch((res) => {
            this.$message({message: '操作失败, ' + res.response.data.retMessage, type: 'error'})
            callback(res)
          })
      },
    }
  }
</script>
