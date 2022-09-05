<template>
  <div class="content-detail-end shadow">
    <div class="detail-left-head">精选文章</div>
    <div class="detail-left-body">
      <div class="row">
        <div v-for="item in mostView" class="col-sm-4 col-md-4 col-lg-4">
          <div class="detail-mostviewed">
            <img :src="item.photo" >
            <div class="caption">
              <a :href="'/detail/'+item.articleId"><h5>{{item.title}}</h5></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    mostView: {
      type: Array,
      require: true
    }
  },
  name: 'article-most-view',
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
      if (!this.mostView) {
        throw new Error('必须传入具体的文章列表数组，数组格式[{tagName:"标签名",tagId:"标签id可以不传"}]');
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.detail-left-head{
  padding-left: 10px;
  font-size: 18px;
  margin-bottom: 10px;
  border-left: 4px solid #2298d1;
}
.detail-left-body{
  padding: 0 10px;
}
.content-detail-end{
  padding-left: 0px;
  padding-right: 0px;
}
.detail-mostviewed{
  display: block;
  overflow: hidden;
  border: 1px solid #eee;
  margin-bottom: 20px;
  line-height: 1.42857143;
  background-color: #fafafa;
  -webkit-transition: border .2s ease-in-out;
  -o-transition: border .2s ease-in-out;
  transition: border .2s ease-in-out;
}
.detail-mostviewed > img{
  width: 100%;
  height: auto;
  margin-right: auto;
  margin-left: auto;
  min-height: 80px;
}
.detail-mostviewed .caption{
  overflow-y: hidden;
  padding: 9px;
  color: #333;
}
.detail-mostviewed a{
  display: block;
  margin-bottom: 10px;
}
.detail-mostviewed img:hover{
  transform: scale(1.1,1.1);
  -webkit-tranform: scale(1.1,1.1);
  -moz-tranform: scale(1.1,1.1);
  -ms-tranform: scale(1.1,1.1);
  transition: transform .8s;
  -webkit-transition: -webkit-transform .8s;
  -moz-tranform: scale(1.1,1.1);
  -ms-tranform: scale(1.1,1.1);
}
@media (max-width: 767px){
  .detail-mostviewed > img {
      min-height: 160px;
  }
}

</style>
