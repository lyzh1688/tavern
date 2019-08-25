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
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
  return axios({
    url: '/user/customer/findPermissions',
    method: 'get',
    params
  })
}
