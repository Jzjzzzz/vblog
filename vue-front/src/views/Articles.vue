<template>
  <div class="articles">
    <!-- 头部内容 开始 -->
    <banner></banner>
    <!-- 头部内容 结束 -->
    <!-- 正文 开始 -->
    <el-row :gutter="20">
      <div id="content">
        <el-col :span="12" :offset="5">
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
    <!-- 正文 结束 -->

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
      article: {}
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

