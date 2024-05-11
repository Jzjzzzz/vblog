import request from '@/utils/request'

const api_name = '/system/dict'

// 查询字典数据列表
export function listData(query) {
  return request({
    url: `${api_name}/data/list`,
    method: 'get',
    params: query
  })
}

// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: `${api_name}/data/${dictCode}` ,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: `${api_name}/data/type/${dictType}`,
    method: 'get'
  })
}

// 新增字典数据
export function addData(data) {
  return request({
    url: `${api_name}/data`,
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData(data) {
  return request({
    url: `${api_name}/data`,
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData(dictCode) {
  return request({
    url: `${api_name}/data/${dictCode}`,
    method: 'delete'
  })
}
