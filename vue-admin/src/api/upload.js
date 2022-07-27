import request from '@/utils/request'

// 删除图片
export function deleteImg(url) {
  return request({
    url: `/api/upload/deleteImg?url=`+url ,
    method: 'delete'
  })
}
