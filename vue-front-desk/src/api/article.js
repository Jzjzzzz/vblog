import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: `/front/article/listPage`,
    method: 'post',
    data: data
  })
}

export function getById(id) {
  return request({
    url: `/front/article/getById/${id}`,
    method: 'get'
  })
}

export function getHeadById(id){
  return request({
    url: `/front/article/getHeadById/${id}`,
    method: 'get'
  })
}

// 根据Id点赞文章
export function getByIdLike(id) {
  return request({
    url: `/front/article/getByIdLike/${id}`,
    method: 'get'
  })
}
