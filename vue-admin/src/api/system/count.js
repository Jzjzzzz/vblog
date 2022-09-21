import request from '@/utils/request'

// 获取统计数据
export function getCount() {
  return request({
    url: '/sys/count',
    method: 'get'
  })
}
