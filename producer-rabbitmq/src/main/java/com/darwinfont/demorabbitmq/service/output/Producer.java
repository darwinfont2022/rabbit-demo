package com.darwinfont.demorabbitmq.service.output;

import com.darwinfont.demorabbitmq.domain.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface Producer {
    void sendMessage(Employee employeeDto);
}
