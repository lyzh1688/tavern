import axios from '../axios'

/*
 * 银行流水管理模块
 */
// 删除
export const batchDelete = (fileId,valid) => {
  return axios({
    url: '/alleria/bank/valid/'+ fileId + '/' + valid,
    method: 'put'
  })
}
// 上传文件
export const uploadFile = (formData) => {
  return axios({
    url: '/zuul/alleria/bank/upload',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data;charset=utf-8'
    }
  })
}
// 分页查询
export const findDtlPage = (params) => {
  return axios({
    url: '/alleria/bank/dtl',
    method: 'get',
    params
  })
}

// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/alleria/bank/page',
    method: 'get',
    params
  })
}
