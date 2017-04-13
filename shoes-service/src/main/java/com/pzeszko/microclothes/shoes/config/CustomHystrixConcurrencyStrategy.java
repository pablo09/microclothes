package com.pzeszko.microclothes.shoes.config;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class CustomHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    public CustomHystrixConcurrencyStrategy() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new HystrixContextWrapper<T>(callable);
    }

    public static class HystrixContextWrapper<V> implements Callable<V> {

        private HystrixRequestContext hystrixRequestContext;
        private Callable<V> delegate;

        public HystrixContextWrapper(Callable<V> delegate) {
        this.hystrixRequestContext = HystrixRequestContext.getContextForCurrentThread();
            this.delegate = delegate;
        }

        @Override
        public V call() throws Exception {
            HystrixRequestContext existingState = HystrixRequestContext.getContextForCurrentThread();
            try {
                HystrixRequestContext.setContextOnCurrentThread(this.hystrixRequestContext);
                return this.delegate.call();
            } finally {
                HystrixRequestContext.setContextOnCurrentThread(existingState);
            }
        }
    }
}