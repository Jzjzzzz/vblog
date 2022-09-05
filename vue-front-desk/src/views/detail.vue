<template>
  <div>
    <div :style="viewBackstyle" :class="current.class">
      <div class="title"><h1>{{title}}</h1>
        <h2 v-if="current.id === 'blog-detail'" class="title-h2 tagsArr">
          <a v-for="item in tagsArr" href="javascript:;" class="tag">{{item}}</a>
        </h2>
        <h2 class="title-h2">
          <span ><i class="fa fa-user" aria-hidden="true"></i> &nbsp;{{author}}</span><br /><br />
          <span class="title-h2-date">
            <i aria-hidden="true" class="fa fa-calendar-o"></i> &nbsp;{{time}}</span>
        </h2>
        <h3 class="title-h3">
          <span class="title-h3-view">
            <i class="fa fa-eye" aria-hidden="true"></i> view {{readCount}}
          </span>
          <span>
            <a class="title-h3-comment" href="#comment">
              <i class="fa fa-comments-o" aria-hidden="true"></i> comment {{commentCount}}
            </a>
          </span>
          <br>
        </h3>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
import config from '@/config/blog-config.json'

export default {
  name: 'detail',
  data () {
    return {
      viewBackstyle: {
        backgroundImage: ''
      },
      currentType: '',
      detailList: [],
      articleList: [],
      lifeList: [],
      current: null,
      title: '',
      tagsArr: null,
      author: '',
      time: '',
      readCount: -1,
      commentCount: -1
    }
  },
  created () {
    this.init();
  },
  mounted () {

  },
  methods: {
    init () {
      this.getConfig();
      this.current = this.getCurrentRoute();
      if (this.current.id == 'blog-detail') {
        this.getArticleDetail();
      } else if (this.current.id == 'life-detail') {
        this.getLifeDetail();
      }
    },
    getConfig () {
      this.detailList = config.page.detail;
      this.articleList = config.data.article['articleList'];
      this.lifeList = config.data.life['lifeList'];
    },
    getCurrentRoute () {
      const id = this.$route.name;
      let current = this.detailList.find(item => {
        return item.id === id;
      });
      return current;
    },
    getArticleDetail () {
      let articleId = this.$route.params.articleId;
      let article = this.articleList.find(item => {
        return item.id == articleId
      });
      this.title = article.title;
      this.viewBackstyle.backgroundImage = `url(${require('../' + article.photoUrl)})`;
      this.tagsArr = article.tag.split(',');
      this.author = article.author;
      this.time = article.time;
      this.readCount = article.articleRead;
      this.commentCount = article.commentCount;
    },
    getLifeDetail () {
      let lifeId = this.$route.params.lifeId;
      let life = this.lifeList.find(item => {
        return item.id == lifeId
      });
      console.log(life);
      this.title = life.title;
      this.viewBackstyle.backgroundImage = `url(${require('../' + life.photo)})`;
      this.author = life.author;
      this.time = life.time;
      this.readCount = life.read;
      this.commentCount = life.thumb;
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.slide .view-detail{
  transform: translate3d(0,-60%,0);
  -webkit-transform: translate3d(0,-60%,0);
  -moz-transform: translate3d(0,-60%,0);
  -ms-transform: translate3d(0,-60%,0);
}
.slide .view-detail::after{
  background: 0 0;
}
.view-detail-life{
  position: relative;
  min-height: 600px;
  background-size: cover;
  background-position: center;
  transition: all .5s;
  -webkit-transition: all .5s;
  -moz-transition: all .5s;
  -ms-transition: all .5s;
}
.view-detail{
  position: absolute;
  top: 0;
  width: 100%;
  bottom: 0;
  background-size: cover;
  background-position: center;
  transition: all .4s;
  -webkit-transition: all .4s;
  z-index: 98;
}
.view-detail::after,
.view-detail-life::after{
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  pointer-events: none;
  transition: all .4s;
  -webkit-transition: all .4s;
}
.view-detail::after{
  background-color: rgba(29,29,29,.45);
}
.title{
  position: absolute;
  text-align: justify;
  margin: 0 auto;
  width: 100%;
  color: #fff;
  z-index: 1;
}
.view-detail-life .title{
  top:unset;
  padding-top:120px;
  height: 100%;
  padding-right: 20px;
  background-color: rgba(29,29,29,.45);
}
.view-detail .title{
  top: 20%;
  padding: 0 18%;
}
.title h1{
  padding: 0 0 .2em;
  color: #fff;
  font-weight: 700;
}
.view-detail .title h1{
  font-size: 65px;
  margin: 0 auto;
  border-radius: 5px;
  margin-bottom: -10px;
}
.view-detail-life .title h1{
  font-size: 3.75em;
  margin-top: 0;
  margin-bottom: 40px;
  border-radius: 5px;
}
.view-detail-life .title .title-h2{
  font-size: 28px;
}
.view-detail-life .title .title-h3{
  margin-top: 30px;
}
.title-h3 .title-h3-comment, .title-h3 .title-h3-view{
  margin-top: 14px;
  font-size: 20px;
}
.title .title-h2-date{
  font-family: Lora,'Times New Roman',serif;
  font-style: italic;
  font-weight: 300;
  display: block;
  margin-top: 16px;
  font-size: 20px;
}
.tagsArr{
  margin-top: 0;
  margin-bottom: 50px;
}
.tagsArr .tag{
  margin-left: 14px;
  background-color: #fff;
  color: rgba(153,153,153,.8);
  font-weight: 400;
}
.tag{
  color: #fff;
  font-size: 14px;
  height: 24px;
  line-height: 24px;
}
.tagsArr .tag::before{
  border-right-color: #fff;
}
.tag:before{
  width: 0;
  height: 0;
  top: 0;
  left: -24px;
  border: 12px solid transparent;
  border-right-color: grey;
}
.tag:after{
  width: 4px;
  height: 4px;
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 0 0 1px rgba(0,0,0,.3);
  top: 10px;
  left: 1px;
}
.tag:hover {
  color: #fff;
  background-color: #de686d;
}
.tag:hover::before {
  border-right-color: #de686d;
}
.title-h3-view{
  margin-right: 20px;
}
.title-h3{
  font-size: 20px;
  margin-top: 28px;
}
.title-h3-comment{
  color:#fff;
}
@media (max-width: 767px){
  .tagsArr .tag {
    font-size: 12px;
  }
  .view-detail .title {
    top: 14%;
    padding: 0 20px;
  }
  .view-detail .title h1 {
    font-size: 40px;
  }
  .view-detail-life .title{
    padding-left: 24px;
  }
}
@media (min-width: 768px){
  .view-detail-life .title{
    padding-left: 22%;
    padding-right: 22%;
  }
}
</style>
