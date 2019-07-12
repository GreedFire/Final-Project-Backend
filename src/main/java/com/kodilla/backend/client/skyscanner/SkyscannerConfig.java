package com.kodilla.backend.client.skyscanner;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SkyscannerConfig {

    @Value("${skyscanner.api.endpoint}")
    private String skyscannerApiEndpoint;

    @Value("${skyscanner.api.header.host}")
    private String skyscannerHeaderHost;

    @Value("${skyscanner.api.header.key}")
    private String skyscannerHeaderKey;
}
