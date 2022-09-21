import request from '@/utils/request'

// 查询文章基本信息列表
export function listArticleInform(query) {
  return request({
    url: '/article/inform',
    method: 'get',
    params: query
  })
}
// 新增文章
export function add(data) {
  return request({
    url: '/article/inform',
    method: 'post',
    data: data
  })
}
// 根据id查询文章
export function getById(id) {
  return request({
    url: `/article/inform/${id}`,
    method: 'get'
  })
}
// 根据id修改文章
export function updateById(data) {
  return request({
    url: `/article/inform`,
    method: 'put',
    data: data
  })
}

// 根据id批量删除文章
export function deleteBthById(ids) {
  return request({
    url: `/article/inform/${ids}`,
    method: 'delete'
  })
}

// 后台文章排行榜数据
export function getRankList() {
  return request({
    url: `/article/inform/getRank`,
    method: 'get'
  })
}
