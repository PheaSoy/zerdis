package zerdis.core.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {
	
	
	private RefreshEndpoint refreshEndpoint;
	
	private Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);
   
	public RedisMessageSubscriber(RefreshEndpoint refreshEndpoint) {
		this.refreshEndpoint = refreshEndpoint;
	}
	
    public void onMessage(final Message message, final byte[] pattern) {
        //messageList.add(message.toString());
    	logger.info("Got the refresh.");
    	refreshEndpoint.refresh();
        logger.info("Message received:{}",new String(message.getBody()));
    }
}