package com.darwinfont.listenerrabbitmq.service.app;

import com.darwinfont.listenerrabbitmq.domain.Employee;

import java.util.List;

public interface AppServiceDemo {
    List<Employee> getMessages();

    void addMsg(Employee employee);
}
