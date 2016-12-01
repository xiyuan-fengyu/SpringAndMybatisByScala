package com.xiyuan.template.service

import com.xiyuan.template.dao.CommonDao

/**
  * Created by xiyuan_fengyu on 2016/12/1.
  */
class CommonService [Model] {

  protected def dao: CommonDao[Model] = null

  def deleteByPrimaryKey(id: Integer): Int = {
    dao.deleteByPrimaryKey(id)
  }

  def insert(record: Model): Int = {
    dao.insert(record)
  }

  def insertSelective(record: Model): Int = {
    dao.insertSelective(record)
  }

  def selectByPrimaryKey(id: Integer): Model = {
    dao.selectByPrimaryKey(id)
  }

  def updateByPrimaryKeySelective(record: Model): Int = {
    dao.updateByPrimaryKeySelective(record)
  }

  def updateByPrimaryKey(record: Model): Int = {
    dao.updateByPrimaryKey(record)
  }

}
