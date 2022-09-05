<template>
  <fieldset>
    <legend v-if="!isResponse" class="comment-head">留下你的评论</legend>
    <legend v-if="isResponse" class="comment-head">回复 {{responseName}}</legend>
    <form>
    <div class="comment-body">
      <div class="comment-name">
        <input style="color:#000;" @focus="isStartNick=true;isFirstInput = false;" v-model="nickname" type="text" name="comment" placeholder="您的昵称">
        <br v-if="!isPC" /><font v-show="!isNickName" color="red">  &nbsp;(昵称必须小于10个字节，不能包含特殊字符)</font>
      </div>
      <div class="comment-name">
        <input style="color:#000;" @focus="isStartEmail=true; isFirstInput =false;" v-model="email" type="text" name="comment" placeholder="您的邮箱(在回复提醒您的时候将会向此邮箱发邮件,不会对外公布)">
        <br v-if="!isPC" /><font v-show="!isEmail" color="red">  &nbsp;(输入的必须为邮箱格式)</font>
      </div>
      <div class="emotions">
        <span @click="emotionSelect($event)" class="em00" title=":smile:"></span>
        <span @click="emotionSelect($event)" class="em01" title=":joy:"></span>
        <span @click="emotionSelect($event)" class="em02" title=":stuck_out_tongue_winking_eye:"></span>
        <span @click="emotionSelect($event)" class="em03" title=":persevere:"></span>
        <span @click="emotionSelect($event)" class="em04" title=":sob:"></span>
        <span @click="emotionSelect($event)" class="em05" title=":cold_sweat:"></span>
        <span @click="emotionSelect($event)" class="em06" title=":rage:"></span>
        <span @click="emotionSelect($event)" class="em07" title=":triumph:"></span>
        <span @click="emotionSelect($event)" class="em08" title=":eyes:"></span>
        <span @click="emotionSelect($event)" class="em09" title=":scream:"></span>
        <span @click="emotionSelect($event)" class="em10" title=":sunglasses:"></span>
        <span @click="emotionSelect($event)" class="em11" title=":yum:"></span>
        <span @click="emotionSelect($event)" class="em12" title=":heart:"></span>
        <span @click="emotionSelect($event)" class="em13" title=":broken_heart:"></span>
        <span @click="emotionSelect($event)" class="em14" title=":smiling_imp:"></span>
      </div>
      <textarea placeholder="在此写下你的想法" v-focus="focusStatus" @blur="focusStatus=false" style="color:#000;" v-model="textareaData"></textarea>
    </div>
    <div class="comment-foot">
      <button @click="confirm" class="comment-btn" type="button">提交</button>
    </div>
    </form>
  </fieldset>
</template>

<script>
export default {
  props: ['isResponse', 'responseName', 'userData'],
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
    this.isPC = this.isPc();
  },
  computed: {
    isEmail: function () {
      if (!this.isStartEmail) {
        return true;
      }
      return this.isEmailMethod(this.email);
    },
    isNickName: function () {
      if (!this.isStartNick) {
        return true;
      }
      return this.isNicknameOk(this.nickname);
    }
  },
  methods: {
    emotionSelect: function (e) {
      this.textareaData = this.textareaData + e.currentTarget.title;
      this.focusStatus = true;
    },
    confirm: function () {
      var _self = this;
      if (this.isFirstInput) {
        this.isStartNick = true;
        this.isStartEmail = true;
      }
      var data = {
        nickname: _self.nickname,
        textareaData: _self.textareaData,
        email: _self.email,
        isText: _self.isTextOk(),
        isEmail: _self.isEmail,
        isNickname: _self.isNickName
      };
      console.log(data);
      this.$emit('submit-comment', data);
      this.textareaData = '';
      if (this.isNickName && this.isEmail && this.isTextOk()) {
        if (this.userData) {
          return;
        }
        this.nickname = '';
        this.email = '';
        this.isStartNick = false;
        this.isStartEmail = false;
      }
    },
    isEmailMethod: function (str) {
      var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      return reg.test(str);
    },
    isNicknameOk: function (str) {
      var reg = /[！@#\$%\^&\*]+/g;
      var flag = false;
      if (this.getLength(str) <= 12 && str != '' && str != null && !(/\s/).test(str)) {
        flag = !(reg.test(str));
      } else {
        flag = false;
      }
      return flag;
    },
    isTextOk: function () {
      if (!this.isNull(this.textareaData)) {
        return true;
      } else {
        return false;
      }
    },
    getLength: function (s) {
      var len = 0;
      for (var i = 0; i < s.length; i++) {
        var c = s.substr(i, 1);
        var ts = escape(c);
        if (ts.substring(0, 2) == '%u') {
          len += 2;
        } else {
          len += 1;
        }
      }
      return len;
    },
    isPc: function () {
      var userAgentInfo = navigator.userAgent;
      var Agents = ['Android', 'iPhone',
        'SymbianOS', 'Windows Phone',
        'iPad', 'iPod'];
      var flag = true;
      for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
          flag = false;
          break;
        }
      }
      return flag;
    },
    isNull: function (str) {
      if (str == '') return true;
      var regu = '^[ ]+$';
      var re = new RegExp(regu);
      return re.test(str);
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
.comment{
		overflow:unset;
		margin-bottom: 20px;
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
.emotions{
		display: inline-block;
}
.em00, .em01, .em02, .em03, .em04, .em05, .em06, .em07, .em08, .em09, .em10, .em11, .em12, .em13, .em14{
		cursor: pointer;
		background-image: url(../../assets/image/emotions/emotions.png);
		background-size: 120px;
		float: left;
		height: 24px;
		margin-right: 5px;
		width: 24px;
		transition: all .2s ease-out;
		-webkit-transition: all .2s ease-out;
}
.em01{background-position: -24px 0;}
.em02{background-position: -48px 0;}
.em03{background-position: -72px 0;}
.em04{background-position: -96px 0;}
.em05{background-position: 0px -24px;}
.em06{background-position: -24px -24px;}
.em07{background-position: -48px -24px;}
.em08{background-position: -72px -24px;}
.em09{background-position: -96px -24px;}
.em10{background-position: 0px -48px;}
.em11{background-position: -24px -48px;}
.em12{background-position: -48px -48px;}
.em13{background-position: -72px -48px;}
.em14{background-position: -96px -48px;}

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
