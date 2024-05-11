<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="归档标题" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入归档标题"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="状态"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.currency_status"
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
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center" />
      <el-table-column label="归档标题" align="center" prop="name" :show-overflow-tooltip="true" />
      <el-table-column prop="banner" label="归档图" align="center">
        <template slot-scope="scope">
          <el-image
            style="width:100px;height: 100px"
            :src="scope.row.banner"
            :preview-src-list="[scope.row.banner]"
          />
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.currency_status" :value="scope.row.status" />
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
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleList(scope.row)"
          >文章列表</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="30%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="归档标题" prop="name">
          <el-input v-model="form.name" placeholder="请输入归档标题" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态" clearable :style="{width: '100%'}">
            <el-option
              v-for="dict in dict.type.currency_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="归档图" prop="banner">
          <div style="width: 200px;height: auto">
            <el-image v-if="form.banner!=null && form.banner !==''"
                      :src="form.banner"
                      fit="fill"
                      :preview-src-list="[form.banner]">
            </el-image>
            <el-button style="margin-left: 53px" type="success" size="mini" @click="handleImgList">选择图片
            </el-button>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 文章列表 -->
    <el-dialog :title="title" :visible.sync="openList" append-to-body width="780px">
      <el-transfer
        v-model="value"
        :titles="['所有文章', '当前归档']"
        :button-texts="['移除', '添加']"
        :data="articleData"
        :render-content="renderFunc"
      @change="handleChange"></el-transfer>
    </el-dialog>

    <!-- 图片列表 -->
    <el-dialog  :visible.sync="imgOpenList" width="780px" append-to-body>
      <div class="goods-list-container" v-for="(item, index) in this.galleryList" :key="index">
        <el-card :body-style="{ padding: '0px' }" shadow="hover">
          <div class="goods-list-card-body">
            <div class="goods-list-image-group">
              <el-image
                style="width: 620px; height: 450px"
                :src="item.imgAddress"
                fit="fill"
                :preview-src-list="[item.imgAddress]"
              ></el-image>
            </div>
            <el-button
              style="padding-left: 46%"
              type="text"
              size="medium"
              icon="el-icon-thumb"
              @click="setPicture(item)"
            >使用该图片
            </el-button>
          </div>
        </el-card>
      </div>
      <pagination
        :pager-count="5"
        v-show="imgTotal>0"
        :total="imgTotal"
        :page-sizes="[2,5,8]"
        :page.sync="imgParams.pageNum"
        :limit.sync="imgParams.pageSize"
        @pagination="handleImgList"
      />
    </el-dialog>
  </div>
</template>
<script>
import { listArticleSummary, add, del, getInfo, update, articleList } from '@/api/article/summary'
import { updateSummaryById } from "@/api/article/article";
import { deleteImg } from '@/api/upload'
import {list} from "@/api/gallery/gallery";

export default {
  name: 'Summary',
  dicts: ['currency_status'],
  data() {
    return {
      summaryId: '',
      articleData: [],
      value: [],
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl: '/api/upload/uploadImg?name=articleSummary',
      // 文件上传类型
      imgType: ['png', 'jpg', 'jpeg'],
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
      //图集分页总条数
      imgTotal:0,
      // 表格数据
      list: [],
      // 弹出层标题
      title: '',
      // 是否显示新增删除弹出层
      open: false,
      // 是否显示文章列表弹出层
      openList: false,
      //图片列表显示弹出层
      imgOpenList: false,
      //图集
      galleryList: [],
      // 图片url
      imgPath: '',
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        status: undefined,
        topStatus: undefined
      },
      //图集分页参数
      imgParams: {
        pageNum: 1,
        pageSize: 2,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: '归档标题不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '归档状态不能为空', trigger: 'blur' }
        ],
        topStatus: [
          { required: true, message: '归档是否展示不能为空', trigger: 'blur' }
        ],
        banner: [
          { required: true, message: '归档图不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 图片选择
    setPicture(item) {
      this.form.banner = item.imgAddress
      this.imgParams.pageNum = 1
      this.imgParams.pageSize = 2
      this.imgOpenList = false
    },
    // 图集列表
    handleImgList() {
      list(this.imgParams).then(response => {
        this.imgOpenList = true
        this.galleryList = response.rows
        this.imgTotal = response.total
      })
    },

    renderFunc(h, option) {
      return <span title={option.label}>{option.label}</span>;
    },
    // 图片回显
    handleResponse(response) {
      if (response.success) {
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

    /** 查询归档列表 */
    getList() {
      this.loading = true
      listArticleSummary(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
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
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        articleTitle: undefined
      }
      this.resetForm('form')
    },
    /** 穿梭框点击 */
    handleChange(value, direction, movedKeys) {
      let type = "1"
      if(direction==='left'){
        type = "0"
      }
      updateSummaryById(movedKeys,this.summaryId,type).then(res=>{
        this.$modal.msgSuccess('修改成功')
      })
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
      this.title = '添加资源'
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
        this.imgPath = this.form.resourceImg
        this.open = true
        this.title = '修改资源'
      })
    },
    /** 文章列表按钮操作 */
    handleList(row) {
      this.articleData = []
      this.value = []
      const id = row.id || this.ids
      articleList(id).then(response=>{
        this.articleData = response.data
        this.summaryId = id
        this.articleData.forEach(s => {
          if(s.belong === 1){
            this.value.push(s.key)
          }
        })
        this.openList = true
        this.title = '文章列表'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
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
      this.$modal.confirm('是否确认删除归档编号为"' + ids + '"的数据项？').then(function() {
        return del(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>

