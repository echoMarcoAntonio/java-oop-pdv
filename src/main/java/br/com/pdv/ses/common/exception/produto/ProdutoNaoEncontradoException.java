package br.com.pdv.ses.common.exception.produto;

public class ProdutoNaoEncontradoException extends RuntimeException {
   public ProdutoNaoEncontradoException(String mensagem) {
       super(mensagem);
   }
}
