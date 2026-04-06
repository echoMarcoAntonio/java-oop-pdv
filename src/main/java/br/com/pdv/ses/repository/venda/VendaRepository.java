package br.com.pdv.ses.repository.venda;

import br.com.pdv.ses.domain.venda.Venda;

import java.util.Optional;

public interface VendaRepository {

    void adicionar(Venda venda);

    Optional<Venda> buscar(Integer id);

    void atualizar(Venda venda);

    void remover(Integer id);

    Integer proximoId();
}
