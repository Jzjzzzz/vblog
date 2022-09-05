<template>
  <div class="comment-list-body">
    <comment-list :comment-list="commentList"></comment-list>
    <p v-show="!isLoading && !isFinish" class="falls-item"><i class="fa fa-angle-double-down animate-movedown fa-2x fa-fw margin-bottom"></i> 下滑更多留言</p>
    <p v-show="isLoading" class="falls-item"><i class="fa fa-spinner fa-spin fa-3x fa-fw margin-bottom"></i> 刷新中</p>
    <div style="width: 100%;text-align: center;padding-top: 20px;" v-show="isFinish">
      <h5><img src="../../assets/image/icon/cry.svg"> &nbsp;&nbsp;没有更多的留言啦 </h5>
    </div>
  </div>
</template>

<script>
import {scrollToBottom} from '@/utils/utils';
import commentList from './comment-list'

export default {
  props: ['commentList', 'waterfallData'],
  name: 'commentWall',
  components: {
    commentList: commentList
  },
  data () {
    return {
      isLoading: false,
      isFinish: false
    }
  },
  watch: {
    waterfallData (newValue, oldValue) {
      console.log(newValue);
      console.log(oldValue);
      this.isLoading = newValue.isLoading;
      this.isFinish = newValue.isFinish && true;
    }
  },
  mounted: function () {
    window.addEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll: function (e) {
      const _self = this;
      scrollToBottom.onScrollEvent(function () {
        if (!_self.isFinish && !_self.isLoading) {
          console.log('到底了');
          _self.isLoading = true;
          _self.waterFall(_self.isLoading);
        }
      });
    },
    waterFall: function (isLoading) {
      let _self = this;
      this.$emit('waterfall', {
        isLoading: isLoading,
        isFinish: _self.isFinish
      });
    }
  },
  beforeDestroy () {
    console.log('瀑布流组件开始销毁');
    window.removeEventListener('scroll', this.handleScroll);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
