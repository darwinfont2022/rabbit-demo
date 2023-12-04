package com.darwinfont.demorabbitmq.controller;

import com.darwinfont.demorabbitmq.domain.Employee;
import com.darwinfont.demorabbitmq.service.output.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo-rabbit")
public class DemoController {
    private final Producer producer;

    public DemoController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping()
    public ResponseEntity<?> sendMessage(@RequestBody() Employee employeeDto) {
        producer.sendMessage(employeeDto);
        return ResponseEntity.ok().build();
    }
}
