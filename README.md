## <center> Vblog
#### <center> 基于SpringBoot+Vue前后端分离的个人博客项目


### 项目介绍
轻量级的博客项目，没有弄那些花里胡哨的功能。开源不易，如喜欢请帮忙点个star ，各位的star也是我对该项目后期维护和新增功能的动力。

### 技术栈
- SpringBoot
- Redis
- RabbitMQ
- Sa-Token
- Mybatis-Plus
- MySql
- Swagger
- AJ-Captcha
- Quartz
- Vue
- Element-UI

### 后台功能
1. 文章管理:文章列表,支持Markdown编写博客，可直接复制图片到编辑器上自动上传OSS(可切换本地),文章归档。
2. 资源管理：网站的收藏夹可直达站点，可上传预览图。
3. 友链管理：维护友链。
4. 图片管理：重复使用的图片可上传图片管理，方便二次调用。图片上传分为本地和OSS可切换。
5. 系统管理：字典表维护，系统参数设置，定时计划的维护，网站基本信息配置，公告维护，日志维护。
6. 个人信息：头像修改，密码修改。
7. 登录：Sa-Token管理，图形验证码校验。
8. 评论列表，评论回复可通过邮箱通知留言者(可关闭),评论消息发送通过RabbitMQ(可关闭)。
9. 题库(2023/12/15添加)

### 前台功能
1. 文章列表，详情展示。
2. 文章归档。
3. 文章留言。
4. 友链展示。

### 在线站点

[前台博客端](http://jzjzzzz.cn/)


### 启动教程
#### 后端
1. 项目下db目录内寻找最新日期sql文件运行。
2. 修改dev下redis，mysql配置信息，这时即可通过VblogApplication启动项目。
3. 如不需要oss和邮箱功能可去系统管理-参数设置处关闭，如开启状态下需要在dev里填入相关秘钥即可。

#### 后台前端页面（vue-admin）
1. npm install
2. npm run dev
3. 后台初始账号密码：admin,123456

#### 前台前端页面（vue-front-desk）
1. npm install
2. npm run dev

### 演示图（前台博客端请移步站点查看）
![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/2f24cb06652d4d0badae95b0216ee7a3.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/dbc0748747f24f12ab1d16df6e8dc739.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/88b93384e1734b94a179f6c194a90876.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/f95dc2067b12427ba1d99377aef10716.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/a102756ac2e14d5981a704d2cabbd419.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/82a0cb2e06bf455994361946f24520a7.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/4c48b05744b3421ba3cd8ad0d50d9cad.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/889a01cd9873433fac5434a24dcac2cb.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/44e184b1f2a94616b6fa8720d83f559a.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/0deae15f769f4e8c973302875aa891f9.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/780c18a8dfd741e582030831bd10a657.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/82a970e349bb486bbca24dc20ebe473b.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/d783b2e0d79548aab1187e3988b6fb0e.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/0a73001048ab4cb2815fb2aa2cd9d024.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2022/12/14/a82883ffc73c4d5f90065bcdf8c5f652.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2023/12/15/6493ab13bd284bbb90f1666405910f71.png)

![image.png](https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/article/2023/12/15/5a20f605bc684e1b8b68b17c16278704.png)



### 问题解决

如遇到bug，可在项目lssues提问，大家有好的修改都可提交，感谢！
