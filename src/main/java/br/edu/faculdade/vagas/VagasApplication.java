package br.edu.faculdade.vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * O ponto de partida. O @SpringBootApplication liga o @ComponentScan, que
 * procura beans neste pacote e em TODOS os subpacotes — controller, service,
 * repository... É por isso que as pastas por camada funcionam sem configuração
 * nenhuma, e é por isso que esta classe fica na raiz, acima delas.
 */
@SpringBootApplication
public class VagasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VagasApplication.class, args);
    }
}
