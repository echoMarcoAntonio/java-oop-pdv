package main.java.pdv;

import java.math.BigDecimal;

import main.java.pdv.exception.MensagensExcecao;
import main.java.pdv.exception.produto.*;

public class Produto {

    private static final int TAMANHO_MAXIMO_NOME = 100;
    private static final int TAMANHO_MAXIMO_CODIGO = 20;

    private String nome;
    private String codigo;
    private BigDecimal preco;

    public Produto(
            String nome,
            String codigo,
            BigDecimal preco) {

        ValidarNome(nome);
        ValidarCodigo(codigo);
        ValidarPreco(preco);

        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    private static void ValidarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_OBRIGATORIO);
        }
        if (nome.trim().length() > TAMANHO_MAXIMO_NOME) {
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_GRANDE_DEMAIS);
        }
    }

    private static void ValidarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new CodigoInvalidoException(MensagensExcecao.CODIGO_PRODUTO_OBRIGATORIO);
        }
        if (codigo.trim().length() > TAMANHO_MAXIMO_CODIGO) {
            throw new CodigoInvalidoException(MensagensExcecao.CODIGO_PRODUTO_GRANDE_DEMAIS);
        }
    }

    private static void ValidarPreco(BigDecimal preco) {
        if (preco == null) {
            throw new PrecoInvalidoException(MensagensExcecao.PRECO_PRODUTO_OBRIGATORIO);
        }
        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PrecoInvalidoException(MensagensExcecao.PRECO_NEGATIVO);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
