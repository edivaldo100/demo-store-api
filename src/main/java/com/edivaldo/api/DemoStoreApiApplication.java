package com.edivaldo.api;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStoreApiApplication.class, args);
	}
	  @PostConstruct
      void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
      }
}
