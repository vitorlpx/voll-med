package br.com.med.voll.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure()
				.directory("src/main/resources") // Diretório onde está o .env
				.load();

		// Configura as variáveis de ambiente
		System.setProperty("DB_NAME", dotenv.get("DB_NAME"));
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(ApiApplication.class, args);
	}

}
