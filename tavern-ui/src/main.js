// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'element-ui/lib/theme-chalk/index.css'
import './assets/icon/iconfont.css'
import '../static/css/vender.css'
import 'lib-flexible'
import 'babel-polyfill'
import 'url-search-params-polyfill'

import Vue from 'vue'
import axios from 'axios'
import Cookies from 'js-cookie'
import qs from 'qs'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import VueQriously from 'vue-qriously'
import Print from 'vue-print-nb'
import VueBarcode from '@xkeshi/vue-barcode'

Vue.config.productionTip = false

/* eslint-disable no-new */
Vue.use(ElementUI)
Vue.use(VueQriously)
Vue.use(Print)
Vue.component(VueBarcode.name, VueBarcode)
axios.defaults.withCredentials = true
Vue.prototype.$ajax = axios   /* 全局注册，使用方法：this.$ajax */
Vue.prototype.qs = qs     //全局注册，使用方法为:this.qs
Vue.prototype.suffix = '.api' // 项目统一后缀名
Vue.prototype.baseUrl = 'https://www.easy-mock.com/mock/5d121ec97bb97316834de88c/example'//全局注册，使用方法：this.baseUrl
// Vue.prototype.baseUrl = 'http://127.0.0.1:8080'   //全局注册，使用方法：this.baseUrl
// Vue.prototype.baseUrl = "http://192.168.1.225:8860/spd"; //全局注册，使用方法：this.baseUrl
// Vue.prototype.ftpUrl = "http://192.168.1.89"; //全局注册默认的ftp访问的前缀地址，便于获取文件展示
Vue.prototype.clientHeight = document.body.clientHeight     //全局变量  屏幕分辨率   高
Vue.prototype.clientWidth = document.body.clientWidth       //全局变量  屏幕分辨率   宽

Vue.prototype.isNull = function (data) {
  let reg = /^[0-9]+.?[0-9]*$/
  if (reg.test(data)) {
    return false
  } else {
    if (data == undefined || data == '' || data == null) {
      return true
    }
  }
  return false
}

Vue.prototype.inpNumberBlur = function (data) {
  if (data.indexOf('.') == -1) {
    return parseInt(data).toString()
  }
  var a0 = data.split('.')[0]
  var a1 = data.split('.')[1]
  var b
  var b0
  var b1
  if (a0.length > 0) {
    b0 = parseInt(a0).toString()
  } else if (a0.length == 0) {
    b0 = '0'
  }
  if (a1.length > 0 && parseInt(a1) == 0) {
    b1 = '0'
  } else if (a1.length > 0 && parseInt(a1) != 0) {
    b1 = a1
  } else if (a1.length == 0) {
    b1 = '0'
  }
  if (b1 != '0') {
    b = b0 + '.' + b1
  } else {
    b = b0
  }
  return b
}

/**
 * 驼峰命名法转换   下划线转驼峰
 * @param str
 * @returns {*}
 */
Vue.prototype.toHump = function (str) {
  return str.replace(/\_(\w)/g, function (all, letter) {
    return letter.toUpperCase()
  })
}
/**
 * 驼峰命名法转换   驼峰转下划线
 * @param str
 * @returns {string}
 */
Vue.prototype.toLine = function (str) {
  return str.replace(/([A-Z])/g, '_$1').toLowerCase()
}

/**
 * 初始化参照中列的信息
 * el-table-colum属性初始化
 * 初始化属性：prop,label,width,isShow,algin
 * @param data 参数：参照的json配置
 */
Vue.prototype.initReferColums = async function (type) {
  //根据传进来的标识直接获取json文件的配置
  return this.getRefConfiguration(type).then(ref => {
    var retCols = []
    var colums = ref.columns
    var showColums = ref.showName
    var hidColumns = ref.hidColumns
    var newHidCols = []
    if (colums.length == 0 || showColums.length == 0 || colums.length != showColums.length) return null
    //先处理需要隐藏的字段
    if (hidColumns != null && hidColumns.length != 0) {
      for (var k = 0; k < hidColumns.length; k++) {
        if (hidColumns[k].indexOf(' ') != -1) {
          let hid = hidColumns[k].split(' ')
          newHidCols.push(this.toHump(hid[hid.length - 1].trim()))
        } else if (hidColumns[k].indexOf('.') != -1) { /*根据.判断有没有前缀*/
          let hid = hidColumns[k].split('.')
          newHidCols.push(this.toHump(hid[hid.length - 1].trim()))
        } else {
          newHidCols.push(this.toHump(hidColumns[k]))
        }
      }
    }
    for (var j = 0; j < colums.length; j++) {
      let col = {}
      let prop = ''
      /*根据空格判断有没有别名*/
      if (colums[j].indexOf(' ') != -1) {
        let strarr = colums[j].split(' ')
        prop = this.toHump(strarr[strarr.length - 1].trim())
      } else if (colums[j].indexOf('.') != -1) { /*根据.判断有没有前缀*/
        let strarr = colums[j].split('.')
        prop = this.toHump(strarr[strarr.length - 1].trim())
      } else {
        prop = this.toHump(colums[j])
      }
      //判断是否有隐藏字段
      if (newHidCols.indexOf(prop) > -1) {
        col.isShow = false
      } else {
        col.isShow = true
      }
      col.prop = prop
      col.label = showColums[j]
      col.width = 120 + 'px'
      col.algin = 'center'
      retCols.push(col)
    }
    if (retCols.length == 0) return null
    return retCols
  })
}

/*
给表单数据增加isEditFlag属性  默认false
用于表单数据需要输入文本时
 */
Vue.prototype.initTableArray = function (arr) {
  for (var i = 0; i < arr.length; i++) {
    arr[i].isEditFlag = false
  }
}

/*初始化json文件的数据 并刷新dom   取消全局变量做法 直接返回值*/
Vue.prototype.getRefConfiguration = async function (val) {
  return this.$ajax.get('static/data/reference2.0.json').then(res => {
    return res.data[val]
  })
}

/*参照ajax方法*/
Vue.prototype.getReferData = function (data) {
  let _this = this
  let code = data.refCode
  let method = data.method
  let url = data.url
  let wherePart = data.wherePart
  let json = data.data
  if (method == null) { //默认使用POST方式传值
    method = 'POST'
  }
  if (url == null) { //默认路径
    url = '/refer/tbData'
  }
  if (url.indexOf('/') == 0) { //说明默认就是不带前缀直接进行地址请求
    url = this.baseUrl + url
  }
  if (url.indexOf(this.suffix) == -1) {
    url = url + this.suffix
  }
  if (this.isNull(code)) {
    return this.$message.error('初始化参照失败：参照编码为空！')
  }
  let params = new URLSearchParams()
  if (!this.isNull(json)) {
    for (const item in json) {
      params.append(item, json[item])
    }
  }
  if (!this.isNull(wherePart)) {//参照动态where条件判断
    if (!this.isNull(params.get('wherePart').trim()) && wherePart.trim().length >= 3 && wherePart.trim().substring(0, 3).toLowerCase() != 'and') {
      wherePart = 'and ' + wherePart
    }
    params.set('wherePart', params.get('wherePart') + ' ' + wherePart)
  }
  this.$ajax({
    url: url,
    method: method,
    data: params
  }).then(function (respone) {
  	localStorage.setItem('oldTime',new Date().getTime());
    if (respone.status === 200) {
      data.success(respone.data)
    } else {
      _this.$message.error('参照初始化失败！')
    }
  })
    .catch(function (err) {
      console.log(err)
    })
}

Vue.prototype.ajaxPost = function (options) {
  let _this = this
  if (options.toString().indexOf('[object') === -1) {
    options = JSON.parse(options)
  }
  if (this.isNull(options.url)) {
    options.error('url is null')
    return
  }
  // 判断url是不是默认不带前缀地址
  if (options.url.indexOf('/') == 0) {
    // 说明默认就是不带前缀直接进行地址请求
    options.url = this.baseUrl + options.url
  }
  // 再去判断是否包含项目请求后缀
 /* if (options.url.indexOf(this.suffix) == -1) {
    options.url = options.url + this.suffix
  }*/
  // 判断请求类型
  var defMethod = 'POST'
  if (!this.isNull(options.method)) {
    defMethod = options.method.toUpperCase
  }
  //判断是否符合请求类型
  if (defMethod.toUpperCase() == 'GET' || defMethod.toUpperCase() == 'POST' ||
    defMethod.toUpperCase() == 'DELETE' || defMethod.toUpperCase() == 'PUT' ||
    defMethod.toUpperCase() == 'REQUEST' || defMethod.toUpperCase() == 'HEAD') {
  } else {
    options.error('method is not in GET,POST,DELETE,PUT,REQUEST,HEAD')
    return
  }
  //判断传来的参数
  let params = new URLSearchParams()
  if (!this.isNull(options.data)) {
    for (const item in options.data) {
      params.append(item, options.data[item])
    }
  }
  this.$ajax({
    url: options.url,
    method: defMethod,
    data: params
  })
    .then(function (data) {
    	localStorage.setItem('oldTime',new Date().getTime());
      //验证下ajax的data.data中是否含有无权限/未登录的异常，如果有，自动跳转到登录页面,不进行事件分发
      //let res = data.data
      if (data.data.statuCode == 200) {
        options.success(data.data)
      } else {
        //判断当前错误码是不是-998 或者 -999 ，-998代表当前用户检测到未经过当前页面认证，理应跳转到登录路由页面，-999代表当前的方法无权限访问，返回当前用户无权限的提示
        if (data.data.statuCode == -998) {
          _this.$router.push({path: '/'})
          _this.$message.error('抱歉，请登录以后再进行操作')
        } else if (data.data.statuCode == -999) {
          _this.$message.error('抱歉，当前用户暂无访问权限')
        } else if (data.data.statuCode == -1000) {
          _this.$message.error('抱歉，当前用户长时间未操作，请重新登录')
          _this.$router.push({
            path: '/'
          })
        }else if(data.data.statuCode == -1999){ //失效或未登录，重新跳转到登录页面，并将localStorage 中 Flag置为空
        	_this.$message.error(data.data.msg)
        	localStorage.setItem('Flag', '')
        	_this.$router.push({path: '/'})
        }else if(data.data.statuCode == -1700){ //密码失效，跳转到正在注销界面
          _this.$alert(data.data.msg, '提示',{
            showClose : false,
            callback : action=> {
              _this.$router.push({
                name: 'loginOut',
                params: {type: 'PasswordInvalidation'}
              })
          }})
        }else {
          //如果不是200，说明当前请求是失败的，默认进行处理了
          let msg = ''
          if(_this.isNull(data.data.msg)){
            msg = data.data
          }else {
            msg = data.data.msg
          }
          _this.$message.error('操作失败:' + msg)
        }
      }
    })
    .catch(function (err) {
      console.log(err)
      //options.error(err)
    })
}

//全局上传的方法，第一个参数固定为自定义上传的content，第二个为上传参数，内部传递可以传url，oData(额外参数),success,error回调等，默认只需要设置上传的url，可以不存在前缀，如果有就不添加，没有自动添加前缀
Vue.prototype.upload = function (content, url) {
  //var _this = this
  var uploadFile = new FormData()
  //默认后台接受的文件名必须叫uploadFile
  uploadFile.append('uploadFile', content.file)
  //如果是/开始，说明没设置前缀，默认自动添加
  if (url.indexOf('/') == 0) {
    url = this.baseUrl + url
  }
  this.$ajax({
    url: url,
    method: 'post',
    data: uploadFile
  })
    .then(function (data) {
      content.onSuccess('成功')
    })
    .catch(function (err) {
      content.onError('上传文件失败' + err)
    })
}

/**
 *  对象clone方法
 */
Vue.prototype.clone = function (obj) {
  let temp = null
  if (obj instanceof Array) {
    temp = obj.concat()
  } else if (obj instanceof Function) {
    //函数是共享的是无所谓的，js也没有什么办法可以在定义后再修改函数内容
    temp = obj
  } else {
    temp = {}
    for (let item in obj) {
      let val = obj[item]
      temp[item] = typeof val == 'object' ? this.clone(val) : val //这里也没有判断是否为函数，因为对于函数，我们将它和一般值一样处理
    }
  }
}
/**
 **js把GMT标准时间转成日期（即yyyy-MM-dd不含时分秒）格式(针对elmentUi的DatePicker日期选择器的值进行转换)
 ** 第一个参数是需要转换的时间，第二个参数是自定义转换的时间格式，传入'/'就转换成yyyy/MM/dd格式（可以不传，默认是yyyy-MM-dd格式）
 */
Vue.prototype.convertDate = function (date, formatStr) {
  if (this.isNull(date)) {
    return ''
  }
  var defFormatStr = this.isNull(formatStr) ? '-' : formatStr
  //不为空开始转换
  var d = new Date(date)
  //记录一下当前的月份和日期，防止数字不足导致格式不满足
  let month = (d.getMonth() + 1)
  if (month < 10) {
    month = '0' + month
  }
  let day = d.getDate()
  if (day < 10) {
    day = '0' + day
  }
  return d.getFullYear() + defFormatStr + month + defFormatStr + day
}

/**
 **js把GMT标准时间转成时间格式（即yyyy-MM-dd hh:mm:ss）格式(针对elmentUi的DateTimePicker日期时间选择器的值进行转换)
 **第一个参数是需要转换的时间，第二个参数是自定义转换的时间格式，传入'/'就转换成yyyy/MM/dd格式（可以不传，默认是yyyy-MM-dd格式）
 **第三个参数是自定义转换的时分秒拼接的格式，传入'/'默认时分秒之间连接格式为hh/mm/ss(默认时间格式是hh:mm:ss),第四个参数是日期和时间之间连接通过什么连接，可以自己传参数来连接，默认为' '
 */
Vue.prototype.convertTime = function (time, dateFormatStr, timeFormatStr, connectStr) {
  if (this.isNull(time)) {
    return ''
  }
  //转换的格式
  var defdateFormatStr = this.isNull(dateFormatStr) ? '-' : dateFormatStr
  var defTimeFormatStr = this.isNull(timeFormatStr) ? ':' : timeFormatStr
  var defConnectStr = this.isNull(connectStr) ? ' ' : connectStr
  //不为空开始转换
  var d = new Date(time)
  let month = (d.getMonth() + 1)
  if (month < 10) {
    month = '0' + month
  }
  let day = d.getDate()
  if (day < 10) {
    day = '0' + day
  }
  let hours = d.getHours()
  if (hours < 10) {
    hours = '0' + hours
  }
  let minu = d.getMinutes()
  if (minu < 10) {
    minu = '0' + minu
  }
  let seconds = d.getSeconds()
  if (seconds < 10) {
    seconds = '0' + seconds
  }
  return d.getFullYear() + defdateFormatStr + month + defdateFormatStr + day + defConnectStr + hours +
    defTimeFormatStr + minu + defTimeFormatStr + seconds
}
/**
 * 是否存在的验证方法
 * @param {Object} item
 */
/*Array.prototype.contains = function (item) {
  var _this = this
  var len = _this.length
  for (var i = 0; i < len; i++) {
    if (equals(_this[i], item)) {
      return true
    }
  }
  return false
}*/

/**
 * js的array原型链添加一个获取当前item对应index的方法，不存在返回-1
 * @param {Object} item
 */
Array.prototype.getIndex = function (item) {
  var _this = this
  var len = _this.length
  for (var i = 0; i < len; i++) {
    if (_this[i] == item) {
      return i
    }
  }
  return -1
}

//原型链array添加一个add系列方法，常用有把一个数组中的数据放入另一个数组中，pushAll函数
Array.prototype.pushAll = function (array) {
  var _this = this
  var len = array.length
  for (var i = 0; i < len; i++) {
    _this.push(array[i])
  }
}

//原型链array中添加remove系列方法
Array.prototype.remove = function (item) {
  //调用原型链添加的getIndex方法获取在当前数组的index，进行移除
  var _this = this
  var index = _this.getIndex(item)
  if (index == -1) {
    return
  }
  _this.splice(index, 1)
}

//原型链array中添加remove系列方法
Array.prototype.removeIndex = function (index) {
  //调用原型链添加的getIndex方法获取在当前数组的index，进行移除
  var _this = this
  let reg = /^[0-9]+.?[0-9]*$/
  if (index < 0 || index == undefined || !reg.test(index)) {
    return
  }
  _this.splice(index, 1)
}

//删除数组中所有存在的item
Array.prototype.removeAll = function (array) {
  var _this = this
  var len = array.length
  for (var i = len - 1; i >= 0; i--) {
    _this.remove(array[i])
  }
}

//替换操作(指定原来的index和要替换成哪一个)
Array.prototype.updateIndex = function (index, item) {
  var _this = this
  if (index > -1) {
    _this.splice(index, 1, item)
  }
}

//替换item(指定原来的item是哪一个，现在替换成哪一个)
Array.prototype.updateItem = function (oldItem, newItem) {
  var _this = this
  var index = _this.getIndex(oldItem)
  if (index == -1) {
    return
  }
  _this.splice(index, 1, newItem)
}

//将整个数组的所有内容改成指定新数组的内容，并且要触发视图更新操作(比如每次查询以后重新设置值作为当前的展示的结果)
Array.prototype.reSet = function (array) {
  var _this = this
  _this.clear()
  _this.pushAll(array)
}

//清空一个对象中的内容，比如form表单，根据类型进行动态清理数据，并且给定默认值，可以选择不清理部分属性，有需要不清理的传递string数组进来(数据量过大会导致卡死，慎用)
Array.prototype.clear = function () {
  var _this = this
  _this.splice(0, _this.length)
}
//使用拦截器在请求前对Header进行添加
/*Vue.http.interceptors.push((request, next) => {
  if (window.localStorage.getItem('token')) {
  //  Vue.http.headers.common['Authorization'] = 'Bearer ' + window.localStorage.getItem('token')
    Vue.http.headers.common['Authorization'] =  window.localStorage.getItem('token')
  }
  next((response) => {
    if (response.status === 400 || response.status === 401) {
      localStorage.clear()  // 当 Token 已经失效时，清空所有保存在 localStorage 的数据
      window.location.pathname = '/login'
    }
  })
})
*/
//路由验证
router.beforeEach((to, from, next) => { //to即将进入的目标路由对象，from当前导航正要离开的路由，next 下一步执行的函数钩子
//let token = Cookies.get('token')
////没有之前的时间，说明当前是第一次访问，就想直接跳过登录
////获取一下之前保存的操作时间
//let oldTime = sessionStorage.getItem('oldTime')
////与当前时间进行比较，判断一下当前时间距离上次操作的时间是不是已经超时了
//if (oldTime == undefined || oldTime == null) {
//  //保存一下当前时间
//  sessionStorage.setItem('oldTime', new Date().getTime())
//  //说明当前是本次会话中第一次进入项目，那么只能跳转到登录页
//  next({path: '/'})
//  return
//}
////如果距离上次操作的时间超过session过期时间，那么默认为你长时间不进行操作，需要重新登录，并且给予提示
//if (new Date().getTime() - oldTime > (60 * 1000 * 10)) {
//  sessionStorage.setItem('oldTime', new Date().getTime())
//  sessionStorage.setItem('pageOutTime', true)
//  next({path: '/'})
//  return
//}
////每次路由跳转出去之前记录一下当前操作的时间
//sessionStorage.setItem('oldTime', new Date().getTime())
//if (to.path === '/') {
//  next()
//} else { //进入的不是登录路由，并且还未登录，则路由定向到登录路由
//  if (to.meta.requiresAuth && (!token || token === null)) {
//    next({
//      path: '/'
//    })
//  } else {
//    next() //已经登录成功，直接放行
//  }
//}

		//没有之前的时间，说明当前是第一次访问，就想直接跳过登录
		//获取一下之前保存的操作时间
		let oldTime = localStorage.getItem('oldTime');
		//与当前时间进行比较，判断一下当前时间距离上次操作的时间是不是已经超时了
		if(oldTime == undefined || oldTime == null){
				//保存一下当前时间
				localStorage.setItem('oldTime',new Date().getTime());
				//说明当前是本次会话中第一次进入项目，那么只能跳转到登录页
				next();
		}
		//如果距离上次操作的时间超过session过期时间，那么默认为你长时间不进行操作，需要重新登录，并且给予提示
		if(new Date().getTime() - oldTime > (3600 * 1000)){
				localStorage.setItem('oldTime',new Date().getTime());
				localStorage.setItem('pageOutTime',true);
				localStorage.setItem('Flag','');
				next({path : '/'});
		}
		//每次路由跳转出去之前记录一下当前操作的时间
		localStorage.setItem('oldTime',new Date().getTime());

		if(to.path == '/') { //如果要去的是登录页
				if(localStorage.getItem('Flag') == 'isLogin' ) { //判断当前浏览器的本地存储里面是不是有已经登录的信息
						next({
								name: 'invoiceFileUpload'
						}) //有的话，ok，去你想去的地址
				} else { //没有的话，也就是没有登录
						next();
				}
				//		next() //那么随意，去就是了
		} else {
				if(localStorage.getItem('Flag') == 'isLogin') { //判断当前浏览器的本地存储里面是不是有已经登录的信息
						next() //有的话，ok，去你想去的地址
				} else { //没有的话，也就是没有登录
						next({
								path: '/'    // 如果从这里走，则又跳转到  if(to.path == '/') 这行，该方法的出口就是 next();
						}) //去登录页
				}
		}

})

new Vue({
  el: '#app',
  router,
  components: {
    App
  },
  template: '<App/>'
})
