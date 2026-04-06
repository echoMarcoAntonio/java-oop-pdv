package br.com.pdv.ses.dto.venda;

import br.com.pdv.ses.domain.venda.ItemVenda;
import br.com.pdv.ses.domain.venda.Status;

import java.time.LocalDateTime;
import java.util.List;

public record VendaResponseDTO(

        Integer id,

        List<ItemVenda> itens,

        Status status,

        String nmUsuarioCriacao,

        String nmUsuarioAlteracao,

        LocalDateTime dtHrCriacao,

        LocalDateTime dtHrAlteracao
) {
}

