import request from '@/utils/request'

// 列表
export function list() {
  return request({
    url: `/system/todo`,
    method: 'get'
  })
}

// 新增备忘
export function addTodo(content) {
  return request({
    url: `/system/todo/${content}`,
    method: 'post'
  })
}

// 修改备忘
// 根据id修改文章
export function updateById(data) {
  return request({
    url: `/system/todo`,
    method: 'put',
    data: data
  })
}

// 根据id删除
export function deleteById(id) {
  return request({
    url: `/system/todo/${id}`,
    method: 'delete'
  })
}
