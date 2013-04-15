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
import java.util.HashSet;
import java.util.List;

import com.huinfo.auth.as.model.Client;
import com.huinfo.auth.as.model.ResourceOwn;
import com.huinfo.auth.as.model.ResourceOwnExample;
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
public class ResourceOwnMapperTest {

    public ResourceOwnMapperTest() {
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
     * Test of countByExample method, of class ResourceOwnMapper.
     */
    public void testCountByExample() {
        System.out.println("countByExample");
        ResourceOwnExample example = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.countByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByExample method, of class ResourceOwnMapper.
     */
    public void testDeleteByExample() {
        System.out.println("deleteByExample");
        ResourceOwnExample example = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.deleteByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByPrimaryKey method, of class ResourceOwnMapper.
     */
    public void testDeleteByPrimaryKey() {
        System.out.println("deleteByPrimaryKey");
        Long id = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.deleteByPrimaryKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class ResourceOwnMapper.
     */
    public void testInsert() {
        System.out.println("insert");
        ResourceOwn record = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.insert(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSelective method, of class ResourceOwnMapper.
     */
    public void testInsertSelective() {
        System.out.println("insertSelective");
        ResourceOwn record = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.insertSelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectByExample method, of class ResourceOwnMapper.
     */
    @Test
    public void testSelectByExample() {
        System.out.println("selectByExample");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            ResourceOwnMapper mapper = sqlSession.getMapper(ResourceOwnMapper.class);
            ResourceOwnExample condition = new ResourceOwnExample();
            condition.createCriteria().andUsernameEqualTo("test");
            List<ResourceOwn> list = mapper.selectByExample(condition);
            String digestClient = SecretDigest.digestClient("test");
            System.out.println(digestClient);
            Object[] toArray = list.toArray();
            System.out.println(Arrays.toString(toArray));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of selectByPrimaryKey method, of class ResourceOwnMapper.
     */
    public void testSelectByPrimaryKey() {
        System.out.println("selectByPrimaryKey");
        Long id = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        ResourceOwn expResult = null;
        ResourceOwn result = instance.selectByPrimaryKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByExampleSelective method, of class ResourceOwnMapper.
     */
    public void testUpdateByExampleSelective() {
        System.out.println("updateByExampleSelective");
        ResourceOwn record = null;
        ResourceOwnExample example = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.updateByExampleSelective(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByExample method, of class ResourceOwnMapper.
     */
    public void testUpdateByExample() {
        System.out.println("updateByExample");
        ResourceOwn record = null;
        ResourceOwnExample example = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.updateByExample(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class ResourceOwnMapper.
     */
    public void testUpdateByPrimaryKeySelective() {
        System.out.println("updateByPrimaryKeySelective");
        ResourceOwn record = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKeySelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKey method, of class ResourceOwnMapper.
     */
    public void testUpdateByPrimaryKey() {
        System.out.println("updateByPrimaryKey");
        ResourceOwn record = null;
        ResourceOwnMapper instance = new ResourceOwnMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKey(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ResourceOwnMapperImpl implements ResourceOwnMapper {

        public int countByExample(ResourceOwnExample example) {
            return 0;
        }

        public int deleteByExample(ResourceOwnExample example) {
            return 0;
        }

        public int deleteByPrimaryKey(Long id) {
            return 0;
        }

        public int insert(ResourceOwn record) {
            return 0;
        }

        public int insertSelective(ResourceOwn record) {
            return 0;
        }

        public List<ResourceOwn> selectByExample(ResourceOwnExample example) {
            return null;
        }

        public ResourceOwn selectByPrimaryKey(Long id) {
            return null;
        }

        public int updateByExampleSelective(ResourceOwn record, ResourceOwnExample example) {
            return 0;
        }

        public int updateByExample(ResourceOwn record, ResourceOwnExample example) {
            return 0;
        }

        public int updateByPrimaryKeySelective(ResourceOwn record) {
            return 0;
        }

        public int updateByPrimaryKey(ResourceOwn record) {
            return 0;
        }
    }
}
