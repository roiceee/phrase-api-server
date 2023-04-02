package com.roiceee.phraseapi;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuoteJokeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteJokeApiApplication.class, args);
    }

}
