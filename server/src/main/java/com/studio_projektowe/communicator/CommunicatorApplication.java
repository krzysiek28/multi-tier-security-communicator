package com.studio_projektowe.communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class CommunicatorApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
	}
}
