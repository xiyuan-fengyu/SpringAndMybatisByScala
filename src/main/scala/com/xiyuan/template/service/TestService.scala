package com.xiyuan.template.service

import com.xiyuan.template.dao.{CommonDao, TbTestDao}
import com.xiyuan.template.model.TbTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
  * Created by xiyuan_fengyu on 2016/10/20.
  */
@Service
@Transactional(rollbackFor = Array(classOf[Exception]))
class TestService extends CommonService[TbTest]{

  @Autowired
  private val testDao: TbTestDao = null

  override protected def dao: CommonDao[TbTest] = testDao

  def idBetween(start: Int, end: Int): Array[TbTest] = {
    testDao.idBetween(start, end).toArray().map(_.asInstanceOf[TbTest])
  }

  def maxId(): Int = {
    testDao.maxId()
  }

  def page(pageNum: Int, pageSize: Int): Array[TbTest] = {
    testDao.page(pageNum, pageSize).toArray().map(_.asInstanceOf[TbTest])
  }

  def testRollback(id: Int): Unit = {
    val item = testDao.selectByPrimaryKey(id)
    item.setName("newNameForRollbackTest")
    item.setContent("newContentForRollbackTest")
    testDao.updateByPrimaryKey(item)

    //注意必须添加@Transactional(rollbackFor = Array(classOf[Exception]))注解才有效

//    //主动抛出异常可以使事务回滚
//    throw new Exception("An exception for rollback!")

    //运行时异常也可以使事务回滚
    val i = Integer.parseInt("fsdjk2342354dsf")

//    //如果异常被捕获，没有向上抛出，则无法使事务回滚
//    try {
//      val i = Integer.parseInt("fsdjk2342354dsf")
//    }
//    catch {
//      case e: Exception =>
//        e.printStackTrace()
//    }

  }

}
