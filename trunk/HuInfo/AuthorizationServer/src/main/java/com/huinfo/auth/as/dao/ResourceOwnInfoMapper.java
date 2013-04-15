
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ResourceOwnInfo;

public interface ResourceOwnInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ResourceOwnInfo record);

    int insertSelective(ResourceOwnInfo record);

    ResourceOwnInfo selectByPrimaryKey(Long id);

    ResourceOwnInfo selectByUserID(String userID);

    int updateByPrimaryKeySelective(ResourceOwnInfo record);

    int updateByPrimaryKey(ResourceOwnInfo record);
}