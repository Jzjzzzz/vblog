import request from '@/utils/request'


// 查询归档列表
export function listArticleSummary(query) {
  return request({
    url: '/article/summary',
    method: 'get',
    params: query
  })
}

// 查询所有归档列表
export function listAll() {
  return request({
    url: '/article/summary/listAll',
    method: 'get'
  })
}

// 新增归档
export function add(data) {
  return request({
    url: '/article/summary',
    method: 'post',
    data: data
  })
}

// 删除归档
export function del(id) {
  return request({
    url: `/article/summary/${id}`,
    method: 'delete'
  })
}
// 根据Id查询归档详细
export function getInfo(id) {
  return request({
    url: `/article/summary/${id}`,
    method: 'get'
  })
}

// 修改归档
export function update(data) {
  return request({
    url: '/article/summary/',
    method: 'put',
    data: data
  })
}

// 根据归档id查询文章列表
export function articleList(id) {
  return request({
    url: `/article/summary/articleList/${id}`,
    method: 'get'
  })
}
