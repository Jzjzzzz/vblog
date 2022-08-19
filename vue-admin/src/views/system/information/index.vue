<template>
  <div class="app-container">

    <el-row :gutter="20">
      <el-col :span="12" :offset="6">
        <el-form ref="from" :model="formData" :rules="rules" size="small" label-width="100px">
          <el-form-item label="微信" prop="wechat">
            <el-input v-model="formData.wechat" placeholder="请输入微信" :maxlength="50" clearable
                      prefix-icon='el-icon-position' :style="{width: '100%'}"></el-input>
          </el-form-item>
          <el-form-item label="秋秋" prop="qq">
            <el-input v-model="formData.qq" placeholder="请输入QQ" :maxlength="20" clearable
                      prefix-icon='el-icon-position' :style="{width: '100%'}"></el-input>
          </el-form-item>
          <el-form-item label="GitEE" prop="gitee">
            <el-input v-model="formData.gitee" placeholder="请输入GitEE" prefix-icon='el-icon-position' clearable
                      :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="GitHub" prop="github">
            <el-input v-model="formData.github" placeholder="请输入GitHub" clearable prefix-icon='el-icon-position'
                      :style="{width: '100%'}"></el-input>
          </el-form-item>
          <el-form-item label="网站名称" prop="webName">
            <el-input v-model="formData.webName" placeholder="请输入网站名称" :maxlength="20" clearable
                      prefix-icon='el-icon-position' :style="{width: '100%'}"></el-input>
          </el-form-item>
          <el-form-item label="首页头像" prop="logImg">
            <ele-upload-image
              :action="BASE_API+uploadUrl"
              v-model="formData.webAvatar"
              :responseFn="handleResponse"
              :isShowSuccessTip="false"
              :fileSize="5"
              :file-type="imgType"
              :beforeRemove="beforeRemove"
            ></ele-upload-image>
          </el-form-item>
          <el-form-item label="首页背景图" prop="homeBanner">
            <ele-upload-image
              :action="BASE_API+uploadUrl"
              v-model="formData.homeBanner"
              :responseFn="handleResponse"
              :isShowSuccessTip="false"
              :fileSize="20"
              :file-type="imgType"
              :beforeRemove="beforeRemove"
            ></ele-upload-image>
          </el-form-item>
          <el-form-item label="网站详情" prop="webDetails" >
            <editor v-model="formData.webDetails"  :min-height="192" ></editor>
          </el-form-item>
          <el-form-item size="large">
            <el-button size="medium" type="primary" @click="submitForm">提交</el-button>
            <el-button size="medium" @click="resetForm">重置</el-button>
            <el-button size="medium" type="danger" @click="handleRefreshCache" icon="el-icon-refresh">刷新缓存</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import {getInformation, refreshCache, updateInformation} from "@/api/system/information";
import {deleteImg} from "@/api/upload";

export default {
  components: {},
  props: [],
  data() {
    return {
      BASE_API: process.env.VUE_APP_BASE_API, // 接口API地址
      uploadUrl:'/api/upload/uploadImg?name=webLogo',
      imgPath: '',
      //文件上传类型
      imgType: ['png', 'jpg', 'jpeg'],
      formData: {
        wechat: undefined,
        qq: undefined,
        gitee: undefined,
        github: undefined,
        webName: undefined,
        webDetails: undefined,
        webAvatar: undefined,
        id: undefined,
        homeBanner: undefined,
        articleBanner: undefined
      },
      rules: {
        webName: [{
          required: true,
          message: '请输入网站名称',
          trigger: 'blur'
        }],
      },
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
    /** 刷新缓存按钮操作 */
    handleRefreshCache() {
      refreshCache().then(() => {
        this.$modal.msgSuccess("刷新成功");
        this.getInfo()
      });
    },
    //图片回显
    handleResponse(response) {
      if (response.code === 20000) {
        this.$modal.msgSuccess("上传文件成功")
        this.imgPath = response.data.url
        return response.data.url;
      }
      return this.$modal.msgError("上传文件失败");
    },
    //删除图片
    beforeRemove() {
      deleteImg(this.imgPath).then(response => {
        this.$modal.msgSuccess("删除成功")
      })
    },
    getInfo() {
      getInformation().then(response => {
        this.formData = response.data
        this.imgPath = this.formData.webAvatar
      })
    },
    submitForm() {
      this.$refs['from'].validate(valid => {
        if (!valid) return
        updateInformation(this.formData).then(response => {
          this.$modal.msgSuccess("修改成功");
          this.getInfo()
        })
      })
    },
    resetForm() {
      this.$refs['from'].resetFields()
    },
  }
}
</script>
<style>
</style>
