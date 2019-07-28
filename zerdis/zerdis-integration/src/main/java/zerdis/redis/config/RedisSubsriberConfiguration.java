package zerdis.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import zerdis.core.redis.RedisMessageSubscriber;

@Configuration
public class RedisSubsriberConfiguration {
	
    @Autowired
    private ChannelTopic topic;
    
	@Autowired JedisConnectionFactory jedisConnectionFactory;
	
	@Autowired
	private RefreshEndpoint refreshEndpoint;
	
	
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber(refreshEndpoint));
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(messageListener(), topic);
        return container;
    }


}
