package org.xusheng.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Ok {

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }
}
