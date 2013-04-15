
package com.huinfo.auth.as.dao;

import java.util.List;
import com.huinfo.auth.as.model.AccessToken;

public interface AccessTokenMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);

    AccessToken selectByPrimaryKey(Long id);

    AccessToken selectByRefreshToken(String refreshToken);

    AccessToken selectByAccessToken(String accessToken);

    List<AccessToken> selectByClientId(String clientId);

    List<AccessToken> selectByClientIdandUserId(AccessToken accessToken);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByRefreshTokenSelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
}