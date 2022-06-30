package com.requestdesign.testingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TestingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingserviceApplication.class, args);
    }

}
