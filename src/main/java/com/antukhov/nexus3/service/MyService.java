package com.antukhov.nexus3.service;

import com.antukhov.nexus3.model.MyResponseDto;
import reactor.core.publisher.Flux;

public interface MyService {

    Flux<MyResponseDto> doSomething(String param);

}
