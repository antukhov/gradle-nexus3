package com.antukhov.nexus3.service.impl;

import com.antukhov.nexus3.model.MyResponseDto;
import com.antukhov.nexus3.service.MyService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class MyServiceImpl implements MyService {

    /**
     * Method produces Flux emitting the MyResponseDto objects
     * @param param - sample parameter
     * @return Flux of MyResponseDto - resulted objects
     */
    @Override
    public Flux<MyResponseDto> doSomething(String param) {
        return Flux
                .create(fluxSink -> {
                    for (int i = 0; i < 100000; ++i) {
                        fluxSink.next(new MyResponseDto("Hello, " + param + " " + i, LocalDateTime.now()));
                    }
                    fluxSink.complete();
                });
    }

}
