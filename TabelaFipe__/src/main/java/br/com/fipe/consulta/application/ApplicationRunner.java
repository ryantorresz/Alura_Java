package br.com.fipe.consulta.application;

import br.com.fipe.consulta.model.Marca;
import br.com.fipe.consulta.model.Veiculo;
import br.com.fipe.consulta.repository.FipeRepository;
import br.com.fipe.consulta.service.FipeService.ServicoFipe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private final ServicoFipe fipeService;
    private final FipeRepository fipeRepository;
    private final Scanner scanner = new Scanner(System.in);

    public ApplicationRunner(ServicoFipe fipeService, FipeRepository fipeRepository) {
        this.fipeService = fipeService;
        this.fipeRepository = fipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("**** OPÇÕES ****");
        System.out.println("Carro\nMoto\nCaminhão");
        System.out.println("Digite uma das opções para consultar valores:");
        String tipoVeiculo = scanner.nextLine().toLowerCase();

        // Validação do tipo de veículo
        if (!List.of("carro", "moto", "caminhão").contains(tipoVeiculo)) {
            System.out.println("Opção inválida!");
            return;
        }

        try {
            // Listar marcas
            List<Marca> marcas = fipeRepository.getMarcas(tipoVeiculo);
            if (marcas.isEmpty()) {
                System.out.println("Nenhuma marca encontrada!");
                return;
            }

            marcas.forEach(m -> System.out.println("Cód: " + m.getCodigo() + " Descrição: " + m.getNome()));
            System.out.println("Informe o código da marca para consulta:");
            String codigoMarca = scanner.nextLine();

            // Listar modelos
            List<Marca> modelos = fipeRepository.getModelos(tipoVeiculo, codigoMarca);
            if (modelos.isEmpty()) {
                System.out.println("Nenhum modelo encontrado para esta marca!");
                return;
            }

            modelos.forEach(m -> System.out.println("Cód: " + m.getCodigo() + " Descrição: " + m.getNome()));
            System.out.println("Digite um trecho do nome do veículo para consulta:");
            String trechoModelo = scanner.nextLine();

            // Filtrar modelos
            List<Marca> modelosFiltrados = modelos.stream()
                    .filter(m -> m.getNome().toLowerCase().contains(trechoModelo.toLowerCase()))
                    .collect(Collectors.toList());

            if (modelosFiltrados.isEmpty()) {
                System.out.println("Nenhum modelo encontrado com este nome!");
                return;
            }

            modelosFiltrados.forEach(m -> System.out.println("Cód: " + m.getCodigo() + " Descrição: " + m.getNome()));
            System.out.println("Digite o código do modelo para consultar valores:");
            String codigoModelo = scanner.nextLine();

            // Consultar valores por ano
            List<Veiculo> veiculos = fipeService.consultarValoresPorModelo(tipoVeiculo, codigoMarca, codigoModelo);
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum valor encontrado para este modelo!");
                return;
            }

            System.out.println("Todos os veículos com os valores por ano:");
            veiculos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}