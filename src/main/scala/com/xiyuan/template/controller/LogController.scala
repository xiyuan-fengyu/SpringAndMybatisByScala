package com.xiyuan.template.controller

import java.util.Date

import com.google.gson.JsonObject
import com.xiyuan.template.util.ResponseUtil
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by YT on 2016/6/30.
  */
@Controller
class LogController {

  private val maxWaitTime = 20000

  val logs: ArrayBuffer[String] = new ArrayBuffer[String]()

  @RequestMapping(value = Array("log"))
  def log(): String = {
    "log/index"
  }

  @RequestMapping(value = Array("log/test"))
  @ResponseBody
  def logTest(): JsonObject = {
    logs += "日志测试：" + new Date()
    val result = ResponseUtil.createJson(true, "测试日志添加成功")
    result
  }

  @RequestMapping(value = Array("log/list"))
  @ResponseBody
  def logList(): JsonObject = {
    var waitTime = 0
    while (waitTime < maxWaitTime && logs.isEmpty) {
      Thread.sleep(100)
      waitTime += 100
    }

    val result = ResponseUtil.createJson(true, "日志获取成功", logs.toArray)
    logs.clear()
    result
  }

}
