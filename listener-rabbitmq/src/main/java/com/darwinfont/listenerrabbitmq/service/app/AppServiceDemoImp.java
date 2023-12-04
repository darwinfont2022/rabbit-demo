package com.darwinfont.listenerrabbitmq.service.app;

import com.darwinfont.listenerrabbitmq.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class AppServiceDemoImp implements AppServiceDemo {

    private List<Employee> msgs;
    AppServiceDemoImp() {
        this.msgs = new ArrayList<>();
    }

    @Override
    public List<Employee> getMessages() {
        return msgs;
    }

    @Override
    public void addMsg(Employee msg) {
        log.info("Message add {}", msg);
        msgs.add(msg);
    }
}
