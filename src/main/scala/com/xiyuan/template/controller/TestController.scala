package com.xiyuan.template.controller

import com.google.gson.{Gson, JsonObject}
import com.xiyuan.template.dao.TbTestMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
  * Created by YT on 2016/6/30.
  */
@Controller
class TestController {

  @Autowired
  private val tbTestMappper: TbTestMapper = null

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
    result.add("data", new Gson().toJsonTree(tbTestMappper.selectByExample(null)))
    result.addProperty("success", true)
    result.addProperty("message", "test")
    result
  }

}
