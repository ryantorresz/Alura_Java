import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19)
        );

        // Filtra pessoas com mais de 18 anos, extrai os nomes e ordena alfabeticamente
        List<String> nomesMaioresDe18 = pessoas.stream()
                .filter(pessoa -> pessoa.getIdade() > 18)  // Filtra por idade
                .map(Pessoa::getNome)                      // Pega apenas os nomes
                .sorted()                                  // Ordena alfabeticamente
                .collect(Collectors.toList());             // Coleta os resultados

        // Imprime os nomes
        System.out.println("Nomes das pessoas maiores de 18 anos (ordem alfabética):");
        nomesMaioresDe18.forEach(System.out::println);
    }
}