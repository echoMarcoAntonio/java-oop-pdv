package main.java.pdv.domain;

import java.math.BigDecimal;
import java.util.Objects;

import main.java.pdv.exception.MensagensExcecao;
import main.java.pdv.exception.produto.*;

public class Produto {

    private static final int TAMANHO_MINIMO_CODIGO = 3;
    private static final int TAMANHO_MAXIMO_CODIGO = 20;
    private static final int TAMANHO_MINIMO_NOME = 3;
    private static final int TAMANHO_MAXIMO_NOME = 100;

    private final String nome;
    private final String cdProduto;
    private final BigDecimal preco;

    public Produto(
            String nome,
            String cdProduto,
            BigDecimal preco) {

        this.nome = validarNome(nome);
        this.cdProduto = validarCdProduto(cdProduto);
        this.preco = validarPreco(preco);
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_OBRIGATORIO);
        }

        String nomeTrimado = nome.trim();
        int tamanho = nomeTrimado.length();

        if (tamanho < TAMANHO_MINIMO_NOME) {
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_PEQUENO_DEMAIS);
        }
        if (tamanho > TAMANHO_MAXIMO_NOME) {
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_GRANDE_DEMAIS);
        }

        return nomeTrimado; // persiste normalizado
    }

    private static String validarCdProduto(String cdProduto) {
        if (cdProduto == null || cdProduto.isBlank()) {
            throw new CodigoInvalidoException(MensagensExcecao.CODIGO_PRODUTO_OBRIGATORIO);
        }

        String cdProdutoTrimado = cdProduto.trim();
        int tamanho = cdProdutoTrimado.length();

        if (tamanho < TAMANHO_MINIMO_CODIGO) {
            throw new CodigoInvalidoException(MensagensExcecao.CODIGO_PRODUTO_PEQUENO_DEMAIS);
        }
        if (tamanho > TAMANHO_MAXIMO_CODIGO) {
            throw new CodigoInvalidoException(MensagensExcecao.CODIGO_PRODUTO_GRANDE_DEMAIS);
        }

        return cdProdutoTrimado;
    }

    private static BigDecimal validarPreco(BigDecimal preco) {
        if (preco == null) {
            throw new PrecoInvalidoException(MensagensExcecao.PRECO_PRODUTO_OBRIGATORIO);
        }
        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecoInvalidoException(MensagensExcecao.PRECO_NEGATIVO);
        }

        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getCdProduto() {
        return cdProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto other)) return false;
        return Objects.equals(this.cdProduto, other.cdProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdProduto);
    }

    @Override
    public String toString() {
        return "Produto{cdProduto='" + cdProduto + "', nome='" + nome + "', preco=" + preco + "}";
    }
}
