/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : oauth

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2013-04-15 15:13:28
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
) ENGINE=InnoDB AUTO_INCREMENT=10081 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accesstoken
-- ----------------------------
INSERT INTO `accesstoken` VALUES ('10070', '2013-04-15 12:27:12', '2013-04-15 12:34:27', 'Bearer', '302400', 'fe34f4e8e257e1bb579eef2fc43c01eb', '1390951803', 'c48a9d0940cbf440f6ee27dd92dba9c0', '99998', 'refresh_token');
INSERT INTO `accesstoken` VALUES ('10072', '2013-04-15 12:42:01', '2013-04-15 12:49:20', 'Bearer', '302400', '201f953386e3d81528d40ac3f7b69780', 'test', '5676eb3714e4dc206166a91be0be5cb8', '99998', 'password');
INSERT INTO `accesstoken` VALUES ('10073', '2013-04-15 12:55:16', '2013-04-15 14:53:37', 'Bearer', '302400', '2dce2115fa5e5d3f78fba603e18bcd88', '703219695EF721146091513317FACC05', '076c1f1de87507d2ef1a0a85d3566bfb', '99998', 'trusted_token');
INSERT INTO `accesstoken` VALUES ('10074', '2013-04-15 14:04:36', '2013-04-15 14:04:36', 'Bearer', '302400', null, null, '0e9211e2dc8ccfe5373218e3716065fe', '99998', 'client_credentials');
INSERT INTO `accesstoken` VALUES ('10075', '2013-04-15 14:05:09', '2013-04-15 14:05:09', 'Bearer', '302400', null, null, '262e204615e9dc11a4eef4fb2bfb084d', '99998', 'client_credentials');
INSERT INTO `accesstoken` VALUES ('10076', '2013-04-15 14:41:15', '2013-04-15 14:41:15', 'Bearer', '302400', null, null, '96e48739613059e945a9ce7bc5c10a92', '99998', 'client_credentials');
INSERT INTO `accesstoken` VALUES ('10077', '2013-04-15 14:41:45', '2013-04-15 14:41:45', 'Bearer', '302400', null, null, 'ad46a8e6fa2f10c9f016cf3aa0fe3e88', '99998', 'client_credentials');
INSERT INTO `accesstoken` VALUES ('10078', '2013-04-15 14:43:32', '2013-04-15 14:43:32', 'Bearer', '302400', null, null, 'b387451b7321ecff2f0fd2e95bee4732', '99998', 'client_credentials');
INSERT INTO `accesstoken` VALUES ('10079', '2013-04-15 14:43:46', '2013-04-15 14:43:46', 'Bearer', '302400', null, null, 'a62b88c906c9ccdf60de39d8b84843b7', '99998', 'client_credentials');
INSERT INTO `accesstoken` VALUES ('10080', '2013-04-15 14:44:51', '2013-04-15 14:44:51', 'Bearer', '302400', null, null, '2c5551746bcf7c20db2be9e3566ad664', '99998', 'client_credentials');

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
-- Records of accesstoken_scopes
-- ----------------------------
INSERT INTO `accesstoken_scopes` VALUES ('10072', null);
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');
INSERT INTO `accesstoken_scopes` VALUES ('10073', 'unprotected');

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
  UNIQUE KEY `I_RESOURCE_OWN_INFO_UEERNAME` (`userName`) USING BTREE,
  KEY `I_RESOURCE_OWN_INFO_DOM` (`domain`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_own_info
-- ----------------------------
INSERT INTO `resource_own_info` VALUES ('1', null, null, '1', '小明', null, '21', '男', null, null);
INSERT INTO `resource_own_info` VALUES ('7', '1390951803', 'https://api.weibo.com/2/', 'sxse', 'sxse', 'http://tp4.sinaimg.cn/1390951803/50/5630124907/1', '0', '男', null, '{\"uid\":1390951803,\"appkey\":\"2618327280\",\"scope\":null,\"create_at\":1365992959,\"expire_in\":157675385,\"trusted_token\":\"2.00VGSIWBKXOMrCa79df8728cEVfvfC\",\"trusted_domain\":\"https:\\/\\/api.weibo.com\\/2\\/\",\"TRUSTED_TIME\":1365997575457,\"data\":[{\"id\":1390951803,\"idstr\":\"1390951803\",\"screen_name\":\"sxse\",\"name\":\"sxse\",\"province\":\"34\",\"city\":\"6\",\"location\":\"安徽 淮北\",\"description\":\"\",\"url\":\"http:\\/\\/blog.sina.com.cn\\/sxse1\",\"profile_image_url\":\"http:\\/\\/tp4.sinaimg.cn\\/1390951803\\/50\\/5630124907\\/1\",\"profile_url\":\"sxse\",\"domain\":\"sxse\",\"weihao\":\"\",\"gender\":\"m\",\"followers_count\":20,\"friends_count\":75,\"statuses_count\":22,\"favourites_count\":0,\"created_at\":\"Wed Oct 20 23:10:54 +0800 2010\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Wed Mar 27 21:08:00 +0800 2013\",\"id\":3560570743869320,\"mid\":\"3560570743869320\",\"idstr\":\"3560570743869320\",\"text\":\"Hi，我正在使用#百度网盘#，给大家分享“471.patch”文件，快来看看吧~ http:\\/\\/t.cn\\/zThWFH7\",\"source\":\"<a href=\\\"http:\\/\\/app.weibo.com\\/t\\/feed\\/2afzVX\\\" rel=\\\"nofollow\\\">百度分享<\\/a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_ids\":[\"52e83d7bjw1e34n0gpajzj\"],\"thumbnail_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/thumbnail\\/52e83d7bjw1e34n0gpajzj.jpg\",\"bmiddle_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/bmiddle\\/52e83d7bjw1e34n0gpajzj.jpg\",\"original_pic\":\"http:\\/\\/ww4.sinaimg.cn\\/large\\/52e83d7bjw1e34n0gpajzj.jpg\",\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0}},\"allow_all_comment\":true,\"avatar_large\":\"http:\\/\\/tp4.sinaimg.cn\\/1390951803\\/180\\/5630124907\\/1\",\"verified_reason\":\"\",\"follow_me\":false,\"online_status\":1,\"bi_followers_count\":9,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0}]}');
INSERT INTO `resource_own_info` VALUES ('8', '703219695EF721146091513317FACC05', 'https://open.t.qq.com/api/', 'zhangzhenli', '霁雨天明', 'http://app.qlogo.cn/mbloghead/21385fa181c5e2c2ff46/50', '631123200314', '男', null, '{\"data\":{\"birth_day\":1,\"birth_month\":1,\"birth_year\":1990,\"city_code\":\"1\",\"comp\":null,\"country_code\":\"1\",\"edu\":[{\"departmentid\":0,\"id\":24037,\"level\":4,\"schoolid\":93148,\"year\":2007}],\"email\":\"\",\"exp\":1153,\"fansnum\":58,\"favnum\":0,\"head\":\"http:\\/\\/app.qlogo.cn\\/mbloghead\\/21385fa181c5e2c2ff46\",\"homecity_code\":\"13\",\"homecountry_code\":\"1\",\"homepage\":\"http:\\/\\/t.qq.com\\/zhangzhenli\",\"homeprovince_code\":\"34\",\"hometown_code\":\"\",\"https_head\":\"https:\\/\\/app.qlogo.cn\\/mbloghead\\/21385fa181c5e2c2ff46\",\"idolnum\":47,\"industry_code\":2002,\"introduction\":\"\",\"isent\":0,\"ismyblack\":0,\"ismyfans\":0,\"ismyidol\":0,\"isrealname\":1,\"isvip\":0,\"level\":3,\"location\":\"中国 江苏 南京\",\"mutual_fans_num\":11,\"name\":\"zhangzhenli\",\"nick\":\"霁雨天明\",\"openid\":\"703219695EF721146091513317FACC05\",\"province_code\":\"32\",\"regtime\":1279761288,\"send_private_flag\":2,\"sex\":1,\"tag\":null,\"tweetinfo\":[{\"city_code\":\"1\",\"country_code\":\"1\",\"emotiontype\":0,\"emotionurl\":\"\",\"from\":\"QQ空间分享\",\"fromurl\":\"http:\\/\\/rc.qzone.qq.com\\/share\\/\\n\",\"geo\":\"\",\"id\":\"263736120015752\",\"image\":[\"http:\\/\\/app.qpic.cn\\/mblogpic\\/6d7522661329062f3c70\"],\"latitude\":\"0\",\"location\":\"中国 江苏 南京\",\"longitude\":\"0\",\"music\":null,\"origtext\":\"【PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET 】PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET,我们也可以！ (分享自 @Qzone) http:\\/\\/url.cn\\/9S8tHD \",\"province_code\":\"32\",\"self\":1,\"status\":0,\"text\":\"【PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET 】PaaS正能量：6人团队，仅1人全职后端 支撑6000万用户-CSDN.NET,我们也可以！ (分享自 @Qzone) <a href=\\\"http:\\/\\/url.cn\\/9S8tHD\\\" target=\\\"_blank\\\">http:\\/\\/url.cn\\/9S8tHD<\\/a> \",\"timestamp\":1363352296,\"type\":1,\"video\":null}],\"tweetnum\":142,\"verifyinfo\":\"\"},\"errcode\":0,\"msg\":\"ok\",\"ret\":0,\"seqid\":5866932687917127722,\"trusted_token\":\"fde1bb78dbc270e3e05b065bb6c4d4da\",\"trusted_domain\":\"https:\\/\\/open.t.qq.com\\/api\\/\",\"trusted_uid\":\"703219695EF721146091513317FACC05\",\"TRUSTED_TIME\":1366001716314}');

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
INSERT INTO `trusted_domain` VALUES ('https://open.t.qq.com/api/', '99998', 'appsecret', '4e840fb93d23f3672f354226c8cdf06f');
INSERT INTO `trusted_domain` VALUES ('https://open.t.qq.com/api/', '99998', 'appkey', '801336424');
INSERT INTO `trusted_domain` VALUES ('https://api.weibo.com/2/', '99998', 'AppKey', '2618327280');
INSERT INTO `trusted_domain` VALUES ('https://api.weibo.com/2/', '99998', 'AppSecret', '2420513437649628b6531e1ada864f6a');
