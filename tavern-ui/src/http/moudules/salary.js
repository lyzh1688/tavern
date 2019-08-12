import axios from '../axios'

/*
 * 工资单管理模块
 */
// 删除
export const batchDelete = (fileId,valid) => {
  return axios({
    url: '/alleria/salary/valid/'+ fileId + '/' + valid,
    method: 'put'
  })
}
// 上传文件
export const uploadFile = (formData) => {
  return axios({
    url: '/zuul/alleria/salary/upload',
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
    url: '/alleria/salary/dtl/page',
    method: 'get',
    params
  })
}

// 分页查询
export const findPage = (params) => {
  return axios({
    url: '/alleria/salary/page',
    method: 'get',
    params
  })
}
