package com.xiyuan.template.dao;

/**
 * Created by xiyuan_fengyu on 2016/12/1.
 */
public interface CommonDao<Model> {

    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
}
