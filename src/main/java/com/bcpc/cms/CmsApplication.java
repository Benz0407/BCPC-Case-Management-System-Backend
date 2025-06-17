package com.bcpc.cms;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CmsApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		Properties props = System.getProperties();
		props.setProperty("DB_URL", dotenv.get("DB_URL"));
		props.setProperty("DB_USER", dotenv.get("DB_USER"));
		props.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		props.setProperty("jwt.secret", dotenv.get("JWT_KEY"));

		SpringApplication.run(CmsApplication.class, args);
	}

}
