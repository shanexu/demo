package org.xusheng.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xusheng.demo.metrics.MeterRegistries;
import org.xusheng.demo.services.MyService;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@RestController
public class Demo2Ctrl {

    private final MeterRegistry registry;

    private final MyService myService;

    public Demo2Ctrl(final MyService myService, MeterRegistries registries) {
        this.myService = myService;
        this.registry = registries.get("demo2");
    }

    @GetMapping("/demo2")
    public boolean demo2() throws Exception {
        final Timer.Sample sample = Timer.start(registry);
        final boolean response = myService.execute();
        sample.stop(registry.timer("my.timer", "response", response ? "succeed" : "failed"));
        return response;
    }
}
