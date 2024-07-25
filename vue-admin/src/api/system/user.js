import request from '@/utils/request'

// 查询列表
export function list(query) {
  return request({
    url: `/system/user`,
    method: 'get',
    params: query
  })
}

// 查询详细
export function get(id) {
  return request({
    url: `/system/user/${id}`,
    method: 'get'
  })
}

// 新增
export function add(data) {
  return request({
    url: `/system/user`,
    method: 'post',
    data: data
  })
}

// 修改
export function update(data) {
  return request({
    url: `/system/user`,
    method: 'put',
    data: data
  })
}

// 重置密码
export function restPassword(data) {
  return request({
    url: `/system/user/restPassword`,
    method: 'put',
    data: data
  })
}
//分配角色
export function allocationRole(data){
  return request({
    url: `/system/user/allocationRole`,
    method: 'put',
    data: data
  })
}
// 删除
export function del(id) {
  return request({
    url: `/system/user/${id}`,
    method: 'delete'
  })
}
