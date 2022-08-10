import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/style.less'
import './assets/font/iconfont.css'
import 'mavon-editor/dist/css/index.css'
import 'element-ui/lib/theme-chalk/index.css'
require('./Mock')
import {parseTime} from './utils'

import { getInformation } from "@/api/webInformation"
import { getDicts } from "@/api/system/dict/data";
import {selectDictLabel} from "@/utils/vblog";
//Markdown
import mavonEditor from 'mavon-editor'
// 字典数据组件
import DictData from '@/components/DictData'
// 字典标签组件
import DictTag from '@/components/DictTag'
//Element-UI
import ElementUI from 'element-ui';

// 全局方法挂载
Vue.prototype.getInformation = getInformation
Vue.prototype.getDicts = getDicts
Vue.prototype.selectDictLabel = selectDictLabel
Vue.component('DictTag', DictTag)

Vue.use(mavonEditor)
Vue.use(ElementUI);
DictData.install()


Vue.config.productionTip = false


Vue.filter('parseTime', (v) => parseTime(v,'{y}-{m}-{d}'))
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
