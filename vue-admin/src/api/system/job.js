import request from '@/utils/request'

// 查询定时任务列表
export function list(query) {
  return request({
    url: '/system/job',
    method: 'get',
    params: query
  })
}
// 新增定时任务
export function add(data) {
  return request({
    url: '/system/job',
    method: 'post',
    data: data
  })
}

// 修改定时计划表达式
export function updateCron(data) {
  return request({
    url: '/system/job/update',
    method: 'put',
    data: data
  })
}

// 暂停定时计划
export function handlePause(data) {
  return request({
    url: '/system/job/pause',
    method: 'put',
    data: data
  })
}

// 恢复定时计划
export function handleResume(data) {
  return request({
    url: '/system/job/resume',
    method: 'put',
    data: data
  })
}

// 删除定时任务
export function del(data) {
  return request({
    url: '/system/job',
    method: 'delete',
    data: data
  })
}
