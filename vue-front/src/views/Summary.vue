<template>
  <div class="home">
    <banner isHome="true"></banner>
    <div class="site-content animate">
      <!--焦点图-->
      <div class="top-feature">
        <section-title>
          <div style="display: flex;align-items: flex-end;">归档
            <small-ico></small-ico>
          </div>
        </section-title>
          <el-row :gutter="20" >
            <el-col :span="8" v-for="item in features" :key="item.title">
              <div class="feature-item feature-content"  >
                  <Feature :data="item"></Feature>
              </div>
            </el-col>
          </el-row>
      </div>
      <!--加载更多-->
      <div class="more" v-show="hasNextPage">
        <div class="more-btn" @click="loadMore">更多</div>
      </div>
    </div>
  </div>
</template>

<script>
import sectionTitle from '@/components/section-title'
import Banner from '@/components/banner'
import Feature from '@/components/feature'
import SmallIco from '@/components/small-ico'
import {fetchList} from  '@/api/summary'

export default {
  name: 'Summary',
  data() {
    return {
      summaryQuery: {
        currPage: 1,
        limit: 6
      },
      features: [
      ],
      hasNextPage: false
    }
  },
  components: {
    Banner,
    Feature,
    SmallIco,
    sectionTitle
  },
  computed: {
  },
  methods: {
    fetchList() {
      fetchList(this.summaryQuery).then(res => {
        this.features = res.data.map.items || []
        this.summaryQuery.currPage = res.data.map.currPage
        this.hasNextPage = res.data.map.hasNextPage
      }).catch(err => {
        console.log(err)
      })
    },
    loadMore() {
      this.summaryQuery.currPage = this.summaryQuery.currPage + 1

      fetchList(this.summaryQuery).then(res => {
        this.features = this.features.concat(res.data.map.items || [])
        this.summaryQuery.currPage = res.data.map.currPage
        this.hasNextPage = res.data.map.hasNextPage
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
