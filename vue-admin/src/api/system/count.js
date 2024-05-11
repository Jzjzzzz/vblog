import request from '@/utils/request'

const api_name = '/system/count'

// 获取统计数据
export function getCount() {
  return request({
    url: `${api_name}`,
    method: 'get'
  })
}


// 获取过去七天单日统计数据
export function getSevenCount() {
  return request({
    url: `${api_name}/getSevenCount`,
    method: 'get'
  })
}
