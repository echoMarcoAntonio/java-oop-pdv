package br.com.pdv.ses.common.exception.venda;

public class VendaNaoEncontradaException extends RuntimeException {
    public VendaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
