
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ClientAttributes;

public interface ClientAttributesMapper {

    int deleteByPrimaryKey(Long clientId);

    int insert(ClientAttributes record);

    int insertSelective(ClientAttributes record);

    ClientAttributes selectByPrimaryKey(Long clientId);

    int updateByPrimaryKeySelective(ClientAttributes record);

    int updateByPrimaryKey(ClientAttributes record);
}