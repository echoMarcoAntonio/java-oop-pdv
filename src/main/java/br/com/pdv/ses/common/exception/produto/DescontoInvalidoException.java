package br.com.pdv.ses.common.exception.produto;

public class DescontoInvalidoException extends RuntimeException {
    public DescontoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
