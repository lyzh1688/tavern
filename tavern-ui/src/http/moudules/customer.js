import axios from '../axios'

/*
 * 用户管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/user/customer/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/user/customer/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/user/customer/findPage',
        method: 'post',
        data
    })
}
// 分页查询
export const findCustomerPage = (data) => {
  return axios({
    url: '/user/customer/findCustomerPage',
    method: 'post',
    data
  })
}
// 分页查询
export const findCompanyPage = (data) => {
    return axios({
        url: '/user/customer/findCompanyPage',
        method: 'post',
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
