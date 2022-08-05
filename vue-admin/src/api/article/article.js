import request from '@/utils/request'

// 查询文章基本信息列表
export function listArticleInform(query) {
  return request({
    url: '/article/inform/list',
    method: 'get',
    params: query
  })
}
// 新增文章
export function add(data) {
  return request({
    url: '/article/inform/add',
    method: 'post',
    data: data
  })
}
// 根据id查询文章
export function getById(id) {
  return request({
    url: `/article/inform/getById/${id}`,
    method: 'get'
  })
}
// 根据id修改文章
export function updateById(data) {
  return request({
    url: `/article/inform/updateById`,
    method: 'put',
    data: data
  })
}

// 根据id批量删除文章
export function deleteBthById(ids) {
  return request({
    url: `/article/inform/deleteById/${ids}`,
    method: 'delete'
  })
}

