<template>
  <div class="article-next-pre">
    <ul>
      <li class="apre">
        <router-link :to="preNext[0].route">
          <font class="title-font">← PREVIOUS</font><br /><span>{{preNext[0].name}}</span>
        </router-link>
      </li>
      <li class="anext">
        <router-link :to="preNext[1].route">
          <font class="title-font">NEXT →</font><br /><span>{{preNext[1].name}}</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    preNext: {
      type: Array,
      require: true
    }
  },
  name: 'article-next-pre',
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.article-next-pre{
  margin-bottom: 36px;
}
.article-next-pre ul{
  margin: 20px 0 0;
  padding: 0;
  line-height: 28px;
  display: block;
  text-align: center;
  list-style: none;
  zoom: 1;
}
.article-next-pre ul::before{
  display: table;
  content: " ";
}
.article-next-pre ul::after{
  clear: both;
  display: block;
  content: "";
  height: 0;
  overflow: hidden;
}
.article-next-pre li{
  display: inline;
  border: 1px solid #eee;
}
.apre{
  float: left;
  width: 48%;
}
.anext{
  float: right;
  width: 48%;
}
.article-next-pre ul li a{
  display: block;
  padding: 10px;
  font-size: 16px;
  font-weight: 700;
  color: #555;
}
.anext span, .apre span{
  transition-duration: .3s;
  color: #a9a9a9;
  font-size: 14px;
  font-weight: 400;
  letter-spacing: .7px;
}
.article-next-pre .title-font{
  font-family: -apple-system,BlinkMacSystemFont,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
}
.article-next-pre a:hover,
.article-next-pre li:hover{
  color: #fff;
  background-color: #696969;
}
.article-next-pre li:hover span{
  color: #fff
}
</style>
