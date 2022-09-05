<template>
  <div>
    <div v-for="(item,$key) in articleList" class="content-item">
        <div class="rowitem-left">
            <img style="cursor:pointer;" @click="jump(item.id)" :src="item.photoUrl">
        </div>
        <div class="rowitem-right">
            <div class="rowitem-title">
                <router-link :to="'/detail/'+item.id">
                <h1 style="margin: 0px;"> {{item.title}}</h1>
                </router-link>

                  <router-link :to="'/detail/'+item.id"><p style="cursor:pointer;">{{item.summary}}</p></router-link>

            </div>
        </div>
        <div class="rowitem-foot">
            <span><i class="fa fa-clock-o" aria-hidden="true"></i> {{item.time.split(' ')[0]}}&nbsp;&nbsp;&nbsp; <i class="fa fa-user" aria-hidden="true"></i> {{item.author}}</span>
            <span class="article_taglist">
              <a :href="'/blog/'+taglistItem" v-for="taglistItem in articleTaglist[$key]" class="tag">{{taglistItem}}</a>
            </span>
            <span class="rowitem-foot-span">
                <a :style="item.thumbUpStatus?'color:#de686d':''" @click="thumbupClick(item.article,$key)" class="thumbup" href="javascript:;">
                    <transition mode="out-in" name="bounce"><i key="up" v-if="!item.thumbUpStatus" class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                        <i key="down" v-else style="color:#de686d" class="fa fa-thumbs-up" aria-hidden="true"></i>
                    </transition>
                    <transition mode="out-in" name="hideToggle">
                        <font key="tup" v-if="item.thumbUpStatus">{{item.thumbUp}}</font>
                        <font key="tdown" v-else>{{item.thumbUp}}</font>
                    </transition>
                </a>&nbsp;&nbsp;&nbsp;
                <a class="eyes" href="javascript:;"><i class="fa fa-eye" aria-hidden="true"></i> {{item.articleRead}}</a>&nbsp;&nbsp;&nbsp;
                <a class="comment" href="javascript:;"><i class="fa fa-comment-o" aria-hidden="true"></i> {{item.commentCount}}</a>
            </span>
        </div>
    </div>
    </div>
</template>

<script>
export default {
  props: ['articleList'],
  name: 'article-item',
  data () {
    return {
      articleTaglist: []
    }
  },
  created () {
    this.articleList.forEach(item => {
      let tagNameArr;
      item.tag ? tagNameArr = item.tag.split(',') : tagNameArr = [];
      this.articleTaglist.push(tagNameArr)
    });
  },
  methods: {
    thumbupClick: function (id, index) {
      this.$emit('thumbclick', {
        articleId: id,
        index: index
      });
    },
    jump: function (id) {
      window.open('/detail/' + id);
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content-item{
  margin-bottom: 15px;
  background-color: #fff;
  padding: 15px;
  border-left: 6px solid transparent;
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
  box-shadow: 0 0.1rem 0.2rem rgba(0,0,0,.05);
  -webkit-box-shadow: 0 0.1rem 0.2rem rgba(0,0,0,.05);
  -moz-box-shadow: 0 0.1rem 0.2rem rgba(0,0,0,.05);
  -o-box-shadow: 0 0.1rem 0.2rem rgba(0,0,0,.05);
}
.rowitem-left{
  display: inline-block;
  width: 30%;
}
.rowitem-left img{
  width: 100%;
  height: auto;
}
.rowitem-right{
  display: inline-block;
  width: 66.6%;
  float: right;
}
.rowitem-right p, .rowitem-right p *{
  word-break: break-all;
  color: #888;
}
.rowitem-foot{
  padding: 10px 0;
  line-height: 28px;
}
.rowitem-foot span:first-child{
  color: #9d9d9d;
}
.article_taglist{
  margin-left: 30px;
}
.rowitem-foot-span{
  float: right;
}
.rowitem-title a h1{
  font-size: 24px;
  margin-bottom: 10px;
  line-height: unset;
}
.content-item:hover{
  transform: scale(1.05, 1.05);
  -webkit-transform: scale(1.05, 1.05);
  -moz-transform: scale(1.05, 1.05);
  -ms-transform: scale(1.05, 1.05);
  border-left-color: #337ab7;
  box-shadow: 10px 20px 80px rgba(0, 0, 0, 0.20);
  -webkit-box-shadow: 10px 20px 80px rgba(0, 0, 0, 0.20);
  -moz-box-shadow: 10px 20px 80px rgba(0, 0, 0, 0.20);
  -ms-box-shadow: 10px 20px 80px rgba(0, 0, 0, 0.20);
}
.comment,
.comment i,
.eyes,
.eyes i,
.thumbup,
.thumbup i{
  color: #555;
}
@media (max-width: 540px){
  .rowitem-title a h1 {
    font-size: 18px;
    margin: 0;
    text-overflow: ellipsis;
    overflow: hidden;
    -webkit-line-clamp: 2;
    word-break: break-all;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
  .rowitem-title p{
    display: none;
  }
  .content-item .tag{
    display: none;
  }
  .rowitem-foot span:first-child,
  .rowitem-foot-span {
    font-size: 12px;
  }
}

@media (min-width: 768px){
  .rowitem-right p {
    margin-top: 20px;
  }
}

@media (max-width: 767px) {
  .rowitem-foot{
    line-height: 20px;
  }
}

@media (max-width: 1024px) and (min-width: 768px) {
  .rowitem-right p{
    margin-top: 20px;
    font-size: 14px;
    margin: 0;
    text-overflow: ellipsis;
    overflow: hidden;
    -webkit-line-clamp: 2;
    word-break: break-all;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
  .rowitem-right a h1{
    font-size: 20px;
  }
  .content-item .tag{
    display: none;
  }
}
@media (max-width: 768px) and (min-width: 540px) {
  .rowitem-right p{
    margin-top: 20px;
    font-size: 14px;
    margin: 0;
    text-overflow: ellipsis;
    overflow: hidden;
    -webkit-line-clamp: 1;
    word-break: break-all;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
}
</style>
