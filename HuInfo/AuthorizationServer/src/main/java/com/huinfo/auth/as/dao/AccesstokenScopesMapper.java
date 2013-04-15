
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.AccesstokenScopes;

public interface AccesstokenScopesMapper {

    int deleteByPrimaryKey(Long accesstokenId);

    int insert(AccesstokenScopes record);

    int insertSelective(AccesstokenScopes record);

    AccesstokenScopes selectByPrimaryKey(Long accesstokenId);

    int updateByPrimaryKeySelective(AccesstokenScopes record);

    int updateByPrimaryKey(AccesstokenScopes record);
}