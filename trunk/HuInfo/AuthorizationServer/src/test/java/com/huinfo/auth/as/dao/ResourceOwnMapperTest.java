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

import com.huinfo.auth.as.model.ResourceOwn;
import com.huinfo.auth.as.model.ResourceOwnInfo;
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
     * Test of deleteByPrimaryKey method, of class ResourceOwnMapper.
     */
    @Test
    public void testDeleteByPrimaryKey() {
    }

    /**
     * Test of insert method, of class ResourceOwnMapper.
     */
    @Test
    public void testInsert() {
    }

    /**
     * Test of insertSelective method, of class ResourceOwnMapper.
     */
    @Test
    public void testInsertSelective() {
    }

    /**
     * Test of selectByPrimaryKey method, of class ResourceOwnMapper.
     */
    @Test
    public void testSelectByPrimaryKey() {
    }

    @Test
    public void testSelectByUserName() {
        System.out.println("testSelectByUserName");
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            ResourceOwnMapper mapper = sqlSession.getMapper(ResourceOwnMapper.class);
            ResourceOwn list = mapper.selectByUserName("test");
//            Object[] toArray = list.toArray();
            System.out.println(list.toString());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class ResourceOwnMapper.
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
    }

    /**
     * Test of updateByPrimaryKey method, of class ResourceOwnMapper.
     */
    @Test
    public void testUpdateByPrimaryKey() {
    }
}
