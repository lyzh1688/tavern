import axios from '../axios'

/*
 * 客户管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/crm/info/save',
    method: 'post',
    data
  })
}

// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/crm/info/delete',
    method: 'delete',
    data
  })
}
// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/crm/info/page',
    method: 'get',
    params
  })
}
// 分页查询
export const findCompanyPage = (params) => {
  return axios({
    url: '/crm/company/page',
    method: 'get',
    params
  })
}
// 保存公司信息
export const saveCompany = (data) => {
  return axios({
    url: '/crm/company/save',
    method: 'post',
    data
  })
}
// 删除公司信息
export const batchDeleteCompany = (data) => {
  return axios({
    url: '/crm/company/delete',
    method: 'delete',
    data
  })
}
// 分页查询公司业务
export const findCompanyBusinessPage = (params) => {
  return axios({
    url: '/crm/business/page',
    method: 'get',
    params
  })
}
// 删除公司信息
export const batchDeleteCompanyBusiness = (data) => {
  return axios({
    url: '/crm/business/delete',
    method: 'delete',
    data
  })
}
// 分页查询
export const findStaffPage = (params) => {
  return axios({
    url: '/crm/staff/page',
    method: 'get',
    params
  })
}
// 保存员工信息
export const saveStaff = (data) => {
  return axios({
    url: '/crm/staff/save',
    method: 'post',
    data
  })
}
// 删除公司信息
export const batchDeleteStaff = (data) => {
  return axios({
    url: '/crm/staff/delete',
    method: 'delete',
    data
  })
}
// 分页查询订单
export const findOrderPage = (params) => {
  return axios({
    url: '/crm/order/page',
    method: 'get',
    params
  })
}
// 保存订单信息
export const saveOrder = (data) => {
  return axios({
    url: '/crm/order/save',
    method: 'post',
    data
  })
}
// 查询业务字典
export const findBizDict = (params) => {
  return axios({
    url: '/crm/dict/business',
    method: 'get',
    params
  })
}


// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
  return axios({
    url: '/user/customer/findPermissions',
    method: 'get',
    params
  })
}
