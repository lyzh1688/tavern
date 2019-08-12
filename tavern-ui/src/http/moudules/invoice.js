import axios from '../axios'

/*
 * 发票管理模块
 */

// 删除
export const batchDelete = (fileId,valid) => {
  return axios({
    url: '/alleria/invoice/std/valid/'+ fileId + '/' + valid,
    method: 'put'
  })
}

// 分页查询
export const uploadFile = (formData) => {
  return axios({
    url: '/zuul/alleria/invoice/std/upload',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data;charset=utf-8'
    }
  })
}

// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/alleria/invoice/std/record/page',
    method: 'get',
    params
  })
}
// 分页查询
export const findDtlPage = (params) => {
  return axios({
    url: '/alleria/invoice/std/dtl/page',
    method: 'get',
    params
  })
}
