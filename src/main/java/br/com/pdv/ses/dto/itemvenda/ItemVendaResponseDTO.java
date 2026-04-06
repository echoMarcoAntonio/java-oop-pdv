package br.com.pdv.ses.dto.itemvenda;

import br.com.pdv.ses.domain.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemVendaResponseDTO(

        Integer id,

        Produto idProduto,

        BigDecimal precoUnitario,

        BigDecimal subtotal,

        int quantidade,

        String nmUsuarioCriacao,

        String nmUsuarioAlteracao,

        LocalDateTime dtHrCriacao,

        LocalDateTime dtHrAlteracao
) {
}
