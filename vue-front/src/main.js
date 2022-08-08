import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/style.less'
import './assets/font/iconfont.css'
import 'mavon-editor/dist/css/index.css'
require('./Mock')
import {parseTime} from './utils'
import hljs from 'highlight.js';
import 'highlight.js/styles/atom-one-dark.css'	//样式
import { getInformation } from "@/api/webInformation";
//Markdown
import mavonEditor from 'mavon-editor'

// 全局方法挂载
Vue.prototype.getInformation = getInformation

Vue.use(mavonEditor)

Vue.directive('highlight',function (el) {
  let blocks = el.querySelectorAll('pre code');
  blocks.forEach((block)=>{
    hljs.highlightBlock(block)
  })
})
Vue.config.productionTip = false


Vue.filter('parseTime', (v) => parseTime(v,'{y}-{m}-{d}'))
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
