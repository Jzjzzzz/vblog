<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="资源名称" prop="resourceName">
        <el-input
          v-model="queryParams.resourceName"
          placeholder="请输入资源名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资源类型" prop="resourceType">
        <el-select
          v-model="queryParams.resourceType"
          placeholder="资源类型"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_website_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          :disabled="$hasBP('btn.website.add')  === false"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single || $hasBP('btn.website.edit')  === false"
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
          :disabled="multiple || $hasBP('btn.website.del')  === false"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <right-toolbar :show-search.sync="showSearch" @queryTable="getList"/>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="websiteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="资源名称" align="center" prop="resourceName" :show-overflow-tooltip="true" min-width="150px"/>
      <el-table-column label="资源网址" align="center" prop="resourceAddress" width="200px">
        <template slot-scope="scope">
          <a :href="scope.row.resourceAddress" target="_blank">
            <button class="learn-more">
              <span class="circle" aria-hidden="true">
                <span class="icon arrow"></span>
              </span>
              <span class="button-text">前往站点</span>
            </button>
          </a>
        </template>
      </el-table-column>
      <el-table-column prop="resourceImg" label="标题图" align="center" width="150px">
        <template slot-scope="scope">
          <el-image
            style="width:100px;height: 100px"
            :src="scope.row.resourceImg"
            :preview-src-list="[scope.row.resourceImg]"
          />
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="resourceType" width="80px">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_website_type" :value="scope.row.resourceType"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.currency_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="点击量" align="center" prop="clickRate" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            :disabled="$hasBP('btn.website.edit')  === false"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            :disabled="$hasBP('btn.website.del')  === false"
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资源名称" prop="resourceName">
          <el-input v-model="form.resourceName" placeholder="请输入资源名称"/>
        </el-form-item>
        <el-form-item label="资源描述" prop="resourceDetail">
          <el-input v-model="form.resourceDetail" placeholder="请输入资源描述"/>
        </el-form-item>
        <el-form-item label="资源地址" prop="resourceAddress">
          <el-input v-model="form.resourceAddress" placeholder="请输入资源地址"/>
        </el-form-item>
        <el-form-item label="资源分类" prop="resourceType">
          <el-select v-model="form.resourceType" placeholder="请选择分类" clearable :style="{width: '100%'}">
            <el-option
              v-for="dict in dict.type.sys_website_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
        <el-form-item label="资源图" prop="resourceImg">
          <ele-upload-image
            v-model="form.resourceImg"
            :action="BASE_API+uploadUrl"
            :response-fn="handleResponse"
            :is-show-success-tip="false"
            :file-size="5"
            :file-type="imgType"
            :before-remove="beforeRemove"
            :headers="header"
          />
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
import {listWebsiteInform, addWebSite, getInfo, updateWebsite, delWebSite} from '@/api/website/website'
import {deleteImg} from '@/api/upload'
import {getToken} from '@/utils/auth'

export default {
  name: 'Website',
  dicts: ['sys_website_type', 'currency_status'],
  data() {
    return {
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl: 'api/upload/uploadImg?name=website',
      header:{
        token: getToken()
      },
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
      // 表格数据
      websiteList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 图片url
      imgPath: '',
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        resourceName: undefined,
        resourceType: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        resourceName: [
          {required: true, message: '资源名称不能为空', trigger: 'blur'}
        ],
        resourceAddress: [
          {required: true, message: '资源地址不能为空', trigger: 'blur'}
        ],
        resourceType: [
          {required: true, message: '资源分类不能为空', trigger: 'blur'}
        ],
        status: [
          {required: true, message: '资源状态不能为空', trigger: 'blur'}
        ],
        resourceImg: [
          {required: true, message: '资源图不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
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
    /** 查询列表 */
    getList() {
      this.isPreList('btn.website.list')
      this.loading = true
      listWebsiteInform(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.websiteList = response.rows
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
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateWebsite(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addWebSite(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除资源编号为"' + ids + '"的数据项？').then(function () {
        return delWebSite(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>
<style>
button {
  position: relative;
  display: inline-block;
  cursor: pointer;
  outline: none;
  border: 0;
  vertical-align: middle;
  text-decoration: none;
  background: transparent;
  padding: 0;
  font-size: inherit;
  font-family: inherit;
}

button.learn-more {
  width: 12rem;
  height: auto;
}

button.learn-more .circle {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  position: relative;
  display: block;
  margin: 0;
  width: 3rem;
  height: 3rem;
  background: #282936;
  border-radius: 1.625rem;
}

button.learn-more .circle .icon {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  position: absolute;
  top: 0;
  bottom: 0;
  margin: auto;
  background: #fff;
}

button.learn-more .circle .icon.arrow {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  left: 0.625rem;
  width: 1.125rem;
  height: 0.125rem;
  background: none;
}

button.learn-more .circle .icon.arrow::before {
  position: absolute;
  content: "";
  top: -0.29rem;
  right: 0.0625rem;
  width: 0.625rem;
  height: 0.625rem;
  border-top: 0.125rem solid #fff;
  border-right: 0.125rem solid #fff;
  transform: rotate(45deg);
}

button.learn-more .button-text {
  transition: all 0.45s cubic-bezier(0.65, 0, 0.076, 1);
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  padding: 0.75rem 0;
  margin: 0 0 0 1.85rem;
  color: #282936;
  font-weight: 700;
  line-height: 1.6;
  text-align: center;
  text-transform: uppercase;
}

button:hover .circle {
  width: 100%;
}

button:hover .circle .icon.arrow {
  background: #fff;
  transform: translate(1rem, 0);
}

button:hover .button-text {
  color: #fff;
}
</style>
