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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.huinfo.auth.as.model.Client;
import com.huinfo.auth.as.model.ClientExample;
import com.huinfo.auth.as.utils.SecretDigest;
import com.huinfo.auth.as.utils.StringTools;
import org.apache.commons.codec.digest.DigestUtils;
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
public class ClientMapperTest {

    public ClientMapperTest() {
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
     * Test of countByExample method, of class ClientMapper.
     */
    @Test
    public void testCountByExample() {
        System.out.println("countByExample");
    }

    /**
     * Test of deleteByExample method, of class ClientMapper.
     */
    @Test
    public void testDeleteByExample() {
        System.out.println("deleteByExample");
    }

    /**
     * Test of deleteByPrimaryKey method, of class ClientMapper.
     */
    @Test
    public void testDeleteByPrimaryKey() {
        System.out.println("deleteByPrimaryKey");
    }

    /**
     * Test of insert method, of class ClientMapper.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
    }

    /**
     * Test of insertSelective method, of class ClientMapper.
     */
    @Test
    public void testInsertSelective() {
        System.out.println("insertSelective");
    }

    /**
     * Test of selectByExample method, of class ClientMapper.
     */
    @Test
    public void testSelectByExample() {
        System.out.println("selectByExample");
    }

    /**
     * Test of selectByPrimaryKey method, of class ClientMapper.
     */
    @Test
    public void testSelectByPrimaryKey()
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("selectByPrimaryKey");
        SqlSession session = DBSessionFactory.getSession();
        ClientMapper mapper = session.getMapper(ClientMapper.class);
        Client selectByPrimaryKey = mapper.selectByPrimaryKey(99998L);
        String sha256Hex = DigestUtils.sha256Hex(selectByPrimaryKey.getSecret());
        String digestClient = SecretDigest.digestClient(selectByPrimaryKey.getSecret());
        System.out.println(digestClient);
        digestClient = SecretDigest.digestClient(selectByPrimaryKey.getSecret());
        System.out.println(digestClient);
        System.out.println(sha256Hex);
        System.out.println(selectByPrimaryKey);
    }

    /**
     * Test of selectByPrimaryKey method, of class ClientMapper.
     */
    @Test
    public void testSelectByClientID()
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("selectByClientID");
        SqlSession session = DBSessionFactory.getSession();
        ClientMapper mapper = session.getMapper(ClientMapper.class);
        Client selectByPrimaryKey = mapper.selectByClientID("MMWall");
        String sha256Hex = DigestUtils.sha256Hex(selectByPrimaryKey.getSecret());
        String digestClient = SecretDigest.digestClient(selectByPrimaryKey.getSecret());
        System.out.println(digestClient);
        digestClient = SecretDigest.digestClient(selectByPrimaryKey.getSecret());
        System.out.println(digestClient);
        System.out.println(sha256Hex);
        System.out.println(selectByPrimaryKey);
    }

    /**
     * Test of updateByExampleSelective method, of class ClientMapper.
     */
    @Test
    public void testUpdateByExampleSelective() {
        System.out.println("updateByExampleSelective");
        SqlSession session = DBSessionFactory.getSession();
        ClientScopesMapper mapper = session.getMapper(ClientScopesMapper.class);
        List<String> selectByClientPrimaryKey = mapper.selectByClientPrimaryKey(99998L);
        Object[] toArray = selectByClientPrimaryKey.toArray();
        System.out.println(Arrays.toString(toArray));

    }

    /**
     * Test of updateByExample method, of class ClientMapper.
     */
    @Test
    public void testUpdateByExample() {
        System.out.println("updateByExample");
    }

    /**
     * Test of updateByPrimaryKeySelective method, of class ClientMapper.
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        System.out.println("updateByPrimaryKeySelective");
    }

    /**
     * Test of updateByPrimaryKey method, of class ClientMapper.
     */
    @Test
    public void testUpdateByPrimaryKey() {
        System.out.println("updateByPrimaryKey");
    }
}
