<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题内容" prop="articleTitle">
        <el-input
          v-model="queryParams.articleTitle"
          placeholder="请输入标题内容"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章类别" prop="articleType">
        <el-select
          v-model="queryParams.articleType"
          placeholder="文章类别"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_article_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文章标签" prop="articleTag">
        <el-select
          v-model="queryParams.articleTag"
          placeholder="文章标签"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_article_tag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="归档目录" prop="aggregateId">
        <el-select
          v-model="queryParams.aggregateId"
          placeholder="归档目录"
          clearable
          style="width: 240px"
        >
          <el-option
            v-for="summary in summaryList"
            :key="summary.id"
            :label="summary.name"
            :value="summary.id"
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
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <router-link :to="'/article/add/'">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
          >新增
          </el-button>
        </router-link>
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
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-col>
    </el-row>
    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column type="index" label="序号" align="center" />
      <el-table-column label="标题" align="center" prop="articleTitle" :show-overflow-tooltip="true"/>
      <el-table-column prop="logImg" label="标题图" align="center">
        <template slot-scope="scope">
          <el-image
            style="width:100px;height: 100px"
            :src="scope.row.logImg"
            :preview-src-list="[scope.row.logImg]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="articleType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_article_type" :value="scope.row.articleType"/>
        </template>
      </el-table-column>
      <el-table-column label="标签" align="center" prop="articleTagList" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-tag style="margin: 5px" v-for="item in scope.row.articleTagList" :key="item.id" type="success">{{
              item
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否置顶" align="center" prop="topStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_article_top" :value="scope.row.topStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="是否原创" align="center" prop="originStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_article_origin" :value="scope.row.originStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="评论" align="center" prop="commentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_article_comment" :value="scope.row.commentStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="点击量" align="center" prop="clickRate" :show-overflow-tooltip="true"/>
      <el-table-column label="点赞数" align="center" prop="numberLike" :show-overflow-tooltip="true"/>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_article_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="'/article/update/'+scope.row.id">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
            >修改
            </el-button>
          </router-link>
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
  </div>
</template>
<script>
import {deleteBthById, listArticleInform} from "@/api/article/article";
import {listAll} from "@/api/article/summary"

export default {
  name: "Dict",
  dicts: ['sys_article_type', 'sys_article_status', 'sys_article_top', 'sys_article_origin', 'sys_article_comment','sys_article_tag'],
  data() {
    return {
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
      // 文章表格数据
      articleList: [],
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleTitle: undefined,
      },
      //归档列表
      summaryList: []
    };
  },
  created() {
    this.getList();
    this.getSummaryList();
  },
  methods: {
    getSummaryList(){
      listAll().then(response=>{
        this.summaryList = response.data
      })
    },
    /** 查询文章列表 */
    getList() {
      this.loading = true;
      listArticleInform(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.articleList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除文章编号为"' + ids + '"的数据项？').then(function () {
        return deleteBthById(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    }
  }
};
</script>
