import Vue from 'vue'
import Router from 'vue-router'
import Login from '../pages/Login'
import Index from '../pages/index'

import loginOut from '../pages/loginOut'
/* 增值税*/
import vatInvoiceFileUpload from '../pages/vatInvoice/vatInvoiceFileUpload'
/* 增值税 批次详情展示*/
import vatInvoiceFileDtl from '../pages/vatInvoice/vatInvoiceFileDtl'
/* 增值税 发票修正页面展示*/
import vatInvoiceFileAmend from '../pages/vatInvoice/vatInvoiceFileAmend'

/* 银行流水*/
import statementFileUpload from '../pages/bank/statementFileUpload'


Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login,
    meta: {
      requiresAuth: true, //是否需要登录验证
      keepAlive: false //不需要被缓存
    }
  }, {
    path: '/index',
    name: 'index',
    component: Index,
    meta: {
      keepAlive: true  //需要被缓存
    }
  }, {
    path: '/loginOut',
    name: 'loginOut',
    component: loginOut,
    meta: {
      keepAlive: false
    }
  },
  /*增值税 */
  {
    path: '/vatInvoice/vatInvoiceFileUpload',
    name: 'invoiceFileUpload',
    component: vatInvoiceFileUpload,
    meta: {
      keepAlive: true  //需要被缓存
    },
  },
  /*增值税 批次文件详情*/
  {
    path: '/vatInvoice/vatInvoiceFileDtl',
    name: 'invoiceFileDtl',
    component: vatInvoiceFileDtl,
    meta: {
      keepAlive: true  //需要被缓存
    }
  },
  /*增值税 修正页面*/
  {
    path: '/vatInvoice/vatInvoiceFileAmend',
    name: 'invoiceFileAmend',
    component: vatInvoiceFileAmend,
    meta: {
      keepAlive: true  //需要被缓存
    }
  },


  /*银行流水 */
  {
    path: '/bank/statementFileUpload',
    name: 'bankStatementFileUpload',
    component: statementFileUpload,
    meta: {
      keepAlive: true  //需要被缓存
    },
  },
]
const router = new Router({
  routes: routes
})

export default router
