<template>
  <div>
    <div class="content-fixed-bar">
      <ul>
        <li>
          <a v-show="!isFullScreen"
          @click="toggleGuide($event)"
          @mouseenter="tooltipToggle($event)"
          @mouseleave="tooltipToggle($event)"
          data-toggle="tooltip"
          data-placement="right"
          title="文章导航">
            <img ref="guideImg" src="@/assets/image/icon/guide.png">
          </a>
        </li>
        <li>
          <a v-show="!isFullScreen && isShowReturnTop"
           @click="returnTop"
           @mouseenter="tooltipToggle($event)"
           @mouseleave="tooltipToggle($event)"
           data-toggle="tooltip"
           data-placement="right"
           title="返回顶部">
            <img src="@/assets/image/icon/top.svg">
          </a>
        </li>
        <li>
          <a v-show="!isPhone" @click="requestFullScreen" @mouseenter="tooltipToggle($event)" @mouseleave="tooltipToggle($event)" type="button" data-toggle="tooltip" data-placement="right" title="阅读模式">
            <img :src="!isFullScreen?readImage:closeFullImage">
          </a>
        </li>
      </ul>
    </div>
    <div ref="guide" :style="[!styleFlag?guidestyle:'',!stylePositionFlag?guidestyleTop:'']" class="content-guide shadow">
      <div class="content-guide-head">
        <a @click="toggleGuide($event)" class="head-close" href="javascript:void(0)">
          <i class="fa fa-close" aria-hidden="true"></i>
        </a>
        <a href="javascript:scroll(0,0)">目录</a>
      </div>
      <ul class="guide-list">
        <li :class="Hitem.class" v-for="Hitem in list">
          <a @click="toggleGuide($event)" :href="Hitem.href">{{Hitem.title}}</a>
        </li>
      </ul>
      <div class="guide-comment">
        <i class="fa fa-hand-o-right" aria-hidden="true"></i>
        <a href="#comment"> 查看评论</a>
      </div>
    </div>
    <div @click="toggleGuide($event)" v-show="styleFlag" class="guide-mask"></div>
  </div>
</template>

<script>
import {throttle, isPhone} from '@/utils/utils'

export default {
  name: 'detail-nav-bar',
  data () {
    return {
      isFullScreen: false,
      isPhone: false,
      isShowReturnTop: false,
      readImage: require('@/assets/image/icon/reading.svg'),
      closeFullImage: require('@/assets/image/icon/closeFull.svg'),
      tooltipFlag: false,
      styleFlag: false,
      stylePositionFlag: false,
      list: [],
      guidestyle: {
          left: '-405px'
      },
      guidestyleTop: {
          top: '-1500px'
      }
    }
  },
  mounted () {
    this.init();
  },
  beforeDestroy () {
    console.log('博文组件销毁，移除监听');
    document.removeEventListener('scroll', this.listenProcess);
  },
  methods: {
    init () {
      this.guide();
      this.fullScreenChange();
      document.addEventListener('scroll', this.listenProcess);
      this.isPhone = isPhone();
    },
    listenProcess (e) {
      throttle(this.returnTopListen(e), 100);
    },
    returnTopListen (e) {
      let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      if (scrollTop > 1400) {
        this.isShowReturnTop = true;
      } else {
        this.isShowReturnTop = false;
      }
    },
    tooltipToggle () {
      this.tooltipFlag = !this.tooltipFlag;
      if (this.tooltipFlag) {
          $(event.currentTarget).tooltip('show');
      }
    },
    guide () {
      var _self = this.list;
      $('.content-detail > h2,h3,h4').each(function (index, obj) {
          var flag = $(obj)[0].tagName.replace('H', '');
          var hobj = {
            title: $(this).text(),
            href: '#' + $(this).text(),
            class: 'guide-list-h' + flag
          }
          _self.push(hobj);
          $(this).attr('id', $(this).text());
      });
    },
    toggleGuide (event) {
        if (event.view.innerWidth < 768) {
            this.guidestyleTop.top = '-' + ($('.content-guide').height() + 50) + 'px';
        }
        this.styleFlag = !this.styleFlag;
        this.stylePositionFlag = !this.stylePositionFlag;
    },
    returnTop () {
      (function smoothscroll () {
          var currentScroll = document.documentElement.scrollTop || document.body.scrollTop;
          if (currentScroll > 1) {
               window.requestAnimationFrame(smoothscroll);
               window.scrollTo(0, currentScroll - (currentScroll / 8));
          }
      })();
    },
    requestFullScreen () {
      if (!this.isFullScreen) {
          let de = this.$parent.$refs['content-detail'];
          if (de.requestFullscreen) {
              de.requestFullscreen();
          } else if (de.mozRequestFullScreen) {
              de.mozRequestFullScreen();
          } else if (de.webkitRequestFullScreen) {
              de.webkitRequestFullScreen();
          }
      }
      if (this.isFullScreen) {
          let de = document;
          if (de.exitFullscreen) {
              de.exitFullscreen();
          } else if (de.mozCancelFullScreen) {
              de.mozCancelFullScreen();
          } else if (de.webkitCancelFullScreen) {
              de.webkitCancelFullScreen();
          }
      }
    },
    fullScreenChange () {
        var _self = this;
        document.addEventListener('fullscreenchange', function () { _self.isFullScreen = !_self.isFullScreen; });
        document.addEventListener('webkitfullscreenchange', function () { _self.isFullScreen = !_self.isFullScreen; });
        document.addEventListener('mozfullscreenchange', function () { _self.isFullScreen = !_self.isFullScreen; });
        document.addEventListener('MSFullscreenChange', function () { _self.isFullScreen = !_self.isFullScreen; });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content-fixed-bar{
  position: fixed;
  z-index: 99;
  bottom: 100px;
  left: 10px;
  bottom: 50px;
  padding: 0;
}
.content-fixed-bar ul{
  padding-left: 0px;
}
.content-fixed-bar > ul > li a{
  display: block;
  background-color: rgba(72,72,72,.75);
  border-radius: 2px;
  margin-bottom: 5px;
}
.content-fixed-bar > ul > li a:hover{
  cursor: pointer;
}
.content-fixed-bar img{
  width: 25px;
  height: 25px;
  margin: 8px;
}
.content-guide{
  z-index: 100000;
  background-color: #5d5d5d;
  position: fixed;
  padding: 10px;
  opacity: .99;
  overflow: auto;
  transition: all .6s;
  -moz-transition: all .6s;
  -webkit-transition: all .6s;
  -o-transition: all .6s;
}
.content-guide-head{
  text-align: center;
  font-size: 20px;
  font-weight: 500;
  border-bottom: 2px solid #2298d1;
  color: #fff;
  padding-bottom: 10px;
}
.content-guide-head>a{
  color:#fff;
}
.head-close{
  float: right;
  font-size: 28px;
}
.head-close i{
  display: block;
}
.content-guide>ul{
  padding: 0px;
}
.guide-list{
  margin: 10px 20px 10px 0;
}
.guide-list li{
  border-bottom: 1px solid #717171;
  margin-bottom: 18px;
}
.guide-list-h2{
	margin-left: 12px;
}
.guide-list-h3{
	margin-left: 24px;
}
.guide-list-h4{
	margin-left: 36px;
}
.guide-comment{
  font-size: 16px;
  color: #fff;
  margin-bottom: 10px;
}
.guide-comment a{
  padding: 10px;
}
.content-guide a{
  color: #e5e5e5;
}
.guide-mask{
  position: fixed;
  top: 0px;
  right: 0px;
  left: 0px;
  bottom: 0px;
}
.content-guide a:hover{
  color: #a9a9a9;
}
@media (min-width: 768px){
  .content-guide {
    height: 100%;
    top: 0!important;
    left: 0;
    width: 400px;
  }
  .content-fixed-bar{
    left: 36px;
  }
}
@media (max-width: 767px){
  .content-guide {
    width: 100%;
    height: 50%;
    overflow: auto;
    top: 0;
    left: 0!important;
  }
}

</style>
