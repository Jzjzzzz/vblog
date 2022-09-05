<template>
  <div id="view-body" class="view-body view-body-main">
    <div style="text-align:center" class="container">
      <header style="padding-top:6px;" class="codrops-header">
        <h1>{{title}}
          <span>
            <br />
            <p class="header-desc-p" v-for="desc in descList">
              {{desc}}
            </p>
            <br />
          </span>
          <span style="font-size:14px;font-weight:500;margin-top:40px;">
            —— 欢迎你！第 <font style="font-size:34px;">{{count}}</font> 位访问的游客 ——
          </span>
        </h1>
      </header>
    <div class="grid">
      <figure v-for="figure in figureList" :class="figure.style">
          <img :src="figure.image" alt="图片在此"/>
          <figcaption>
              <h2 v-html="figure.title"></h2>
              <p v-html="figure.desc"></p>
              <a :href="figure.router">View more</a>
          </figcaption>
      </figure>
    </div>
  </div>
  <blog-foot></blog-foot>
</div>
</template>

<script>
import blogFoot from '@/views/components/blog-foot'

export default {
  name: 'index',
  components: {
    blogFoot: blogFoot
  },
  data () {
    return {
      count: 500,
      figureList: [{
        title: '留言墙',
        desc: '留 下 你 的 足 迹。',
        image: require('../../assets/image/1.jpg'),
        router: '/contact#leave_message',
        style: 'effect-lily'
      }, {
        title: '技术 <span>杂谈</span>',
        desc: '在 技 术 上，<br>时 刻 保 持 谦 虚。',
        image: require('../../assets/image/2.jpg'),
        router: '/blog',
        style: 'effect-sadie'
      }, {
        title: '生活 <span>分享</span>',
        desc: '喜 欢 有 趣 的 生 活。',
        image: require('../../assets/image/8.jpg'),
        router: '/life',
        style: 'effect-oscar'
      }, {
        title: '关 于  <span>我</span>',
        desc: '我 知 道，你 很 想 遇 见 我。',
        image: require('../../assets/image/cj0.jpeg'),
        router: '/contact',
        style: 'effect-marley'
      }],
      title: '我是一名95年生的coder',
      descList: ['一名刚刚毕业的菜鸟小码农，热爱生活，喜欢设计，', '严谨，强迫症，爱玩，爱电影，爱运动', '这是我的地盘，分享技术经验，生活琐事，见闻趣事。']
    }
  },
  methods: {

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
  background: #f6f9fa;
}
a{
  color: #4f7f90;
  text-decoration: none;
  outline: 0;
}
.codrops-header .header-desc-p{
  margin: 0 0 24px;
}
.view-body-main{
  top: 85%;
  padding: 0px;
}
.container{
  width: 100%;
  color: #536d76;
  font-weight: 400;
}
.codrops-header{
  margin: 0 auto;
  padding: 4em 1em;
  text-align: center;
  padding-top: 6px;
}
.codrops-header h1{
  margin: 0;
  font-weight: 800;
  font-size: 3em;
  line-height: 1.3;
}
.codrops-header h1 span{
  display: block;
  padding: 0 0 .6em .1em;
  font-size: 45%;
  font-weight: 300;
  color: #7e959d;
}
/** grid.css **/
.grid{
  overflow: hidden;
  margin: 0;
  padding: 3em 0 0 0;
  width: 100%;
  list-style: none;
  text-align: center;
}
.grid figure{
  position: relative;
  z-index: 1;
  display: inline-block;
  overflow: hidden;
  margin: -.135em;
  width: 50%;
  height: 400px;
  background: #3085a3;
  text-align: center;
  cursor: pointer;
}
.grid figure img{
  position: relative;
  display: block;
  min-height: 100%;
  width: 100%;
  opacity: .8;
}
.grid figure a{
  z-index: 1000;
  text-indent: 200%;
  white-space: nowrap;
  font-size: 0;
  opacity: 0;
}
.grid figure a,
.grid figure figcaption{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.grid figure figcaption{
  padding: 2em;
  color: #fff;
  text-transform: uppercase;
  font-size: 1.25em;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}
.grid figure h2,
.grid figure p{
  margin: 0;
}
.grid figure h2{
  word-spacing: -.15em;
  font-weight: 300;
}
.grid figure p{
  letter-spacing: 1px;
  font-size: 68.5%;
}
.grid figure figcaption::after,
.grid figure figcaption::before{
  pointer-events: none;
}
.grid figure h2 span{
  font-weight: 800;
}

/** effect-lily.css */
figure.effect-lily img{
  width: -webkit-calc(100% + 50px);
  width: calc(100% + 50px);
  opacity: .7;
  -webkit-transition: opacity .35s,-webkit-transform .35s;
  transition: opacity .35s,transform .35s;
  -webkit-transform: translate3d(-40px,0,0);
  transform: translate3d(-40px,0,0);
}
figure.effect-lily figcaption{
  top: auto;
  bottom: 0;
  height: 50%;
  text-align: left;
}
figure.effect-lily h2{
  -webkit-transition: -webkit-transform .35s;
  transition: transform .35s;
}
figure.effect-lily h2,
figure.effect-lily p{
  -webkit-transform: translate3d(0,40px,0);
  transform: translate3d(0,40px,0);
}
figure.effect-lily p{
  color: rgba(255,255,255,.6);
  opacity: 0;
  -webkit-transition: opacity .2s,-webkit-transform .35s;
  transition: opacity .2s,transform .35s;
}
figure.effect-lily:hover img,
figure.effect-lily:hover p{
  opacity: 1;
}
figure.effect-lily:hover h2,
figure.effect-lily:hover img,
figure.effect-lily:hover p{
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}

/* effect-sadie.css */
figure.effect-sadie h2{
  position: absolute;
  top: 50%;
  left: 0;
  width: 100%;
  color: #484c61;
  -webkit-transition: -webkit-transform .35s,color .35s;
  transition: transform .35s,color .35s;
  -webkit-transform: translate3d(0,-50%,0);
  transform: translate3d(0,-50%,0);
}
figure.effect-sadie:hover h2{
  color: #fff;
  -webkit-transform: translate3d(0,-50%,0) translate3d(0,-40px,0);
  transform: translate3d(0,-50%,0) translate3d(0,-40px,0);
}
figure.effect-sadie:hover figcaption::before, figure.effect-sadie:hover p{
  opacity: 1;
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}

figure.effect-sadie figcaption::before{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: -webkit-linear-gradient(top,rgba(72,76,97,0) 0,rgba(72,76,97,.8) 75%);
  background: linear-gradient(to bottom,rgba(72,76,97,0) 0,rgba(72,76,97,.8) 75%);
  content: '';
  opacity: 0;
  -webkit-transform: translate3d(0,50%,0);
  transform: translate3d(0,50%,0);
}
figure.effect-sadie p{
  position: absolute;
  bottom: 0;
  left: 0;
  padding: 2em;
  width: 100%;
  opacity: 0;
  -webkit-transform: translate3d(0,10px,0);
  transform: translate3d(0,10px,0);
}
figure.effect-sadie figcaption::before,
figure.effect-sadie p{
  -webkit-transition: opacity .35s,-webkit-transform .35s;
  transition: opacity .35s,transform .35s;
}
figure.effect-sadie:hover figcaption::before,
figure.effect-sadie:hover p{
  opacity: 1;
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}

/** effect-oscar.css **/
figure.effect-oscar{
  background: -webkit-linear-gradient(45deg,#22682a 0,#9b4a1b 40%,#3a342a 100%);
  background: linear-gradient(45deg,#22682a 0,#9b4a1b 40%,#3a342a 100%);
}
figure.effect-oscar img{
  opacity: .9;
  -webkit-transition: opacity .35s;
  transition: opacity .35s;
}
figure.effect-oscar figcaption{
  padding: 3em;
  background-color: rgba(58,52,42,.7);
  -webkit-transition: background-color .35s;
  transition: background-color .35s;
}
figure.effect-oscar:hover img{
  opacity: .4;
}
figure.effect-oscar:hover figcaption{
  background-color: rgba(58,52,42,0);
}
figure.effect-oscar figcaption::before{
  position: absolute;
  top: 30px;
  right: 30px;
  bottom: 30px;
  left: 30px;
  border: 1px solid #fff;
  content: '';
}
figure.effect-oscar:hover figcaption::before,
figure.effect-oscar:hover p{
  opacity: 0;
  -webkit-transition: opacity .35s,-webkit-transform .35s;
  transition: opacity .35s,transform .35s;
  -webkit-transform: scale(0);
  transform: scale(0);
}
figure.effect-oscar figcaption::before,
figure.effect-oscar p{
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
}
figure.effect-oscar h2{
  margin: 20% 0 10px 0;
  -webkit-transition: -webkit-transform .35s;
  transition: transform .35s;
  -webkit-transform: translate3d(0,100%,0);
  transform: translate3d(0,100%,0);
}
figure.effect-oscar:hover h2{
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}
figure.effect-oscar:hover figcaption::before,
figure.effect-oscar:hover p{
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
}
figure.effect-oscar figcaption::before,
figure.effect-oscar p{
  opacity: 0;
  -webkit-transition: opacity .35s,-webkit-transform .35s;
  transition: opacity .35s,transform .35s;
  -webkit-transform: scale(0);
  transform: scale(0);
}

figure.effect-marley figcaption{
  text-align: right;
}
figure.effect-marley h2{
  top: 30px;
  -webkit-transition: -webkit-transform .35s;
  transition: transform .35s;
  -webkit-transform: translate3d(0,20px,0);
  transform: translate3d(0,20px,0);
}

/** effect-marley **/
figure.effect-marley h2,
figure.effect-marley p{
  position: absolute;
  right: 30px;
  left: 30px;
  padding: 10px 0;
}
figure.effect-marley p{
  bottom: 30px;
  line-height: 1.5;
  -webkit-transform: translate3d(0,100%,0);
  transform: translate3d(0,100%,0);
}
figure.effect-marley h2::after{
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  height: 4px;
  background: #fff;
  content: '';
  -webkit-transform: translate3d(0,40px,0);
  transform: translate3d(0,40px,0);
}
figure.effect-marley h2::after,
figure.effect-marley p{
  opacity: 0;
  -webkit-transition: opacity .35s,-webkit-transform .35s;
  transition: opacity .35s,transform .35s;
}
figure.effect-marley:hover h2::after,
figure.effect-marley:hover p{
  opacity: 1;
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}
figure.effect-marley:hover h2{
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}
figure.effect-marley h2::after,
figure.effect-marley p{
  opacity: 0;
  -webkit-transition: opacity .35s,-webkit-transform .35s;
  transition: opacity .35s,transform .35s;
}
figure.effect-marley:hover h2::after,
figure.effect-marley:hover p{
  opacity: 1;
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}

@media screen and (max-width: 69.5em){
  .grid figure {
    width: 50%;
  }
  .grid figure figcaption {
    font-size: 90%;
  }
}
@media screen and (max-width: 41.5em){
  .grid figure {
    width: 100%;
  }
}
</style>
