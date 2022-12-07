<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
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
        <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
      </el-col>
    </el-row>
    <div class="goods-list-container">
      <el-row :gutter="24">
        <el-col
          v-for="(item, index) in this.list"
          :key="index"
          :xs="24"
          :sm="8"
          :md="8"
          :lg="8"
          :xl="4"
          style="margin-left: 60px"
        >
          <el-card :body-style="{ padding: '0px' }" shadow="hover">
            <div class="goods-list-card-body">
              <div class="goods-list-image-group">
                <img :src="item.imgAddress" class="goods-list-image" />
              </div>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(item.id)"
              >删除</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>


    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="25%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="220px">
        <el-form-item label="图片" prop="imgAddress">
          <ele-upload-image
            v-model="form.imgAddress"
            :action="BASE_API+uploadUrl"
            :response-fn="handleResponse"
            :is-show-success-tip="false"
            :file-size="5"
            :file-type="imgType"
            :before-remove="beforeRemove"
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
import { list,add,del } from "@/api/gallery/gallery"
import { deleteImg } from '@/api/upload'
export default {
  name: 'Gallery',
  data() {
    return {
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl: '/api/upload/uploadImg?name=gallery',
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
      list: [],
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
        pageSize: 10
      },
      // 表单参数
      form: {
        imgAddress: undefined
      },
      // 表单校验
      rules: {
        imgAddress: [
          { required: true, message: '图片不能为空', trigger: 'blur' }
        ],
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
    /** 查询图片列表 */
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
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          add(this.form).then(response => {
            this.$modal.msgSuccess('新增成功')
            this.open = false
            this.getList()
          })
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(id) {
      this.$modal.confirm('是否确认删除图片编号为"' + id + '"的数据项？').then(function() {
        return del(id)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
<style lang="scss" scoped>
.goods-list-container {
  .goods-list-card-body {
    position: relative;
    text-align: center;
    cursor: pointer;

    .goods-list-image-group {
      height: 370px;
      overflow: hidden;
      .goods-list-image {
        width: 100%;
        height: 350px;
        transition: all ease-in-out 0.3s;
        &:hover {
          transform: scale(1.1);
        }
      }
    }
  }
}
</style>
