
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.AccessToken;
import com.huinfo.auth.as.model.AccessTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessTokenMapper {

    int countByExample(AccessTokenExample example);

    int deleteByExample(AccessTokenExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);

    List<AccessToken> selectByExample(AccessTokenExample example);

    AccessToken selectByPrimaryKey(Long id);

    AccessToken selectByRefreshToken(String refreshToken);

    AccessToken selectByAccessToken(String accessToken);

    int updateByExampleSelective(@Param("record") AccessToken record, @Param("example") AccessTokenExample example);

    int updateByExample(@Param("record") AccessToken record, @Param("example") AccessTokenExample example);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByRefreshTokenSelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
}