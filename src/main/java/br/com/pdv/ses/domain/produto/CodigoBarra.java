package br.com.pdv.ses.domain.produto;

import br.com.pdv.ses.common.exception.MensagensExcecao;
import br.com.pdv.ses.common.exception.produto.CodigoBarrasInvalidoException;
import jakarta.persistence.Embeddable;

import java.util.Objects;

/**
 * Value Object que representa um código de barras padrão EAN
 *
 * suporta modelos EAN-8 (8 dígitos) e EAN-13 (13 dígitos).
 * imutável, comparado por valor, auto-validação no construtor.
 */
@Embeddable
public class CodigoBarra {

    private static final int TAMANHO_EAN_8 = 8;
    private static final int TAMANHO_EAN_13 = 13;

    private String cdBarra;

    // construtor para o JPA

    protected CodigoBarra(){
    }

    // construtor

    public CodigoBarra(
            String cdBarra
    ) {
        this.cdBarra = validar(cdBarra);
    }

    // validações

    private String validar(String cdBarra) {
        if (cdBarra == null || cdBarra.isBlank()) {
            throw new CodigoBarrasInvalidoException(MensagensExcecao.CODIGO_BARRAS_VALOR_OBRIGATORIO);
        }

        String trimado = cdBarra.trim();

        if (!trimado.matches("\\d+")) {
            throw new CodigoBarrasInvalidoException(MensagensExcecao.CODIGO_BARRAS_SOMENTE_DIGITOS);
        }

        int tamanho = trimado.length();
        if (tamanho != TAMANHO_EAN_8 && tamanho != TAMANHO_EAN_13) {
            throw new CodigoBarrasInvalidoException(MensagensExcecao.CODIGO_BARRAS_TAMANHO_INVALIDO);
        }

        if (!digitoVerificadorValido(trimado)) {
            throw new CodigoBarrasInvalidoException(MensagensExcecao.CODIGO_BARRAS_DIGITO_VERIFICADOR_INVALIDO);
        }

        return trimado;
    }

    private boolean digitoVerificadorValido(String cdBarra) {
        int somaDigitos = 0;
        int comprimento = cdBarra.length();

        for (int i = 0; i < comprimento - 1; i++) {
            int digito = Character.getNumericValue(cdBarra.charAt(i));
            int multiplicador = ((comprimento -1 -i) % 2 == 0) ? 1 : 3;

            somaDigitos += digito * multiplicador;
        }

        int resultadoEsperado = (10 - (somaDigitos % 10)) % 10;
        return resultadoEsperado == Character.getNumericValue(cdBarra.charAt(comprimento -1));
    }

    public String getCdBarra() {
        return cdBarra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoBarra that)) return false;
        return Objects.equals(cdBarra, that.cdBarra);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cdBarra);
    }
}
