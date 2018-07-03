package com.wf.wholesale.taxie.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class TaxieCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxieCloudConfigServerApplication.class, args);
	}
}
