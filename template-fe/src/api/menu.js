import request from '@/utils/request'

export function list(query) {
  return request({
    url: '/menu/list',
    method: 'get',
    params: { query }
  })
}

export function getById(id) {
  return request({
    url: '/menu/detail',
    method: 'get',
    params: { id }
  })
}
