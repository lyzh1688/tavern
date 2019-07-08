<template>
  <div id="app" style="height: 100%;"  @mouseover="handleTime" @keyup="handleTime"> 
    <!--其他页-->
    <el-container class="nav-top" v-if="$route.meta.keepAlive" style="height: 100%;">
      <!--导航栏-->
      <keep-alive>
        <navHeader></navHeader>
      </keep-alive>
      <el-container>
        <!--左菜单栏-->
        <keep-alive>
          <leftMenu></leftMenu>
        </keep-alive>
        <!--Body路由容器-->
        <el-main>
          <router-view style="margin-left: 8px;"></router-view>
        </el-main>
      </el-container>
    </el-container>

    <!--登录页-->
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </div>
</template>

<script>
  import navHeader from './components/navHeader'
  import leftMenu from './components/leftMenu'

  export default {
    name: 'App',
    components: {
      navHeader,
      leftMenu
    },
    methods:{
    	handleTime(){
    		let _this = this;
    		let oldTime = localStorage.getItem('oldTime')
    		let pageOutTime = localStorage.getItem('pageOutTime')
    		let isfirst = sessionStorage.getItem('isfirst');
    		
    		if(oldTime == undefined || oldTime == null){
						//保存一下当前时间
						localStorage.setItem('oldTime',new Date().getTime());
						//说明当前是本次会话中第一次进入项目，那么只能跳转到登录页
						_this.$router.push({
		          	path : '/',
		        });
				}
    		
    		//已过期，且不是新打开的页面
    		if(pageOutTime == 'true' && !_this.isNull(isfirst)){
    				localStorage.setItem('oldTime',new Date().getTime());
						localStorage.setItem('Flag','');
	    			_this.$router.push({
		          	path : '/',
		        });
    		}
    		
    		if(new Date().getTime()-oldTime > (1200 * 1000)){
	    			localStorage.setItem('oldTime',new Date().getTime());
						localStorage.setItem('pageOutTime','true');
						localStorage.setItem('Flag','');
						
	    			_this.$router.push({
		          	path : '/',
		        });
    		}else{
    				localStorage.setItem('oldTime',new Date().getTime());
    		}
    	}
    }
  }
</script>

<style>
  html, body {
    height: 100%;
  }

  body {
    margin: 0;
    padding: 0;
  }

  #app {
    font-size: 16px;
    font-family: '微软雅黑', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .el-main {
    padding: 0px !important;
    background: #e6ebf2;
  }

  .nav-top {
    flex-direction: column
  }
</style>
