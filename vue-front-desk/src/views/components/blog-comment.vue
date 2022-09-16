<template>
  <fieldset>
    <legend v-if="!isResponse" class="comment-head">留下你的评论</legend>
    <legend v-if="isResponse" class="comment-head">回复 {{responseName}}</legend>
    <form>
    <div class="comment-body">
      <div class="comment-name">
        <input style="color:#000;" @focus="isStartNick=true;isFirstInput = false;" v-model="nickname" type="text" name="comment" placeholder="您的昵称" maxlength="20">
      </div>
      <div class="comment-name">
        <input style="color:#000;" @focus="isStartEmail=true; isFirstInput =false;" v-model="email" type="text" name="comment" placeholder="您的邮箱(在回复提醒您的时候将会向此邮箱发邮件,不会对外公布)" maxlength="30">
      </div>
      <textarea placeholder="在此写下你的想法" v-focus="focusStatus" @blur="focusStatus=false" style="color:#000;" v-model="textareaData" maxlength="200" ></textarea>
    </div>
    <div class="comment-foot">
      <button @click="confirm" class="comment-btn" type="button">提交</button>
    </div>
    </form>
  </fieldset>
</template>

<script>
import { saveMessage } from '@/api/comment';
export default {
  props: ['isResponse', 'responseName', 'userData', 'commentType', 'articleId'],
  name: 'blogComment',
  data () {
    return {
      focusStatus: false,
      textareaData: '',
      nickname: '',
      email: '',
      isResponseData: true,
      isPC: false,
      isStartNick: false,
      isStartEmail: false,
      isFirstInput: true
    }
  },
  created: function () {
    if (this.userData) {
      this.email = this.userData.email;
      this.nickname = this.userData.nickname;
    }
  },
  computed: {
  },
  methods: {
    confirm: function () {
      var _self = this;
      var data = {
        nickName: _self.nickname,
        content: _self.textareaData,
        email: _self.email,
        commentType: this.commentType,
        articleId : this.articleId
      };
      if (data.nickName.length == 0 && data.nickName == ''){
        alert('昵称不能为空')
        return
      }
      if (data.content.length == 0 && data.content == ''){
        alert('内容不能为空')
        return
      }
      if (data.email.length == 0 && data.email == ''){
        alert('邮箱不能为空')
        return
      }
      var email_Regex = new RegExp('^.+@[A-Z0-9a-z]+\.[a-zA-Z]+$');
      if (!email_Regex.test(data.email)) {
        alert('邮箱格式错误！');
        return
      }
      saveMessage(data).then(res => {
        alert('留言成功!')
        this.$router.go(0);
      })
    }
  },
  directives: {
    focus: {
      update: function (el, {value}) {
        if (value) {
          el.focus();
        }
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
fieldset{
  padding: .35em .625em .75em;
  border: none;
  border-top: 1px solid #e2e2e2;
  margin: 0 2px;
}
legend{
  display: block;
  width: auto;
  border: none;
  margin-left: 20px;
  padding-left: 8px;
  padding-right: 8px;
  line-height: inherit;
}
.comment-head{
		font-size: 18px;
		font-weight: 400;
    color: #666;
}

.comment-body textarea{
		width: 100%;
		height: auto;
		min-height: 100px;
		padding:6px 10px;
		border:1px solid #eee;
		line-height:28px;
		box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
		-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
		-moz-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
		background-color:#fafafa;
		border-radius: 2px;
}
.comment-body textarea:focus,
.comment-name input{
	outline: none;
}
.comment-name{
	margin-bottom: 10px;
	position: relative;
}
.comment-name input{
	width: 60%;
	padding: 8px;
	border:1px solid #eee;
	background-color: #FAFAFA;
	box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
	-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
	-moz-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
	border-radius: 2px;
}
.comment-btn{
	height: 38px;
	line-height: 38px;
	padding: 0 18px;
	background-color: #337ab7;
	color: #fff;
	white-space: nowrap;
	text-align: center;
	font-size: 14px;
	border: none;
	border-radius: 2px;
	cursor: pointer;
	opacity: .9;
}
.comment-foot{
	margin-top: 10px;
}
@media(max-width: 767px){
  .comment-name input{
		width: 100%;
	}
}
</style>
