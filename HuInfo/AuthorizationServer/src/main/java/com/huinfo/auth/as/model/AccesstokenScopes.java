
package com.huinfo.auth.as.model;

public class AccesstokenScopes {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column
     * accesstoken_scopes.ACCESSTOKEN_ID
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    private Long accesstokenId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accesstoken_scopes.element
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    private String element;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column
     * accesstoken_scopes.ACCESSTOKEN_ID
     *
     * @return the value of accesstoken_scopes.ACCESSTOKEN_ID
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public Long getAccesstokenId() {
        return accesstokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column
     * accesstoken_scopes.ACCESSTOKEN_ID
     *
     * @param accesstokenId the value for accesstoken_scopes.ACCESSTOKEN_ID
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void setAccesstokenId(Long accesstokenId) {
        this.accesstokenId = accesstokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column
     * accesstoken_scopes.element
     *
     * @return the value of accesstoken_scopes.element
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public String getElement() {
        return element;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column
     * accesstoken_scopes.element
     *
     * @param element the value for accesstoken_scopes.element
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void setElement(String element) {
        this.element = element == null ? null : element.trim();
    }
}