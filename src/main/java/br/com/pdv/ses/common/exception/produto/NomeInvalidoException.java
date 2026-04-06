package br.com.pdv.ses.common.exception.produto;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException(String mensagem) {
        super(mensagem);
    }
}
