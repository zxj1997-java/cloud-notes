import request from '@/utils/request'

// 查询扫码登录列表
export function listToken(query) {
  return request({
    url: '/system/token/list',
    method: 'get',
    params: query,
    headers: {
      isToken: false
    },
  })
}

// 查询扫码登录详细
export function getToken(id) {
  return request({
    url: '/system/token/' + id,
    method: 'get',
    headers: {
      isToken: false
    },
  })
}

// 新增扫码登录
export function addToken(data) {
  return request({
    url: '/system/token',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    }
  })
}

// 修改扫码登录
export function updateToken(data) {
  return request({
    url: '/system/token',
    method: 'put',
    data: data
  })
}

// 删除扫码登录
export function delToken(id) {
  return request({
    url: '/system/token/' + id,
    method: 'delete'
  })
}
