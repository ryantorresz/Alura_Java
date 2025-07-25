import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringToNumberConverter {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("10", "abc", "20", "30x");

        List<Integer> output = input.stream()
                .filter(s -> s.matches("\\d+"))  // Verifica se a string contém apenas dígitos
                .map(Integer::parseInt)          // Converte para Integer
                .collect(Collectors.toList());   // Coleta os resultados em uma lista

        System.out.println(output);  // Saída: [10, 20]
    }
}