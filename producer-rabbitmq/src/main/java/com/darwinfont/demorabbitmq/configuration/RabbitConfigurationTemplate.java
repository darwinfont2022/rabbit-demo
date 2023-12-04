package com.darwinfont.demorabbitmq.configuration;

import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfigurationTemplate {
//    @Bean
//    public BatchingRabbitTemplate bashTemplate(CachingConnectionFactory cf, TaskScheduler taskScheduler) {
//        BatchingStrategy batchingStrategy = new SimpleBatchingStrategy(10, 1000000, 1000);
//        return new BatchingRabbitTemplate(cf, batchingStrategy, taskScheduler);
//    }

    @Bean
    public RabbitTemplate simpleTemplate(CachingConnectionFactory cf) {
        return new RabbitTemplate(cf);
    }
}
