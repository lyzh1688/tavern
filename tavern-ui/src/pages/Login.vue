<template>
	<!--登录页-->
	<!--头部-->
	<div>
		<!--<div class="header">
			<img class="login-logo" src="../assets/images/logo.png" />
			<ul class="header-nav">
				<li class="nav-li">
					<a href="">首页</a>
				</li>
				<li class="nav-li">
					<a href="">平台介绍</a>
				</li>
				<li class="nav-li">
					<a href="">帮助中心</a>
				</li>
				<li class="nav-li">
					<a href="">联系我们</a>
				</li>
				<li class="nav-li"><img id="tel-img" src="../assets/images/telphone.png" />
					<a id="zanweikf" href="">暂未开放</a>
				</li>
			</ul>
		</div>-->
		<!--登录-->
		<div class="loginWrap">

			<div class="formWrap">
				<!--密码登录-->
				<div class="pswLogin" v-if="willShow">
					<!--<img class="topImg" src="../assets/images/erweima.png" @click="showScavenging" />-->
					<p class="tit" align="center">财务管理平台</p>
					<el-form :rules="rules" ref="loginForm" :model="user" status-icon label-width="75px">
						<div class="formGroup">
							<el-form-item label="账号" prop="name">
								<el-input type="text" auto-complete="off" v-model="user.name" placeholder="请输入您的账号" class="form-control"></el-input>
							</el-form-item>
							<el-form-item label="密码" prop="pass" >
								<el-input type="password" @keyup.enter.native="login" auto-complete="off" v-model="user.pass" placeholder="请输入密码" class="form-control"></el-input>
							</el-form-item>
							<el-form-item label="验证码" prop="validateNum" >
								<el-input type="text" auto-complete="off" v-model="user.validateNum" placeholder="" class="form-control" style="width: 30%;float: left;"></el-input>
								<div class="yanzhengma"><img class="validateImg" :src="imgUrl" /> <i class="el-icon-refresh" @click="editCaptcha"></i></div>
							</el-form-item>
						</div>
						<div class="remFor">
							<el-checkbox v-model="checked" class="remember">记住密码</el-checkbox>
						</div>
						<div class="formButton">
							<el-form-item style="width:100%;">
								<button type="button" class="button" @click="login">登录</button>
								<button type="button" class="button button1">取消</button>
							</el-form-item>
						</div>
					</el-form>
				</div>
			</div>

		</div>
		<!--底部-->
		<el-footer>
			<p>版权所有 &copy; </p>
		</el-footer>
	</div>
</template>

<script>
	import Cookies from 'js-cookie';
	export default {
		name: 'Login',
		data() {
			return {
				imgUrl: this.baseUrl + '/user/imgCode.html',
				user: {
					name: '',
					pass: '',
				},
				willShow: true,
				rules: {
					name: [{
						required: true,
						message: '用户名不能为空',
						trigger: 'blur'
					}, ],
					pass: [{
						required: true,
						message: '密码不能为空',
						trigger: 'blur'
					}, ]
				},
				checked: false,
				initQCode: '',
				websock: null,
				timeQZ: null,
			};
		},
		created() {
			//初始化的时候调用一下websokect，保持连接，并且发送数据给服务器，表名自己是谁
			let _this = this;
			//初始化的时候调用一下session域中的存的值，看看是不是因为路由超时导致的进入本业
			let outFlag = localStorage.getItem('pageOutTime');
			console.log('flag:'+outFlag);
			if(outFlag=='true'){
				let isfirst = sessionStorage.getItem('isfirst');
				if(isfirst=='no'){
					_this.$message.error('当前会话已经过期，请重新登录');
					//并且删除掉这个item
					sessionStorage.removeItem('pageOutTime');
				}
			}
			// _this.initWebSocket();
		},
		destroyed() {
			//首先取消定时调度方法
			clearTimeout(this.timeQZ);
			//离开当前页面的时候销毁当前的websokect
			// this.websock.close();
		},
		methods: {
			/*initWebSocket() {
				let _this = this;
				_this.websock = new WebSocket(_this.websocketUrl);
				_this.websock.onmessage = _this.websocketonmessage;
				_this.websock.onclose = _this.websocketclose;
				let msg = JSON.stringify({
					isInit: true
				});
				_this.websocketsend(JSON.stringify({
					type: 0,
					msg: msg
				}));
			},*/
			/**接受数据**/
		/*	websocketonmessage(data) {
				let _this = this;
				//接受到数据以后执行的业务操作  data是字符串类型
				//转换json实例
				let dataObj = JSON.parse(data.data);
				console.log(dataObj);
				_this.initQCode = dataObj.code;
				//定时器三秒以后再次发送一个重新生成的二维码过去(模拟二维码过期重置的操作)
				_this.timeQZ = setTimeout(function() {
					let msg = JSON.stringify({
						id: _this.initQCode,
						isResume: true
					});
					let sendMsg = JSON.stringify({
						type: 0,
						msg: msg
					});
					_this.websocketsend(sendMsg);
				}, 60 * 1000);
			},
			/!**关闭sokect**!/
			websocketclose() {
				console.log('断开连接');
			},
			websocketsend(data) {
				let _this = this;
				//若是ws开启状态
				if(_this.websock.readyState === this.websock.OPEN) {
					_this.websock.send(data);
				}else if(_this.websock.readyState === _this.websock.CONNECTING) {// 若是 正在开启状态，则等待300毫秒
					setTimeout(function() {
						_this.websock.send(data)
					}, 300);
				} else {// 若未开启 ，则开启并且等待500毫秒
					_this.initWebSocket();
					setTimeout(function() {
						_this.websock.send(data)
					}, 500);
				}
			},*/
			/*显示扫码登录*/
			showScavenging() {
				if(this.willShow == true) {
					this.willShow = false;
				} else {
					this.willShow = true;
				}
			},
			/*显示密码登录*/
			showpswLogin() {
				let _this = this;
				if(this.willShow == true) {
					this.willShow = false;
				} else {
					this.willShow = true;
				}
			},
			/*点击登录按钮*/
			login() {
				let _this = this;
				if(localStorage.getItem('Flag') == 'isLogin' ) { //判断当前浏览器的本地存储里面是不是有已经登录的信息
					_this.$router.push({
						name:'invoiceFileUpload'
					});
				}

				var userName = this.user.name;
				var pass = this.user.pass;
				var validateNum = this.user.validateNum;
				var isAutoLogin = this.checked;
				let data = {
					userName: userName,
					password: pass,
					code: validateNum,
					isAutoLogin: isAutoLogin
				};
				_this.ajaxPost({
					url: '/user/login',
					data: data,
					success(response) {
						if(response.statuCode == 200) {
							if(isAutoLogin == true) {
								Cookies.set('name', userName, {
									expires: 15
								}); //cookie保存15天
								Cookies.set('token', response.data.token, {
									expires: 15
								}); //cookie保存15天
							}
              Cookies.set('hello','world!');
							//在跳转之前关闭掉当前的websokect
							// _this.websock.close()
							//我们设置一个名为Flag，值为isLogin的字段，作用是如果Flag有值且为isLogin的时候，证明用户已经登录了。
							localStorage.setItem('Flag', 'isLogin');
							localStorage.setItem('pageOutTime','false');
							/*此处用sessionStorage 是因为如果用localStorage便永远存在，
							关闭浏览器重新打开，因为值一直存在，可能created方法里面判断后，
							可能显示'当前会话已经过期，请重新登录',会使人莫名其妙*/
							sessionStorage.setItem('isfirst','no');
							_this.$router.push({
								name:'invoiceFileUpload'
								//path: '/tabl'
							}); //登录成功后跳转到首页

						} else {
							/*登录失败时提示信息*/
							console.log(response);
							_this.$message({
								type: 'error',
								message: '用户名或者密码错误',
								showClose: true
							})
						}
					},
					error(err) {
						console.log(err)
					}
				})
			},
			/*点击更换验证码*/
			editCaptcha() {
				this.imgUrl = this.imgUrl + '?hy' + Math.random();
			}
		},
	}
</script>

<style scoped="scoped">
	ul,
	li {
		list-style: none;
	}

	a {
		text-decoration: none;
	}

	.header {
		width: 100%;
		height: 75px;
		position: absolute;
		top: 0;
		display: flex;
		justify-content: space-between;
		background: #fff;
	}

	.login-logo {
		width: 150px;
		height: 50px;
		margin-top: 15px;
		margin-left: 150px;
	}

	.header-nav {
		display: flex;
		justify-content: flex-end;
		margin-top: 30px;
		margin-right: 50px;
	}

	.header-nav li {
		margin: 0 30px;
		color: #333;
	}

	.header-nav li a {
		color: #333;
	}

	.header-nav li:hover a {
		color: #007FE1;
	}

	.header-nav li:hover #zanweikf {
		color: #999;
	}

	.header-nav li:last-child {
		position: relative;
	}

	#tel-img {
		position: absolute;
		top: -3px;
		left: -20px;
	}

	.loginWrap {
		background: #999;
		position: absolute;
		top: 75px;
		bottom: 60px;
		margin: 0 auto;
		width: 100%;
		background: url(../assets/images/bg-img.jpg) 50% no-repeat;
		background-size: cover;
	}

	.tit {
		font-size: 14px;
		font-weight: 600;
		margin-left: 30px!important;
		margin-top: 24px!important;
		margin-bottom: 20px!important;
	}

	/*.formGroup {
		margin-left: -5px;
	}*/

	.formWrap {
		width: 266px;
		height: 330px;
		background: #fff;
		border-radius: 3px;
		position: absolute;
		right: 15%;
		top: 20%;
	}

	.remember {
		margin-left: 15px;
	}

	.form-control {
		width: 85%;
	}

	.yanzhengma {
		border: 1px solid #dcdfe6;
		width: 31%;
		float: left;
		height: 28px;
		margin-left: 15px;
		position: relative;
		margin-top: 5px;
	}

	.validateImg {
		width: 100%;
		height: 100%;
	}

	.el-icon-refresh {
		position: absolute;
		top: 5px;
		right: -30px;
		font-size: 22px;
		color: #007FE1;
		cursor: pointer;
	}

	.button {
		display: block;
		border: 0;
		width: 220px;
		height: 33px;
		color: #fff;
		background: #90c31f;
		border-radius: 5px;
		margin: 0 -35px 10px;
		font-size: 14px;
		cursor: pointer;
	}

	.button:focus {
		outline: 0;
	}

	.button1 {
		background: #1E65D4;
	}

	.el-footer {
		width: 100%;
		height: 60px;
		text-align: center;
		position: absolute;
		bottom: 0;
	}

	.el-footer p {
		color: #666;
		font-size: 16px!important;
		margin-top: 14px!important;
	}

	.formButton {
		margin-left: -15px;
	}

	.topImg,
	.computerImg {
		position: absolute;
		right: 12px;
		top: 12px;
		cursor: pointer;
	}

	.el-form-item>>>.el-input__inner {
		height: 30px!important;
		padding: 5px 8px!important;
		font-size: 12px!important;
	}

	.form-inline>>>.el-form-item__label {
		font-size: 12px!important;
	}

	.el-form-item {
		margin-bottom: 12px!important;
	}

	.remFor {
		margin-left: 10px;
		margin-bottom: 8px;
		margin-top: -10px!important;
	}

	.scavengingWrap {
		width: 120px;
		height: 120px;
		padding: 10px;
		border: 1px solid #ddd;
		margin: 30px auto;
	}

	.scavenging-footer {
		height: 25px;
		display: flex;
		align-items: center;
	}

	.scavenging-footer img {
		margin-left: 40px;
		margin-right: 20px;
	}

	.scavenging-p {
		font-size: 12px;
		color: #999;
		line-height: 18px;
	}

	.scavenging-span {
		color: #007AFF;
	}

	.click-p {
		font-size: 12px;
		color: #999;
		text-align: right;
		margin-right: 20px;
		margin-top: 10px;
		cursor: pointer;
		width: 48px;
		float: right;
	}
</style>
