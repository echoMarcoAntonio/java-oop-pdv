package br.com.pdv.ses.repository.venda;

import br.com.pdv.ses.domain.venda.Venda;
import br.com.pdv.ses.repository.venda.dao.VendaDAO;

import java.util.Optional;

public class VendaRepositoryImpl implements VendaRepository {

    private final VendaDAO dao;

    public VendaRepositoryImpl (
            VendaDAO dao
    ) {
        this.dao = dao;
    }

    @Override
    public void adicionar(Venda venda) {
        dao.adicionar(venda);
    }

    @Override
    public Optional<Venda> buscar(Integer id) {
        return dao.buscar(id);
    }

    @Override
    public void atualizar(Venda venda) {
        dao.atualizar(venda);
    }

    @Override
    public void remover(Integer id) {
        dao.remover(id);
    }

    @Override
    public Integer proximoId() {
        return dao.proximoIdVenda();
    }
}
