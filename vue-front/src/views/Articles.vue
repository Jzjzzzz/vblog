<template>
  <div class="articles">
    <!-- 头部内容 开始 -->
    <banner :article="article"></banner>
    <!-- 头部内容 结束 -->
    <!-- 正文 开始 -->
    <div style="padding-top: 40px">
      <el-row :gutter="20">
        <div id="content">
          <el-col :span="14" :offset="5">
            <div >
              <mavon-editor
                  :value="article.content"
                  :subfield="prop.subfield"
                  :defaultOpen="prop.defaultOpen"
                  :toolbarsFlag="prop.toolbarsFlag"
                  :editable="prop.editable"
                  :scrollStyle="prop.scrollStyle"
                  :externalLink="externalLink"
              ></mavon-editor>
            </div>
          </el-col>
        </div>
        <!-- 文章目录 -->
        <el-col :span="2" :offset="1">
        </el-col>
      </el-row>
    </div>
    <!-- 正文 结束 -->
    <!-- 文章底部 开始 -->
    <div class="site-content animate">
      <main class="site-main">
        <article class="hentry">
          <section-title>
            <footer class="post-footer">
              <!-- 阅读次数 -->
              <div class="post-like">
                <i class="iconfont iconeyes"></i>
                <span class="count">685</span>
              </div>
              <div class="donate" @click="showDonate=!showDonate">
                <span>赞</span>
                <ul class="donate_inner" :class="{'show':showDonate}">
                  <li class="wedonate"><img src="http://cdn.fengziy.cn/gblog/wexin_pay.png"><p>微信</p></li>
                  <li class="alidonate"><img src="http://cdn.fengziy.cn/gblog/ali_pay.jpg"><p>支付宝</p></li>
                </ul>
              </div>
              <!-- 文章标签 -->
              <div class="post-tags">
                <i class="iconfont iconcategory"></i>
                <router-link to="/category/web">更多</router-link>
              </div>
            </footer>
          </section-title>
          <!--声明-->
          <div class="open-message">
            <p>声明：Vblog博客|版权所有，违者必究|如未注明，均为原创|本网站采用<a href="https://creativecommons.org/licenses/by-nc-sa/3.0/" target="_blank">BY-NC-SA</a>协议进行授权</p>
            <p>转载：转载请注明原文链接 - <a href="/">{{ article.articleTitle }}</a></p>
          </div>
        </article>
      </main>
    </div>

    <!-- 文章底部 结束 -->
  </div>
</template>

<script>
import Banner from '@/components/banner'
import sectionTitle from '@/components/section-title'
import comment from '@/components/comment'
import menuTree from '@/components/menu-tree'

import {getById} from '../api/article'

export default {
  name: 'articles',
  data() {
    return {
      showDonate: false,
      comments: [],
      menus: [],
      codeStyle: '',
      //需要配置的内容：
      externalLink: {
        markdown_css: function () {
          // 这是你的markdown css文件路径
          return '/mavon-editor/markdown/github-markdown.min.css';
        },
        hljs_js: function () {
          // 这是你的hljs文件路径
          return '/mavon-editor/highlightjs/highlight.min.js';
        },
        hljs_css: function (css) {
          // 这是你的代码高亮配色文件路径
          return '/mavon-editor/highlightjs/styles/' + css + '.min.css';
        },
        hljs_lang: function (lang) {
          // 这是你的代码高亮语言解析路径
          return '/mavon-editor/highlightjs/languages/' + lang + '.min.js';
        },
        katex_css: function () {
          // 这是你的katex配色方案路径路径
          return '/mavon-editor/katex/katex.min.css';
        },
        katex_js: function () {
          // 这是你的katex.js路径
          return '/mavon-editor/katex/katex.min.js';
        },
      },
      article: {
        content: undefined,
        updateTime: undefined,
        articleTitle: undefined,
        introduce: undefined,
        articleType: undefined,
        articleTag: undefined,
        originStatus: undefined,
        clickRate: undefined
      }
    }
  },
  components: {
    Banner,
    sectionTitle,
    comment,
    menuTree
  },
  methods: {
    getArticle() {
      getById(this.$route.params.id).then(res => {
        this.article = res.data
      })
    },
  },
  mounted() {
    let that = this;
    that.codeStyle = "darcula";
  },
  computed: {
    prop() {
      return {
        subfield: false, // 单双栏模式
        defaultOpen: "preview", //edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: false,
        toolbarsFlag: false,
        scrollStyle: true,
        navigation: true
      }
    }
  },
  created() {
    this.getArticle()
  }
}
</script>

<style scoped lang="less">
.site-content {
  position: relative;
  .site-main {
    padding: 20px 0 0 0;
  }
}

article.hentry {
  .entry-header {
    .entry-title {
      font-size: 23px;
      font-weight: 600;
      color: #737373;
      margin: 0.67em 0;

      &:before {
        content: "#";
        margin-right: 6px;
        color: #d82e16;
        font-size: 20px;
        font-weight: 600;
      }
    }

    hr {
      height: 1px;
      border: 0;
      background: #EFEFEF;
      margin: 15px 0;
    }

    .breadcrumbs {
      font-size: 14px;
      color: #D2D2D2;
      text-decoration: none;
      margin-bottom: 30px;
    }
  }

  .entry-content {}

  footer.post-footer {
    width: 100%;
    padding: 20px 10px;
    margin-top: 30px;
    height: 65px;
    position: relative;
    i{
      font-size: 18px;
      margin-right: 5px;
    }
    .post-like {
      float: right;
      margin: 7px 0 0 20px;
    }

    .post-share {
      float: right;
      list-style: none;
      margin-right: 20px;
    }

    .donate {
      float: left;
      line-height: 36px;
      border-radius: 100%;
      -webkit-border-radius: 100%;
      -moz-border-radius: 100%;
      border: 1px solid #2B2B2B;
      &:hover{
        border: 1px solid goldenrod;
        span{
          color: goldenrod;
        }
      }
      span {
        color: #2B2B2B;
        padding: 10px;
        position: relative;
        cursor: pointer;
      }

      .donate_inner {
        display: none;
        margin: 0;
        list-style: none;
        position: absolute;
        left: 80px;
        top: -40px;
        background: #FFF;
        padding: 10px;
        border: 1px solid #ddd;
        box-shadow: 0 2px 6px rgba(0, 0, 0, .08);
        border-radius: 3px;
        &.show{
          display: block;
        }
        li {
          float: left;
        }

        img {
          width: 100px;
        }
        p {
          text-align: center;
          font-size: 15px;
          color: #D2D2D2;
          line-height: 1rem;
        }
      }

      .donate_inner:after, .donate_inner:before {
        content: "";
        position: absolute;
        left: 0;
        bottom: 45%;
        margin-left: -8px;
        border-top: 8px solid transparent;
        border-bottom: 8px solid transparent;
        border-right: 8px solid #fff;
      }

      .donate_inner:before {
        left: -1px;
        border-right: 8px solid #ddd;
      }

    }

    .post-tags {
      margin: 7px 0 0 20px;
      float: left;
      text-transform: uppercase;
      a:hover{
        color: #ff6d6d;
      }
    }
  }
  .open-message {
    margin: 50px 0;
    position: relative;
    background: #2B2B2B;
    padding: 10px 30px;
    border-radius: 3px;
    font-size: 14px;
    color: #fff;
    &:after {
      content: "";
      border-left: 10px solid transparent;
      border-right: 10px solid transparent;
      border-bottom: 10px solid #2B2B2B;
      position: absolute;
      top: -8px;
      left: 48%;
    }
    p {
      margin: 10px 0;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    a {
      color: #A0DAD0;
      padding: 0 5px;
    }
  }
}
</style>
