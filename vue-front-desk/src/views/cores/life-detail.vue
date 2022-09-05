<template>
  <div class="view-body-detail">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <div class="content-detail content">
          <div class="detail-head"><hr /></div>
          <div v-html="markdownHtmlTest" class="markdown-body">
          </div>
        </div>
        <div class="thumb-for">
          <!-- thumbs-count传递的是点赞的数量，thumbs-up-flag传递的是当前用户是否点赞的标识 -->
          <thumb-up :thumbs-count="1" :thumbs-up-flag="true"></thumb-up>
        </div>
        <article-share :signature-author="signatureAuthor"></article-share>
        <hr style="margin-top:8px;" />
        <br/>
        <div class="content-detail-end comment shadow">
          <blog-comment :user-data="userData" v-on:submit-comment="subLeaveMessage">
          </blog-comment><hr />
          <div class="comment-list">
            <div id="comment" class="comment-list-head">
              评论列表 <font style="font-size: 14px;color:#c7254e;">(共5条评论)</font>
              <hr>
            </div>
            <div class="comment-list-body">
              <div class="pulldown" v-if="!isGetComment"><a @click="getComment" href="javascript:;">看看别人怎么说</a></div>
              <comment-list v-if="isGetComment" :comment-list="commentList"></comment-list>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import thumbUp from '@/views/components/thumb-up.vue';
import articleShare from '@/views/components/detail/article-share.vue';
import blogComment from '@/views/components/blog-comment';
import commentList from '@/views/components/comment-list';

// 这里异步加载本地
import content from '../../assets/life_test.txt';
// 可以由外部引入您的markdown样式，这里引入的是我自己博客的markdown文章的样式。
// 需要更改的可以注释掉，引入自己需要的比如富文本的样式或者自己在markdown.CSS里面修改
import markdown from '../../assets/css/markdown/markdown.css';
import config from '@/config/blog-config.json';

export default {
  name: 'life-detail',
  components: {
    thumbUp: thumbUp,
    articleShare: articleShare,
    blogComment: blogComment,
    commentList: commentList
  },
  data () {
    return {
      content: content,
      markdownHtmlTest: '',
      thumbsUpFlag: false,
      thumbsUpCount: 0,
      signatureAuthor: '2018-07-24 8:00 By Leon',
      isGetComment: false,
      userData: {
        nickname: '唐益达',
        email: '530063113@qq.com'
      },
      commentList: []
    }
  },
  created () {
    let converter = new showdown.Converter();
    this.markdownHtmlTest = converter.makeHtml(this.content);
    this.init();
  },
  methods: {
    init () {
      this.getConfig()
    },
    getConfig () {
      this.commentList = JSON.parse(JSON.stringify(config.data.detail['detailComment']));
      this.commentList.forEach(item => {
        item.userPhoto = require('../../' + item.userPhoto);
      })
    },
    subLeaveMessage (data) {
      alert(JSON.stringify(data));
    },
    getComment () {
      alert('这里通过请求获取了评论信息，通过isGetComment标识来判断是否显示请求的数据与此按钮');
      this.isGetComment = !this.isGetComment;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.view-body-detail{
  position: relative;
  top:unset;
}
.detail-head{
  margin-top: 30px;
}
.content-detail, .content-detail-end{
  background-color: #fafafa;
  padding: 5% 4%;
}
@media (min-width: 768px){
  .view-body-detail >.row{
    padding: 0 14%;
  }
}
</style>
