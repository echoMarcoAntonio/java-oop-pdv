package br.com.pdv.ses.common.exception.produto;

public class CodigoBarrasInvalidoException extends RuntimeException {
    public CodigoBarrasInvalidoException(String mensagem) {
        super(mensagem);
    }
}
