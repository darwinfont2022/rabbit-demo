package com.darwinfont.listenerrabbitmq.service.rabbit.input;

import com.darwinfont.listenerrabbitmq.domain.Employee;
import com.darwinfont.listenerrabbitmq.service.app.AppServiceDemo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Consumer;

@Component
@Slf4j
public class Listener {
    @Autowired
    private StreamBridge bridge;
    @Autowired
    AppServiceDemo serviceDemo;

    @Bean
    Consumer<String> input() {
        return list -> {
            log.info("Data : {}" ,list);
            ObjectMapper mapper = new ObjectMapper();
            Employee emp = null;
            try {
                emp = mapper.readValue(list, Employee.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            serviceDemo.addMsg(emp);
        };
    }
}
