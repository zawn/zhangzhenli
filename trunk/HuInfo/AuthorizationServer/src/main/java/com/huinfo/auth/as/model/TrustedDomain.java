
package com.huinfo.auth.as.model;

public class TrustedDomain {

    private String domain;
    private Long clientid;
    private String attributeName;
    private String attributeValue;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public Long getClientid() {
        return clientid;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName == null ? null : attributeName.trim();
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue == null ? null : attributeValue.trim();
    }

    @Override
    public String toString() {
        return "TrustedDomain{" + "domain=" + domain + ", clientid=" + clientid + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue + '}';
    }
}