import Vue from 'vue'
import Router from 'vue-router'
import Cookies from "js-cookie"
import Login from '@/views/Login'
import NotFound from '@/views/Error/404'
import Home from '@/views/Home'
import Intro from '@/views/Intro/Intro'
import Generator from '@/views/Generator/Generator'
import api from '@/http/api'
import store from '@/store'
import Dtl from '@/views/Zzs/Dtl'
import BankDtl from '@/views/bank/Dtl'
import InvoiceDtl from '@/views/invoice/Dtl'
import InvoiceQuery from '@/views/invoice/Query'
import Amend from '@/views/Zzs/Amend'
import SalaryQuery from '@/views/Salary/Query'
import SalaryDtl from '@/views/Salary/Dtl'
import CustomerDtl from '@/views/PreSales/CustomerDtl'
import CompanyDtl from '@/views/PreSales/CompanyDtl'
import OrderDtl from '@/views/PreSales/OrderDtl'
import Order from '@/views/PreSales/Order'
import WorkFlow from '@/views/WorkFlow/WorkFlow'
import {getIFramePath, getIFrameUrl} from '@/utils/iframe'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: '首页',
      component: Home,
      children: [
        {
          path: '',
          name: '系统主页',
          component: Intro,
          meta: {
            icon: 'fa fa-home fa-lg',
            index: 0
          }
        }
      ]
    },
    {
      path: '/login',
      name: '登录',
      component: Login
    },
    {
      path: '/404',
      name: 'notFound',
      component: NotFound
    },
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/zzs/dtl',
          name: '增值税验证结果',
          component: Dtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0,
            keepAlive: true  //需要被缓存
          }
        }
      ]
    },
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/zzs/amend',
          name: '增值税修正详情',
          component: Amend,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0,
            keepAlive: true  //需要被缓存
          }
        }
      ]
    }, {
      path: '/',
      component: Home,
      children: [
        {
          path: '/bank/dtl',
          name: '流水明细',
          component: BankDtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    }, {
      path: '/',
      component: Home,
      children: [
        {
          path: '/invoice/dtl',
          name: '发票明细',
          component: InvoiceDtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    },/*,{
      path: '/',
      component: Home,
      children: [
        {
          path: '/invoice/query',
          name: '发票查询',
          component: InvoiceQuery,
          meta: {
            icon: 'fa fa fa-search ',
            index: 0
          }
        }
      ]
    },*/ {
      path: '/',
      component: Home,
      children: [
        {
          path: '/salary/dtl',
          name: '工资明细',
          component: SalaryDtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    },/*,{
      path: '/',
      component: Home,
      children: [
        {
          path: '/salary/query',
          name: '工资查询',
          component: SalaryQuery,
          meta: {
            icon: 'fa fa fa-search ',
            index: 0
          }
        }
      ]
    },*/
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/preSales/customerDtl',
          name: '客户详情',
          component: CustomerDtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    },
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/preSales/companyDtl',
          name: '公司详情',
          component: CompanyDtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    },
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/preSales/orderDtl',
          name: '订单详情',
          component: OrderDtl,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    },
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/preSales/order',
          name: '订单管理',
          component: Order,
          meta: {
            icon: 'fa fa-shopping-cart ',
            index: 0
          }
        }
      ]
    },
    {
      path: '/',
      component: Home,
      children: [
        {
          path: '/workFlow/workFlow',
          name: '工作流日志',
          component: WorkFlow,
          meta: {
            icon: 'fa fa-file-text ',
            index: 0
          }
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  // 登录界面登录成功之后，会把用户信息保存在会话
  // 存在时间为会话生命周期，页面关闭即失效。
  let token = Cookies.get('token')
  let userName = sessionStorage.getItem('user')
  if (to.path === '/login') {
    // 如果是访问登录界面，如果用户会话信息存在，代表已登录过，跳转到主页
    if (token) {
      next({path: '/'})
    } else {
      next()
    }
  } else {
    if (!token) {
      // 如果访问非登录界面，且户会话信息不存在，代表未登录，则跳转到登录界面
      next({path: '/login'})
    } else {
      // 加载动态菜单和路由
      addDynamicMenuAndRoutes(userName, to, from)
      next()
    }
    if(to.matched.length == 0){
      next({path: '/login'})
    }
  }
})

/**
 * 加载动态菜单和路由
 */
function addDynamicMenuAndRoutes(userName, to, from) {
  // 处理IFrame嵌套页面
  handleIFrameUrl(to.path)
  if (store.state.app.menuRouteLoaded) {
    console.log('动态菜单和路由已经存在.')
    return
  }
  let res = api.menu.findNavTree({'userName': userName})
  // .then(res => {
  // 添加动态路由
  let dynamicRoutes = addDynamicRoutes(res.data)
  // 处理静态组件绑定路由
  handleStaticComponent(router, dynamicRoutes)
  router.addRoutes(router.options.routes)
  // 保存加载状态
  store.commit('menuRouteLoaded', true)
  // 保存菜单树
  store.commit('setNavTree', res.data)
  // }).then(res => {
  //   api.user.findPermissions({'name':userName}).then(res => {
  //     // 保存用户权限标识集合
  //     store.commit('setPerms', res.data)
  //   })
  // })
  // .catch(function(res) {
  // })
}

/**
 * 处理路由到本地直接指定页面组件的情况
 * 比如'代码生成'是要求直接绑定到'Generator'页面组件
 */
function handleStaticComponent(router, dynamicRoutes) {
  for (let j = 0; j < dynamicRoutes.length; j++) {
    if (dynamicRoutes[j].name == '代码生成') {
      dynamicRoutes[j].component = Generator
      break
    }
  }
  router.options.routes[0].children = router.options.routes[0].children.concat(dynamicRoutes)
}

/**
 * 处理IFrame嵌套页面
 */
function handleIFrameUrl(path) {
  // 嵌套页面，保存iframeUrl到store，供IFrame组件读取展示
  let url = path
  let length = store.state.iframe.iframeUrls.length
  for (let i = 0; i < length; i++) {
    let iframe = store.state.iframe.iframeUrls[i]
    if (path != null && path.endsWith(iframe.path)) {
      url = iframe.url
      store.commit('setIFrameUrl', url)
      break
    }
  }
}

/**
 * 添加动态(菜单)路由
 * @param {*} menuList 菜单列表
 * @param {*} routes 递归创建的动态(菜单)路由
 */
function addDynamicRoutes(menuList = [], routes = []) {
  var temp = []
  for (var i = 0; i < menuList.length; i++) {
    if (menuList[i].children && menuList[i].children.length >= 1) {
      temp = temp.concat(menuList[i].children)
    } else if (menuList[i].url && /\S/.test(menuList[i].url)) {
      menuList[i].url = menuList[i].url.replace(/^\//, '')
      // 创建路由配置
      var route = {
        path: menuList[i].url,
        component: null,
        name: menuList[i].name,
        meta: {
          icon: menuList[i].icon,
          index: menuList[i].id
        }
      }
      let path = getIFramePath(menuList[i].url)
      if (path) {
        // 如果是嵌套页面, 通过iframe展示
        route['path'] = path
        route['component'] = resolve => require([`@/views/IFrame/IFrame`], resolve)
        // 存储嵌套页面路由路径和访问URL
        let url = getIFrameUrl(menuList[i].url)
        let iFrameUrl = {'path': path, 'url': url}
        store.commit('addIFrameUrl', iFrameUrl)
      } else {
        try {
          // 根据菜单URL动态加载vue组件，这里要求vue组件须按照url路径存储
          // 如url="sys/user"，则组件路径应是"@/views/sys/user.vue",否则组件加载不到
          let array = menuList[i].url.split('/')
          let url = ''
          for (let i = 0; i < array.length; i++) {
            url += array[i].substring(0, 1).toUpperCase() + array[i].substring(1) + '/'
          }
          url = url.substring(0, url.length - 1)
          route['component'] = resolve => require([`@/views/${url}`], resolve)
        } catch (e) {
        }
      }
      routes.push(route)
    }
  }
  if (temp.length >= 1) {
    addDynamicRoutes(temp, routes)
  } else {
    console.log('动态路由加载...')
    console.log(routes)
    console.log('动态路由加载完成.')
  }
  return routes
}

export default router
