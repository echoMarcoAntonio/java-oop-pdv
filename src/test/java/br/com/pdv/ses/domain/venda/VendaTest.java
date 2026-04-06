package br.com.pdv.ses.domain.venda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static br.com.pdv.ses.common.MaquinaConstants.vendaValida;

/**
 * teste de unidade puro sem complexidade na classe de dominio (nucleo)
 */
public class VendaTest {

    // deve criar venda válida

    @Test
    public void deveCriarVendaValida_RetornaVenda() {
        Venda venda = vendaValida();

        Assertions.assertNotNull(venda);
    }
}
