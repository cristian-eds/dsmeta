package com.cristian.eds.dsmeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsmetaApplication {
	
	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

}
