<template>
  <!--左菜单栏-->
  <el-aside id="aside" width="220px">
    <div class="sliderBar-top">
      <span class="iconfont icon-admin"></span>
      <div class="slideBar-usrname">
        <p v-show="isShowUser"><span>{{loginName}}</span></p>
        <p v-show="isShowUser">({{loginId}})</p>
      </div>
    </div>
    <!--菜单区域-->
    <el-row class="tac">
      <el-col>
        <el-switch v-model="isCollapse" active-color="#13ce66" inactive-color="#ff4949"
                   @change="switchChange" style="padding-left: 18px;">
        </el-switch>
      </el-col>
      <el-col :span="24">
        <el-menu router :default-active="$route.path" :unique-opened="true" class="el-menu-vertical-demo"
                 @open="handleOpen" @close="handleClose" :collapse="!isCollapse">
          <el-submenu v-for='(role,num) in userRoles' :key="num" v-bind:index="''+num+1">
            <template slot="title">
              <i class="el-icon-message"></i><!-- 可以使用-->
              <span slot="title">{{role.name}}</span>
            </template>
            <el-menu-item-group v-for='(childMenu,childNum) in role.children' :key="childNum">
              <el-menu-item :index="childMenu.href">{{childMenu.name}}</el-menu-item>
            </el-menu-item-group>
            <!--例子-->
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
  </el-aside>
</template>

<script>
  import Cookies from 'js-cookie';

  export default {
    name: 'leftMenu',
    data() {
      return {
        loginId: '',
        loginName: '',
        userName: '',
        userRoles: [],
        isCollapse: true,
        isShowUser: true,
        initWidth: ''
      }
    },
    created: function () {
      let _this = this;
      var token = Cookies.get('token'); //获取存储的cookie
      _this.ajaxPost({
        url: '/menu/findAllMenus',
        data: {
          'token': token
        },
        success(data) {
          _this.userRoles = data.menus.children;
          _this.loginId = data.loginId;
          _this.loginName = data.loginName;
        },
        error(err) {
          console.log(err);
        }
      });
      setTimeout(() => {
        let aside = document.querySelector('#aside');
        this.initWidth = aside.style.width;
      }, 500)
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      switchChange(value) {
        let aside = document.querySelector('#aside');
        if (!value) {
          this.isShowUser = false;
          aside.style.width = 90 + 'px';
        } else {
          this.isShowUser = true;
          aside.style.width = this.initWidth;
        }
      },
    }

  }
</script>

<style scoped="scoped">
  #aside {
    background: #fff;
    color: #333;
  }

  .sliderBar-top {
    height: 65px;
    background: #fff;
  }

  .icon-admin {
    font-size: 48px;
    color: #007fe1;
    float: left;
    margin: -3px 15px 0 10px;
  }

  .slideBar-usrname {
    font-size: 14px;
    color: #999;
    margin-top: 25px;
  }

  .slideBar-usrname p {
    margin: 0;
    line-height: 24px;
  }

  /*菜单样式*/
  .slide-icon {
    font-size: 22px;
    margin: 0 5px;
  }

  .el-menu-item {
    padding: 0;
    text-indent: 20px;
    height: 40px !important;
    line-height: 40px !important;
  }
</style>
