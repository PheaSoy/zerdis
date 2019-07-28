# zerdis
[Zerdis](https://github.com/PheaSoy/zerdis) is a distributed configuration system. It helps to manage spring boot configuration for propertysource by implementing spring cloud config.
Features include managing the configuration across the services through the Redis NoSQL memory data store by using pub/sub mode.

If you have the configure-server who manage the configuration with connected to redis by publish with specific topic, after that configure-client can subscribe to the topic. You can broadcast all the client or specific client configuration by just call to the configure-server /redis-refresh endpoint.

<img src="https://user-images.githubusercontent.com/16829392/62006692-3d088c00-b16e-11e9-8564-3de740d53af3.png" alt="Architecture" />

## Quick-start
