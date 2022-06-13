package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbStarter {
    public static void main(String[] args) {
        var context = SpringApplication.run(DbStarter.class, args);
        context.getBean("actionDemo", ActionDemo.class).action();
    }
}
