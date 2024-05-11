import request from '@/utils/request'

const api_name = '/monitor/operlog'

// 查询操作日志列表
export function list(query) {
  return request({
    url: `${api_name}/list`,
    method: 'get',
    params: query
  })
}

// 删除操作日志
export function delOperlog(operId) {
  return request({
    url: `${api_name}/${operId}`,
    method: 'delete'
  })
}

// 清空操作日志
export function cleanOperlog() {
  return request({
    url: `${api_name}/clean`,
    method: 'delete'
  })
}
