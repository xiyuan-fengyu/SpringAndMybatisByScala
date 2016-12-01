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

  /**
  @ RequestMapping(value = Array("test/string"), produces = Array("text/plain;charset=UTF-8"))
  上面的注解只能局部解决问题，要想全局解决问题，可以在applicationContext.xml中添加如下配置，注意bean和annotation-driven的先后顺序不能错
  <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter" >
		<property name="messageConverters">
			<list>
				<bean class="org.springframework.http.converter.StringHttpMessageConverter">
					<property name="supportedMediaTypes">
						<list>
							<value>text/plain;charset=UTF-8</value>
						</list>
					</property>
				</bean>
			</list>
		</property>
	</bean>
	<mvc:annotation-driven />
    */
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

    result.addProperty("before", XYLog.anyToString(testService.selectByPrimaryKey(1)))
    try {
      testService.testRollback(1)
    }
    catch {
      case e: Exception =>
        result.addProperty("exception", e.getMessage)
    }
    result.addProperty("afterRollbackTest", XYLog.anyToString(testService.selectByPrimaryKey(1)))

    result.addProperty("success", true)
    result.addProperty("message", "rollback test")
    result
  }

}
