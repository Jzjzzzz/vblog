import request from '@/utils/request'

const api_name = '/article/comment'

// 分页查询
export function pageList(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
  })
}

// 根据id获取评论详情
export function getInfo(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
  })
}

// 回复
export function reply(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}

// 修改回复
export function update(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 删除评论
export function del(ids) {
  return request({
    url: `${api_name}/${ids}`,
    method: 'delete'
  })
}

// 审核评论
export function audit(ids,type) {
  return request({
    url: `${api_name}/${ids}/${type}`,
    method: 'put'
  })
}
