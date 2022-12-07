import request from '@/utils/request'

// 查询列表
export function list(query) {
  return request({
    url: '/gallery',
    method: 'get',
    params: query
  })
}

// 新增
export function add(data) {
  return request({
    url: '/gallery',
    method: 'post',
    data: data
  })
}

// 删除
export function del(id) {
  return request({
    url: `/gallery/${id}`,
    method: 'delete'
  })
}
