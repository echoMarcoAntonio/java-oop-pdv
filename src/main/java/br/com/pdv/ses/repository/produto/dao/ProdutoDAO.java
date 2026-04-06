package br.com.pdv.ses.repository.produto.dao;

import br.com.pdv.ses.domain.produto.Produto;

import java.util.Optional;

/*
 * retorna o próximo valor da sequence (pdv_sequence)
 * chamado pelo criar() ao definir o codigo do produto
 */
public interface ProdutoDAO {

    void adicionar(Produto produto);

    Optional<Produto> buscar(Integer id);

    void atualizar(Produto produto);

    void remover(Integer id);

    Integer proximoIdProduto();
}
