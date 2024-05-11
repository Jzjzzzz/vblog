import request from '@/utils/request'

const api_name = '/system/config'

// 查询参数列表
export function listConfig(query) {
  return request({
    url: `${api_name}/list`,
    method: 'get',
    params: query
  })
}

// 查询参数详细
export function getConfig(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
  })
}

// 根据参数键名查询参数值
export function getConfigKey(configKey) {
  return request({
    url: `${api_name}/configKey/${configKey}`,
    method: 'get'
  })
}

// 新增参数配置
export function addConfig(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}

// 修改参数配置
export function updateConfig(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 删除参数配置
export function delConfig(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'delete'
  })
}

// 刷新参数缓存
export function refreshCache() {
  return request({
    url: `${api_name}/refreshCache`,
    method: 'delete'
  })
}
