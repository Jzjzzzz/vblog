import request from '@/utils/request'

// 查询列表
export function list(query) {
  return request({
    url: `/system/role`,
    method: 'get',
    params: query
  })
}

// 角色列表
export function listAll(userId) {
  return request({
    url: `/system/role/listAll/${userId}`,
    method: 'get'
  })
}

// 查询详细
export function get(id) {
  return request({
    url: `/system/role/${id}`,
    method: 'get'
  })
}

// 新增
export function add(data) {
  return request({
    url: `/system/role`,
    method: 'post',
    data: data
  })
}

// 修改
export function update(data) {
  return request({
    url: `/system/role`,
    method: 'put',
    data: data
  })
}

// 删除
export function del(id) {
  return request({
    url: `/system/role/${id}`,
    method: 'delete'
  })
}
