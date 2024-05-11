import request from '@/utils/request'

const api_name = '/friend/link'

// 查询列表
export function list(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
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
// 根据Id查询
export function getInfo(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
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
