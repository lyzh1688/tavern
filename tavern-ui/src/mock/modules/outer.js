/*
 * 用户管理模块
 */

// 保存
export function save() {
  return {
    url: 'user/outer/save',
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
    url: 'user/outer/delete',
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
    url: 'user/outer/findPage',
    type: 'post',
    data: findPageData
  }
}

export function getContent(pageNum, pageSize) {
  let content = []
  let name = ["张三丰","李连杰","萧敬腾","广东余生","薛之谦","令狐冲","九阴真经","神农氏","杨幂"]
  let bizType = ["许可证办理","商标注册","代理记账","公积金代缴","社保代缴","工商变更","公司注销","代理记账催收"]
  let aliAccnt = ["爱情错觉","半吨兄弟","火箭少女","倒带","年少有为","远走高飞","你的酒馆对我打了烊","苦行僧","大头儿子"]
  let weChatName = ["爱情错觉","半吨兄弟","火箭少女","倒带","年少有为","远走高飞","你的酒馆对我打了烊","苦行僧","大头儿子"]
  let com = ["微软","空客","洛克希德马丁","休斯顿火箭","美国国家宇航局","因特尔","SAP","华为","中兴"]
  let city = ["上海","大连","荆州","合肥","郑州","邯郸","西安","贵阳","镇江"]
  let district = ["浦东新区","庐阳区","黄浦区","闸北区","静安区","拱墅区","嘉定区","杨浦区","崇明区"]
  let curProcess = ['公积金代缴','社保代开','社保代缴','公积金代开','公积金代缴','社保代开','社保代缴','公积金代开','社保代缴']
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.name = name[index]
    obj.aliAccnt = aliAccnt[index]
    obj.comName = com[index]
    obj.city = city[index]
    obj.district = district[index]
    obj.weChatName = weChatName[index]
    obj.bizType = bizType[index]
    obj.cost = 10+i
    obj.emeLevel = '重要'
    obj.maxDays = 10+i
    obj.emeLevel = '重要'
    obj.emeLevel = '重要'
    obj.createTime= '2019-08-18 18:07:08'
    obj.curProcess= curProcess[index]
    obj.customerLevel= 'VIP'
    content.push(obj)
  }
  return content
}
// 分页查询
export function findBak(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  if(params !== null) {
    // pageNum = params.pageNum
  }
  if(params !== null) {
    // pageSize = params.pageSize
  }
  let content = this.getBakContent()
  findPageData.data.content = content
  return {
    url: 'user/outer/findBak',
    type: 'get',
    data: findPageData
  }
}

export function getBakContent() {
  let content = []
  let writer = ["马云","王健林","马化腾","徐一航","郑伊健"]
  for(let i=0; i<5; i++) {
    let obj = {}
    obj.content = "此单是镇江市的订单，非常重要"
    obj.writer = writer[i]
    obj.link = "http://101.132.134.96/invoice/timg.jpg"
    obj.time= '2019-08-18 18:07:08'
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
    url: 'user/outer/findPermissions',
    type: 'get',
    data: permsData
  }
}
