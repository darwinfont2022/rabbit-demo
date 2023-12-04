package com.darwinfont.demorabbitmq.service.input;

import com.darwinfont.demorabbitmq.domain.Employee;
import com.darwinfont.demorabbitmq.domain.MyCorrelationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

@Component
@Slf4j
public class Listener {
    @Autowired
    private StreamBridge bridge;

//    @Bean
    Consumer<List<String>> input() {
        return list -> {
            List<MyCorrelationData> results = new ArrayList<>();
            list.forEach(str -> {
                log.info("Received: " + str);
                MyCorrelationData corr = new MyCorrelationData(UUID.randomUUID().toString(), str);
                results.add(corr);
                this.bridge.send("output-out-0", MessageBuilder.withPayload(str.toUpperCase())
                        .setHeader(AmqpHeaders.PUBLISH_CONFIRM_CORRELATION, corr)
                        .build());
            });
            results.forEach(correlation -> {
                try {
                    CorrelationData.Confirm confirm = correlation.getFuture().get(10, TimeUnit.SECONDS);
                    log.info(confirm + " for " + correlation.getPayload());
                    if (correlation.getReturned() != null) {
                        log.error("Message for " + correlation.getPayload() + " was returned ");

                        // throw some exception to invoke binder retry/error handling

                    }
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException(e);
                }
                catch (ExecutionException | TimeoutException e) {
                    throw new IllegalStateException(e);
                }
            });
        };
    }
}
