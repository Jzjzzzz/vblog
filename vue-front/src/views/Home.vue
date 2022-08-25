<template>
  <div class="home">
    <banner isHome="true"></banner>
    <div class="site-content animate">
      <!--通知栏-->
      <div class="notify">
        <quote>{{ notice }}</quote>
      </div>
      <!--焦点图-->
      <div class="top-feature">
        <section-title>
          <div style="display: flex;align-items: flex-end;">归档
            <small-ico></small-ico>
          </div>
        </section-title>
        <div class="feature-content">
          <div class="feature-item" v-for="item in features" :key="item.title">
            <Feature :data="item"></Feature>
          </div>
        </div>
      </div>
      <!--文章列表-->
      <main class="site-main">
        <section-title>推荐</section-title>
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
import sectionTitle from '@/components/section-title'
import Post from '@/components/post'
import SmallIco from '@/components/small-ico'
import Quote from '@/components/quote'
import {summaryTop} from '@/api/summary'
import {fetchList} from '@/api/article'

export default {
  name: 'Home',
  data() {
    return {
      articleQuery: {
        type: '',
        aggregateId: '',
        tag: undefined,
        title: '',
        currPage: 1,
        limit: 4
      },
      features: [
        {
          id: 1,
          name: 'Jzj',
          banner: 'https://s1.ax1x.com/2020/05/14/YDfRnU.jpg'
        },
        {
          id: 2,
          name: '使用说明',
          banner: 'https://s1.ax1x.com/2020/05/14/YDf4AJ.jpg'
        },
        {
          id: 3,
          name: '文章归档',
          banner: 'https://s1.ax1x.com/2020/05/14/YDfT91.jpg'
        }
      ],
      postList: [],
      hasNextPage: false
    }
  },
  components: {
    Banner,
    Feature,
    sectionTitle,
    Post,
    SmallIco,
    Quote
  },
  computed: {
    notice() {
      return '凡作事，将成功之时，其困难最甚。行百里者半九十，有志当世之务者，不可不戒，不可不勉。'
    }
  },
  methods: {
    summaryTop() {
      summaryTop().then(res => {
        this.features = res.data || []
      })
    },
    fetchList() {
      fetchList(this.articleQuery).then(res => {
        this.postList = res.data.map.items || []
        this.hasNextPage = res.data.map.hasNextPage
      })
    },
    loadMore() {
      this.articleQuery.currPage = this.articleQuery.currPage + 1
      fetchList(this.articleQuery).then(res => {
        this.postList = this.postList.concat(res.data.map.items || [])
        this.hasNextPage = res.data.map.hasNextPage
      })
    }
  },
  created() {
    this.summaryTop();
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
    .notify {
      margin: 30px 0 0 0;
    }
  }
}

/******/
</style>
