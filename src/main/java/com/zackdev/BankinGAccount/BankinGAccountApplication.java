package com.zackdev.BankinGAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankinGAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankinGAccountApplication.class, args);
	}

}
