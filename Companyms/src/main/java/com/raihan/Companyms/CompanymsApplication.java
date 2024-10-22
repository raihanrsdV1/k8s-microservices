package com.raihan.Companyms;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@EnableFeignClients
@SpringBootApplication
public class CompanymsApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(CompanymsApplication.class, args);
    }

	@Value("${eureka.client.service-url.default-zone}")
	private String eurekaUrl;

    @PostConstruct
    public void init() {
		System.out.println("Eureka URL: " + eurekaUrl);
		System.out.println("Active profiles: " + Arrays.toString(environment.getActiveProfiles()));
	}
}
