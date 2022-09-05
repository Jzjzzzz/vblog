<template>
  <div class="content-detail-end row">
    <div class="col-sm-12 col-lg-12 article-tags">
      <div class="detail-tags-title">ARTICLE TAGS</div>
      <div class="detail-tags-body">
        <a v-for="tagItem in tagList" class="tags-detail" :href="'/blog/'+tagItem.tagName">{{tagItem.tagName}}</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    tagList: {
      type: Array,
      require: true
    }
  },
  name: 'article-tags',
  data () {
    return {
    }
  },
  created () {
    this.init();
  },
  methods: {
    init () {
      this.assert();
    },
    assert () {
      if (!this.tagList) {
        throw new Error('必须传入具体的tag标签数组，数组格式[{tagName:"标签名",tagId:"标签id可以不传"}]');
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.detail-tags-title{
  padding: 10px;
  font-weight: 700;
  color: #d3d3d3;
}
.tags-detail{
  display: inline-block;
  padding-left: 10px;
  padding-right: 10px;
  line-height: 24px;
  font-size: 14px;
  color: #bfbfbf;
  border: 1px solid #bfbfbf;
  border-radius: 999em;
  margin: 0 6px;
  margin-bottom: 6px;
}
.tags-detail:hover{
  color: #fff;
  background-color: #696969;
}
</style>
