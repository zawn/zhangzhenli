/*
 * Copyright 2013 ZhangZhenli <zhangzhenli@live.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huinfo.auth.as.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.huinfo.auth.as.model.AccessToken;
import com.huinfo.auth.as.pojo.BaseAccessToken;
import org.apache.ibatis.session.SqlSession;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.TokenType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class AccessTokenMapperTest {

    public AccessTokenMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class AccessTokenMapper.
     */
    @Test
    public void testInsert()
            throws OAuthSystemException {
        System.out.println("insert");
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        BaseAccessToken at = new BaseAccessToken(oauthIssuerImpl.accessToken(), TokenType.BEARER.toString());
        at.setExpiresIn(302400L);
        at.setScope("zhangzhenli");
        AccessToken record = new AccessToken(at, null, 99998L, GrantType.CLIENT_CREDENTIALS.toString());
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            mapper.insert(record);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        System.out.println(record);
    }

    /**
     * Test of selectByExample method, of class AccessTokenMapper.
     */
    @Test
    public void testSelectByAccessToken() {
        System.out.println("testSelectByAccessToken");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            AccessToken record = mapper.selectByAccessToken("9e5460ba34ebcc35efc613383eb3b1ac");
            System.out.println(record);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of selectByExample method, of class AccessTokenMapper.
     */
    @Test
    public void testSelectByRefreshToken() {
        System.out.println("testSelectByAccessToken");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            AccessToken record = mapper.selectByRefreshToken("a0de0802d223852ce3753a5d02bda2");
            System.out.println(record);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of updateByExampleSelective method, of class AccessTokenMapper.
     */
    public void testUpdateByRefreshTokenSelective() {
        System.out.println("testUpdateByRefreshTokenSelective");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            AccessToken record = mapper.selectByRefreshToken("a0dea90802d223852ce3753a5d02bda2");
            System.out.println(record);
            record.setModificationdate(new Date());
            record.setAccessToken("zhangzhenli");
            System.out.println(record);
            int updateByRefreshTokenSelective = mapper.updateByRefreshTokenSelective(record);
            System.out.println(updateByRefreshTokenSelective);
            record = mapper.selectByRefreshToken("a0dea90802d223852ce3753a5d02bda2");
            System.out.println(record);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of deleteByPrimaryKey method, of class AccessTokenMapper.
     */
    @Test
    public void testDeleteByPrimaryKey() {
    }

    /**
     * Test of insertSelective method, of class AccessTokenMapper.
     */
    @Test
    public void testInsertSelective() {
    }

    /**
     * Test of selectByPrimaryKey method, of class AccessTokenMapper.
     */
    @Test
    public void testSelectByPrimaryKey() {
    }

    /**
     * Test of selectByClientId method, of class AccessTokenMapper.
     */
    @Test
    public void testSelectByClientId() {
        System.out.println("testSelectByClientId");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            List<AccessToken> record = mapper.selectByClientId(99998L);
            System.out.println(record.toString());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of selectByClientIdandUserId method, of class AccessTokenMapper.
     */
    @Test
    public void testSelectByClientIdandUserId() {
        System.out.println("testSelectByClientIdandUserId");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            AccessToken at = new AccessToken();
            at.setClientId(99998L);
            at.setResourceownerid("test");
            List<AccessToken> record = mapper.selectByClientIdandUserId(at);
            System.out.println(record.toString());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class AccessTokenMapper.
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
    }
}
