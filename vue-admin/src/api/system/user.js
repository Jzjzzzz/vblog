import request from '@/utils/request'

const api_name = '/system/user'

// 查询列表
export function list(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
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

// 重置密码
export function restPassword(data) {
  return request({
    url: `${api_name}/restPassword`,
    method: 'put',
    data: data
  })
}
//分配角色
export function allocationRole(data){
  return request({
    url: `${api_name}/allocationRole`,
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
