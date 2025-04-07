package gov.mt.seplag.gestao_servidores;

import gov.mt.seplag.gestao_servidores.configuration.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoDeServidoresApplication {

	public static void main(String[] args) {
		EnvLoader.load();

		SpringApplication.run(GestaoDeServidoresApplication.class, args);
	}
}
