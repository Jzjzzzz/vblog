<template>
  <div id="view-body" class="view-body view-body-blog">
    <div class="row">
      <div class="col-md-12 col-sm-12 ">
        <div>
          <div class="moreTag" @click="tagShowFlag=!tagShowFlag">
            <img :class="tagShowFlag?'moreTagTransform':''" src="../../assets/image/icon/moreTag.svg">
            {{ tagShowFlag ? '收起标签墙' : '展开标签墙' }}
          </div>
          <transition name="slide-more-tag">
            <div v-show="tagShowFlag" class="tag-wall shadow">
              <div class="tag-wall-head">标签墙</div>
              <div class="tag-wall-body">
                <a v-for="tagItem in tagList" class="tag"
                   @click="clickTag(tagItem.dictValue)">{{ tagItem.dictLabel }}</a>
              </div>
            </div>
          </transition>
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
  name: 'blog',
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
        tagId: undefined
      },
      totalPage: 0,
      tagShowFlag: false,
      tagList: [],
      rowItem: []
    }
  },
  created() {
    this.init();
  },
  watch:{
    $route(to, from) {
      if (from.name !== 'blog-detail'){
        this.init();
      }
    }
  },
  methods: {
    clickTag(tagId) {
      this.query.currPage = 1
      this.query.limit = 5
      this.query.tagId = tagId
      this.init()
    },
    init() {
      if (this.$route.query.tagId != null) {
        this.query.tagId = this.$route.query.tagId
      }
      fetchList(this.query).then(res => {
        this.totalPage = res.data.map.totalPage
        this.rowItem = res.data.map.items
        this.tagList = res.data.map.tagList
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
.moreTag {
  color: #555;
  text-align: center;
  margin-bottom: 20px;
  margin-top: 10px;
  cursor: pointer;
}

.moreTag img {
  width: 30px;
  transition: all;
  -webkit-transition: all .4s;
  -moz-transition: all .4s;
}

.tag-wall {
  background-color: #fff;
  padding: 10px 0;
  margin-bottom: 10px;
}

.tag-wall-head {
  padding-left: 10px;
  font-size: 18px;
  margin-bottom: 10px;
  border-left: 4px solid #2298d1;
}

.row-content {
  margin-bottom: 6px;
}

</style>
