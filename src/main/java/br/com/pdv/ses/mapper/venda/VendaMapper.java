package br.com.pdv.ses.mapper.venda;

import br.com.pdv.ses.domain.venda.Venda;
import br.com.pdv.ses.dto.venda.VendaRequestDTO;
import br.com.pdv.ses.dto.venda.VendaResponseDTO;

public class VendaMapper {

    private VendaMapper() {
    }

    public static Venda toEntity(VendaRequestDTO dto, Integer id) {
        // instancia o Aggregate Root e setta id
        Venda venda = new Venda();
        venda.setId(id);

        // delega a criação de itens para a própria entidade
        if (dto.itens() != null) {
            dto.itens().forEach(itemDTO ->
                    venda.adicionarItemVenda(
                            itemDTO.idProduto(),
                            itemDTO.quantidade()
                    ));
        }

        return venda;
    }

    public static VendaResponseDTO toResponse(Venda venda) {
        return new VendaResponseDTO(
                venda.getId(),
                venda.getItens(),
                venda.getStatus(),
                venda.getNmUsuarioCriacao(),
                venda.getNmUsuarioAlteracao(),
                venda.getDtHrCriacao(),
                venda.getDtHrAlteracao()
        );
    }
}
