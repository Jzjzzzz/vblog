import request from '@/utils/request'

// 删除图片
export function deleteImg(url) {
  return request({
    url: `/api/upload/deleteImg?url=`+url ,
    method: 'delete'
  })
}

// 上传图片
export function uploadImg(file,name) {
  return request({
    url: `/api/upload/uploadImg?name=`+name ,
    method: 'post',
    data: file
  })
}
