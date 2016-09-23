package com.xiyuan.template.controller

import com.google.gson.{Gson, JsonObject}
import com.xiyuan.template.dao.{TbTestDao, TbTestMapper}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
  * Created by YT on 2016/6/30.
  */
@Controller
class TestController {

  @Autowired
  private val tbTestDao: TbTestDao = null

  @RequestMapping(value = Array("test"))
  @ResponseBody
  def test(): JsonObject = {
    val result = new JsonObject
    result.addProperty("success", true)
    result.addProperty("message", "test")
    result
  }

  @RequestMapping(value = Array("test/list"))
  @ResponseBody
  def testList(): JsonObject = {
    val result = new JsonObject
    val maxId = tbTestDao.maxId()
    result.add("all", new Gson().toJsonTree(tbTestDao.idBetween(0, maxId)))
    result.add("maxId", new Gson().toJsonTree(maxId))
    result.add("idBetween", new Gson().toJsonTree(tbTestDao.idBetween(0, 10)))
    result.addProperty("success", true)
    result.addProperty("message", "test")
    result
  }

}
