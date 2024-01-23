package com.groom.sqc.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ServerProperties {
    @Value("${python.server.host}")
    private String pythonHostName;
}
