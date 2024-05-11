import request from '@/utils/request'

const api_name = '/api/upload'

// 删除图片
export function deleteImg(url) {
  return request({
    url: `${api_name}/deleteImg?url=`+url ,
    method: 'delete'
  })
}

// 上传图片
export function uploadImg(file,name) {
  return request({
    url: `${api_name}/uploadImg?name=`+name ,
    method: 'post',
    data: file
  })
}
