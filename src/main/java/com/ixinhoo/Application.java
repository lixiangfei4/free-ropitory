package com.ixinhoo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@ComponentScan(basePackages = "com.ixinhoo")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
    }

}
