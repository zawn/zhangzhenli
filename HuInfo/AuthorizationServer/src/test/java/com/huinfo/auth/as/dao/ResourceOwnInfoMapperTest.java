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
import com.huinfo.auth.as.model.ResourceOwnInfo;
import com.huinfo.auth.as.model.TrustedDomain;
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
public class ResourceOwnInfoMapperTest {

    public ResourceOwnInfoMapperTest() {
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
     * Test of deleteByPrimaryKey method, of class ResourceOwnInfoMapper.
     */
    public void testDeleteByPrimaryKey() {
        System.out.println("deleteByPrimaryKey");
        Long id = null;
        ResourceOwnInfoMapper instance = new ResourceOwnInfoMapperImpl();
        int expResult = 0;
        int result = instance.deleteByPrimaryKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class ResourceOwnInfoMapper.
     */
    public void testInsert() {
        System.out.println("insert");
        ResourceOwnInfo record = null;
        ResourceOwnInfoMapper instance = new ResourceOwnInfoMapperImpl();
        int expResult = 0;
        int result = instance.insert(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSelective method, of class ResourceOwnInfoMapper.
     */
    public void testInsertSelective() {
        System.out.println("insertSelective");
        ResourceOwnInfo record = null;
        ResourceOwnInfoMapper instance = new ResourceOwnInfoMapperImpl();
        int expResult = 0;
        int result = instance.insertSelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectByPrimaryKey method, of class ResourceOwnInfoMapper.
     */
    public void testSelectByPrimaryKey() {
        System.out.println("selectByPrimaryKey");
        Long id = null;
        ResourceOwnInfoMapper instance = new ResourceOwnInfoMapperImpl();
        ResourceOwnInfo expResult = null;
        ResourceOwnInfo result = instance.selectByPrimaryKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectByUserID method, of class ResourceOwnInfoMapper.
     */
    public void testSelectByUserID() {
        System.out.println("selectByUserID");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            ResourceOwnInfoMapper mapper = sqlSession.getMapper(ResourceOwnInfoMapper.class);
            ResourceOwnInfo list = mapper.selectByUserID("703219695EF721146091513317FACC05");
//            Object[] toArray = list.toArray();
            System.out.println(list.toString());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class
     * ResourceOwnInfoMapper.
     */
    public void testUpdateByPrimaryKeySelective() {
        System.out.println("updateByPrimaryKeySelective");
        ResourceOwnInfo record = null;
        ResourceOwnInfoMapper instance = new ResourceOwnInfoMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKeySelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKey method, of class ResourceOwnInfoMapper.
     */
    public void testUpdateByPrimaryKey() {
        System.out.println("updateByPrimaryKey");
        ResourceOwnInfo record = null;
        ResourceOwnInfoMapper instance = new ResourceOwnInfoMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKey(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ResourceOwnInfoMapperImpl implements ResourceOwnInfoMapper {

        public int deleteByPrimaryKey(Long id) {
            return 0;
        }

        public int insert(ResourceOwnInfo record) {
            return 0;
        }

        public int insertSelective(ResourceOwnInfo record) {
            return 0;
        }

        public ResourceOwnInfo selectByPrimaryKey(Long id) {
            return null;
        }

        public ResourceOwnInfo selectByUserID(String userID) {
            return null;
        }

        public int updateByPrimaryKeySelective(ResourceOwnInfo record) {
            return 0;
        }

        public int updateByPrimaryKey(ResourceOwnInfo record) {
            return 0;
        }
    }
}
