package br.com.pdv.ses.mapper.produto;

import br.com.pdv.ses.dto.produto.ProdutoRequestDTO;
import br.com.pdv.ses.dto.produto.ProdutoResponseDTO;
import br.com.pdv.ses.domain.produto.Categoria;
import br.com.pdv.ses.domain.produto.CodigoBarra;
import br.com.pdv.ses.domain.produto.Produto;
import br.com.pdv.ses.domain.produto.Setor;

/**
 * o mapper recebe a requisicao do controller e transforma antes de mandar pro service
 */
public class ProdutoMapper {

    private ProdutoMapper() {

    }

    public static Produto toEntity(ProdutoRequestDTO dto, Integer id) {
        return new Produto(
                id,
                new CodigoBarra(dto.cdBarra()),
                dto.nome(),
                dto.preco(),
                Setor.valueOf(dto.setor()),
                Categoria.valueOf(dto.categoria())
        );
    }

    public static ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getCdBarra().getCdBarra(),
                produto.getNome(),
                produto.getPreco(),
                produto.getDesconto(),
                produto.getSetor().name(),
                produto.getCategoria().name(),
                produto.getNmUsuarioCriacao(),
                produto.getNmUsuarioAlteracao(),
                produto.getDtHrCriacao(),
                produto.getDtHrAlteracao()
        );
    }
}
