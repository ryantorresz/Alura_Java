public enum Moeda {
    DOLAR(4.93),    // Taxa de conversão para dólar (1 dólar = R$4,93)
    EURO(5.37),     // Taxa de conversão para euro (1 euro = R$5,37)
    REAL(1.0);      // Taxa para real (1 real = R$1,0)

    private final double taxaParaReal;

    // Construtor do enum
    Moeda(double taxaParaReal) {
        this.taxaParaReal = taxaParaReal;
    }

    // Método que converte de reais para a moeda
    public double converterPara(double valorEmReais) {
        return valorEmReais / this.taxaParaReal;
    }

    // Método main para teste
    public static void main(String[] args) {
        double valorEmReais = 100.0;

        System.out.println("Conversão de R$" + valorEmReais + ":");
        System.out.printf("Dólar: %.2f%n", Moeda.DOLAR.converterPara(valorEmReais));
        System.out.printf("Euro: %.2f%n", Moeda.EURO.converterPara(valorEmReais));
        System.out.printf("Real: %.2f%n", Moeda.REAL.converterPara(valorEmReais));

        // Exemplo com outros valores
        System.out.println("\nOutros exemplos:");
        System.out.printf("R$50 em dólar: %.2f%n", Moeda.DOLAR.converterPara(50));
        System.out.printf("R$200 em euro: %.2f%n", Moeda.EURO.converterPara(200));
    }
}