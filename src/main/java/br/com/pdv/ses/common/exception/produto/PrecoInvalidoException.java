package br.com.pdv.ses.common.exception.produto;

public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
