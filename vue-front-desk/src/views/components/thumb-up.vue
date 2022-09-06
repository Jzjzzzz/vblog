<template>
  <a class="thumbUpAnimate thumb-up-btn" :class="isThumbsUp?'thumbUpAlready':''" @click="setArticleThumbUpById()" href="javascript:;">
    <transition mode="out-in" name="bounce">
      <i key="up" v-if="isThumbsUp" style="font-size:18px;" class="fa fa-thumbs-up" aria-hidden="true"></i>
      <i key="down" v-else style="font-size:18px;" class="fa fa-thumbs-o-up" aria-hidden="true"></i>
    </transition>
    {{isThumbsUp?'已点赞':''}}&nbsp;&nbsp;{{count}}
  </a>
</template>
<script>
import { getByIdLike } from '@/api/article';

export default {
  props: {
    thumbsCount: {
      type: Number,
      require: true
    },
    thumbsUpFlag: {
      type: Boolean,
      require: true
    }
  },
  name: 'thumb-up',
  data () {
    return {
      count: this.thumbsCount,
      isThumbsUp: this.thumbsUpFlag
    }
  },
  methods: {
    setArticleThumbUpById () {
      getByIdLike(this.$route.params.articleId).then(res => {
        this.$message('点赞成功');
      })
      this.isThumbsUp = !this.isThumbsUp;
      this.isThumbsUp ? this.count++
      : this.count--;
      this.$emit('thumbup', {
        count: this.count,
        isThumbsUp: this.isThumbsUp
      })
    }
  },
  watch:{
    thumbsCount(val){
      this.count = val
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.thumb-up-btn{
  display: inline-block;
  color: #de686d;
  border: 1px solid #de686d;
  line-height: 24px;
  padding: 6px 20px;
  font-size: 14px;
  border-radius: 20px;
  animation: all .8s ease-in-out!important;
  -webkit-animation: all .8s ease-in-out!important;
  -ms-animation: all .8s ease-in-out!important;
}
</style>
