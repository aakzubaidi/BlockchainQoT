package com.iot.server.iotserver;

/**
 * @author Ali Alzubaidi
 *
 * 
 * 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableScheduling
public class IotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotServerApplication.class, args);

	}

}
