package zerdis.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import zerdis.core.redis.MessagePublisher;
import zerdis.core.redis.RedisMessagePublisher;

@Configuration
public class RedisPublisherCongiuration {
	
	@Autowired
	RedisTemplate<String,Object> redisTemplate;
    
	@Autowired
    private ChannelTopic topic;
    
	@Bean
    MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate, topic);
    }
	

}
