import Vue from 'vue'
import Router from 'vue-router'
import entry from '@/views/entry'
import detail from '@/views/detail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'entry',
      component: entry,
      children: [
        {
          path: '',
          component: (resolve) => require(['@/views/cores/index'], resolve),
          name: 'index'
        },
        {
          path: 'contact',
          component: (resolve) => require(['@/views/cores/contact'], resolve),
          name: 'contact'
        },
        {
          path: 'blog',
          component: (resolve) => require(['@/views/cores/blog'], resolve),
          name: 'blog'
        },
        {
          path: 'life',
          component: (resolve) => require(['@/views/cores/life'], resolve),
          name: 'life'
        }
      ]
    }, {
      path: '/detail',
      name: 'detail',
      component: detail,
      children: [
        {
          path: ':articleId',
          component: (resolve) => require(['@/views/cores/blog-detail'], resolve),
          name: 'blog-detail'
        },
        {
          path: 'life/:lifeId',
          component: (resolve) => require(['@/views/cores/life-detail'], resolve),
          name: 'life-detail'
        }
      ]
    },
    {
      path: '*',
      redirect: '/'
    }
  ],
  saveScrollPosition: true,
  transitionOnload: true
})
