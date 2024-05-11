import request from '@/utils/request'

const api_name = '/system/web/information'

// 查询网站基本信息
export function getInformation() {
  return request({
    url: `${api_name}/getInfo`,
    method: 'get'
  })
}

// 修改网站基本信息
export function updateInformation(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 刷新参数缓存
export function refreshCache() {
  return request({
    url: `${api_name}/refreshCache`,
    method: 'delete'
  })
}
