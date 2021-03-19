package com.zhouhong.redistask.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * description: RedisKey键监听以及业务逻辑处理
 * @author: zhouhong
 * @version: V1.0.0
 * @date: 2021年3月19日 上午10:58:52
 */
@Service
@Component
public class RedisTaskService extends KeyExpirationEventMessageListener {

	Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	/**
	 * @param listenerContainer
	 */
	public RedisTaskService(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}
	@Override
    public void onMessage(Message message, byte[] pattern) {
		String expiredKey = message.toString();	
		// 将拿到的过期键使用之前拼接时的特殊符号分割成字符数组
		String[] expiredKeyArr = expiredKey.split("\\|");
		String businessSign = expiredKeyArr[0].toString();
		String expiredTimeSign = expiredKeyArr[1].toString();
		String othersParm = expiredKeyArr[2].toString();
		
		logger.info(businessSign + expiredTimeSign + othersParm);
		Date date = new Date();
		// 只有本业务才执行以下操作
		if (businessSign.equals("business1")) {
			if (expiredTimeSign.equals("key10S")) {
				// 定时十秒钟后业务处理
				logger.info("十秒钟时的时间："+ date);
				logger.info("定时任务10秒钟已到，下面处理相关业务逻辑代码！！！");
				logger.info("10秒钟后的业务逻辑代码，其他业务参数" + othersParm);
			} else if (expiredTimeSign.equals("key20S")) {
				// 定时十秒钟后业务处理
				logger.info("二十秒钟时的时间："+ date);
				logger.info("定时任务20秒钟已到，下面处理相关业务逻辑代码！！！");
				logger.info("20秒钟后的业务逻辑代码，其他业务参数" + othersParm);
			}
		} else {
			logger.error("非business1业务不做处理");
		}
	}
}
