import request from '@/utils/request'

// 查询列表
export function list(query) {
  return request({
    url: `/friend/link`,
    method: 'get',
    params: query
  })
}
// 新增
export function add(data) {
  return request({
    url: `/friend/link`,
    method: 'post',
    data: data
  })
}
// 根据Id查询
export function getInfo(id) {
  return request({
    url: `/friend/link/${id}`,
    method: 'get'
  })
}

// 修改
export function update(data) {
  return request({
    url: `/friend/link`,
    method: 'put',
    data: data
  })
}

// 删除
export function del(id) {
  return request({
    url: `/friend/link/${id}`,
    method: 'delete'
  })
}
