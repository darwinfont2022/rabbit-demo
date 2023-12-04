package com.darwinfont.demorabbitmq.service.output;

import com.darwinfont.demorabbitmq.domain.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProducerImp implements Producer {
    private final RabbitTemplate template;
    private final ObjectMapper mapper;

    public ProducerImp(RabbitTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }
    @Override
    public void sendMessage(Employee employeeDto) {
        String emp = null;
        try {
            emp = mapper.writeValueAsString(employeeDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        template.convertAndSend("input-in-0", "input-in-0.rbgh303", emp);
    }
}
