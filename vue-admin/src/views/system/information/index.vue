<template>
  <div class="app-container" >
    <el-row :gutter="20">
      <el-col :span="16"><el-form ref="from" :model="formData" :rules="rules" size="small" label-width="100px">
        <el-divider content-position="left">社交</el-divider>
        <el-row type="flex" justify="start" align="top" >
          <el-form-item label="微信" prop="wechat">
            <el-input v-model="formData.wechat" placeholder="请输入微信" :maxlength="50" clearable
                      prefix-icon='el-icon-position' :style="{width: '100%'}"></el-input>
          </el-form-item>
          <el-form-item label="QQ" prop="qq">
            <el-input v-model="formData.qq" placeholder="请输入QQ" :maxlength="20" clearable
                      prefix-icon='el-icon-position' :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-row>
        <el-row >
          <el-form-item label="GitEE" prop="gitee">
            <el-input v-model="formData.gitee" placeholder="请输入GitEE" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="GitHub" prop="github">
            <el-input v-model="formData.github" placeholder="请输入GitHub" clearable prefix-icon='el-icon-position'
                      :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-row>
        <el-divider content-position="left">网站信息</el-divider>
        <el-row >
          <el-form-item label="网站名称" prop="webName">
            <el-input v-model="formData.webName" placeholder="请输入网站名称" :maxlength="20" clearable
                      prefix-icon='el-icon-position' :style="{width: '100%'}"></el-input>
          </el-form-item>
          <el-form-item label="网站详情" prop="webDetails">
            <el-input v-model="formData.webDetails" type="textarea" placeholder="请输入网站详情" :maxlength="100"
                      show-word-limit :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-row>
        <el-form-item size="large">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form></el-col>
    </el-row>

  </div>
</template>
<script>
import { getInformation,updateInformation } from "@/api/system/information";

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        wechat: undefined,
        qq: undefined,
        gitee: undefined,
        github: undefined,
        webName: undefined,
        webDetails: undefined,
        id: undefined
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
  mounted() {},
  methods: {
    getInfo(){
      getInformation().then(response=>{
        this.formData = response.data
      })
    },
    submitForm() {
      this.$refs['from'].validate(valid => {
        if (!valid) return
        updateInformation(this.formData).then(response=>{
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
