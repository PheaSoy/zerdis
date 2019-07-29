# sample-code

### Quick start

Store your configuration in redis following format:

 SET app-name|profile|label|key valye
 * Example:
 
  ```SET redis-configure-client|dev|latest|message "Hello World from configure client1 updated {5}"```
  
* Refresh the properties to all the clients who subcribed the topic.

```CURL -X POST http://confifure-server-host:8080/redis_refresh```
