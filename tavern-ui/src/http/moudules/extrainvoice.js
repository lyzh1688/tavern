import axios from '../axios'

/* 
 * 专票补票管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/alleria/invoice/extra/save',
        method: 'post',
        data
    })
}

export const deleteRecord = (recordId,valid) => {
  return axios({
    url: '/alleria/invoice/extra/valid/'+ recordId + '/' + valid,
    method: 'put'
  })
}

// 分页查询
export const findPage = (params) => {
    return axios({
        url: '/alleria/invoice/extra/page',
        method: 'get',
        params
    })
}
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
    return axios({
        url: '/extrainvoice/findPermissions',
        method: 'get',
        params
    })
}
