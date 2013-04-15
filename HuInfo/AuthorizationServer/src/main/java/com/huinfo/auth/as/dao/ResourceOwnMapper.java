
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ResourceOwn;

public interface ResourceOwnMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ResourceOwn record);

    int insertSelective(ResourceOwn record);

    ResourceOwn selectByPrimaryKey(Long id);

    ResourceOwn selectByUserName(String userName);

    int updateByPrimaryKeySelective(ResourceOwn record);

    int updateByPrimaryKey(ResourceOwn record);
}