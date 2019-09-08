import axios from '../axios'

// 查询我的待办
export const findTodo = (params) => {
  return axios({
    url: '/workflow/event/todo/page',
    method: 'get',
    params
  })
}
// 查询历史备注
export const findLog = (params) => {
  return axios({
    url: '/workflow/event/log/page',
    method: 'get',
    params
  })
}
// 查询备注添加
export const saveLog = (formData) => {
  return axios({
    url: '/zuul/workflow/event/log/save',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data;charset=utf-8'
    }
  })
}
