import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '主页', icon: 'dashboard' }
    }]
  },
  {
    path: '/article',
    component: Layout,
    redirect: '/article/index',
    name: 'sys_article',
    meta: { title: '文章管理', icon: 'el-icon-collection' },
    alwaysShow: true,
    children: [
      {
        path: 'index',
        name: 'Article',
        component: () => import('@/views/article/index'),
        meta: { title: '文章列表', icon: 'el-icon-receiving' }
      },
      {
        path: 'summary',
        name: 'Summary',
        component: () => import('@/views/article/summary'),
        meta: { title: '文章归档', icon: 'el-icon-folder-opened' }
      },
      {
        path: 'add/',
        name: 'Add',
        component: () => import('@/views/article/add'),
        hidden: true,
        meta: { title: '新增文章', icon: 'el-icon-receiving' }
      },
      {
        path: 'update/:id',
        name: 'update',
        component: () => import('@/views/article/add'),
        hidden: true,
        meta: { title: '修改文章', icon: 'el-icon-receiving' }
      },
      {
        path: 'comment',
        name: 'Comment',
        component: () => import('@/views/article/comment'),
        meta: { title: '评论列表', icon: 'el-icon-edit-outline' }
      },
    ]
  },
  {
    path: '/website',
    component: Layout,
    redirect: '/website/index',
    name: 'sys_website',
    meta: { title: '资源管理', icon: 'el-icon-folder-opened' },
    alwaysShow: true,
    children: [
      {
        path: 'index',
        name: 'Website',
        component: () => import('@/views/website/index'),
        meta: { title: '资源列表', icon: 'el-icon-receiving' }
      }
    ]
  },
  {
    path: '/link',
    component: Layout,
    redirect: '/link/index',
    name: 'friend_link',
    meta: { title: '友链管理', icon: 'el-icon-position' },
    alwaysShow: true,
    children: [
      {
        path: 'index',
        name: 'Link',
        component: () => import('@/views/link/index'),
        meta: { title: '友链列表', icon: 'el-icon-receiving' }
      }
    ]
  },
  {
    path: '/gallery',
    component: Layout,
    redirect: '/gallery/index',
    name: 'gallery',
    meta: { title: '图片管理', icon: 'el-icon-collection-tag' },
    alwaysShow: true,
    children: [
      {
        path: 'index',
        name: 'GalleryList',
        component: () => import('@/views/gallery/index'),
        meta: { title: '图库', icon: 'el-icon-receiving' }
      }
    ]
  },
  {
    path: '/itembank',
    component: Layout,
    redirect: '/itembank/index',
    name: 'itembank',
    meta: { title: '题库', icon: 'el-icon-collection-tag' },
    alwaysShow: true,
    children: [
      {
        path: 'index',
        name: 'itembankList',
        component: () => import('@/views/itembank/index'),
        meta: { title: '题库', icon: 'el-icon-receiving' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/dict',
    name: 'System',
    meta: { title: '系统管理', icon: 'el-icon-setting' },
    alwaysShow: true,
    children: [
      {
        path: 'dict',
        name: 'Dict',
        component: () => import('@/views/system/dict/index'),
        meta: { title: '字典分类', icon: 'el-icon-receiving' }
      },
      {
        path: 'data/:id(\\d+)',
        name: 'Data',
        hidden: true,
        component: () => import('@/views/system/dict/data'),
        meta: { title: '字典数据', icon: 'el-icon-receiving' }
      },
      {
        path: 'config',
        name: 'Config',
        component: () => import('@/views/system/config/index'),
        meta: { title: '参数设置', icon: 'el-icon-s-tools' }
      },
      {
        path: 'job',
        name: 'Job',
        component: () => import('@/views/system/job/index'),
        meta: { title: '定时任务', icon: 'el-icon-alarm-clock' }
      },
      {
        path: 'information',
        name: 'Information',
        component: () => import('@/views/system/information/index'),
        meta: { title: '网站信息', icon: 'el-icon-postcard' }
      },
      {
        path: 'notice',
        name: 'Notice',
        component: () => import('@/views/system/notice/index'),
        meta: { title: '公告管理', icon: 'el-icon-s-comment' }
      },
      {
        path: 'operlog',
        name: 'Operlog',
        component: () => import('@/views/monitor/operlog/index'),
        meta: { title: '日志管理', icon: 'el-icon-s-order' }
      }
    ]
  },
  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'http://jzjzzzz.cn/',
        meta: { title: '博客首页', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
