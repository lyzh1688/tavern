
import axios from '../axios'

/*
 * 用户管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/user/accountant/save',
    method: 'post',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/user/accountant/delete',
    method: 'post',
    data
  })
}
// 分页查询
export const findPage = (data) => {
  return axios({
    url: '/user/accountant/findPage',
    method: 'post',
    data
  })
}
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
  return axios({
    url: '/user/accountant/findPermissions',
    method: 'get',
    params
  })
}
/*
/!*
 * 客户管理模块
 *!/

// 保存
export const save = (data) => {
  return axios({
    url: '/user/accountant/accountant/save',
    method: 'post',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/user/accountant/accountant/delete',
    method: 'post',
    data
  })
}
// 分页查询
export const findPage = (data) => {
  const findPageData = {
    data:{}
  }
  let pageNum = 1
  let pageSize = 8
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.account = '121544' + i
    obj.name = '林书豪' + index
    obj.position = '普通会计' + i
    obj.updateDate= '2018-08-14 11:11:11'
    content.push(obj)
  }
  findPageData.data.pageNum = pageNum
  findPageData.data.pageSize = pageSize
  findPageData.data.totalSize = 50
  findPageData.data.content = content
  return findPageData;
}


export function getContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.account = '121544' + i
    obj.name = '林书豪' + index
    obj.position = '普通会计' + i
    obj.updateDate= '2018-08-14 11:11:11'
    content.push(obj)
  }
  return content
}
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
  return axios({
    url: '/user/accountant/findPermissions',
    method: 'get',
    params
  })
}*/
