public enum Mes {
    JANEIRO(31),
    FEVEREIRO(28),
    MARCO(31),
    ABRIL(30),
    MAIO(31),
    JUNHO(30),
    JULHO(31),
    AGOSTO(31),
    SETEMBRO(30),
    OUTUBRO(31),
    NOVEMBRO(30),
    DEZEMBRO(31);

    private final int numeroDeDias;

    // Construtor do enum
    Mes(int numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    // Método que retorna o número de dias do mês
    public int getNumeroDeDias() {
        return this.numeroDeDias;
    }

    // Método main para teste
    public static void main(String[] args) {
        // Testando alguns meses
        System.out.println("Janeiro tem " + Mes.JANEIRO.getNumeroDeDias() + " dias");
        System.out.println("Fevereiro tem " + Mes.FEVEREIRO.getNumeroDeDias() + " dias");
        System.out.println("Julho tem " + Mes.JULHO.getNumeroDeDias() + " dias");
        System.out.println("Novembro tem " + Mes.NOVEMBRO.getNumeroDeDias() + " dias");

        // Testando todos os meses
        System.out.println("\nDias em cada mês:");
        for (Mes mes : Mes.values()) {
            System.out.println(mes + ": " + mes.getNumeroDeDias() + " dias");
        }
    }
}