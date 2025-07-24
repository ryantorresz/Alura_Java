import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println("Produtos por categoria:");
        produtosPorCategoria.forEach((categoria, lista) -> {
            System.out.println(categoria + ": " + lista);
        });

        Map<String, Long> contagemPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.counting()
                ));

        System.out.println("\nQuantidade por categoria:");
        contagemPorCategoria.forEach((categoria, quantidade) -> {
            System.out.println(categoria + ": " + quantidade);
        });

        Map<String, Optional<Produto>> maisCaroPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))
                ));

        System.out.println("\nProduto mais caro por categoria:");
        maisCaroPorCategoria.forEach((categoria, produto) -> {
            System.out.println(categoria + ": " + produto.orElse(null));
        });


        Map<String, Double> totalPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.summingDouble(Produto::getPreco)
                ));

        System.out.println("\nTotal de preços por categoria:");
        totalPorCategoria.forEach((categoria, total) -> {
            System.out.printf("%s: R$%.2f%n", categoria, total);
        });
    }
}
