import request from '@/utils/request'

const api_name = '/system/menu'

// 查询列表
export function list(query) {
  return request({
    url: `${api_name}/findNodes`,
    method: 'get',
    params: query
  })
}

export function findNodes(){
  return request({
    url: `${api_name}/findNodes`,
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
// 查看某个角色的权限列表
export function toAssign(roleId) {
  return request({
    url: `${api_name}/toAssign/${roleId}`,
    method: 'get'
  })
}

// 给某个角色授权
export function doAssign(assignMenuVo) {
  return request({
    url: `${api_name}/doAssign`,
    method: "post",
    data: assignMenuVo
  })
}
