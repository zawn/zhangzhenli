
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ClientRedirecturis;

public interface ClientRedirecturisMapper {

    int deleteByPrimaryKey(Long clientId);

    int insert(ClientRedirecturis record);

    int insertSelective(ClientRedirecturis record);

    ClientRedirecturis selectByPrimaryKey(Long clientId);

    int updateByPrimaryKeySelective(ClientRedirecturis record);

    int updateByPrimaryKey(ClientRedirecturis record);
}