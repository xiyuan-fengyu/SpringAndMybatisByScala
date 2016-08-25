package com.xiyuan.template.util

import com.google.gson.{Gson, JsonObject}
import com.xiyuan.template.model.TbTest

import scala.collection.mutable

/**
  * Created by xiyuan_fengyu on 2016/8/25.
  */
object ResponceUtil {

  private val kData = "data"

  private val kSuccess = "success"

  private val kMessage = "message"

  private val kTimestamp = "timestamp"

  private val gson = new Gson()

  def createJson[T](data: T, success: Boolean, msg: String): JsonObject = {
    val result = new JsonObject
    result.addProperty(kSuccess, success)
    result.addProperty(kMessage, msg)
    result.addProperty(kTimestamp, System.currentTimeMillis())

    try {
      data match {
        case n: Number =>
          result.addProperty(kData, n)
        case c: Character =>
          result.addProperty(kData, c)
        case b: Boolean =>
          result.addProperty(kData, b)
        case s: String =>
          result.addProperty(kData, s)
        case _ =>
          result.add(kData, gson.toJsonTree(data))
      }
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
    }

    result
  }

  def main(args: Array[String]) {
    val tbTest = new TbTest
    tbTest.setId(123.toLong)
    tbTest.setName("123")
    tbTest.setContent("123")
    println(createJson(tbTest, true, "好啊"))
  }

}
