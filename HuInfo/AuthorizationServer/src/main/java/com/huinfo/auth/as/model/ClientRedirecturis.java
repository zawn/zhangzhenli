
package com.huinfo.auth.as.model;

public class ClientRedirecturis {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column
     * client_redirecturis.CLIENT_ID
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    private Long clientId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client_redirecturis.element
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    private String element;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column
     * client_redirecturis.CLIENT_ID
     *
     * @return the value of client_redirecturis.CLIENT_ID
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column
     * client_redirecturis.CLIENT_ID
     *
     * @param clientId the value for client_redirecturis.CLIENT_ID
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column
     * client_redirecturis.element
     *
     * @return the value of client_redirecturis.element
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public String getElement() {
        return element;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column
     * client_redirecturis.element
     *
     * @param element the value for client_redirecturis.element
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void setElement(String element) {
        this.element = element == null ? null : element.trim();
    }
}