package br.com.pdv.ses.domain.venda;

import br.com.pdv.ses.common.MaquinaConstants;
import br.com.pdv.ses.common.exception.produto.CodigoInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.pdv.ses.common.MaquinaConstants.*;
import static br.com.pdv.ses.common.exception.MensagensExcecao.CODIGO_PRODUTO_OBRIGATORIO;

/**
 * teste de unidade puro sem complexidade na classe de dominio (nucleo)
 */
public class ItemVendaTest {

    // deve criar item de venda válido

    @Test
    public void deveCriarItemDeVendaValido_RetornaItemDeVenda() {
        ItemVenda itemVenda = itemVendaValido();

        Assertions.assertNotNull(itemVenda);
        Assertions.assertEquals(produtoValido(), itemVenda.getProduto());
        Assertions.assertEquals(69, itemVenda.getProduto().getId());
        Assertions.assertEquals(new BigDecimal("40.69"), itemVenda.getProduto().getPreco());
        Assertions.assertEquals(new BigDecimal("40.69"), itemVenda.getPrecoUnitario());
    }

    // exceções: produto
    @Test
    public void deveCriarItemDeVendaInvalido_RetornaExcecaoDeDominio() {
        Exception exception = Assertions.assertThrows(CodigoInvalidoException.class,
                MaquinaConstants::itemVendaInvalido);

        // Assertions.assertEquals(PRODUTO_OBRIGATORIO, exception.getMessage());
        Assertions.assertEquals(CODIGO_PRODUTO_OBRIGATORIO, exception.getMessage());
    }

    // exceções: quantidade

}
