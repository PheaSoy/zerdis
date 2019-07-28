package zerdis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import zerdis.core.redis.RedisConfiguration;
import zerdis.core.redis.RedisMessagePublisher;
import zerdis.endpoint.RedisRefreshEndpoint;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisRefreshEndpoint.class,RedisMessagePublisher.class,RedisConfiguration.class})
@Documented
public @interface EnableRedisConfigureServer {
	
}