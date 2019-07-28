package zerdis.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfiguration {
	
	@Autowired JedisConnectionFactory jedisConnectionFactory;
	
	@Value("${configure.redis.topic:spring-redis-propertysource}")
	private String topic;
	
	@Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
	
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(topic);
    }
}
