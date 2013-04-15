
package com.huinfo.auth.as.dao;

import java.util.List;

import com.huinfo.auth.as.model.TrustedDomain;

public interface TrustedDomainMapper {

    int insert(TrustedDomain record);

    int insertSelective(TrustedDomain record);

    List<TrustedDomain> selectClientIDandTrustedDomain(TrustedDomain record);
}