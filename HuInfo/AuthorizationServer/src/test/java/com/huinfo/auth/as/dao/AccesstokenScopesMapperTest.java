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

import java.util.List;

import com.huinfo.auth.as.model.AccesstokenScopes;
import com.huinfo.auth.as.model.AccesstokenScopesExample;
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
public class AccesstokenScopesMapperTest {
    
    public AccesstokenScopesMapperTest() {
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
     * Test of countByExample method, of class AccesstokenScopesMapper.
     */
    
    public void testCountByExample() {
        System.out.println("countByExample");
        AccesstokenScopesExample example = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.countByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByExample method, of class AccesstokenScopesMapper.
     */
    
    public void testDeleteByExample() {
        System.out.println("deleteByExample");
        AccesstokenScopesExample example = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.deleteByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByPrimaryKey method, of class AccesstokenScopesMapper.
     */
    
    public void testDeleteByPrimaryKey() {
        System.out.println("deleteByPrimaryKey");
        Long accesstokenId = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.deleteByPrimaryKey(accesstokenId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class AccesstokenScopesMapper.
     */
    
    public void testInsert() {
        System.out.println("insert");
        AccesstokenScopes record = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.insert(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSelective method, of class AccesstokenScopesMapper.
     */
    
    public void testInsertSelective() {
        System.out.println("insertSelective");
        AccesstokenScopes record = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.insertSelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectByExample method, of class AccesstokenScopesMapper.
     */
    
    public void testSelectByExample() {
        System.out.println("selectByExample");
        AccesstokenScopesExample example = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        List expResult = null;
        List result = instance.selectByExample(example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectByPrimaryKey method, of class AccesstokenScopesMapper.
     */
    
    public void testSelectByPrimaryKey() {
        System.out.println("selectByPrimaryKey");
        Long accesstokenId = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        AccesstokenScopes expResult = null;
        AccesstokenScopes result = instance.selectByPrimaryKey(accesstokenId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByExampleSelective method, of class AccesstokenScopesMapper.
     */
    
    public void testUpdateByExampleSelective() {
        System.out.println("updateByExampleSelective");
        AccesstokenScopes record = null;
        AccesstokenScopesExample example = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.updateByExampleSelective(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByExample method, of class AccesstokenScopesMapper.
     */
    
    public void testUpdateByExample() {
        System.out.println("updateByExample");
        AccesstokenScopes record = null;
        AccesstokenScopesExample example = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.updateByExample(record, example);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class AccesstokenScopesMapper.
     */
    
    public void testUpdateByPrimaryKeySelective() {
        System.out.println("updateByPrimaryKeySelective");
        AccesstokenScopes record = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKeySelective(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateByPrimaryKey method, of class AccesstokenScopesMapper.
     */
    
    public void testUpdateByPrimaryKey() {
        System.out.println("updateByPrimaryKey");
        AccesstokenScopes record = null;
        AccesstokenScopesMapper instance = new AccesstokenScopesMapperImpl();
        int expResult = 0;
        int result = instance.updateByPrimaryKey(record);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AccesstokenScopesMapperImpl implements AccesstokenScopesMapper {

        public int countByExample(AccesstokenScopesExample example) {
            return 0;
        }

        public int deleteByExample(AccesstokenScopesExample example) {
            return 0;
        }

        public int deleteByPrimaryKey(Long accesstokenId) {
            return 0;
        }

        public int insert(AccesstokenScopes record) {
            return 0;
        }

        public int insertSelective(AccesstokenScopes record) {
            return 0;
        }

        public List<AccesstokenScopes> selectByExample(AccesstokenScopesExample example) {
            return null;
        }

        public AccesstokenScopes selectByPrimaryKey(Long accesstokenId) {
            return null;
        }

        public int updateByExampleSelective(AccesstokenScopes record, AccesstokenScopesExample example) {
            return 0;
        }

        public int updateByExample(AccesstokenScopes record, AccesstokenScopesExample example) {
            return 0;
        }

        public int updateByPrimaryKeySelective(AccesstokenScopes record) {
            return 0;
        }

        public int updateByPrimaryKey(AccesstokenScopes record) {
            return 0;
        }
    }
}
