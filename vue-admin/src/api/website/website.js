import request from '@/utils/request'

const api_name = '/website/resource'

// 查询站点资源基本信息列表
export function listWebsiteInform(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
  })
}
// 新增资源
export function addWebSite(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}
// 根据Id查询资源详细
export function getInfo(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
  })
}

// 修改资源
export function updateWebsite(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 删除资源
export function delWebSite(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'delete'
  })
}
