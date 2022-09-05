import Vue from 'vue'
import App from './App'
import router from './router'
import 'font-awesome/css/font-awesome.css'
import './main.css'// 可复用的CSS全局的CSS文件
import loading from '@/views/components/loading'

Vue.config.productionTip = false

/* 因为vue路由route-view的滚动条共享的原因(bug)，所以在这里如果滚动条置顶的话会交互不友好（明显的上滑动作），所以在这路由拦截 */
/* 引入了加载动作的实现，由于为了平衡掉动画带来的影响，所以在afterEach钩子里面写了延迟函数，为了一个流畅的过渡效果 */
/* 不喜欢这种过渡效果的可以在下面把两个钩子函数注释掉，那么遮罩就不会出现 */
router.beforeEach((to, from, next) => {
  loading.show();
  window.scrollTo(0, 0);
  // 这里由于route-view的bug，明显上滑动作不够友好，如果很不喜欢这种效果或者不喜欢单文件路由跳转，可以把路由跳转中所有的<route-link></route-link>取消换成a标签（不推荐）
  next();
})

router.afterEach((to, from, next) => {
  setTimeout(() => {
    loading.hide();
  }, 220);
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
