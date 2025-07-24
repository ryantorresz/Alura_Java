import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );

        // Função para verificar se um número é primo
        java.util.function.Predicate<Integer> isPrimo = n -> {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        // Filtrar e ordenar os números primos
        List<Integer> primosOrdenados = listaDeNumeros.stream()
                .flatMap(List::stream) // Achata as sublistas em um único stream
                .filter(isPrimo)       // Filtra apenas os números primos
                .sorted()              // Ordena em ordem crescente
                .collect(Collectors.toList());

        System.out.println("Números primos ordenados: " + primosOrdenados);
    }
}