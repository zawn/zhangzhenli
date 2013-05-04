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

package com.huinfo.auth.as.issuer.domain;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public enum TrustedDomainType {

    TencentWeibo("open.t.qq.com"),
    SinaWeibo("api.weibo.com"),
    TencentConnect("connect.qq.com"),
    Huinfo("mm.huinfo.com");
    private String grantType;

    TrustedDomainType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        return grantType;
    }
}
