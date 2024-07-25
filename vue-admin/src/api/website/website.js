import request from '@/utils/request'

// 查询站点资源基本信息列表
export function listWebsiteInform(query) {
  return request({
    url: `/website/resource`,
    method: 'get',
    params: query
  })
}
// 新增资源
export function addWebSite(data) {
  return request({
    url: `/website/resource`,
    method: 'post',
    data: data
  })
}
// 根据Id查询资源详细
export function getInfo(id) {
  return request({
    url: `/website/resource/${id}`,
    method: 'get'
  })
}

// 修改资源
export function updateWebsite(data) {
  return request({
    url: `/website/resource`,
    method: 'put',
    data: data
  })
}

// 删除资源
export function delWebSite(id) {
  return request({
    url: `/website/resource/${id}`,
    method: 'delete'
  })
}
