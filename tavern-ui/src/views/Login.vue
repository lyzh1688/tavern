<template>
  <div>
    <div class="loginWrap">
      <el-form :model="loginForm" :rules="fieldRules" ref="loginForm" label-position="left" label-width="0px"
               class="demo-ruleForm login-container">
   <!-- <span class="tool-bar">
      &lt;!&ndash; 主题切换 &ndash;&gt;
      <theme-picker style="float:right;" class="theme-picker" :default="themeColor"
                    @onThemeChange="onThemeChange"></theme-picker>
      &lt;!&ndash; 语言切换 &ndash;&gt;
      &lt;!&ndash; <lang-selector class="lang-selector"></lang-selector>    &ndash;&gt;
    </span>-->
        <h2 class="title" style="padding-left:22px;">拓佑财务管理平台</h2>
        <el-form-item prop="account">
          <el-input type="text" v-model="loginForm.account" auto-complete="off" placeholder="账号"
                    @keyup.enter.native="login"
                    size="medium"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"
                    @keyup.enter.native="login"
                    size="medium"></el-input>
        </el-form-item>
       <!-- <el-form-item prop="userType">
          <el-select v-model="loginForm.userType" clearable auto-complete="off" placeholder="请选择登录类型" size="medium">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>-->

        <!-- <el-form-item>
           <el-col :span="12">
             <el-form-item prop="captcha">
               <el-input type="test" v-model="loginForm.captcha" auto-complete="off" placeholder="验证码, 单击图片刷新"
                         style="width: 100%;">
               </el-input>
             </el-form-item>
           </el-col>
           <el-col class="line" :span="1">&nbsp;</el-col>
           <el-col :span="11">
             <el-form-item>
               <img style="width: 100%;" class="pointer" :src="loginForm.src" @click="refreshCaptcha">
             </el-form-item>
           </el-col>
         </el-form-item>-->
        <!-- <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox> -->
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:48%;" @click.native.prevent="reset">重 置</el-button>
          <el-button type="primary" style="width:48%;" @click.native.prevent="login" :loading="loading">登 录</el-button>
        </el-form-item>
      </el-form>
      <!--<div style="padding-bottom: 0px">
        <el-footer >
          <p class="copyright">© 2018 </p>
        </el-footer>
      </div>-->
    </div>

  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import Cookies from "js-cookie"
  import ThemePicker from "@/components/ThemePicker"
  import LangSelector from "@/components/LangSelector"

  export default {
    name: 'Login',
    components: {
      ThemePicker,
      LangSelector
    },
    data() {
      return {
        loading: false,
        loginForm: {
          account: '',
          password: '',
          userType: '1',
          captcha: '',
          src: ''
        },
        fieldRules: {
          account: [
            {required: true, message: '请输入账号', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
         /* userType: [
            {required: true, message: '请选择登录类型', trigger: 'blur'}
          ]*/
          // ,
          // captcha: [
          //   { required: true, message: '请输入验证码', trigger: 'blur' }
          // ]
        },
        checked: true,
        options: [{
          value: '1',
          label: '员工'
        }, {
          value: '2',
          label: '客户'
        }]
      }
    },
    methods: {
      login() {
        this.loading = true
        let userInfo = {
          userAccnt: this.loginForm.account,
          password: this.loginForm.password,
          userType: this.loginForm.userType,
          captcha: this.loginForm.captcha
        }
        if (this.loginForm.account == undefined || this.loginForm.account == null || this.loginForm.account == '') {
          this.$message({
            message: "请输入用户名",
            type: 'error'
          })
          this.loading = false
          return;
        }
        if (this.loginForm.password == undefined || this.loginForm.password == null || this.loginForm.password == '') {
          this.$message({
            message: "请输入密码",
            type: 'error'
          })
          this.loading = false
          return;
        }
        /*if (this.loginForm.userType == undefined || this.loginForm.userType == null || this.loginForm.userType == '') {
          this.$message({
            message: "请选择用户类型",
            type: 'error'
          })
          this.loading = false
          return;
        }*/
        this.$api.login.login(userInfo).then((res) => {
          if (res.retCode != 0) {
            this.$message({
              message: res.retMessage,
              type: 'error'
            })
          } else {
            Cookies.set('token', res.data.token) // 放置token到Cookie
            sessionStorage.setItem('user', res.data.userAccnt) // 保存用户到本地会话
            sessionStorage.setItem('userId', res.data.userId) // 保存用户到本地会话
            sessionStorage.setItem('roles', res.data.roles) // 保存用户到本地会话
            sessionStorage.setItem('userInfo', JSON.stringify(res.data)) // 保存用户到本地会话
            sessionStorage.setItem('userName', res.data.userName) // 保存用户到本地会话
            this.$store.commit('menuRouteLoaded', false) // 要求重新加载导航菜单
            this.$router.push('/')  // 登录成功，跳转到主页
          }
          this.loading = false
        }).catch((res) => {
          this.$message({
            message: JSON.stringify(res),
            type: 'error'
          })
          this.loading = false
        });
      },
      refreshCaptcha: function () {
        // this.loginForm.src = this.global.baseUrl + "/captcha.jpg?t=" + new Date().getTime();
      },
      reset() {
        this.$refs.loginForm.resetFields()
      },
      // 切换主题
      onThemeChange: function (themeColor) {
        this.$store.commit('setThemeColor', themeColor)
      }
    },
    mounted() {
      this.refreshCaptcha()
    },
    computed: {
      ...mapState({
        themeColor: state => state.app.themeColor
      })
    }
  }
</script>

<style lang="scss" scoped>
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 100px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 30px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
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

  .copyright-footer {
    margin: 0 auto;
    padding: 0 22px;
    width: 1300px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .logo {
      margin-left: -50px;
    }
    .copyright {
      color: #666;
      line-height: 1.5;
      font-size: 12px;
    }
  }

</style>
