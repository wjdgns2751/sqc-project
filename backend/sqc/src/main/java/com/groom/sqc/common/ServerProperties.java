package com.groom.sqc.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ServerProperties {
    @Value("${python.server.url}")
    public static String PYTHON_HOST_NAME;

}
