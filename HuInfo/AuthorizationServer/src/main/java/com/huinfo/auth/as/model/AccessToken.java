
package com.huinfo.auth.as.model;

import java.util.Date;

import com.huinfo.auth.as.pojo.BaseAccessToken;

public class AccessToken extends BaseAccessToken {

    public AccessToken() {
        super(null, null);
    }

    public AccessToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public AccessToken(String accessToken, String tokenType) {
        super(accessToken, tokenType);
    }

    public AccesstokenScopes getScopes() {
        AccesstokenScopes scopes = new AccesstokenScopes();
        scopes.setAccesstokenId(this.id);
        scopes.setElement(scope);
        return scopes;
    }

    public AccessToken(BaseAccessToken baseAccessToken, String resourceownerid, Long clientId) {
        super(baseAccessToken);
        this.creationdate = new Date();
        this.modificationdate = this.creationdate;
        this.resourceownerid = resourceownerid;
        this.clientId = clientId;
    }

    public AccessToken(BaseAccessToken baseAccessToken, String resourceownerid, Long clientId, String grantType) {
        this(baseAccessToken, resourceownerid, clientId);
        this.grantType = grantType;
    }
    private Long id;
    private Date creationdate;
    private Date modificationdate;
    private String resourceownerid;
    private Long clientId;
    private String grantType;
    private String preRefreshToken;

    public String getPreRefreshToken() {
        return preRefreshToken;
    }

    public void setPreRefreshToken(String preRefreshToken) {
        this.preRefreshToken = preRefreshToken;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(Date modificationdate) {
        this.modificationdate = modificationdate;
    }

    public String getResourceownerid() {
        return resourceownerid;
    }

    public void setResourceownerid(String resourceownerid) {
        this.resourceownerid = resourceownerid == null ? null : resourceownerid.trim();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {

        return super.toString() + ", id=" + id + ", creationdate=" + creationdate + ", modificationdate=" + modificationdate + ", resourceownerid=" + resourceownerid + ", clientId=" + clientId + '}';
    }
}