import request from '@/utils/request'

// 获取统计数据
export function getCount() {
  return request({
    url: `/system/count`,
    method: 'get'
  })
}


// 获取过去七天单日统计数据
export function getSevenCount() {
  return request({
    url: `/system/count/getSevenCount`,
    method: 'get'
  })
}
