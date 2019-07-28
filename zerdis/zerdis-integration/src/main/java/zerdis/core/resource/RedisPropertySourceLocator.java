package zerdis.core.resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import zerdis.common.RedisPropertyNamePatternProvider;

@Configuration
public class RedisPropertySourceLocator implements PropertySourceLocator {

    String name;
    private static final Logger logger = LoggerFactory.getLogger(RedisPropertySourceLocator.class);

    @Value("${spring.application.name}")
    String application;

    String label="latest";

    @Autowired
    private Environment env;

    @Override
    public PropertySource<?> locate(Environment environment)
    {

        StringRedisTemplate redisUtil = this.getStringRedisTemplate();
        String[] profiles = env.getActiveProfiles();
        String profile = profiles[0];
        Set<String> applicationKeys = new HashSet<>();
        String key = RedisPropertyNamePatternProvider.generateKeyPattern(application, profile, label);
        applicationKeys.addAll(redisUtil.keys(key));
        //CompositePropertySource compositePropertySource = new CompositePropertySource("RedisConfig");

        HashMap<String, Object> properties = new HashMap<>();
        List<String> propertyValues  = redisUtil.opsForValue().multiGet(applicationKeys);
        int i = 0;
        for (String keyPro : applicationKeys) {
            String propertyName = formatKey(application, profile, label, keyPro);
            String propertyValue = propertyValues.get(i);
            properties.put(propertyName, propertyValue);
            i++;
        }
        logger.info("Properties source of application: {} profile: {} label: {} properties:{}",application,profile,label,properties);
        return new MapPropertySource("redis-configure",
                properties);
    }

    @Bean
    StringRedisTemplate getStringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        return stringRedisTemplate;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }
    private String formatKey(String application, String profile, String label, String key) {
        return RedisPropertyNamePatternProvider.formatRedisKeyIntoPropertyName(application, profile, label, key);
    }
}