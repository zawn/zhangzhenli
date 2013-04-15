
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.Client;

public interface ClientMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Long id);

    /**
     * 根据ClientID查询对应的Client记录
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    Client selectByClientID(String clientID);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
}