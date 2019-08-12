import axios from '../axios'

/*
 * 文件上传管理模块
 */
// 删除
export const batchDelete = (id) => {
  return axios({
    url: '/alleria/file/delete/' + id,
    method: 'delete',
  })
}
// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/alleria/file/page',
    method: 'get',
    params
  })
}

// 查询进度
export const findPercentage = (params) => {
  return axios({
    url: '/alleria/file/percentage',
    method: 'get',
    params
  })
}
