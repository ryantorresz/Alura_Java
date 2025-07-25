public enum CodigoErro {
    // Códigos de erro HTTP 4xx (Cliente)
    BAD_REQUEST(400, "Requisição inválida - O servidor não pode processar a requisição devido a um erro do cliente"),
    UNAUTHORIZED(401, "Não autorizado - Autenticação necessária"),
    FORBIDDEN(403, "Acesso proibido - O cliente não tem direitos de acesso ao conteúdo"),
    NOT_FOUND(404, "Recurso não encontrado - O servidor não encontrou o recurso solicitado"),
    METHOD_NOT_ALLOWED(405, "Método não permitido - Método de requisição não suportado"),

    // Códigos de erro HTTP 5xx (Servidor)
    INTERNAL_SERVER_ERROR(500, "Erro interno do servidor - O servidor encontrou uma situação com a qual não sabe lidar"),
    NOT_IMPLEMENTED(501, "Não implementado - O servidor não suporta a funcionalidade requerida"),
    BAD_GATEWAY(502, "Gateway inválido - O servidor, ao atuar como um gateway, recebeu uma resposta inválida"),
    SERVICE_UNAVAILABLE(503, "Serviço indisponível - O servidor não está pronto para manipular a requisição");

    private final int codigo;
    private final String descricao;

    // Construtor do enum
    CodigoErro(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    // Métodos de acesso
    public int getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    // Método para encontrar um código de erro pelo número
    public static CodigoErro porCodigo(int codigo) {
        for (CodigoErro erro : values()) {
            if (erro.codigo == codigo) {
                return erro;
            }
        }
        throw new IllegalArgumentException("Código de erro HTTP desconhecido: " + codigo);
    }

    // Método main para teste
    public static void main(String[] args) {
        // Exemplos de uso
        System.out.println("Código 404: " + CodigoErro.NOT_FOUND.getDescricao());
        System.out.println("Descrição do 500: " + CodigoErro.INTERNAL_SERVER_ERROR.getDescricao());

        // Busca por código
        System.out.println("\nBuscando por códigos:");
        System.out.println("403: " + CodigoErro.porCodigo(403));
        System.out.println("503: " + CodigoErro.porCodigo(503).getDescricao());

        // Listando todos os códigos
        System.out.println("\nTodos os códigos de erro:");
        for (CodigoErro erro : CodigoErro.values()) {
            System.out.printf("%d - %s: %s%n",
                    erro.getCodigo(),
                    erro.name(),
                    erro.getDescricao());
        }
    }
}