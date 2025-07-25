public class Main {
    public static void main(String[] args) {
        System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
        System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"
    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        // Remove espaços extras e divide em partes
        String[] partes = nomeCompleto.trim().split("\\s+");

        // Se só tiver um nome, retorna ele mesmo
        if (partes.length == 1) {
            return partes[0];
        }

        // Combina primeiro e último nome
        return partes[0] + " " + partes[partes.length - 1];
    }
}