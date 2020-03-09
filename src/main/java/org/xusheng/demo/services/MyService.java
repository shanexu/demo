package org.xusheng.demo.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    private Random random = new Random(System.currentTimeMillis());

    public boolean execute() throws Exception {
        Thread.sleep((long)random.nextInt(1000));
        return random.nextDouble() > 0.5;
    }
}
