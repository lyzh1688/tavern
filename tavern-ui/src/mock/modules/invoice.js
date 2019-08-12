/*
 * 银行流水管理模块
 */

// 保存
export function save() {
  return {
    url: 'invoice/save',
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
    url: 'invoice/delete',
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
    url: 'invoice/findPage',
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
    url: 'invoice/findDtlPage',
    type: 'post',
    data: findPageData
  }
}
export function getDtlContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.invoiceId = '3100183130'
    obj.invoiceCode = '29539746'
    obj.buyerName =  '上海灏米广告有限公司'
    obj.buyerTaxCode =  '91310110MA1G8BRU4N'
    obj.bankBranch =  '招商银行股份有限公司上海高安支行'
    obj.bankAccount =  '121925277610501'
    obj.address =  '上海市杨浦区延吉中路77号201-B4室'
    obj.tel =  '021-6029 3308'
    obj.invoiceDate =  '2019-05-05'
    obj.productVersion =  '32.0'
    obj.receiptId =  '20190501'
    obj.productName =  '*广播影视服务*制作费'
    obj.productSpec =  '1'
    obj.productUnit =  '5'
    obj.productCount =  '20000'
    obj.productUnitPrice =  '2589.54'
    obj.taxRate =  '15'
    obj.tax =  '15'
    obj.taxTypeCode =  '3040701030000000000'
    content.push(obj)
  }
  return content
}
