<template>
  <div class="view-body-detail">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <div id="content-detail" ref="content-detail" class="content-detail content">
         <div v-if="sonRefresh">
           <detail-nav-bar></detail-nav-bar>
         </div>
          <div class="detail-head">
            <div class="detail-title">
              <h1>{{article.articleTitle}}</h1>
            </div>
            <hr />
          </div>
          <div v-html="article.htmlContent" class="markdown-body">
          </div>
        </div>
        <div class="thumb-for">
          <!-- thumbs-count传递的是点赞的数量，thumbs-up-flag传递的是当前用户是否点赞的标识 -->
          <thumb-up :thumbs-count="article.numberLike" :thumbs-up-flag="false" ></thumb-up>
        </div>
        <article-share :signature-author="signatureAuthor"></article-share>
        <hr style="margin-top:8px;" />
        <article-next-pre :pre-next="articleNextPreData"></article-next-pre>
        <article-tags :tag-list="tagList"></article-tags>
        <hr /><br/>
        <div v-if="commentStatus" class="content-detail-end comment shadow">
          <blog-comment :user-data="userData" :comment-type="commentType" :article-id="article.id" v-on:submit-comment="subLeaveMessage">
          </blog-comment><hr />
          <div class="comment-list">
            <div id="comment" class="comment-list-head">
              评论列表 <font style="font-size: 14px;color:#c7254e;">(共 {{commentList.length}} 条评论)</font>
              <hr>
            </div>
            <div class="comment-list-body">
              <div class="pulldown" v-if="!isGetComment"><a @click="getComment" href="javascript:;">看看别人怎么说</a></div>
              <comment-list v-if="isGetComment" :comment-list="commentList"></comment-list>
            </div>
          </div>
        </div>
        <article-most-view :most-view="articleMostView"></article-most-view>
      </div>
    </div>
  </div>
</template>

<script>
import thumbUp from '@/views/components/thumb-up.vue';
import articleNextPre from '@/views/components/detail/article-next-pre.vue';
import articleTags from '@/views/components/detail/article-tags.vue';
import articleMostView from '@/views/components/detail/article-most-view.vue';
import articleShare from '@/views/components/detail/article-share.vue';
import detailNavBar from '@/views/components/detail-nav-bar.vue';
import blogComment from '@/views/components/blog-comment';
import commentList from '@/views/components/comment-list';
// 可以由外部引入您的markdown样式，这里引入的是我自己博客的markdown文章的样式。
// 需要更改的可以注释掉，引入自己需要的比如富文本的样式或者自己在markdown.CSS里面修改
import markdown from '../../assets/css/markdown/markdown.css';
import content from '../../assets/content.txt';
import { getById } from '@/api/article'
import { getListArticle } from '@/api/comment'
export default {
  name: 'blog-detail',
  components: {
    detailNavBar: detailNavBar,
    thumbUp: thumbUp,
    articleNextPre: articleNextPre,
    articleTags: articleTags,
    blogComment: blogComment,
    commentList: commentList,
    articleMostView: articleMostView,
    articleShare: articleShare
  },
  data () {
    return {
      commentType: '1',
      commentStatus : false,
      sonRefresh: false,
      article: {
        id: undefined,
        content: undefined,
        updateTime: undefined,
        articleTitle: undefined,
        introduce: undefined,
        articleType: undefined,
        articleTag: undefined,
        originStatus: undefined,
        clickRate: undefined,
        articleTagList: [],
        tagList: [],
        createBy:undefined,
        commentNumber: undefined,
        logImg: undefined,
        numberLike: undefined,
        articleNextPreDataList:[],
        commentStatus: undefined,
        articlePopularList: [],
        htmlContent: undefined
      },
      content: content,
      thumbsUpFlag: false,
      thumbsUpCount: 0,
      signatureAuthor: '2018-07-24 8:00 By Jzj',
      isGetComment: false,
      userData: {
        nickname: '',
        email: ''
      },
      articleMostView: [],
      commentList: [],
      // 默认第一个是pre，第二个是next
      articleNextPreData: [],
      tagList: []
    }
  },
  created () {
    this.init();
  },
  methods: {
    init () {
      this.getArticleDetail();
      this.getConfig();
    },
    getArticleDetail () {
      getById(this.$route.params.articleId).then(res => {
        this.article = res.data
        // 作者签名
        this.signatureAuthor = this.article.updateTime + ' By ' + this.article.createBy
        // 封装上一页下一页
        this.articleNextPreData = this.article.articleNextPreDataList
        // 封装全部标签列表
        this.tagList = this.article.tagList
        this.content = this.article.content
        this.sonRefresh = true
        this.articleMostView = this.article.articlePopularList
        // 评论是否显示
        if (this.article.commentStatus === '0'){
          this.commentStatus = true
        }
      })
    },
    getConfig () {
      getListArticle(this.$route.params.articleId).then(res => {
        this.commentList = res.data.list
      })
    },
    subLeaveMessage (data) {
      alert(data);
    },
    getComment () {
      this.isGetComment = !this.isGetComment;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.view-body-detail{
  opacity: 0;
  position: absolute;
  top: 40%;
  background: 0 0;
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
  width: 100%;
}
.slide .view-body-detail{
  opacity: 1;
}
.content-detail,
.content-detail-end{
  background-color: #fafafa;
  padding: 5% 4%;
}
.detail-head{
  font-weight: 400;
  text-align: center;
  font-family: Vollkorn,Palatino,Times;
  color: #333;
  line-height: 1.4;
  margin-top: 50px;
  margin-bottom: 30px;
}
.comment-list-head{
  font-weight: 500;
  font-size: 18px;
}
.comment-list-head hr{
  margin-top: 10px;
  margin-bottom: 10px;
}
@media (min-width: 768px){
  .view-body-detail {
    padding: 10px 14% 0 14%;
  }
  .view-body-detail>.row {
    padding: 0 10%;
  }
}
@media (max-width: 767px){
  .row {
    margin: 0;
  }
}
@media (max-width: 540px) {
  .content-detail{
    padding: 5% 0%;
  }
}
</style>
