import request from '@/utils/request'

// 分页查询
export function pageList(query) {
  return request({
    url: '/article/comment/pageList',
    method: 'get',
    params: query
  })
}

// 根据id获取评论详情
export function getInfo(id) {
  return request({
    url: `/article/comment/${id}`,
    method: 'get'
  })
}

// 回复
export function reply(data) {
  return request({
    url: `/article/comment/reply`,
    method: 'post',
    data: data
  })
}

// 修改回复
export function update(data) {
  return request({
    url: `/article/comment`,
    method: 'put',
    data: data
  })
}

// 删除评论
export function del(id) {
  return request({
    url: `/article/comment/${id}`,
    method: 'delete'
  })
}
