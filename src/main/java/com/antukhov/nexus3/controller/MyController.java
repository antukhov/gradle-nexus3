package com.antukhov.nexus3.controller;

import com.antukhov.nexus3.model.MyResponseDto;
import com.antukhov.nexus3.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MyController {

    MyService myService;

    @Autowired
    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/my-endpoint/param/{param}")
    Flux<MyResponseDto> getMyResponseDto(@PathVariable String param) {
        return myService.doSomething(param);
    }

}
