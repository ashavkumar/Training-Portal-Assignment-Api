package com.barclays.courseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate instanceOfRestTemplate() {
		//HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
		//factory.setConnectTimeout(3000);
		return new RestTemplate();
	}
}
