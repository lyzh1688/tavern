<template>
<div class="fontStyle">
  <span >{{prefix}}<span style="color: red">{{count}}</span>{{suffix}}</span>
</div>
</template>

<script>
    export default {
        name: 'loginOut',
      created(){
          this.initText()
          this.lodding()
      },
      data(){
        return {
          prefix:'',
          suffix:'',
          type:this.$route.params.type,
          count:3,
        }
      },
      methods:{
          initText(){
            if(this.type=='PasswordInvalidation'){//密码失效
              this.suffix = '秒后自动跳转...'
            }else if(this.type=='EditCurrentPassword'){//修改当前用户密码
              this.prefix = '正在注销，'
              this.suffix = '秒后回到登录界面...'
            }
          },
          lodding(){
            setTimeout(() => {
              this.count--
              if(this.count<=0){
                localStorage.setItem('Flag', '')
                sessionStorage.setItem('isfirst','yes');
                this.$router.push({path: '/'})
                //this.$router.push({name: 'testVue'})
              }else{
                this.lodding()
              }
            }, 750)
          }
      }
    }
</script>

<style scoped>
.fontStyle{
  padding-left: 20px;
  padding-top: 20px;
  font-size: 20px;
  letter-spacing: 1.3px;
  font-family: "宋体";
  font-weight: bold;
}
</style>
