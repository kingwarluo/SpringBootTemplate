/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const menuRouter = {
  path: '/menu',
  component: Layout,
  redirect: '/menu/list',
  name: 'menuMain',
  meta: {
    title: 'menu main',
    icon: 'nested'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/menu/list'),
      name: 'menuList',
      meta: { title: 'menu list' }
    },
    {
      path: 'edit',
      component: () => import('@/views/menu/edit'),
      name: 'menuEdit',
      meta: { title: 'menu edit' }
    }
  ]
}

export default menuRouter
