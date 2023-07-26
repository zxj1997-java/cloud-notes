import request from '@/utils/request'

// 查询【请填写功能名称】详细
export function getNoteFile(id) {
    return request({
        url: '/notefile/' + id,
        method: 'get'
    })
}

// 新增【请填写功能名称】
export function saveNoteFile(data) {
    return request({
        url: '/notefile',
        method: 'post',
        data: data
    })
}

// 删除【请填写功能名称】
export function delNote(id) {
    return request({
        url: '/notefile/' + id,
        method: 'delete'
    })
}

// 删除【请填写功能名称】
export function uploadFile(data) {
    return request({
        url: '/notefile/upload',
        method: 'post',
        data: data
    })
}

export function downloadFile(id) {
    return request({
        url: '/notefile/image/' + id,
        method: 'get',
    })
}


export function fullSearch(keyword) {
    return request({
        url: '/notefile/search',
        method: 'post',
        data:keyword
    })
}



