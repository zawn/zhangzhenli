package com.huinfo.auth.as.dao;

import com.huinfo.auth.as.model.ResourceServerScpoes;
import com.huinfo.auth.as.model.ResourceServerScpoesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceServerScpoesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    int countByExample(ResourceServerScpoesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    int deleteByExample(ResourceServerScpoesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    int insert(ResourceServerScpoes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    int insertSelective(ResourceServerScpoes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    List<ResourceServerScpoes> selectByExample(ResourceServerScpoesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    int updateByExampleSelective(@Param("record") ResourceServerScpoes record, @Param("example") ResourceServerScpoesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table resourceserver_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    int updateByExample(@Param("record") ResourceServerScpoes record, @Param("example") ResourceServerScpoesExample example);
}