package com.xiyuan.template.controller

import org.jsoup.Jsoup
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
  * Created by xiyuan_fengyu on 2017/1/18.
  */
@Controller
class SpiderController {

  @RequestMapping(value = Array("spider"))
  def spiderPage(): String = {
    "spider/index"
  }

  @RequestMapping(value = Array("spider/get"), produces = Array("text/plain;charset=UTF-8"))
  @ResponseBody
  def spiderGet(url: String, cookie: String): String = {
    val tempUrl = "http://api.bilibili.com/x/v2/reply?callback=jQuery17203384123721505703_1484793644928&jsonp=jsonp&pn=1&type=1&oid=8035979&sort=0&_=1484793646486"
    val tempCookie = "sid=ls3u7ah5; fts=1484719906; buvid3=7FAFDFFF-E2FD-442F-AE2A-E311A4AF0EE92765infoc; LIVE_BUVID=e55ce0bfcbb6048644ac24aa3f482898; LIVE_BUVID__ckMd5=d47361713bd05ec3"
    val connection = Jsoup.connect(tempUrl)
    connection.ignoreContentType(true)
    connection.header("Accept", "*/*")
    connection.header("Accept-Encoding", "gzip, deflate, sdch")
    connection.header("Accept-Language", "zh-CN,zh;q=0.8")
    connection.header("Cache-Control", "no-cache")
    connection.header("Connection", "keep-alive")
    connection.header("Cookie", tempCookie)
    connection.header("Host", "api.bilibili.com")
    connection.header("Pragma", "no-cache")
    connection.header("Referer", "http://www.bilibili.com/video/av8035979/")
    connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
    connection.get().toString
  }

}
