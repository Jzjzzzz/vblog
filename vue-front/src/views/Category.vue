<template>
  <div class="home">
    <banner isHome="true"></banner>
    <div class="site-content animate">
      <!--通知栏-->
      <div class="notify">
        <div class="search-result">
          <p style="padding-bottom: 15px">文章分类</p>
          <button @click="queryList(dict.value)" class="btn" style="margin: 5px"
                  v-for="dict in dict.type.sys_article_type"> {{ dict.label }}
          </button>
        </div>
      </div>
      <!--文章列表-->
      <main class="site-main">
        <template v-for="item in postList">
          <post :post="item" :key="item.id"></post>
        </template>
      </main>
      <!--加载更多-->
      <div class="more" v-show="hasNextPage">
        <div class="more-btn" @click="loadMore">更多</div>
      </div>
    </div>
  </div>
</template>

<script>
import Banner from '@/components/banner'
import Feature from '@/components/feature'
import Post from '@/components/post'
import SmallIco from '@/components/small-ico'
import Quote from '@/components/quote'
import {fetchList} from '../api/article'

export default {
  name: 'Home',
  dicts: ['sys_article_type'],
  data() {
    return {
      postList: [],
      hasNextPage: false,
      articleQuery: {
        type: '',
        currPage: 1,
        limit: 1
      }
    }
  },
  components: {
    Banner,
    Feature,
    Post,
    SmallIco,
    Quote
  },
  computed: {},
  methods: {
    queryList(type) {
      this.articleQuery.currPage = 1
      this.articleQuery.type = type
      this.fetchList()
    },
    fetchList() {
      fetchList(this.articleQuery).then(res => {
        this.postList = res.data.map.items || []
        this.articleQuery.currPage = res.data.map.currPage
        this.hasNextPage = res.data.map.hasNextPage
        this.articleQuery.type = res.data.map.type
      }).catch(err => {
        console.log(err)
      })
    },
    loadMore() {
      this.articleQuery.currPage = this.articleQuery.currPage + 1
      fetchList(this.articleQuery).then(res => {
        this.postList = this.postList.concat(res.data.map.items || [])
        this.articleQuery.currPage = res.data.map.currPage
        this.hasNextPage = res.data.map.hasNextPage
        this.articleQuery.type = res.data.map.type
      })
    }
  },
  mounted() {
    this.fetchList();
  }
}
</script>
<style scoped lang="less">
.site-content {
  .notify {
    margin: 60px 0;
    border-radius: 3px;

    & > div {
      padding: 20px;
    }
  }

  .search-result {
    padding: 15px 20px;
    text-align: center;
    font-size: 22px;
    font-weight: 400;
    color: white;
    background-image: url("../assets/img/bg.jpg");
  }
}

.top-feature {
  width: 100%;
  height: auto;
  margin-top: 30px;

  .feature-content {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
    position: relative;

    .feature-item {
      width: 32.9%;
    }
  }
}

.site-main {
  padding-top: 80px;

  &.search {
    padding-top: 0;
  }
}

.more {
  margin: 50px 0;

  .more-btn {
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    color: #ADADAD;
    border: 1px solid #ADADAD;
    border-radius: 20px;
    margin: 0 auto;
    cursor: pointer;

    &:hover {
      color: #8fd0cc;
      border: 1px dashed #8fd0cc;
    }
  }
}

/******/
@media (max-width: 800px) {
  .top-feature {
    display: none;
  }

  .site-main {
    padding-top: 40px;
  }

  .site-content {
    .search-result {
      margin-bottom: 20px;
      font-size: 16px;
    }
  }
}

//button样式
/* From uiverse.io by @adamgiebl */
/* From uiverse.io */
.btn {
  position: relative;
  font-size: 12px;
  text-transform: uppercase;
  text-decoration: none;
  padding: 1em 2.5em;
  display: inline-block;
  border-radius: 6em;
  transition: all .2s;
  border: none;
  font-family: inherit;
  font-weight: 500;
  color: black;
  background-color: white;
}

.btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.btn:active {
  transform: translateY(-1px);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.btn::after {
  content: "";
  display: inline-block;
  height: 100%;
  width: 100%;
  border-radius: 100px;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  transition: all .4s;
}

.btn::after {
  background-color: #fff;
}

.btn:hover::after {
  transform: scaleX(1.4) scaleY(1.6);
  opacity: 0;
}

/******/
</style>
