import axios from '../axios'

/*
 * 合作方管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/crm/thirdParty/save',
    method: 'post',
    data
  })
}
// 删除
export const deleteOne = (params) => {
  return axios({
    url: '/crm/thirdParty/delete',
    method: 'delete',
    params
  })
}
// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/crm/thirdParty/page',
    method: 'get',
    params
  })
}
