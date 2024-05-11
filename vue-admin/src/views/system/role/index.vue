<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="角色名" prop="roleName">
        <el-input
          v-model="queryParams.roleName"
          placeholder="请输入角色名"
          clearable
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
          :disabled="$hasBP('btn.role.add')  === false"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single || $hasBP('btn.role.edit')  === false"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple || $hasBP('btn.role.del')  === false"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center" />
      <el-table-column
        label="角色名"
        align="center"
        prop="roleName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="角色编码"
        align="center"
        prop="roleCode"
        :show-overflow-tooltip="true"
      />
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
      <el-table-column label="操作" align="center" width="200px" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            :disabled="$hasBP('btn.role.edit')  === false"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-flag"
            @click="handlePerms(scope.row)"
            :disabled="$hasBP('btn.role.edit')  === false"
          >权限分配</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            :disabled="$hasBP('btn.role.del')  === false"
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

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色名" prop="roleName">
              <el-input v-model="form.roleName" placeholder="请输入角色名" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="角色编码" prop="roleCode">
              <el-input v-model="form.roleCode" placeholder="请输入角色编码" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="角色备注" prop="description">
              <el-input v-model="form.description" placeholder="请输入角色备注" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="角色状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in dict.type.currency_status"
                  :key="dict.value"
                  :label="dict.value"
                >{{ dict.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 权限分配对话框 -->
    <el-dialog :title="titlePre" :visible.sync="openPre" width="780px" append-to-body>
      <el-tree
        style="margin: 20px 0"
        ref="tree"
        :data="sysMenuList"
        node-key="id"
        show-checkbox
        default-expand-all
        :props="defaultProps"
      />
      <div slot="footer" class="dialog-footer">
        <el-button :loading="loading" type="primary" icon="el-icon-check" size="mini" @click="savePerms">确定</el-button>
        <el-button @click="openPre=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list, get, del, add, update } from '@/api/system/role'
import { toAssign,doAssign } from  '@/api/system/menu'

export default {
  name: 'Role',
  dicts: ['currency_status'],
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
      // 日期范围
      dateRange: [],
      openPre: false,
      // 总条数
      total: 0,
      // 表格数据
      list: [],
      // 弹出层标题
      title: '',
      titlePre: '',
      // 是否显示增改弹出层
      open: false,
      roleId: '',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleName: undefined
      },
      // 表单参数
      form: {},
      sysMenuList: [], // 所有
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      // 表单校验
      rules: {
        roleName: [
          { required: true, message: '角色名不能为空', trigger: 'blur' }
        ],
        roleCode: [
          { required: true, message: '角色编码不能为空', trigger: 'blur' }
        ],
        status: [
          {required: true, message: '请选择状态', trigger: 'change'}
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.isPreList('btn.role.list')
      this.loading = true
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.list = response.rows
        this.total = response.total
        this.loading = false
      })
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
      }
      this.resetForm('form')
    },
    /*
      得到所有选中的id列表
      */
    getCheckedIds (auths, initArr = []) {
      return auths.reduce((pre, item) => {
        if (item.select && item.children.length === 0) {
          pre.push(item.id)
        } else if (item.children) {
          this.getCheckedIds(item.children, initArr)
        }
        return pre
      }, initArr)
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加角色'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      get(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改角色'
      })
    },
    /** 分配权限按钮 */
    handlePerms(row){
      const roleId = row.id
      toAssign(roleId).then(result => {
        const sysMenuList = result.data
        this.sysMenuList = sysMenuList
        this.roleId = row.id
        const checkedIds = this.getCheckedIds(sysMenuList)
        this.$nextTick(()=>{
          this.$refs.tree.setCheckedKeys(checkedIds)
        })

        this.openPre = true
        this.titlePre = '为"'+row.roleName + '"分配权限'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined) {
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
    savePerms(){
      const allCheckedNodes = this.$refs.tree.getCheckedNodes(false, true);
      let idList = allCheckedNodes.map(node => node.id);
      let assignMenuVo = {
        roleId: this.roleId,
        menuIdList: idList
      }
      this.loading = true
      doAssign(assignMenuVo).then(result => {
        this.loading = false
        this.$message.success('分配权限成功')
        this.openPre = false
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const Ids = row.id || this.ids
      this.$modal.confirm('是否确认删除角色编号为"' + Ids + '"的数据项？').then(function() {
        return del(Ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
    }
  }
}
</script>
