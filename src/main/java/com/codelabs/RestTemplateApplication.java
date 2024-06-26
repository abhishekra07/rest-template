package com.codelabs;

import com.codelabs.exmaples.RestTemplateGetExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication implements CommandLineRunner {

	@Autowired
	private RestTemplateGetExample restTemplateGetExample;

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restTemplateGetExample.getForObjectExample();
	}

}
