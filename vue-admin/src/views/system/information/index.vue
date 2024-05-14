<template>
  <div class="app-container">
    <el-form ref="from" :model="formData" :rules="rules" size="small" label-width="auto">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基础信息" name="first">
          <el-row :gutter="24">
            <el-col :span="12">
              <div class="grid-content bg-purple">
                <el-form-item label="微信" prop="wechat">
                  <el-input
                    v-model="formData.wechat"
                    placeholder="请输入微信"
                    :maxlength="50"
                    clearable
                    prefix-icon="el-icon-position"
                    :style="{width: '100%'}"
                  />
                </el-form-item>
                <el-form-item label="Gitee" prop="gitee">
                  <el-input
                    v-model="formData.gitee"
                    placeholder="请输入Gitee"
                    prefix-icon="el-icon-position"
                    clearable
                    :style="{width: '100%'}"
                  />
                </el-form-item>
                <el-form-item label="GitHub" prop="github">
                  <el-input
                    v-model="formData.github"
                    placeholder="请输入GitHub"
                    clearable
                    prefix-icon="el-icon-position"
                    :style="{width: '100%'}"
                  />
                </el-form-item>
                <el-form-item label="备案号" prop="recordNumber">
                  <el-input
                    v-model="formData.recordNumber"
                    placeholder="请输入备案号"
                    clearable
                    prefix-icon="el-icon-position"
                    :style="{width: '100%'}"
                  />
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="grid-content bg-purple-light">
                <el-form-item label="秋秋" prop="qq">
                  <el-input
                    v-model="formData.qq"
                    placeholder="请输入QQ"
                    :maxlength="20"
                    clearable
                    prefix-icon="el-icon-position"
                    :style="{width: '100%'}"
                  />
                </el-form-item>
                <el-form-item label="Email" prop="email">
                  <el-input
                    v-model="formData.email"
                    placeholder="请输入Email"
                    prefix-icon="el-icon-position"
                    clearable
                    :style="{width: '100%'}"
                  />
                </el-form-item>
                <el-form-item label="网站名称" prop="webName">
                  <el-input
                    v-model="formData.webName"
                    placeholder="请输入网站名称"
                    :maxlength="20"
                    clearable
                    prefix-icon="el-icon-position"
                    :style="{width: '100%'}"
                  />
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="24">
              <el-form-item label="网站详情" prop="webDetails">
                <editor v-model="formData.webDetails" :min-height="192" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="图片相关" name="second">
          <el-row :gutter="24">
            <el-col :span="1" :offset="7">
              <div class="grid-content bg-purple">
                <el-form-item label="微信二维码" prop="wechatImg">
                  <ele-upload-image
                    v-model="formData.wechatImg"
                    :action="BASE_API+uploadUrl"
                    :response-fn="handleResponse"
                    :is-show-success-tip="false"
                    :file-size="20"
                    :file-type="imgType"
                    :before-remove="beforeRemove"
                    :headers="header"
                  />
                </el-form-item>
                <el-form-item label="首页头像" prop="logImg">
                  <ele-upload-image
                    v-model="formData.webAvatar"
                    :action="BASE_API+uploadUrl"
                    :response-fn="handleResponse"
                    :is-show-success-tip="false"
                    :file-size="5"
                    :file-type="imgType"
                    :before-remove="beforeRemove"
                    :headers="header"
                  />
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="1" :offset="2">
              <div class="grid-content bg-purple">
                <el-form-item label="首页背景图" prop="homeBanner">
                  <ele-upload-image
                    v-model="formData.homeBanner"
                    :action="BASE_API+uploadUrl"
                    :response-fn="handleResponse"
                    :is-show-success-tip="false"
                    :file-size="20"
                    :file-type="imgType"
                    :before-remove="beforeRemove"
                    :headers="header"
                  />
                </el-form-item>
                <el-form-item label="评论头像" prop="commentImg">
                  <ele-upload-image
                    v-model="formData.commentImg"
                    :action="BASE_API+uploadUrl"
                    :response-fn="handleResponse"
                    :is-show-success-tip="false"
                    :file-size="20"
                    :file-type="imgType"
                    :before-remove="beforeRemove"
                    :headers="header"
                  />
                </el-form-item>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
      <el-row :gutter="20">
        <el-col :span="12" :offset="8">
          <div class="grid-content bg-purple">
            <el-form-item size="large">
              <el-button size="medium" type="primary" @click="submitForm" :disabled="$hasBP('btn.itembank.edit')  === false">提交</el-button>
              <el-button size="medium" @click="resetForm">重置</el-button>
              <el-button size="medium" type="danger" icon="el-icon-refresh" @click="handleRefreshCache" :disabled="$hasBP('btn.itembank.edit')  === false">刷新缓存</el-button>
            </el-form-item>
          </div>
        </el-col>
      </el-row>

    </el-form>
  </div>
</template>
<script>
import { getInformation, refreshCache, updateInformation } from '@/api/system/information'
import { deleteImg } from '@/api/upload'
import {getToken} from '@/utils/auth'
export default {
  components: {},
  props: [],
  data() {
    return {
      activeName: 'first',
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl: 'api/upload/uploadImg?name=webLogo',
      header:{
        token: getToken()
      },
      imgPath: '',
      // 文件上传类型
      imgType: ['png', 'jpg', 'jpeg'],
      formData: {
        wechat: undefined,
        qq: undefined,
        gitee: undefined,
        github: undefined,
        webName: undefined,
        webDetails: undefined,
        webAvatar: undefined,
        email:undefined,
        id: undefined,
        homeBanner: undefined,
        articleBanner: undefined,
        wechatImg: undefined,
        commentImg: undefined,
        recordNumber: undefined
      },
      rules: {
        webName: [{
          required: true,
          message: '请输入网站名称',
          trigger: 'blur'
        }]
      }
    }
  },
  computed: {},
  watch: {},
  created() {
    this.getInfo()
  },
  mounted() {
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    /** 刷新缓存按钮操作 */
    handleRefreshCache() {
      refreshCache().then(() => {
        this.$modal.msgSuccess('刷新成功')
        this.getInfo()
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
    getInfo() {
      this.isPreList('btn.information.list')
      getInformation().then(response => {
        this.formData = response.data
        this.imgPath = this.formData.webAvatar
      })
    },
    submitForm() {
      this.$refs['from'].validate(valid => {
        if (!valid) return
        updateInformation(this.formData).then(response => {
          this.$modal.msgSuccess('修改成功')
          this.getInfo()
        })
      })
    },
    resetForm() {
      this.$refs['from'].resetFields()
    }
  }
}
</script>
<style>
</style>
