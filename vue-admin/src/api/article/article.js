import request from '@/utils/request'

// 查询文章基本信息列表
export function listArticleInform(query) {
  return request({
    url: '/article/inform/list',
    method: 'get',
    params: query
  })
}
