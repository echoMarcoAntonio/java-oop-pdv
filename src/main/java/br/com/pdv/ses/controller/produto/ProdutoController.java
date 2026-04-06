package br.com.pdv.ses.controller.produto;

import br.com.pdv.ses.domain.produto.Produto;
import br.com.pdv.ses.dto.produto.ProdutoRequestDTO;
import br.com.pdv.ses.dto.produto.ProdutoResponseDTO;
import br.com.pdv.ses.mapper.produto.ProdutoMapper;
import br.com.pdv.ses.service.produto.ProdutoService;

import java.util.Optional;

public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(
            ProdutoService service
    ) {
        this.service = service;
    }

    public ProdutoResponseDTO adicionar(ProdutoRequestDTO dto) {
        Integer id = service.proximoId();
        Produto produto = ProdutoMapper.toEntity(dto, id);
        Produto salvo = service.adicionar(produto);
        return ProdutoMapper.toResponse(salvo);
    }

    public Optional<ProdutoResponseDTO> buscar(Integer id) {
        return service.buscar(id)
                .map(ProdutoMapper::toResponse);
    }

    public ProdutoResponseDTO atualizar(Integer id, ProdutoRequestDTO dto) {
        Produto produto = ProdutoMapper.toEntity(dto, id);
        Produto atualizado = service.atualizar(produto);
        return ProdutoMapper.toResponse(atualizado);
    }

    public void remover(Integer id) {
        service.buscar(id);
        service.remover(id);
    }
}
