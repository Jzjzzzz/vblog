import request from '@/utils/request'

export function linkList() {
  return request({
    url: `/front/link/list`,
    method: 'get'
  })
}
