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

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import com.huinfo.auth.as.model.ResourceOwn;
import com.huinfo.auth.as.model.ResourceOwnExample;
import com.huinfo.auth.as.model.TrustedDomain;
import com.huinfo.auth.as.model.TrustedDomainExample;
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
     * Test of countByExample method, of class TrustedDomainMapper.
     */
    public void testCountByExample() {
        System.out.println("countByExample");
        TrustedDomainExample example = null;
        TrustedDomainMapper instance = new TrustedDomainMapperImpl();
        int expResult = 0;
        int result = instance.countByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByExample method, of class TrustedDomainMapper.
     */
    public void testDeleteByExample() {
        System.out.println("deleteByExample");
        TrustedDomainExample example = null;
        TrustedDomainMapper instance = new TrustedDomainMapperImpl();
        int expResult = 0;
        int result = instance.deleteByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of selectByExample method, of class TrustedDomainMapper.
     */
    @Test
    public void testSelectByExample() {
        System.out.println("selectByExample");
        List expResult = null;
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            TrustedDomainExample example = new TrustedDomainExample();
            example.createCriteria().andClientidEqualTo(99998L).andDomainEqualTo("https://open.t.qq.com/api/");
            TrustedDomainMapper instance = sqlSession.getMapper(TrustedDomainMapper.class);
            List result = instance.selectByExample(example);
//            ResourceOwnExample condition = new ResourceOwnExample();
//            condition.createCriteria().andUsernameEqualTo("test");
//            List<ResourceOwn> list = mapper.selectByExample(condition);
//            String digestClient = SecretDigest.digestClient("test");
//            System.out.println(digestClient);
            Object[] toArray = result.toArray();
            System.out.println(Arrays.toString(toArray));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of updateByExampleSelective method, of class TrustedDomainMapper.
     */
    public void testUpdateByExampleSelective() {
        System.out.println("updateByExampleSelective");
        TrustedDomain record = null;
        TrustedDomainExample example = null;
        TrustedDomainMapper instance = new TrustedDomainMapperImpl();
        int expResult = 0;
        int result = instance.updateByExampleSelective(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByExample method, of class TrustedDomainMapper.
     */
    public void testUpdateByExample() {
        System.out.println("updateByExample");
        TrustedDomain record = null;
        TrustedDomainExample example = null;
        TrustedDomainMapper instance = new TrustedDomainMapperImpl();
        int expResult = 0;
        int result = instance.updateByExample(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TrustedDomainMapperImpl implements TrustedDomainMapper {

        public int countByExample(TrustedDomainExample example) {
            return 0;
        }

        public int deleteByExample(TrustedDomainExample example) {
            return 0;
        }

        public int insert(TrustedDomain record) {
            return 0;
        }

        public int insertSelective(TrustedDomain record) {
            return 0;
        }

        public List<TrustedDomain> selectByExample(TrustedDomainExample example) {
            return null;
        }

        public int updateByExampleSelective(TrustedDomain record, TrustedDomainExample example) {
            return 0;
        }

        public int updateByExample(TrustedDomain record, TrustedDomainExample example) {
            return 0;
        }
    }
}
