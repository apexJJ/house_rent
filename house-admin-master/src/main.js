import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import store from './store'
import '@/style/index.scss' // glob scss
import './plugins/element.js'
import animated from 'animate.css'
import '@/assets/iconfont/iconfont.css'
import axios from 'axios'
import ElementUI from 'element-ui'
// 引入vue-amap
import AMap from 'vue-amap'

Vue.use(ElementUI)
Vue.use(animated)
Vue.use(AMap)

// 初始化vue-amap
AMap.initAMapApiLoader({
  // 申请的高德key
  key: '4270a8f5c907e72116dea86e17e16939',
  // 插件集合
  plugin: [
    'AMap.Autocomplete',
    'AMap.PlaceSearch',
    'AMap.Scale',
    'AMap.OverView',
    'AMap.ToolBar',
    'AMap.MapType',
    'AMap.PolyEditor',
    'AMap.CircleEditor'
  ]
})

//定义全局变量
// Vue.use(SlideVerify)
Vue.config.productionTip = false
Vue.prototype.axios = axios
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
