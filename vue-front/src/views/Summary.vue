<template>
  <div class="home">
    <banner isHome="true"></banner>
    <div class="site-content">
      <!--焦点图-->
      <div class="top-feature">
        <section-title>
          <div style="display: flex;align-items: flex-end;">归档</div>
        </section-title>
        <el-row :gutter="20">
          <el-col :span="8" v-for="item in features" :key="item.title">
            <div class="feature-item feature-content">
              <Feature :data="item"></Feature>
            </div>
          </el-col>
        </el-row>
      </div>
      <!--加载更多-->
      <div class="more">
        <div v-show="summaryQuery.currPage!=1" class="more-btn" @click="fetchList(1)">上一页</div>
        <div v-show="hasNextPage" class="more-btn" @click="fetchList(2)">下一页</div>
      </div>
    </div>
  </div>
</template>

<script>
import sectionTitle from '@/components/section-title'
import Banner from '@/components/banner'
import Feature from '@/components/feature'
import {fetchList} from '@/api/summary'

export default {
  name: 'Summary',
  data() {
    return {
      summaryQuery: {
        currPage: 1,
        limit: 6
      },
      features: [],
      hasNextPage: false
    }
  },
  components: {
    Banner,
    Feature,
    sectionTitle
  },
  methods: {
    fetchList(type) {
      if (type === 1) this.summaryQuery.currPage = this.summaryQuery.currPage - 1
      if (type === 2) this.summaryQuery.currPage = this.summaryQuery.currPage + 1
      fetchList(this.summaryQuery).then(res => {
        this.features = res.data.map.items || []
        this.hasNextPage = res.data.map.hasNextPage
      })
    }
  },
  mounted() {
    this.fetchList(0);
  }
}
</script>
<style scoped lang="less">
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

.more {
  padding: 45px 0;
  display: flex;
  justify-content: center;

  .more-btn {
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    color: #ADADAD;
    border: 1px solid #ADADAD;
    border-radius: 20px;
    cursor: pointer;
    flex-shrink: 0;
    margin: 5px;

    &:hover {
      color: #8fd0cc;
      border: 1px dashed #8fd0cc;
    }
  }
}
</style>
