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

package com.huinfo.auth.as.pojo;

/**
 * 根据rfc6749#section-5.1实现的Token接口
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class BaseAccessToken {

    protected String accessToken;
    protected String tokenType;
    protected Long expiresIn;
    protected String refreshToken;
    protected String scope;

    public BaseAccessToken(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public BaseAccessToken(BaseAccessToken baseAccessToken) {
        this.accessToken = baseAccessToken.getAccessToken();
        this.tokenType = baseAccessToken.getTokenType();
        this.expiresIn = baseAccessToken.getExpiresIn();
        this.refreshToken = baseAccessToken.getRefreshToken();
        this.scope = baseAccessToken.getScope();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public BaseAccessToken setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public BaseAccessToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public BaseAccessToken setScope(String scope) {
        this.scope = scope;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.accessToken != null ? this.accessToken.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseAccessToken other = (BaseAccessToken) obj;
        if ((this.accessToken == null) ? (other.accessToken != null) : !this.accessToken.equals(other.accessToken)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AccessToken{" + "accessToken=" + accessToken + ", tokenType=" + tokenType + ", expiresIn=" + expiresIn + ", refreshToken=" + refreshToken + ", scope=" + scope + '}';
    }
}
