package org.xusheng.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

import org.xusheng.demo.metrics.MeterRegistries;

@Configuration
class MetricConfig {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    public MeterRegistries meterRegistries(MeterRegistry registry) {
        return new MeterRegistries(registry);
    }

}
