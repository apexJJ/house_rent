import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'

Vue.use(Router)

import Layout from '@/layout'
import { Message } from 'element-ui'
import getTitle from '@/utils/getTitle'

/**
 * 路由相关属性说明
 * hidden: 当设置hidden为true时，意思不在sideBars侧边栏中显示
 * mete{
 * title: xxx,  设置sideBars侧边栏名称
 * icon: xxx,  设置ideBars侧边栏图标
 * noCache: true  当设置为true时不缓存该路由页面
 * }
 */

/*通用routers*/
export const currencyRoutes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login'),
    meta: { title: '登录页' },
    hidden: true
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/error-page/404.vue'),
    hidden: true
  },
  {
    path: '/house',
    name: 'House',
    meta: { title: '租房网' },
    component: () => import('@/views/houseShow'),
    hidden: true
  },
  {
    path: '/allhouse',
    name: 'AllHouse',
    meta: { title: '所有租房' },
    component: () => import('@/views/houseShow/common/allHouse.vue'),
    hidden: true
  },
  {
    path: '/houseDetail/:id/',
    name: 'HouseDetail',
    meta: { title: '房屋详情' },
    component: () => import('@/views/houseShow/common/houseDetail.vue'),
    hidden: true
  },
  {
    path: '/mapSearch',
    name: 'MapSearch',
    meta: { title: '地图搜房' },
    component: () => import('@/views/houseShow/common/mapSearch.vue'),
    hidden: true
  },
  {
    path: '/',
    name: 'Home',
    component: Layout,
    redirect: '/dashbord',
    children: [
      {
        path: 'dashbord',
        name: 'Dashbord',
        component: () => import('@/views/dashboard'),
        meta: { title: '销售信息', icon: 'el-icon-s-data' }
      }
    ]
  }
]
/*动态添加routers*/
export const asyncRoutes = [
  {
    path: '/manager',
    name: 'Table',
    redirect: '/table/table',
    component: Layout,
    meta: {
      title: '租房管理',
      icon: 'el-icon-s-home'
    },
    children: [
      {
        path: 'houseManager',
        name: 'ComplexTable',
        component: () => import('@/views/table/table'),
        meta: { title: '房源信息管理' }
      }
    ]
  }
]
const creatRouter = () => {
  return new Router({
    routes: currencyRoutes,
    scrollBehavior() {
      return { x: 0, y: 0 }
    }
  })
}

const router = creatRouter()

// 解决addRoute不能删除动态路由问题
export function resetRouter() {
  const reset = creatRouter()
  router.matcher = reset.matcher
}

// 导航守卫
router.beforeEach(async (to, from, next) => {
  document.title = getTitle(to.meta.title)
  if (to.path === '/login') {
    next()
  } else {
    if (store.getters.token) {
      const hasRoles = store.getters.roles.length > 0
      if (hasRoles) {
        next()
      } else {
        try {
          const { roles } = await store.dispatch('user/_getInfo')
          const addRoutes = await store.dispatch(
            'permission/getAsyncRoutes',
            roles
          )
          router.addRoutes(addRoutes)

          // hack method to ensure that addRoutes is complete
          // set the replace: true, so the navigation will not leave a history record
          next({ ...to, replace: true })
        } catch (error) {
          Message.error(error)
        }
      }
    } else {
      next({
        path: '/login',
        query: {
          redirect: to.fullPath
        }
      })
    }
  }
})
export default router
