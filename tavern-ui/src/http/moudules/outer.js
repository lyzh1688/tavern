import axios from '../axios'

/*
 * 用户管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/user/outer/save',
    method: 'post',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/user/outer/delete',
    method: 'post',
    data
  })
}
// 分页查询
export const findPage = (data) => {
  return axios({
    url: '/user/outer/findPage',
    method: 'post',
    data
  })
}
// 分页查询
export const findBak = (params) => {
  return axios({
    url: '/user/outer/findBak',
    method: 'get',
    params
  })
}
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
  return axios({
    url: '/user/outer/findPermissions',
    method: 'get',
    params
  })
}
