<template>
  <div>
  <el-autocomplete v-model="bindData" :size="size" :fetch-suggestions="filterData" @focus="inpFocus" @blur="inpBlur" :trigger-on-focus="false" @select="inpSelect" highlight-first-item>
    <i slot="suffix" class="el-input__icon el-icon-search" @click="toRefer"></i></el-autocomplete>
  <div>
    <el-dialog :width="autoWidth" :title="title" :visible.sync="visible" @open="openRefer" :append-to-body="appendToBody" :center="center">
      <template>
          <span>
            <el-input class="ref_search" v-model="search"  placeholder="请输入关键字搜索" width></el-input>
          </span>
        <el-table :data="search ? refData.filter(this.createFilter(search)) : refData" @current-change="changeCurrentRow" @selection-change="selectionChange"
                  highlight-current-row  border @row-dblclick="refColumDoubleClick" :height="autoHeight">
          <el-table-column v-if="selection" type="selection" align="center"></el-table-column>
          <el-table-column type="index" align="center"></el-table-column>
          <el-table-column ref="tcol" v-for="(col,k) in refColumns" :prop="col.prop" :label="col.label" :render-header="renderHeader"
                            v-if="col.isShow" :align="col.algin" :key="k" :show-overflow-tooltip="true"></el-table-column>
        </el-table>
      </template>
      <template>
        <div style="height: 20px;" v-if="showButton">
          <el-button class="ref_btn" type="primary" size="small" @click="onbosure">确认</el-button>
          <el-button class="ref_btn" size="small" @click="onbocancel">取消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
  </div>
</template>

<script>
    export default {
        name: 'vReference',
      created(){
      },
      props:{
        //visible:{type:Boolean,default:false},
        refData: {type: Array,default:[]},
        refColumns:{type:Array,default:[]},
        refUcode:{type:String,default:''},
        filterColumns:{type:Array,default:[]},
        wherePart:{type:String,default:''},
        selection:{type:Boolean,default:false},
        showButton:{type:Boolean,default:false},
        title:String,
        width:String,
        height:String,
        RowDbclick:Function,
        surebtnClick:Function,
        change:Function,
        imgClick:Function,
        bindData:Object,
        size:{type:String,default:'medium'},
        appendToBody:{type:Boolean,default:false},
        center:{type:Boolean,default:false}
      },
      data() {
        return {
          ROW:{},           //当前选中row
          search: '',
          visible:false,
          //参照输入框过滤状态  0 未过滤（初始状态）  -1  未选择状态    1  选择状态
          referInpType:0,
          selectionRows:[],   //当前所有勾选的行
          autoHeight:'',
          autoWidth:'',
          showCols : 0,
          columnsWidth:0,
        }
      },
      watch:{
        width(val){
          let width = val.substring(0,val.length-2)
          if(screen.availWidth>=1500){
            if(width>this.clientWidth*0.6){
              this.autoWidth = this.clientWidth*0.6+'px'
            }else{
              this.autoWidth = width+'px'
            }
          }else{
            if(width>this.clientWidth*0.7){
              this.autoWidth = this.clientWidth*0.7+'px'
            }else{
              this.autoWidth = width+'px'
            }
          }
        },
        height(val){
          if(typeof  val == 'undefined'){
            return false
          }
          this.autoHeight =val
        },
        bindData(val){
          this.$emit('update:bindData', val)
          this.$emit('change',val)
        },
        refColumns(val){
          this.$nextTick(()=>{
            let cols = this.$refs.tcol
            let twidth = 0
            for (let i = 0;i<cols.length;i++){
              twidth += cols[i].columnConfig.width
            }
            if(this.selection){
              this.width = twidth + 128 + 'px'
            }else{
              this.width = twidth + 78 + 'px'
            }
          })
        },
        referInpType(val){
          if(val==0){
            this.$emit('blur')
          }else if(val==1){
            this.referInpType = 0
          }
        },
      },
      mounted() {
      },
      methods:{
        renderHeader(h, {column, $index}) {
          let l = column.label.length
          let f = 18
          column.width = f * (l + 2)
          return h('span',[column.label])
        },
        toRefer(){
          this.$emit('img-click');
          let _this = this
          _this.initRefColumns(_this.refUcode)
          _this.initRefData(_this.refUcode)
          _this.visible = true
        },
        initRefColumns(ucode){
          let _this = this
          _this.initReferColums(ucode).then(cols => {
              _this.refColumns = cols
          })
        },
        initRefData(ucode){
          let _this = this
          _this.getRefConfiguration(ucode).then(ref => {
            _this.getReferData({
              data: ref,
              refCode: ucode,
              wherePart:_this.wherePart,
              success: function (data) {
                for (let i = 0; i < data.rls.length; i++) {
                  var item = data.rls[i]
                  var value = ''
                  if (!_this.isNull(_this.filterColumns)) {//非空验证  参照是否设置过滤字段
                    for (let j = 0; j < _this.filterColumns.length; j++) {   //参照设置过滤字段时  按照过滤字段增加过滤条件
                      value += item[_this.filterColumns[j]] + ' '
                    }
                  }else{
                    value = item[_this.refColumns[0].prop]+' '+item[_this.refColumns[1].prop]       //参照未设置过滤字段时  按照json文件配置的前两个字段过滤
                  }
                  item.value = value
                }
                _this.refData.reSet(data.rls);
              },
              error: function (err) {
                _this.$message.error('初始化参照失败:' + err);
              }
            });
          })
        },
        refColumDoubleClick(row, column, event){
          this.$emit('update:bindData',row[this.refColumns[0].prop])
          this.$emit('row-dbclick',row)
          this.visible = false;
        },
        filterData(search, cb){
          var restaurants = this.refData;
          var results = search ? restaurants.filter(this.createFilter(search)) : restaurants;
          this.referInpType=-1 //将状态置为‘未选择’状态
          cb(results);
        },
        createFilter(search) {
          return (restaurant) => {
            return (restaurant.value.toLowerCase().indexOf(search.toLowerCase()) != -1);
          };
        },
        inpSelect(data){
          this.$emit('update:bindData',data[this.refColumns[0].prop])
          this.$emit('row-dbclick',data)
          this.referInpType=1   //将状态置为‘选择’状态
        },
        inpFocus(){
          this.$emit('focus');
          this.initRefData(this.refUcode)
          this.initRefColumns(this.refUcode)
        },
        inpBlur() {
          var flag = this.referInpType == -1
          if (flag) {//已选择
            /*this.referInpType = 0
          } else {*/
            //未选择操作   清空自动带出来的文本框
            this.$emit('update:bindData', '')
            let row = {}
            for (let i = 0; i < this.filterColumns.length; i++) {
              row[this.filterColumns[i]] = ''
            }
            this.$emit('row-dbclick', row)
            this.$emit('surebtn-click',row)
            this.referInpType = 0
          }
          this.$emit('blur')
        },
        changeCurrentRow(currentRow, oldCurrentRow){
          this.ROW = currentRow
        },
        selectionChange(selection){
          this.selectionRows = selection
        },
        onbocancel(){
          this.visible = false
        },
        onbosure(){
          if(!this.selection){//单选时
            if(this.isNull(this.ROW))return this.$message.warning('没有选择的数据')
            this.$emit('update:bindData',this.ROW[this.refColumns[0].prop])
            this.$emit('surebtn-click',this.ROW)
            this.visible = false;
          }else{//允许多选时
            let rows = this.selectionRows
            if(this.isNull(rows)){
              return this.$message.warning('没有选择的数据')
            }
            //拼接返回值
            var retvalue = ''
            for(let i = 0; i<rows.length;i++){
              retvalue += rows[i][this.refColumns[0].prop]+','
            }
            this.$emit('update:bindData',retvalue.substring(retvalue.length-1,0))
            this.$emit('surebtn-click',rows)
            this.visible = false;
          }
        },
        openRefer(){
          this.height = document.body.clientHeight*0.6+'px'
        },
      }
    }
</script>

<style scoped>
  .ref_search{
    /*width: 99%;*/
  }
  .ref_btn{
    float: right;
    margin-top: 8px;
    margin-right: 10px;
  }
  .el-dialog__wrapper >>>	.el-dialog__body{font-size: 12px!important;padding: 10px 20px 20px 20px;}
	.el-dialog__wrapper >>>	.el-dialog__header{padding: 10px 20px 12px 20px;}
</style>
