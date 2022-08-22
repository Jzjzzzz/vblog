import request from '@/utils/request'

// 查询定时任务列表
export function list(query) {
  return request({
    url: '/system/job/list',
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
    url: '/sys/job?cron',
    method: 'put',
    data: data
  })
}
