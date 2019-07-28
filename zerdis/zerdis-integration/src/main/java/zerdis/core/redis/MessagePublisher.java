package zerdis.core.redis;

public interface MessagePublisher {

    void publish(final String message);
}