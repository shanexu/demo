package org.xusheng.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xusheng.demo.services.MyService;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@RestController
public class DemoCtrl {

    private final MeterRegistry registry;

    private final MyService myService;

    public DemoCtrl(final MyService myService, final MeterRegistry registry) {
        this.myService = myService;
        this.registry = registry;
    }

    @GetMapping("/demo1")
    public boolean demo1() throws Exception {
        final Timer.Sample sample = Timer.start(registry);
        final boolean response = myService.execute();
        sample.stop(registry.timer("my.timer", "response", response ? "succeed" : "failed"));
        return response;
    }
}
