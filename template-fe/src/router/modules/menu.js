/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const menuRouter = {
  path: '/menu',
  component: Layout,
  redirect: '/menu/list',
  name: 'menuMain',
  meta: {
    title: '菜单',
    icon: 'nested'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/menu/list'),
      name: 'menuList',
      meta: { title: '菜单管理' }
    },
    {
      path: 'edit',
      component: () => import('@/views/menu/edit'),
      name: 'menuEdit',
      meta: { title: '编辑菜单' },
      hidden: true
    }
  ]
}

export default menuRouter
