package com.xiyuan.template.dao;


import com.xiyuan.template.model.TbTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiyuan_fengyu on 2016/8/11.
 */
public interface TbTestDao extends CommonDao<TbTest> {

    List<TbTest> page(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    List<TbTest> idBetween(@Param("start") int start, @Param("end") int end);

    int maxId();

}
