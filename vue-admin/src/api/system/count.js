import request from '@/utils/request'

// 获取统计数据
export function getCount() {
  return request({
    url: '/sys/count',
    method: 'get'
  })
}


// 获取过去七天日统计数据
export function getSevenCount() {
  return request({
    url: '/sys/count/getSevenCount',
    method: 'get'
  })
}
