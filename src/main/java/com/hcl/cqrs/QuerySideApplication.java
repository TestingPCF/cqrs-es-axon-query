package com.hcl.cqrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories
public class QuerySideApplication {
	private static final Logger LOG = LoggerFactory.getLogger(QuerySideApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(QuerySideApplication.class, args);
		LOG.info("Starting the QUERY-SIDE PCF Axon CQRS Demo [The eCommerce viewer] with SpringBoot.");
	}

}
