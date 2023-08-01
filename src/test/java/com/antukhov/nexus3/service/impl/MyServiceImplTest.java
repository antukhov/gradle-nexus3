package com.antukhov.nexus3.service.impl;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class MyServiceImplTest {

    @Test
    void doSomething() {

        new MyServiceImpl().doSomething("test").as(StepVerifier::create)
                .expectNextCount(100000)
                .verifyComplete();

    }

}