package com.zhouhong.redistask.redistaskcontroller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 测试Redis定时Controller类
 * @author: zhouhong
 * @version: V1.0.0
 * @date: 2021年3月19日 上午10:59:21
 */

@RestController
public class RedisTaskController {

	@Autowired
	private RedisTemplate< String, String> template;
	Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	/**
	 * 设置定时key，这里key最好使用业务前缀，防止名字相同
	 * @return
	 */
	@RequestMapping(value =  "putkeys", method = RequestMethod.POST)
	public String putRedisTaskKeys() {
		Date date = new Date();
		logger.info("业务开始时间：" + date);
		String key10S = "business1"+"|"+"key10S"+"|"+"其他业务中需要使用到的参数";
		String key20S = "business1"+"|"+"key20S"+"|"+"其他业务中需要使用到的参数";
		template.opsForValue().set(key10S, "values", 10, TimeUnit.SECONDS);
		template.opsForValue().set(key20S, "values", 20, TimeUnit.SECONDS);
		return "RedisKey过期键设置成功";
	}
	
}
