# sample-code
This is an example how you can work with zerdis.
### Quick start

Store your configuration in redis following format:

 SET app-name|profile|label|key value
 * Example:
 * Go to redis server
 ``` redis-cli```
 
  ``` 127.0.0.1:6379> SET redis-configure-client|dev|latest|message "Hello World from configure client1 updated {5}"```
  
  ### configure-client
  * Just need your bootstrap file in your spring-boot application in your client:
  
  ```
  spring:
  redis:
    port: 6379
    host: localhost
  application:
    name: redis-configure-client
  profiles:
    active:
      - dev
```
* Running your configure-client. After the application started you see the log something like:
```
2019-07-29 13:13:41.858  INFO 81235 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2019-07-29 13:13:41.861  INFO 81235 --- [           main] c.e.demo.ConfigureClientApplication      : Started ConfigureClientApplication in 23.562 seconds (JVM running for 24.165)
message:Hello World from configure client1 updated {5}
```
The {message} key has been fetching from the redis when the application bootstrap. It's implementing `PropertySourceLocator`.

* Testing your configure-client
```
curl -X GET http://localhost:8081/greet
```
* You should see the response something like this:
```
Hello World from configure client1 updated {5}
```

### configure-server
The same with configure-client you just need to put the configuration in your bootstrap file like this:
```
spring:
  redis:
    port: 6379
    host: localhost
  application:
    name: redis-configure-server
  profiles:
    active:
      - dev
```
So after you put your `configure-server` bootstrap then you can start your `configure-server`.

### Refresh the configurations all the clients who subscribe the topic by invoke configure-server:

 * Go to redis server and change the value of `configure-client`
 ``` redis-cli```
 
  ``` 127.0.0.1:6379> SET redis-configure-client|dev|latest|message "Hello World from configure client1 updated {6}"```
  
* Invoke the /redis-refresh to `configure-server`

```CURL -X POST http://confifure-server-host:8080/redis_refresh```
* The Response from `configure-server` after refresh should be something like this:
```Refresh has sent successfully.```

* Testing your configure-client again after refresh
```
curl -X GET http://localhost:8081/greet
```
* You should see the response something like this:
```
Hello World from configure client1 updated {6}
```
So you see now the value has changed from {5} -> {6}. 

Please read more [zerdis](https://github.com/PheaSoy/zerdis and feel free to contribute. We need a lot use cases on UI.

