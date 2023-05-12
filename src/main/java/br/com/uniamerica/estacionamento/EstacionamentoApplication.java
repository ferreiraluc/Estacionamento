package br.com.uniamerica.estacionamento;

import br.com.uniamerica.estacionamento.entity.Cor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static br.com.uniamerica.estacionamento.entity.Cor.*;

@SpringBootApplication
public class EstacionamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoApplication.class, args);

	}

}
