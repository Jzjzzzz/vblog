import request from '@/utils/request'

// 分页查询
export function list(query) {
  return request({
    url: '/itemBank',
    method: 'get',
    params: query
  })
}
// 新增
export function add(data) {
  return request({
    url: '/itemBank',
    method: 'post',
    data: data
  })
}
// 根据Id查询
export function getInfo(id) {
  return request({
    url: `/itemBank/${id}`,
    method: 'get'
  })
}

export function random() {
  return request({
    url: `/itemBank/random`,
    method: 'get'
  })
}

// 修改
export function update(data) {
  return request({
    url: '/itemBank',
    method: 'put',
    data: data
  })
}

// 删除
export function del(id) {
  return request({
    url: `/itemBank/${id}`,
    method: 'delete'
  })
}
