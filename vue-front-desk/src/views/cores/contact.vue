<template>
  <div id="view-body" class="view-body view-body-contact">
    <div style="margin-top:20px;" class="content-container">
      <div class="content-container-item">
        <header class="contact-header">
          <div class="tangyidaImag">
            <img class="tangyida" :src="bloggerImage"/>
          </div>
          <div class="name-introduce">
            <h1>我是唐益达</h1>
            <h5>一名95年生的前端小菜鸡<font style="font-weight:200"> <(￣︶￣)></font>。</h5><br />
            <div style="position:relative;" class="contact-tools">
              <div v-show="wechatFlag" style="position:absolute;top:-100%;left:0px;right:0px;">
                <img @mouseleave="wechatFlag=false;" width="320px" src="../../assets/image/WechatIMG390.jpeg" />
              </div>
              <a @mouseenter="wechatFlag=true;"  href="javascript:;" style="color:#00ca0e;">
                <i class="fa fa-wechat fa-2x" aria-hidden="true"></i>
              </a>
              <a href="https://github.com/xdnloveme" target="view_window" style="color:#222;margin-left: 10px;">
                <i class="fa fa-github fa-2x" aria-hidden="true"></i>
              </a>
              <a href="https://weibo.com/xdnloveme" target="view_window" style="color:#e4162c;margin-left: 10px;">
                <i class="fa fa-weibo fa-2x" aria-hidden="true"></i>
              </a>
            </div>
          </div>
          <hr />
          <div v-for="introduce in introduceList" class="contact-introduce-item">
            <h2>{{introduce.title}}</h2>
            <p v-for="sectionItem in introduce.section" v-html="sectionItem.content">
            </p>
          </div>
        </header>
      </div>
      <div class="content-container-item">
        <div style="text-align:center;">
          <h2><i class="fa fa-clock-o" aria-hidden="true"></i> 本站的时间线</h2>
          <br />
        </div>
        <hr />
        <time-line :timeline-list="timelineList"></time-line>
      </div>
      <hr />
      <div style="text-align:center;" class="contact-introduce-item">
        <h2>谢谢你。</h2>
        <p>谢谢你能耐心地看到这里，谢谢你的支持，留下一个足迹可好？（:-D 下方留言）</p>
      </div>
      <div id="leave_message" class="content-container-item">
        <h2>留言</h2>
        <h5>留言板块</h5>
        <br />
        <div class="comment">
          <blog-comment :user-data="userData" v-on:submit-comment="subLeaveMessage">
          </blog-comment>
          <br/><br />
          <div id="comment" class="comment-list-head">评论墙 <font style="font-size: 14px;color:#c7254e;">(共20条评论)</font><hr/></div>
            <comment-wall :comment-list="commentList" :waterfall-data="waterfallData" v-on:waterfall="waterfallTest"></comment-wall>
        </div>
      </div>
    </div>
    <blog-foot></blog-foot>
  </div>
</template>

<script>
import timeLine from '../components/time-line';
import blogFoot from '@/views/components/blog-foot';
import blogComment from '@/views/components/blog-comment';
import commentWall from '@/views/components/comment-wall';

import config from '@/config/blog-config.json';

export default {
  name: 'contact',
  components: {
    timeLine: timeLine,
    blogFoot: blogFoot,
    blogComment: blogComment,
    commentWall: commentWall
  },
  data () {
    return {
      wechatFlag: false,
      bloggerImage: require('../../assets/image/tangyida.jpeg'),
      userData: {
        nickname: '唐益达',
        email: '530063113@qq.com'
      },
      introduceList: [],
      timelineList: [],
      commentList: [],
      // 一定要声明这个瀑布流加载的数据对象
      waterfallData: null,
      count: 0 // 瀑布流页码模拟
    }
  },
  created () {
    console.log(this.$route);
    this.init();
  },
  methods: {
    init () {
      this.getConfig();
    },
    getConfig () {
      this.timelineList = config.data.contact['timeLine'];
      this.commentList = JSON.parse(JSON.stringify(config.data.contact['commentList']));
      this.introduceList = config.data.contact['introduceList'];
      this.getConfigImage();
    },
    getConfigImage () {
      this.commentList.forEach(item => {
        item.userPhoto = require('../../' + item.userPhoto);
        // 这里通过创建虚拟dom节点获取评论消息中所有的图片，将其require()进来进行一个转换
        var frag = document.createElement('div');
        frag.innerHTML = item.message;
        Array.prototype.forEach.call(frag.getElementsByTagName('img'), (nodeItem) => {
          var image = nodeItem.src.split(`${window.location.host}/`)[1];// 获取图片的相对路径
          nodeItem.src = require('../../' + image);
        });
        item.message = frag.innerHTML;
      })
    },
    subLeaveMessage (data) {
      alert(`您点击了提交,数据对象如下:${JSON.stringify(data)}`);
    },
    // 用作瀑布流的留言墙测试例子（仅供参考）
    waterfallTest: function (data) {
      alert('滑动到底了，触发瀑布流效果，这是一个延迟函数模拟请求，仅供测试');
      console.log(data);
      let _self = this;
      if (data.isFinish) { // 如果服务器没有数据了，那么就返回(不建议这样做);
        return;
      }
      setTimeout(() => {
        // 成功的回调
        _self.count++;
        data.isLoading = false;
        data.isLoading = false;
        if (_self.count === 3) { // 如果在服务器端没有数据返回了为空(这里假设获取的数据为空)，那么赋值isFinish为真，这里仅仅为测试
          data.isFinish = true;
        }
        _self.commentList.push(_self.commentList[0]);
        _self.commentList.push(_self.commentList[1]);
        _self.commentList.push(_self.commentList[2]);
        _self.commentList.push(_self.commentList[3]);
        _self.commentList.push(_self.commentList[4]);
        _self.waterfallData = data;
      }, 2000)
    }
  }

}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,h2,h3,h4,h5,h6{
 color: #3d4351;
 margin-top: 0;
}
.view-body-contact{
  z-index: 98;
  top: 85%;
  color: #768390;
  -webkit-font-smoothing: antialiased;
  font-family: Optima,"Lucida Sans",Calibri,Candara,Arial,source-han-serif-sc,"Source Han Serif SC","Source Han Serif CN","Source Han Serif TC","Source Han Serif TW","Source Han Serif","Songti SC","Microsoft YaHei",sans-serif;
}
.h3, h3{
  font-size: 24px;
}

.content-container{
  padding-left: 15px;
  padding-right: 15px;
}
.content-container-item{
  padding-top:20px;
}
.tangyidaImag{
  text-align: center;
}
.tangyida{
  width: 100px;
  height: 100px;
  border-radius: 100%;
  opacity: .85;
}
.name-introduce{
  text-align: center;
  margin-top: 20px;
}
.name-introduce h1{
  font-weight: 800;
  font-size: 3.25em;
}
.name-introduce h5{
  color: #777;
}
.contact-introduce-item{
  margin-bottom: 80px;
}
.contact-header p,
.contact-introduce-item p{
  font-size: 20px;
  margin-bottom: 28px;
}
.contact-introduce-item > p:last-child{
  margin-bottom: 0px;
}
.contact-introduce-item >>> a{
  text-decoration: underline;
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
  .view-body-contact {
      padding: 0 15%;
  }
	.tangyida{
		width: 180px;
		height: 180px;
		border-radius: 100%;
	}
}
</style>
