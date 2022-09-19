import request from '@/utils/request'

export function list() {
  return request({
    url: `/front/summary/list`,
    method: 'get'
  })
}
