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

package com.huinfo.auth.as.utils;

import java.io.IOException;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class SMSHelperTest {

    public SMSHelperTest() {
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
     * Test of getConnectTimeOut method, of class SMSHelper.
     */
    @Test
    public void testGetConnectTimeOut() {
    }

    /**
     * Test of setConnectTimeOut method, of class SMSHelper.
     */
    @Test
    public void testSetConnectTimeOut() {
    }

    /**
     * Test of sendSMSCocde method, of class SMSHelper.
     */
    @Test
    public void testSendSMSCocde()
            throws IOException {
        String[] contentTemplate = SMSHelper.getContentTemplate();
        System.out.println(Arrays.toString(contentTemplate));
    }
}
