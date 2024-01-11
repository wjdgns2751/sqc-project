package com.groom.sqc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class WebController {

    @GetMapping("/sqc-api/get")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public Mono<String> redirectToSwaggerUI(HttpServletResponse response) throws IOException {
        return Mono.defer(()-> Mono.just("redirect:/swagger-ui/index.html"));
    }

}
