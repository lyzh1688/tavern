/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : alleria

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-08-06 16:54:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alr_bank_statement_dtl_ccbl_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_bank_statement_dtl_ccbl_record`;
CREATE TABLE `alr_bank_statement_dtl_ccbl_record` (
  `DETAIL_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CUSTOM_ACCNT_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CUSTOM_ACCNT_NAME` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TRADE_DATE` date DEFAULT NULL,
  `DEBIT_AMOUNT` decimal(20,6) DEFAULT NULL COMMENT '借方发生额',
  `CREDIT_AMOUNT` decimal(20,6) DEFAULT NULL COMMENT '贷方发生额',
  `BALANCE` decimal(20,6) DEFAULT NULL COMMENT '余额',
  `COIN_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'CNY' COMMENT 'CNY,USD',
  `RECV_ACCNT_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对方账户',
  `RECV_ACCNT_NAME` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对方户名',
  `INSTITUTION` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开户机构',
  `ACCOUNTING_DATE` date DEFAULT NULL COMMENT '记账日期',
  `COMPANY_SEQ` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业流水号',
  `CERTIFICATE_TYPE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '凭证种类',
  `CERTIFICATE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '凭证ID',
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ABSTRACTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`DETAIL_ID`),
  KEY `IDX_BANK_DTL_CCBL` (`CUSTOM_ACCNT_ID`,`TRADE_DATE`,`FILE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='建设银行流水明细表';

-- ----------------------------
-- Records of alr_bank_statement_dtl_ccbl_record
-- ----------------------------
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1797-3106136400N5PFSOSI5', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-01', '0.000000', '500.000000', '105145.510000', '人民币元', '艺域商务咨询（上海）有限公司', '31050161364000004065', '建行上海浦东分行', '2019-05-01', null, '电子转账凭证', '102322869612', '3106136400N5PFSOSI5', '网络转账', '服务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1798-31028000016IPEHWZZV', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-05', '0.000000', '12000.000000', '117145.510000', '人民币元', '上海宝原科技有限公司', '00000455972870509', '中国银行上海市分行营业部', '2019-05-05', null, '    ', null, '31028000016IPEHWZZV', '自定义', '宝原企畅代理记账费用2019.04-2020.03/上海');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1799-3102800000OMPP6LQ4A', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-06', '20000.000000', '0.000000', '97145.510000', '人民币元', '支付宝（中国）网络技术有限公司', '215500690', '建行杭州西湖支行营业部', '2019-05-06', null, '    ', null, '3102800000OMPP6LQ4A', '消费', '网上购物2019050607665299720224320202402-特约商户-');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1800-31075364326GPWTR09E', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-07', '5.000000', '0.000000', '97140.510000', '人民币元', null, null, null, '2019-05-07', null, '    ', null, '31075364326GPWTR09E', '收费', '收费项目:对公一户通-主账户账户管理费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1801-3107536430N0PV5B469', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-07', '0.000000', '2900.000000', '100040.510000', '人民币元', '上海慕客信教育科技有限公司', '31050175364300000028', '建行上海国定路支行', '2019-05-07', null, '电子转账凭证', '102329166024', '3107536430N0PV5B469', '网络转账', '社会保险费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1802-3106136400NRPID1BGO', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-07', '0.000000', '500.000000', '100540.510000', '人民币元', '上海乐般教育科技有限公司', '31050161364000003342', '建行上海浦东分行', '2019-05-07', null, '电子转账凭证', '102329406542', '3106136400NRPID1BGO', '网络转账', '汇算清缴办公费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1803-3107037000NEP6T4TXA', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-07', '0.000000', '300.000000', '100840.510000', '人民币元', '上海伊柏电子科技有限责任公司', '31050170370000001044', '建行上海外滩支行', '2019-05-07', null, '电子转账凭证', '102329577365', '3107037000NEP6T4TXA', '网络转账', '咨询费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1804-3102800000OTP842YSM', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-08', '45000.000000', '0.000000', '55840.510000', '人民币元', '支付宝（中国）网络技术有限公司', '215500690', '建行杭州西湖支行营业部', '2019-05-08', null, '    ', null, '3102800000OTP842YSM', '消费', '网上购物2019050807721544200224330112608-特约商户-');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1805-3107037000N5PG5QOEB', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-10', '0.000000', '300.000000', '56140.510000', '人民币元', '上海安素文化传播有限公司', '31050170370000001019', '建行上海外滩支行', '2019-05-10', null, '电子转账凭证', '102333925326', '3107037000N5PG5QOEB', '网络转账', '服务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1806-3107536430NCPCFNWNU', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-10', '30000.000000', '0.000000', '26140.510000', '人民币元', '镇江嘉元财务咨询有限公司', '3211020271010000005907', '江苏镇江农村商业银行股份有限公司丁卯支行', '2019-05-10', null, '电子转账凭证', '102334536604', '3107536430NCPCFNWNU', '网络转账', '镇江绩效补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1807-3107536430NNPDCN876', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-10', '15000.000000', '0.000000', '11140.510000', '人民币元', '镇江嘉元财务咨询有限公司', '3211020271010000005907', '江苏镇江农村商业银行股份有限公司丁卯支行', '2019-05-10', null, '电子转账凭证', '102334534459', '3107536430NNPDCN876', '网络转账', '镇江2季度房租补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1808-3102800000N5P1X6NER', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-11', '0.000000', '300.000000', '11440.510000', '人民币元', '上海践道建筑科技有限公司', '1001359409024849266', '中国工商银行上海市分行业务营运中心', '2019-05-11', null, '电汇凭证', null, '3102800000N5P1X6NER', '电子汇入', '电子汇入');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1809-3102800000NKPM9IMGS', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-13', '0.000000', '300.000000', '11740.510000', '人民币元', '上海卢新贸易有限公司', '121918992910901', '招商银行股份有限公司上海新客站支行', '2019-05-13', null, '电汇凭证', null, '3102800000NKPM9IMGS', '自定义', '代理汇算清缴费用');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1810-3107536430NBPH7JIM3', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-14', '10000.000000', '0.000000', '1740.510000', '人民币元', '镇江嘉元财务咨询有限公司', '3211020271010000005907', '江苏镇江农村商业银行股份有限公司丁卯支行', '2019-05-14', null, '电子转账凭证', '102340392143', '3107536430NBPH7JIM3', '网络转账', '镇江4月工资补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1811-3106136990N5PIA4TDZ', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-14', '0.000000', '300.000000', '2040.510000', '人民币元', '上海保锐环保科技有限公司', '31050161369900000417', '建行上海金茂支行', '2019-05-14', null, '电子转账凭证', '102340551417', '3106136990N5PIA4TDZ', '网络转账', '劳务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1812-3106136990NDPS03SRE', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-14', '0.000000', '3300.000000', '5340.510000', '人民币元', '上海保锐环保科技有限公司', '31050161369900000417', '建行上海金茂支行', '2019-05-14', null, '电子转账凭证', '102340584758', '3106136990NDPS03SRE', '网络转账', '劳务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1813-3102800000NNPS6X54C', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-15', '0.000000', '500.000000', '5840.510000', '人民币元', '上海飞贝通信技术有限公司', '10551000000571685', '华夏银行股份有限公司总行', '2019-05-15', null, '电汇凭证', null, '3102800000NNPS6X54C', '自定义', '飞贝汇算清缴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1814-3107041000NAPJNUFHK', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-17', '0.000000', '2400.000000', '8240.510000', '人民币元', '上海泽荣节能科技有限公司', '31050170410000000582', '建行上海西藏路支行', '2019-05-17', null, '电子转账凭证', '102345420298', '3107041000NAPJNUFHK', '网络转账', '服务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1815-3106741000NOPSJJM7N', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-17', '0.000000', '1500.000000', '9740.510000', '人民币元', '上海野石管理咨询有限公司', '31001664204052505526', '建行上海延长路支行', '2019-05-17', null, '电子转账凭证', '102346095821', '3106741000NOPSJJM7N', '网络转账', '会计年度审计费用');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1816-31028000016XP3SYBK7', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-17', '0.000000', '1300.000000', '11040.510000', '人民币元', '众襄（上海）创业孵化器管理有限公司', '03002910868', '上海银行股份有限公司', '2019-05-17', null, '    ', null, '31028000016XP3SYBK7', '自定义', '服务费/汇算清缴服务费/众襄（上海）创业孵');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1817-3102800000NFP5N7W7I', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-17', '0.000000', '50000.000000', '61040.510000', '人民币元', '广东信智投资有限公司', '44226201040006167', '中国农业银行股份有限公司惠州分行运营管理部', '2019-05-17', null, '电汇凭证', null, '3102800000NFP5N7W7I', '自定义', '往来款');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1818-3102800000NEPP3VIAI', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-17', '0.000000', '50000.000000', '111040.510000', '人民币元', '广东信智投资有限公司', '44226201040006167', '中国农业银行股份有限公司惠州分行运营管理部', '2019-05-17', null, '电汇凭证', null, '3102800000NEPP3VIAI', '自定义', '往来款');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1819-3102800000OCP7UNHL6', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-20', '20000.000000', '0.000000', '91040.510000', '人民币元', '支付宝（中国）网络技术有限公司', '215500690', '建行杭州西湖支行营业部', '2019-05-20', null, '    ', null, '3102800000OCP7UNHL6', '消费', '网上购物2019052008014873560224330301801-特约商户-');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1820-31075364316UPCE32WC', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-20', '161.530000', '0.000000', '90878.980000', '人民币元', '待报解预算收入（财库联网集中户）', '310280000156241035000000011', '建行上海分行运行中心', '2019-05-20', null, '电子缴税凭证', '331016190500772808', '31075364316UPCE32WC', '缴税', '个人所得税1.54|个人所得税96.24|个人所得税63.75');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1821-3102800000NAP3DU0BU', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-20', '0.000000', '800.000000', '91678.980000', '人民币元', '镇江紫金隆百货商贸有限公司', '1104010319000286916', '中国工商银行总行清算中心', '2019-05-20', null, '电汇凭证', null, '3102800000NAP3DU0BU', '电子汇入', '电子汇入');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1822-3102800000MZPJ132A2', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-20', '0.000000', '800.000000', '92478.980000', '人民币元', '镇江市宇鑫制衣有限公司', '00017854175012', '江苏银行股份有限公司', '2019-05-20', null, '电汇凭证', null, '3102800000MZPJ132A2', '自定义', '代账费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1823-3102800000NIPQJU27W', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-21', '0.000000', '50000.000000', '142478.980000', '人民币元', '广东信智投资有限公司', '44226201040006167', '中国农业银行股份有限公司惠州分行运营管理部', '2019-05-21', null, '电汇凭证', null, '3102800000NIPQJU27W', '自定义', '往来款');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1824-3102800000NJP737KBK', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-21', '0.000000', '50000.000000', '192478.980000', '人民币元', '广东信智投资有限公司', '44226201040006167', '中国农业银行股份有限公司惠州分行运营管理部', '2019-05-21', null, '电汇凭证', null, '3102800000NJP737KBK', '自定义', '往来款');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1825-3106337000NAPXKKVAD', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-21', '0.000000', '300.000000', '192778.980000', '人民币元', '上海希越企业管理咨询有限公司', '31050163370000000365', '建行上海瞿溪路支行', '2019-05-21', null, '电子转账凭证', '102352543935', '3106337000NAPXKKVAD', '网络转账', '服务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1826-3106741000NNPTRL1T1', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-22', '0.000000', '50.000000', '192828.980000', '人民币元', '上海野石管理咨询有限公司', '31001664204052505526', '建行上海延长路支行', '2019-05-22', null, '电子转账凭证', '102353283051', '3106741000NNPTRL1T1', '网络转账', '5月开增值税发票费用');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1827-3107536430N6PVSWL02', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-23', '2063.000000', '0.000000', '190765.980000', '人民币元', '上海顺衡物流有限公司', '1001741919000018783', '中国工商银行上海市徐泾支行', '2019-05-23', null, '电子转账凭证', '102356139356', '3107536430N6PVSWL02', '网络转账', '4月快递费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1828-3107536430N8PPYEH2N', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-23', '1200.000000', '0.000000', '189565.980000', '人民币元', '成都元升财务咨询有限公司', '51050146830800001652', '建行成都第六支行', '2019-05-23', null, '电子转账凭证', '102356124775', '3107536430N8PPYEH2N', '网络转账', '成都房租补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1829-3107536430NBPXQ0UHM', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-23', '22076.780000', '0.000000', '167489.200000', '人民币元', '上海元升财务咨询有限公司', '1001293529358487582', '中国工商银行上海市中山南路支行', '2019-05-23', null, '电子转账凭证', '102356144337', '3107536430NBPXQ0UHM', '网络转账', '4月社保');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1830-3107236000N6PVWUU8Z', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-23', '0.000000', '2400.000000', '169889.200000', '人民币元', '上海阅乐会教育科技有限公司', '31050172360009730316', '建行上海虹口支行', '2019-05-23', null, '电子转账凭证', '102356437279', '3107236000N6PVWUU8Z', '网络转账', '年费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1831-3107041000NNPTSJB8C', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-25', '0.000000', '2300.000000', '172189.200000', '人民币元', '上海凯途环境治理有限公司', '31050170410000000616', '建行上海西藏路支行', '2019-05-25', null, '电子转账凭证', '102359066411', '3107041000NNPTSJB8C', '网络转账', '服务费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1832-31028000026NPDTE0QR', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-26', '16.000000', '0.000000', '172173.200000', '人民币元', null, null, null, '2019-05-26', null, '    ', null, '31028000026NPDTE0QR', '收费', '代发-本行同城');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1833-31075364326CPTOIDB6', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-26', '1.600000', '0.000000', '172171.600000', '人民币元', null, null, null, '2019-05-26', null, '    ', null, '31075364326CPTOIDB6', '收费', '收费项目:对公人民币转账、汇款（含退汇）-对公资金划转跨行同城');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1834-31075364326CPTOIDBM', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-26', '65.000000', '0.000000', '172106.600000', '人民币元', null, null, null, '2019-05-26', null, '    ', null, '31075364326CPTOIDBM', '收费', '收费项目:对公人民币转账、汇款（含退汇）-对公资金划转跨行异地');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1835-31075364326CPTOIDBZ', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-26', '15.000000', '0.000000', '172091.600000', '人民币元', null, null, null, '2019-05-26', null, '    ', null, '31075364326CPTOIDBZ', '收费', '收费项目:对公人民币转账、汇款（含退汇）-对公资金划转本行异地');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1836-3102800000N9P6E35HG', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-26', '0.000000', '234151.000000', '406242.600000', '人民币元', '广东信智投资有限公司', '44226201040006167', '中国农业银行股份有限公司惠州分行运营管理部', '2019-05-26', null, '电汇凭证', null, '3102800000N9P6E35HG', '自定义', '往来款');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1837-31028000016APY7IB3K', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '0.000000', '1800.000000', '408042.600000', '人民币元', '思丽绮（上海）商贸有限公司', '00000452075537944', '中国银行上海市分行营业部', '2019-05-27', null, '    ', null, '31028000016APY7IB3K', '自定义', '服务费/思丽绮（上海）商贸有限公司');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1838-3102800000OUPYLHJXG', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '38000.000000', '0.000000', '370042.600000', '人民币元', '支付宝（中国）网络技术有限公司', '215500690', '建行杭州西湖支行营业部', '2019-05-27', null, '    ', null, '3102800000OUPYLHJXG', '消费', '网上购物2019052708194858520224400202303-特约商户-');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1839-3102800000OQPU7DOSS', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '20000.000000', '0.000000', '350042.600000', '人民币元', '支付宝（中国）网络技术有限公司', '215500690', '建行杭州西湖支行营业部', '2019-05-27', null, '    ', null, '3102800000OQPU7DOSS', '消费', '网上购物2019052708205153320224440111602-特约商户-');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1840-3107536430NEP55V3N3', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '8608.480000', '0.000000', '341434.120000', '人民币元', '李青', '6230661031004195287', '江苏镇江农村商业银行股份有限公司营业部', '2019-05-27', null, '电子转账凭证', '102361797138', '3107536430NEP55V3N3', '网络转账', '5月特批报销');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1841-3107536430NIPQJLTHK', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '35000.000000', '0.000000', '306434.120000', '人民币元', '李青', '6230661031004195287', '江苏镇江农村商业银行股份有限公司营业部', '2019-05-27', null, '电子转账凭证', '102361796226', '3107536430NIPQJLTHK', '网络转账', '退休人员工资');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1842-3107536430NRPGRPR2A', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '18000.000000', '0.000000', '288434.120000', '人民币元', '宁波百恒财务咨询有限公司', '33150199630000000147', '建行宁波杭州湾新区支行', '2019-05-27', null, '电子转账凭证', '102361790428', '3107536430NRPGRPR2A', '网络转账', '宁波5月工资补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1843-31075364316RP1KLQAS', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '46085.890000', '0.000000', '242348.230000', '人民币元', '个人／单位存款', 'D1CN000011A0331PA01190527T050538967', null, '2019-05-27', null, '    ', '270095479909', '31075364316RP1KLQAS', '自定义', '代发工资');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1844-3107536430NDP89PVOV', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '85000.000000', '0.000000', '157348.230000', '人民币元', '镇江嘉元财务咨询有限公司', '3211020271010000005907', '江苏镇江农村商业银行股份有限公司丁卯支行', '2019-05-27', null, '电子转账凭证', '102361775025', '3107536430NDP89PVOV', '网络转账', '镇江5月工资社保补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1845-3107536430NMPF197FA', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '85000.000000', '0.000000', '72348.230000', '人民币元', '镇江嘉元财务咨询有限公司', '3211020271010000005907', '江苏镇江农村商业银行股份有限公司丁卯支行', '2019-05-27', null, '电子转账凭证', '102361772854', '3107536430NMPF197FA', '网络转账', '镇江5月工资社保补贴');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1846-31075364316APIEVG29', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-27', '0.000000', '1428.570000', '73776.800000', '人民币元', '个人／单位存款', 'D1CN000011A0331PA01190527T050538967', null, '2019-05-27', null, '    ', '270095479909', '31075364316APIEVG29', '自定义', '批量代收代付资金转回');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1847-3107546000MZPZDDJO9', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-29', '0.000000', '2900.000000', '76676.800000', '人民币元', '上海树芭母婴用品有限公司', '31050175460000000201', '建行上海长海支行', '2019-05-29', null, '电子转账凭证', '102364772660', '3107546000MZPZDDJO9', '网络转账', '代理费及汇算清缴费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1848-3107536430NFP5C1LOO', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-29', '857.140000', '0.000000', '75819.660000', '人民币元', '王军', '6217920180845161', '上海浦东发展银行股份有限公司创智天地支行', '2019-05-29', null, '电子转账凭证', '102365270809', '3107536430NFP5C1LOO', '网络转账', '5月工资');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1849-3107536430NAP3EXF5K', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-29', '5700.000000', '0.000000', '70119.660000', '人民币元', '柴快长', '6230941210000582712', '建行上海国定路支行', '2019-05-29', null, '电子转账凭证', '102365263656', '3107536430NAP3EXF5K', '网络转账', '5月报销');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1850-3107536430N0PG42K8X', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-29', '15000.000000', '0.000000', '55119.660000', '人民币元', '龚嘉玺', '6227001216730143488', '建行上海浙江路支行', '2019-05-29', null, '电子转账凭证', '102365262591', '3107536430N0PG42K8X', '网络转账', '2018年度');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1851-3106136400NGPFF4B05', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-30', '0.000000', '2400.000000', '57519.660000', '人民币元', '上海乐般教育科技有限公司', '31050161364000003342', '建行上海浦东分行', '2019-05-30', null, '电子转账凭证', '102366779895', '3106136400NGPFF4B05', '网络转账', '外账办公费');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1852-3107536430MZPWCMZVQ', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-30', '7800.000000', '0.000000', '49719.660000', '人民币元', '上海元升财务咨询有限公司', '1001293529358487582', '中国工商银行上海市中山南路支行', '2019-05-30', null, '电子转账凭证', '102365893998', '3107536430MZPWCMZVQ', '网络转账', '5月社保差额');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1853-3102800000OBP7VSW0S', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-30', '8000.000000', '0.000000', '41719.660000', '人民币元', '支付宝（中国）网络技术有限公司', '215500690', '建行杭州西湖支行营业部', '2019-05-30', null, '    ', null, '3102800000OBP7VSW0S', '消费', '网上购物2019053008274507700224130302304-特约商户-');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1854-3107536430NLPYA8TQZ', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-30', '2110.390000', '0.000000', '39609.270000', '人民币元', '唐磊敏', '6222801195940300158', '建行上海临汾路支行', '2019-05-30', null, '电子转账凭证', '102368126715', '3107536430NLPYA8TQZ', '网络转账', '4月，5月工资');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1855-3107542000NGPFU3AF0', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-30', '0.000000', '3600.000000', '43209.270000', '人民币元', '上海翰影商贸有限公司', '31050175420000000603', '建行上海平凉路支行', '2019-05-30', null, '电子转账凭证', '102368165242', '3107542000NGPFU3AF0', '网络转账', '2019年6月1日至2020年5月30日代理记账费用');
INSERT INTO `alr_bank_statement_dtl_ccbl_record` VALUES ('1856-3102800000OWPTHKXFP', '31001669701052514387-0001', '上海元升财务咨询有限公司', '2019-05-30', '0.000000', '2100.000000', '45309.270000', '人民币元', '刘慧', '6217001210073968081', '建行上海彰武路支行', '2019-05-30', null, '    ', null, '3102800000OWPTHKXFP', '转账存入', null);

-- ----------------------------
-- Table structure for alr_bank_statement_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_bank_statement_record`;
CREATE TABLE `alr_bank_statement_record` (
  `BATCH_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ACCOUNT_PERIOD` date DEFAULT NULL COMMENT '会计期间YYYYMM',
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `CUSTOM_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BANK_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`),
  KEY `IDX_BANK_RECORD` (`CUSTOM_ID`,`ACCOUNT_PERIOD`) USING BTREE,
  KEY `IDX_BANK_RECORD_BATCH` (`BATCH_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_bank_statement_record
-- ----------------------------
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000MZPJ132A2', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000N5P1X6NER', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000N9P6E35HG', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NAP3DU0BU', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NEPP3VIAI', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NFP5N7W7I', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NIPQJU27W', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NJP737KBK', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NKPM9IMGS', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000NNPS6X54C', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OBP7VSW0S', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OCP7UNHL6', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OMPP6LQ4A', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OQPU7DOSS', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OTP842YSM', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OUPYLHJXG', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3102800000OWPTHKXFP', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31028000016APY7IB3K', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31028000016IPEHWZZV', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31028000016XP3SYBK7', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31028000026NPDTE0QR', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106136400N5PFSOSI5', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106136400NGPFF4B05', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106136400NRPID1BGO', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106136990N5PIA4TDZ', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106136990NDPS03SRE', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106337000NAPXKKVAD', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106741000NNPTRL1T1', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3106741000NOPSJJM7N', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107037000N5PG5QOEB', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107037000NEP6T4TXA', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107041000NAPJNUFHK', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107041000NNPTSJB8C', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107236000N6PVWUU8Z', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430MZPWCMZVQ', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430N0PG42K8X', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430N0PV5B469', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430N6PVSWL02', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430N8PPYEH2N', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NAP3EXF5K', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NBPH7JIM3', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NBPXQ0UHM', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NCPCFNWNU', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NDP89PVOV', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NEP55V3N3', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NFP5C1LOO', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NIPQJLTHK', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NLPYA8TQZ', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NMPF197FA', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NNPDCN876', '123.xlsx', null, '1', '2019-07-28 23:32:43', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107536430NRPGRPR2A', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364316APIEVG29', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364316RP1KLQAS', '123.xlsx', null, '1', '2019-07-28 23:32:46', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364316UPCE32WC', '123.xlsx', null, '1', '2019-07-28 23:32:44', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364326CPTOIDB6', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364326CPTOIDBM', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364326CPTOIDBZ', '123.xlsx', null, '1', '2019-07-28 23:32:45', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '31075364326GPWTR09E', '123.xlsx', null, '1', '2019-07-28 23:32:42', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107542000NGPFU3AF0', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');
INSERT INTO `alr_bank_statement_record` VALUES ('aesribw858c0000', '3107546000MZPZDDJO9', '123.xlsx', null, '1', '2019-07-28 23:32:47', '31001669701052514387-0001', '31001669701052514387-0001');

-- ----------------------------
-- Table structure for alr_extra_invoice_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_extra_invoice_record`;
CREATE TABLE `alr_extra_invoice_record` (
  `CUSTOM_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ACCOUNT_PERIOD` date DEFAULT NULL,
  `RECORD_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INVOICE_TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TOTAL_AMOUNT` decimal(20,6) DEFAULT NULL,
  `ITEM_COUNT` decimal(10,0) DEFAULT NULL,
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`RECORD_ID`),
  KEY `IDX_EXTRA_INVOICE` (`CUSTOM_ID`,`ACCOUNT_PERIOD`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_extra_invoice_record
-- ----------------------------
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-31', '东方证券_20190613_1', '1', '214.000000', '4124215412', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-31', '东方证券_20190623_1', '1', '214.000000', '4124215412', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-31', '东方证券_20190630_1', '1', '214.000000', '4124215412', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-31', '东方证券_20190631_1', '1', '214.000000', '4124215412', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-03', '东方证券_20190703_0', '0', '142214.000000', '535135', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-03', '东方证券_20190703_1', '1', '23223.000000', '343243', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-31', '东方证券_20190731_0', '0', '32532.000000', '325235325', '1');
INSERT INTO `alr_extra_invoice_record` VALUES ('东方证券', '2019-07-31', '东方证券_20190731_1', '1', '214.000000', '2222222', '1');

-- ----------------------------
-- Table structure for alr_file_upload_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_file_upload_record`;
CREATE TABLE `alr_file_upload_record` (
  `BATCH_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PACKAGE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FILE_COUNT` int(11) DEFAULT NULL,
  `UPLOAD_DATE` datetime DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '0:未开始,1:处理中,2:已完成，3:失败',
  `OPERATOR` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作者ID',
  `UPDATE_DATE` datetime DEFAULT NULL,
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PACKAGE_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1:银行流水ZIP,2:发票清单ZIP,3:增值税ZIP,4:工资单ZIP',
  PRIMARY KEY (`BATCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_file_upload_record
-- ----------------------------
INSERT INTO `alr_file_upload_record` VALUES ('186q05b6e61s000', 'bank201905(1).zip', '1', '2019-07-28 12:15:28', '3', 'admin', '2019-07-28 12:15:28', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('1incqj35bukg000', 'bank201905(1).zip', '1', '2019-07-28 13:17:50', '2', 'admin', '2019-07-28 13:17:50', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('1lxlpcioj2qo000', '空岛五月销售清单.zip', '1', '2019-07-24 23:48:18', '2', 'admin', '2019-07-24 23:48:18', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('2kpx7e53vmg0000', '空岛五月销售清单.zip', '1', '2019-07-24 23:35:22', '1', 'admin', '2019-07-24 23:35:22', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('2z5g7gtw3l00000', '空岛五月销售清单.zip', '1', '2019-07-24 22:10:55', '2', 'admin', '2019-07-24 22:10:55', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('34evdpfw5yo0000', '123.zip', '1', '2019-07-28 12:31:32', '2', 'admin', '2019-07-28 12:31:32', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('436mcwe4aji0000', 'bank201905(1).zip', '1', '2019-07-28 13:09:44', '2', 'admin', '2019-07-28 13:09:44', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('53273gr6an40000', 'bank201905(1).zip', '1', '2019-07-28 13:06:57', '2', 'admin', '2019-07-28 13:06:57', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('5rzzzaljhrk0000', 'bank201905(1).zip', '1', '2019-07-28 13:16:34', '2', 'admin', '2019-07-28 13:16:34', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('686lcumz9ng0000', 'bank201905(1).zip', '1', '2019-07-28 12:16:10', '2', 'admin', '2019-07-28 12:16:10', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('6cdjj1o7ax80000', 'bank201905(1).zip', '1', '2019-07-28 13:10:54', '2', 'admin', '2019-07-28 13:10:54', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('8bfxh5k8c4w0000', 'bank201905(1).zip', '1', '2019-07-28 13:17:08', '2', 'admin', '2019-07-28 13:17:08', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('8ssfhygbpcg0000', 'bank201905(1).zip', '1', '2019-07-28 12:57:51', '2', 'admin', '2019-07-28 12:57:51', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('9ldg0fb6m380000', '空岛五月销售清单.zip', '1', '2019-07-24 23:33:55', '2', 'admin', '2019-07-24 23:33:55', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('9peyot639n40000', '空岛五月销售清单.zip', '1', '2019-07-24 23:27:39', '3', 'admin', '2019-07-24 23:27:39', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('aesribw858c0000', '123.zip', '1', '2019-07-28 23:32:42', '2', 'admin', '2019-07-28 23:32:42', '1', '1');
INSERT INTO `alr_file_upload_record` VALUES ('akxzdzmzrm80000', 'bank201905(1).zip', '1', '2019-07-28 13:28:54', '2', 'admin', '2019-07-28 13:28:54', '1', '1');
INSERT INTO `alr_file_upload_record` VALUES ('av4kyyzz5ig0000', 'bank201905(1).zip', '1', '2019-07-28 13:09:25', '2', 'admin', '2019-07-28 13:09:25', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('b3xnac51wug0000', '空岛五月销售清单.zip', '1', '2019-07-24 23:30:12', '3', 'admin', '2019-07-24 23:30:12', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('bfmte7byvk00000', 'bank201905(1).zip', '1', '2019-07-28 13:15:43', '2', 'admin', '2019-07-28 13:15:43', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('bgdg9xzg9qw0000', 'bank201905(1).zip', '1', '2019-07-28 12:56:49', '1', 'admin', '2019-07-28 12:56:49', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('bqng165qzsg0000', '空岛五月销售清单.zip', '1', '2019-07-25 00:29:13', '2', 'admin', '2019-07-25 00:29:13', '1', '2');
INSERT INTO `alr_file_upload_record` VALUES ('cf6xu2faso00000', '空岛五月销售清单.zip', '1', '2019-07-24 22:09:46', '3', 'admin', '2019-07-24 22:09:46', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('ckr9ssurs800000', 'bank201905(1).zip', '1', '2019-07-28 13:03:28', '2', 'admin', '2019-07-28 13:03:28', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('dcracwa22l40000', '空岛五月销售清单.zip', '1', '2019-07-24 23:49:06', '2', 'admin', '2019-07-24 23:49:06', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('dqxy19yxpjk0000', 'bank201905(1).zip', '1', '2019-07-28 13:20:48', '2', 'admin', '2019-07-28 13:20:48', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('elpj87b2zxc0000', '空岛五月销售清单.zip', '1', '2019-07-24 23:41:02', '2', 'admin', '2019-07-24 23:41:02', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('em8tqeye6nk0000', 'bank201905(1).zip', '1', '2019-07-28 13:22:01', '2', 'admin', '2019-07-28 13:22:01', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('ergp89i1fa80000', '空岛五月销售清单.zip', '1', '2019-07-24 23:28:48', '3', 'admin', '2019-07-24 23:28:48', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('ev2w24e318g0000', '空岛五月销售清单.zip', '1', '2019-07-24 22:05:48', '3', 'admin', '2019-07-24 22:05:48', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('f4hoh594fbs0000', 'bank201905(1).zip', '1', '2019-07-28 13:04:16', '2', 'admin', '2019-07-28 13:04:16', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('f5c59szg84g0000', 'bank201905(1).zip', '1', '2019-07-28 12:14:54', '3', 'admin', '2019-07-28 12:14:54', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('f6r70cvgbmg0000', 'bank201905(1).zip', '1', '2019-07-28 12:12:42', '3', 'admin', '2019-07-28 12:12:42', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('fin29p33lpk0000', 'bank201905(1).zip', '1', '2019-07-28 12:21:47', '2', 'admin', '2019-07-28 12:21:47', '0', '1');
INSERT INTO `alr_file_upload_record` VALUES ('fuiq9etm61s0000', '空岛五月销售清单.zip', '1', '2019-07-24 23:31:35', '2', 'admin', '2019-07-24 23:31:35', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('hjdm9n6nxdk000', '空岛五月销售清单.zip', '1', '2019-07-24 22:26:52', '3', 'admin', '2019-07-24 22:26:52', '0', '2');
INSERT INTO `alr_file_upload_record` VALUES ('pb22850jzww000', 'bank201905(1).zip', '1', '2019-07-28 13:13:48', '2', 'admin', '2019-07-28 13:13:48', '0', '1');

-- ----------------------------
-- Table structure for alr_invoice_type_dict
-- ----------------------------
DROP TABLE IF EXISTS `alr_invoice_type_dict`;
CREATE TABLE `alr_invoice_type_dict` (
  `INVOICE_TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INVOICE_TYPE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`INVOICE_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_invoice_type_dict
-- ----------------------------

-- ----------------------------
-- Table structure for alr_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `alr_menu_info`;
CREATE TABLE `alr_menu_info` (
  `ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `PARENT_ID` varchar(45) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL,类型：1.普通页面（如票据管理， /salary/query） 2.嵌套完整外部页面，以http(s)开头的链接 ',
  `PERMS` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `TYPE` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of alr_menu_info
-- ----------------------------

-- ----------------------------
-- Table structure for alr_role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `alr_role_menu_rel`;
CREATE TABLE `alr_role_menu_rel` (
  `ID` varchar(45) NOT NULL COMMENT '编号',
  `ROLE_ID` varchar(45) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of alr_role_menu_rel
-- ----------------------------

-- ----------------------------
-- Table structure for alr_salary_statement_dtl_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_salary_statement_dtl_record`;
CREATE TABLE `alr_salary_statement_dtl_record` (
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DETAIL_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STAFF_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '1:FULL-TIME,2:PART-TIME',
  `STAFF_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NEED_ENDOWMENT_INS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '养老金',
  `NEED_UNEMPLOY_INS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '失业保险',
  `NEED_MEDICAL_INS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医疗保险',
  `NEED_INJURY_INS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工伤保险',
  `NEED_BIRTH_INS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生育保险',
  `NEED_HABITATION_INS` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '住房保险',
  `BASE_SALARY` decimal(10,0) DEFAULT NULL,
  `POSITION_VALUE` decimal(10,0) DEFAULT NULL COMMENT '岗位工资',
  `TRAVEL_ALLOWANCE` decimal(10,0) DEFAULT NULL COMMENT '交通补贴',
  PRIMARY KEY (`DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_salary_statement_dtl_record
-- ----------------------------

-- ----------------------------
-- Table structure for alr_salary_statement_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_salary_statement_record`;
CREATE TABLE `alr_salary_statement_record` (
  `BATCH_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ACCOUNT_PERIOD` date DEFAULT NULL COMMENT '会计期间YYYYMM',
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `UPDATE_DATE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CUSTOM_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户代码',
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_salary_statement_record
-- ----------------------------

-- ----------------------------
-- Table structure for alr_std_invoice_dtl_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_std_invoice_dtl_record`;
CREATE TABLE `alr_std_invoice_dtl_record` (
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INVOICE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发票号码',
  `INVOICE_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发票代码',
  `BUYER_NAME` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购方企业名称',
  `BUYER_TAX_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购方税号',
  `BANK_BRANCH` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行支行名称',
  `BANK_ACCOUNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行账号',
  `ADDRESS` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TEL` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `INVOICE_DATE` datetime DEFAULT NULL COMMENT '开票日期',
  `PRODUCT_VERSION` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编码版本号',
  `RECEIPT_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单据号',
  `PRODUCT_NAME` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PRODUCT_SPEC` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规格',
  `PRODUCT_UNIT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PRODUCT_COUNT` decimal(20,10) DEFAULT NULL,
  `PRODUCT_UNIT_PRICE` decimal(20,10) DEFAULT NULL,
  `TOTAL_PRICE` decimal(20,10) DEFAULT NULL,
  `TAX_RATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税率',
  `TAX` decimal(20,10) DEFAULT NULL COMMENT '税额',
  `TAX_TYPE_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税收分类编码',
  `IMAGE_URL` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`,`INVOICE_ID`,`INVOICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_std_invoice_dtl_record
-- ----------------------------
INSERT INTO `alr_std_invoice_dtl_record` VALUES ('29539746_3100183130', '3100183130', '29539746', '上海灏米广告有限公司', '91310110MA1G8BRU4N', '招商银行股份有限公司上海高安支行  121925277610501', '招商银行股份有限公司上海高安支行  121925277610501', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '2019-05-05 00:00:00', '32', null, '*广播影视服务*制作费', null, null, null, null, '95000.0000000000', '6%', '5700.0000000000', '3040701030000000000', null);
INSERT INTO `alr_std_invoice_dtl_record` VALUES ('29539747_3100183130', '3100183130', '29539747', '上海灏米广告有限公司', '91310110MA1G8BRU4N', '招商银行股份有限公司上海高安支行  121925277610501', '招商银行股份有限公司上海高安支行  121925277610501', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '2019-05-05 00:00:00', '32', null, '*广播影视服务*制作费', null, null, null, null, '95000.0000000000', '6%', '5700.0000000000', '3040701030000000000', null);
INSERT INTO `alr_std_invoice_dtl_record` VALUES ('29539748_3100183130', '3100183130', '29539748', '上海灏米广告有限公司', '91310110MA1G8BRU4N', '招商银行股份有限公司上海高安支行  121925277610501', '招商银行股份有限公司上海高安支行  121925277610501', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '2019-05-05 00:00:00', '32', null, '*广播影视服务*制作费', null, null, null, null, '95000.0000000000', '6%', '5700.0000000000', '3040701030000000000', null);
INSERT INTO `alr_std_invoice_dtl_record` VALUES ('29539749_3100183130', '3100183130', '29539749', '上海灏米广告有限公司', '91310110MA1G8BRU4N', '招商银行股份有限公司上海高安支行  121925277610501', '招商银行股份有限公司上海高安支行  121925277610501', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '上海市杨浦区延吉中路77号201-B4室  021-6029 3308', '2019-05-05 00:00:00', '32', null, '*广播影视服务*制作费', null, null, null, null, '95000.0000000000', '6%', '5700.0000000000', '3040701030000000000', null);
INSERT INTO `alr_std_invoice_dtl_record` VALUES ('29539750_3100183130', '3100183130', '29539750', '帛铭医疗科技（上海）有限公司', '91310115MA1H7PBE5N', '中国光大银行股份有限公司上海龙茗支行  368201880000693', '中国光大银行股份有限公司上海龙茗支行  368201880000693', '上海市浦东新区东方路3601号2号楼2887室', '上海市浦东新区东方路3601号2号楼2887室', '2019-05-06 00:00:00', '32', null, '*广播影视服务*三维影片制作', null, null, null, null, '9433.9600000000', '6%', '566.0400000000', '3040701030000000000', null);

-- ----------------------------
-- Table structure for alr_std_invoice_record
-- ----------------------------
DROP TABLE IF EXISTS `alr_std_invoice_record`;
CREATE TABLE `alr_std_invoice_record` (
  `BATCH_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ACCOUNT_PERIOD` datetime DEFAULT NULL COMMENT '会计期间YYYYMM',
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `CUSTOM_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户代码',
  `FINANCE_DISK_TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '金税盘种类',
  PRIMARY KEY (`FILE_ID`),
  KEY `IDX_INVOICE_RECORD` (`CUSTOM_ID`) USING BTREE,
  KEY `IDX_INVOICE_RECORD_BATCH` (`BATCH_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alr_std_invoice_record
-- ----------------------------
INSERT INTO `alr_std_invoice_record` VALUES ('bqng165qzsg0000', '29539746_3100183130', '空岛五月销售清单.xlsx', null, '1', '2019-07-25 00:29:13', '91310110MA1G8BRU4N', null);
INSERT INTO `alr_std_invoice_record` VALUES ('bqng165qzsg0000', '29539747_3100183130', '空岛五月销售清单.xlsx', null, '1', '2019-07-25 00:29:13', '91310110MA1G8BRU4N', null);
INSERT INTO `alr_std_invoice_record` VALUES ('bqng165qzsg0000', '29539748_3100183130', '空岛五月销售清单.xlsx', null, '1', '2019-07-25 00:29:13', '91310110MA1G8BRU4N', null);
INSERT INTO `alr_std_invoice_record` VALUES ('bqng165qzsg0000', '29539749_3100183130', '空岛五月销售清单.xlsx', null, '1', '2019-07-25 00:29:13', '91310110MA1G8BRU4N', null);
INSERT INTO `alr_std_invoice_record` VALUES ('bqng165qzsg0000', '29539750_3100183130', '空岛五月销售清单.xlsx', null, '1', '2019-07-25 00:29:13', '91310115MA1H7PBE5N', null);

-- ----------------------------
-- Table structure for alr_std_invoice_record_1_ext
-- ----------------------------
DROP TABLE IF EXISTS `alr_std_invoice_record_1_ext`;
CREATE TABLE `alr_std_invoice_record_1_ext` (
  `INVOICE_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ITME_COUNT` decimal(10,0) DEFAULT NULL COMMENT '份数',
  `AMOUNT` decimal(10,0) DEFAULT NULL COMMENT '金额',
  `TAX` decimal(10,0) DEFAULT NULL COMMENT '税额',
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='种类1的金税盘扩展信息';

-- ----------------------------
-- Records of alr_std_invoice_record_1_ext
-- ----------------------------

-- ----------------------------
-- Table structure for alr_value_added_tax_scan_items_result
-- ----------------------------
DROP TABLE IF EXISTS `alr_value_added_tax_scan_items_result`;
CREATE TABLE `alr_value_added_tax_scan_items_result` (
  `INVOICE_ID` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `INVOICE_CODE` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `ITEM_NAME` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '名称',
  `ITEM_SPEC` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '规格',
  `ITEM_UNIT` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '单位',
  `ITEM_AMOUNT` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '数量',
  `PRICE_UNIT` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '单价',
  `PRICE_SUM` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '金额',
  `TAX_RATE` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '税率',
  `TAX_SUM` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '税额',
  PRIMARY KEY (`INVOICE_ID`,`INVOICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='增值税商品明细';

-- ----------------------------
-- Records of alr_value_added_tax_scan_items_result
-- ----------------------------

-- ----------------------------
-- Table structure for alr_value_added_tax_scan_result
-- ----------------------------
DROP TABLE IF EXISTS `alr_value_added_tax_scan_result`;
CREATE TABLE `alr_value_added_tax_scan_result` (
  `FILE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否有效',
  `IS_VERIFIED` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否验证通过',
  `IS_EMEND` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否已订正',
  `INVOICE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发票号码',
  `INVOICE_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发票代码',
  `INVOICE_DATE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CHECK_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '校验码',
  `TAX_FREE_AMOUNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '不含税金额',
  `UPDATE_DATE` datetime DEFAULT NULL,
  `BATCH_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OPERATOR` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FAILED_REASON` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `INVOICE_TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发票类型',
  `SELLER_NAME` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '销售方名称',
  `SELLER_TAX_CODE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '销售方纳税人识别号',
  `SELLER_BANK` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '销售方开户行',
  `BUYER_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购方名称',
  `BUYER_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购方纳税人识别号',
  `BUYER_CONTACT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购方联系方式',
  `BUYER_BANK` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购方开户行',
  `INVOICE_MACHINE_NUM` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发票机器码',
  `IS_INVALID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否作废',
  `TAX_AMOUNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税额合计',
  `TOTAL_AMOUNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '价税合计',
  `QUANTITY` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数量合计',
  `REMARK` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `VERIFY_TIMES` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税局查验次数',
  `UPDATE_TIME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '验真返回更新时间',
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='增值税发票识别结果表';

-- ----------------------------
-- Records of alr_value_added_tax_scan_result
-- ----------------------------
