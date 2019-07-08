<template>

<div class="mainWrap">

<!--消息提醒-->
<el-row class="messageWrap">
  <el-col :span="4" class="messageDetail">
  	<span class="iconfont icon-zhengzhaoweituisongtixing"></span>
  	<p>证照未推送提醒</p>
  	<p>10</p>
  </el-col>
  <el-col :span="4" class="messageDetail">
  	<span class="iconfont icon-pinzhongweiweihuzhengzhaotixing"></span>
  	<p>品种未维护证照提醒</p>
  	<p>10</p>
  </el-col>
  <el-col :span="4" class="messageDetail">
  	<span class="iconfont icon-shengchanqiyeweiweihuzhengzhaotixing"></span>
  	<p>生产企业未维护证照提醒</p>
  	<p>10</p>
  </el-col>
  <el-col :span="4" class="messageDetail">
  	<span class="iconfont icon-zhengzhaoweishenhetixing"></span>
  	<p>证照未审核提醒</p>
  	<p>10</p>
  </el-col>
  <el-col :span="4" class="messageDetail">
  	<span class="iconfont icon-zhengzhaoyibohuitixing"></span>
  	<p>证照已驳回提醒</p>
  	<p>10</p>
  </el-col>
  <el-col :span="4" class="messageDetail">
  	<span class="iconfont icon-zhucezhengweiguanlianchangjiashouquanshutixing"></span>
  	<p>注册证未关联厂家授权书提醒</p>
  	<p>10</p>
  </el-col>
</el-row>
<!--待处理&&库存效期提醒-->
<div class="secondCon">
	<div class="pendingWrap">
		<p class="tit-p">待处理</p>
		<div class="pendingDetail">
			<div class="detailLeft">
				<span class="iconfont icon-daiquerendingdan"></span>
				<div class="detail-p">
					<p>待确认订单</p>
					<p>800</p>
				</div>
			</div>
			<div class="line"></div>
			<div class="detailLeft" >
				<span class="iconfont icon-daipeisongdingdan"></span>
				<div class="detail-p">
					<p>待配送订单</p>
					<p>800</p>
				</div>
			</div>
		</div>
	</div>
	<div class="pendingWrap">
		<p class="tit-p">库存效期提醒</p>
		<div class="pendingDetail">
			<div class="detailLeft">
				<span class="iconfont icon-jijiangguoqikucun"></span>
				<div class="detail-p">
					<p>即将过期库存</p>
					<p>800</p>
				</div>

			</div>
			<div class="line"></div>
			<div class="detailLeft">
				<span class="iconfont icon-yiguoqikucun"></span>
				<div class="detail-p">
					<p>已过期库存</p>
					<p>800</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!--证照效期预警&&证照总体情况-->
	<div class="secondCon">
		<div class="pendingWrap licenceWrap1">
			<p class="tit-p">证照效期预警</p>
			<!--表格-->
			 <el-table
			 	:cell-style="cellStyle"
		      :data="tableData"
		      style="width: 100%">
		      <el-table-column
		        prop="licenceName"
		        label="证照"
		       >
		      </el-table-column>
		      <el-table-column
		        prop="weiguoqi1"
		        label="90天即将过期证照"
		       >
		      </el-table-column>
		      <el-table-column
		        prop="weiguoqi2"
		        label="30天即将过期证照"
		       >
		      </el-table-column>
		      <el-table-column
		        prop="yiguoqi"
		        label="已过期证照">
		      </el-table-column>
		    </el-table>
		</div>
		<div class="pendingWrap licenceWrap2">
			<p class="tit-p">证照总体情况</p>
			 <el-table
		      :data="tableData"
		      style="width: 100%">
		      <el-table-column
		        prop="licenceName"
		        label="证照"
		       >
		      </el-table-column>
		      <el-table-column
		        prop="weiguoqi1"
		        label="数量"
		      >
		      </el-table-column>
		    </el-table>
		</div>
	</div>
<!--公告展示-->
<div class="secondCon">
	<div class="pendingWrap gonggaoWrap">
		<p class="tit-p">公告展示</p>
		<ul class="gonggao-lists">
			<li class="gonggao-li gonggao-active">
				<p>公告展示公告展示公告展示公告展示<img src="../assets/images/new.png"/></p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li gonggao-active">
				<p>公告展示公告展示公告展示公告展示<img src="../assets/images/new.png"/></p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li">
				<p>公告展示公告展示公告展示公告展示</p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li">
				<p>公告展示公告展示公告展示公告展示</p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li">
				<p>公告展示公告展示公告展示公告展示</p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li">
				<p>公告展示公告展示公告展示公告展示</p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li">
				<p>公告展示公告展示公告展示公告展示</p>
				<span class="time">2018.04.13</span>
			</li>
			<li class="gonggao-li">
				<p>公告展示公告展示公告展示公告展示</p>
				<span class="time">2018.04.13</span>
			</li>
		</ul>
	</div>
</div>
</div>

</template>

<script>
	export default{
		name:'index',
		 data() {
        return {
          tableData: [{
            licenceName: '供应商证照',
            weiguoqi1: '322',
            weiguoqi2:'22',
            yiguoqi: '20'
          }, {
            licenceName: '生产企业证照',
           weiguoqi1: '322',
            weiguoqi2:'22',
            yiguoqi: '20'
          }, {
            licenceName: '注册证证照',
            weiguoqi1: '322',
            weiguoqi2:'22',
            yiguoqi: '20'
          }, {
           licenceName: '厂家授权书证照',
           weiguoqi1: '322',
            weiguoqi2:'22',
            yiguoqi: '20'
          }, {
           licenceName: '合同证照',
           weiguoqi1: '322',
            weiguoqi2:'22',
            yiguoqi: '20'
          }]
        }
      }

	}
</script>

<style scoped>
	p{margin: 0!important;}
	/*消息提醒*/
	.messageWrap{height: 164px!important;}
	.messageDetail{height: 164px;color: #fff;text-align: center;}
	.messageDetail:first-child{background: #007FE1;}
	.messageDetail:nth-of-type(2){background: #229fff;}
	.messageDetail:nth-of-type(3){background: #3caaff;}
	.messageDetail:nth-of-type(4){background: #5cb8ff;}
	.messageDetail:nth-of-type(5){background: #80c8ff;}
	.messageDetail:nth-of-type(6){background: #a2d7ff;}
	.messageDetail span{font-size: 56px;line-height:90px;}
	.messageDetail p:first-child{font-size: 14px;margin: 0!important;}
	.messageDetail p:last-child{font-size: 20px;margin-top: 14px;color:#ffea00;}

	.secondCon{display: flex;justify-content: space-between;margin-top: 30px;}
	/*待处理*/
	.pendingWrap{width: 49%;/*height: 160px;*/border: 1px solid #ddd;}
	.tit-p{font-size: 16px;color:#333;background: #e6ebf2;height: 40px;line-height: 40px;text-indent: 40px;}
	.pendingDetail{display: flex;align-items: center;height: 120px;flex: 2;width: 100%;}
	.line{width: 1px;height: 50px;background: #ddd;}
	.detailLeft{display: flex;height: 62px;width: 49.9%;}
	.icon-daiquerendingdan,.icon-daipeisongdingdan,.icon-yiguoqikucun,.icon-jijiangguoqikucun{font-size:55px;margin:0 20px 0 80px;}
	.icon-daiquerendingdan,.detail-p:nth-of-type(1)>p:nth-of-type(2){color: #007FE1;}
	.icon-daipeisongdingdan{color: #fc7d00;}
	.icon-jijiangguoqikucun{color: #00aeb4;}
	.icon-yiguoqikucun{color: #e00000;}
	.detail-p p:first-child{font-size: 18px;}
	.detail-p p:last-child{font-size:20px;}
	/*证照*/
	.licenceWrap1{width: 55%;}
	.licenceWrap2{width: 43%;}
/*证照效期预警*/
/*公告展示*/
.gonggaoWrap{width: 100%;}
.gonggao-lists{width: 96%;margin: 0 auto;height:165px;overflow-y:auto ;}
.gonggao-li{height: 42px;border-bottom: 1px solid #ddd;line-height: 39px;font-size:12px;color: #999;}
.gonggao-active{color: #333;}
.gonggao-li p{width:75%;float: left;margin-left: 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
.time{float: right;margin-right: 85px;}
</style>
