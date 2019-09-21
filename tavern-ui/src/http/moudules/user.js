import axios from '../axios'

/*
 * 用户管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/hrm/user/save',
    method: 'post',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/hrm/user/delete',
    method: 'delete',
    data
  })
}
// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/hrm/user/page',
    method: 'get',
    params
  })
}
// 查找用户的菜单权限标识集合
/*
export const findPermissions = (params) => {
    return axios({
        url: '/hrm/user/findPermissions',
        method: 'get',
        params
    })
}*/
export function findPermissions() {
  let permsData = {
    "code": 200,
    "msg": null,
    "data": [
      "sys:user:add",
      "sys:user:delete",
      "sys:user:edit",
      "sys:user:view",
      "sys:menu:add",
      "sys:menu:del",
      "sys:menu:edit",
      "sys:menu:view",
      "sys:role:add",
      "sys:role:edit",
      "sys:role:view",
      "sys:role:delete",
      "sys:dept:edit",
      "sys:dict:edit",
      "sys:dict:delete",
      "sys:log:view",
      "sys:dept:delete",
      "sys:dict:view",
      "sys:dept:view",
      "sys:dept:add",
      "sys:dict:add"
    ]
  }
  return {
    url: 'user/findPermissions',
    type: 'get',
    data: permsData
  }
}
