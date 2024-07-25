import request from '@/utils/request'

// 查询网站基本信息
export function getInformation() {
  return request({
    url: `/system/web/information/getInfo`,
    method: 'get'
  })
}

// 修改网站基本信息
export function updateInformation(data) {
  return request({
    url: `/system/web/information`,
    method: 'put',
    data: data
  })
}

// 刷新参数缓存
export function refreshCache() {
  return request({
    url: `/system/web/information/refreshCache`,
    method: 'delete'
  })
}
