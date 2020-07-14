/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50560
Source Host           : 39.97.167.227:3306
Source Database       : hr

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-04-01 11:40:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for achives
-- ----------------------------
DROP TABLE IF EXISTS `achives`;
CREATE TABLE `achives` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '档案表编号',
  `emp_name` varchar(20) DEFAULT NULL,
  `emp_sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `emp_address` varchar(50) DEFAULT NULL COMMENT '地址',
  `emp_birth` datetime DEFAULT NULL,
  `emp_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `entry_time` datetime DEFAULT NULL COMMENT '入职时间',
  `emp_titles` varchar(20) DEFAULT NULL COMMENT '职称',
  `mobile` varchar(12) DEFAULT NULL COMMENT '手机号',
  `graduation_time` datetime DEFAULT NULL COMMENT '毕业时间',
  `graduation_shcool` varchar(20) DEFAULT NULL COMMENT '毕业院校',
  `education` int(11) DEFAULT NULL COMMENT '学历（1专科2本科3硕士4博士）',
  `emp_depart` int(11) DEFAULT NULL COMMENT '部门编号',
  `emp_status` int(11) DEFAULT '1' COMMENT '状态（1在职，2离职 ）', 
  `ext_a` varchar(200) DEFAULT NULL,
  `ext_b` varchar(200) DEFAULT NULL,
  `ext_c` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='档案表';

-- ----------------------------
-- Records of achives
-- ----------------------------
INSERT INTO `achives` VALUES ('1', '林俊杰', '男', '四川省成都市', '1992-06-23 12:12:04', '6200141463140huadi', '2020-06-20 12:12:04', '正处级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '1', '1', '', null, null);
INSERT INTO `achives` VALUES ('2', '荆赧青', '男', '四川省成都市', '1992-06-23 21:15:06', '6200141463018huadi', '2020-06-20 21:15:06', '副处级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '2', '1', null, null, null);
INSERT INTO `achives` VALUES ('3', '李杨柯', '男', '四川省成都市', '1992-06-23 20:35:06', '6200141463137huadi', '2020-06-20 20:35:06', '副处级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '1', '1', null, null, null);
INSERT INTO `achives` VALUES ('4', '张震', '男', '四川省成都市', '1992-06-23 20:22:01', '6200141463058huadi', '2020-06-20 20:22:01', '正科级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '2', '1', null, null, null);
INSERT INTO `achives` VALUES ('5', '赵新言', '男', '四川省成都市', '1992-06-23 10:35:05', '6200141463039huadi', '2020-06-20 10:35:05', '正科级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '1', '1', null, null, null);
INSERT INTO `achives` VALUES ('6', '朱凯', '男', '四川省成都市', '1992-06-23 20:13:03', '6200141463147huadi', '2020-06-20 20:13:03', '副科级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '2', '1', null, null, null);
INSERT INTO `achives` VALUES ('7', '赵知朔', '男', '四川省成都市', '1992-06-23 21:12:55', '6200141463106huadi', '2020-06-20 21:12:55', '副科级', '18888888888', '2021-06-03 09:00:00', '四川大学', '2', '5', '1', null, null, null);

-- ----------------------------
-- Table structure for asessment
-- ----------------------------
DROP TABLE IF EXISTS `asessment`;
CREATE TABLE `asessment` (
  `asessment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考勤编号',
  `asessment_date` datetime DEFAULT NULL COMMENT '考勤时间',
  `emp_id` int(11) DEFAULT NULL COMMENT '员工编号',
  `asessment_type` int(11) DEFAULT NULL COMMENT '1迟到 2早退 3旷工',
  `deduction_salary` double DEFAULT NULL COMMENT '扣除薪资',
  `asessment_status` int(11) DEFAULT '1' COMMENT '状态 1正常 2删除',
  `ext_a` varchar(200) DEFAULT NULL,
  `ext_b` varchar(200) DEFAULT NULL,
  `ext_c` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`asessment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='考勤表';

-- ----------------------------
-- Records of asessment
-- ----------------------------
INSERT INTO `asessment` VALUES ('1', '2020-06-22 17:23:48', '1', '3', '20', '1', null, null, null);
INSERT INTO `asessment` VALUES ('2', '2020-06-22 13:22:22', '2', '3', '20', '1', null, null, null);
INSERT INTO `asessment` VALUES ('3', '2020-06-22 14:33:21', '3', '3', '20', '1', null, null, null);
INSERT INTO `asessment` VALUES ('4', '2020-06-22 17:22:33', '4', '3', '20', '1', null, null, null);
INSERT INTO `asessment` VALUES ('5', '2020-06-22 11:22:22', '5', '3', '20', '1', null, null, null);
INSERT INTO `asessment` VALUES ('6', '2020-06-22 12:12:32', '6', '3', '20', '1', null, null, null);
INSERT INTO `asessment` VALUES ('7', '2020-06-22 12:12:32', '7', '3', '20', '1', null, null, null);

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart` (
  `depart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `depart_name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `depart_status` int(11) DEFAULT '1' COMMENT '状态（1正常）',
  `create_time` datetime DEFAULT NULL COMMENT '成立时间',
  `ext_a` varchar(200) DEFAULT NULL,
  `ext_b` varchar(200) DEFAULT NULL,
  `ext_c` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of depart
-- ----------------------------
INSERT INTO `depart` VALUES ('1', '教务处', '1', '2020-06-15 00:00:00', null, null, null);
INSERT INTO `depart` VALUES ('2', '后勤保障中心', '1', '2020-06-15 00:00:00', '', '', '');
INSERT INTO `depart` VALUES ('3', '教师发展处', '1', '2020-06-15 00:00:00', '', '', '');
INSERT INTO `depart` VALUES ('4', '校团委', '1', '2020-06-15 00:00:00', '', '', '');
INSERT INTO `depart` VALUES ('5', '校工会', '1', '2020-06-15 00:00:00', '', '', '');

-- ----------------------------
-- Table structure for recruit
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `recruit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招聘编号',
  `person_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `person_sex` int(11) DEFAULT NULL COMMENT '性别（1男 2 女）',
  `recruit_date` datetime DEFAULT NULL COMMENT '面试时间',
  `graduation_school` varchar(20) DEFAULT NULL COMMENT '毕业院校',
  `graduation_date` date DEFAULT NULL COMMENT '毕业时间',
  `education` int(11) DEFAULT NULL COMMENT '学历（1专科2本科3硕士4博士）',
  `recruit_result` int(11) DEFAULT NULL COMMENT '1未处理 2已通过 3未通过',
  `recruit_status` int(11) DEFAULT NULL COMMENT '状态 1正常 2删除',
  `ext_a` varchar(200) DEFAULT NULL,
  `ext_b` varchar(200) DEFAULT NULL,
  `ext_c` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`recruit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='招聘信息表（面试登记表）';

-- ----------------------------
-- Records of recruit
-- ----------------------------
INSERT INTO `recruit` VALUES ('1', '包晓乐', '1', '2020-03-30 15:36:28', '四川华迪', '2020-06-24', '2', '1', '1', '', '', '');
INSERT INTO `recruit` VALUES ('2', '包大', '1', '2045-07-01 09:00:00', '清华大学', '2045-06-03', '4', '2', '1', null, null, null);
INSERT INTO `recruit` VALUES ('3', '包二', '1', '2045-07-01 09:00:00', '北京大学', '2045-06-03', '4', '2', '1', '', '', '');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `salary_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '薪资单编号',
  `emp_id` int(11) DEFAULT NULL COMMENT '员工编号',
  `base_salary` double DEFAULT NULL COMMENT '基本工资',
  `achievements` double DEFAULT NULL COMMENT '绩效',
  `bonus` double DEFAULT NULL COMMENT '项目奖金',
  `check_deduction` double DEFAULT NULL COMMENT '考勤扣除',
  `sum_salary` double DEFAULT NULL COMMENT '实发工资',
  `salary_month` date DEFAULT NULL COMMENT '薪资月份',
  `salary_status` int(11) DEFAULT NULL COMMENT '薪资状态(1未发放2已发放)',
  `ext_a` varchar(200) DEFAULT NULL,
  `ext_b` varchar(200) DEFAULT NULL,
  `ext_c` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`salary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('1', '1', '10000', '200', '500', '200', '10500', '2020-06-25', '1', null, null, null);
INSERT INTO `salary` VALUES ('2', '2', '10000', '600', '500', '200', '10900', '2020-06-25', '1', null, null, null);
INSERT INTO `salary` VALUES ('3', '3', '10000', '300', '500', '200', '10600', '2020-06-25', '2', null, null, null);
INSERT INTO `salary` VALUES ('4', '4', '10000', '400', '500', '200', '10700', '2020-06-25', '2', null, null, null);
INSERT INTO `salary` VALUES ('5', '5', '10000', '300', '500', '200', '10600', '2020-06-25', '1', null, null, null);
INSERT INTO `salary` VALUES ('6', '6', '10000', '500', '500', '200', '10800', '2020-06-24', '2', '', '', '');
INSERT INTO `salary` VALUES ('7', '7', '10000', '200', '500', '200', '10500', '2020-06-24', '2', '', '', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  `role` int(11) DEFAULT NULL COMMENT '1超管 2 普通管理员',
  `create_time` datetime DEFAULT NULL,
  `user_status` int(11) DEFAULT '1' COMMENT '1正常 2 锁定 3删除',
  `ext_a` varchar(200) DEFAULT NULL,
  `ext_b` varchar(200) DEFAULT NULL,
  `ext_c` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Superadmin', 'Superadmin', '1', '2020-06-14 23:04:13', '1', null, null, null);
INSERT INTO `user` VALUES ('2', 'Linjunjie', 'Linjunjie', '2', '2020-06-14 23:04:01', '2', null, null, null);
INSERT INTO `user` VALUES ('3', 'Jingnanqing', 'Jingnanqing', '2', '2020-06-14 11:23:33', '1', null, null, null);
INSERT INTO `user` VALUES ('4', 'Liyangke', 'Liyangke', '2', '2020-06-14 11:23:33', '1', null, null, null);
INSERT INTO `user` VALUES ('5', 'Zhangzhen', 'Zhangzhen', '2', '2020-06-14 11:23:33', '1', null, null, null);
INSERT INTO `user` VALUES ('6', 'Zhaoxingyan', 'Zhaoxingyan', '2', '2020-06-14 11:23:33', '1', null, null, null);
INSERT INTO `user` VALUES ('7', 'Zhukai', 'Zhukai', '2', '2020-06-14 11:23:33', '1', null, null, null);
INSERT INTO `user` VALUES ('8', 'Zhaozhishuo', 'Zhaozhishuo', '2', '2020-06-14 11:23:33', '1', null, null, null);

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
create table document
(
    document_id   int auto_increment comment '文档编号'
        primary key,
    document_name varchar(60) null comment '文档名称',
    create_time   datetime    null comment '创建时间'
)
    comment '文档表' charset = utf8;


