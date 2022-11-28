package io.bertty.sharable.backend.persistence.config;

import io.bertty.sharable.backend.persistence.model.Memory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan("io.bertty.sharable.backend")
@EnableRedisRepositories(basePackages = "io.bertty.sharable.backend")
@PropertySource("classpath:application.properties")
public class RedisConfig {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<String, Memory> redisTemplate() {
    final RedisTemplate<String, Memory> template = new RedisTemplate<String, Memory>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericToStringSerializer<Memory>(Memory.class));
    return template;
  }

//  @Bean
//  MessageListenerAdapter messageListener() {
//    return new MessageListenerAdapter(new RedisMessageSubscriber());
//  }
//
//  @Bean
//  RedisMessageListenerContainer redisContainer() {
//    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//    container.setConnectionFactory(jedisConnectionFactory());
//    container.addMessageListener(messageListener(), topic());
//    return container;
//  }
//
//  @Bean
//  MessagePublisher redisPublisher() {
//    return new RedisMessagePublisher(redisTemplate(), topic());
//  }
//
//  @Bean
//  ChannelTopic topic() {
//    return new ChannelTopic("pubsub:queue");
//  }
}
