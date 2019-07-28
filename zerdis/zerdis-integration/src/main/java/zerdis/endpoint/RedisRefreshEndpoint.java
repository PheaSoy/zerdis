package zerdis.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import zerdis.core.redis.RedisMessagePublisher;

@RestController
public class RedisRefreshEndpoint {

	private Logger logger = LoggerFactory.getLogger(RedisRefreshEndpoint.class);

	private final RedisMessagePublisher redisMessagePublisher;
	
	String message = "Refresh has sent successfully.";
	
	public RedisRefreshEndpoint(RedisMessagePublisher redisMessagePublisher, RefreshEndpoint refreshEndpoint) {

		this.redisMessagePublisher = redisMessagePublisher;
	}

	@PostMapping("/redis-refresh")
	public String refresh() {

		try {
			redisMessagePublisher.publish("Refresh properties.");
		} catch (Exception ex) {
			logger.error("Refresh failed cause ", ex);
			message = "Refresh has sent failed.";
		}
		
		return message;

	}

}
