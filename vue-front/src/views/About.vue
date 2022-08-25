<template>
    <div class="about">
     <div>
       <banner isHome="true"></banner>
     </div>
        <div class="site-content">
            <div class="content-warp">
                <div class="about-site about-info">
                    <section-title><span>❤</span>关于博客</section-title>
                    <div class="info-card">
                        <p>本博客前台采用了<a target="_blank" class="out-link" href="https://gitee.com/fengziy/Gblog">Gblog</a>博客的主题，作为二次开发，后端使用SpringBoot。
                        </p>
                        <p><a target="_blank" href="https://gitee.com/jzjzz" style="color: #ff6d6d;">Gitee仓库</a> | <a target="_blank" href="https://github.com/Jzjzzzz" style="color: #ff6d6d;">Github仓库</a>记得Star哟</p>
                    </div>
                </div>

              <div class="about-site about-info">
                <section-title><span>❤</span>版本更新</section-title>
                <div class="info-card">
                  <div class="block">
                    <el-timeline>
                      <el-timeline-item timestamp="2022/7/19" placement="top">
                        <el-card>
                          <h4>构建项目后端架构</h4>
                          <p>漫漫长路 2022 年 7 月 19 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/7/21" placement="top">
                        <el-card>
                          <h4>新增字典管理</h4>
                          <p>漫漫长路 2022 年 7 月 21 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item  timestamp="2022/7/22" placement="top">
                        <el-card>
                          <h4>系统设置,日志模块,公告管理</h4>
                          <p>漫漫长路 2022 年 7 月 22 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/7/27" placement="top">
                        <el-card>
                          <h4>文件上传(本地上传,OSS上传)</h4>
                          <p>漫漫长路 2022 年 7 月 27 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/5" placement="top">
                        <el-card>
                          <h4>博客管理</h4>
                          <p>漫漫长路 2022 年 8 月 5 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/9" placement="top">
                        <el-card>
                          <h4>前台文章显示</h4>
                          <p>漫漫长路 2022 年 8 月 9 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/11" placement="top">
                        <el-card>
                          <h4>文章归档管理</h4>
                          <p>漫漫长路 2022 年 8 月 11 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/14" placement="top">
                        <el-card>
                          <h4>后台首页图表，新增备忘录</h4>
                          <p>漫漫长路 2022 年 8 月 14 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/18" placement="top">
                        <el-card>
                          <h4>新增spring-retry轮询重试功能</h4>
                          <p>漫漫长路 2022 年 8 月 18 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/19" placement="top">
                        <el-card>
                          <h4>整合sa-token,登录校验</h4>
                          <p>漫漫长路 2022 年 8 月 19 日提交</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2022/8/22" placement="top">
                        <el-card>
                          <h4>新增定时计划管理，项目增加定时计划框架Quartz</h4>
                          <p>漫漫长路 2022 年 8 月 22 日提交</p>
                        </el-card>
                      </el-timeline-item>

                      <el-timeline-item type="success" timestamp="2022/8/25" placement="top">
                        <el-card>
                          <h4>后台登录添加AJ-Captcha行为验证码</h4>
                          <p>漫漫长路 2022 年 8 月 25 日提交</p>
                        </el-card>
                      </el-timeline-item>
                    </el-timeline>
                  </div>
                </div>
              </div>
            </div>
        </div>
    </div>
</template>
<script>
import sectionTitle from '@/components/section-title'
import Banner from '@/components/banner'

export default {
  name: "About",
  data() {
    return {
      list: []
    }
  },
  components: {
    Banner,
    sectionTitle
  },
        methods: {},
        mounted() {
        }
    }
</script>
<style scoped lang="less">
    .about {
        padding-top: 40px;
    }

    .content-warp {
        margin-top: 80px;

        .about-info {
            margin: 30px 0;

            span {
                color: red;
                margin-right: 10px;
            }

            .info-card {
                min-height: 100px;
                padding: 20px;
                border-radius: 3px;
                margin: 30px 0 50px 0;
                box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
                p{
                    line-height: 1.7rem;
                }
            }
        }
        .contactForm{
            width: 100%;
            padding: 20px;
            .form-item{
                align-items: center;
                display: flex;
                &:not(:last-child){
                    margin-bottom: 20px;
                }
                label{
                  width: 80px;
                }
                .v{
                    min-height: 40px;
                    line-height: 20px;
                    border-radius: 3px;
                    padding: 2px 10px;
                    outline:none;
                    border: 1px solid #8fd0cc;
                    width: 100%;
                    resize: vertical;
                }
                button{
                    width: 100px;
                    height: 40px;
                    border-radius: 3px;
                    outline:0;
                    border-style: none;
                    cursor: pointer;
                    background-color: #409eff;
                    color: white;
                    &:hover{
                        opacity: 0.8;
                    }
                }
            }
        }
    }

    /*******/
    @media (max-width: 800px) {
        .content-warp {
            margin-top: 0;
        }
    }
</style>
