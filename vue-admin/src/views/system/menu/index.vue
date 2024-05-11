<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="add"
          :disabled="$hasBP('btn.menu.add')  === false"
        >新增目录</el-button>
      </el-col>
    </el-row>
    <el-table
      :data="sysMenuList"
      style="width: 100%;margin-bottom: 20px;margin-top: 10px;"
      row-key="id"
      border
      :default-expand-all="false"
      :tree-props="{children: 'children'}">

      <el-table-column prop="name" label="菜单名称" width="160"/>
      <el-table-column label="图标">
        <template slot-scope="scope">
          <i :class="scope.row.icon"></i>
        </template>
      </el-table-column>
      <el-table-column prop="perms" label="权限标识" width="160"/>
      <el-table-column prop="path" label="路由地址" width="120"/>
      <el-table-column prop="component" label="组件路径" width="160"/>
      <el-table-column prop="sortValue" label="排序" width="60"/>
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.currency_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="text"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="add(scope.row)"
            v-if="scope.row.type !== '2'"
            :disabled="$hasBP('btn.menu.add')  === false"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="edit(scope.row)"
            :disabled="$hasBP('btn.menu.edit')  === false"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="removeDataById(scope.row)"
            :disabled="scope.row.children.length > 0 || $hasBP('btn.menu.del')  === false"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="40%" >
      <el-form ref="dataForm" :model="sysMenu" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="上级部门" v-if="sysMenu.id === ''">
          <el-input v-model="sysMenu.parentName" :disabled="true"/>
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <el-radio-group v-model="sysMenu.type" :disabled="typeDisabled">
            <el-radio :label="0" :disabled="type0Disabled">目录</el-radio>
            <el-radio :label="1" :disabled="type1Disabled">菜单</el-radio>
            <el-radio :label="2" :disabled="type2Disabled">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="sysMenu.name"/>
        </el-form-item>
        <el-form-item label="图标" prop="icon" v-if="sysMenu.type !== '2'">
          <el-select v-model="sysMenu.icon" clearable>
            <el-option v-for="item in iconList" :key="item.class" :label="item.class" :value="item.class">
            <span style="float: left;">
             <i :class="item.class"></i>  <!-- 如果动态显示图标，这里添加判断 -->
            </span>
              <span style="padding-left: 6px;">{{ item.class }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="sysMenu.sortValue" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item prop="path">
              <span slot="label">
                <el-tooltip content="访问的路由地址，如：`sysUser`" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                路由地址
              </span>
          <el-input v-model="sysMenu.path" placeholder="请输入路由地址" />
        </el-form-item>
        <el-form-item prop="component" v-if="sysMenu.type !== '0'">
              <span slot="label">
                <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                组件路径
              </span>
          <el-input v-model="sysMenu.component" placeholder="请输入组件路径" />
        </el-form-item>
        <el-form-item v-if="sysMenu.type === '2'">
          <el-input v-model="sysMenu.perms" placeholder="请输入权限标识" maxlength="100" />
          <span slot="label">
                <el-tooltip content="控制器中定义的权限字符，如：@PreAuthorize(hasAuthority('btn.sysRole.list'))" placement="top">
                <i class="el-icon-question"></i>
                </el-tooltip>
                权限字符
              </span>
        </el-form-item>
        <el-form-item label="状态" prop="type">
          <el-select
            v-model="sysMenu.status"
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
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import { del, add, update,findNodes } from '@/api/system/menu'

const defaultForm = {
  id: '',
  parentId: '',
  name: '',
  type: '0',
  path: '',
  component: '',
  perms: '',
  icon: '',
  sortValue: 1,
  status: '1'
}
export default {
  name: 'Menu',
  dicts: ['currency_status'],
  // 定义数据
  data() {
    return {
      sysMenuList: [],
      expandKeys: [], // 需要自动展开的项

      typeDisabled: false,
      type0Disabled: false,
      type1Disabled: false,
      type2Disabled: false,
      dialogTitle: '',

      dialogVisible: false,
      sysMenu: defaultForm,
      saveBtnDisabled: false,

      iconList: [
        {
          class: "el-icon-s-tools",
        },
        {
          class: "el-icon-s-custom",
        },
        {
          class: "el-icon-setting",
        },
        {
          class: "el-icon-user-solid",
        },
        {
          class: "el-icon-s-help",
        },
        {
          class: "el-icon-phone",
        },
        {
          class: "el-icon-s-unfold",
        },
        {
          class: "el-icon-s-operation",
        },
        {
          class: "el-icon-more-outline",
        },
        {
          class: "el-icon-s-check",
        },
        {
          class: "el-icon-tickets",
        },
        {
          class: "el-icon-s-goods",
        },
        {
          class: "el-icon-document-remove",
        },
        {
          class: "el-icon-warning",
        },
        {
          class: "el-icon-warning-outline",
        },
        {
          class: "el-icon-question",
        },
        {
          class: "el-icon-info",
        },
        {
          class: "el-icon-picture-outline"
        },
        {
          class: "el-icon-camera"
        },
        {
          class: "el-icon-s-platform"
        },
        {
          class: "el-icon-s-flag"
        },
        {
          class: "el-icon-s-data"
        },
        {
          class: "el-icon-menu"
        },
        {
          class: "el-icon-s-comment"
        },
        {
          class: "el-icon-star-on"
        },
        {
          class: "el-icon-paperclip"
        },
        {
          class: "el-icon-edit"
        }
      ]
    }
  },

  // 当页面加载时获取数据
  created() {
    this.fetchData()
  },

  methods: {
    // 调用api层获取数据库中的数据
    fetchData() {
      this.isPreList('btn.menu.list')
      findNodes().then(response => {
        this.sysMenuList = response.data
      })
    },

    // 根据id删除数据
    removeDataById(row) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return del(row.id)
      }).then((response) => {
        this.fetchData()
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message.info('取消删除')
      })
    },

    // -------------
    add(row){
      this.typeDisabled = false
      this.dialogTitle = '添加下级节点'
      this.dialogVisible = true

      this.sysMenu = Object.assign({}, defaultForm)
      this.sysMenu.id = ''
      if(row.id !== undefined) {
        this.sysMenu.parentId = row.id
        this.sysMenu.parentName = row.name
        if(row.type === '0') {
          this.sysMenu.type = '1'
          this.typeDisabled = false
          this.type0Disabled = false
          this.type1Disabled = false
          this.type2Disabled = true
        } else if(row.type === '1') {
          this.sysMenu.type = '2'
          this.typeDisabled = true
        }
      } else {
        this.dialogTitle = '添加目录节点'
        this.sysMenu.type = '0'
        this.sysMenu.component = 'Layout'
        this.sysMenu.parentId = '0'
        this.typeDisabled = true
      }
    },

    edit(row) {
      debugger
      this.dialogTitle = '修改节点'
      this.dialogVisible = true

      this.sysMenu = Object.assign({}, row)
      this.typeDisabled = true
    },

    saveOrUpdate() {
      if(this.sysMenu.type === '0' && this.sysMenu.parentId !== '0') {
        this.sysMenu.component = 'ParentView'
      }
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          this.saveBtnDisabled = true // 防止表单重复提交
          if (!this.sysMenu.id) {
            this.saveData()
          } else {
            this.updateData()
          }
        }
      })
    },

    // 新增
    saveData() {
      add(this.sysMenu).then(response => {
        this.$message.success(response.message || '操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    },

    // 根据id更新记录
    updateData() {
      update(this.sysMenu).then(response => {
        this.$message.success(response.message || '操作成功')
        this.dialogVisible = false
        this.fetchData()
      })
    }
  }
}
</script>
