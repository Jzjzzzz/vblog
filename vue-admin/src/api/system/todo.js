import request from "@/utils/request";

//列表
export function list() {
  return request({
    url: `/sys/todo/list`,
    method: 'get'
  })
}

//新增备忘
export function addTodo(content) {
  return request({
    url: `/sys/todo/add/${content}`,
    method: 'post'
  })
}

//修改备忘
// 根据id修改文章
export function updateById(data) {
  return request({
    url: `/sys/todo`,
    method: 'put',
    data: data
  })
}

// 根据id删除
export function deleteById(id) {
  return request({
    url: `/sys/todo/${id}`,
    method: 'delete'
  })
}
