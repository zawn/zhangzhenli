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
import java.util.List;

import com.huinfo.auth.as.model.ResourceOwn;
import com.huinfo.auth.as.model.TrustedDomain;
import com.huinfo.auth.as.utils.SecretDigest;
import org.apache.ibatis.session.SqlSession;
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
public class TrustedDomainMapperTest {
    
    public TrustedDomainMapperTest() {
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
     * Test of insert method, of class TrustedDomainMapper.
     */
    public void testInsert() {
        System.out.println("insert");
        TrustedDomain record = null;
        TrustedDomainMapper instance = new TrustedDomainMapperImpl();
        int expResult = 0;
        int result = instance.insert(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSelective method, of class TrustedDomainMapper.
     */
    public void testInsertSelective() {
        System.out.println("insertSelective");
        TrustedDomain record = null;
        TrustedDomainMapper instance = new TrustedDomainMapperImpl();
        int expResult = 0;
        int result = instance.insertSelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectClientIDandTrustedDomain method, of class
     * TrustedDomainMapper.
     */
    @Test
    public void testSelectClientIDandTrustedDomain() {
        System.out.println("selectClientIDandTrustedDomain");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            TrustedDomainMapper mapper = sqlSession.getMapper(TrustedDomainMapper.class);
            TrustedDomain t = new TrustedDomain();
            t.setClientid(99998L);
            t.setDomain("https://open.t.qq.com/api/");
            List<TrustedDomain> list = mapper.selectClientIDandTrustedDomain(t);
            Object[] toArray = list.toArray();
            System.out.println(Arrays.toString(toArray));
        } finally {
            sqlSession.close();
        }
    }
    
    public class TrustedDomainMapperImpl implements TrustedDomainMapper {
        
        public int insert(TrustedDomain record) {
            return 0;
        }
        
        public int insertSelective(TrustedDomain record) {
            return 0;
        }
        
        public List<TrustedDomain> selectClientIDandTrustedDomain(TrustedDomain record) {
            return null;
        }
    }
}
