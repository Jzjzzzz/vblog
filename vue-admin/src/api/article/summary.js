import request from '@/utils/request'

const api_name = '/article/summary'

// 查询归档列表
export function listArticleSummary(query) {
  return request({
    url: `${api_name}`,
    method: 'get',
    params: query
  })
}

// 查询所有归档列表
export function listAll() {
  return request({
    url: `${api_name}/listAll`,
    method: 'get'
  })
}

// 新增归档
export function add(data) {
  return request({
    url: `${api_name}`,
    method: 'post',
    data: data
  })
}

// 删除归档
export function del(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'delete'
  })
}
// 根据Id查询归档详细
export function getInfo(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'get'
  })
}

// 修改归档
export function update(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 根据归档id查询文章列表
export function articleList(id) {
  return request({
    url: `${api_name}/articleList/${id}`,
    method: 'get'
  })
}
