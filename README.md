# zerdis
[Zerdis](https://github.com/PheaSoy/zerdis) is a Distributed externalise configuration  with Spring Cloud + Redis. It helps to manage spring boot configuration for propertysource by implementing spring cloud config.
Features include managing the configuration across the services through the Redis NoSQL memory data store by using pub/sub mode.

If you have the configure-server who manage the configuration with connected to redis by publish with specific topic, after that configure-client can subscribe to the topic. You can broadcast all the client or specific client configuration by just call to the configure-server /redis-refresh endpoint.

<img src="https://user-images.githubusercontent.com/16829392/62006692-3d088c00-b16e-11e9-8564-3de740d53af3.png" alt="Architecture" />

## Quick-start

Adding depedency to your application:

```
  <dependency>  
    <groupId>io.zerdis</groupId>
    <artifactId>zerdis-integration</artifactId>
    <version>${version}</version>
  </dependency>
```

### Configure Server
Adding `@EnableRedisConfigureServer` to your application.
```
@SpringBootApplication
@EnableRedisConfigureServer
public class CofigureServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CofigureServerApplication.class, args);
  }
}
```
You also need to add the `boostrap.yaml` in your spring boot application.
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
We need to put the redis configuration like port, host and password if required.Because on the bootstrap both configure-server and configure-client need to fetch the configuration from redis and publish for the configure-server and subscribe for configure-client.

By default configure-server is exposing the `/redis-refresh` endpoint. It's sending the message `Refresh properties.` to the default topic `spring-redis-propertysource`. if you want to change the default topic. You can set in `.properties` file
`configure.redis.topic=my-spring-boot-redis-topic`.


### Configure Client
Like Configure Server, you just `@EnableRedisConfigureClient` to your spring boot application.
```
@SpringBootApplication
@EnableRedisConfigureClient
public class ConfigureClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigureClientApplication.class, args);
  }
 }
```
You also need to add the `boostrap.yaml` in your spring boot application. 
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
For the queue **topic** is the same `Configure Server`. If you want to change the topic name fo Configure Server or Configure Client they are pair and must be the same.

More understand you can download the project [sample-code](https://github.com/PheaSoy/zerdis/tree/master/sample-code).
We're welcome you to contribute more features like UI authentication, history,etc.
