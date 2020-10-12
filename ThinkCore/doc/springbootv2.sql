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
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名字',
  `create_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名字',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件信息表';

-- ----------------------------
-- Records of t_sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_file_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file_data`;
CREATE TABLE `t_sys_file_data` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `data_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '数据id',
  `file_id` varchar(255) COLLATE utf8_bin DEFAULT '文件id' COMMENT '文件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件数据外键绑定表';

-- ----------------------------
-- Records of t_sys_file_data
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice`;
CREATE TABLE `t_sys_notice` (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `type` int(5) DEFAULT NULL COMMENT '类型',
  `create_id` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `create_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '发信时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告';

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
-- Records of t_sys_notice_user
-- ----------------------------
INSERT INTO `t_sys_notice_user` VALUES ('330381411037089792', '330381411007729664', '1', '1');
INSERT INTO `t_sys_notice_user` VALUES ('330381411045478400', '330381411007729664', '488294747442511872', '0');
INSERT INTO `t_sys_notice_user` VALUES ('330381806375407616', '330381806358630400', '1', '0');
INSERT INTO `t_sys_notice_user` VALUES ('330381806379601920', '330381806358630400', '488294747442511872', '0');
INSERT INTO `t_sys_notice_user` VALUES ('330622143622680576', '330622143597514752', '1', '1');
INSERT INTO `t_sys_notice_user` VALUES ('330622143626874880', '330622143597514752', '488294747442511872', '0');

-- ----------------------------
-- Table structure for t_sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_oper_log`;
CREATE TABLE `t_sys_oper_log` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '方法',
  `oper_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `oper_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'url',
  `oper_param` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `error_msg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `oper_time` date DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志记录表';

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
-- Records of t_sys_role_user
-- ----------------------------
INSERT INTO `t_sys_role_user` VALUES ('594342089710370816', '1', '488243256161730560');
INSERT INTO `t_sys_role_user` VALUES ('602322111859523584', '488294747442511872', '488289006124007424');
INSERT INTO `t_sys_role_user` VALUES ('612107905532952576', '612107905532952576', '488289006124007424');
INSERT INTO `t_sys_role_user` VALUES ('612107905537146880', '612107905532952576', '488305788310257664');


-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员');
INSERT INTO `t_sys_user` VALUES ('488294747442511872', 'fuce', 'e10adc3949ba59abbe56e057f20f883e', '米西');
-- ----------------------------
-- Table structure for t_sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission_role`;
CREATE TABLE `t_sys_permission_role` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(255) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限中间表';

-- ----------------------------
-- Records of t_sys_permission_role
-- ----------------------------
INSERT INTO `t_sys_permission_role` VALUES ('03b0e9e9-b4fd-40e2-a301-04c3129a470a', '488243256161730560', '3401274122705346604');
INSERT INTO `t_sys_permission_role` VALUES ('0765b08f-2bc9-4aca-817b-83c04bf7484d', '488243256161730560', '16');
INSERT INTO `t_sys_permission_role` VALUES ('08ef46dd-7e70-48af-9137-fd308c2dc667', '488243256161730560', '8');
INSERT INTO `t_sys_permission_role` VALUES ('0964eebc-79ea-4468-a260-ffebdebcdc5e', '488243256161730560', '3303650266428252204');
INSERT INTO `t_sys_permission_role` VALUES ('0d7431a8-bb6c-4f52-959e-ae734c1b5522', '488243256161730560', '496127240363311104');
INSERT INTO `t_sys_permission_role` VALUES ('11f5ac72-248a-4d98-bb47-606778c19227', '488243256161730560', '5946910264304599043');
INSERT INTO `t_sys_permission_role` VALUES ('1398e8c4-cda6-4ba3-ba12-53f7290542c4', '488243256161730560', '3303650266428252171');
INSERT INTO `t_sys_permission_role` VALUES ('150df200-32a2-41bc-a239-a3e41f2c1c78', '488243256161730560', '496126970468237312');
INSERT INTO `t_sys_permission_role` VALUES ('17c0b7c1-cd2b-40e1-92f0-4fce77631f16', '488243256161730560', '340088022018166784');
INSERT INTO `t_sys_permission_role` VALUES ('18393ce8-877b-4108-8211-21e8771a42f0', '488243256161730560', '3328572814798397451');
INSERT INTO `t_sys_permission_role` VALUES ('1a06c4d5-b374-49f3-8c9d-b32756cbe3cf', '488243256161730560', '12');
INSERT INTO `t_sys_permission_role` VALUES ('22b511bd-1081-426c-b90f-802a0511edfa', '488243256161730560', '3400681518049566764');
INSERT INTO `t_sys_permission_role` VALUES ('260129e7-842e-45c3-bb54-5471ea915b9b', '488243256161730560', '5946910264304599041');
INSERT INTO `t_sys_permission_role` VALUES ('27c3b417-144a-4a49-a460-3ba0d7a011fa', '488243256161730560', '575852089792528384');
INSERT INTO `t_sys_permission_role` VALUES ('27d466cf-75af-4028-8450-411293b0bdac', '488243256161730560', '9');
INSERT INTO `t_sys_permission_role` VALUES ('281d9eb3-5984-42d2-9b39-0365585776bb', '488243256161730560', '3400961831355064353');
INSERT INTO `t_sys_permission_role` VALUES ('28749e58-c30f-45cc-aec9-d645e604838c', '488243256161730560', '3400880220181667873');
INSERT INTO `t_sys_permission_role` VALUES ('2bead982-ca89-4b58-9e88-446fd9baa890', '488243256161730560', '340066503263129600');
INSERT INTO `t_sys_permission_role` VALUES ('2c5f27d2-3c63-4a86-849a-29ff02dec893', '488243256161730560', '575853607149109248');
INSERT INTO `t_sys_permission_role` VALUES ('2e1cb245-4534-4416-ad26-f80b834d56dd', '488243256161730560', '3303650266428252182');
INSERT INTO `t_sys_permission_role` VALUES ('2fd3b60c-735f-457b-9f77-59670d50d422', '488243256161730560', '3400880220181667851');
INSERT INTO `t_sys_permission_role` VALUES ('30a1978c-1a81-4504-aa30-2326a62cf4eb', '488243256161730560', '6192095215075983363');
INSERT INTO `t_sys_permission_role` VALUES ('325f1f75-f3b4-4bda-ad47-70efbd40c060', '488243256161730560', '340068151804956672');
INSERT INTO `t_sys_permission_role` VALUES ('366965f0-9c8d-4c9e-846b-751ee49f4ead', '488243256161730560', '3321578609202995233');
INSERT INTO `t_sys_permission_role` VALUES ('391300b0-2ed6-4632-950b-6fc3247f3ef6', '488243256161730560', '6192095214866268162');
INSERT INTO `t_sys_permission_role` VALUES ('3a04cdaa-85e5-4c4d-9f58-640650a75241', '488243256161730560', '5');
INSERT INTO `t_sys_permission_role` VALUES ('3fad7a3c-53fe-425e-886b-c8eec5433621', '488243256161730560', '11');
INSERT INTO `t_sys_permission_role` VALUES ('41b398a6-f02c-4809-8adb-e3b68f28e133', '488243256161730560', '21');
INSERT INTO `t_sys_permission_role` VALUES ('461de94e-99d4-4bee-8909-a09870214719', '488243256161730560', '592167738407911424');
INSERT INTO `t_sys_permission_role` VALUES ('481a0635-05d4-4495-bb31-03afe5837781', '488243256161730560', '3400681518049566742');
INSERT INTO `t_sys_permission_role` VALUES ('488c942a-622f-42e6-a1b1-b3e979c8c908', '488243256161730560', '6192095215075983364');
INSERT INTO `t_sys_permission_role` VALUES ('48cb2013-fd47-49a6-80cd-7a66cbda1537', '488243256161730560', '619836559427895296');
INSERT INTO `t_sys_permission_role` VALUES ('492dde30-2198-455b-a94f-223897c2c970', '488243256161730560', '20');
INSERT INTO `t_sys_permission_role` VALUES ('4bd1ee94-441f-4ca0-b758-4e0dd45ea7c1', '488243256161730560', '610983815791247360');
INSERT INTO `t_sys_permission_role` VALUES ('519054d0-3b9e-447a-96d8-ae431c8e22d5', '488243256161730560', '592059865673760768');
INSERT INTO `t_sys_permission_role` VALUES ('536846fb-54b8-4db8-89d3-2b3992111acb', '488243256161730560', '5946910264304599042');
INSERT INTO `t_sys_permission_role` VALUES ('5b0a8f80-559a-4493-8c6c-62c14f21082c', '488243256161730560', '3400961831355064331');
INSERT INTO `t_sys_permission_role` VALUES ('5caf3b70-75a7-4f94-b738-e4e890c7f12b', '488243256161730560', '13');
INSERT INTO `t_sys_permission_role` VALUES ('60aaa4ac-c554-405d-b2fb-1f2261158c16', '488243256161730560', '6189186317989969924');
INSERT INTO `t_sys_permission_role` VALUES ('62776c9e-b8e6-405a-b9c9-b65b67a15ea6', '488243256161730560', '486690002869157888');
INSERT INTO `t_sys_permission_role` VALUES ('651260bb-55e5-4f7d-840f-1e35fe36ea45', '488243256161730560', '6192095214866268161');
INSERT INTO `t_sys_permission_role` VALUES ('6582e003-7d43-4180-8e23-b050cc1a4d2e', '488243256161730560', '340096183135506432');
INSERT INTO `t_sys_permission_role` VALUES ('65f40af7-ed25-4836-bb8e-9a796a2d5fe0', '488243256161730560', '332857281479839744');
INSERT INTO `t_sys_permission_role` VALUES ('668d20e1-a56a-4ea9-908c-98b5bcc6a182', '488243256161730560', '587453033487532032');
INSERT INTO `t_sys_permission_role` VALUES ('6a1e9508-96a1-4c42-9c28-ec92af14ed3c', '488243256161730560', '3328572814798397473');
INSERT INTO `t_sys_permission_role` VALUES ('6cb99347-9d82-473e-88b6-415f4e8a2957', '488243256161730560', '17');
INSERT INTO `t_sys_permission_role` VALUES ('6ef3576c-4f93-4a07-93c1-7ef7ab1fb3dc', '488243256161730560', '3400880220181667862');
INSERT INTO `t_sys_permission_role` VALUES ('7a879be0-1bdb-44b6-8002-0f981fe7c81d', '488243256161730560', '330365026642825216');
INSERT INTO `t_sys_permission_role` VALUES ('7f5804a2-57b8-49ae-9be4-6e7865ea63a1', '488243256161730560', '3321578609202995244');
INSERT INTO `t_sys_permission_role` VALUES ('8261586c-95d4-455a-afc6-978407628a67', '488243256161730560', '4');
INSERT INTO `t_sys_permission_role` VALUES ('85a9fff6-72c1-43cb-a681-b4309c856797', '488243256161730560', '10');
INSERT INTO `t_sys_permission_role` VALUES ('924cc85c-9e57-447b-83fe-021c81f80afa', '488243256161730560', '1');
INSERT INTO `t_sys_permission_role` VALUES ('92f7def5-aeb8-48b9-acc2-ad6a7f10beb9', '488243256161730560', '23');
INSERT INTO `t_sys_permission_role` VALUES ('952b7b3e-9f61-47ec-b4bf-446dbe5dd4c2', '488243256161730560', '340127412270534656');
INSERT INTO `t_sys_permission_role` VALUES ('957a0ad0-11a2-4951-adc6-35f3c2ce500f', '488243256161730560', '592067570522128384');
INSERT INTO `t_sys_permission_role` VALUES ('98bce365-0255-4537-b2fc-a1b6b8f09b3b', '488243256161730560', '3400961831355064342');
INSERT INTO `t_sys_permission_role` VALUES ('9f6b5700-e7b3-4c69-a1a5-79f160533f53', '488243256161730560', '332157860920299520');
INSERT INTO `t_sys_permission_role` VALUES ('9fdc6cb7-6ebb-4b91-92f3-54886b30d7d9', '488243256161730560', '3400880220181667884');
INSERT INTO `t_sys_permission_role` VALUES ('a08b4c09-8db2-4f93-abfc-21c22d5fae82', '488243256161730560', '7');
INSERT INTO `t_sys_permission_role` VALUES ('a253c83d-4bfe-4f63-873b-74039e2a987f', '488243256161730560', '3401274122705346582');
INSERT INTO `t_sys_permission_role` VALUES ('a8eb57dd-2707-4a1b-83b2-8d4bbe934729', '488243256161730560', '575851658483859456');
INSERT INTO `t_sys_permission_role` VALUES ('ac9e1b57-fc19-4146-9f91-f6a2b3b2736e', '488243256161730560', '3303650266428252193');
INSERT INTO `t_sys_permission_role` VALUES ('b194c629-736e-4aae-b364-49b5965c96c3', '488243256161730560', '610635950447394816');
INSERT INTO `t_sys_permission_role` VALUES ('b4895f30-8889-42ce-83e8-5597c757be53', '488243256161730560', '6189186317738311681');
INSERT INTO `t_sys_permission_role` VALUES ('b4a151ff-f040-4ae2-88e3-042933fcbbf8', '488243256161730560', '496127794879660032');
INSERT INTO `t_sys_permission_role` VALUES ('b7912ffe-e836-4ad0-bb79-80a10162ba17', '488243256161730560', '18');
INSERT INTO `t_sys_permission_role` VALUES ('bfd1ec3e-562c-4b59-a06c-34341dfdc56f', '488243256161730560', '3401274122705346571');
INSERT INTO `t_sys_permission_role` VALUES ('c022f0e5-4549-4d16-bbf0-ee6188f0438f', '488243256161730560', '3321578609202995211');
INSERT INTO `t_sys_permission_role` VALUES ('c119b8ca-5b05-4946-a854-37f3898ea6f9', '488243256161730560', '335330315113467904');
INSERT INTO `t_sys_permission_role` VALUES ('c20b9025-d55a-44f0-b7a5-959373b75715', '488243256161730560', '6189186317948026882');
INSERT INTO `t_sys_permission_role` VALUES ('c46d2db3-cc19-4f55-8377-64b11c677a09', '488243256161730560', '14');
INSERT INTO `t_sys_permission_role` VALUES ('c4d79ea3-db80-4c70-8e9e-28526007fb77', '488243256161730560', '6');
INSERT INTO `t_sys_permission_role` VALUES ('c6fa0b93-c169-42e6-bb14-2779ece9e0ad', '488243256161730560', '3400961831355064364');
INSERT INTO `t_sys_permission_role` VALUES ('cb50cdb2-3f2d-4bbb-b9ab-316c4ca4da27', '488243256161730560', '594691026430459904');
INSERT INTO `t_sys_permission_role` VALUES ('cb8d7e0d-0743-4abf-92a0-c7de9adb4528', '488243256161730560', '617751079701970944');
INSERT INTO `t_sys_permission_role` VALUES ('d1dd3b37-1f13-461e-9c48-e173b52fb391', '488243256161730560', '589559916704825344');
INSERT INTO `t_sys_permission_role` VALUES ('d2d77e40-ff6f-48c7-9fca-d32d0f3b2b20', '488243256161730560', '3400681518049566731');
INSERT INTO `t_sys_permission_role` VALUES ('d7f14f82-1808-4a08-912f-b1727468d121', '488243256161730560', '15');
INSERT INTO `t_sys_permission_role` VALUES ('d8e30b4f-c945-4bdb-8124-6991424313ba', '488243256161730560', '618918631769636864');
INSERT INTO `t_sys_permission_role` VALUES ('da7a7ac2-bcb9-4bb7-b495-c5c976d598c5', '488243256161730560', '22');
INSERT INTO `t_sys_permission_role` VALUES ('db064e7f-1e67-455f-851f-2fe5e589310f', '488243256161730560', '6189186317948026883');
INSERT INTO `t_sys_permission_role` VALUES ('dbc9d35d-8c66-4bcb-8e34-ad3214759713', '488243256161730560', '340067579836108800');
INSERT INTO `t_sys_permission_role` VALUES ('dd5c579d-62fb-4888-87d6-a06d3b346635', '488243256161730560', '19');
INSERT INTO `t_sys_permission_role` VALUES ('e503a7da-d937-4eca-8fe4-6fac4a8a4657', '488243256161730560', '610635485890478080');
INSERT INTO `t_sys_permission_role` VALUES ('e8ce3bc4-db82-416f-9331-33a33a7fcdea', '488243256161730560', '581541547099553792');
INSERT INTO `t_sys_permission_role` VALUES ('e9055eea-92bb-4ebe-a8ad-bb1c0c574e61', '488243256161730560', '340381240911859712');
INSERT INTO `t_sys_permission_role` VALUES ('ea040457-75d8-4da9-b9db-1971f09c7e86', '488243256161730560', '589559748521623552');
INSERT INTO `t_sys_permission_role` VALUES ('eb3954ac-bb7c-496b-a90f-8cd04403b3bd', '488243256161730560', '496782496638173184');
INSERT INTO `t_sys_permission_role` VALUES ('ec5ff15f-9f5a-41f2-9439-76e4a1334b5b', '488243256161730560', '617766548966211584');
INSERT INTO `t_sys_permission_role` VALUES ('eff2223a-6169-4147-a9e7-a6ab89ffa3ff', '488243256161730560', '589559475422101504');
INSERT INTO `t_sys_permission_role` VALUES ('f3c55eb5-5de4-4f33-bce7-a14c47eb9932', '488243256161730560', '586003694080753664');
INSERT INTO `t_sys_permission_role` VALUES ('f551a1da-a4b1-41a1-9231-53478758594b', '488243256161730560', '331778807298134016');
INSERT INTO `t_sys_permission_role` VALUES ('f7a929f3-ab49-40c0-8656-1183ab012003', '488243256161730560', '340301160042860544');
INSERT INTO `t_sys_permission_role` VALUES ('f9baf7cf-2309-4a7a-98ad-9719d2adb2a2', '488243256161730560', '5946910264304599044');
INSERT INTO `t_sys_permission_role` VALUES ('fa5dcc78-de24-4e91-958c-9ddb7ce80526', '488243256161730560', '3401274122705346593');
INSERT INTO `t_sys_permission_role` VALUES ('fa9ed25b-0ece-4500-abfe-1d92e9a2fde2', '488243256161730560', '3321578609202995222');
INSERT INTO `t_sys_permission_role` VALUES ('ff668be6-036f-4e3b-a35b-9de729818538', '488243256161730560', '583063272123531264');
INSERT INTO `t_sys_permission_role` VALUES ('fff1cad2-306b-4b7a-a0e9-c1dbe03421d0', '488243256161730560', '3400681518049566753');

-- ----------------------------
-- Table structure for t_sys_premission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_premission`;
CREATE TABLE `t_sys_premission` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `descripion` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '授权链接',
  `is_blank` int(255) DEFAULT '0' COMMENT '是否跳转 0 不跳转 1跳转',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点id',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `visible` int(255) DEFAULT NULL COMMENT '是否可见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_sys_premission
-- ----------------------------
INSERT INTO `t_sys_premission` VALUES ('1', '首页', '首页', '#', '0', '0', '#', '0', 'fa fa-home', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('10', '角色集合', '角色集合', '/RoleController/list', '0', '9', 'system:role:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('11', '角色添加', '角色添加', '/RoleController/add', '0', '9', 'system:role:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('12', '角色删除', '角色删除', '/RoleController/remove', '0', '9', 'system:role:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('13', '角色修改', '角色修改', '/RoleController/edit', '0', '9', 'system:role:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('14', '权限展示', '权限展示', '/PremissionController/view', '0', '592059865673760768', 'system:premission:view', '1', 'fa fa-key', '3', '0');
INSERT INTO `t_sys_premission` VALUES ('15', '权限集合', '权限集合', '/PremissionController/list', '0', '14', 'system:premission:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('16', '权限添加', '权限添加', '/PremissionController/add', '0', '14', 'system:premission:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('17', '权限删除', '权限删除', '/PremissionController/remove', '0', '14', 'system:premission:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('18', '权限修改', '权限修改', '/PremissionController/edit', '0', '14', 'system:premission:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('19', '文件展示', '文件展示', '/FileController/view', '0', '592059865673760768', 'system:file:view', '1', 'fa fa-file-image-o', '4', '0');
INSERT INTO `t_sys_premission` VALUES ('20', '文件添加', '文件添加', '/FileController/add', '0', '19', 'system:file:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('21', '文件删除', '文件删除', '/FileController/remove', '0', '19', 'system:file:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('22', '文件修改', '文件修改', '/FileController/edit', '0', '19', 'system:file:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('23', '文件集合', '文件集合', '/FileController/list', '0', '19', 'system:file:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('330365026642825216', '公告管理', '公告展示', '/SysNoticeController/view', '0', '592059865673760768', 'gen:sysNotice:view', '1', 'fa fa-telegram', '10', '0');
INSERT INTO `t_sys_premission` VALUES ('3303650266428252171', '公告集合', '公告集合', '/SysNoticeController/list', '0', '330365026642825216', 'gen:sysNotice:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3303650266428252182', '公告添加', '公告添加', '/SysNoticeController/add', '0', '330365026642825216', 'gen:sysNotice:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3303650266428252193', '公告删除', '公告删除', '/SysNoticeController/remove', '0', '330365026642825216', 'gen:sysNotice:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3303650266428252204', '公告修改', '公告修改', '/SysNoticeController/edit', '0', '330365026642825216', 'gen:sysNotice:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('331778807298134016', '定时器表达式', '', '/quartz/view', '0', '592059865673760768', '#', '1', 'fa fa-flash', '12', '0');
INSERT INTO `t_sys_premission` VALUES ('332157860920299520', '定时任务', '定时任务调度表展示', '/SysQuartzJobController/view', '0', '592059865673760768', 'gen:sysQuartzJob:view', '1', 'fa fa-hourglass-1', '13', '0');
INSERT INTO `t_sys_premission` VALUES ('3321578609202995211', '定时任务调度表集合', '定时任务调度表集合', '/SysQuartzJobController/list', '0', '332157860920299520', 'gen:sysQuartzJob:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3321578609202995222', '定时任务调度表添加', '定时任务调度表添加', '/SysQuartzJobController/add', '0', '332157860920299520', 'gen:sysQuartzJob:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3321578609202995233', '定时任务调度表删除', '定时任务调度表删除', '/SysQuartzJobController/remove', '0', '332157860920299520', 'gen:sysQuartzJob:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3321578609202995244', '定时任务调度表修改', '定时任务调度表修改', '/SysQuartzJobController/edit', '0', '332157860920299520', 'gen:sysQuartzJob:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('332857281479839744', '定时任务日志', '定时任务日志', '/SysQuartzJobLogController/view', '0', '592059865673760768', 'gen:sysQuartzJobLog:view', '1', 'fa fa-database', '14', '0');
INSERT INTO `t_sys_premission` VALUES ('3328572814798397451', '定时任务调度日志表集合', '定时任务调度日志表集合', '/SysQuartzJobLogController/list', '0', '332857281479839744', 'gen:sysQuartzJobLog:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3328572814798397473', '定时任务调度日志表删除', '定时任务调度日志表删除', '/SysQuartzJobLogController/remove', '0', '332857281479839744', 'gen:sysQuartzJobLog:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('335330315113467904', 'Json工具', 'Json格式化工具', '/Json/view', '1', '617766548966211584', '#', '1', 'fa fa-retweet', '10', '0');
INSERT INTO `t_sys_premission` VALUES ('340066503263129600', '省份设置', '省份设置', null, '0', '1', null, '0', 'fa fa-building', '4', '0');
INSERT INTO `t_sys_premission` VALUES ('340067579836108800', '省份管理', '', '#', '0', '340066503263129600', '#', '1', 'fa fa-building-o', null, '0');
INSERT INTO `t_sys_premission` VALUES ('340068151804956672', '省份表管理', '省份表展示', '/SysProvinceController/view', '0', '340067579836108800', 'gen:sysProvince:view', '1', 'fa fa-quora', '2', '0');
INSERT INTO `t_sys_premission` VALUES ('3400681518049566731', '省份表集合', '省份表集合', '/SysProvinceController/list', '0', '340068151804956672', 'gen:sysProvince:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400681518049566742', '省份表添加', '省份表添加', '/SysProvinceController/add', '0', '340068151804956672', 'gen:sysProvince:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400681518049566753', '省份表删除', '省份表删除', '/SysProvinceController/remove', '0', '340068151804956672', 'gen:sysProvince:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400681518049566764', '省份表修改', '省份表修改', '/SysProvinceController/edit', '0', '340068151804956672', 'gen:sysProvince:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('340088022018166784', '城市表管理', '城市设置展示', '/SysCityController/view', '0', '340067579836108800', 'gen:sysCity:view', '1', 'fa fa-quora', '3', '0');
INSERT INTO `t_sys_premission` VALUES ('3400880220181667851', '城市设置集合', '城市设置集合', '/SysCityController/list', '0', '340088022018166784', 'gen:sysCity:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400880220181667862', '城市设置添加', '城市设置添加', '/SysCityController/add', '0', '340088022018166784', 'gen:sysCity:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400880220181667873', '城市设置删除', '城市设置删除', '/SysCityController/remove', '0', '340088022018166784', 'gen:sysCity:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400880220181667884', '城市设置修改', '城市设置修改', '/SysCityController/edit', '0', '340088022018166784', 'gen:sysCity:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('340096183135506432', '地区设置管理', '地区设置展示', '/SysAreaController/view', '0', '340067579836108800', 'gen:sysArea:view', '1', 'fa fa-quora', '4', '0');
INSERT INTO `t_sys_premission` VALUES ('3400961831355064331', '地区设置集合', '地区设置集合', '/SysAreaController/list', '0', '340096183135506432', 'gen:sysArea:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400961831355064342', '地区设置添加', '地区设置添加', '/SysAreaController/add', '0', '340096183135506432', 'gen:sysArea:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400961831355064353', '地区设置删除', '地区设置删除', '/SysAreaController/remove', '0', '340096183135506432', 'gen:sysArea:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3400961831355064364', '地区设置修改', '地区设置修改', '/SysAreaController/edit', '0', '340096183135506432', 'gen:sysArea:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('340127412270534656', '街道设置管理', '街道设置展示', '/SysStreetController/view', '0', '340067579836108800', 'gen:sysStreet:view', '1', 'fa fa-quora', '5', '0');
INSERT INTO `t_sys_premission` VALUES ('3401274122705346571', '街道设置集合', '街道设置集合', '/SysStreetController/list', '0', '340127412270534656', 'gen:sysStreet:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3401274122705346582', '街道设置添加', '街道设置添加', '/SysStreetController/add', '0', '340127412270534656', 'gen:sysStreet:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3401274122705346593', '街道设置删除', '街道设置删除', '/SysStreetController/remove', '0', '340127412270534656', 'gen:sysStreet:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('3401274122705346604', '街道设置修改', '街道设置修改', '/SysStreetController/edit', '0', '340127412270534656', 'gen:sysStreet:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('340301160042860544', '省份联动', '省份联动', '/ProvinceLinkageController/view', '0', '340067579836108800', '#', '1', 'fa fa-etsy', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('340381240911859712', 'JavaScript格式化', 'JavaScript格式化', '/static/admin/htmlformat/javascriptFormat.html', '1', '617766548966211584', '#', '1', 'fa fa-magic', '11', '0');
INSERT INTO `t_sys_premission` VALUES ('4', '用户管理', '用户展示', '/UserController/view', '0', '592059865673760768', 'system:user:view', '1', 'icon icon-user', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('486690002869157888', '用户密码修改', '用户密码修改', '/UserController/editPwd', '0', '4', 'system:user:editPwd', '2', 'entypo-tools', '3', '0');
INSERT INTO `t_sys_premission` VALUES ('496126970468237312', '日志展示', '日志管理', '/LogController/view', '0', '592059865673760768', 'system:log:view', '1', 'fa fa-info', '9', '0');
INSERT INTO `t_sys_premission` VALUES ('496127240363311104', '日志删除', '日志删除', '/LogController/remove', '0', '496126970468237312', 'system:log:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('496127794879660032', '日志集合', '日志集合', '/LogController/list', '0', '496126970468237312', 'system:log:list', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('496782496638173184', '系统设置', '后台设置', null, '0', '1', null, '0', 'fa fa-gear', '3', '0');
INSERT INTO `t_sys_premission` VALUES ('5', '用户集合', '用户集合', '/UserController/list', '0', '4', 'system:user:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('575851658483859456', '代码生成旧', '代码生成1', '#', '0', '617751079701970944', '#', '1', 'fa fa-ra', '2', '1');
INSERT INTO `t_sys_premission` VALUES ('575852089792528384', '代码管理', '代码生成', '/generatorController/view', '0', '575851658483859456', 'system:generator:view', '1', 'fa fa-bug', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('575853607149109248', '代码集合', '代码集合', '/generatorController/list', '0', '575852089792528384', 'system:generator:list', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('581541547099553792', 'druid监控', 'druid监控', '/druid/', '0', '617766548966211584', 'user:list', '1', 'fa fa-line-chart', '6', '0');
INSERT INTO `t_sys_premission` VALUES ('583063272123531264', 'API文档', 'API文档', '/swagger-ui.html', '1', '617766548966211584', '--', '1', 'fa fa-font', '8', '0');
INSERT INTO `t_sys_premission` VALUES ('586003694080753664', '表单构建', '表单构建', '/ToolController/view', '0', '617766548966211584', 'system:tool:view', '1', 'fa fa-list-alt', '5', '0');
INSERT INTO `t_sys_premission` VALUES ('587453033487532032', '后台模板', '后台模板', '/static/admin/bootstarp/index.html', '1', '617766548966211584', 'system:htmb:view', '1', 'fa fa-telegram', '9', '0');
INSERT INTO `t_sys_premission` VALUES ('589559475422101504', '测试目录', '测试目录', null, '0', '1', null, '0', 'fa fa-etsy', '5', '0');
INSERT INTO `t_sys_premission` VALUES ('589559748521623552', '一级菜单', '测试菜单', '#', '0', '589559475422101504', '#', '1', 'fa fa-address-book', null, '0');
INSERT INTO `t_sys_premission` VALUES ('589559916704825344', '二级菜单', '二级菜单', '#', '0', '589559748521623552', '#', '1', 'fa fa-address-book', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('592059865673760768', '系统管理', '后台管理', '#', '0', '496782496638173184', '#', '1', 'fa fa-home', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('592067570522128384', '测试跳转', '测试跳转', 'http://www.baidu.com', '1', '589559748521623552', '#', '1', 'fa fa-address-book', null, '0');
INSERT INTO `t_sys_premission` VALUES ('592167738407911424', '系统监控', '系统监控', '/ServiceController/view', '0', '617766548966211584', 'system:service:view', '1', 'fa fa-video-camera', '7', '0');
INSERT INTO `t_sys_premission` VALUES ('594691026430459904', '电子邮件管理', '电子邮件展示', '/EmailController/view', '0', '592059865673760768', 'system:email:view', '1', 'fa fa-envelope', '8', '0');
INSERT INTO `t_sys_premission` VALUES ('5946910264304599041', '电子邮件集合', '电子邮件集合', '/EmailController/list', '0', '594691026430459904', 'system:email:list', '2', '', null, '0');
INSERT INTO `t_sys_premission` VALUES ('5946910264304599042', '电子邮件添加', '电子邮件添加', '/EmailController/add', '0', '594691026430459904', 'system:email:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('5946910264304599043', '电子邮件删除', '电子邮件删除', '/EmailController/remove', '0', '594691026430459904', 'system:email:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('5946910264304599044', '电子邮件修改', '电子邮件修改', '/EmailController/edit', '0', '594691026430459904', 'system:email:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('6', '用户添加', '用户添加', '/UserController/add', '0', '4', 'system:user:add', '2', 'entypo-plus-squared', null, '0');
INSERT INTO `t_sys_premission` VALUES ('610635485890478080', '代码生成新', '代码生成2', '#', '0', '617751079701970944', '#', '1', 'fa fa-blind', '1', '0');
INSERT INTO `t_sys_premission` VALUES ('610635950447394816', '全局配置', '', '/autoCodeController/global', '0', '610635485890478080', 'system:autocode:global', '1', 'fa fa-university', null, '0');
INSERT INTO `t_sys_premission` VALUES ('610983815791247360', '单表生成', '', '/autoCodeController/one', '0', '610635485890478080', 'system:autocode:one', '1', 'fa fa-hand-peace-o', null, '0');
INSERT INTO `t_sys_premission` VALUES ('617751079701970944', '代码生成', '代码生成', null, '0', '1', null, '0', 'fa fa-500px', '4', '0');
INSERT INTO `t_sys_premission` VALUES ('617766548966211584', '系统工具', '系统工具', '#', '0', '496782496638173184', '#', '1', 'fa fa-th-large', '2', '0');
INSERT INTO `t_sys_premission` VALUES ('618918631769636864', '字典管理', '字典类型表展示', '/DictTypeController/view', '0', '592059865673760768', 'system:dictType:view', '1', 'fa fa-puzzle-piece', '11', '0');
INSERT INTO `t_sys_premission` VALUES ('6189186317738311681', '字典类型表集合', '字典类型表集合', '/DictTypeController/list', '0', '618918631769636864', 'system:dictType:list', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6189186317948026882', '字典类型表添加', '字典类型表添加', '/DictTypeController/add', '0', '618918631769636864', 'system:dictType:add', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6189186317948026883', '字典类型表删除', '字典类型表删除', '/DictTypeController/remove', '0', '618918631769636864', 'system:dictType:remove', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6189186317989969924', '字典类型表修改', '字典类型表修改', '/DictTypeController/edit', '0', '618918631769636864', 'system:dictType:edit', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6192095214866268161', '字典数据表集合', '字典数据表集合', '/DictDataController/list', '0', '618918631769636864', 'system:dictData:list', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6192095214866268162', '字典数据表添加', '字典数据表添加', '/DictDataController/add', '0', '618918631769636864', 'system:dictData:add', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6192095215075983363', '字典数据表删除', '字典数据表删除', '/DictDataController/remove', '0', '618918631769636864', 'system:dictData:remove', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('6192095215075983364', '字典数据表修改', '字典数据表修改', '/DictDataController/edit', '0', '618918631769636864', 'system:dictData:edit', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('619836559427895296', '字典数据视图', '字典数据视图', '/DictDataController/view', '0', '618918631769636864', 'system:dictData:view', '2', null, null, '0');
INSERT INTO `t_sys_premission` VALUES ('7', '用户删除', '用户删除', '/UserController/remove', '0', '4', 'system:user:remove', '2', 'entypo-trash', null, '0');
INSERT INTO `t_sys_premission` VALUES ('8', '用户修改', '用户修改', '/UserController/edit', '0', '4', 'system:user:edit', '2', 'fa fa-wrench', null, '0');
INSERT INTO `t_sys_premission` VALUES ('9', '角色管理', '角色展示', '/RoleController/view', '0', '592059865673760768', 'system:role:view', '1', 'fa fa-group', '2', '0');

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
-- ----------------------------
-- Table structure for t_com_corporation
-- ----------------------------
DROP TABLE IF EXISTS `com_corporation`;
CREATE TABLE `com_corporation` (
  `uid` varchar(35) NOT NULL  COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '社团名称',
  `description` varchar(100) DEFAULT NULL COMMENT '社团描述',
  `slogan` varchar(50) DEFAULT NULL COMMENT '社团口号',
  `logo` varchar(50) DEFAULT NULL COMMENT '社团logo',
  `label` varchar(50) DEFAULT NULL COMMENT '标签',
  `member_No` int DEFAULT NULL COMMENT '成员人数',
  `institution` varchar(30) DEFAULT NULL COMMENT '所属机构',
  `star` int DEFAULT 0 COMMENT '级别',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `like` int DEFAULT 0 COMMENT '点赞数',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `Is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='社团';
-- ----------------------------
-- Records of t_test
-- ----------------------------
DROP TABLE IF EXISTS `com_activity`;
CREATE TABLE `com_activity` (
  `uid` varchar(35) NOT NULL  COMMENT '主键',
  `subject` varchar(50) NOT NULL COMMENT '主题',
  `pic` varchar(30) DEFAULT NULL COMMENT '图片url',
  `content_html` text DEFAULT NULL COMMENT '内容html',
  `like` int DEFAULT 0 COMMENT '点赞数',
  `corpor_id` varchar(35) DEFAULT NULL COMMENT '社团id',
  `organ` varchar(35) DEFAULT NULL COMMENT '组织方',
  `finish` int DEFAULT 0 COMMENT '是否完成',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `Is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='活动';

DROP TABLE IF EXISTS `com_discuss`;
CREATE TABLE `com_discuss` (
  `id` int  NOT NULL AUTO_INCREMENT COMMENT '主键自增列',
  `content` varchar(300) DEFAULT NULL COMMENT '帖子内容',
  `pic` varchar(30) DEFAULT NULL COMMENT '帖子图片',
  `location` varchar(20) DEFAULT NULL COMMENT '帖子定位',
  `like` int DEFAULT 0 COMMENT '点赞数',
  `concern_no` int DEFAULT 0 COMMENT '关注数',
  `creator` varchar(35) DEFAULT NULL COMMENT '发帖人',
  `creator_sex` int DEFAULT 0 COMMENT '性别',
  `creator_avat` varchar(30) DEFAULT NULL  COMMENT '头像',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `Is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='帖子';

DROP TABLE IF EXISTS `com_user_corp`;
CREATE TABLE `com_user_corp` (
  `id` int  NOT NULL AUTO_INCREMENT COMMENT '主键自增列',
  `u_uid` varchar(30) NOT NULL COMMENT '用户id',
  `c_uid` varchar(30) NOT NULL COMMENT '社团id',
  `state` int DEFAULT 0 COMMENT '状态com_user_corp',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `Is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='用户社团'

DROP TABLE IF EXISTS `com_comment`;
CREATE TABLE `com_comment` (
  `id` int  NOT NULL AUTO_INCREMENT COMMENT '主键自增列',
  `content` varchar(300) DEFAULT NULL COMMENT '评论内容',
  `root_d` varchar(35) NOT NULL COMMENT '根节点',
  `parent_id` varchar(35) DEFAULT NULL COMMENT '父节点',
  `creator` varchar(30) DEFAULT NULL COMMENT '评论人',
  `creator_sex` int DEFAULT NULL COMMENT '性别',
  `creator_avat` varchar(30) DEFAULT NULL  COMMENT '头像',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='评论'

DROP TABLE IF EXISTS `com_notice_user`;
CREATE TABLE `com_notice_user` (
  `id` int  NOT NULL AUTO_INCREMENT COMMENT '主键自增列',
  `notice_id` varchar(300) DEFAULT NULL COMMENT '公告id',
  `u_uid` varchar(35) DEFAULT NULL COMMENT '用户id',
  `state` int DEFAULT 0 COMMENT '阅读状态',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3679 DEFAULT CHARSET=utf8 COMMENT='公告用户表'

DROP TABLE IF EXISTS `com_user_discuss`;
CREATE TABLE `com_user_discuss` (
  `id` int NOT NULL,
  `u_uid` varchar(35) DEFAULT NULL COMMENT '角色id',
  `d_id` int DEFAULT NULL COMMENT '帖子id',
  `state` int DEFAULT 1 COMMENT '关注状态com_user_discuss',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色帖子中间表';

DROP TABLE IF EXISTS `t_deb_topic`;
CREATE TABLE `t_deb_topic` (
  `uid` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `subject` varchar(34) DEFAULT NULL COMMENT '专栏名',
  `subject_id` varchar(34) DEFAULT NULL COMMENT '专栏id',
  `title` varchar(100) DEFAULT NULL COMMENT '立题',
  `content` varchar(300) DEFAULT NULL COMMENT '内容',
  `label1` varchar(30)  DEFAULT NULL COMMENT '标签1',
  `label2` varchar(30)  DEFAULT NULL COMMENT '标签2',
  `label3` varchar(30)  DEFAULT NULL COMMENT '标签3',
  `thinking` int DEFAULT 0 COMMENT '思维度',
  `flexibility` int  DEFAULT 0 COMMENT '灵活性',
  `logicality` int  DEFAULT 0 COMMENT '逻辑性',
  `totalDiff` int  DEFAULT 0 COMMENT '总难度',
  `keyword` varchar(60)  DEFAULT NULL COMMENT '关键字',
  `views` int  DEFAULT 0 COMMENT '浏览量',
  `answers` int  DEFAULT 0 COMMENT '回答数',
  `belong_nm` varchar(50)  DEFAULT null COMMENT '收录书架',
  `belong_id` varchar(50)  DEFAULT null COMMENT '书架Id',
  `min_like` int  DEFAULT 0 COMMENT '最低喜欢数',
  `min_recom` int  DEFAULT 0 COMMENT '最低推荐数',
  `finish` int  DEFAULT 0 COMMENT '已完成',
  `period` int  DEFAULT 0 COMMENT '期数',
  `year` char(4)  DEFAULT '2000' COMMENT '年度',
  `status` int  DEFAULT -1 COMMENT '状态',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',

  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标题信息表';

DROP TABLE IF EXISTS `t_deb_answer`;
CREATE TABLE `t_deb_answer` (
  `no` varchar(40) NOT NULL COMMENT '主键',
  `content` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '立论',
  `conclusion` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '结论',
  `like` int DEFAULT 0 COMMENT '喜欢数',
  `recom_no` int  DEFAULT 0 COMMENT '推荐数',
  `topic_id` varchar(30)  DEFAULT null COMMENT '话题Id',
  `grade` int  DEFAULT 0 COMMENT '立论分值',
  `favorer` text  DEFAULT 0 COMMENT '点赞',
  `creator` varchar(30)  DEFAULT NULL COMMENT '创建人',
  `creator_avat` varchar(30)  DEFAULT null COMMENT '创建人头像',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',

  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='回答表';

DROP TABLE IF EXISTS `t_deb_recom`;
CREATE TABLE `t_deb_recom` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '评论',
  `likes` int CHARACTER SET utf8 DEFAULT NULL COMMENT '点赞',
  `ans_id` varchar(40) DEFAULT -1 COMMENT '回答节点',
  `creator` varchar(30)  DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='推荐表';




DROP TABLE IF EXISTS `t_deb_user_topic`;
CREATE TABLE `t_deb_user_topic` (
  `id` bigint auto_increment NOT NULL COMMENT 'id',
  `topic_id` varchar(40) DEFAULT NULL COMMENT '立论id',
  `ans_id` varchar(40) DEFAULT NULL COMMENT '回答id',
  `rank` int  DEFAULT 0 COMMENT '名次',
  `status` int  DEFAULT 0 COMMENT '引用状态',
  `refer_book` varchar(30)  DEFAULT null COMMENT '应用书籍',
  `book_type` varchar(30)  DEFAULT null COMMENT '书籍类型',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人',
  `create_dat` datetime DEFAULT NULL COMMENT '创建时间',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色立论中间表';

DROP TABLE IF EXISTS `t_per_suggest`;
CREATE TABLE `t_per_suggest` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contont` varchar(300) DEFAULT NULL COMMENT '内容',
  `username` varchar(30) DEFAULT NULL  COMMENT '用户名',
  `adopt` int DEFAULT 0 COMMENT '采纳',
  `create_by` varchar(35) DEFAULT NULL COMMENT '创建人Id',
  `create_dat` date DEFAULT NULL COMMENT '创建日期',
/*  `create_tim` time DEFAULT NULL COMMENT '创建时间',*/
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户意见表';

DROP TABLE IF EXISTS `t_per_recombook`;
CREATE TABLE `t_per_recombook` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bookname` varchar(30) DEFAULT NULL COMMENT '书名',
  `label` varchar(30) DEFAULT NULL  COMMENT '标签',
  `reason` varchar(100) DEFAULT NULL COMMENT '推荐理由',
  `source` int DEFAULT 0 COMMENT '来源',
  `root_id` varchar(40) DEFAULT 0 COMMENT '根id',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_dat` date DEFAULT NULL COMMENT '创建日期',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐书名表';

DROP TABLE IF EXISTS `t_per_saying`;
CREATE TABLE `t_per_saying` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(100) DEFAULT NULL COMMENT '名言内容',
  `author` varchar(10) DEFAULT NULL  COMMENT '、作者',
  `product` varchar(30) DEFAULT NULL COMMENT '作品',
  `source` int DEFAULT 0 COMMENT '来源',
  `root_id` varchar(40) DEFAULT 0 COMMENT '根id',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_dat` date DEFAULT NULL COMMENT '创建日期',
  `l_update_dat` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` int DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='名言表';
-- 触发器，每插入一条推荐表记录，增加回答表的推荐数
DELIMITER |
create trigger upRecomNo
after insert on t_deb_recom
for each row
begin
update t_deb_answer set recom_no =recom_no+1 where id=new.parent_id;
end
|