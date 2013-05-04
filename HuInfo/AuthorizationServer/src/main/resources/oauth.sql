/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : oauth

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2013-04-26 13:04:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accesstoken`
-- ----------------------------
DROP TABLE IF EXISTS `accesstoken`;
CREATE TABLE `accesstoken` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `encodedPrincipal` varchar(1024) DEFAULT NULL,
  `expires` bigint(20) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `resourceOwnerId` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) NOT NULL,
  `grant_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_CCSSTKN_REFRESHTOKEN` (`refreshToken`),
  UNIQUE KEY `U_CCSSTKN_TOKEN` (`token`),
  KEY `I_CCSSTKN_CLIENT` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `accesstoken_scopes`
-- ----------------------------
DROP TABLE IF EXISTS `accesstoken_scopes`;
CREATE TABLE `accesstoken_scopes` (
  `ACCESSTOKEN_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_CCSSCPS_ACCESSTOKEN_ID` (`ACCESSTOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `ac_permission`
-- ----------------------------
DROP TABLE IF EXISTS `ac_permission`;
CREATE TABLE `ac_permission` (
  `id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `ac_role`
-- ----------------------------
DROP TABLE IF EXISTS `ac_role`;
CREATE TABLE `ac_role` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_role
-- ----------------------------

-- ----------------------------
-- Table structure for `ac_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `ac_role_permission`;
CREATE TABLE `ac_role_permission` (
  `id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `ac_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ac_user_role`;
CREATE TABLE `ac_user_role` (
  `id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ac_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `authorizationrequest`
-- ----------------------------
DROP TABLE IF EXISTS `authorizationrequest`;
CREATE TABLE `authorizationrequest` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `authState` varchar(255) DEFAULT NULL,
  `authorizationCode` varchar(255) DEFAULT NULL,
  `encodedPrincipal` varchar(1024) DEFAULT NULL,
  `redirectUri` varchar(255) DEFAULT NULL,
  `responseType` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_THRZQST_CLIENT` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorizationrequest
-- ----------------------------

-- ----------------------------
-- Table structure for `authorizationrequest_grantedscopes`
-- ----------------------------
DROP TABLE IF EXISTS `authorizationrequest_grantedscopes`;
CREATE TABLE `authorizationrequest_grantedscopes` (
  `AUTHORIZATIONREQUEST_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_THRZCPS_AUTHORIZATIONREQUEST_ID` (`AUTHORIZATIONREQUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorizationrequest_grantedscopes
-- ----------------------------

-- ----------------------------
-- Table structure for `authorizationrequest_requestedscopes`
-- ----------------------------
DROP TABLE IF EXISTS `authorizationrequest_requestedscopes`;
CREATE TABLE `authorizationrequest_requestedscopes` (
  `AUTHORIZATIONREQUEST_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_THRZCPS_AUTHORIZATIONREQUEST_ID1` (`AUTHORIZATIONREQUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorizationrequest_requestedscopes
-- ----------------------------

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `contactName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `expireDuration` bigint(20) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `notAllowedImplicitGrant` bit(1) DEFAULT NULL,
  `secret` varchar(255) NOT NULL,
  `skipConsent` bit(1) DEFAULT NULL,
  `thumbNailUrl` varchar(255) DEFAULT NULL,
  `useRefreshTokens` bit(1) DEFAULT NULL,
  `resourceserver_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_CLIENT_SECRET` (`secret`),
  UNIQUE KEY `U_CLIENT_CLIENTID` (`clientId`),
  KEY `I_CLIENT_RESOURCESERVER` (`resourceserver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('99998', null, null, 'MmWall', 'zhangzhenli@live.com', 'zhangzhenli', 'Javascript application for authorization server administration', null, 'Authorization Server Admin Client', null, '8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92', null, 'https://raw.github.com/OpenConextApps/apis/master/apis-images/surf-oauth-client.png', null, '99998');

-- ----------------------------
-- Table structure for `client_attributes`
-- ----------------------------
DROP TABLE IF EXISTS `client_attributes`;
CREATE TABLE `client_attributes` (
  `client_id` bigint(20) DEFAULT NULL,
  `attribute_name` varchar(255) NOT NULL,
  `attribute_value` varchar(255) DEFAULT NULL,
  KEY `I_CLNTBTS_CLIENT_ID` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_attributes
-- ----------------------------

-- ----------------------------
-- Table structure for `client_redirecturis`
-- ----------------------------
DROP TABLE IF EXISTS `client_redirecturis`;
CREATE TABLE `client_redirecturis` (
  `CLIENT_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_CLNTTRS_CLIENT_ID` (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_redirecturis
-- ----------------------------

-- ----------------------------
-- Table structure for `client_scopes`
-- ----------------------------
DROP TABLE IF EXISTS `client_scopes`;
CREATE TABLE `client_scopes` (
  `CLIENT_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `I_CLNTCPS_CLIENT_ID` (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_scopes
-- ----------------------------
INSERT INTO `client_scopes` VALUES ('99998', 'read');
INSERT INTO `client_scopes` VALUES ('99998', 'write');

-- ----------------------------
-- Table structure for `ibatis`
-- ----------------------------
DROP TABLE IF EXISTS `ibatis`;
CREATE TABLE `ibatis` (
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ibatis
-- ----------------------------
INSERT INTO `ibatis` VALUES ('1');

-- ----------------------------
-- Table structure for `resourceserver`
-- ----------------------------
DROP TABLE IF EXISTS `resourceserver`;
CREATE TABLE `resourceserver` (
  `id` bigint(20) NOT NULL,
  `creationDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `contactName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `resourceServerKey` varchar(255) DEFAULT NULL,
  `resourceServerName` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `thumbNailUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_RSRCRVR_KEY` (`resourceServerKey`),
  UNIQUE KEY `U_RSRCRVR_OWNER` (`owner`,`resourceServerName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resourceserver
-- ----------------------------
INSERT INTO `resourceserver` VALUES ('99998', null, null, 'localadmin@example.com', 'local admin', null, 'authorization-server-admin', 'Authorization Server Apis', null, 'cafebabe-cafe-babe-cafe-babecafebabe', 'https://raw.github.com/OpenConextApps/apis/master/apis-images/surf-oauth.png');

-- ----------------------------
-- Table structure for `resourceserver_scopes`
-- ----------------------------
DROP TABLE IF EXISTS `resourceserver_scopes`;
CREATE TABLE `resourceserver_scopes` (
  `RESOURCESERVER_ID` bigint(20) DEFAULT NULL,
  `element` varchar(255) DEFAULT NULL,
  UNIQUE KEY `I_RSRCCPS_ELEMENT` (`element`),
  KEY `I_RSRCCPS_RESOURCESERVER_ID` (`RESOURCESERVER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resourceserver_scopes
-- ----------------------------
INSERT INTO `resourceserver_scopes` VALUES ('99998', 'read');
INSERT INTO `resourceserver_scopes` VALUES ('99998', 'write');

-- ----------------------------
-- Table structure for `resource_own`
-- ----------------------------
DROP TABLE IF EXISTS `resource_own`;
CREATE TABLE `resource_own` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ext` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `I_RESOURCE_OWN_USERNAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_own
-- ----------------------------
INSERT INTO `resource_own` VALUES ('1', 'test', '9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08', '0');

-- ----------------------------
-- Table structure for `resource_own_info`
-- ----------------------------
DROP TABLE IF EXISTS `resource_own_info`;
CREATE TABLE `resource_own_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userID` varchar(100) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `age` bigint(20) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `ext` text,
  PRIMARY KEY (`id`),
  KEY `I_RESOURCE_OWN_INFO_DOM` (`domain`),
  KEY `I_RESOURCE_OWN_INFO_UEERNAME` (`userName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_own_info
-- ----------------------------
INSERT INTO `resource_own_info` VALUES ('1', null, null, '1', '小明', null, '21', '男', null, null);
INSERT INTO `resource_own_info` VALUES ('18', '0DA5CED14B5AB315E92AD910F043FE6B', 'https://connect.qq.com/', '张振利', '张振利', 'http://q.qlogo.cn/qqapp/100330589/0DA5CED14B5AB315E92AD910F043FE6B/100', '0', null, null, '{\"client_id\":\"100330589\",\"openid\":\"0DA5CED14B5AB315E92AD910F043FE6B\",\"trusted_token\":\"71EE429C198DBC08DBE7FE7BD38687DC\",\"trusted_domain\":\"https:\\/\\/connect.qq.com\\/\",\"TRUSTED_TIME\":1366616658907,\"data\":[{\"ret\":0,\"msg\":\"\",\"nickname\":\"张振利\",\"figureurl\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/100330589\\/0DA5CED14B5AB315E92AD910F043FE6B\\/30\",\"figureurl_1\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/100330589\\/0DA5CED14B5AB315E92AD910F043FE6B\\/50\",\"figureurl_2\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/100330589\\/0DA5CED14B5AB315E92AD910F043FE6B\\/100\",\"figureurl_qq_1\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/100330589\\/0DA5CED14B5AB315E92AD910F043FE6B\\/41\",\"figureurl_qq_2\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/100330589\\/0DA5CED14B5AB315E92AD910F043FE6B\\/100\",\"is_yellow_vip\":\"0\",\"yellow_vip_level\":\"0\",\"is_yellow_year_vip\":\"0\"}]}');
INSERT INTO `resource_own_info` VALUES ('19', '703219695EF721146091513317FACC05', 'https://open.t.qq.com/api/', 'zhangzhenli', '霁雨天明', 'http://app.qlogo.cn/mbloghead/21385fa181c5e2c2ff46/50', '631123200031', '男', null, '{\"data\":{\"birth_day\":1,\"birth_month\":1,\"birth_year\":1990,\"city_code\":\"1\",\"comp\":null,\"country_code\":\"1\",\"edu\":[{\"departmentid\":0,\"id\":24037,\"level\":4,\"schoolid\":93148,\"year\":2007}],\"email\":\"\",\"exp\":1159,\"fansnum\":60,\"favnum\":0,\"head\":\"http:\\/\\/app.qlogo.cn\\/mbloghead\\/21385fa181c5e2c2ff46\",\"homecity_code\":\"13\",\"homecountry_code\":\"1\",\"homepage\":\"http:\\/\\/t.qq.com\\/zhangzhenli\",\"homeprovince_code\":\"34\",\"hometown_code\":\"\",\"https_head\":\"https:\\/\\/app.qlogo.cn\\/mbloghead\\/21385fa181c5e2c2ff46\",\"idolnum\":48,\"industry_code\":2002,\"introduction\":\"\",\"isent\":0,\"ismyblack\":0,\"ismyfans\":0,\"ismyidol\":0,\"isrealname\":1,\"isvip\":0,\"level\":3,\"location\":\"中国 江苏 南京\",\"mutual_fans_num\":11,\"name\":\"zhangzhenli\",\"nick\":\"霁雨天明\",\"openid\":\"703219695EF721146091513317FACC05\",\"province_code\":\"32\",\"regtime\":1279761288,\"send_private_flag\":2,\"sex\":1,\"tag\":null,\"tweetinfo\":[{\"city_code\":\"1\",\"country_code\":\"1\",\"emotiontype\":0,\"emotionurl\":\"\",\"from\":\"QQ空间分享\",\"fromurl\":\"http:\\/\\/rc.qzone.qq.com\\/share\\/\\n\",\"geo\":\"\",\"id\":\"263736120015752\",\"image\":[\"http:\\/\\/app.qpic.cn\\/mblogpic\\/6d7522661329062f3c70\"],\"latitude\":\"0\",\"location\":\"中国 江苏 南京\",\"longitude\":\"0\",\"music\":null,\"origtext\":\"【PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET 】PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET,我们也可以！ (分享自 @Qzone) http:\\/\\/url.cn\\/9S8tHD \",\"province_code\":\"32\",\"self\":1,\"status\":0,\"text\":\"【PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET 】PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET,我们也可以！ (分享自 @Qzone) <a href=\\\"http:\\/\\/url.cn\\/9S8tHD\\\" target=\\\"_blank\\\">http:\\/\\/url.cn\\/9S8tHD<\\/a> \",\"timestamp\":1363352296,\"type\":1,\"video\":null}],\"tweetnum\":142,\"verifyinfo\":\"\"},\"errcode\":0,\"msg\":\"ok\",\"ret\":0,\"seqid\":5869574058451133478,\"trusted_token\":\"fde1bb78dbc270e3e05b065bb6c4d4da\",\"trusted_domain\":\"https:\\/\\/open.t.qq.com\\/api\\/\",\"trusted_uid\":\"703219695EF721146091513317FACC05\",\"TRUSTED_TIME\":1366616707031}');
INSERT INTO `resource_own_info` VALUES ('20', '1390951803', 'https://api.weibo.com/2/', 'sxse', 'sxse', 'http://tp4.sinaimg.cn/1390951803/50/5630124907/1', '0', '男', null, '{\"uid\":1390951803,\"appkey\":\"2618327280\",\"scope\":null,\"create_at\":1366080817,\"expire_in\":157144068,\"trusted_token\":\"2.00VGSIWBKXOMrCa79df8728cEVfvfC\",\"trusted_domain\":\"https:\\/\\/api.weibo.com\\/2\\/\",\"TRUSTED_TIME\":1366616748584,\"data\":[{\"id\":1390951803,\"idstr\":\"1390951803\",\"screen_name\":\"sxse\",\"name\":\"sxse\",\"province\":\"34\",\"city\":\"6\",\"location\":\"安徽 淮北\",\"description\":\"\",\"url\":\"http:\\/\\/blog.sina.com.cn\\/sxse1\",\"profile_image_url\":\"http:\\/\\/tp4.sinaimg.cn\\/1390951803\\/50\\/5630124907\\/1\",\"profile_url\":\"sxse\",\"domain\":\"sxse\",\"weihao\":\"\",\"gender\":\"m\",\"followers_count\":21,\"friends_count\":75,\"statuses_count\":22,\"favourites_count\":0,\"created_at\":\"Wed Oct 20 23:10:54 +0800 2010\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Wed Mar 27 21:08:00 +0800 2013\",\"id\":3560570743869320,\"mid\":\"3560570743869320\",\"idstr\":\"3560570743869320\",\"text\":\"Hi，我正在使用#百度网盘#，给大家分享“471.patch”文件，快来看看吧~ http:\\/\\/t.cn\\/zThWFH7\",\"source\":\"<a href=\\\"http:\\/\\/app.weibo.com\\/t\\/feed\\/2afzVX\\\" rel=\\\"nofollow\\\">百度分享<\\/a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_urls\":[{\"thumbnail_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/thumbnail\\/52e83d7bjw1e34n0gpajzj.jpg\"}],\"thumbnail_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/thumbnail\\/52e83d7bjw1e34n0gpajzj.jpg\",\"bmiddle_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/bmiddle\\/52e83d7bjw1e34n0gpajzj.jpg\",\"original_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/large\\/52e83d7bjw1e34n0gpajzj.jpg\",\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0}},\"allow_all_comment\":true,\"avatar_large\":\"http:\\/\\/tp4.sinaimg.cn\\/1390951803\\/180\\/5630124907\\/1\",\"verified_reason\":\"\",\"follow_me\":false,\"online_status\":0,\"bi_followers_count\":9,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0}]}');

-- ----------------------------
-- Table structure for `trusted_domain`
-- ----------------------------
DROP TABLE IF EXISTS `trusted_domain`;
CREATE TABLE `trusted_domain` (
  `domain` varchar(255) NOT NULL,
  `clientID` bigint(20) NOT NULL,
  `attribute_name` varchar(255) NOT NULL,
  `attribute_value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trusted_domain
-- ----------------------------
INSERT INTO `trusted_domain` VALUES ('open.t.qq.com', '99998', 'AppSecret', '4e840fb93d23f3672f354226c8cdf06f');
INSERT INTO `trusted_domain` VALUES ('open.t.qq.com', '99998', 'AppKey', '801336424');
INSERT INTO `trusted_domain` VALUES ('api.weibo.com', '99998', 'AppKey', '2618327280');
INSERT INTO `trusted_domain` VALUES ('api.weibo.com', '99998', 'AppSecret', '2420513437649628b6531e1ada864f6a');
INSERT INTO `trusted_domain` VALUES ('connect.qq.com', '99998', 'AppKey', '100330589');
INSERT INTO `trusted_domain` VALUES ('connect.qq.com', '99998', 'AppSecret', '6b447af578b9dcd5233ce966625ac495');
