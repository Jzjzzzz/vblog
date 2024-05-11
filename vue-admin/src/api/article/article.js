import request from '@/utils/request'

const api_name = '/article/inform'
// 查询文章基本信息列表
export function listArticleInform(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
  })
}
// 新增文章
export function add(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}
// 根据id查询文章
export function getById(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
  })
}
// 根据id修改文章
export function updateById(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 根据id批量删除文章
export function deleteBthById(ids) {
  return request({
    url: `${api_name}/${ids}`,
    method: 'delete'
  })
}

// 后台文章排行榜数据
export function getRankList() {
  return request({
    url: `${api_name}/getRank`,
    method: 'get'
  })
}

// 根据id修改文章归档
export function updateSummaryById(id,summaryId,type) {
  return request({
    url: `${api_name}/updateSummaryById/${id}/${summaryId}/${type}`,
    method: 'put'
  })
}
