import request from '@/utils/request'

export function saveMessage(data) {
  return request({
    url: `/front/comment/insert/message`,
    method: 'post',
    data: data
  })
}

export function getMessageList(pageNumber) {
  return request({
    url: `/front/comment/getList/message/${pageNumber}`,
    method: 'get'
  })
}

export function getListArticle(articleId) {
  return request({
    url: `/front/comment/getList/article/${articleId}`,
    method: 'get'
  })
}
