import request from '@/utils/request'

const api_name = '/system/role'

// 查询列表
export function list(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
  })
}

// 角色列表
export function listAll(userId) {
  return request({
    url: `${api_name}/listAll/${userId}`,
    method: 'get'
  })
}

// 查询详细
export function get(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
  })
}

// 新增
export function add(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}

// 修改
export function update(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 删除
export function del(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'delete'
  })
}
