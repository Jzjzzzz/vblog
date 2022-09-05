<template>
  <ul>
    <li v-if="commentList.length==0">T.T尴尬,似乎没人评论......</li>
    <li v-for="(comment,$key) in commentList">
      <div class="comment-detail">
        <img class="comment-detail-img" :src="comment.userPhoto">
        <div class="comment-detail-body">
          <div class="comment-detail-body-top">
            <span class="detail-top-nickname">{{comment.nickname}} &nbsp;<span v-if="comment.nickname=='唐益达'" class="bz-icon">博主</span></span>
            <span class="detail-top-area">&nbsp;&nbsp;{{comment.area}} &nbsp;</span>
            <span style="float:right;color:#aaa;">{{commentList.length - $key}}楼 &nbsp;{{comment.date}}</span>
          </div>
          <div v-if="comment.response" class="comment-detail-response">
            <h5>引用 {{comment.responseName}}
              <font style="font-size: 12px;float: right;color:#aaa;font-weight: 400;">发表时间：{{comment.responseTime}}</font>
            </h5> {{comment.response}}
          </div>
          <div v-html="comment.message" class="comment-detail-body-bottom"></div>
          <div class="comment-detail-foot">
            <a href="javascript:;" @click="commentResponse($key)">
              <font v-if="!comment.isResponse">回复</font>
              <font v-if="comment.isResponse">取消回复</font>
            </a>
          </div>
        </div>
      </div>
      <div v-if="comment.isResponse">
        <blog-comment :user-data="userData"
        v-on:submit-comment="submitCommentResponse($event,comment.id,$key)"
        :response-name="comment.nickname"
        :is-response="true">
        </blog-comment>
      </div>
    </li>
  </ul>
</template>

<script>
import blogComment from './blog-comment';

export default {
  name: 'commentItem',
  props: ['commentList'],
  components: {
    blogComment: blogComment
  },
  data () {
    return {
      userData: {
        nickname: '唐益达',
        email: '530063113@qq.com'
      }
    }
  },
  methods: {
    commentResponse: function (index) {
      this.$set(this.commentList[index], 'isResponse', !this.commentList[index].isResponse);
    },
    submitCommentResponse: function (data, id, index) {
      alert(`您点击了回复他人的回话，条目编号为${index}，输出的数据为${JSON.stringify(data)}，即将回复的条目id为${id}`)
      console.log(data);
      console.log(index);
      console.log(id);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.comment-list-body,
.comment-list-body p,
.comment-list-body span,
.comment-list-body a,
.comment-list-body font{
	font-family:  Optima,"Lucida Sans",Calibri,Candara,Arial,source-han-serif-sc,"Source Han Serif SC","Source Han Serif CN","Source Han Serif TC","Source Han Serif TW","Source Han Serif","Songti SC","Microsoft YaHei",sans-serif;
	/*font-family: -apple-system,BlinkMacSystemFont,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;*/
}
.comment-list{
		padding: 0px 10px;
}
.comment-detail-img{
		display: inline-block;
		width: 60px;
		height: 60px;
		border-radius: 50%;
		border: 1px solid #eee;
}
.detail-top-nickname{
		font-weight: 600;
		font-size: 16px;
		color:#333;
}
.comment-detail-body{
		display: inline-block;
		margin-left: 20px;
		flex: 1;
		/*position: absolute;*/
}
.comment-list-body ul{
		padding: 0px;
		position: relative;
}
.comment-list-body li{
		padding: 10px;
		border-bottom: 1px solid #eee;
}
.comment-detail-body-bottom{
		padding-bottom: 20px;
		color: #768390;
		padding-top: 8px;
		font-weight: 500;
		font-size: 16px;
}
.comment-detail{
		display: flex;
		position: relative;
		padding: 10px 0px;
}
.guide-comment{
		font-size: 16px;
		color: white;
		margin-bottom: 10px;
}
.guide-comment a{
		padding: 10px;
}
.comment-detail-foot{
		position: absolute;
		right: 0px;
		bottom: 0px;
}
.comment-detail-foot a{
		font-size: 12px;
		color: #de686d;
		background: #fff;
		border-radius: 4px;
		border: 1px solid #de686d;
		line-height: 30px;
		padding: 2px 5px;
}
.response-item-date{
	font-size: 12px;
	float: right;
	color:#aaa;
	font-weight: 400;
}
.comment-detail-response{
		background-color: #eee;
		/*min-height: 200px;*/
		padding: 10px;
		margin:6px 10px;
		font-size: 14px;
		font-weight: 400;
		color:#888;
}
.comment-name::before{
	content: '*';
  position: absolute;
  left: -10px;
  top: 12px;
  color: rgb(199, 37, 78);
}
.detail-top-area{
  font-size: 12px;
  color: #999;
}
@media (max-width: 768px) {
  .comment-list{
		padding: 0px;
	}
	.comment-list-body li{
		padding: 2px 2px 8px 2px;
	}
	.comment-detail-body{
		margin-left:0px;
    margin-bottom: 6px;
	}
	.comment-detail-img{
		width: 40px;
		height: 40px;
	}
	.comment-detail-body-top{
		padding-left: 10px;
	}
	.comment-detail-body-bottom{
		padding-left: 10px;
		padding-top: 6px;
	}
	.response-item-date{
		float: none;
		margin-left: 10px;
	}
}
@media (max-width: 540px){
  .detail-top-area {
    display: none;
  }
}
</style>
