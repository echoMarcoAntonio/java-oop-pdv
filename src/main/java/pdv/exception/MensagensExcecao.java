package main.java.pdv.exception;

public final class MensagensExcecao {

    // PRODUTO
    public static final String NOME_PRODUTO_OBRIGATORIO = "O nome do produto é obrigatório.";
    public static final String NOME_PRODUTO_GRANDE_DEMAIS = "O nome do produto não pode exceder 100 caracteres.";
    public static final String CODIGO_PRODUTO_OBRIGATORIO = "O código do produto é obrigatório.";
    public static final String CODIGO_PRODUTO_GRANDE_DEMAIS = "O código do produto não pode exceder 20 caracteres.";
    public static final String PRECO_PRODUTO_OBRIGATORIO = "O preço do produto é obrigatório.";
    public static final String PRECO_NEGATIVO = "O preço do produto deve ser maior que zero.";

    // ITEM VENDA
    public static final String QUANTIDADE_MINIMA = "A quantidade deve ser de pelo menos 1 unidade.";
    public static final String PRODUTO_OBRIGATORIO = "O produto não pode ser nulo no item de venda.";

    private MensagensExcecao() {
        throw new UnsupportedOperationException("Classe utilitária");
    }
}
