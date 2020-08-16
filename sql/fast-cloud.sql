/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : fast-cloud

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 16/08/2020 18:45:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mon_login_log
-- ----------------------------
DROP TABLE IF EXISTS `mon_login_log`;
CREATE TABLE `mon_login_log` (
  `login_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `browser_name` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os_name` varchar(50) DEFAULT '' COMMENT '操作系统',
  `message` varchar(255) DEFAULT '' COMMENT '记录信息',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

-- ----------------------------
-- Records of mon_login_log
-- ----------------------------
BEGIN;
INSERT INTO `mon_login_log` VALUES (1, 1, 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 16:50:59', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES (2, 1, 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 17:12:17', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES (3, 1, 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 17:12:59', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES (4, 1, 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 12:05:03', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES (5, 1, 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 16:33:51', '', '', '登录成功');
INSERT INTO `mon_login_log` VALUES (6, 1, 'admin', '0', '192.168.99.140,127.0.0.1', '2020-07-15 18:41:12', '', '', '登录成功');
COMMIT;

-- ----------------------------
-- Table structure for mon_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `mon_oper_log`;
CREATE TABLE `mon_oper_log` (
  `oper_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `message` varchar(255) DEFAULT '' COMMENT '记录信息',
  `business_type` int(2) DEFAULT '1' COMMENT '业务类型（1-查询 2-新增 3-修改 4-删除 5-导入 6-导出）',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `request_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `request_date` datetime DEFAULT NULL COMMENT '访问时间',
  `request_url` varchar(500) DEFAULT NULL COMMENT '请求 URL',
  `execute_time` bigint(20) DEFAULT NULL COMMENT '执行时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  PRIMARY KEY (`oper_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3005 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

-- ----------------------------
-- Records of mon_oper_log
-- ----------------------------
BEGIN;
INSERT INTO `mon_oper_log` VALUES (1, '新增终端配置表', 2, 'POST', '{}', '2020-07-15 17:46:29', '/client', 164, 1, 'admin', '192.168.99.140,127.0.0.1', 0);
INSERT INTO `mon_oper_log` VALUES (2, '修改头像', 3, 'POST', '{}', '2020-07-15 11:07:51', '/user/profile/avatar', 410, 1, 'admin', '192.168.99.140,127.0.0.1', 0);
INSERT INTO `mon_oper_log` VALUES (3001, '修改头像', 3, 'POST', '{}', '2020-07-15 12:09:40', '/user/profile/avatar', 674, 1, 'admin', '192.168.99.140,127.0.0.1', 0);
INSERT INTO `mon_oper_log` VALUES (3002, '个人信息', 3, 'PUT', '{}', '2020-07-15 12:10:31', '/user/profile', 41, 1, 'admin', '192.168.99.140,127.0.0.1', 0);
INSERT INTO `mon_oper_log` VALUES (3003, '修改头像', 3, 'POST', '{}', '2020-07-15 16:59:37', '/user/profile/avatar', 10122, 1, 'admin', '192.168.99.140,127.0.0.1', 0);
INSERT INTO `mon_oper_log` VALUES (3004, '修改头像', 3, 'POST', '{}', '2020-07-15 17:03:08', '/user/profile/avatar', 416, 1, 'admin', '192.168.99.140,127.0.0.1', 0);
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '终端编号',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '资源ID标识',
  `client_secret` varchar(255) NOT NULL COMMENT '终端安全码',
  `scope` varchar(255) NOT NULL COMMENT '终端授权范围',
  `authorized_grant_types` varchar(255) NOT NULL COMMENT '终端授权类型',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '服务器回调地址',
  `authorities` varchar(255) DEFAULT NULL COMMENT '访问资源所需权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '设定终端的access_token的有效时间值（秒）',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定终端的refresh_token的有效时间值（秒）',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '附加信息',
  `autoapprove` tinyint(4) DEFAULT NULL COMMENT '是否登录时跳过授权',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='终端配置表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('fastcloud', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,client_credentials,refresh_token', '', NULL, 3600, 7200, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('web', '', '$2a$10$y2hKeELx.z3Sbz.kjQ4wmuiIsv5ZSbUQ1ov4BwFH6ccirP8Knp1uq', 'server', 'password,refresh_token', '', NULL, 3600, 7200, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `parent_ids` varchar(500) DEFAULT NULL COMMENT '父部门ids',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) DEFAULT '0' COMMENT '排序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人Id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, '未来科技', 0, '0', NULL, NULL, NULL, NULL, NULL, 0, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dept` VALUES (100, '上海分公司', 1, '0,1', NULL, NULL, NULL, NULL, NULL, 1, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dept` VALUES (101, '人事部', 100, '0,1,100', NULL, NULL, NULL, NULL, NULL, 1, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dept` VALUES (102, '研发部', 100, '0,1,100', NULL, NULL, NULL, NULL, NULL, 2, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dept` VALUES (200, '北京分公司', 1, '0,1', NULL, NULL, NULL, NULL, NULL, 0, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dept` VALUES (201, '人事部', 200, '0,1,200', NULL, NULL, NULL, NULL, NULL, 1, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dept` VALUES (202, '研发部', 200, '0,1,200', NULL, NULL, NULL, NULL, NULL, 2, '0', 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `dict_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_code` varchar(100) NOT NULL COMMENT '字典类型',
  `item_name` varchar(100) DEFAULT '' COMMENT '字典项名称',
  `item_value` varchar(100) DEFAULT '' COMMENT '字典项键值',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` VALUES (1, 'sys_user_sex', '男', '0', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (2, 'sys_user_sex', '女', '1', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (3, 'sys_user_sex', '未知', '2', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (5, 'sys_show_hide', '显示', '0', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (6, 'sys_show_hide', '隐藏', '1', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (7, 'sys_normal_disable', '正常', '0', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (8, 'sys_normal_disable', '停用', '1', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (9, 'sys_common_status', '成功', '0', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (10, 'sys_common_status', '失败', '1', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (11, 'sys_oper_type', '查询', '1', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (12, 'sys_oper_type', '新增', '2', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (13, 'sys_oper_type', '修改', '3', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (14, 'sys_oper_type', '删除', '4', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (15, 'sys_oper_type', '导入', '5', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (16, 'sys_oper_type', '导出', '6', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (102, 'sys_user_initPassword', '默认密码', '123', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (103, 'article_state', '已发布', '0', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (104, 'article_state', '草稿', '1', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_item` VALUES (105, 'article_state', '已删除', '2', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_code` varchar(100) DEFAULT '' COMMENT '字典编码',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES (2, '用户初始密码', 'sys_user_initPassword', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES (3, '菜单状态', 'sys_show_hide', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES (4, '系统开关', 'sys_normal_disable', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES (5, '登录状态', 'sys_common_status', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES (6, '操作类型', 'sys_oper_type', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_dict_type` VALUES (7, '文章状态', 'article_state', '0', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `menu_type` int(1) DEFAULT NULL COMMENT '菜单类型（1-目录 2-菜单 3-按钮）',
  `router_path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_link` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `visible` char(1) DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) DEFAULT '0' COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=300212 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', '', 1, 'system', 0, NULL, 1, '0', '0', 'system', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (2, '系统监控', '', 1, 'monitor', 0, NULL, 1, '0', '0', 'monitor', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1001, '用户管理', 'system:user:list', 2, 'user', 1, 'system/user/index', 1, '0', '0', 'user', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1002, '角色管理', 'system:role:list', 2, 'role', 1, 'system/role/index', 1, '0', '0', 'peoples', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1003, '菜单管理', 'system:menu:list', 2, 'menu', 1, 'system/menu/index', 1, '0', '0', 'tree-table', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1004, '部门管理', 'system:dept:list', 2, 'dept', 1, 'system/dept/index', 1, '0', '0', 'tree', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1005, '岗位管理', 'system:post:list', 2, 'post', 1, 'system/post/index', 1, '0', '0', 'post', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1006, '字典管理', 'system:dict:list', 2, 'dict', 1, 'system/dict/index', 1, '0', '0', 'dict', NULL, 6, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (1007, '终端管理', 'system:client:list', 2, 'client', 1, 'system/client/index', 1, '0', '0', 'server', NULL, 7, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (2001, '登录日志', 'monitor:loginlog:list', 2, 'loginlog', 2, 'monitor/loginlog/index', 1, '0', '0', 'form', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (2002, '操作日志', 'monitor:operlog:list', 2, 'operlog', 2, 'monitor/operlog/index', 1, '0', '0', 'logininfor', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (2004, '接口文档', 'monitor:swagger:list', 2, 'swagger', 2, 'monitor/swagger/index', 1, '0', '0', 'swagger', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100101, '用户查询', 'system:user:query', 3, '', 1001, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100102, '用户新增', 'system:user:add', 3, '', 1001, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100103, '用户修改', 'system:user:edit', 3, '', 1001, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100104, '用户删除', 'system:user:delete', 3, '', 1001, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100105, '用户导出', 'system:user:export', 3, '', 1001, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100201, '角色查询', 'system:role:query', 3, '', 1002, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100202, '角色新增', 'system:role:add', 3, '', 1002, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100203, '角色修改', 'system:role:edit', 3, '', 1002, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100204, '角色删除', 'system:role:delete', 3, '', 1002, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100205, '角色导出', 'system:role:export', 3, '', 1002, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100301, '菜单查询', 'system:menu:query', 3, '', 1003, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100302, '菜单新增', 'system:menu:add', 3, '', 1003, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100303, '菜单修改', 'system:menu:edit', 3, '', 1003, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100304, '菜单删除', 'system:menu:delete', 3, '', 1003, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100305, '菜单导出', 'system:menu:export', 3, '', 1003, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100401, '部门查询', 'system:dept:query', 3, '', 1004, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100402, '部门新增', 'system:dept:add', 3, '', 1004, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100403, '部门修改', 'system:dept:edit', 3, '', 1004, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100404, '部门删除', 'system:dept:delete', 3, '', 1004, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100405, '部门导出', 'system:dept:export', 3, '', 1004, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100501, '岗位查询', 'system:post:query', 3, '', 1005, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100502, '岗位新增', 'system:post:add', 3, '', 1005, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100503, '岗位修改', 'system:post:edit', 3, '', 1005, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100504, '岗位删除', 'system:post:delete', 3, '', 1005, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100505, '岗位导出', 'system:post:export', 3, '', 1005, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100601, '字典查询', 'system:dict:query', 3, '', 1006, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100602, '字典新增', 'system:dictt:add', 3, '', 1006, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100603, '字典修改', 'system:dict:edit', 3, '', 1006, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100604, '字典删除', 'system:dict:delete', 3, '', 1006, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100605, '字典导出', 'system:dict:export', 3, '', 1006, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100701, '终端查询', 'system:client:query', 3, '', 1007, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100702, '终端新增', 'system:client:add', 3, '', 1007, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100703, '终端修改', 'system:client:edit', 3, '', 1007, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (100704, '终端删除', 'system:client:delete', 3, '', 1007, '', 1, '0', '0', '#', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200101, '登录日志查询', 'monitor:loginlog:query', 3, '', 2001, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200102, '登录日志新增', 'monitor:loginlog:add', 3, '', 2001, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200103, '登录日志修改', 'monitor:loginlog:edit', 3, '', 2001, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200104, '登录日志删除', 'monitor:loginlog:delete', 3, '', 2001, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200105, '登录日志导出', 'monitor:loginlog:export', 3, '', 2001, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200201, '操作日志查询', 'monitor:operlog:query', 3, '', 2002, '', 1, '0', '0', '#', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200202, '操作日志新增', 'monitor:operlog:add', 3, '', 2002, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200203, '操作日志修改', 'monitor:operlog:edit', 3, '', 2002, '', 1, '0', '0', '#', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200204, '操作日志删除', 'monitor:operlog:delete', 3, '', 2002, '', 1, '0', '0', '#', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_menu` VALUES (200205, '操作日志导出', 'monitor:operlog:export', 3, '', 2002, '', 1, '0', '0', '#', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) NOT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post` VALUES (2, 'manager', '总经理', '0', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES (3, 'hr', '人事', '0', NULL, 3, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES (4, 'finance', '财务', '0', NULL, 4, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_post` VALUES (5, 'sale', '销售', '0', NULL, 5, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `order_num` int(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', '1', '0', NULL, 1, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_role` VALUES (100, '测试', 'ceshi', '2', '0', NULL, 2, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` VALUES (100, 1);
INSERT INTO `sys_role_dept` VALUES (100, 100);
INSERT INTO `sys_role_dept` VALUES (100, 101);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 1001);
INSERT INTO `sys_role_menu` VALUES (1, 1002);
INSERT INTO `sys_role_menu` VALUES (1, 1003);
INSERT INTO `sys_role_menu` VALUES (1, 1004);
INSERT INTO `sys_role_menu` VALUES (1, 1005);
INSERT INTO `sys_role_menu` VALUES (1, 1006);
INSERT INTO `sys_role_menu` VALUES (1, 2001);
INSERT INTO `sys_role_menu` VALUES (1, 2002);
INSERT INTO `sys_role_menu` VALUES (1, 2003);
INSERT INTO `sys_role_menu` VALUES (1, 2004);
INSERT INTO `sys_role_menu` VALUES (1, 100101);
INSERT INTO `sys_role_menu` VALUES (1, 100102);
INSERT INTO `sys_role_menu` VALUES (1, 100103);
INSERT INTO `sys_role_menu` VALUES (1, 100104);
INSERT INTO `sys_role_menu` VALUES (1, 100105);
INSERT INTO `sys_role_menu` VALUES (1, 100201);
INSERT INTO `sys_role_menu` VALUES (1, 100202);
INSERT INTO `sys_role_menu` VALUES (1, 100203);
INSERT INTO `sys_role_menu` VALUES (1, 100204);
INSERT INTO `sys_role_menu` VALUES (1, 100205);
INSERT INTO `sys_role_menu` VALUES (1, 100301);
INSERT INTO `sys_role_menu` VALUES (1, 100302);
INSERT INTO `sys_role_menu` VALUES (1, 100303);
INSERT INTO `sys_role_menu` VALUES (1, 100304);
INSERT INTO `sys_role_menu` VALUES (1, 100305);
INSERT INTO `sys_role_menu` VALUES (1, 100401);
INSERT INTO `sys_role_menu` VALUES (1, 100402);
INSERT INTO `sys_role_menu` VALUES (1, 100403);
INSERT INTO `sys_role_menu` VALUES (1, 100404);
INSERT INTO `sys_role_menu` VALUES (1, 100405);
INSERT INTO `sys_role_menu` VALUES (1, 100501);
INSERT INTO `sys_role_menu` VALUES (1, 100502);
INSERT INTO `sys_role_menu` VALUES (1, 100503);
INSERT INTO `sys_role_menu` VALUES (1, 100504);
INSERT INTO `sys_role_menu` VALUES (1, 100505);
INSERT INTO `sys_role_menu` VALUES (1, 100601);
INSERT INTO `sys_role_menu` VALUES (1, 100602);
INSERT INTO `sys_role_menu` VALUES (1, 100603);
INSERT INTO `sys_role_menu` VALUES (1, 100604);
INSERT INTO `sys_role_menu` VALUES (1, 100605);
INSERT INTO `sys_role_menu` VALUES (1, 200101);
INSERT INTO `sys_role_menu` VALUES (1, 200102);
INSERT INTO `sys_role_menu` VALUES (1, 200103);
INSERT INTO `sys_role_menu` VALUES (1, 200104);
INSERT INTO `sys_role_menu` VALUES (1, 200105);
INSERT INTO `sys_role_menu` VALUES (1, 200201);
INSERT INTO `sys_role_menu` VALUES (1, 200202);
INSERT INTO `sys_role_menu` VALUES (1, 200203);
INSERT INTO `sys_role_menu` VALUES (1, 200204);
INSERT INTO `sys_role_menu` VALUES (1, 200205);
INSERT INTO `sys_role_menu` VALUES (100, 1);
INSERT INTO `sys_role_menu` VALUES (100, 2);
INSERT INTO `sys_role_menu` VALUES (100, 1001);
INSERT INTO `sys_role_menu` VALUES (100, 1002);
INSERT INTO `sys_role_menu` VALUES (100, 1003);
INSERT INTO `sys_role_menu` VALUES (100, 1004);
INSERT INTO `sys_role_menu` VALUES (100, 1005);
INSERT INTO `sys_role_menu` VALUES (100, 1006);
INSERT INTO `sys_role_menu` VALUES (100, 2001);
INSERT INTO `sys_role_menu` VALUES (100, 2002);
INSERT INTO `sys_role_menu` VALUES (100, 2003);
INSERT INTO `sys_role_menu` VALUES (100, 100101);
INSERT INTO `sys_role_menu` VALUES (100, 100102);
INSERT INTO `sys_role_menu` VALUES (100, 100103);
INSERT INTO `sys_role_menu` VALUES (100, 100104);
INSERT INTO `sys_role_menu` VALUES (100, 100105);
INSERT INTO `sys_role_menu` VALUES (100, 100201);
INSERT INTO `sys_role_menu` VALUES (100, 100202);
INSERT INTO `sys_role_menu` VALUES (100, 100203);
INSERT INTO `sys_role_menu` VALUES (100, 100204);
INSERT INTO `sys_role_menu` VALUES (100, 100205);
INSERT INTO `sys_role_menu` VALUES (100, 100301);
INSERT INTO `sys_role_menu` VALUES (100, 100302);
INSERT INTO `sys_role_menu` VALUES (100, 100303);
INSERT INTO `sys_role_menu` VALUES (100, 100304);
INSERT INTO `sys_role_menu` VALUES (100, 100305);
INSERT INTO `sys_role_menu` VALUES (100, 100401);
INSERT INTO `sys_role_menu` VALUES (100, 100402);
INSERT INTO `sys_role_menu` VALUES (100, 100403);
INSERT INTO `sys_role_menu` VALUES (100, 100404);
INSERT INTO `sys_role_menu` VALUES (100, 100405);
INSERT INTO `sys_role_menu` VALUES (100, 100501);
INSERT INTO `sys_role_menu` VALUES (100, 100502);
INSERT INTO `sys_role_menu` VALUES (100, 100503);
INSERT INTO `sys_role_menu` VALUES (100, 100504);
INSERT INTO `sys_role_menu` VALUES (100, 100505);
INSERT INTO `sys_role_menu` VALUES (100, 100601);
INSERT INTO `sys_role_menu` VALUES (100, 100602);
INSERT INTO `sys_role_menu` VALUES (100, 100603);
INSERT INTO `sys_role_menu` VALUES (100, 100604);
INSERT INTO `sys_role_menu` VALUES (100, 100605);
INSERT INTO `sys_role_menu` VALUES (100, 200101);
INSERT INTO `sys_role_menu` VALUES (100, 200102);
INSERT INTO `sys_role_menu` VALUES (100, 200103);
INSERT INTO `sys_role_menu` VALUES (100, 200104);
INSERT INTO `sys_role_menu` VALUES (100, 200105);
INSERT INTO `sys_role_menu` VALUES (100, 200201);
INSERT INTO `sys_role_menu` VALUES (100, 200202);
INSERT INTO `sys_role_menu` VALUES (100, 200203);
INSERT INTO `sys_role_menu` VALUES (100, 200204);
INSERT INTO `sys_role_menu` VALUES (100, 200205);
INSERT INTO `sys_role_menu` VALUES (100, 200301);
INSERT INTO `sys_role_menu` VALUES (100, 200302);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_status` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 102, 'admin', 'admin', '00', 'admin1@qq.com', '13910011001', '0', '/fast-cloud/2020-07-15/1597568588827.png', '$2a$10$wB/CLoEv/ic4ca9r/ATFReier32QA0FZwNd8yE0Mlm3VmyHT6AAuu', '0', '0', '192.168.99.140', '2020-07-23 12:02:13', NULL, 'admin', '2020-06-06 03:00:00', NULL, NULL);
INSERT INTO `sys_user` VALUES (105, 102, 'ceshi', 'ceshi', '00', 'ceshi@qq.com', '13310011001', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '192.168.99.103', '2020-07-21 13:43:18', NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
INSERT INTO `sys_user` VALUES (106, 101, '1', '1', '00', '1@qq.com', '13110011000', '0', '', '$2a$10$ErIDLtoMqu6wa1zQhaC5muyLOn.sfcI7zEqwL5l18uhafCwifLAYW', '0', '0', '', NULL, NULL, 'admin', '2020-06-06 03:00:00', NULL, NULL);
INSERT INTO `sys_user` VALUES (107, 101, '2', '2', '00', '2@qq.com', '13120002000', '0', '', '$2a$10$qUajjZCSXyx3BZ4hBKmYreVHfD3ovSfxKWcBZW3dyz6f7D6BxaMNm', '0', '0', '', NULL, NULL, 'admin', '2020-06-06 03:00:00', 'admin', '2020-06-06 03:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_post` VALUES (1, 2);
INSERT INTO `sys_user_post` VALUES (105, 3);
INSERT INTO `sys_user_post` VALUES (106, 3);
INSERT INTO `sys_user_post` VALUES (107, 3);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (105, 100);
INSERT INTO `sys_user_role` VALUES (106, 100);
INSERT INTO `sys_user_role` VALUES (107, 100);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
