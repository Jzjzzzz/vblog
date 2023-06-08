<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="题目" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入题目"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="类型"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_item_bank_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-s-promotion"
          size="mini"
          @click="handleRandom"
        >每日一题
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <right-toolbar :show-search.sync="showSearch" @queryTable="getList"/>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="题目" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="类型" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_item_bank_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <el-dialog :title="title" :visible.sync="openOne" width="80%" append-to-body>
      <el-form ref="formOne" :model="formOne" :rules="rules" label-width="80px">
        <el-form-item label="题目类型" prop="type">
          <el-select v-model="formOne.type" placeholder="请选择题目类型" clearable :style="{width: '100%'}">
            <el-option
              v-for="dict in dict.type.sys_item_bank_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="题目" prop="title">
          <el-input autosize type="textarea" v-model="formOne.title" placeholder="请输入题目"/>
        </el-form-item>
        <el-form-item label="答案" prop="answer" v-if="openAnswer">
          <div>
            <mavon-editor
              v-model="formOne.answer"
              defaultOpen="preview"
              :toolbarsFlag="false"
              :editable="false"
              :subfield="false"
            />
          </div>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-switch
          style="padding-right: 20px"
          v-model="openAnswer"
          active-text="隐藏答案"
          inactive-text="查看答案">
        </el-switch>
        <el-button type="primary" @click="handleRandom">下一题</el-button>
        <el-button @click="cancelOne">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="题目类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择题目类型" clearable :style="{width: '100%'}">
            <el-option
              v-for="dict in dict.type.sys_item_bank_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="题目" prop="title">
          <el-input autosize type="textarea" v-model="form.title" placeholder="请输入题目"/>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <div>
            <mavon-editor
              ref="md"
              v-model="form.answer"
              style="height: 1100px"
              :external-link="externalLink"
              @imgAdd="imgAdd"
              @imgDel="imgDel"
            >
            </mavon-editor>
          </div>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {list, add, update, del, getInfo, random} from '@/api/itembank/itembank'
import {deleteImg, uploadImg} from '@/api/upload'

export default {
  name: 'Link',
  dicts: ['sys_item_bank_type'],
  data() {
    return {
      externalLink: {
        markdown_css: function () {
          // 这是你的markdown css文件路径
          return '/mavon-editor/markdown/github-markdown.min.css'
        },
        hljs_js: function () {
          // 这是你的hljs文件路径
          return '/mavon-editor/highlightjs/highlight.min.js'
        },
        hljs_css: function (css) {
          // 这是你的代码高亮配色文件路径
          return '/mavon-editor/highlightjs/styles/' + css + '.min.css'
        },
        hljs_lang: function (lang) {
          // 这是你的代码高亮语言解析路径
          return '/mavon-editor/highlightjs/languages/' + lang + '.min.js'
        },
        katex_css: function () {
          // 这是你的katex配色方案路径路径
          return '/mavon-editor/katex/katex.min.css'
        },
        katex_js: function () {
          // 这是你的katex.js路径
          return '/mavon-editor/katex/katex.min.js'
        }
      },
      uploadUrl: '/api/upload/uploadImg?name=itemBank',
      // 文件上传类型
      imgType: ['png', 'jpg', 'jpeg'],
      // 图片url
      imgPath: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 表格数据
      list: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      openOne: false,
      openAnswer: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        type: undefined
      },
      formOne: {},
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: '题目类型不能为空', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询文章列表 */
    getList() {
      this.loading = true
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.list = response.rows
          this.total = response.total
          this.loading = false
        }
      )
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    cancelOne() {
      this.openOne = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        articleTitle: undefined
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加题目'
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改题目'
      })
    },
    handleRandom(row) {
      this.reset()
      random().then(response => {
        this.formOne = response.data
        this.openOne = true
        this.openAnswer = false
        this.title = '每日一题'
      })
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

    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            update(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            add(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除题目编号为"' + ids + '"的数据项？').then(function () {
        return del(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    // MarkDown图片上传
    imgAdd(pos, file) {
      var _this = this
      var formData = new FormData()
      formData.append('file', file)
      uploadImg(formData, 'itemBank').then(response => {
        var url = response.data.url
        if (response.success) {
          _this.$refs.md.$imglst2Url([[pos, url]])
        } else {
          _this.$message({type: response.code, message: response.msg})
        }
      })
    },
    // MarkDown图片删除
    imgDel(pos) {
      var url = pos[0]
      deleteImg(url).then(response => {
        this.$modal.msgSuccess('删除成功')
      })
    }
  }
}
</script>
