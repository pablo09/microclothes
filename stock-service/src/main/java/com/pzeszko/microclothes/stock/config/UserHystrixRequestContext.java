package com.pzeszko.microclothes.stock.config;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import org.springframework.security.core.Authentication;

public class UserHystrixRequestContext {

    private static final HystrixRequestVariableDefault<Authentication> userContextVariable = new HystrixRequestVariableDefault<>();

    private UserHystrixRequestContext() {}

    public static HystrixRequestVariableDefault<Authentication> getInstance() {
        return userContextVariable;
    }
}