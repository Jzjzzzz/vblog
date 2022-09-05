<template>
  <div class="share-icon">
    <span class="detail-signture">{{signatureAuthor}}</span>
    <span class="detail-share-span">
      分享到
      <a style="background-color:#ed143d"  href="javascript:;">
        <i style="color:#ffffff;" class="fa fa-weibo" aria-hidden="true"></i>
      </a>
      <a style="background-color:#00bb29" id="onMenuShareTimeline" href="javascript:;">
        <i style="color:#ffffff;" class="fa fa-weixin" aria-hidden="true"></i>
      </a>
    </span>
  </div>
</template>

<script>
export default {
  props: {
    signatureAuthor: {
      type: String,
      require: true
    }
  },
  name: 'article-share',
  data () {
    return {
    }
  },
  created () {

  },
  methods: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.share-icon{
  display: flex;
  overflow: hidden;
  padding: 0 26px;
  height: 32px;
}
.share-icon span{
  line-height: 28px;
  margin-right: 5px;
}
.detail-signture{
  font-style: italic;
  font-weight: 300;
  float: left;
  color: #bfbfbf;
  max-width: 50%;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.detail-share-span{
  display: flex;
  color: #bfbfbf;
  flex: 1;
  justify-content: flex-end;
}
.share-icon i{
  padding: 6px;
}
.share-icon a{
  float: right;
  margin-left: 10px;
  font-size: 20px;
  border-radius: 50px;
}
@media (max-width: 767px){
  .share-icon {
    padding: 0 10px;
  }
}
</style>
