<template>
	<!--头部-->
	<el-header>
		<el-row>
			<el-col :span="19">
				<div class="grid-content bg-purple top-left">
					<!--<img class="index-logo" src="../assets/images/index-logo.png" />-->
					<p class="hosName">{{loginName}}</p>
				</div>
			</el-col>
			<el-col :span="5">
				<div class="grid-content bg-purple top-right">

					<!--<div style="position: relative;"><img class="ir-icon" src="../assets/images/tishi.png" /><span class="tishiNum">8</span></div>
					<img class="ir-icon" src="../assets/images/quanping.png" id="btn" onclick="fullScreen()" />
					<img class="ir-icon" src="../assets/images/xiaoxi.png" />-->
					<div class="userWrap">
						<p style="margin-top: 15px!important;"><span class="iconfont icon-zidongdenglu"></span>{{loginId}}</p>
						<ul class="userList">
							<li class="user-li">
								<a href="javascript:void(0)" @click="userInfo">个人信息</a>
							</li>
							<li class="user-li">
								<a href="javascript:void(0)" @click="pwdEdit">修改密码</a>
							</li>
							<li class="user-li">
								<a href="javascript:void(0)" @click="forHelp">系统帮助</a>
							</li>
							<li class="user-li">
								<a href="javascript:void(0)" @click="logout">退出系统</a>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
		</el-row>

    <el-dialog title="修改密码" :visible.sync="pwdEditVisible" width="400px">
      <el-form :model="pwdEditForm" ref="peRef" :rules="rules" :inline="true" label-width="100px" status-icon>
        <el-form-item label="当前密码：" prop="oldPass">
          <el-input v-model="pwdEditForm.oldPass" placeholder="请输入当前密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码：" prop="newPass">
          <el-input v-model="pwdEditForm.newPass" placeholder="请输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码：" prop="checkPass">
          <el-input v-model="pwdEditForm.checkPass" placeholder="请再次输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="buttonClass" type="primary" @click="submitPwdeditForm('peRef')">提交</el-button>
          <el-button class="buttonClass" @click="resetPwdeditForm('peRef')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="个人信息" :visible.sync="userInfoVisible" width="400px">
      <el-form :model="userForm" ref="userRef" :rules="rules" :inline="true" label-width="100px" status-icon>
        <el-form-item label="主键：" prop="sid" v-if="false">
          <el-input v-model="userForm.sid"></el-input>
        </el-form-item>
        <el-form-item label="用户编码：" prop="loginId">
          <el-input v-model="userForm.loginId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="用户名称：" prop="firstName">
          <el-input v-model="userForm.firstName" placeholder="请输入用户名称" :disabled="!isUserEidt"></el-input>
        </el-form-item>
        <el-form-item label="名称：" prop="lastName">
          <el-input v-model="userForm.lastName" placeholder="请输入名称" :disabled="!isUserEidt"></el-input>
        </el-form-item>
        <el-form-item label="性别：" prop="genderCode">
          <el-radio v-model="userForm.genderCode" label="M" :disabled="!isUserEidt">男</el-radio>
          <el-radio v-model="userForm.genderCode" label="F" :disabled="!isUserEidt">女</el-radio>
        </el-form-item>
        <el-form-item label="用户类型：" prop="userType">
          <el-select  v-model="userForm.userType" placeholder="请选择" :disabled="true">
            <el-option label="超级管理员" value="0"></el-option>
            <el-option label="平台管理员" value="1"></el-option>
            <el-option label="平台" value="2"></el-option>
            <el-option label="医院" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态：" prop="status">
          <el-select  v-model="userForm.status" placeholder="请选择" :disabled="true">
            <el-option label="未激活" value="-1"></el-option>
            <el-option label="正常" value="0"></el-option>
            <el-option label="已冻结（停用）" value="1"></el-option>
            <el-option label="已注销" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上次登录时间：" prop="lastLoginTime">
          <el-date-picker v-model="userForm.lastLoginTime" type="datetime" placeholder="选择日期时间" :disabled="true"></el-date-picker>
        </el-form-item>
        <el-form-item label="版本号：" prop="version" v-if="false">
          <el-input v-model="userForm.version" ></el-input>
        </el-form-item>
        <el-form-item>
          <template>
            <span v-if="isUserEidt">
              <el-button class="buttonClass" type="primary" @click="submitUserForm">提交</el-button>
              <el-button class="buttonClass" @click="UserFormCancle">取消</el-button>
            </span>
            <span v-else>
              <el-button class="buttonClass" type="primary" @click="userFormEdit">修改</el-button>
              <el-button class="buttonClass" @click="userFormClose">关闭</el-button>
            </span>
          </template>
        </el-form-item>
      </el-form>
    </el-dialog>

	</el-header>
</template>

<script>
	export default {
		name: 'navHeader',
		data() {
      var validateOldPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }else {
          if (this.pwdEditForm.newPass !== '') {
            this.$refs.peRef.validateField('newPass');
          }
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }else if(this.pwdEditForm.oldPass == value){
          callback(new Error('新密码与旧密码不能一致'));
        }else {
          if (this.pwdEditForm.checkPass !== '') {
            this.$refs.peRef.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.pwdEditForm.newPass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
			return {
				loginId : '',
				loginName : '',
        pwdEditVisible:false,
        userInfoVisible:false,
        pwdEditForm:{
				  oldPass:'',
          newPass:'',
          checkPass:''
        },
        rules: {
          oldPass: [
            { validator: validateOldPass, trigger: 'blur' }
          ],
          newPass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
        },
        userForm:{
				  sid:'',
          loginId:'',
          firstName:'',
          lastName:'',
          genderCode:'',
          userType:'',
          status:'',
          lastLoginTime:'',
          version:-1
        },
        userDataBackup:null,
        isUserEidt:false,
			}
		},
		created : function(){
			let _this = this;
			_this.ajaxPost({
				url : '/user/getCurrentLoinInfo',
				success : function(data){
					if(data){
						_this.loginId = data.loginId
						_this.loginName = data.loginName
					}
				},
				error : function(err){
					console.log(err);
				}
			});
		},
		methods: {
			logout(){
				let _this = this;
				_this.ajaxPost({
					url : '/user/logout',
					success : function(data){
						if(data.statuCode == 200){
							_this.$message.success(data.msg)
							localStorage.setItem('Flag','')
							sessionStorage.setItem('isfirst','yes');
							_this.$router.push({
					          	path: '/',
					        });
						}else{
							_this.$message.error(data.msg)
						}
					},
					error : function(err){
						console.log(err);
					}
				});
			},
      pwdEdit(){
			  this.pwdEditVisible = true
      },
      submitPwdeditForm(formName) {
			  let _this = this
        _this.$refs[formName].validate((valid) => {
          if (valid) {//通过表单校验，调用修改密码的ajax
            _this.ajaxPost({
              url:'/user/passwordChange',
              data:{loginId:_this.loginId,oldPass:_this.pwdEditForm.oldPass,newPass:_this.pwdEditForm.newPass},
              success:function (data) {
                if(data.statuCode == 200){
                  _this.pwdEditVisible = false
                  setTimeout(() => {
                    _this.$alert('修改密码成功，请注销后重新登录', '提示',{
                      showClose : false,
                      callback : action=> {
                        _this.$router.push({
                          name: 'loginOut',
                          params: {type: 'EditCurrentPassword'}
                        })
                      }})
                  },750)
                }
              },
              error:function (err) {
                _this.$message.error('修改密码失败：'+err)
              }
            })
          } else {
            return false;
          }
        });
      },
      resetPwdeditForm(formName) {
        this.$refs[formName].resetFields();
      },
      forHelp(){
			  this.$message.info('功能正在开发中...')
      },
      userInfo(){
			  //初始化信息
        let _this = this
        _this.ajaxPost({
          url:'/user/initCurrentUser',
          data:{loginId:_this.loginId},
          success:function (data) {
            if(data.statuCode==200){
              data.user.userType = data.user.userType.toString()
              data.user.status = data.user.status.toString()
              _this.userDataBackup = JSON.stringify(data.user)
              _this.userForm = data.user
              _this.userInfoVisible = true
            }
          }
        })
      },
      userFormEdit(){
			  this.isUserEidt = true
      },
      userFormClose(){
        this.userInfoVisible = false
      },
      submitUserForm(){//修改方法
			  let _this = this
        _this.ajaxPost({
          url:'/user/updateCurrentUser',
          data:{sid:_this.userForm.sid,version:_this.userForm.version,firstName:_this.userForm.firstName,lastName:_this.userForm.lastName,genderCode:_this.userForm.genderCode},
          success:function (data) {
            if(data.statuCode==200){
              _this.loginName =  _this.userForm.lastName
              _this.isUserEidt = false
              _this.userInfoVisible = false
              _this.$message.success('修改成功!')
            }
          }
        })
      },
      UserFormCancle(){
        this.isUserEidt = false
        this.userForm = JSON.parse(this.userDataBackup)
      },
		}
	}
</script>

<style >
	/*头部*/
	ul li{list-style: none;}
	a{text-decoration: none;}
	.el-header {height: 58px!important;background: #3595df;color: #fff;display: flex;align-items: center;justify-content: space-between;}
	.el-row {width: 100%;/*height: 66px!important;*/}
	.top-left,.top-right {display: flex;align-items: center;height: 66px;}
    .index-logo {width: 147px;height: 38px;margin: 0 30px;}
	.ir-icon {margin-left: 20px;}
	.tishiNum {display: block;width: 18px;height: 18px;background: red;color: #fff;border-radius: 50%;text-align: center;position: absolute;top: 4px;left: 38px;line-height: 18px;font-size: 12px;}
	.userWrap {width: 100px;height: 66px;color: #fff;float: right;margin: 0 5% 0 8px;text-align: center;font-size: 14px;position: relative;}
	.icon-zidongdenglu {font-size: 24px;position: relative;top: 5px;left: -3px;}
	.userList {width: 100px;position: absolute;left: 0;top: 50px;background: #5c99c9;display: none;padding-left: 0;}
	.user-li {height: 34px;line-height: 34px;}
	.user-li a {color: #fff;}
	.userWrap:hover {background: #006ec2;}
	.userWrap:hover .userList {display: block;z-index: 5;}
	.user-li:hover {background: #1379c7;}
  .buttonClass{margin-left: 100px}
</style>
