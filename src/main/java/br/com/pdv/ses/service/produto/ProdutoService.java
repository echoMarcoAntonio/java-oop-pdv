package br.com.pdv.ses.service.produto;

import br.com.pdv.ses.common.audit.UsuarioContexto;
import br.com.pdv.ses.common.exception.produto.ProdutoNaoEncontradoException;
import br.com.pdv.ses.domain.produto.Produto;
import br.com.pdv.ses.repository.produto.ProdutoRepository;

import java.util.Optional;

import static br.com.pdv.ses.common.exception.MensagensExcecao.PRODUTO_INEXISTENTE;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(
            ProdutoRepository repository
    ) {
        this.repository = repository;
    }

    public Integer proximoId() {
        return repository.proximoId();
    }

    public Produto adicionar(Produto produto) {
        UsuarioContexto.set("sistema"); // bomba provisória
        try {
            repository.adicionar(produto);
            return produto;
        } finally {
            UsuarioContexto.limpar(); // evita vazamento no pool de threads
        }
    }

    public Optional<Produto> buscar(Integer id) {
        return repository.buscar(id);
    }

    /**
     o merge do JPA implementado em DAO, não valida se o registro existe ou não.
     */
    public Produto atualizar(Produto produto) {
        repository.buscar(produto.getId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(PRODUTO_INEXISTENTE));

        UsuarioContexto.set("sistema"); // bomba hardcodada
        try {
            repository.atualizar(produto); // repository recebe produto, nao dto
            return produto;
        } finally {
            UsuarioContexto.limpar();
        }
    }

    public void remover(Integer id) {
        repository.buscar(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(PRODUTO_INEXISTENTE));
        repository.remover(id);
    }
}
