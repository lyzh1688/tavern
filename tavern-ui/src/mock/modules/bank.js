/*
 * 银行流水管理模块
 */

// 保存
export function save() {
  return {
    url: 'bank/save',
    type: 'post',
    data: {
      "code": 0,
      "msg": null,
      "data": 1
    }
  }
}
// 批量删除
export function batchDelete() {
  return {
    url: 'bank/delete',
    type: 'post',
    data: {
      "code": 0,
      "msg": null,
      "data": 1
    }
  }
}
// 分页查询
export function findPage(params) {
  let findPageData = {
    "code": 0,
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
  findPageData.data.current = pageNum
  findPageData.data.size = pageSize
  findPageData.total = 50
  findPageData.data.content = content
  return {
    url: 'bank/findPage',
    type: 'post',
    data: findPageData
  }
}
export function getContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.batchId = index
    obj.accountPeriod = '2018-09-14'
    obj.fileName = '工商银行-20190708'
    obj.updateDate =  '2018-09-14 12:12:12'
    content.push(obj)
  }
  return content
}
// 分页查询
export function findDtlPage(params) {
  let findPageData = {
    "code": 0,
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
  findPageData.data.current = pageNum
  findPageData.data.size = pageSize
  findPageData.total = 50
  findPageData.data.content = content
  return {
    url: 'bank/findDtlPage',
    type: 'post',
    data: findPageData
  }
}
export function getDtlContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.customAccntId = '31001669701052514387-0001'
    obj.customAccntName = '上海元升财务咨询有限公司'
    obj.tradeDate =  '20190501 04:07:44'
    obj.debitAmount =  '20000'
    obj.creditAmount =  '20000'
    obj.balance =  '1000'
    obj.coinCode =  '人民币元'
    obj.recvAccntId =  '31050161364000004065'
    obj.recvAccntName =  '艺域商务咨询（上海）有限公司'
    obj.institution =  '建行上海浦东分行'
    obj.accountingDate =  '20190501'
    obj.companySeq =  '35325'
    obj.certificateType =  '电子转账凭证'
    obj.certificateId =  '102322869612'
    obj.debitAmount =  '20000'
    content.push(obj)
  }
  return content
}
