package br.com.pdv.ses.common.exception.produto;

public class StatusInativoException extends RuntimeException {
    public StatusInativoException(String mensagem) {
        super(mensagem);
    }
}
