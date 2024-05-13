<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
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
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          :disabled="$hasBP('btn.job.add')  === false"
        >新增
        </el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-collection-tag
" size="mini" @click="openExample">cron示例
        </el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="list">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" align="center" type="index"/>
      <el-table-column label="任务名称" align="center" prop="jobName" :show-overflow-tooltip="true"/>
      <el-table-column label="任务所在组" align="center" prop="jobGroup" :show-overflow-tooltip="true"/>
      <el-table-column label="任务类名" align="center" prop="jobClassName"/>
      <el-table-column label="触发器名称" align="center" prop="triggerName"/>
      <el-table-column label="触发器所在组" align="center" prop="triggerGroup"/>
      <el-table-column label="表达式" align="center" prop="cronExpression"/>
      <el-table-column label="时区" align="center" prop="timeZoneId"/>
      <el-table-column label="状态" align="center" prop="triggerState"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-magic-stick"
            @click="handleManual(scope.$index, scope.row)"
            :disabled="$hasBP('btn.job.edit')  === false"
          >手动执行
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.$index, scope.row)"
            :disabled="$hasBP('btn.job.edit')  === false"
          >修改
          </el-button>
          <el-button
            v-show="scope.row.triggerState === 'ACQUIRED' || scope.row.triggerState === 'WAITING'"
            size="mini"
            type="text"
            icon="el-icon-video-pause"
            @click="handlePause(scope.$index, scope.row)"
            :disabled="$hasBP('btn.job.edit')  === false"
          >暂停
          </el-button>
          <el-button
            v-show="scope.row.triggerState === 'PAUSED'"
            size="mini"
            type="text"
            icon="el-icon-video-play"
            @click="handleResume(scope.$index, scope.row)"
            :disabled="$hasBP('btn.job.edit')  === false"
          >恢复
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)"
            :disabled="$hasBP('btn.job.del')  === false"
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
    <!--示例对话框-->
    <el-dialog :title="title" :visible.sync="dialogExample" width="700px" append-to-body>
      <el-table
        :data="cronData"
        border
        style="width: 100%">
        <el-table-column
          prop="expression"
          label="表达式"
          width="180">
        </el-table-column>
        <el-table-column
          prop="effect"
          label="效果">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogExample = false">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名称" prop="configName">
          <el-input v-model="form.jobClassName" placeholder="请输入任务名称"/>
        </el-form-item>
        <el-form-item label="任务分组" prop="configKey">
          <el-input v-model="form.jobGroupName" placeholder="请输入任务分组"/>
        </el-form-item>
        <el-form-item label="表达式" prop="configValue">
          <el-input v-model="form.cronExpression" placeholder="请输入表达式"/>
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
          <el-input v-model="updateForm.cronExpression" placeholder="请输入表达式"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="update">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {add, del, handlePause,handleManual, handleResume, list, updateCron} from '@/api/system/job'

export default {
  name: 'Job',
  data() {
    return {
      // 遮罩层
      loading: true,
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
      title: '',
      // 是否显示新增弹出层
      dialogFormVisible: false,
      // 是否显示修改弹出层
      updateFormVisible: false,
      // 是否显示示例弹出层
      dialogExample: false,
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
          {required: true, message: '任务名称不能为空', trigger: 'blur'}
        ],
        jobGroupName: [
          {required: true, message: '任务分组不能为空', trigger: 'blur'}
        ],
        cronExpression: [
          {required: true, message: '表达式不能为空', trigger: 'blur'}
        ]
      },
      cronData: [{
        expression: "0/2 * * * * ?",
        effect: "表示每2秒 执行任务"
      }, {
        expression: "0 0/2 * * * ?",
        effect: "表示每2分钟 执行任务"
      }, {
        expression: "0 0 2 1 * ?",
        effect: "表示在每月的1日的凌晨2点调整任务"
      }, {
        expression: "0 15 10 ? * MON-FRI",
        effect: "表示周一到周五每天上午10:15执行作业"
      }, {
        expression: "0 0 10,14,16 * * ? ",
        effect: "每天上午10点，下午2点，4点 "
      }, {
        expression: "0 0 12 * * ?",
        effect: "每天中午12点触发 "
      }]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      this.isPreList('btn.job.list')
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
      this.dialogFormVisible = false
      this.updateFormVisible = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        jobClassName: undefined,
        jobGroupName: undefined,
        cronExpression: undefined
      }
      this.updateForm = {
        jobClassName: undefined,
        jobGroupName: undefined,
        cronExpression: undefined
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
      this.dialogFormVisible = true
      this.title = '添加定时任务'
    },
    //示例按钮操作
    openExample() {
      this.dialogExample = true
      this.title = 'cron示例'
    },
    /** 修改按钮操作 */
    handleUpdate(index, row) {
      this.reset()
      this.updateFormVisible = true
      this.updateForm.jobClassName = row.jobName
      this.updateForm.jobGroupName = row.jobGroup
      this.updateForm.cronExpression = row.cronExpression
      this.title = '修改定时任务'
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          add(this.form).then(response => {
            this.$modal.msgSuccess('新增成功')
            this.dialogFormVisible = false
            this.getList()
          })
        }
      })
    },
    /** 修改按钮 **/
    update: function () {
      updateCron(this.updateForm).then(response => {
        this.$modal.msgSuccess('修改成功')
        this.updateFormVisible = false
        this.getList()
      })
    },
    /** 暂停任务 **/
    handlePause: function (index, row) {
      this.updateForm.jobClassName = row.jobName
      this.updateForm.jobGroupName = row.jobGroup
      handlePause(this.updateForm).then(res => {
        this.$modal.msgSuccess('暂停任务成功')
        this.getList()
      })
    },
    /** 手动调用 **/
    handleManual: function (index, row) {
      this.updateForm.jobClassName = row.jobName
      this.updateForm.jobGroupName = row.jobGroup
      handleManual(this.updateForm).then(res => {
        this.$modal.msgSuccess('手动调用任务成功')
        this.getList()
      })
    },
    /** 恢复任务 **/
    handleResume: function (index, row) {
      this.updateForm.jobClassName = row.jobName
      this.updateForm.jobGroupName = row.jobGroup
      handleResume(this.updateForm).then(res => {
        this.$modal.msgSuccess('恢复任务成功')
        this.getList()
      })
    },
    /** 删除按钮操作 */
    handleDelete(index, row) {
      this.updateForm.jobClassName = row.jobName
      this.updateForm.jobGroupName = row.jobGroup
      this.$confirm('是否删除该定时任务', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(() => {
          del(this.updateForm).then(res => {
            this.$modal.msgSuccess('删除成功')
            this.getList()
          })
        })
        .catch(action => {
        })
    }
  }
}
</script>
