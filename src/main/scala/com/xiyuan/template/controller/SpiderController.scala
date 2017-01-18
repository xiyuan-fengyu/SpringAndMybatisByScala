package com.xiyuan.template.controller

import com.xiyuan.template.util.HttpRequest
import org.jsoup.Jsoup
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

import scala.collection.mutable

/**
  * Created by xiyuan_fengyu on 2017/1/18.
  */
@Controller
class SpiderController {

  @RequestMapping(value = Array("spider"))
  def spiderPage(): String = {
    "spider/index"
  }

  @RequestMapping(value = Array("spider/get"))
  @ResponseBody
  def spiderGet(url: String, cookie: String): String = {
//    val request = new HttpRequest(url, "GET")
//    request.setCookie(cookie)
//    val str = request.getString
//    str


    val connection = Jsoup.connect(url)
    if (cookie != null && cookie != "") {
      cookie.split("; ").foreach(item => {
        val keyVal = item.split("=")
        connection.cookie(keyVal(0), keyVal(1))
      })
    }
    val doc = connection.get()
    doc.toString
  }

}
