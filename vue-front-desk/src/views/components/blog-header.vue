<template>
  <nav :class="isUp?'':'nav-hide'" class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-bar" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
        </button>
        <router-link class="navbar-brand" to="/"><img src="../../assets/image/favicon.png"></router-link></div>
      <div class="navbar-collapse collapse" id="nav-bar" aria-expanded="false" style="height:1px;">
      <ul  class="nav navbar-nav">
          <li v-for="item in navList" @mouseenter="navHover($event)" @mouseleave="navHoverLeft($event)">
              <router-link class="nav-router-link" :to="item.href">
                <img class="navIcon" :src="item.icon"> {{item.name}}
              </router-link>
          </li>
          <!-- 这是导航下标的滚动条，喜欢的可以开起来，js在methods对象里面 -->
          <!-- <span :style="lineStyle" class="slide-nav-bar"></span> -->
      </ul>
      <ul @mouseleave="userHoverLeave" class="nav navbar-nav nav-right">
          <li>
          <a @mouseenter="userHover">
          </a>
          <transition name="user">
            <ul v-if="userInfo" v-show="userflag" class="userInfo">
                <li>
                  <a href="/article/logout">退出登录</a>
                </li>
            </ul>
          </transition>
          <li v-if="userInfo" class="logout"><a href="/article/logout">退出登录</a>
          </li>
          </ul>
          </li>
      </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import {throttle} from '@/utils/utils'

export default {
  props: {
    navList: {
      type: Array,
      require: true
    }
  },
  name: 'nav-header',
  data () {
    return {
      headCoverSilde: false,
      userflag: false,
      isUp: false,
      userInfo: false,
      scrollCount: null,
      lineStyle: {
        left: '0px',
        opacity: 0,
        width: '68px',
        top: '56px'
      }
    }
  },
  mounted () {
    this.init();
    window.addEventListener('scroll', throttle(this.handleScroll, 10));
  },
  watch: {
    headCoverSilde: function () {
      this.$emit('slidechange', this.headCoverSilde);
    }
  },
  methods: {
    init: function () {
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      if (scrollTop >= 1) {
        this.headCoverSilde = true;
      } else {
        this.headCoverSilde = false;
      }
    },
    handleScroll: function (e) {
      e = e || window.e;
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;

      if (scrollTop >= 1) {
        this.headCoverSilde = true;
        if (scrollTop - this.scrollCount > 0) {
          this.isUp = false;
          $('#nav-bar').collapse('hide');
        } else {
          this.isUp = true;
        }
        this.scrollCount = scrollTop;
      } else {
        this.headCoverSilde = false;
      }
    },
    /* 这是导航下标的滚动条的2个方法，喜欢的可以开起来具体的js逻辑 */
    navHover: function (ele) {
      // this.lineStyle.width = $(ele.target).width() + 'px';
      // this.lineStyle.left = $(ele.target).position().left + 'px';
      // this.lineStyle.opacity = 1;
    },
    navHoverLeft: function (ele) {
      // this.lineStyle.opacity = 0;
      // this.lineStyle.width = '68px';
    },
    userHover: function () {
      this.userflag = true;
    },
    userHoverLeave: function () {
      this.userflag = false;
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
li, ul{
  list-style:none;
}
.navbar{
  min-height: 60px;
}
.navbar, .navbar-inverse{
  border-radius: 0;
  background: 0 0;
  border: none;
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
  position: fixed;
  background-color: #fff!important;
  width: 100%;
  z-index: 9999;
  box-shadow: 0 1px 5px rgba(0,0,0,.1);
  -webkit-box-shadow: 0 1px 5px rgba(0,0,0,.1);
  -moz-box-shadow: 0 1px 5px rgba(0,0,0,.1);
  opacity: 0;
}
.nav-router-link{
  opacity: .75;
  /* font-family: -apple-system,BlinkMacSystemFont,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif */
}
.slide .navbar{
  opacity: 1;
}
.navbar-nav{
  float: left;
  position: relative;
}
.navbar-brand > img {
  height: 100%;
  width: auto;
}
.navIcon{
  width: 20px;
  height: 20px;
}
.navbar-brand{
  margin: 0 30px;
  height: 60px;
}
.navbar-inverse .navbar-collapse,
.navbar-inverse .navbar-form{
  border-color: #eee;
}
.navbar-inverse .navbar-toggle .icon-bar{
  background-color: #222;
}
.navbar-inverse .navbar-toggle:focus,
.navbar-inverse .navbar-toggle:hover{
  background-color: #fafafa;
}
.navbar-inverse .navbar-nav>li>a{
  color: #555;
}
.navbar-inverse .navbar-nav>li>a:focus,
.navbar-inverse .navbar-nav>li>a:hover,
.navbar-inverse .navbar-nav>li>a:active{
  color: #222!important;
  opacity: 1;
  font-weight: 500;
}
.slide-nav-bar{
  position: absolute;
  left: 0;
  top: 0;
  width: 0;
  height: 5px;
  transform: scale(.5,.5);
  -webkit-transform: scale(.5,.5);
  -moz-transform: scale(.5,.5);
  background-color: #222;
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
}
@media (min-width: 768px){
  .navbar-header>a, .navbar-nav>li>a {
    line-height: 60px;
    display: block;
    color: #555!important;
    transition: all .4s ease;
    -webkit-transition: all .4s ease;
    -moz-transition: all .4s ease;
    padding: 0 20px;
  }
  .navbar-nav > li {
    line-height: 60px;
    position: relative;
  }
  .navbar-brand > img {
    padding: 15px;
  }
  .navbar-header>a, .navbar-nav>li>a{
    line-height: 60px;
    display: block;
    color: #555!important;
    transition: all .4s ease;
    -webkit-transition: all .4s ease;
    -moz-transition: all .4s ease;
    padding: 0 20px;
  }
}
</style>
