
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ResourceServer;

public interface ResourceServerMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ResourceServer record);

    int insertSelective(ResourceServer record);

    ResourceServer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceServer record);

    int updateByPrimaryKey(ResourceServer record);
}