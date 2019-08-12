import axios from '../axios'

/*
 * 增值税管理模块
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
// 上传文件
export const uploadFile = (formData) => {
  return axios({
    url: '/zuul/alleria/invoice/zzs/upload',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data;charset=utf-8'
    }
  })
}

//查询记录
export const findFileGeneralPage = (params) => {
  return axios({
    url: '/alleria/invoice/zzs/scan',
    method: 'get',
    params
  })
}

//根据fileId查询记录
export const findFileScanDtl = (fileId) => {
  return axios({
    url: '/alleria/invoice/zzs/scan/' + fileId,
    method: 'get',
  })
}

// 修改记录
export const editScanDtl = (data) => {
  return axios({
    url: '/alleria/invoice/zzs/edit',
    method: 'put',
    data
  })
}
