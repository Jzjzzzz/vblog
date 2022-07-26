import request from '@/utils/request'

// 上传图片
export function pictureUpload(query) {
  return request({
    url: '/api/upload/uploadImg',
    method: 'get',
    params: query
  })
}
