import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String [] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 899.99, "Eletrônicos"),
                new Produto("Notebook", 2500.00, "Eletrônicos"),
                new Produto("Fone de Ouvido", 150.50, "Eletrônicos"),
                new Produto("Tablet", 950.00, "Eletrônicos"),
                new Produto("Camiseta", 49.90, "Vestuário")
        );

        List<Produto> resultado = produtos.stream()
                .filter(p -> p.getCategoria().equals("Eletrônicos"))
                .filter(p -> p.getPreco() < 1000.00)
                .sorted(Comparator.comparingDouble(Produto::getPreco))
                .collect(Collectors.toList());

        System.out.println("Produtos de Eletrônicos com preço < R$1000 (ordenados):");
        resultado.forEach(System.out::println);
    }
}