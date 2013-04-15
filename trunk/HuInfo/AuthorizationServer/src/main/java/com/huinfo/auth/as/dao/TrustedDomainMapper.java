package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.TrustedDomain;
import com.huinfo.auth.as.model.TrustedDomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrustedDomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    int countByExample(TrustedDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    int deleteByExample(TrustedDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    int insert(TrustedDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    int insertSelective(TrustedDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    List<TrustedDomain> selectByExample(TrustedDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    int updateByExampleSelective(@Param("record") TrustedDomain record, @Param("example") TrustedDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    int updateByExample(@Param("record") TrustedDomain record, @Param("example") TrustedDomainExample example);
}