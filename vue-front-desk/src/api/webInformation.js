import request from '@/utils/request'

// 查询网站基本信息
export function getInformation() {
  return request({
    url: '/front/config/getConfig',
    method: 'get'
  })
}
