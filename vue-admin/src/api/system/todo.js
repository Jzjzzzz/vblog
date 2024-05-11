import request from '@/utils/request'

const api_name = '/system/todo'

// 列表
export function list() {
  return request({
    url: `${api_name}`,
    method: 'get'
  })
}

// 新增备忘
export function addTodo(content) {
  return request({
    url: `${api_name}/${content}`,
    method: 'post'
  })
}

// 修改备忘
// 根据id修改文章
export function updateById(data) {
  return request({
    url: `${api_name}`,
    method: 'put',
    data: data
  })
}

// 根据id删除
export function deleteById(id) {
  return request({
    url: `${api_name}/${id}`,
    method: 'delete'
  })
}
