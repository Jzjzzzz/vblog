<template>
  <div id="view-body" class="view-body view-body-blog">
    <div class="row">
      <div class="col-md-12 col-sm-12 ">
        <div>
          <h2 style="text-align:center;padding-bottom: 30px">{{ this.$route.query.summaryName }}</h2>
        </div>
        <div class="row-content">
          <article-item :article-list="rowItem"></article-item>
          <article-page :current-page="query.currPage" :page-all="totalPage"
                        v-on:pageprocss="pageProcss"></article-page>
        </div>
      </div>
    </div>
    <blog-foot></blog-foot>
  </div>
</template>

<script>
import blogFoot from '@/views/components/blog-foot';
import articleItem from '@/views/components/article-item';
import articlePage from '@/views/components/article-page';
import {fetchList} from '@/api/article';

export default {
  name: 'list',
  components: {
    blogFoot: blogFoot,
    articleItem: articleItem,
    articlePage: articlePage
  },
  data() {
    return {
      query: {
        currPage: 1, // 当前页
        limit: 5, // 总页数
        summaryId: undefined
      },
      totalPage: 0,
      rowItem: []
    }
  },
  watch:{
    $route() {
      if (this.$route.query.refresh === 'true'){
        this.init();
      }
    }
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      if (this.$route.query.summaryId != null) {
        this.query.summaryId = this.$route.query.summaryId
      }
      fetchList(this.query).then(res => {
        this.totalPage = res.data.map.totalPage
        this.rowItem = res.data.map.items
      })
    },
    pageProcss(data) {
      this.query.currPage = data.current
      this.init();
      if (data.type == null) {
        alert(data.message);
      }
    }
  }
}
</script>

<style scoped>
.moreTag img {
  width: 30px;
  transition: all;
  -webkit-transition: all .4s;
  -moz-transition: all .4s;
}

.row-content {
  margin-bottom: 6px;
}
</style>
