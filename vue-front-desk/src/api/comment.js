import request from '@/utils/request'

export function saveMessage(data) {
  return request({
    url: `/front/comment/insert/message`,
    method: 'post',
    data: data
  })
}
