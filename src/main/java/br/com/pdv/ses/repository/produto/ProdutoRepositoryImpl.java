package br.com.pdv.ses.repository.produto;

import br.com.pdv.ses.domain.produto.Produto;
import br.com.pdv.ses.repository.produto.dao.ProdutoDAO;

import java.util.Optional;

public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoDAO dao;

    public ProdutoRepositoryImpl (
            ProdutoDAO dao
    ) {
        this.dao = dao;
    }

    @Override
    public void adicionar(Produto produto) {
        dao.adicionar(produto);
    }

    @Override
    public Optional<Produto> buscar(Integer id) {
        return dao.buscar(id);
    }

    @Override
    public void atualizar(Produto produto) {
        dao.atualizar(produto);
    }

    @Override
    public void remover(Integer id) {
        dao.remover(id);
    }

    @Override
    public Integer proximoId() {
        return dao.proximoIdProduto();
    }
}
