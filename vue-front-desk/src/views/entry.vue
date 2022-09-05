<template>
  <div :style="viewBackstyle" :class="currentRoute.isOpacityFull?'opacity-full':''" class="view" ref="view">
    <div v-if="isShowNavMain" class="main-nav">
        <ul>
          <li v-for="nav in navList">
            <router-link :to="nav.router" replace>{{nav.name}}</router-link>
          </li>
        </ul>
    </div>
    <div class="introduce-title">
        <h1>{{currentRoute.title}}</h1>
        <p class="subline">{{currentRoute.desc}}</p>
        <div :class="currentRoute.titleClass" class="title-mini">
          <h1>{{currentRoute.title}}</h1>
          <p class="subline-mini">
            {{currentRoute.desc}}
          </p>
        </div>

    </div>
    <router-view/>
  </div>
</template>

<script>
import config from '@/config/blog-config.json'

export default {
  name: 'entryPage',
  data () {
    return {
      // 背景的opacity是否为1
      isOpacityFull: false,
      viewBackstyle: {
        backgroundImage: ''
      },
      currentRoute: null,
      navList: [],
      // 主页面入口中各个页面的详细信息
      textMessage: [],
      pageTitle: '',
      pageDesc: '',
      isShowNavMain: true
    }
  },
  created () {
    this.getConfig();
    this.currentRoute = this.getCurrentRoute();
    this.init();
  },
  watch: {
    $route (to, from) {
      this.routerChange(from, to);
      this.getTitleDetails();
    }
  },
  methods: {
    // 获取config的各个数据配置
    getConfig () {
      this.textMessage = config.page.entryPage;
      this.navList = config.nav.mainNav;
    },
    init () {
      this.getTitleDetails();
      this.routerChange(null, this.$route);
    },
    getCurrentRoute () {
      const routeName = this.$route.name;
      let current = this.textMessage.find(item => {
        return item.name === routeName;
      });
      return current;
    },
    // 获取新路由的对应页面的相关信息，包括页面标题，页面描述，页面背景图和页面的透明度样式
    getTitleDetails () {
      let current = this.getCurrentRoute();
      this.currentRoute = current;
      this.viewBackstyle.backgroundImage = `url(${require('../' + current.backgroundImage)})`;
      console.log(this.viewBackstyle);
    },
    routerChange (oldRoute, newRoute) {
      newRoute.name != 'index' ? (this.isShowNavMain = false) : (this.isShowNavMain = true)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-nav{
  position: absolute;
  top: 70%;
  width: 100%;
  text-align: center;
  z-index: 9;
}
.main-nav ul{
  padding: 0px;
}
.main-nav li{
  display: inline-block;
  margin: 0 15px;
}
.main-nav li a{
  color: #fff;
  border-bottom: 1px solid #fff;
  padding-bottom: 5px;
  font-weight: 300;
  font-style: initial;
  font-size: 16px;
  transition: all .4s;
  -webkit-transition: all .4s;
}
.introduce-title{
  position: absolute;
  text-align: center;
  margin: 0 auto;
  width: 100%;
  height: 100%;
  color: #fff;
  z-index: 1;
  transform: translate3d(0,20%,0);
  -webkit-transform: translate3d(0,20%,0);
  -moz-transform: translate3d(0,20%,0);
}
.slide .title-blog{
	transform: translate3d(0,64%,0);
  -webkit-transform: translate3d(0,64%,0);
  -moz-transform: translate3d(0,64%,0);
	-ms-transform: translate3d(0,64%,0);
}
.slide .title-contact{
	transform: translate3d(0,54%,0);
  -webkit-transform: translate3d(0,54%,0);
  -moz-transform: translate3d(0,54%,0);
	-ms-transform: translate3d(0,54%,0);
}
.title-mini{
  opacity: 0;
  position: absolute;
  bottom: 0px;
  top: 0px;
  right: 0px;
  left: 0px;
}
.title-mini h1{
  font-weight: 400;
  font-size: 32px;
}
.title-contact h1,
.title-contact p{
  color: #555;
}
.title-blog h1,
.title-blog p{
  color: #fff;
}
.slide .introduce-title >p,
.slide .introduce-title >h1{
  opacity: 0;
}
.slide .title-mini{
  opacity: 1;
}
.introduce-title .title-mini{
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
}
.slide .opacity-full::after{
  opacity: 1;
}
.slide .main-nav ul li a{
  color: #444;
  border-bottom: 1px solid #222;
  font-size: 20px;
  font-weight: 300;
}
</style>
