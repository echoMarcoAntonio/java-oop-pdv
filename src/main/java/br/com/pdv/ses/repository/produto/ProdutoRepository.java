package br.com.pdv.ses.repository.produto;

import br.com.pdv.ses.domain.produto.Produto;

import java.util.Optional;

public interface ProdutoRepository {

    void adicionar(Produto produto);

    Optional<Produto> buscar(Integer id);

    void atualizar(Produto produto);

    void remover(Integer id);

    Integer proximoId();
}
