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

import java.util.Date;
import java.util.List;

import com.huinfo.auth.as.model.AccessToken;
import com.huinfo.auth.as.model.AccessTokenExample;
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
     * Test of countByExample method, of class AccessTokenMapper.
     */
    public void testCountByExample() {
        System.out.println("countByExample");
        AccessTokenExample example = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.countByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByExample method, of class AccessTokenMapper.
     */
    public void testDeleteByExample() {
        System.out.println("deleteByExample");
        AccessTokenExample example = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.deleteByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByPrimaryKey method, of class AccessTokenMapper.
     */
    public void testDeleteByPrimaryKey() {
        System.out.println("deleteByPrimaryKey");
        Long id = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.deleteByPrimaryKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of insertSelective method, of class AccessTokenMapper.
     */
    public void testInsertSelective() {
        System.out.println("insertSelective");
        AccessToken record = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.insertSelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of selectByPrimaryKey method, of class AccessTokenMapper.
     */
    public void testSelectByPrimaryKey() {
        System.out.println("selectByPrimaryKey");
        Long id = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        AccessToken expResult = null;
        AccessToken result = instance.selectByPrimaryKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of updateByExample method, of class AccessTokenMapper.
     */
    public void testUpdateByExample() {
        System.out.println("updateByExample");
        AccessToken record = null;
        AccessTokenExample example = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.updateByExample(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class AccessTokenMapper.
     */
    public void testUpdateByPrimaryKeySelective() {
        System.out.println("updateByPrimaryKeySelective");
        AccessToken record = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKeySelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKey method, of class AccessTokenMapper.
     */
    public void testUpdateByPrimaryKey() {
        System.out.println("updateByPrimaryKey");
        AccessToken record = null;
        AccessTokenMapper instance = new AccessTokenMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKey(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AccessTokenMapperImpl implements AccessTokenMapper {

        public int countByExample(AccessTokenExample example) {
            return 0;
        }

        public int deleteByExample(AccessTokenExample example) {
            return 0;
        }

        public int deleteByPrimaryKey(Long id) {
            return 0;
        }

        public int insert(AccessToken record) {
            return 0;
        }

        public int insertSelective(AccessToken record) {
            return 0;
        }

        public List<AccessToken> selectByExample(AccessTokenExample example) {
            return null;
        }

        public AccessToken selectByPrimaryKey(Long id) {
            return null;
        }

        public int updateByExampleSelective(AccessToken record, AccessTokenExample example) {
            return 0;
        }

        public int updateByExample(AccessToken record, AccessTokenExample example) {
            return 0;
        }

        public int updateByPrimaryKeySelective(AccessToken record) {
            return 0;
        }

        public int updateByPrimaryKey(AccessToken record) {
            return 0;
        }

        @Override
        public AccessToken selectByRefreshToken(String refreshToken) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public AccessToken selectByAccessToken(String accessToken) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int updateByRefreshTokenSelective(AccessToken record) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
