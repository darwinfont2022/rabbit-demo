package com.darwinfont.demorabbitmq.domain;

import org.springframework.amqp.rabbit.connection.CorrelationData;

public class MyCorrelationData extends CorrelationData {
    private final String payload;

    public MyCorrelationData(String id, String payload) {
        super(id);
        this.payload = payload;
    }

    public String getPayload() {
        return this.payload;
    }
}
