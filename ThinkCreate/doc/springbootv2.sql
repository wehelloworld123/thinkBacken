/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50725
Source Host           : 127.0.0.1:3306
Source Database       : springbootv2

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-10-05 16:50:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_area
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_area`;
CREATE TABLE `t_sys_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `area_code` varchar(40) NOT NULL COMMENT '区代码',
  `city_code` varchar(40) DEFAULT NULL COMMENT '父级市代码',
  `area_name` varchar(40) NOT NULL COMMENT '市名称',
  `short_name` varchar(20) NOT NULL COMMENT '简称',
  `lng` varchar(20) DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) DEFAULT NULL COMMENT '纬度',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `memo` varchar(250) DEFAULT NULL COMMENT '备注',
  `data_state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`area_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='地区设置';

-- ----------------------------
-- Records of t_sys_area
-- ----------------------------
INSERT INTO `t_sys_area` VALUES ('1975', '500101', '500100', '万州区', '万州', '108.380249', '30.807808', '28', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1976', '500102', '500100', '涪陵区', '涪陵', '107.394905', '29.703651', '11', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1977', '500103', '500100', '渝中区', '渝中', '106.562881', '29.556742', '37', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1978', '500104', '500100', '大渡口区', '大渡口', '106.48613', '29.481003', '6', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1979', '500105', '500100', '江北区', '江北', '106.532845', '29.575352', '13', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1980', '500106', '500100', '沙坪坝区', '沙坪坝', '106.454201', '29.541224', '24', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1981', '500107', '500100', '九龙坡区', '九龙坡', '106.480988', '29.523493', '15', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1982', '500108', '500100', '南岸区', '南岸', '106.560814', '29.523993', '18', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1983', '500109', '500100', '北碚区', '北碚', '106.437866', '29.82543', '2', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1984', '500112', '500100', '渝北区', '渝北', '106.512848', '29.601452', '35', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1985', '500113', '500100', '巴南区', '巴南', '106.519424', '29.38192', '1', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1986', '500114', '500100', '黔江区', '黔江', '108.782578', '29.527548', '21', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1987', '500115', '500100', '长寿区', '长寿', '107.074852', '29.833672', '4', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1988', '500222', '500100', '綦江区', '綦江', '106.651421', '29.028091', '22', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1989', '500223', '500100', '潼南县', '潼南', '105.84182', '30.189554', '27', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1990', '500224', '500100', '铜梁县', '铜梁', '106.054947', '29.839945', '26', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1991', '500225', '500100', '大足区', '大足', '105.715317', '29.700499', '7', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1992', '500226', '500100', '荣昌县', '荣昌', '105.594063', '29.403627', '23', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1993', '500227', '500100', '璧山县', '璧山', '106.231125', '29.59358', '3', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1994', '500228', '500100', '梁平县', '梁平', '107.800034', '30.672169', '17', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1995', '500229', '500100', '城口县', '城口', '108.664902', '31.946293', '5', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1996', '500230', '500100', '丰都县', '丰都', '107.732483', '29.866425', '9', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1997', '500231', '500100', '垫江县', '垫江', '107.348694', '30.330011', '8', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1998', '500232', '500100', '武隆县', '武隆', '107.756554', '29.323759', '29', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('1999', '500233', '500100', '忠县', '忠县', '108.037521', '30.291536', '38', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2000', '500234', '500100', '开县', '开县', '108.413315', '31.167734', '16', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2001', '500235', '500100', '云阳县', '云阳', '108.697701', '30.930529', '36', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2002', '500236', '500100', '奉节县', '奉节', '109.465775', '31.019966', '10', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2003', '500237', '500100', '巫山县', '巫山', '109.878929', '31.074842', '30', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2004', '500238', '500100', '巫溪县', '巫溪', '109.628914', '31.396601', '31', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2005', '500240', '500100', '石柱土家族自治县', '石柱', '108.11245', '29.998529', '25', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2006', '500241', '500100', '秀山土家族苗族自治县', '秀山', '108.99604', '28.444773', '32', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2007', '500242', '500100', '酉阳土家族苗族自治县', '酉阳', '108.767204', '28.839828', '34', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2008', '500243', '500100', '彭水苗族土家族自治县', '彭水', '108.16655', '29.293856', '20', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2009', '500381', '500100', '江津区', '江津', '106.253159', '29.283386', '14', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2010', '500382', '500100', '合川区', '合川', '106.265556', '29.990993', '12', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2011', '500383', '500100', '永川区', '永川', '105.894714', '29.348747', '33', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');
INSERT INTO `t_sys_area` VALUES ('2012', '500384', '500100', '南川区', '南川', '107.098152', '29.156647', '19', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');

-- ----------------------------
-- Table structure for t_sys_city
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_city`;
CREATE TABLE `t_sys_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `city_code` varchar(40) NOT NULL COMMENT '市代码',
  `city_name` varchar(40) NOT NULL COMMENT '市名称',
  `short_name` varchar(20) NOT NULL COMMENT '简称',
  `province_code` varchar(40) DEFAULT NULL COMMENT '省代码',
  `lng` varchar(20) DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) DEFAULT NULL COMMENT '纬度',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `memo` varchar(250) DEFAULT NULL COMMENT '备注',
  `data_state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`city_code`)
) ENGINE=InnoDB AUTO_INCREMENT=391 DEFAULT CHARSET=utf8 COMMENT='城市设置';

-- ----------------------------
-- Records of t_sys_city
-- ----------------------------
INSERT INTO `t_sys_city` VALUES ('255', '500100', '重庆市', '重庆', '500000', '106.504959', '29.533155', '1', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');

-- ----------------------------
-- Table structure for t_sys_datas
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_datas`;
CREATE TABLE `t_sys_datas` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `file_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件表存储表';

-- ----------------------------
-- Records of t_sys_datas
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_data`;
CREATE TABLE `t_sys_dict_data` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of t_sys_dict_data
-- ----------------------------
INSERT INTO `t_sys_dict_data` VALUES ('331043380933038080', '1', '一般', '1', 'sys_notice_type', '', 'info', 'Y', '0', 'admin', '2019-09-09 22:15:03', 'admin', '2019-09-09 22:15:43', '');
INSERT INTO `t_sys_dict_data` VALUES ('331043525137403904', '2', '重要', '2', 'sys_notice_type', '', 'important', 'N', '0', 'admin', '2019-09-09 22:15:37', 'admin', '2019-09-11 00:30:04', '');
INSERT INTO `t_sys_dict_data` VALUES ('340080322395901952', '1', '开启', '0', 'sys_province_state', '', 'info', 'Y', '0', 'admin', '2019-10-04 20:44:37', 'admin', '2019-10-04 20:46:41', '');
INSERT INTO `t_sys_dict_data` VALUES ('340080779201744896', '2', '关闭', '-1', 'sys_province_state', '', 'important', 'Y', '0', 'admin', '2019-10-04 20:46:26', 'admin', '2019-10-04 20:46:45', '');

-- ----------------------------
-- Table structure for t_sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_type`;
CREATE TABLE `t_sys_dict_type` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of t_sys_dict_type
-- ----------------------------
INSERT INTO `t_sys_dict_type` VALUES ('340079827459641344', '省份状态', 'sys_province_state', '0', 'admin', '2019-10-04 20:42:39', '', '2019-10-04 20:42:39', '省份状态');
INSERT INTO `t_sys_dict_type` VALUES ('6', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-09-15 00:29:19', '通知类型列表');

-- ----------------------------
-- Table structure for t_sys_email
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_email`;
CREATE TABLE `t_sys_email` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `receivers_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '接收人电子邮件',
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮件标题',
  `content` text COLLATE utf8_bin COMMENT '内容',
  `send_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送人id',
  `send_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送人账号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='电子邮件';

-- ----------------------------
-- Records of t_sys_email
-- ----------------------------
INSERT INTO `t_sys_email` VALUES ('595001021625794560', '87766867@qq.com', 'springbootv2测试邮件', 0x3C703EE6B58BE8AF95E6B58BE6B58BE6B58B3C2F703E, '1', 'admin', '2019-06-30 21:21:38');


-- ----------------------------
-- Records of t_sys_notice
-- ----------------------------
INSERT INTO `t_sys_notice` VALUES ('330381411007729664', '测试公告', '<p>啊啊啊<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>', '1', '1', 'admin', '2019-09-08 02:24:37');
INSERT INTO `t_sys_notice` VALUES ('330381806358630400', '鲜花视频', '<p>哈哈哈哈<img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>', '2', '1', 'admin', '2019-09-08 02:26:11');
INSERT INTO `t_sys_notice` VALUES ('330622143597514752', '水水水水水水水', '<p>水水水水水水水水水水水水水水水水水水水</p>', '1', '1', 'admin', '2019-09-08 18:21:12');

-- ----------------------------
-- Table structure for t_sys_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice_user`;
CREATE TABLE `t_sys_notice_user` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `notice_id` varchar(255) DEFAULT NULL COMMENT '公告id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `state` int(2) DEFAULT NULL COMMENT '0未阅读 1 阅读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告_用户外键';
-- ----------------------------
-- Records of t_sys_oper_log
-- ----------------------------
INSERT INTO `t_sys_oper_log` VALUES ('337136915998445568', '用户新增', 'com.fc.test.controller.admin.UserController.add()', 'admin', '/UserController/add', '{\"username\":[\"admin222\"],\"password\":[\"admin222\"],\"nickname\":[\"1111\"]}', null, '2019-09-26');
INSERT INTO `t_sys_oper_log` VALUES ('337137170802413568', '用户新增', 'com.fc.test.controller.admin.UserController.add()', 'admin', '/UserController/add', '{\"username\":[\"admin11111\"],\"password\":[\"admin11111\"],\"nickname\":[\"1111111111111111111111\"]}', null, '2019-09-26');
-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('488243256161730560', '管理员');
INSERT INTO `t_sys_role` VALUES ('488289006124007424', '用户');
INSERT INTO `t_sys_role` VALUES ('488305788310257664', '能修改用户密码角色');

-- ----------------------------
-- Table structure for t_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_user`;
CREATE TABLE `t_sys_role_user` (
  `id` varchar(255) NOT NULL,
  `sys_user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `sys_role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Table structure for t_sys_province
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_province`;
CREATE TABLE `t_sys_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `province_code` varchar(40) NOT NULL COMMENT '省份代码',
  `province_name` varchar(50) NOT NULL COMMENT '省份名称',
  `short_name` varchar(20) NOT NULL COMMENT '简称',
  `lng` varchar(20) DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) DEFAULT NULL COMMENT '纬度',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `memo` varchar(250) DEFAULT NULL COMMENT '备注',
  `data_state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`province_code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='省份表';

-- ----------------------------
-- Records of t_sys_province
-- ----------------------------
INSERT INTO `t_sys_province` VALUES ('22', '500000', '重庆', '重庆', '106.504959', '29.533155', '22', '2019-02-28 17:16:58', '2019-02-28 17:17:05', '', '0');

-- ----------------------------
-- Table structure for t_sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_quartz_job`;
CREATE TABLE `t_sys_quartz_job` (
  `id` varchar(255) NOT NULL COMMENT '日志id',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(255) DEFAULT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron执行表达式',
  `misfire_policy` varchar(255) DEFAULT NULL COMMENT 'cron计划策略',
  `concurrent` varchar(255) DEFAULT NULL COMMENT '是否并发执行（0允许 1禁止）',
  `status` int(11) DEFAULT NULL COMMENT '任务状态（0正常 1暂停）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of t_sys_quartz_job
-- ----------------------------
INSERT INTO `t_sys_quartz_job` VALUES ('332182389491109888', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', '*/10 * * * * ?', '12', '1', '1');

-- ----------------------------
-- Table structure for t_sys_quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_quartz_job_log`;
CREATE TABLE `t_sys_quartz_job_log` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(255) DEFAULT NULL COMMENT '调用目标字符串',
  `job_message` varchar(255) DEFAULT NULL COMMENT '日志信息',
  `status` int(11) DEFAULT NULL COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(255) DEFAULT NULL COMMENT '异常信息',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of t_sys_quartz_job_log
-- ----------------------------
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610541354455040', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', 'v2Task2 总共耗时：3毫秒', '0', null, '2019-09-17 00:16:01', '2019-09-17 00:16:01');
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610547549442048', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', 'v2Task2 总共耗时：1毫秒', '0', null, '2019-09-17 00:16:03', '2019-09-17 00:16:03');
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610553832509440', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', 'v2Task2 总共耗时：0毫秒', '0', null, '2019-09-17 00:16:04', '2019-09-17 00:16:04');
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610558995697664', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', 'v2Task2 总共耗时：0毫秒', '0', null, '2019-09-17 00:16:06', '2019-09-17 00:16:06');
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610566486724608', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', 'v2Task2 总共耗时：0毫秒', '0', null, '2019-09-17 00:16:07', '2019-09-17 00:16:07');
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610572270669824', 'v2Task2', 'SYSTEM', 'v2Task.runTask2(1,2l,\'asa\',true,2D)', 'v2Task2 总共耗时：0毫秒', '0', null, '2019-09-17 00:16:09', '2019-09-17 00:16:09');



-- ----------------------------
-- Table structure for t_sys_street
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_street`;
CREATE TABLE `t_sys_street` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `street_code` varchar(40) NOT NULL COMMENT '街道代码',
  `area_code` varchar(40) DEFAULT NULL COMMENT '父级区代码',
  `street_name` varchar(50) NOT NULL COMMENT '街道名称',
  `short_name` varchar(30) NOT NULL COMMENT '简称',
  `lng` varchar(20) DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) DEFAULT NULL COMMENT '纬度',
  `sort` int(6) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `memo` varchar(250) DEFAULT NULL COMMENT '备注',
  `data_state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`street_code`)
) ENGINE=InnoDB AUTO_INCREMENT=42361 DEFAULT CHARSET=utf8 COMMENT='街道设置';

-- ----------------------------
-- Records of t_sys_street
-- ----------------------------



-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `age` int(11) DEFAULT NULL COMMENT '性别',
  `cratetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表';

DROP TABLE IF EXISTS `t_pro_liter`;
CREATE TABLE `t_pro_liter` (
  `uid` varchar(34) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '书名',
  `cover` varchar(50) DEFAULT NULL COMMENT '封面',
  `typ` varchar(100) DEFAULT NULL COMMENT '类型',
  `label` varchar(100) DEFAULT '0' COMMENT '标签',
  `introduce` varchar(100) DEFAULT null COMMENT '介绍',
  `describe` varchar(300) DEFAULT NULL COMMENT '描述',
  `kind` int DEFAULT -1 COMMENT '类别',
  `charpter` int DEFAULT 0 COMMENT '章节',
  `section` int DEFAULT 0 COMMENT '小节',
  `fin_charp` int DEFAULT 0 COMMENT '完成章节',
  `fin_section` int DEFAULT 0 COMMENT '已完成小节',
  `form` int DEFAULT -1 COMMENT '形式',
  `copyright` int DEFAULT 0 COMMENT '版权',
  `reward` varchar(30) DEFAULT NULL COMMENT '版费',
  `deadline` date DEFAULT NULL COMMENT '完成期限',
  `publisher` varchar(50) DEFAULT NULL COMMENT '出版方',
  `is_top` int DEFAULT 0 COMMENT '置顶',
  `partner` int DEFAULT 0 COMMENT '参与创作者',
  `views` bigint DEFAULT 0 COMMENT '浏览量',
  `finish` int DEFAULT 0 COMMENT '完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文学创作信息表';

DROP TABLE IF EXISTS `t_pro_charpt`;
CREATE TABLE `t_pro_charpt` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `introduce` varchar(300) DEFAULT NULL COMMENT '章节介绍',
  `requirement` varchar(300) DEFAULT NULL COMMENT '创作要求',
  `creators` int DEFAULT 0 COMMENT '创作人员数',
  `root` int DEFAULT 0 COMMENT '是否章',
  `root_id` bigint DEFAULT 0 COMMENT '章节id',
  `book_id` varchar(30) DEFAULT NULL COMMENT '书id',
  `paint_id` bigint DEFAULT 0 COMMENT '绘画id',
  `poem_id` bigint DEFAULT 0 COMMENT '诗歌id',
   `root_ord` int DEFAULT 0 COMMENT '父类序号',
  `ord` int DEFAULT 0 COMMENT '序号',
  `is_lock` int DEFAULT 1 COMMENT '上锁',
  `finish` int DEFAULT 0 COMMENT '已完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文学章节信息表';


DROP TABLE IF EXISTS `t_pro_content`;
CREATE TABLE `t_pro_content` (
  `no` varchar(34) NOT NULL COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `summary` varchar(200) DEFAULT NULL COMMENT '简述',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
 // `detail` text DEFAULT NULL  COMMENT '内容详情',
  `file` varchar(30) DEFAULT NULL COMMENT '附件路径',
  `charp_id` int DEFAULT 0 COMMENT '章节Id',
  `book_id` VARCHAR(30) DEFAULT NULL COMMENT '书籍Id',
  `charp_name` VARCHAR(30) DEFAULT NULL COMMENT '章节名称',
  `sec_name` VARCHAR(30) DEFAULT NULL COMMENT '小节名称',
  `likes` int DEFAULT 0 COMMENT '喜欢数',
  `recom_no` int DEFAULT 0 COMMENT '推荐数',
  `views` int DEFAULT 0 COMMENT '展示',
  `adopt` int DEFAULT 0 COMMENT '采纳',
  `favorer` text DEFAULT NULL COMMENT '点赞用户字串',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文学创作表';

DROP TABLE IF EXISTS `t_pro_recom`;
CREATE TABLE `t_pro_recom` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `content_id` varchar(35) DEFAULT 0 COMMENT '章节Id',
  `typ` int DEFAULT -1 COMMENT '推荐类型',
  `likes` int DEFAULT 0 COMMENT '喜欢数',
  `favorer` text DEFAULT NULL COMMENT '点赞用户字串',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='创作推荐表';


DROP TABLE IF EXISTS `t_pro_paint`;
CREATE TABLE `t_pro_paint` (
  `uid` varchar(30) NOT NULL COMMENT '主键',
  `seter` varchar(10) DEFAULT NULL COMMENT '集',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `cover` varchar(30) DEFAULT NULL COMMENT '封面',
  `topic` varchar(30) DEFAULT NULL COMMENT '主旨',
  `purpose` varchar(30) DEFAULT NULL COMMENT '用途',
  `describe` varchar(100) DEFAULT NULL COMMENT '描述',
  `kind` int DEFAULT -1 COMMENT '类别',
  `parts` int DEFAULT -1 COMMENT '部分',
  `section` int DEFAULT 0 COMMENT '小节',
  `fin_part` int DEFAULT 0 COMMENT '完成部分',
  `fin_section` int DEFAULT 0 COMMENT '完成小节',
  `form` varchar(20) DEFAULT NULL COMMENT '形式',
  `partner` bigint DEFAULT 0 COMMENT '参与创作者',
  `views` bigint DEFAULT 0 COMMENT '浏览量',
  `copyright` int DEFAULT 0 COMMENT '版权',
  `reward` varchar(30) DEFAULT NULL COMMENT '奖池',
  `deadline` date DEFAULT NULL COMMENT '截至日期',
  `publisher` varchar(20) DEFAULT NULL COMMENT '出品方',
  `is_top` int DEFAULT 0 COMMENT '置顶',
  `finish` int DEFAULT 0 COMMENT '完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绘画创作表';

DROP TABLE IF EXISTS `t_pro_paint_content`;
CREATE TABLE `t_pro_paint_content` (
  `no` varchar(34) NOT NULL COMMENT '主键',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `image` varchar(100) DEFAULT NULL COMMENT '图片url',
  `cover` varchar(30) DEFAULT NULL COMMENT '封面',
  `part_id` int DEFAULT 0 COMMENT '局部id',
  `paint_id` varchar(30) DEFAULT NULL COMMENT '画作id',
  `paint_name` varchar(30) DEFAULT NULL COMMENT '画集名称',
  `part_name` varchar(30) DEFAULT NULL COMMENT '局部名称',
  `sec_name` varchar(30) DEFAULT NULL COMMENT '小节名称',
  `likes` int DEFAULT 0 COMMENT '喜欢数',
  `recom_no` int DEFAULT 0 COMMENT '推荐数',
  `adopt` int DEFAULT 0 COMMENT '采纳',
  `favorer` text DEFAULT NULL COMMENT '点赞用户字串',
  `dat` date DEFAULT NULL COMMENT '日期',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绘画创作内容表';


DROP TABLE IF EXISTS `t_pro_paint_part`;
CREATE TABLE `t_pro_paint_part` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject` varchar(20) DEFAULT NULL COMMENT '主题',
  `introduce` varchar(300) DEFAULT NULL COMMENT '章节介绍',
  `requirement` varchar(300) DEFAULT NULL COMMENT '创作要求',
  `creators` int DEFAULT 0 COMMENT '创作人员数',
  `root` int DEFAULT 0 COMMENT '根',
  `root_id` bigint DEFAULT 0 COMMENT '分作id',
  `liter_id` bigint DEFAULT 0 COMMENT '文章id',
  `poem_id` bigint DEFAULT 0 COMMENT '诗歌id',
  `number` int DEFAULT 0 COMMENT '数量',
  `root_ord` int DEFAULT 0 COMMENT '父类序号',
  `ord` int DEFAULT 0 COMMENT '序号',
  `paint_id` varchar(30) DEFAULT NULL COMMENT '画作id',
  `finish` int DEFAULT 0 COMMENT '完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='绘画创作部分表';

DROP TABLE IF EXISTS `t_pro_poetry`;
CREATE TABLE `t_pro_poetry` (
  `uid` varchar(35) NOT NULL COMMENT '主键',
  `seter` varchar(10) DEFAULT NULL COMMENT '集',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `cover` varchar(30) DEFAULT NULL COMMENT '封面',
  `topic` varchar(30) DEFAULT NULL COMMENT '主旨',
  `purpose` varchar(30) DEFAULT NULL COMMENT '用途',
  `describe` varchar(100) DEFAULT NULL COMMENT '描述',
  `kind` int DEFAULT -1 COMMENT '类别',
  `charpter` int DEFAULT -1 COMMENT '章节',
  `section` int DEFAULT 0 COMMENT '小节',
  `fin_charpter` int DEFAULT 0 COMMENT '完成章节',
  `fin_section` int DEFAULT 0 COMMENT '完成小节',
  `form` varchar(20) DEFAULT NULL COMMENT '形式',
  `partner` bigint DEFAULT 0 COMMENT '参与创作者',
  `views` bigint DEFAULT 0 COMMENT '浏览量',
  `copyright` int DEFAULT 0 COMMENT '版权',
  `reward` varchar(30) DEFAULT NULL COMMENT '奖池',
  `deadline` date DEFAULT NULL COMMENT '截至日期',
  `publisher` varchar(20) DEFAULT NULL COMMENT '出品方',
  `is_top` int DEFAULT 0 COMMENT '置顶',
  `finish` int DEFAULT 0 COMMENT '完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='诗歌创作表';

DROP TABLE IF EXISTS `t_pro_poemset`;
CREATE TABLE `t_pro_poemset` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `charpter` varchar(20) DEFAULT NULL COMMENT '章节题',
  `describe` varchar(300) DEFAULT NULL COMMENT '章节介绍',
  `requirement` varchar(300) DEFAULT NULL COMMENT '创作要求',
  `root` int DEFAULT 0 COMMENT '根',
  `root_id` bigint DEFAULT 0 COMMENT '分作id',
  `liter_id` bigint DEFAULT 0 COMMENT '文章id',
  `paint_id` bigint DEFAULT 0 COMMENT '画作id',
  `creators` int DEFAULT 0 COMMENT '创作人员数',
  `number` int DEFAULT 0 COMMENT '数量',
  `root_ord` int DEFAULT 0 COMMENT '父类序号',
  `ord` int DEFAULT 0 COMMENT '序号',
  `poetry_id` varchar(30) DEFAULT NULL COMMENT '诗歌id',
  `finish` int DEFAULT 0 COMMENT '完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='诗歌创作章节表';

DROP TABLE IF EXISTS `t_pro_paint_content`;
CREATE TABLE `t_pro_paint_content` (
  `no` varchar(34) NOT NULL COMMENT '主键',
  `brand` varchar(20) DEFAULT NULL COMMENT '词牌',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `content` varchar(300) DEFAULT NULL COMMENT '内容',
  `charp_id` varchar(30) DEFAULT NULL COMMENT '章节id',
  `poetry_id` varchar(34) DEFAULT NULL COMMENT '诗歌集id',

  `poetry_name` varchar(30) DEFAULT NULL COMMENT '诗歌集名称',
  `charp_name` varchar(30) DEFAULT NULL COMMENT '章节名称',
  `sec_name` varchar(30) DEFAULT NULL COMMENT '小节名称',
  `likes` int DEFAULT 0 COMMENT '喜欢数',
  `recom_no` int DEFAULT 0 COMMENT '推荐数',
  `adopt` int DEFAULT 0 COMMENT '采纳',
  `favorer` text DEFAULT NULL COMMENT '点赞用户字串',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='诗歌创作内容表';

DROP TABLE IF EXISTS `t_quo_analect`;
CREATE TABLE `t_quo_analect` (
  `uid` varchar(30) NOT NULL COMMENT '主键',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `describe` varchar(50) DEFAULT NULL COMMENT '描述',
  `back_color` varchar(20) DEFAULT NULL COMMENT '背景颜色',
  `lock` int DEFAULT 0 COMMENT '上锁',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建日期',
/*  `create_tim` datetime DEFAULT NULL COMMENT '创建时间',*/
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论语信息表';

DROP TABLE IF EXISTS `t_quo_record`;
CREATE TABLE `t_quo_record` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sentence` varchar(20) DEFAULT NULL COMMENT '名句',
  `content` varchar(50) DEFAULT NULL COMMENT '内容',
  `anal_id` varchar(30) DEFAULT NULL COMMENT '语录id',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建日期',
/*  `create_tim` datetime DEFAULT NULL COMMENT '创建时间',*/
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语录信息表';

DROP TABLE IF EXISTS `t_sys_user_pro`;
CREATE TABLE `t_sys_user_pro` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `username` varchar(30) DEFAULT NULL  COMMENT '用户名',
  `kind` int DEFAULT 0 COMMENT '类型',
  `pro_id` varchar(32) DEFAULT null COMMENT '作品Id',
  `store` int DEFAULT 0 COMMENT '收藏',
  `status` int DEFAULT 0 COMMENT '状态',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` date DEFAULT NULL COMMENT '创建日期',
/*  `create_tim` time DEFAULT NULL COMMENT '创建时间',*/
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户作品表';



DROP TABLE IF EXISTS `t_pro_user_adopt`;
CREATE TABLE `t_pro_user_adopt` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creation_id` varchar(34) DEFAULT 0 COMMENT '创作id',
  `charp_id` bigint DEFAULT 0 COMMENT '章节id',
  `content_id` varchar(34) DEFAULT NULL COMMENT '内容id',
  `typ` int DEFAULT 0 COMMENT '类型',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户id',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像url',
  `adopt` int DEFAULT 0 COMMENT '采纳',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户创作采纳表';


DROP TABLE IF EXISTS `t_pro_user_product`;
CREATE TABLE `t_pro_user_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creation_id` int DEFAULT 0 COMMENT '创作id',
  `typ` int DEFAULT 0 COMMENT '作品类型',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户id',
  `kind` int DEFAULT 0 COMMENT '收录类型',
  `status` int DEFAULT 0 COMMENT '状态',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收录作品表';


-- 触发器，每插入一条作品采纳表记录，将对应的内容表设置为完成
DELIMITER |
create trigger addAdoptPro
after insert on t_pro_user_adopt
for each row
begin
if new.typ = 1 THEN
update t_pro_content set adopt = 1 where no=new.content_id;
update t_pro_charpt set finish = 1 where id=new.charp_id;
else if new.typ = 2 THEN
update t_pro_paint_content set adopt = 1 where no=new.content_id;
update t_pro_paint_part set finish = 1 where id=new.charp_id;
else if new.typ = 3 THEN
update t_pro_poem_content set adopt = 1 where no=new.content_id;
update t_pro_poemset set finish = 1 where id=new.charp_id;
end if;
end if;
end if;
end
|

-- 触发器，每修改章节完成记录，将该表父节点未完成数减1
DELIMITER |
create trigger updateLiter
after update on t_pro_charpt
for each row
begin
 if
 (old.finish != new.finish) AND (new.finish = 1)
	THEN
 update t_pro_liter set fin_charp = new.root_ord,fin_section =new.ord,partner = partner+1  where uid=new.book_id;
 end if;
end
|

-- 触发器，每修改画作完成记录，将该表父节点未完成数减1
DELIMITER |
create trigger updatePaint
after update on t_pro_paint_part
for each row
begin
 if
 (old.finish != new.finish) AND (new.finish = 1)
 THEN
 update t_pro_paint set fin_charp = new.root_ord,fin_section =new.ord,partner = partner+1  where uid = new.paint_id;
 end if;
end
|

-- 触发器，每修改章节完成记录，将该表父节点未完成数减1
DELIMITER |
create trigger updateLiter
after update on t_pro_poemset
for each row
begin
 if
 (old.finish != new.finish) AND (new.finish = 1)
	THEN
 update t_pro_poetry set fin_charp = new.root_ord,fin_section =new.ord,partner = partner+1  where uid = new.poetry_id;
 end if;
end
|