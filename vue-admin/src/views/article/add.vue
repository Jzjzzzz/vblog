<template>
  <div class="app-container">

    <el-steps :active="active" finish-status="success">
      <el-step title="基础信息" />
      <el-step title="内容编辑" />
    </el-steps>
    <div class="grid-content bg-purple">
      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12" :offset="6">
            <div v-show="active === 0">
              <el-form-item label="标题" prop="articleTitle">
                <el-input
                  v-model="form.articleTitle"
                  placeholder="请输入标题"
                  :maxlength="100"
                  clearable
                  :style="{width: '100%'}"
                />
              </el-form-item>
              <el-form-item label="状态" prop="status">
                <el-select v-model="form.status" placeholder="请选择状态" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="dict in dict.type.sys_article_status"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="分类" prop="articleType">
                <el-select v-model="form.articleType" placeholder="请选择分类" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="dict in dict.type.sys_article_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="标签" prop="articleTagArray">
                <el-select
                  v-model="form.articleTagArray"
                  placeholder="请选择标签"
                  multiple
                  clearable
                  :style="{width: '100%'}"
                  @change="$forceUpdate()"
                >
                  <el-option
                    v-for="dict in dict.type.sys_article_tag"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="置顶" prop="topStatus">
                <el-select v-model="form.topStatus" placeholder="请选择置顶" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="dict in dict.type.sys_article_top"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="原创" prop="originStatus">
                <el-select v-model="form.originStatus" placeholder="请选择原创" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="dict in dict.type.sys_article_origin"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="归档">
                <el-select v-model="form.aggregateId" placeholder="请选择归档" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="summary in summaryList"
                    :key="summary.id"
                    :label="summary.name"
                    :value="summary.id"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="评论" prop="commentStatus">
                <el-select v-model="form.commentStatus" placeholder="请选择评论" clearable :style="{width: '100%'}">
                  <el-option
                    v-for="dict in dict.type.sys_article_comment"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="排序" prop="sort">
                <el-input-number v-model="form.sort" :min="0" :max="10" label="排序" />
              </el-form-item>
              <el-form-item label="封面" prop="logImg">
                <ele-upload-image
                  v-model="form.logImg"
                  :action="BASE_API+uploadUrl"
                  :response-fn="handleResponse"
                  :is-show-success-tip="false"
                  :file-size="5"
                  :file-type="imgType"
                  :before-remove="beforeRemove"
                />
              </el-form-item>
              <el-form-item label="简介" prop="introduce">
                <el-input
                  v-model="form.introduce"
                  type="textarea"
                  placeholder="请输入简介"
                  :maxlength="200"
                  show-word-limit
                  :autosize="{minRows: 4, maxRows: 4}"
                  :style="{width: '100%'}"
                />
              </el-form-item>
              <el-form-item size="large">
                <el-button type="primary" @click="next">下一步</el-button>
                <el-button @click="resetForm">重置</el-button>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20" :offset="2">
            <div v-show="active === 1">
              <el-form-item prop="content">
                <div>
                  <mavon-editor
                    ref="md"
                    v-model="form.content"
                    :external-link="externalLink"
                    @imgAdd="imgAdd"
                    @imgDel="imgDel"
                  >
                    <!-- 左工具栏前加入自定义按钮 -->
                    <template slot="left-toolbar-before">
                      <input v-show="false" id="upload" type="file" accept=".md" @change="importMd($event)">
                      <label type="button" for="upload" class="op-icon fa el-icon-folder-add" title="导入md文档" />
                    </template>
                  </mavon-editor>
                </div>
              </el-form-item>
              <el-form-item size="large">
                <el-button type="primary" @click="next">上一步</el-button>
                <el-button type="success" @click="submitForm">提交</el-button>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>
<script>
import { deleteImg, uploadImg } from '@/api/upload'
import { add, getById, updateById } from '@/api/article/article'
import { listAll } from '@/api/article/summary'

export default {
  components: {},
  props: [],
  dicts: ['sys_article_type', 'sys_article_status', 'sys_article_top', 'sys_article_origin', 'sys_article_comment', 'sys_article_tag'],
  data() {
    return {
      externalLink: {
        markdown_css: function() {
          // 这是你的markdown css文件路径
          return '/mavon-editor/markdown/github-markdown.min.css'
        },
        hljs_js: function() {
          // 这是你的hljs文件路径
          return '/mavon-editor/highlightjs/highlight.min.js'
        },
        hljs_css: function(css) {
          // 这是你的代码高亮配色文件路径
          return '/mavon-editor/highlightjs/styles/' + css + '.min.css'
        },
        hljs_lang: function(lang) {
          // 这是你的代码高亮语言解析路径
          return '/mavon-editor/highlightjs/languages/' + lang + '.min.js'
        },
        katex_css: function() {
          // 这是你的katex配色方案路径路径
          return '/mavon-editor/katex/katex.min.css'
        },
        katex_js: function() {
          // 这是你的katex.js路径
          return '/mavon-editor/katex/katex.min.js'
        }
      },
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl: '/api/upload/uploadImg?name=articleLog',
      summaryList: [], // 归档列表
      // 文件上传类型
      imgType: ['png', 'jpg', 'jpeg'],
      active: 0,
      // 图片url
      imgPath: '',
      form: {
        articleTagArray: [], // 标签数组列表
        articleTitle: undefined,
        status: undefined,
        articleType: undefined,
        articleTag: undefined,
        topStatus: undefined,
        originStatus: undefined,
        commentStatus: undefined,
        introduce: undefined,
        logImg: undefined,
        content: undefined,
        id: undefined,
        aggregateId: undefined,
        sort: 0
      },
      rules: {
        articleTitle: [{
          required: true,
          message: '请输入标题',
          trigger: 'blur'
        }],
        status: [{
          required: true,
          message: '请选择状态',
          trigger: 'change'
        }],
        articleType: [{
          required: true,
          message: '请选择分类',
          trigger: 'change'
        }],
        articleTagArray: [{
          required: true,
          type: 'array',
          message: '请至少选择一个articleTag',
          trigger: 'change'
        }],
        topStatus: [{
          required: true,
          message: '请选择置顶',
          trigger: 'change'
        }],
        originStatus: [{
          required: true,
          message: '请选择原创',
          trigger: 'change'
        }],
        commentStatus: [{
          required: true,
          message: '请选择评论',
          trigger: 'change'
        }],
        introduce: [{
          required: true,
          message: '请输入简介',
          trigger: 'blur'
        }]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    // 导入md文档
    importMd(e) {
      const file = e.target.files[0]
      if (!file.name.endsWith('.md')) {
        this.$message.warning('文件扩展名必须为.md！')
        return
      }
      const reader = new FileReader()
      reader.readAsText(file)
      reader.onload = (res) => {
        this.form.content = res.target.result
      }
      e.target.value = null
    },
    // MarkDown图片上传
    imgAdd(pos, file) {
      var _this = this
      var formData = new FormData()
      formData.append('file', file)
      uploadImg(formData, 'article').then(response => {
        var url = response.data.url
        if (response.success) {
          _this.$refs.md.$imglst2Url([[pos, url]])
        } else {
          _this.$message({ type: response.code, message: response.msg })
        }
      })
    },
    // MarkDown图片删除
    imgDel(pos) {
      var url = pos[0]
      deleteImg(url).then(response => {
        this.$modal.msgSuccess('删除成功')
      })
    },

    init() {
      // 归档列表
      listAll().then(response => {
        this.summaryList = response.data
      })
      // 文章回显
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        getById(id).then(response => {
          this.form = response.data
          this.imgPath = this.form.logImg
          this.form.articleTagArray = this.form.articleTag.split(',')
        })
      }
    },
    // 图片回显
    handleResponse(response) {
      if (response.code === 20000) {
        this.$modal.msgSuccess('上传文件成功')
        this.imgPath = response.data.url
        return response.data.url
      }
      return this.$modal.msgError('上传文件失败')
    },
    // 删除图片
    beforeRemove() {
      deleteImg(this.imgPath).then(response => {
        this.$modal.msgSuccess('删除成功')
      })
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        this.form.articleTag = this.form.articleTagArray.join(',')
        if (this.form.id != null) {
          // 修改
          updateById(this.form).then(response => {
            this.$modal.msgSuccess('修改成功')
            this.$router.push({ path: '/article/index' })
          })
        } else {
          // 新增
          add(this.form).then(response => {
            this.$modal.msgSuccess('新增成功')
            this.$router.push({ path: '/article/index' })
          })
        }
      })
    },
    next() {
      if (this.active++ > 0) this.active = 0
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    }
  }
}

</script>
<style>
</style>
