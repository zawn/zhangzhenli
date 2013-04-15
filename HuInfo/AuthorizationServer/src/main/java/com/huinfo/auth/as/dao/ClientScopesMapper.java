
package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ClientScopes;
import java.util.List;

public interface ClientScopesMapper {

    int insert(ClientScopes record);

    int insertSelective(ClientScopes record);

    /**
     * 查询客户端对应的访问范围
     *
     * @param clientPrimaryKey
     * @return
     */
    List<String> selectByClientPrimaryKey(Long clientPrimaryKey);
}
