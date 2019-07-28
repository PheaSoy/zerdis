package zerdis.core.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {
	
	private final Logger logger = LoggerFactory.getLogger(RedisMessagePublisher.class);
	
    
	@Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private ChannelTopic topic;

    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final String message) {
    	logger.info("Sending the message: {} with topic: {}",message,topic.getTopic());
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
    
}