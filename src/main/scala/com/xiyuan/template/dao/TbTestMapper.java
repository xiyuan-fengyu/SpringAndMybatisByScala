package com.xiyuan.template.dao;

import com.xiyuan.template.model.TbTest;

public interface TbTestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbggenerated Fri Sep 23 09:45:50 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbggenerated Fri Sep 23 09:45:50 CST 2016
     */
    int insert(TbTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbggenerated Fri Sep 23 09:45:50 CST 2016
     */
    int insertSelective(TbTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbggenerated Fri Sep 23 09:45:50 CST 2016
     */
    TbTest selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbggenerated Fri Sep 23 09:45:50 CST 2016
     */
    int updateByPrimaryKeySelective(TbTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbggenerated Fri Sep 23 09:45:50 CST 2016
     */
    int updateByPrimaryKey(TbTest record);
}