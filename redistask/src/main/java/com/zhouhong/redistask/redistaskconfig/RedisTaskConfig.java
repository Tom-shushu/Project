package com.zhouhong.redistask.redistaskconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * description: Redis配置类
 * @author: zhouhong
 * @version: V1.0.0
 * @date: 2021年3月19日 上午10:58:24
 */
@Configuration
public class RedisTaskConfig {
	@Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
