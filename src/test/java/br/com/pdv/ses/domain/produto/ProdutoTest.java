package br.com.pdv.ses.domain.produto;


import br.com.pdv.ses.common.exception.produto.CodigoInvalidoException;
import br.com.pdv.ses.common.exception.produto.NomeInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.pdv.ses.common.MaquinaConstants.*;
import static br.com.pdv.ses.common.exception.MensagensExcecao.*;

/**
 * teste de unidade puro sem complexidade na classe de dominio (nucleo)
 */
public class ProdutoTest {

    // deve criar produto válido

    @Test
    public void deveCriarProdutoValido_RetornaProduto() {
        Produto produto = produtoValido();

        Assertions.assertNotNull(produto);
        Assertions.assertEquals(69, produto.getId());
        Assertions.assertEquals(new CodigoBarra("9788576356127"), produto.getCdBarra());
        Assertions.assertEquals("MAMINHA", produto.getNome());
        Assertions.assertEquals(new BigDecimal("40.69"), produto.getPreco());
        Assertions.assertEquals(new BigDecimal("0"), produto.getDesconto());
        Assertions.assertEquals(Setor.ACOUGUE, produto.getSetor());
        Assertions.assertEquals(Categoria.CARNES, produto.getCategoria());
        Assertions.assertFalse(produto.isInativo());
    }

    // exceções: código

    @Test
    public void deveCriarProdutoInvalido_CodigoZero_RetornaExcecaoDeDominio() {
        Exception exception = Assertions.assertThrows(CodigoInvalidoException.class,
                () -> new Produto(
                        CODIGO_ZERO,
                        new CodigoBarra(EAN_13_VALIDO),
                        NOME_VALIDO,
                        new BigDecimal(PRECO_VALIDO),
                        Setor.MERCEARIA,
                        Categoria.ARTIGOS
                ));

        Assertions.assertEquals(CODIGO_PRODUTO_OBRIGATORIO, exception.getMessage());
    }

    // exceções: nome

    @Test
    public void deveCriarProdutoInvalido_NomeVazio_RetornaExcecaoDeDominio() {
        Exception exception = Assertions.assertThrows(NomeInvalidoException.class,
                () -> new Produto(
                        CODIGO_VALIDO,
                        new CodigoBarra(EAN_13_VALIDO),
                        NOME_VAZIO,
                        new BigDecimal(PRECO_VALIDO),
                        Setor.MERCEARIA,
                        Categoria.ARTIGOS
                ));

        Assertions.assertEquals(NOME_PRODUTO_OBRIGATORIO, exception.getMessage());
    }

    @Test
    public void deveCriarProdutoInvalido_NomeEspacoEmBranco_RetornaExcecaoDeDominio() {
        Exception exception = Assertions.assertThrows(NomeInvalidoException.class,
                () -> new Produto(
                        CODIGO_VALIDO,
                        new CodigoBarra(EAN_13_VALIDO),
                        NOME_ESPACO_EM_BRANCO,
                        new BigDecimal(PRECO_VALIDO),
                        Setor.MERCEARIA,
                        Categoria.ARTIGOS
                ));

        Assertions.assertEquals(NOME_PRODUTO_OBRIGATORIO, exception.getMessage());
    }

    @Test
    public void deveCriarProdutoInvalido_NomeCurtoDemais_RetornaExcecaoDeDominio() {
        Exception exception = Assertions.assertThrows(NomeInvalidoException.class,
                () -> new Produto(
                        CODIGO_VALIDO,
                        new CodigoBarra(EAN_13_VALIDO),
                        NOME_CURTO_DEMAIS,
                        new BigDecimal(PRECO_VALIDO),
                        Setor.MERCEARIA,
                        Categoria.ARTIGOS
                ));

        Assertions.assertEquals(NOME_PRODUTO_CURTO_DEMAIS, exception.getMessage());
    }

    @Test
    public void deveCriarProdutoInvalido_NomeLongoDemais_RetornaExcecaoDeDominio() {
        Exception exception = Assertions.assertThrows(NomeInvalidoException.class,
                () -> new Produto(
                        CODIGO_VALIDO,
                        new CodigoBarra(EAN_13_VALIDO),
                        NOME_LONGO_DEMAIS,
                        new BigDecimal(PRECO_VALIDO),
                        Setor.MERCEARIA,
                        Categoria.ARTIGOS
                ));

        Assertions.assertEquals(NOME_PRODUTO_LONGO_DEMAIS, exception.getMessage());
    }

    // exceções: preco

    // exceções: desconto

}
