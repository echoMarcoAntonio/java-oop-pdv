package br.com.pdv.ses.service.venda;

import br.com.pdv.ses.common.audit.UsuarioContexto;
import br.com.pdv.ses.common.exception.venda.VendaNaoEncontradaException;
import br.com.pdv.ses.domain.venda.Venda;
import br.com.pdv.ses.repository.venda.VendaRepository;

import java.util.Optional;

import static br.com.pdv.ses.common.exception.MensagensExcecao.VENDA_INEXISTENTE;

public class VendaService {

    private final VendaRepository repository;

    public VendaService(
            VendaRepository repository
    ) {
        this.repository = repository;
    }

    public Integer proximoId() {
        return repository.proximoId();
    }

    public Venda adicionar(Venda venda) {
        UsuarioContexto.set("sistema");
        try {
            repository.adicionar(venda);
            return venda;
        } finally {
            UsuarioContexto.limpar();
        }
    }

    public Optional<Venda> buscar(Integer id) {
        return repository.buscar(id);
    }

    /**
     o merge do JPA implementado em DAO, não valida se o registro existe ou não.
     */
    public Venda atualizar(Venda venda) {
        repository.buscar(venda.getId())
                .orElseThrow(() -> new VendaNaoEncontradaException(VENDA_INEXISTENTE));

        UsuarioContexto.set("sistema"); // bomba! para bailar tu cuerpo..
        try {
            repository.atualizar(venda);
            return venda;
        } finally {
            UsuarioContexto.limpar();
        }
    }

    public void remover(Integer id) {
        repository.buscar(id)
                .orElseThrow(() -> new VendaNaoEncontradaException(VENDA_INEXISTENTE));
        repository.remover(id);
    }
}
