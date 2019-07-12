package com.kodilla.backend.client.kayak;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KayakConfig {

    @Value("${kayak.api.endpoint}")
    private String kayakApiEndpoint;

    @Value("${kayak.api.header.host}")
    private String kayakHeaderHost;

    @Value("${kayak.api.header.key}")
    private String kayakHeaderKey;





}
