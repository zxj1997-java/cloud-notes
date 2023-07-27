import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function childListNote(query) {
    return request({
        url: '/note/children',
        method: 'get',
        params: query
    })
}

export function parentListNote(query) {
    return request({
        url: '/note/parents',
        method: 'get',
        params: query
    })
}

// 查询【请填写功能名称】详细
export function getNote(id) {
    return request({
        url: '/note/' + id,
        method: 'get'
    })
}

// 新增【请填写功能名称】
export function addNote(data) {
    return request({
        url: '/note',
        method: 'post',
        data: data
    })
}

// 修改【请填写功能名称】
export function updateNote(data) {
    return request({
        url: '/note',
        method: 'put',
        data: data
    })
}

// 删除【请填写功能名称】
export function delNote(id) {
    return request({
        url: '/note/' + id,
        method: 'delete'
    })
}

export function uploadFile(data) {
    return request({
        url: '/note/uploadFile',
        method: 'post',
        data: data
    })
}


export function shareNoteApi(data) {
    return request({
        url: '/note/shareNote',
        method: 'post',
        data: data
    })
}


