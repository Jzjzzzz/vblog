import request from '@/utils/request'

export function fetchList(data) {
    return request({
        url: `/front/article/summaryPage`,
        method: 'post',
        data: data
    })
}

export function summaryTop() {
    return request({
        url: `/front/article/summaryTop`,
        method: 'get'
    })
}