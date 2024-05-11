import request from '@/utils/request'

const api_name = '/gallery'

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

// 删除
export function del(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'delete'
  })
}
