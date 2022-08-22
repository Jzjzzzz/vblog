<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="jobName">
        <el-input
          v-model="queryParams.jobName"
          placeholder="请输入任务名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务组" prop="jobGroup">
        <el-input
          v-model="queryParams.jobGroup"
          placeholder="请输入任务所在组"
          clearable
          style="width: 260px"
          @keyup.enter.native="handleQuery"
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="任务名称" align="center" prop="jobName" :show-overflow-tooltip="true" />
      <el-table-column label="任务所在组" align="center" prop="jobGroup" :show-overflow-tooltip="true" />
      <el-table-column label="任务类名" align="center" prop="jobClassName" />
      <el-table-column label="触发器名称" align="center" prop="triggerName" />
      <el-table-column label="触发器所在组" align="center" prop="triggerGroup" />
      <el-table-column label="表达式" align="center" prop="cronExpression" />
      <el-table-column label="时区" align="center" prop="timeZoneId" />
      <el-table-column label="状态" align="center" prop="triggerState" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.$index, scope.row)"
          >修改</el-button>
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

    <!-- 添加对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名称" prop="configName">
          <el-input v-model="form.jobClassName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="任务分组" prop="configKey">
          <el-input v-model="form.jobGroupName" placeholder="请输入任务分组" />
        </el-form-item>
        <el-form-item label="表达式" prop="configValue">
          <el-input v-model="form.cronExpression" placeholder="请输入表达式" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 修改对话框 -->
    <el-dialog :title="title" :visible.sync="updateFormVisible" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="表达式" prop="configValue">
          <el-input v-model="updateForm.cronExpression" placeholder="请输入表达式" />
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
import { delConfig, updateConfig } from "@/api/system/config";
import { list,add,updateCron } from "@/api/system/job";
export default {
  name: "Config",
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
      // 参数表格数据
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示新增弹出层
      dialogFormVisible: false,
      // 是否显示修改弹出层
      updateFormVisible: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        jobName: undefined,
        jobGroup: undefined
      },
      // 表单参数
      form: {
        jobClassName: undefined,
        jobGroupName: undefined,
        cronExpression: undefined
      },
      updateForm: {
        jobClassName: undefined,
        jobGroupName: undefined,
        cronExpression: undefined
      },
      // 表单校验
      rules: {
        jobClassName: [
          { required: true, message: "任务名称不能为空", trigger: "blur" }
        ],
        jobGroupName: [
          { required: true, message: "任务分组不能为空", trigger: "blur" }
        ],
        cronExpression: [
          { required: true, message: "表达式不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.loading = true;
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.list = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    // 取消按钮
    cancel() {
      this.dialogFormVisible = false;
      this.updateFormVisible = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        jobClassName: undefined,
        jobGroupName: undefined,
        cronExpression: undefined,
      };
      this.updateForm = {
        jobClassName: undefined,
        jobGroupName: undefined,
        cronExpression: undefined,
      }
      this.resetForm("form");
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.dialogFormVisible = true;
      this.title = "添加定时任务";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(index, row) {
      this.reset();
      this.updateFormVisible = true;
      this.updateForm.jobClassName = row.jobName;
      this.updateForm.jobGroupName = row.jobGroup;
      this.updateForm.cronExpression = row.cronExpression;
      this.title = "修改定时任务";
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          add(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.dialogFormVisible = false;
            this.getList();
          });
        }
      });
    },
    updateForm: function (){
      updateCron(this.updateForm).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.updateFormVisible = false;
            this.getList();
          });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除参数编号为"' + ids + '"的数据项？').then(function() {
        return delConfig(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
