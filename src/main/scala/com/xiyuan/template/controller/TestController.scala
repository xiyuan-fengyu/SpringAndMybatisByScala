package com.xiyuan.template.controller

import com.google.gson.{Gson, JsonObject}
import com.xiyuan.template.dao.{TbTestDao, TbTestMapper}
import com.xiyuan.template.log.XYLog
import com.xiyuan.template.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
  * Created by YT on 2016/6/30.
  */
@Controller
class TestController {

  @Autowired
  private val testService: TestService = null

  @RequestMapping(value = Array("test"))
  @ResponseBody
  def test(): JsonObject = {
    val result = new JsonObject
    result.addProperty("data", "中文测试")
    result.addProperty("success", true)
    result.addProperty("message", "test")
    result
  }

  @RequestMapping(value = Array("test/string"), produces = Array("text/plain;charset=UTF-8"))
  @ResponseBody
  def testString(): String = {
    "中文测试"
  }

  @RequestMapping(value = Array("test/list"))
  @ResponseBody
  def testList(): JsonObject = {
    val result = new JsonObject
    val maxId = testService.maxId()
    result.add("all", new Gson().toJsonTree(testService.idBetween(0, maxId)))
    result.add("maxId", new Gson().toJsonTree(maxId))
    result.add("idBetween", new Gson().toJsonTree(testService.idBetween(0, 10)))
    result.addProperty("success", true)
    result.addProperty("message", "test")
    result
  }

  /**
    * 测试事务回滚
    *
    * @return
    */
  @RequestMapping(value = Array("test/rollback"))
  @ResponseBody
  def rollback(): JsonObject = {
    val result = new JsonObject

    result.addProperty("before", XYLog.anyToString(testService.find(1)))
    try {
      testService.testRollback(1)
    }
    catch {
      case e: Exception =>
        result.addProperty("exception", e.getMessage)
    }
    result.addProperty("afterRollbackTest", XYLog.anyToString(testService.find(1)))

    result.addProperty("success", true)
    result.addProperty("message", "rollback test")
    result
  }

}
