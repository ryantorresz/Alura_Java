package br.com.fipe.consulta.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.fipe.consulta.service.FipeService.ServicoFipe")
public class ConsultaFipeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsultaFipeApplication.class, args);
	}
}


