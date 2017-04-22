package com.pzeszko.microclothes.monitor;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sgibb on 7/11/14.
 */
@SpringBootApplication
@Controller
@EnableHystrixDashboard
public class MonitorServiceApplication extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MonitorServiceApplication.class).web(true);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonitorServiceApplication.class).web(true).run(args);
    }
}