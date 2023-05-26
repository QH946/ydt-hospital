package com.qh.ydt.hospitalmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.qh.ydt")
@EnableDiscoveryClient
public class HospitalManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManageApplication.class, args);
	}

}
