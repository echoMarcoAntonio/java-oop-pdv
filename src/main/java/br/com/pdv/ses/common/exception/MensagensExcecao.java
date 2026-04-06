package br.com.pdv.ses.common.exception;

public final class MensagensExcecao {

    /**
     * produto
     */
    public static final String CODIGO_PRODUTO_OBRIGATORIO = "O codigo do produto e obrigatorio.";

    public static final String NOME_PRODUTO_OBRIGATORIO =
            "O nome do produto e obrigatorio.";
    public static final String NOME_PRODUTO_CURTO_DEMAIS   =
            "O nome do produto deve ter pelo menos 3 caracteres.";
    public static final String NOME_PRODUTO_LONGO_DEMAIS =
            "O nome do produto nao pode exceder 100 caracteres.";

    public static final String PRECO_PRODUTO_OBRIGATORIO =
            "O preco do produto e obrigatorio.";
    public static final String PRECO_PRODUTO_NEGATIVO =
            "O preco do produto deve ser maior que zero.";

    public static final String DESCONTO_PRODUTO_NEGATIVO =
            "Se um desconto for aplicado deve ser positivo!";
    public static final String STATUS_PRODUTO_OBRIGATORIO =
            "O status do produto deve ser ativo na criacao.";

    public static final String CODIGO_BARRAS_VALOR_OBRIGATORIO =
            "O codigo de barras deve ser informado";
    public static final String CODIGO_BARRAS_SOMENTE_DIGITOS =
            "O codigo de barras so pode possuir digitos numericos.";
    public static final String CODIGO_BARRAS_TAMANHO_INVALIDO =
            "O último dígito é reservado a um único caractere do identificador especial";
    public static final String CODIGO_BARRAS_DIGITO_VERIFICADOR_INVALIDO =
            "O dígito verificador recebido é invalido";
    public static final String PRODUTO_INEXISTENTE =
            "O informado não existe no sistema";

    /**
     * item venda
     */
    public static final String QUANTIDADE_MINIMA =
            "A quantidade deve ser de pelo menos 1 unidade.";
    public static final String PRODUTO_OBRIGATORIO =
            "O produto nao pode ser nulo no item de venda.";

    /**
     * venda
     */
    public static final String VENDA_INEXISTENTE =
            "A venda informada não existe no sistema";
    public static final String VENDA_FECHADA =
            "Esta venda já foi finalizada";

    private MensagensExcecao() {
        throw new UnsupportedOperationException("Classe utilitaria");
    }
}
