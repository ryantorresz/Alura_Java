import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println(processaNumero(Optional.of(5)));    // Saída: Optional[25]
        System.out.println(processaNumero(Optional.of(-3)));   // Saída: Optional.empty
        System.out.println(processaNumero(Optional.empty()));  // Saída: Optional.empty
    }

    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        return numero
                .filter(n -> n > 0)        // Filtra apenas números positivos
                .map(n -> n * n);           // Calcula o quadrado se presente e positivo
    }
}