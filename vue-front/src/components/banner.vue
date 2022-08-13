<template>
  <div id="banner" :class="{'home-banner':isHome}">
    <template v-if="!isHome">
      <div class="banner-img" :style="{'background-image': `url(${config.articleBanner})`}">
        <!--博主信息-->
        <div class="focusinfo">
          <!-- 头像 -->
          <div class="header-tou">
            <h1 style="font-size: 50px;padding-top: 50px;color: white;word-wrap:break-word;word-break:normal;">{{
                article.articleTitle
              }}</h1>
          </div>
          <!-- 简介 -->
          <div class="header-info">
            <el-row>
              <el-col :span="7">
                <div>文章类别:</div>
              </el-col>
              <el-col :span="17">
                <dict-tag :options="dict.type.sys_article_type" :value="article.articleType"/>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="7">
                <div>文章标签:</div>
              </el-col>
              <el-col :span="17"><p>{{ article.articleTag }}</p></el-col>
            </el-row>
            <el-row>
              <el-col :span="7">
                <div>是否原创:</div>
              </el-col>
              <el-col :span="17">
                <dict-tag :options="dict.type.sys_article_origin" :value="article.originStatus"/>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="7">
                <div>创建时间:</div>
              </el-col>
              <el-col :span="17"><p>{{ article.updateTime }}</p></el-col>
            </el-row>
          </div>
        </div>
        <!--左右倾斜-->
        <div class="slant-left"></div>
        <div class="slant-right"></div>
      </div>
    </template>
    <template v-if="isHome">
      <div class="banner-img" :style="{'background-image': `url(${config.homeBanner})`}">
        <!--博主信息-->
        <div class="focusinfo">
          <!-- 头像 -->
          <div class="header-tou">
            <router-link to="/"><img :src="config.webAvatar"></router-link>
          </div>
          <!-- 简介 -->
          <div class="header-info">
            <p v-html="config.webDetails"></p>
          </div>
          <!-- 社交信息 -->
          <div class="top-social">
            <div key="1" title="github">
              <a :href="config.github" target="_blank">
                <i class="iconfont icongithub"></i>
              </a>
            </div>
            <div key="2" title="gitee">
              <a :href="config.gitee" target="_blank" style="color: #d81e06">
                <i class="iconfont icongitee"></i>
              </a>
            </div>
            <div key="3" title="qq">
              <a :href="config.qq" target="_blank" style="color: #1AB6FF">
                <i class="iconfont iconqq"></i>
              </a>
            </div>
          </div>
        </div>
        <!--左右倾斜-->
        <div class="slant-left"></div>
        <div class="slant-right"></div>
      </div>
    </template>

  </div>
</template>

<script>
export default {
  name: "banner",
  dicts: ['sys_article_type', 'sys_article_origin'],
  data() {
    return {
      websiteInfo: {},
      config: {
        webAvatar: '',
        webDetails: '',
        qq: '',
        wechat: '',
        github: '',
        gitee: '',
        homeBanner: '',
        articleBanner: ''
      }
    }
  },
  props: {
    isHome: {
      type: [Boolean, String],
      default: false
    },
    article: {
      type: Object
    }
  },
  watch: {
    msg(newMsg, oldMsg) {
      this.article = newMsg
    }
  },
  created() {
    this.getWebConfig()
  },
  methods: {
    //获取网站基本信息
    getWebConfig() {
      this.getInformation().then(response => {
        this.config = response.data;
        this.config.qq = 'https://wpa.qq.com/msgrd?v=3&uin=' + this.config.qq + '&site=qq&menu=yes'
      });
    }
  }
}
</script>

<style scoped lang="less">

#banner {
  position: relative;
  margin-top: 80px;
  width: 100%;
  height: 500px;

  .banner-img {
    width: inherit;
    height: inherit;
    background-position: center;
    background-size: cover;
    background-repeat: no-repeat;
    transition: all 0.2s linear;
    overflow: hidden;

    &:hover {
      transform: scale(1.1, 1.1);
      filter: contrast(130%);
    }
  }

  &.home-banner {
    height: 550px;

    .banner-img {
      background-position: center center;
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-size: cover;
      z-index: -1;
      transition: unset;

      &:hover {
        transform: unset;
        filter: unset;
      }
    }

    .slant-left {
      content: '';
      position: absolute;
      width: 0;
      height: 0;
      border-bottom: 100px solid #FFF;
      border-right: 800px solid transparent;
      left: 0;
      bottom: 0;
    }

    .slant-right {
      content: '';
      position: absolute;
      width: 0;
      height: 0;
      border-bottom: 100px solid #FFF;
      border-left: 800px solid transparent;
      right: 0;
      bottom: 0;
    }
  }
}

.focusinfo {
  position: relative;
  max-width: 800px;
  padding: 0 10px;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  -webkit-transform: translate(-50%, -50%);
  text-align: center;

  img {
    width: 80px;
    height: auto;
    border-radius: 50%;
    border: 3px solid rgba(255, 255, 255, 0.3);
  }

  .header-info {
    width: 60%;
    font-size: 14px;
    color: #EAEADF;
    background: rgba(0, 0, 0, 0.66);
    padding: 20px 30px;
    margin: 30px auto 0 auto;
    font-family: miranafont, "Hiragino Sans GB", STXihei, "Microsoft YaHei", SimSun, sans-serif;
    letter-spacing: 1px;
    line-height: 30px;
  }

  .top-social {
    height: 32px;
    margin-top: 30px;
    margin-left: 10px;
    list-style: none;
    display: inline-block;
    font-family: miranafont, "Hiragino Sans GB", STXihei, "Microsoft YaHei", SimSun, sans-serif;

    div {
      float: left;
      margin-right: 10px;
      height: 32px;
      line-height: 32px;
      text-align: center;
      width: 32px;
      background: white;
      border-radius: 100%;
    }
  }
}

@media (max-width: 960px) {
  #banner {
    height: 400px;
  }
}

@media (max-width: 800px) {
  #banner {
    display: none;
  }
}
</style>
