import request from '@/utils/request'

const api_name = '/system/job'

// 查询定时任务列表
export function list(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
  })
}
// 新增定时任务
export function add(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}

// 修改定时计划表达式
export function updateCron(data) {
  return request({
    url: `${api_name}/update`,
    method: 'put',
    data: data
  })
}

// 暂停定时计划
export function handlePause(data) {
  return request({
    url: `${api_name}/pause`,
    method: 'put',
    data: data
  })
}
//手动调用定时计划
export function handleManual(data) {
  return request({
    url: `${api_name}/manualJob`,
    method: 'post',
    data: data
  })
}

// 恢复定时计划
export function handleResume(data) {
  return request({
    url: `${api_name}/resume`,
    method: 'put',
    data: data
  })
}

// 删除定时任务
export function del(data) {
  return request({
    url: `${api_name}`,
    method: 'delete',
    data: data
  })
}
