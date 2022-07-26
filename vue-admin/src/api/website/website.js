import request from '@/utils/request'

// 查询站点资源基本信息列表
export function listWebsiteInform(query) {
  return request({
    url: '/website/resource/list',
    method: 'get',
    params: query
  })
}
