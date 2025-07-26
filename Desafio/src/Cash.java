

public enum Cash {
    DOLAR(4.93),
    EURO(5.37),
    REAL((double)1.0F);

    private final double taxaParaReal;

    private Cash(double taxaParaReal) {
        this.taxaParaReal = taxaParaReal;
    }

    public double converterPara(double valorEmReais) {
        return valorEmReais / this.taxaParaReal;
    }

    public static void main(String[] args) {
        double valorEmReais = (double)100.0F;
        System.out.println("Conversão de R$" + valorEmReais + ":");
        System.out.printf("Dólar: %.2f%n", DOLAR.converterPara(valorEmReais));
        System.out.printf("Euro: %.2f%n", EURO.converterPara(valorEmReais));
        System.out.printf("Real: %.2f%n", REAL.converterPara(valorEmReais));
        System.out.println("\nOutros exemplos:");
        System.out.printf("R$50 em dólar: %.2f%n", DOLAR.converterPara((double)50.0F));
        System.out.printf("R$200 em euro: %.2f%n", EURO.converterPara((double)200.0F));
    }
}