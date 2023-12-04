package com.darwinfont.demorabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Employee {
    private String name;
    private String lastName;
    private String id;
}
