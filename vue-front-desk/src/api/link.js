import request from '@/utils/request'

export function linkList() {
  return request({
    url: `/friend/link/list`,
    method: 'get'
  })
}
