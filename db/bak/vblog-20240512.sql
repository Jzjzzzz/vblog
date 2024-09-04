/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : vblog

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 13/05/2024 16:42:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `parent_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为父节点',
  `article_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章id',
  `comment_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论类型(0-留言板,1-文章评论)',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论ip地址',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属省市',
  `audit_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核状态(0-未审核,1-审核通过,2-审核未通过)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '留言板' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_comment
-- ----------------------------

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容id',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `html_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容转html',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章内容' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_content
-- ----------------------------

-- ----------------------------
-- Table structure for article_inform
-- ----------------------------
DROP TABLE IF EXISTS `article_inform`;
CREATE TABLE `article_inform`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0-下架，1-发布）',
  `article_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `log_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题图',
  `article_type` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章分类',
  `article_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标签',
  `top_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否置顶（0-不置顶，1-置顶）',
  `origin_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否原创（0-原创，1-转载）',
  `comment_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否开启评论（0-不开启，1-开启）',
  `click_rate` bigint(20) NULL DEFAULT NULL COMMENT '文章点击量',
  `number_like` bigint(20) NULL DEFAULT NULL COMMENT '点赞数',
  `aggregate_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归档id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `comment_number` int(11) NULL DEFAULT NULL COMMENT '评论数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章基础信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_inform
-- ----------------------------

-- ----------------------------
-- Table structure for article_summary
-- ----------------------------
DROP TABLE IF EXISTS `article_summary`;
CREATE TABLE `article_summary`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '归档表id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归档名称',
  `banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归档图',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `top_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否显示在首页',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章归档' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_summary
-- ----------------------------

-- ----------------------------
-- Table structure for friend_link
-- ----------------------------
DROP TABLE IF EXISTS `friend_link`;
CREATE TABLE `friend_link`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接名称',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '友情链接' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend_link
-- ----------------------------
INSERT INTO `friend_link` VALUES ('d855c662eddc70853cee7304bb1c1b05', 'Jzjzzzz', 'https://www.jzjzzzz.icu/', '2022-09-10 00:50:33', '2022-09-10 00:50:33', '1');

-- ----------------------------
-- Table structure for gallery
-- ----------------------------
DROP TABLE IF EXISTS `gallery`;
CREATE TABLE `gallery`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `img_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图集' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gallery
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

-- ----------------------------
-- Table structure for item_bank
-- ----------------------------
DROP TABLE IF EXISTS `item_bank`;
CREATE TABLE `item_bank`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '题目',
  `answer` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '答案',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `refresh_rate` int(11) NULL DEFAULT NULL COMMENT '刷新率',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item_bank
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('VblogScheduler', 'com.jzj.vblog.job.ArticleCountJob', 'ArticleCount', '0 0 12 * * ?', 'GMT+08:00');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('VblogScheduler', 'com.jzj.vblog.job.ArticleCountJob', 'ArticleCount', NULL, 'com.jzj.vblog.job.ArticleCountJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('VblogScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('VblogScheduler', 'com.jzj.vblog.job.ArticleCountJob', 'ArticleCount', 'com.jzj.vblog.job.ArticleCountJob', 'ArticleCount', NULL, 1715659200000, 1715575225139, 5, 'WAITING', 'CRON', 1668500317000, 0, NULL, 0, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (6, 'oss云存储-是否开启云存储', 'sys.oss.enable', 'true', 'Y', 'admin', '2022-07-27 14:52:18', 'admin', '2022-07-29 15:02:59', '开启云存储需要配置application.yml里的oss相关参数（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (7, '是否开启邮箱通知', 'sys_email_enable', 'true', 'Y', 'admin', '2022-09-20 14:45:07', 'admin', '2022-09-23 22:16:59', '后台回复留言，邮箱通知留言者,开启需要配置email相关参数 （true开启，false关闭）');
INSERT INTO `sys_config` VALUES (8, 'RabbitMQ开关', 'sys_rabbit_enable', 'false', 'Y', 'admin', '2023-01-16 16:41:31', 'admin', '2024-04-24 09:24:45', '开启-true，关闭-false（开启需要配置RabbitMQ相关参数,目前仅发送邮件用到该中间件）');

-- ----------------------------
-- Table structure for sys_count
-- ----------------------------
DROP TABLE IF EXISTS `sys_count`;
CREATE TABLE `sys_count`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `article_count` bigint(20) NULL DEFAULT NULL COMMENT '文章总数',
  `summary_count` bigint(20) NULL DEFAULT NULL COMMENT '归档总数',
  `user_count` bigint(20) NULL DEFAULT NULL COMMENT '用户总数',
  `click_count` bigint(20) NULL DEFAULT NULL COMMENT '点击总数',
  `like_count` bigint(20) NULL DEFAULT NULL COMMENT '点赞总数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `click_day_count` bigint(20) NULL DEFAULT NULL COMMENT '日点击数',
  `like_day_count` bigint(20) NULL DEFAULT NULL COMMENT '日点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '统计归档' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_count
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 173 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', 'default', 'Y', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2024-05-06 11:56:52', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', 'default', 'N', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2024-05-06 11:56:56', '性别女');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2022-07-21 11:52:18', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (101, 0, '编程', '0', 'sys_article_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:20:03', '', NULL, '编程');
INSERT INTO `sys_dict_data` VALUES (102, 1, '日常', '1', 'sys_article_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:20:43', '', NULL, '日常');
INSERT INTO `sys_dict_data` VALUES (103, 0, 'Java', '0', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:21:22', '', NULL, 'Java');
INSERT INTO `sys_dict_data` VALUES (104, 0, 'SQL', '1', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:21:38', 'admin', '2022-08-08 09:27:52', 'SQL');
INSERT INTO `sys_dict_data` VALUES (105, 1, '下架', '0', 'sys_article_status', NULL, 'danger', 'N', '0', 'admin', '2022-07-22 19:22:30', 'admin', '2022-08-19 15:48:22', '文章下架');
INSERT INTO `sys_dict_data` VALUES (106, 0, '发布', '1', 'sys_article_status', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:22:53', 'admin', '2022-08-19 15:48:16', '文章发布');
INSERT INTO `sys_dict_data` VALUES (107, 0, '默认', '0', 'sys_article_top', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:24:59', 'admin', '2022-07-22 19:27:42', '不置顶');
INSERT INTO `sys_dict_data` VALUES (108, 0, '置顶', '1', 'sys_article_top', NULL, 'success', 'N', '0', 'admin', '2022-07-22 19:25:07', 'admin', '2022-08-05 15:51:39', '置顶');
INSERT INTO `sys_dict_data` VALUES (109, 0, '原创', '0', 'sys_article_origin', NULL, 'success', 'N', '0', 'admin', '2022-07-22 19:27:31', 'admin', '2022-08-05 15:51:53', '文章原创');
INSERT INTO `sys_dict_data` VALUES (110, 0, '转载', '1', 'sys_article_origin', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:28:10', '', NULL, '转载');
INSERT INTO `sys_dict_data` VALUES (111, 0, '开启', '0', 'sys_article_comment', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:28:36', 'admin', '2022-07-22 19:28:40', '开启评论');
INSERT INTO `sys_dict_data` VALUES (112, 0, '关闭', '1', 'sys_article_comment', NULL, 'danger', 'N', '0', 'admin', '2022-07-22 19:28:54', 'admin', '2022-08-05 15:52:04', '关闭评论');
INSERT INTO `sys_dict_data` VALUES (113, 0, '工具', '0', 'sys_website_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-26 15:28:50', '', NULL, '工具');
INSERT INTO `sys_dict_data` VALUES (114, 0, 'Java', '1', 'sys_website_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-26 15:29:03', '', NULL, 'Java');
INSERT INTO `sys_dict_data` VALUES (115, 0, '前端', '2', 'sys_website_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-26 15:29:13', 'admin', '2022-07-26 15:29:18', '前端');
INSERT INTO `sys_dict_data` VALUES (118, 0, 'Spring', '2', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-08 09:27:25', 'admin', '2022-08-08 09:27:39', 'spring');
INSERT INTO `sys_dict_data` VALUES (119, 0, 'Spring MVC', '3', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-08 09:28:23', '', NULL, 'Spring MVC');
INSERT INTO `sys_dict_data` VALUES (120, 0, 'MyBatis', '4', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-08 09:28:42', 'admin', '2022-08-08 09:28:54', 'Mybatis');
INSERT INTO `sys_dict_data` VALUES (123, 0, '默认', '0', 'sys_summary_top', NULL, 'success', 'N', '0', 'admin', '2022-08-12 19:17:40', 'admin', '2022-08-12 19:43:52', '不显示在首页');
INSERT INTO `sys_dict_data` VALUES (124, 0, '展示', '1', 'sys_summary_top', NULL, 'primary', 'N', '0', 'admin', '2022-08-12 19:17:54', 'admin', '2022-08-12 19:43:48', '显示在首页');
INSERT INTO `sys_dict_data` VALUES (125, 0, 'BUG', '2', 'sys_article_type', NULL, 'primary', 'N', '0', 'admin', '2022-08-19 15:45:26', 'admin', '2022-08-19 15:45:32', 'BUG');
INSERT INTO `sys_dict_data` VALUES (126, 0, '算法', '5', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-19 15:46:31', '', NULL, '算法');
INSERT INTO `sys_dict_data` VALUES (127, 0, 'JUC', '6', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-29 09:25:52', '', NULL, 'JUC');
INSERT INTO `sys_dict_data` VALUES (130, 0, '留言板', '0', 'article_comment_type', NULL, 'primary', 'N', '0', 'admin', '2022-09-11 15:32:58', '', NULL, '留言板');
INSERT INTO `sys_dict_data` VALUES (131, 0, '文章', '1', 'article_comment_type', NULL, 'success', 'N', '0', 'admin', '2022-09-11 15:33:16', '', NULL, '文章评论');
INSERT INTO `sys_dict_data` VALUES (132, 0, '未回复', '0', 'article_comment_status', NULL, 'danger', 'N', '0', 'admin', '2022-09-11 15:35:37', 'admin', '2022-09-11 15:35:46', '未回复');
INSERT INTO `sys_dict_data` VALUES (133, 0, '已回复', '1', 'article_comment_status', NULL, 'success', 'N', '0', 'admin', '2022-09-11 15:35:58', 'admin', '2022-09-11 15:36:10', '已回复');
INSERT INTO `sys_dict_data` VALUES (134, 0, '未审核', '0', 'article_comment_audit_status', NULL, 'info', 'N', '0', 'admin', '2022-09-23 21:08:23', '', NULL, '未审核');
INSERT INTO `sys_dict_data` VALUES (135, 0, '通过', '1', 'article_comment_audit_status', NULL, 'success', 'N', '0', 'admin', '2022-09-23 21:08:41', '', NULL, '通过');
INSERT INTO `sys_dict_data` VALUES (136, 0, '未通过', '2', 'article_comment_audit_status', NULL, 'danger', 'N', '0', 'admin', '2022-09-23 21:09:02', '', NULL, '未通过');
INSERT INTO `sys_dict_data` VALUES (137, 0, 'OSS', '7', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-09-26 10:06:37', '', NULL, 'OSS');
INSERT INTO `sys_dict_data` VALUES (138, 0, '短信', '9', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-09-26 14:51:32', '', NULL, '短信');
INSERT INTO `sys_dict_data` VALUES (139, 0, 'NIO', '10', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-09-30 15:07:08', '', NULL, 'NIO');
INSERT INTO `sys_dict_data` VALUES (140, 0, 'Netty', '11', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-10-14 14:57:51', 'admin', '2022-10-14 14:58:09', NULL);
INSERT INTO `sys_dict_data` VALUES (141, 0, 'API', '12', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-11-15 08:24:18', 'admin', '2022-11-15 08:24:24', '');
INSERT INTO `sys_dict_data` VALUES (142, 0, '中间件', '13', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-11-22 02:51:33', '', NULL, '中间件');
INSERT INTO `sys_dict_data` VALUES (143, 0, '开源项目', '3', 'sys_website_type', NULL, 'primary', 'N', '0', 'admin', '2022-12-02 09:13:24', '', NULL, '开源项目');
INSERT INTO `sys_dict_data` VALUES (144, 0, '资料', '4', 'sys_website_type', NULL, 'primary', 'N', '0', 'admin', '2022-12-02 09:13:38', '', NULL, '资料');
INSERT INTO `sys_dict_data` VALUES (145, 0, 'UI', '5', 'sys_website_type', NULL, 'primary', 'N', '0', 'admin', '2022-12-02 09:13:52', '', NULL, 'UI');
INSERT INTO `sys_dict_data` VALUES (146, 0, '文章', '6', 'sys_website_type', NULL, 'info', 'N', '0', 'admin', '2022-12-02 09:16:38', 'admin', '2022-12-02 09:19:10', '文章');
INSERT INTO `sys_dict_data` VALUES (147, 0, '文档', '14', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-12-14 07:49:27', '', NULL, '文档');
INSERT INTO `sys_dict_data` VALUES (148, 0, 'Docker', '15', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-01-05 09:17:07', '', NULL, 'Docker');
INSERT INTO `sys_dict_data` VALUES (149, 0, 'RabbitMQ', '16', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-01-05 09:17:30', '', NULL, 'RabbitMQ');
INSERT INTO `sys_dict_data` VALUES (150, 0, 'ElasticSearch', '17', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-02-10 02:18:42', '', NULL, 'ElasticSearch');
INSERT INTO `sys_dict_data` VALUES (151, 0, 'Logstash', '18', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-02-10 02:19:12', '', NULL, 'Logstash');
INSERT INTO `sys_dict_data` VALUES (152, 0, 'Java基础', '0', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:46:41', '', NULL, 'Java基础');
INSERT INTO `sys_dict_data` VALUES (153, 0, 'MySQL', '1', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:47:06', '', NULL, 'MySQL');
INSERT INTO `sys_dict_data` VALUES (154, 0, 'Spring', '2', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:47:19', '', NULL, 'Spring');
INSERT INTO `sys_dict_data` VALUES (155, 0, 'Spring MVC', '3', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:47:36', '', NULL, 'Spring MVC');
INSERT INTO `sys_dict_data` VALUES (156, 0, 'Spring Boot', '4', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:47:50', '', NULL, 'Spring Boot');
INSERT INTO `sys_dict_data` VALUES (157, 0, 'JVM', '5', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:48:05', '', NULL, 'JVM');
INSERT INTO `sys_dict_data` VALUES (158, 0, 'JUC', '6', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-08 07:48:19', '', NULL, 'JUC');
INSERT INTO `sys_dict_data` VALUES (159, 0, 'Redis', '8', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-09 07:18:41', '', NULL, 'Redis');
INSERT INTO `sys_dict_data` VALUES (160, 0, 'Spring Cloud', '9', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-06-15 03:22:27', '', NULL, 'Spring Cloud');
INSERT INTO `sys_dict_data` VALUES (161, 0, 'k8s', '19', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-04 07:48:46', '', NULL, 'k8s');
INSERT INTO `sys_dict_data` VALUES (162, 0, '云原生', '20', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-04 07:49:09', '', NULL, '云原生');
INSERT INTO `sys_dict_data` VALUES (163, 0, 'ElasticSearch', '10', 'sys_item_bank_type', NULL, 'primary', 'N', '0', 'admin', '2023-07-10 01:07:20', '', NULL, 'ElasticSearch');
INSERT INTO `sys_dict_data` VALUES (165, 0, 'SpringBoot', '21', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-13 07:59:30', 'admin', '2023-07-13 07:59:40', 'SpringBoot');
INSERT INTO `sys_dict_data` VALUES (166, 0, 'Swagger', '22', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-13 08:00:01', '', NULL, 'Swagger');
INSERT INTO `sys_dict_data` VALUES (167, 0, 'Redis', '23', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-13 08:42:52', '', NULL, 'Redis');
INSERT INTO `sys_dict_data` VALUES (168, 0, 'uni-app', '24', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-09-05 03:25:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (171, 0, '正常', '1', 'currency_status', NULL, 'success', 'N', '0', 'admin', '2024-05-06 15:46:35', '', NULL, '正常');
INSERT INTO `sys_dict_data` VALUES (172, 0, '停用', '0', 'currency_status', NULL, 'danger', 'N', '0', 'admin', '2024-05-06 15:47:01', 'admin', '2024-05-06 15:47:11', '停用');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2022-07-22 06:57:38', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '文章分类', 'sys_article_type', '0', 'admin', '2022-07-20 01:14:22', 'admin', '2024-05-06 10:34:21', '文章分类列表');
INSERT INTO `sys_dict_type` VALUES (104, '文章标签', 'sys_article_tag', '0', 'admin', '2022-07-22 01:19:31', 'admin', '2024-05-06 10:34:19', '文章标签列表');
INSERT INTO `sys_dict_type` VALUES (106, '文章状态', 'sys_article_status', '0', 'admin', '2022-07-22 17:09:06', 'admin', '2024-05-06 10:34:17', '文章状态列表');
INSERT INTO `sys_dict_type` VALUES (107, '文章置顶', 'sys_article_top', '0', 'admin', '2022-07-22 19:23:51', 'admin', '2024-05-06 10:34:13', '文章是否置顶列表');
INSERT INTO `sys_dict_type` VALUES (108, '文章原创', 'sys_article_origin', '0', 'admin', '2022-07-22 19:26:02', 'admin', '2024-05-06 10:34:10', '文章是否原创列表');
INSERT INTO `sys_dict_type` VALUES (109, '文章评论', 'sys_article_comment', '0', 'admin', '2022-07-22 19:26:44', 'admin', '2024-05-06 10:34:07', '是否开启评论列表');
INSERT INTO `sys_dict_type` VALUES (110, '资源分类', 'sys_website_type', '0', 'admin', '2022-07-26 15:27:22', 'admin', '2024-05-06 10:34:04', '资源分类列表');
INSERT INTO `sys_dict_type` VALUES (113, '归档置顶', 'sys_summary_top', '0', 'admin', '2022-08-12 19:17:03', 'admin', '2024-05-06 10:33:56', '归档置顶列表');
INSERT INTO `sys_dict_type` VALUES (115, '评论类型', 'article_comment_type', '0', 'admin', '2022-09-11 15:32:13', 'admin', '2024-05-06 10:33:49', '评论类型列表');
INSERT INTO `sys_dict_type` VALUES (116, '评论状态', 'article_comment_status', '0', 'admin', '2022-09-11 15:35:16', 'admin', '2024-05-06 10:33:46', '评论状态列表');
INSERT INTO `sys_dict_type` VALUES (117, '评论审核状态', 'article_comment_audit_status', '0', 'admin', '2022-09-23 21:08:01', 'admin', '2024-05-06 10:33:44', '评论审核状态列表');
INSERT INTO `sys_dict_type` VALUES (118, '题目类型', 'sys_item_bank_type', '0', 'admin', '2023-06-08 07:46:18', 'admin', '2024-05-06 10:33:36', '题目类型列表');
INSERT INTO `sys_dict_type` VALUES (120, '通用状态', 'currency_status', '0', 'admin', '2024-05-06 15:46:11', '', NULL, '通用状态列表');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-04-27 02:14:22');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-05-13 07:16:40');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-11 01:19:05');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-11 06:03:09');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-11 07:08:20');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-20 00:52:08');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 08:29:27');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '1', '验证码错误', '2022-07-21 09:20:15');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 09:20:21');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '退出成功', '2022-07-21 09:30:15');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 09:30:22');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 11:05:38');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '所属上级',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint(3) NOT NULL DEFAULT 0 COMMENT '类型(0:目录,1:菜单,2:按钮)',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort_value` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0186f5e32d71f917bd4cb4d693346cde', '8be50fe7341b636c2115ea7c9cf36854', '列表', 2, '', '', 'btn.config.list', '', 1, '1', '2024-05-11 17:50:49', '2024-05-11 17:50:49', '0');
INSERT INTO `sys_menu` VALUES ('1298ab16f667edbece17a2b73cf51fc0', '8be50fe7341b636c2115ea7c9cf36854', '删除', 2, '', '', 'btn.config.del', '', 1, '1', '2024-05-11 17:50:33', '2024-05-11 17:50:33', '0');
INSERT INTO `sys_menu` VALUES ('12c926c9e528aa15481a552b51c646e1', '749fac6f70563b29de79b81e3ed50a00', '修改', 2, '', '', 'btn.comment.edit', '', 1, '1', '2024-05-11 16:38:26', '2024-05-11 16:38:26', '0');
INSERT INTO `sys_menu` VALUES ('1417e20b26a17e778fb2c6356601e7d1', '6abd125b6490c29c54739d9d0ca27ae9', '删除', 2, '', '', 'btn.gallery.del', '', 1, '1', '2024-05-11 17:01:53', '2024-05-11 17:01:53', '0');
INSERT INTO `sys_menu` VALUES ('16e3dc94b0a0b0ab3eb491d4e172295d', 'be190c8cd6238ea9119a0befa954cf7a', '网站信息', 1, 'information', 'system/information/index', '', 'el-icon-s-platform', 8, '1', '2024-05-08 10:42:56', '2024-05-08 10:46:20', '0');
INSERT INTO `sys_menu` VALUES ('1f2da8d3b50d9ae744279bdeb3694df0', '749fac6f70563b29de79b81e3ed50a00', '删除', 2, '', '', 'btn.comment.del', '', 1, '1', '2024-05-11 16:39:36', '2024-05-11 16:39:36', '0');
INSERT INTO `sys_menu` VALUES ('240307c217e98687e93b05c16a5424aa', '8be50fe7341b636c2115ea7c9cf36854', '修改', 2, '', '', 'btn.config.edit', '', 1, '1', '2024-05-11 17:50:21', '2024-05-11 17:50:21', '0');
INSERT INTO `sys_menu` VALUES ('271bc0f236eaa1afabfec96a91d5cc5d', '5f3c62238b5a3dc58f05bc560cb4d599', '文章列表', 1, 'index', 'article/index', '', 'el-icon-document-remove', 1, '1', '2024-05-07 15:12:34', '2024-05-07 17:47:37', '0');
INSERT INTO `sys_menu` VALUES ('285fca26989c2c49978d9853fb38197c', '271bc0f236eaa1afabfec96a91d5cc5d', '列表', 2, '', '', 'btn.article.list', '', 1, '1', '2024-05-07 15:17:49', '2024-05-13 09:55:12', '0');
INSERT INTO `sys_menu` VALUES ('2d812ec5ddaedd70c93a2f78ab984334', '5ef83c1291ff3e1f435c3dffbf1be55a', '删除', 2, '', '', 'btn.notice.del', '', 1, '1', '2024-05-11 17:34:43', '2024-05-11 17:34:43', '0');
INSERT INTO `sys_menu` VALUES ('30318a16f713de861d99a789e0caac77', '85413b9370843cdb477dfbabf62bf6e5', '删除', 2, '', '', 'btn.dict.del', '', 1, '1', '2024-05-11 17:39:28', '2024-05-11 17:39:28', '0');
INSERT INTO `sys_menu` VALUES ('36c3988c159b20bc56f28f98f4b4aec9', '700e6284217d6743c74bc62efa508239', '删除', 2, '', '', 'btn.itembank.del', '', 1, '1', '2024-05-11 16:55:46', '2024-05-11 16:55:46', '0');
INSERT INTO `sys_menu` VALUES ('3aac4a4b0b7284bc52b5308e776d14ea', '440867f56dbf23944789d672b158bd32', '删除', 2, '', '', 'btn.role.del', '', 1, '1', '2024-05-11 17:14:33', '2024-05-11 17:14:33', '0');
INSERT INTO `sys_menu` VALUES ('3d1d3a7f07e06ed44d8c451125b42963', 'e4dca1309de7f2d9efa1f09beb5ae34a', '删除', 2, '', '', 'btn.user.del', '', 1, '1', '2024-05-11 17:59:49', '2024-05-11 17:59:49', '0');
INSERT INTO `sys_menu` VALUES ('3f43a880c9e2259a1b1411efc9368bcd', 'ed17d32a1f8a7173bcaeb4c84447fb98', '资源列表', 1, 'index', 'website/index', '', 'el-icon-document-remove', 1, '1', '2024-05-08 09:56:25', '2024-05-08 09:56:25', '0');
INSERT INTO `sys_menu` VALUES ('41b3f4ce407fa92523593c6edda2dab8', 'e4dca1309de7f2d9efa1f09beb5ae34a', '修改', 2, '', '', 'btn.user.edit', '', 1, '1', '2024-05-11 17:59:35', '2024-05-11 17:59:35', '0');
INSERT INTO `sys_menu` VALUES ('4316d4c947c23236d9c74bb8fa8448a1', '0', '友链管理', 0, 'link', 'Layout', '', 'el-icon-paperclip', 3, '1', '2024-05-08 09:58:37', '2024-05-08 10:38:36', '0');
INSERT INTO `sys_menu` VALUES ('440867f56dbf23944789d672b158bd32', 'be190c8cd6238ea9119a0befa954cf7a', '角色管理', 1, 'role', 'system/role/index', '', 'el-icon-s-help', 2, '1', '2024-05-08 10:39:34', '2024-05-08 10:39:45', '0');
INSERT INTO `sys_menu` VALUES ('471c8a9a73d0e2bfa59eb40adffb3869', 'be190c8cd6238ea9119a0befa954cf7a', '菜单管理', 1, 'menu', 'system/menu/index', '', 'el-icon-menu', 3, '1', '2024-05-08 10:40:21', '2024-05-08 10:40:21', '0');
INSERT INTO `sys_menu` VALUES ('4a4596413a111e9e0c8944a6be62226a', 'bcd73e1ffe2c3698ca2bed4d1e835891', '列表', 2, '', '', 'btn.link.list', '', 1, '1', '2024-05-09 16:08:09', '2024-05-09 16:08:09', '0');
INSERT INTO `sys_menu` VALUES ('4cfb361d436950364b6f99cf12501425', 'b602e401093b6a40da8dc0101570bcf9', '列表', 2, '', '', 'btn.job.list', '', 1, '1', '2024-05-13 09:23:09', '2024-05-13 09:23:09', '0');
INSERT INTO `sys_menu` VALUES ('51d34848ca84a9da5d6fdbe45afeb34f', '3f43a880c9e2259a1b1411efc9368bcd', '新增', 2, '', '', 'btn.website.add', '', 1, '1', '2024-05-11 17:05:26', '2024-05-11 17:05:26', '0');
INSERT INTO `sys_menu` VALUES ('5b95542e87f4d9bf4c5b7eeffeb76bde', 'bcd73e1ffe2c3698ca2bed4d1e835891', '删除', 2, '', '', 'btn.link.del', '', 1, '1', '2024-05-09 16:07:12', '2024-05-09 16:10:50', '0');
INSERT INTO `sys_menu` VALUES ('5c98ab7391bde87525c3a41a4d0e72cf', 'e4dca1309de7f2d9efa1f09beb5ae34a', '列表', 2, '', '', 'btn.user.list', '', 1, '1', '2024-05-11 18:00:01', '2024-05-11 18:00:01', '0');
INSERT INTO `sys_menu` VALUES ('5d86554626f798497367444eb6ab4da3', '700e6284217d6743c74bc62efa508239', '修改', 2, '', '', 'btn.itembank.edit', '', 1, '1', '2024-05-11 16:55:26', '2024-05-11 16:55:26', '0');
INSERT INTO `sys_menu` VALUES ('5e567f2c0646723f318be3290811da6b', '440867f56dbf23944789d672b158bd32', '新增', 2, '', '', 'btn.role.add', '', 1, '1', '2024-05-11 17:14:04', '2024-05-11 17:14:04', '0');
INSERT INTO `sys_menu` VALUES ('5ea2d48af011f309547f007d5cabbd27', '471c8a9a73d0e2bfa59eb40adffb3869', '列表', 2, '', '', 'btn.menu.list', '', 1, '1', '2024-05-11 17:28:42', '2024-05-11 17:28:42', '0');
INSERT INTO `sys_menu` VALUES ('5ef83c1291ff3e1f435c3dffbf1be55a', 'be190c8cd6238ea9119a0befa954cf7a', '公告管理', 1, 'notice', 'system/notice/index', '', 'el-icon-warning-outline', 6, '1', '2024-05-08 10:43:22', '2024-05-08 10:46:06', '0');
INSERT INTO `sys_menu` VALUES ('5f3c62238b5a3dc58f05bc560cb4d599', '0', '文章管理', 0, 'article', 'Layout', '', 'el-icon-tickets', 1, '1', '2024-05-07 15:09:08', '2024-05-07 17:54:12', '0');
INSERT INTO `sys_menu` VALUES ('6092364759758c2cd63808bdceba4405', '471c8a9a73d0e2bfa59eb40adffb3869', '删除', 2, '', '', 'btn.menu.del', '', 1, '1', '2024-05-11 17:28:29', '2024-05-11 17:28:29', '0');
INSERT INTO `sys_menu` VALUES ('6491c0ddc37084a317b7bf0d5c4817cb', '8be50fe7341b636c2115ea7c9cf36854', '新增', 2, '', '', 'btn.config.add', '', 1, '1', '2024-05-11 17:49:55', '2024-05-11 17:49:55', '0');
INSERT INTO `sys_menu` VALUES ('6abd125b6490c29c54739d9d0ca27ae9', 'd112adfc4ba9caf20104609ec98712df', '图库列表', 1, 'index', 'gallery/index', '', 'el-icon-document-remove', 1, '1', '2024-05-08 10:01:15', '2024-05-08 10:01:15', '0');
INSERT INTO `sys_menu` VALUES ('6be6b8ccf9fba11ce330f2efaeb51c94', 'b602e401093b6a40da8dc0101570bcf9', '新增', 2, '', '', 'btn.job.add', '', 1, '1', '2024-05-13 09:22:21', '2024-05-13 09:22:21', '0');
INSERT INTO `sys_menu` VALUES ('6dc6ec2b8519d3d4716f873428ab276c', '271bc0f236eaa1afabfec96a91d5cc5d', '新增', 2, 'add', 'article/add', 'btn.article.add', '', 1, '1', '2024-05-08 10:56:06', '2024-05-08 17:14:42', '0');
INSERT INTO `sys_menu` VALUES ('700e6284217d6743c74bc62efa508239', '895155a2550b1816cc01b21d13f9f5ce', '题库列表', 1, 'index', 'itembank/index', '', 'el-icon-document-remove', 1, '1', '2024-05-08 10:02:46', '2024-05-08 10:02:46', '0');
INSERT INTO `sys_menu` VALUES ('749fac6f70563b29de79b81e3ed50a00', '5f3c62238b5a3dc58f05bc560cb4d599', '评论列表', 1, 'comment', 'article/comment', '', 'el-icon-s-comment', 3, '1', '2024-05-07 17:56:28', '2024-05-08 09:27:52', '0');
INSERT INTO `sys_menu` VALUES ('76b0773b82f7e9c410a025f1bda0f3c3', '6abd125b6490c29c54739d9d0ca27ae9', '列表', 2, '', '', 'btn.gallery.list', '', 1, '1', '2024-05-11 17:02:10', '2024-05-11 17:02:10', '0');
INSERT INTO `sys_menu` VALUES ('77b30e9d5b7f261153c67947ae97963e', '471c8a9a73d0e2bfa59eb40adffb3869', '新增', 2, '', '', 'btn.menu.add', '', 1, '1', '2024-05-11 17:27:57', '2024-05-11 17:27:57', '0');
INSERT INTO `sys_menu` VALUES ('77c5ef0c5e44e724f9c19f0cb20df4d9', '6abd125b6490c29c54739d9d0ca27ae9', '新增', 2, '', '', 'btn.gallery.add', '', 1, '1', '2024-05-11 17:01:31', '2024-05-11 17:01:31', '0');
INSERT INTO `sys_menu` VALUES ('7e6e754ee2d1f2e0339b5a275a45acdd', 'dae1346779d28f39a06d1e1ce3026781', '列表', 2, '', '', 'btn.operlog.list', '', 1, '1', '2024-05-13 09:29:56', '2024-05-13 09:29:56', '0');
INSERT INTO `sys_menu` VALUES ('8037a655ebd77d22991e33871240bbb3', '9388193f467e661abefae3368414327a', '修改', 2, '', '', 'btn.summary.edit', '', 1, '1', '2024-05-11 16:46:50', '2024-05-11 16:46:50', '0');
INSERT INTO `sys_menu` VALUES ('85413b9370843cdb477dfbabf62bf6e5', 'be190c8cd6238ea9119a0befa954cf7a', '字典分类', 1, 'dict', 'system/dict/index', '', 'el-icon-tickets', 4, '1', '2024-05-08 10:40:46', '2024-05-08 10:45:53', '0');
INSERT INTO `sys_menu` VALUES ('895155a2550b1816cc01b21d13f9f5ce', '0', '题库管理', 0, 'itembank', 'Layout', '', 'el-icon-edit', 4, '1', '2024-05-08 10:02:20', '2024-05-08 10:38:41', '0');
INSERT INTO `sys_menu` VALUES ('8be50fe7341b636c2115ea7c9cf36854', 'be190c8cd6238ea9119a0befa954cf7a', '参数设置', 1, 'config', 'system/config/index', '', 'el-icon-setting', 7, '1', '2024-05-08 10:41:52', '2024-05-08 10:46:10', '0');
INSERT INTO `sys_menu` VALUES ('9388193f467e661abefae3368414327a', '5f3c62238b5a3dc58f05bc560cb4d599', '文章归档', 1, 'summary', 'article/summary', '', 'el-icon-s-help', 2, '1', '2024-05-07 17:53:46', '2024-05-07 17:53:46', '0');
INSERT INTO `sys_menu` VALUES ('a1cf74abe6ef8075975281c08bfe1895', '85413b9370843cdb477dfbabf62bf6e5', '字典数据', 2, 'data/:id(\\d+)', 'system/dict/data', 'btn.dict.data', '', 1, '1', '2024-05-08 10:41:13', '2024-05-08 15:52:27', '0');
INSERT INTO `sys_menu` VALUES ('a3a7a0cf00f49ca90765dd869d5ce399', '85413b9370843cdb477dfbabf62bf6e5', '列表', 2, '', '', 'btn.dict.list', '', 1, '1', '2024-05-11 17:39:55', '2024-05-11 17:39:55', '0');
INSERT INTO `sys_menu` VALUES ('a88286a2308fb14175868ded546953b7', 'dae1346779d28f39a06d1e1ce3026781', '删除', 2, '', '', 'btn.operlog.del', '', 1, '1', '2024-05-13 09:29:37', '2024-05-13 09:29:37', '0');
INSERT INTO `sys_menu` VALUES ('aa3f6a9f28e47f73ddde5e63c1aee822', '9388193f467e661abefae3368414327a', '列表', 2, '', '', 'btn.summary.list', '', 1, '1', '2024-05-11 16:47:17', '2024-05-11 16:47:17', '0');
INSERT INTO `sys_menu` VALUES ('af19e30c6aa4f2fa195918528273aa6d', '5ef83c1291ff3e1f435c3dffbf1be55a', '列表', 2, '', '', 'btn.notice.list', '', 1, '1', '2024-05-11 17:35:01', '2024-05-11 17:35:01', '0');
INSERT INTO `sys_menu` VALUES ('af1ddec5003e789ea5d167e7b8b0a773', '16e3dc94b0a0b0ab3eb491d4e172295d', '修改', 2, '', '', 'btn.information.edit', '', 1, '1', '2024-05-11 17:11:14', '2024-05-11 17:11:14', '0');
INSERT INTO `sys_menu` VALUES ('afef72e3ee28af35f2de19ba4c1dc88e', 'b602e401093b6a40da8dc0101570bcf9', '修改', 2, '', '', 'btn.job.edit', '', 1, '1', '2024-05-13 09:22:44', '2024-05-13 09:22:44', '0');
INSERT INTO `sys_menu` VALUES ('b602e401093b6a40da8dc0101570bcf9', 'be190c8cd6238ea9119a0befa954cf7a', '定时任务', 1, 'job', 'system/job/index', '', 'el-icon-s-unfold', 5, '1', '2024-05-08 10:42:17', '2024-05-08 10:45:59', '0');
INSERT INTO `sys_menu` VALUES ('b71103cc3723538c6ccb7bf3c054c835', 'bcd73e1ffe2c3698ca2bed4d1e835891', '新增', 2, '', '', 'btn.link.add', '', 1, '1', '2024-05-09 15:36:36', '2024-05-09 16:01:17', '0');
INSERT INTO `sys_menu` VALUES ('b72e17d19dd5372895c0b4969b292f1f', '9388193f467e661abefae3368414327a', '新增', 2, '', '', 'btn.summary.add', '', 1, '1', '2024-05-11 16:46:21', '2024-05-11 16:46:21', '0');
INSERT INTO `sys_menu` VALUES ('b8b6a480d4487df8eea7edf88b1ec003', '9388193f467e661abefae3368414327a', '删除', 2, '', '', 'btn.summary.del', '', 1, '1', '2024-05-11 16:47:02', '2024-05-11 16:47:02', '0');
INSERT INTO `sys_menu` VALUES ('b99d96ba9ce2526eba6ad9dabae9f2a0', '700e6284217d6743c74bc62efa508239', '列表', 2, '', '', 'btn.itembank.list', '', 1, '1', '2024-05-11 16:56:02', '2024-05-11 16:56:02', '0');
INSERT INTO `sys_menu` VALUES ('ba5f42390d98d1ac546119627cec0b37', '5ef83c1291ff3e1f435c3dffbf1be55a', '新增', 2, '', '', 'btn.notice.add', '', 1, '1', '2024-05-11 17:34:10', '2024-05-11 17:34:10', '0');
INSERT INTO `sys_menu` VALUES ('bcd73e1ffe2c3698ca2bed4d1e835891', '4316d4c947c23236d9c74bb8fa8448a1', '友链列表', 1, 'index', 'link/index', '', 'el-icon-document-remove', 1, '1', '2024-05-08 09:59:37', '2024-05-09 16:00:53', '0');
INSERT INTO `sys_menu` VALUES ('be190c8cd6238ea9119a0befa954cf7a', '0', '系统管理', 0, 'system', 'Layout', '', 'el-icon-s-tools', 6, '1', '2024-05-08 10:33:07', '2024-05-08 10:38:51', '0');
INSERT INTO `sys_menu` VALUES ('c4c9727d9b18bf87531214b8c3b094a9', '749fac6f70563b29de79b81e3ed50a00', '列表', 2, '', '', 'btn.comment.list', '', 1, '1', '2024-05-11 16:40:08', '2024-05-13 09:58:07', '0');
INSERT INTO `sys_menu` VALUES ('c74c6ede8a88856a513178bdc199f3cb', '3f43a880c9e2259a1b1411efc9368bcd', '删除', 2, '', '', 'btn.website.del', '', 1, '1', '2024-05-11 17:06:07', '2024-05-11 17:06:07', '0');
INSERT INTO `sys_menu` VALUES ('c85f2e6539507a3aa594c9e0a08ded47', '271bc0f236eaa1afabfec96a91d5cc5d', '修改', 2, 'update/:id', 'article/add', 'btn.article.edit', '', 1, '1', '2024-05-08 09:30:21', '2024-05-11 16:29:51', '0');
INSERT INTO `sys_menu` VALUES ('cd0f17b0d5ff3de2ddc974e07681f13e', '85413b9370843cdb477dfbabf62bf6e5', '修改', 2, '', '', 'btn.dict.edit', '', 1, '1', '2024-05-11 17:39:15', '2024-05-11 17:39:15', '0');
INSERT INTO `sys_menu` VALUES ('cd428ecaf91f3747d4f75953ebab413d', '271bc0f236eaa1afabfec96a91d5cc5d', '删除', 2, '', '', 'btn.article.del', '', 1, '1', '2024-05-11 16:25:33', '2024-05-11 16:25:33', '0');
INSERT INTO `sys_menu` VALUES ('ceb82099541da06df10c860a9f6e7d3f', '700e6284217d6743c74bc62efa508239', '新增', 2, '', '', 'btn.itembank.add', '', 1, '1', '2024-05-11 16:54:57', '2024-05-11 16:54:57', '0');
INSERT INTO `sys_menu` VALUES ('d112adfc4ba9caf20104609ec98712df', '0', '图库管理', 0, 'gallery', 'Layout', '', 'el-icon-picture-outline', 5, '1', '2024-05-08 10:00:33', '2024-05-08 10:38:47', '0');
INSERT INTO `sys_menu` VALUES ('d228447e3e3650cedd98bd8c1724c866', '3f43a880c9e2259a1b1411efc9368bcd', '列表', 2, '', '', 'btn.website.list', '', 1, '1', '2024-05-11 17:06:20', '2024-05-11 17:06:20', '0');
INSERT INTO `sys_menu` VALUES ('d55dea05d71d3bb787f5a3abce45c8b0', '3f43a880c9e2259a1b1411efc9368bcd', '修改', 2, '', '', 'btn.website.edit', '', 1, '1', '2024-05-11 17:05:54', '2024-05-11 17:05:54', '0');
INSERT INTO `sys_menu` VALUES ('d839e3275f5fa4b708929c93f1bc531a', '5ef83c1291ff3e1f435c3dffbf1be55a', '修改', 2, '', '', 'btn.notice.edit', '', 1, '1', '2024-05-11 17:34:34', '2024-05-11 17:34:34', '0');
INSERT INTO `sys_menu` VALUES ('dae1346779d28f39a06d1e1ce3026781', 'be190c8cd6238ea9119a0befa954cf7a', '日志管理', 1, 'operlog', 'monitor/operlog/index', '', 'el-icon-edit', 9, '1', '2024-05-08 10:43:53', '2024-05-08 10:46:26', '0');
INSERT INTO `sys_menu` VALUES ('db75d07485333f3a052cac79b54b7c59', 'e4dca1309de7f2d9efa1f09beb5ae34a', '新增', 2, '', '', 'btn.user.add', '', 1, '1', '2024-05-11 17:59:17', '2024-05-11 17:59:17', '0');
INSERT INTO `sys_menu` VALUES ('e1466d34d8c97f3b0cd10370d95ebea3', '471c8a9a73d0e2bfa59eb40adffb3869', '修改', 2, '', '', 'btn.menu.edit', '', 1, '1', '2024-05-11 17:28:12', '2024-05-11 17:28:12', '0');
INSERT INTO `sys_menu` VALUES ('e18a1c6b71f13161ef1580f4fc2bd740', '440867f56dbf23944789d672b158bd32', '列表', 2, '', '', 'btn.role.list', '', 1, '1', '2024-05-11 17:14:55', '2024-05-11 17:14:55', '0');
INSERT INTO `sys_menu` VALUES ('e4dca1309de7f2d9efa1f09beb5ae34a', 'be190c8cd6238ea9119a0befa954cf7a', '用户管理', 1, 'user', 'system/user/index', '', 'el-icon-s-custom', 1, '1', '2024-05-08 10:36:58', '2024-05-08 10:36:58', '0');
INSERT INTO `sys_menu` VALUES ('ed17d32a1f8a7173bcaeb4c84447fb98', '0', '资源管理', 0, 'website', 'Layout', '', 'el-icon-star-on', 2, '1', '2024-05-08 09:55:40', '2024-05-08 10:38:29', '0');
INSERT INTO `sys_menu` VALUES ('efafe29a11921a7026b73d59dfcaa0b4', '440867f56dbf23944789d672b158bd32', '修改', 2, '', '', 'btn.role.edit', '', 1, '1', '2024-05-11 17:14:19', '2024-05-11 17:14:19', '0');
INSERT INTO `sys_menu` VALUES ('f2fbf976272ba320421d6382df1ae7d8', 'bcd73e1ffe2c3698ca2bed4d1e835891', '修改', 2, '', '', 'btn.link.edit', '', 1, '1', '2024-05-09 16:03:29', '2024-05-09 17:07:58', '0');
INSERT INTO `sys_menu` VALUES ('f60544908c9665fe8a7bbc8718531f99', 'b602e401093b6a40da8dc0101570bcf9', '删除', 2, '', '', 'btn.job.del', '', 1, '1', '2024-05-13 09:22:57', '2024-05-13 09:22:57', '0');
INSERT INTO `sys_menu` VALUES ('f65a794f7aaa73d994048c53a928fbec', '16e3dc94b0a0b0ab3eb491d4e172295d', '列表', 2, '', '', 'btn.information.list', '', 1, '1', '2024-05-11 17:11:37', '2024-05-11 17:11:37', '0');
INSERT INTO `sys_menu` VALUES ('ff635db54aeba2e9ca4968973347685e', '85413b9370843cdb477dfbabf62bf6e5', '新增', 2, '', '', 'btn.dict.add', '', 1, '1', '2024-05-11 17:38:57', '2024-05-11 17:38:57', '0');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（1正常 0关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态（1：正常 0：停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('281dd7886533f377fc2dc2c3fa233d1a', '系统管理员', 'SYSTEM', '系统管理员', '2024-05-13 09:38:39', '2024-05-13 09:38:39', '0', '1');
INSERT INTO `sys_role` VALUES ('6f79a34b896e91cefe4a8dc396f76bb0', '超级管理员', 'SUPER', '超级管理员', '2024-05-06 15:56:39', '2024-05-06 15:56:39', '0', '1');
INSERT INTO `sys_role` VALUES ('b78a50cb00af53a5a0d718fc66c31297', '文章管理员', 'ARTICLE', '文章管理员', '2024-05-09 14:56:43', '2024-05-09 15:07:47', '0', '1');
INSERT INTO `sys_role` VALUES ('f78644098becb54d4b8c9210fa327d03', '游客', 'VISITOR', '只具有所有菜单的访问权限', '2024-05-06 16:02:35', '2024-05-13 10:13:18', '0', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0550cd37fb8bb5d968b678168d8f96ee', '281dd7886533f377fc2dc2c3fa233d1a', '30318a16f713de861d99a789e0caac77', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('05c83e5085723a0ade47e4adff37ec48', '281dd7886533f377fc2dc2c3fa233d1a', 'be190c8cd6238ea9119a0befa954cf7a', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('06bfebb5170a5b9de1e322f8124356d7', 'f78644098becb54d4b8c9210fa327d03', 'e18a1c6b71f13161ef1580f4fc2bd740', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('07b1e59d4e9ca867fccedd69ff31e67e', '6f79a34b896e91cefe4a8dc396f76bb0', 'b71103cc3723538c6ccb7bf3c054c835', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('089b3aa79439325257005c29e99b6218', '6f79a34b896e91cefe4a8dc396f76bb0', '76b0773b82f7e9c410a025f1bda0f3c3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('0927cf921574191eca469cec6fad5adf', 'b78a50cb00af53a5a0d718fc66c31297', '6dc6ec2b8519d3d4716f873428ab276c', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('094b49406baf537e67be8b7976fe36c6', '6f79a34b896e91cefe4a8dc396f76bb0', '77b30e9d5b7f261153c67947ae97963e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('0ea163686e578065c8bed5583e60c098', '6f79a34b896e91cefe4a8dc396f76bb0', '5ea2d48af011f309547f007d5cabbd27', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('132dbcf08d7f651c3c5c21ce10c3de80', '6f79a34b896e91cefe4a8dc396f76bb0', '5f3c62238b5a3dc58f05bc560cb4d599', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('14101b1385818ac2fc5728e21ddaa94b', '6f79a34b896e91cefe4a8dc396f76bb0', 'af19e30c6aa4f2fa195918528273aa6d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('154e6f0b7c988b7547d8d0e75d3bf76b', 'b78a50cb00af53a5a0d718fc66c31297', '5f3c62238b5a3dc58f05bc560cb4d599', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('18e28c78f5f42ae5e4f0ccca06a36f3a', '281dd7886533f377fc2dc2c3fa233d1a', 'a3a7a0cf00f49ca90765dd869d5ce399', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('1a7518c44dcb687236544e2a18fe14cd', '6f79a34b896e91cefe4a8dc396f76bb0', '5e567f2c0646723f318be3290811da6b', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('1aaaf92e0bdcfbabfbfa1ba2346e3d66', '6f79a34b896e91cefe4a8dc396f76bb0', 'f2fbf976272ba320421d6382df1ae7d8', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('1da3bd11055001aed821627ccef9629b', 'b78a50cb00af53a5a0d718fc66c31297', 'b72e17d19dd5372895c0b4969b292f1f', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('2399937dc10fdf16445f6f82fa4c2201', '6f79a34b896e91cefe4a8dc396f76bb0', 'd55dea05d71d3bb787f5a3abce45c8b0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('247f1d833badb8a28d8bee808a031d43', '281dd7886533f377fc2dc2c3fa233d1a', '1298ab16f667edbece17a2b73cf51fc0', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('25f2602a2f76920d89e08129d6ff07eb', '6f79a34b896e91cefe4a8dc396f76bb0', '16e3dc94b0a0b0ab3eb491d4e172295d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2890bf77f99dea5dc15a560e07ea76b7', '6f79a34b896e91cefe4a8dc396f76bb0', 'e4dca1309de7f2d9efa1f09beb5ae34a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('290109a11424ad2f44567d9a2df6d735', 'f78644098becb54d4b8c9210fa327d03', '285fca26989c2c49978d9853fb38197c', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('2c039cd6a39ea6542176b4c3aa1e3a64', '6f79a34b896e91cefe4a8dc396f76bb0', '3f43a880c9e2259a1b1411efc9368bcd', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2d9ab6c5596bdc7c180196f581355f8d', '6f79a34b896e91cefe4a8dc396f76bb0', 'f65a794f7aaa73d994048c53a928fbec', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2dcd185cd25988ef76595a2c24424101', 'f78644098becb54d4b8c9210fa327d03', '6abd125b6490c29c54739d9d0ca27ae9', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('2fa45f27918189d6f1e16939340d1322', 'f78644098becb54d4b8c9210fa327d03', '5c98ab7391bde87525c3a41a4d0e72cf', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('3090f908e4ac74da00cefd0a5b18cfbc', '6f79a34b896e91cefe4a8dc396f76bb0', 'a1cf74abe6ef8075975281c08bfe1895', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('30bb30f5dc788fbdf170444e1192a095', '281dd7886533f377fc2dc2c3fa233d1a', 'dae1346779d28f39a06d1e1ce3026781', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('30e878df150f53ad55b57605d9f1b70d', 'f78644098becb54d4b8c9210fa327d03', 'b99d96ba9ce2526eba6ad9dabae9f2a0', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('31259db9c10e7efd310269f817477157', 'b78a50cb00af53a5a0d718fc66c31297', 'b8b6a480d4487df8eea7edf88b1ec003', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('31dfbc97440eebc18fcb683262440a98', 'b78a50cb00af53a5a0d718fc66c31297', '9388193f467e661abefae3368414327a', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('3299b81f457edc6ae724b49fdee943c3', '6f79a34b896e91cefe4a8dc396f76bb0', 'a88286a2308fb14175868ded546953b7', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('378558640ec522566a7d64ee7f15515b', '281dd7886533f377fc2dc2c3fa233d1a', '6be6b8ccf9fba11ce330f2efaeb51c94', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('384c17e634c07b707688828db7545d1b', '6f79a34b896e91cefe4a8dc396f76bb0', 'e1466d34d8c97f3b0cd10370d95ebea3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('38ecb3f02ce0d57da812d493abacee75', '6f79a34b896e91cefe4a8dc396f76bb0', '0186f5e32d71f917bd4cb4d693346cde', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('39e870a26160970eb14a98f922b5a85d', '281dd7886533f377fc2dc2c3fa233d1a', 'e18a1c6b71f13161ef1580f4fc2bd740', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('3a1a4f5fc4dd1df76059b34d24ed3828', '281dd7886533f377fc2dc2c3fa233d1a', 'ba5f42390d98d1ac546119627cec0b37', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('3a1cc43efd63c3b5f626f5e8dda1e52c', 'f78644098becb54d4b8c9210fa327d03', 'dae1346779d28f39a06d1e1ce3026781', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('3ad4d95f2eec38fe81135df0649576f2', '6f79a34b896e91cefe4a8dc396f76bb0', '749fac6f70563b29de79b81e3ed50a00', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3c2f623cb881b42efd0fe38c9889548c', '6f79a34b896e91cefe4a8dc396f76bb0', 'aa3f6a9f28e47f73ddde5e63c1aee822', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3c5248dfe85e8053f6bc615b94f5c423', '281dd7886533f377fc2dc2c3fa233d1a', 'a88286a2308fb14175868ded546953b7', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('3c93136c48108725b079545994ddb08f', '281dd7886533f377fc2dc2c3fa233d1a', 'd839e3275f5fa4b708929c93f1bc531a', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('3d88abc78737f534141dc7d61f01f676', 'f78644098becb54d4b8c9210fa327d03', 'a3a7a0cf00f49ca90765dd869d5ce399', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('3e876d8942842dbdb7bd227736438ebb', '281dd7886533f377fc2dc2c3fa233d1a', 'b602e401093b6a40da8dc0101570bcf9', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('3f3c0c7925e9f388162eb8dcde1dc33a', '6f79a34b896e91cefe4a8dc396f76bb0', 'b8b6a480d4487df8eea7edf88b1ec003', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('401c65cd7e5ae95ba7030c2fa5da852e', 'f78644098becb54d4b8c9210fa327d03', '271bc0f236eaa1afabfec96a91d5cc5d', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('41f4149433d5b7de1dc45b6b4dfa32c6', 'b78a50cb00af53a5a0d718fc66c31297', 'c4c9727d9b18bf87531214b8c3b094a9', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('4665277abf9f7ae080a840f48cb9e390', '281dd7886533f377fc2dc2c3fa233d1a', 'af19e30c6aa4f2fa195918528273aa6d', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('47afd7c98dc9c64cf9bd32e2a1f2a58d', 'f78644098becb54d4b8c9210fa327d03', '8be50fe7341b636c2115ea7c9cf36854', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('494b86ccd5b0d972ea9c1bd6c0f11d9b', '281dd7886533f377fc2dc2c3fa233d1a', '3aac4a4b0b7284bc52b5308e776d14ea', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('4af06b83e51feb512098a7b46572417b', '281dd7886533f377fc2dc2c3fa233d1a', '5ea2d48af011f309547f007d5cabbd27', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('4c8f1035307fac5b630ddc4124c752aa', '6f79a34b896e91cefe4a8dc396f76bb0', 'bcd73e1ffe2c3698ca2bed4d1e835891', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('4d26c4c78619a0d385c0d1b6278f0b2a', '281dd7886533f377fc2dc2c3fa233d1a', '5e567f2c0646723f318be3290811da6b', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('4e30dedce47d13b2990fb77eec674acd', '6f79a34b896e91cefe4a8dc396f76bb0', 'b72e17d19dd5372895c0b4969b292f1f', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('50324eb6f41bf0219ec886e997ef02c8', 'b78a50cb00af53a5a0d718fc66c31297', '12c926c9e528aa15481a552b51c646e1', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('5099bb8d5e29347535021985c607ea54', '281dd7886533f377fc2dc2c3fa233d1a', 'efafe29a11921a7026b73d59dfcaa0b4', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('51bab98881ee21ed652bd0177710d581', '6f79a34b896e91cefe4a8dc396f76bb0', 'e18a1c6b71f13161ef1580f4fc2bd740', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('52c392cf430781760cc8106e02172891', '6f79a34b896e91cefe4a8dc396f76bb0', '77c5ef0c5e44e724f9c19f0cb20df4d9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('54fad8facee7175f526640445bd0905b', '281dd7886533f377fc2dc2c3fa233d1a', '0186f5e32d71f917bd4cb4d693346cde', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('55c2087739690dede75845cc0842528b', '6f79a34b896e91cefe4a8dc396f76bb0', 'db75d07485333f3a052cac79b54b7c59', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('55e322c781de52bcece399fa9d9d6b95', 'f78644098becb54d4b8c9210fa327d03', '9388193f467e661abefae3368414327a', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('5975227ffdf7a05070580423a85ff96c', '6f79a34b896e91cefe4a8dc396f76bb0', 'b602e401093b6a40da8dc0101570bcf9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('5cae2630ac711e825e9d30a818aa72bb', 'f78644098becb54d4b8c9210fa327d03', '76b0773b82f7e9c410a025f1bda0f3c3', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('5cc5c83538d3c1dc3e705ff6a3224ae0', 'b78a50cb00af53a5a0d718fc66c31297', '749fac6f70563b29de79b81e3ed50a00', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('5e14e1e3f45242d16046a903676873cf', '281dd7886533f377fc2dc2c3fa233d1a', 'afef72e3ee28af35f2de19ba4c1dc88e', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('5f7468c4d8cf9fb752d50fbbdaad6763', '281dd7886533f377fc2dc2c3fa233d1a', 'f65a794f7aaa73d994048c53a928fbec', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('60ccb3c24a3b6b30a6885f5a357fee4d', '281dd7886533f377fc2dc2c3fa233d1a', 'e1466d34d8c97f3b0cd10370d95ebea3', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('616d2d5e70e0b0574ae7e3edf304ebbf', '6f79a34b896e91cefe4a8dc396f76bb0', '4316d4c947c23236d9c74bb8fa8448a1', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('61f7b43f70efcf31853a86be3a5b5830', '281dd7886533f377fc2dc2c3fa233d1a', '77b30e9d5b7f261153c67947ae97963e', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('62524e92e20cf2941d240bd656caa2d5', 'f78644098becb54d4b8c9210fa327d03', '85413b9370843cdb477dfbabf62bf6e5', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('633378a4146b8de855ec0ca0c9d245df', 'f78644098becb54d4b8c9210fa327d03', '4a4596413a111e9e0c8944a6be62226a', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('63426b42a8e2563ca8c297958f35f2b7', '6f79a34b896e91cefe4a8dc396f76bb0', 'cd0f17b0d5ff3de2ddc974e07681f13e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('63ba609639607c87649f6260a43c2fc6', '6f79a34b896e91cefe4a8dc396f76bb0', '1298ab16f667edbece17a2b73cf51fc0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('66efd3dc6329bb999af562c1269a47d3', '6f79a34b896e91cefe4a8dc396f76bb0', '36c3988c159b20bc56f28f98f4b4aec9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('68a324d84f2775ce1e89fb40dbddddfc', '6f79a34b896e91cefe4a8dc396f76bb0', '8be50fe7341b636c2115ea7c9cf36854', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('69c658acfcca99c2935aac7e8d18bc3d', '6f79a34b896e91cefe4a8dc396f76bb0', '4a4596413a111e9e0c8944a6be62226a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('6ba24051fc7cdea3f12d64d36a133d8a', 'f78644098becb54d4b8c9210fa327d03', '0186f5e32d71f917bd4cb4d693346cde', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('6bcf30b3898ae5f88917dd7fd750b31e', '6f79a34b896e91cefe4a8dc396f76bb0', '5b95542e87f4d9bf4c5b7eeffeb76bde', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('6c70f195b87f8a039490fa76f6826e73', '6f79a34b896e91cefe4a8dc396f76bb0', '895155a2550b1816cc01b21d13f9f5ce', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('6d06cd313f7a02938a2f5668ff04da74', '281dd7886533f377fc2dc2c3fa233d1a', '6092364759758c2cd63808bdceba4405', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('7081e5f9d4aa9f87ebc56276f72ee953', '6f79a34b896e91cefe4a8dc396f76bb0', 'c4c9727d9b18bf87531214b8c3b094a9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('70ce0747c67d39901c8bb0c75901f889', '6f79a34b896e91cefe4a8dc396f76bb0', '5ef83c1291ff3e1f435c3dffbf1be55a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7205967c9a544b09fddf9b64209350e6', 'f78644098becb54d4b8c9210fa327d03', '16e3dc94b0a0b0ab3eb491d4e172295d', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('723c91f3df6dfc8aa943e825527f9477', '6f79a34b896e91cefe4a8dc396f76bb0', 'c74c6ede8a88856a513178bdc199f3cb', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('731f4f2afd9a6541d0ce35a104492070', '281dd7886533f377fc2dc2c3fa233d1a', '240307c217e98687e93b05c16a5424aa', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('765af7aa5179ebc1f55d8e27f9582a40', '281dd7886533f377fc2dc2c3fa233d1a', '85413b9370843cdb477dfbabf62bf6e5', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('7ce9b7c2d533f3ae2b0ac961897bb158', '6f79a34b896e91cefe4a8dc396f76bb0', 'b99d96ba9ce2526eba6ad9dabae9f2a0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7d1d6e77441fee3dc18f539dc36abb84', '6f79a34b896e91cefe4a8dc396f76bb0', '8037a655ebd77d22991e33871240bbb3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7d32c45e5f83631dee8247c348e80176', '6f79a34b896e91cefe4a8dc396f76bb0', '5d86554626f798497367444eb6ab4da3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7d333bf3222da998e1a7e362e0f121b5', '6f79a34b896e91cefe4a8dc396f76bb0', 'f60544908c9665fe8a7bbc8718531f99', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7e3caf3633f1d4553356a6fa29e7c90e', '281dd7886533f377fc2dc2c3fa233d1a', '471c8a9a73d0e2bfa59eb40adffb3869', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('7e520c2a037474348830e7e763a95275', '6f79a34b896e91cefe4a8dc396f76bb0', 'a3a7a0cf00f49ca90765dd869d5ce399', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7ee3d137e7d8b108f909db12d9808d23', '6f79a34b896e91cefe4a8dc396f76bb0', 'c85f2e6539507a3aa594c9e0a08ded47', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('80a7fde9d1956c03454958e2dbe681c7', '6f79a34b896e91cefe4a8dc396f76bb0', '6dc6ec2b8519d3d4716f873428ab276c', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('892cd04d8137190b048f5eb2cabe2a0c', '6f79a34b896e91cefe4a8dc396f76bb0', '240307c217e98687e93b05c16a5424aa', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('894bc6bd4ab8347d559a7f156b719b5c', '6f79a34b896e91cefe4a8dc396f76bb0', 'ff635db54aeba2e9ca4968973347685e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('8951b84ef3996eedd342a86c9f60855a', 'f78644098becb54d4b8c9210fa327d03', 'f65a794f7aaa73d994048c53a928fbec', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('8a9564685412718a1b8e8c83c6d5fa04', '6f79a34b896e91cefe4a8dc396f76bb0', '3aac4a4b0b7284bc52b5308e776d14ea', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('8aeb53c4a38f5a657c2bd569be814851', '6f79a34b896e91cefe4a8dc396f76bb0', '9388193f467e661abefae3368414327a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('8b7a5835db546b0e7f70fee5fa65bcd6', 'f78644098becb54d4b8c9210fa327d03', '5f3c62238b5a3dc58f05bc560cb4d599', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('90ee8b2a14dc35f6b01593f3fb10928f', '281dd7886533f377fc2dc2c3fa233d1a', 'cd0f17b0d5ff3de2ddc974e07681f13e', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('92b62af891d7b89329475f986c7023ec', '6f79a34b896e91cefe4a8dc396f76bb0', 'd228447e3e3650cedd98bd8c1724c866', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('95dd49684787c6d8cbc479effdee83c0', '6f79a34b896e91cefe4a8dc396f76bb0', 'ba5f42390d98d1ac546119627cec0b37', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('960eaa17e123d4f351f904e4aaeb9430', 'f78644098becb54d4b8c9210fa327d03', '5ef83c1291ff3e1f435c3dffbf1be55a', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('96bf515d9e64e24b1c61ec980d5d827a', 'f78644098becb54d4b8c9210fa327d03', 'd112adfc4ba9caf20104609ec98712df', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('97dbd95db0af19a4baba971fa0cbb63c', '6f79a34b896e91cefe4a8dc396f76bb0', 'dae1346779d28f39a06d1e1ce3026781', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('9833efc742902a9a1851d497a484cc26', '6f79a34b896e91cefe4a8dc396f76bb0', '700e6284217d6743c74bc62efa508239', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('9a4468a9da8eea9a5d5bd5a8414e51cc', '6f79a34b896e91cefe4a8dc396f76bb0', '41b3f4ce407fa92523593c6edda2dab8', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('9db7cc699fb39c3395524610fcc8dc92', 'f78644098becb54d4b8c9210fa327d03', 'af19e30c6aa4f2fa195918528273aa6d', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('9dbe4a24ca7096f202c4ed74bc444ac3', 'f78644098becb54d4b8c9210fa327d03', 'bcd73e1ffe2c3698ca2bed4d1e835891', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('9eb2b3a198b67f607482a48f300e911a', 'f78644098becb54d4b8c9210fa327d03', '440867f56dbf23944789d672b158bd32', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('9ec244414c0807cb2cab87a872f57dfe', '6f79a34b896e91cefe4a8dc396f76bb0', '3d1d3a7f07e06ed44d8c451125b42963', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a0eb3d20c9e7a041fd9dcd7fc655b0eb', '6f79a34b896e91cefe4a8dc396f76bb0', '7e6e754ee2d1f2e0339b5a275a45acdd', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a1ed26903ec5327461b3521c5750be4c', '281dd7886533f377fc2dc2c3fa233d1a', '8be50fe7341b636c2115ea7c9cf36854', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('a368b31410755e8b5258d439213ed7aa', '6f79a34b896e91cefe4a8dc396f76bb0', 'ceb82099541da06df10c860a9f6e7d3f', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a39968220df05827739f10b4a5f8715b', '6f79a34b896e91cefe4a8dc396f76bb0', '30318a16f713de861d99a789e0caac77', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a46664036823b1a7e6afefac67ae414a', 'b78a50cb00af53a5a0d718fc66c31297', 'cd428ecaf91f3747d4f75953ebab413d', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('a48871d6dcc4922893472f9f11677855', '6f79a34b896e91cefe4a8dc396f76bb0', '4cfb361d436950364b6f99cf12501425', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a4b0ef12bf91d1624116e9cdea343712', '6f79a34b896e91cefe4a8dc396f76bb0', '5c98ab7391bde87525c3a41a4d0e72cf', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a5fa707d9afe75014b5c5454013a654e', 'b78a50cb00af53a5a0d718fc66c31297', 'c85f2e6539507a3aa594c9e0a08ded47', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('a60366601958003efc201c7ebb08c12d', '6f79a34b896e91cefe4a8dc396f76bb0', '6be6b8ccf9fba11ce330f2efaeb51c94', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a6be7184a2c3d613f4ebb27ca02ab2bb', '6f79a34b896e91cefe4a8dc396f76bb0', 'be190c8cd6238ea9119a0befa954cf7a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a6d82597b447b9ca4069bac844697beb', '281dd7886533f377fc2dc2c3fa233d1a', '7e6e754ee2d1f2e0339b5a275a45acdd', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('a81f74d2dc19bc8f810af0d58503e782', 'f78644098becb54d4b8c9210fa327d03', 'be190c8cd6238ea9119a0befa954cf7a', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('a88e322c7b4ddeb4deff3b63cedc52d4', '6f79a34b896e91cefe4a8dc396f76bb0', 'efafe29a11921a7026b73d59dfcaa0b4', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a985f4645d3e0e65b15ba996962040d5', 'b78a50cb00af53a5a0d718fc66c31297', '8037a655ebd77d22991e33871240bbb3', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('a9960274a314e32a4d341c4c08682f17', 'f78644098becb54d4b8c9210fa327d03', 'e4dca1309de7f2d9efa1f09beb5ae34a', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('aa042ad4efa7c0f90371729b92f41599', 'f78644098becb54d4b8c9210fa327d03', '471c8a9a73d0e2bfa59eb40adffb3869', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('ab130ced4a29cb160e7dddd06cafd18b', '6f79a34b896e91cefe4a8dc396f76bb0', '51d34848ca84a9da5d6fdbe45afeb34f', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('abc0b09f55ffc68868eccbb2288049a4', '281dd7886533f377fc2dc2c3fa233d1a', 'ff635db54aeba2e9ca4968973347685e', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('acff6e101a836088a0a899ce03b92bb7', '6f79a34b896e91cefe4a8dc396f76bb0', 'af1ddec5003e789ea5d167e7b8b0a773', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('ad015e2640247d9c6228e83ccccd6d9b', 'f78644098becb54d4b8c9210fa327d03', '3f43a880c9e2259a1b1411efc9368bcd', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('ae8d9d2ad22aa6ae2b22a5dcf1b22586', '281dd7886533f377fc2dc2c3fa233d1a', '3d1d3a7f07e06ed44d8c451125b42963', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('b2d752264e5ab3b479bfe3e266babe25', 'b78a50cb00af53a5a0d718fc66c31297', '1f2da8d3b50d9ae744279bdeb3694df0', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('b4dbb304828dd7553b9845787ca4d3fe', '281dd7886533f377fc2dc2c3fa233d1a', 'e4dca1309de7f2d9efa1f09beb5ae34a', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('b4fe0c3be2727834f7470d3abf7b66d3', 'f78644098becb54d4b8c9210fa327d03', 'b602e401093b6a40da8dc0101570bcf9', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('b68aeeb9ec3a4f449e848a3f46c217bf', '281dd7886533f377fc2dc2c3fa233d1a', '5ef83c1291ff3e1f435c3dffbf1be55a', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('b79b9d7efcfcc60aba5b39a2c7fc5b8f', 'f78644098becb54d4b8c9210fa327d03', 'ed17d32a1f8a7173bcaeb4c84447fb98', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('b8c4b6d3f65c51405d570dc2bd831ff3', 'f78644098becb54d4b8c9210fa327d03', '4316d4c947c23236d9c74bb8fa8448a1', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('bd5ca30dc7d12366611e04a5c6f498f9', 'f78644098becb54d4b8c9210fa327d03', '5ea2d48af011f309547f007d5cabbd27', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('be6d795c3c48c49483805161e33cc2db', '6f79a34b896e91cefe4a8dc396f76bb0', '1417e20b26a17e778fb2c6356601e7d1', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('bf422a518e759befb86aebdb1525596f', '6f79a34b896e91cefe4a8dc396f76bb0', '440867f56dbf23944789d672b158bd32', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c18cf1e5b637897fadc0d5f30a8107d4', '6f79a34b896e91cefe4a8dc396f76bb0', '6abd125b6490c29c54739d9d0ca27ae9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c25c59087a449c9602963e432c051f6b', '281dd7886533f377fc2dc2c3fa233d1a', '6491c0ddc37084a317b7bf0d5c4817cb', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('c43ee6833ffbbd8551e03940656eb568', 'f78644098becb54d4b8c9210fa327d03', '895155a2550b1816cc01b21d13f9f5ce', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('c4a02b82859e2e14cb7e4a220adde634', '281dd7886533f377fc2dc2c3fa233d1a', '41b3f4ce407fa92523593c6edda2dab8', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('c61c2a6f1ec9d6c661d6542abe8b8cf7', '6f79a34b896e91cefe4a8dc396f76bb0', 'afef72e3ee28af35f2de19ba4c1dc88e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c755f4bb7c71db0193af2dc1f8084d5c', '6f79a34b896e91cefe4a8dc396f76bb0', '285fca26989c2c49978d9853fb38197c', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c98ca7d8795b57fba26335dc8f60869b', '6f79a34b896e91cefe4a8dc396f76bb0', '12c926c9e528aa15481a552b51c646e1', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('ca1bb4a9e701bb00c2e8856f6aac36ef', '281dd7886533f377fc2dc2c3fa233d1a', 'f60544908c9665fe8a7bbc8718531f99', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('cadaffed71c53e8895da9bbfa0eb706a', 'f78644098becb54d4b8c9210fa327d03', '749fac6f70563b29de79b81e3ed50a00', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('ccd6ec48bfd477a94bf897c1009b96ff', 'b78a50cb00af53a5a0d718fc66c31297', '285fca26989c2c49978d9853fb38197c', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('cd441e700b0d814291cadcb817cd607e', 'b78a50cb00af53a5a0d718fc66c31297', 'aa3f6a9f28e47f73ddde5e63c1aee822', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('cea6890ecc22a94f8535bb4ac6dd0b4a', 'f78644098becb54d4b8c9210fa327d03', 'd228447e3e3650cedd98bd8c1724c866', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('cec47956d8f69578f1a0b99b59b72616', 'f78644098becb54d4b8c9210fa327d03', '700e6284217d6743c74bc62efa508239', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('d0324daf9335cde0b15cea8ea064d3ab', '6f79a34b896e91cefe4a8dc396f76bb0', 'cd428ecaf91f3747d4f75953ebab413d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d09e25e2e0d381d5cda7788f96227eb8', 'f78644098becb54d4b8c9210fa327d03', '7e6e754ee2d1f2e0339b5a275a45acdd', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('d0cbfe40a36d36f68148a9a7a4ca4300', '281dd7886533f377fc2dc2c3fa233d1a', '16e3dc94b0a0b0ab3eb491d4e172295d', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('d682dcd97d204ad3257bc57f39511e50', '6f79a34b896e91cefe4a8dc396f76bb0', '6491c0ddc37084a317b7bf0d5c4817cb', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d73f876a5bcba96ba88d759b1356fdde', '6f79a34b896e91cefe4a8dc396f76bb0', '85413b9370843cdb477dfbabf62bf6e5', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d74e85990c225cfb475d598163ee0651', '6f79a34b896e91cefe4a8dc396f76bb0', 'd112adfc4ba9caf20104609ec98712df', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d851d5d5022f46d5eee03d8cf2d74eac', '281dd7886533f377fc2dc2c3fa233d1a', '5c98ab7391bde87525c3a41a4d0e72cf', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('d8e2140ed5339008b258419b320f585a', '6f79a34b896e91cefe4a8dc396f76bb0', '6092364759758c2cd63808bdceba4405', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('dd79f2fc17c00ea926ebec3a0378ddec', '281dd7886533f377fc2dc2c3fa233d1a', 'db75d07485333f3a052cac79b54b7c59', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('e0db0d6abd8516c49545ec7ec02e51f8', '281dd7886533f377fc2dc2c3fa233d1a', 'a1cf74abe6ef8075975281c08bfe1895', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('e24a69177d99f26ae75c280bf5f8b62d', 'b78a50cb00af53a5a0d718fc66c31297', '271bc0f236eaa1afabfec96a91d5cc5d', '2024-05-13 10:15:08', '2024-05-13 10:15:08', '0');
INSERT INTO `sys_role_menu` VALUES ('e3821a6c28712fed56784085c189ad7a', 'f78644098becb54d4b8c9210fa327d03', '4cfb361d436950364b6f99cf12501425', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('e7ec454cc134d8168072d29752612e3b', '281dd7886533f377fc2dc2c3fa233d1a', '2d812ec5ddaedd70c93a2f78ab984334', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('ec2090efb10831de1e9f98ffe6ccdcea', '281dd7886533f377fc2dc2c3fa233d1a', 'af1ddec5003e789ea5d167e7b8b0a773', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('f2ca7f9ccfaf5463734ecdae18688e7d', '6f79a34b896e91cefe4a8dc396f76bb0', 'ed17d32a1f8a7173bcaeb4c84447fb98', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f39a3fa8fd081e2bf02b6004061f2aef', '6f79a34b896e91cefe4a8dc396f76bb0', '271bc0f236eaa1afabfec96a91d5cc5d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f515563b17bef9d44c4c3507f04685ce', '6f79a34b896e91cefe4a8dc396f76bb0', '471c8a9a73d0e2bfa59eb40adffb3869', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f6d535c656eabaa584a8d5e0e88f7d82', '6f79a34b896e91cefe4a8dc396f76bb0', '1f2da8d3b50d9ae744279bdeb3694df0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f81f5771bfc3af46dfcda52019143425', 'f78644098becb54d4b8c9210fa327d03', 'aa3f6a9f28e47f73ddde5e63c1aee822', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('fb0d2cdc8e49f05595e13cd017753271', '6f79a34b896e91cefe4a8dc396f76bb0', '2d812ec5ddaedd70c93a2f78ab984334', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('fc04a1ab55b41604183aefd9382588e8', '281dd7886533f377fc2dc2c3fa233d1a', '440867f56dbf23944789d672b158bd32', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');
INSERT INTO `sys_role_menu` VALUES ('fc626b7dd82a663fd1ab40d52063ed85', '6f79a34b896e91cefe4a8dc396f76bb0', 'd839e3275f5fa4b708929c93f1bc531a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('fcccd54d44dc4c7020bc8435127ce331', 'f78644098becb54d4b8c9210fa327d03', 'c4c9727d9b18bf87531214b8c3b094a9', '2024-05-13 10:11:53', '2024-05-13 10:11:53', '0');
INSERT INTO `sys_role_menu` VALUES ('fef651de1c15f530686e8bcf327b228d', '281dd7886533f377fc2dc2c3fa233d1a', '4cfb361d436950364b6f99cf12501425', '2024-05-13 10:14:47', '2024-05-13 10:14:47', '0');

-- ----------------------------
-- Table structure for sys_todo
-- ----------------------------
DROP TABLE IF EXISTS `sys_todo`;
CREATE TABLE `sys_todo`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `done` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '备忘录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_todo
-- ----------------------------
INSERT INTO `sys_todo` VALUES ('1dcba433dcff57f17f82d1bbdabadfd4', '2022-11-29 17:01:56', '2024-05-13 10:47:07', '1', '网站备案');
INSERT INTO `sys_todo` VALUES ('7fbf8cdf5b49cf0f6362d4f64b55feb0', '2022-12-07 14:54:07', '2022-12-07 14:54:07', '0', '资源前台展示');
INSERT INTO `sys_todo` VALUES ('ad59208e42473b57794d69034f4869da', '2022-12-07 15:09:05', '2024-05-09 17:10:19', '1', '项目日志');
INSERT INTO `sys_todo` VALUES ('b894ce623f551d8495be0fdaea64bb01', '2022-12-07 14:51:55', '2024-05-13 10:15:58', '0', '时间线开发');
INSERT INTO `sys_todo` VALUES ('c4762dec027acc74786eafca0d50a494', '2022-12-08 14:24:41', '2022-12-08 15:12:04', '1', '优化资源页显示');
INSERT INTO `sys_todo` VALUES ('ecb4e59a419d74dd1c9cfc56e9eb60d7', '2022-12-07 14:51:15', '2022-12-08 14:23:21', '1', '折线图开发');
INSERT INTO `sys_todo` VALUES ('f84a1b530e4f7eac8f74709d7a057bf3', '2022-12-07 14:52:04', '2022-12-16 16:58:55', '1', '项目文档');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `head_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位id',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（1：正常 0：停用）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别（0：男 1：女 ）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('149dd22e7c4e8b0f1121ac1d3718dd93', 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2024-05-09 11:40:34', '2024-05-13 16:19:17', '0', '0');
INSERT INTO `sys_user` VALUES ('62f29877096140b14d5be0f57ae50a17', 'visitor', 'e10adc3949ba59abbe56e057f20f883e', '游客账号密码123456', NULL, NULL, NULL, NULL, NULL, NULL, '1', '2024-05-13 16:30:51', '2024-05-13 16:31:52', '0', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_admin_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('f711839a4af247f98a7b50840fea3786', 'f78644098becb54d4b8c9210fa327d03', '62f29877096140b14d5be0f57ae50a17', '2024-05-13 16:31:22', '2024-05-13 16:31:22', '0');

-- ----------------------------
-- Table structure for sys_web_information
-- ----------------------------
DROP TABLE IF EXISTS `sys_web_information`;
CREATE TABLE `sys_web_information`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `web_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站名称',
  `web_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站详情',
  `web_avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信',
  `github` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'github',
  `gitee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'gitee',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `home_banner` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首页背景图',
  `article_banner` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章背景图',
  `wechat_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信二维码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `comment_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论头像',
  `record_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备案号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网站基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_web_information
-- ----------------------------
INSERT INTO `sys_web_information` VALUES (1, '漫漫前路', '<p>长路漫漫，吾将上下而求索。</p>', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/16/de051c412f2f46ba92de7817496cb605.jpg', '946232976', 'fr946232976', 'https://github.com/Jzjzzzz', 'https://gitee.com/jzjzz', '2022-07-28 20:16:13', '2023-01-06 15:30:49', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/09/a108c42fa21a43a4940c3e08cfd33c4f.jpg', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/10/9d26abe18ec84eadbaa8b9f180fefee6.jpg', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/08/f2330c187a6040ed8a74df5aae1cb763.jpg', '946232976@qq.com', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/19/1b0d32a2d72e4ed58b7cc6ede72621b0.png', '桂ICP备2022010904号-1');

-- ----------------------------
-- Table structure for website_resource
-- ----------------------------
DROP TABLE IF EXISTS `website_resource`;
CREATE TABLE `website_resource`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源站点表id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `resource_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源地址',
  `resource_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源图片',
  `resource_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源分类',
  `click_rate` bigint(20) NOT NULL COMMENT '点击量',
  `resource_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源站点' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of website_resource
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
