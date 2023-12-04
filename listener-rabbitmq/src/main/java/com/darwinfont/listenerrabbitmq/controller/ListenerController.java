package com.darwinfont.listenerrabbitmq.controller;

import com.darwinfont.listenerrabbitmq.service.app.AppServiceDemo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener-demo")
public class ListenerController {
    private final AppServiceDemo serviceDemo;

    public ListenerController(AppServiceDemo serviceDemo) {
        this.serviceDemo = serviceDemo;
    }

    @GetMapping
    public ResponseEntity<?> getMessages() {
        return ResponseEntity.ok(serviceDemo.getMessages());
    }
}
