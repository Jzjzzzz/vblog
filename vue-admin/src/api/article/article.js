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
