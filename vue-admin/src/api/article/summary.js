import request from '@/utils/request'


// 查询归档列表
export function listArticleSummary(query) {
  return request({
    url: '/article/summary/list',
    method: 'get',
    params: query
  })
}
// 新增归档
export function add(data) {
  return request({
    url: '/article/summary/add',
    method: 'post',
    data: data
  })
}

// 删除归档
export function del(id) {
  return request({
    url: '/article/summary/' + id,
    method: 'delete'
  })
}
