import request from '@/utils/request'

export function fetchList(page,limit) {
    return request({
        url: `/front/article/listPage/${page}/${limit}`,
        method: 'get'
    })
}

export function getById(id) {
    return request({
        url: `/article/inform/getById/${id}`,
        method: 'get'
    })
}