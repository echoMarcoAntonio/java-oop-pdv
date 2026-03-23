package main.java.pdv.domain;

import java.math.BigDecimal;

import main.java.pdv.exception.MensagensExcecao;
import main.java.pdv.exception.itemvenda.*;

public class ItemVenda {

    private Produto produto;
    private int quantidade;

    public ItemVenda(
        Produto produto,
        int quantidade
    ) {
        validarProduto(produto);
        validarQuantidade(quantidade);

        this.produto = produto;
        this.quantidade = quantidade;
    }

    private static void validarProduto(Produto produto) {
        if (produto == null) {
            throw new ProdutoInvalidoException(MensagensExcecao.PRODUTO_OBRIGATORIO);
        }
    }

    private static void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException(MensagensExcecao.QUANTIDADE_MINIMA);
        }
    }

    // Regra de negócio: ItemVenda sabe calcular seu valor total (produto + quantidade)
    public BigDecimal getSubtotal() {
        return produto.getPreco().multiply(new BigDecimal(quantidade));
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
