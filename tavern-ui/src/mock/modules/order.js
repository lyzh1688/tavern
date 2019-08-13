/*
 * 用户管理模块
 */

// 保存
export function save() {
  return {
    url: 'user/save',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 批量删除
export function batchDelete() {
  return {
    url: 'user/delete',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 分页查询
export function findPage(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  let pageNum = 1
  let pageSize = 8
  if(params !== null) {
    // pageNum = params.pageNum
  }
  if(params !== null) {
    // pageSize = params.pageSize
  }
  let content = this.getContent(pageNum, pageSize)
  findPageData.data.pageNum = pageNum
  findPageData.data.pageSize = pageSize
  findPageData.data.totalSize = 50
  findPageData.data.content = content
  return {
    url: 'user/findPage',
    type: 'post',
    data: findPageData
  }
}
export function getContent(pageNum, pageSize) {
  let content = []
  let name = ["公司注册","商标注册","代理记账","公司招聘","公司注册","商标注册","代理记账","公司招聘"]
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.orderTime = '2018-08-14 11:11:11'
    obj.pay = '2751.56'
    obj.actualPay = '2051.56'
    obj.bizName = name[index]
    content.push(obj)
  }
  return content
}
// 分页查询
export function findDtlPage(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  let pageNum = 1
  let pageSize = 8
  if(params !== null) {
    // pageNum = params.pageNum
  }
  if(params !== null) {
    // pageSize = params.pageSize
  }
  let content = this.getDtlContent(pageNum, pageSize)
  findPageData.data.pageNum = pageNum
  findPageData.data.pageSize = pageSize
  findPageData.data.totalSize = 50
  findPageData.data.content = content
  return {
    url: 'user/findDtlPage',
    type: 'post',
    data: findPageData
  }
}
export function getDtlContent(pageNum, pageSize) {
  let content = []
  let name = ["公司注册","商标注册","代理记账","公司招聘","公司注册","商标注册","代理记账","公司招聘","公司注册","商标注册"]
  let staff = ["张三丰","李连杰","萧敬腾","广东余生","薛之谦","令狐冲","九阴真经","神农氏","杨幂"]
  let src = ["微信","QQ","旺旺","聊天宝","面谈","亲戚介绍","百度","电话","上门"]
  let com = ["微软","空客","洛克希德马丁","休斯顿火箭","美国国家宇航局","因特尔","SAP","华为","中兴"]
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.name = name[index]
    obj.staff = staff[index]
    obj.source = src[index]
    obj.company = com[index]
    obj.createTime = "2019-08-13 15:07:09"
    content.push(obj)
  }
  return content
}
// 查找用户的菜单权限标识集合
export function findPermissions() {
  let permsData = {
    "code": 200,
    "msg": null,
    "data": [
      null,
      "sys:user:view",
      "sys:menu:delete",
      "sys:dept:edit",
      "sys:dict:edit",
      "sys:dict:delete",
      "sys:menu:add",
      "sys:user:add",
      "sys:log:view",
      "sys:dept:delete",
      "sys:role:edit",
      "sys:role:view",
      "sys:dict:view",
      "sys:user:edit",
      "sys:user:delete",
      "sys:dept:view",
      "sys:dept:add",
      "sys:role:delete",
      "sys:menu:view",
      "sys:menu:edit",
      "sys:dict:add",
      "sys:role:add"
    ]
  }
  return {
    url: 'user/findPermissions',
    type: 'get',
    data: permsData
  }
}
