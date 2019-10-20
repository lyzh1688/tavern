import axios from '../axios'

// 查询我的待办
export const findTodo = (params) => {
  return axios({
    url: '/workflow/event/todo/page',
    method: 'get',
    params
  })
}
// 查询所有
export const findAllEvent = (params) => {
  return axios({
    url: '/workflow/event/todo/all',
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
// 查询下一节点信息
export const  findNextNode = (params) => {
  return axios({
    url: '/workflow/def/nextNode',
    method: 'get',
    params
  })
}
// 查询ROOT下一节点信息
export const  findRootNextNode = (params) => {
  return axios({
    url: '/workflow/def/rootNextNode',
    method: 'get',
    params
  })
}
// 查询下一节点操作人信息
export const findNextOperator = (params) => {
  return axios({
    url: '/workflow/def/operator',
    method: 'get',
    params
  })
}
// 查询备注添加
export const saveNextEvent = (formData) => {
  return axios({
    url: '/zuul/workflow/event/next',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data;charset=utf-8'
    }
  })
}
// 流程日志
export const findGraphLog = (params) => {
  return axios({
    url: '/workflow/event/log/graph',
    method: 'get',
    params
  })
}
// 延期批示
export const delayNotes = (data) => {
  return axios({
    url: '/workflow/event/delay',
    method: 'post',
    data
  })
}
// 退款批示
export const drawBack = (data) => {
  return axios({
    url: '/workflow/event/refund',
    method: 'post',
    data
  })
}
// 批示
export const reChoose = (data) => {
  return axios({
    url: '/workflow/event/reChoose',
    method: 'post',
    data
  })
}
// 查询相关转授权人员
export const reChooseOperator = (params) => {
  return axios({
    url: '/workflow/event/reChoose/operator',
    method: 'get',
    params
  })
}

export const drawBackOperator = (params) => {
  return axios({
    url: '/workflow/event/refundOperator',
    method: 'get',
    params
  })
}
